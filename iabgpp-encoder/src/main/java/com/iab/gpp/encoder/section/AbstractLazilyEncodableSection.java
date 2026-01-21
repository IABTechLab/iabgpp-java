package com.iab.gpp.encoder.section;

import java.util.List;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.segment.EncodableSegment;

abstract class AbstractLazilyEncodableSection<E extends Enum<E> & FieldKey> extends EncodableSection<E> {

  protected List<EncodableSegment<E>> segments;

  private CharSequence encodedString = null;

  private boolean dirty = false;
  private boolean decoded = true;

  protected AbstractLazilyEncodableSection() {
    this.segments = initializeSegments();
  }

  protected abstract List<EncodableSegment<E>> initializeSegments();

  protected abstract CharSequence encodeSection(List<EncodableSegment<E>> segments);

  protected abstract List<EncodableSegment<E>> decodeSection(CharSequence encodedString);

  public boolean hasField(String fieldName) {
    if (!this.decoded) {
      this.segments = this.decodeSection(this.encodedString);
      this.dirty = false;
      this.decoded = true;
    }

    int numSegments = segments.size();
    for (int i = 0; i < numSegments; i++) {
      EncodableSegment<E> segment = segments.get(i);
      if (segment.hasField(fieldName)) {
        return true;
      }
    }

    return false;
  }
  
  public boolean hasField(E fieldName) {
    if (!this.decoded) {
      this.segments = this.decodeSection(this.encodedString);
      this.dirty = false;
      this.decoded = true;
    }

    int numSegments = segments.size();
    for (int i = 0; i < numSegments; i++) {
      EncodableSegment<E> segment = segments.get(i);
      if (segment.hasField(fieldName)) {
        return true;
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

    int numSegments = segments.size();
    for (int i = 0; i < numSegments; i++) {
      EncodableSegment<E> segment = segments.get(i);
      if(segment.hasField(fieldName)) {
        return segment.getFieldValue(fieldName);
      }
    }

    throw new InvalidFieldException("Invalid field: '" + fieldName + "'");
  }
  
  public Object getFieldValue(E fieldName) {
    if (!this.decoded) {
      this.segments = this.decodeSection(this.encodedString);
      this.dirty = false;
      this.decoded = true;
    }

    int numSegments = segments.size();
    for (int i = 0; i < numSegments; i++) {
      EncodableSegment<E> segment = segments.get(i);
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

    int numSegments = segments.size();
    for (int i = 0; i < numSegments; i++) {
      EncodableSegment<E> segment = segments.get(i);
      if(segment.hasField(fieldName)) {
        segment.setFieldValue(fieldName, value);
        this.dirty = true;
        return;
      }
    }

    throw new InvalidFieldException("Invalid field: '" + fieldName + "'");
  }
  
  public void setFieldValue(E fieldName, Object value) {
    if (!this.decoded) {
      this.segments = this.decodeSection(this.encodedString);
      this.dirty = false;
      this.decoded = true;
    }

    int numSegments = segments.size();
    for (int i = 0; i < numSegments; i++) {
      EncodableSegment<E> segment = segments.get(i);
      if(segment.hasField(fieldName)) {
        segment.setFieldValue(fieldName, value);
        this.dirty = true;
        return;
      }
    }

    throw new InvalidFieldException("Invalid field: '" + fieldName + "'");
  }

  public String encode() {
    return encodeCharSequence().toString();
  }

  public CharSequence encodeCharSequence() {
    if (this.encodedString == null || this.encodedString.length() == 0 || this.dirty) {
      this.encodedString = this.encodeSection(this.segments);
      this.dirty = false;
      this.decoded = true;
    }

    return this.encodedString;
  }

  public void decode(CharSequence encodedString) {
    this.encodedString = encodedString;
    this.dirty = false;
    this.decoded = false;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{id=").append(getId()).append(", name=").append(getName()).append(", version=").append(getVersion());
    for (EncodableSegment<E> segment: segments) {
      sb.append(", ").append(segment.toString());
    }
    sb.append('}');
    return sb.toString();
  }
}
