package com.iab.gpp.encoder.section;

import java.util.List;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.segment.EncodableSegment;

public abstract class AbstractLazilyEncodableSection implements EncodableSection {

  private List<EncodableSegment> segments;
  
  private CharSequence encodedString = null;

  private boolean dirty = false;
  private boolean decoded = true;

  public AbstractLazilyEncodableSection() {
    this.segments = initializeSegments();
  }

  protected abstract List<EncodableSegment> initializeSegments();
  
  protected abstract String encodeSection(List<EncodableSegment> segments);

  protected abstract List<EncodableSegment> decodeSection(CharSequence encodedString);
  
  public boolean hasField(String fieldName) {
    if (!this.decoded) {
      this.segments = this.decodeSection(this.encodedString);
      this.dirty = false;
      this.decoded = true;
    }
    
    for(EncodableSegment segment : segments) {
      if(segment.getFieldNames().contains(fieldName)) {
        return segment.hasField(fieldName);
      }
    }
    
    return false;
  }

  public Object getFieldValue(String fieldName) {
    if (!this.decoded) {
      this.segments = this.decodeSection(this.encodedString);
      this.dirty = false;
      this.decoded = true;
    }

    for(EncodableSegment segment : segments) {
      if(segment.hasField(fieldName)) {
        return segment.getFieldValue(fieldName);
      }
    }

    throw new InvalidFieldException("Invalid field: '" + fieldName + "'");
  }

  public void setFieldValue(String fieldName, Object value) {
    if (!this.decoded) {
      this.segments = this.decodeSection(this.encodedString);
      this.dirty = false;
      this.decoded = true;
    }

    for(EncodableSegment segment : segments) {
      if(segment.hasField(fieldName)) {
        segment.setFieldValue(fieldName, value);
        this.dirty = true;
        return;
      }
    }

    throw new InvalidFieldException("Invalid field: '" + fieldName + "'");
  }

  public String encode() {
    if (this.encodedString == null || this.encodedString.length() == 0 || this.dirty) {
      this.encodedString = this.encodeSection(this.segments);
      this.dirty = false;
      this.decoded = true;
    }

    return this.encodedString.toString();
  }

  public void decode(CharSequence encodedString) {
    this.encodedString = encodedString;
    this.dirty = false;
    this.decoded = false;
  }
  
}
