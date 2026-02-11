package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.segment.EncodableSegment;

public abstract class EncodableSection<E extends Enum<E> & FieldKey> extends AbstractEncodable {

  private final Object[] segments;

  @SafeVarargs
  protected EncodableSection(EncodableSegment<E>... segments) {
    this.segments = segments;
  }

  protected final int size() {
    return segments.length;
  }

  @SuppressWarnings("unchecked")
  protected final EncodableSegment<E> getSegment(int index) {
    return (EncodableSegment<E>) segments[index];
  }

  public abstract int getId();

  public abstract String getName();

  public abstract int getVersion();

  // this is a default implementation for single segment sections
  @Override
  protected void doDecode(CharSequence encodedString) {
    if (size() > 1) {
      throw new DecodingException("too many sections to decode");
    }
    getSegment(0).decode(encodedString);
  }

  // this is a default implementation for single segment sections
  @Override
  protected CharSequence doEncode() {
    if (size() > 1) {
      throw new EncodingException("too many sections to encode");
    }
    return getSegment(0).encodeCharSequence();
  }

  public final boolean hasField(FieldKey fieldName) {
    ensureDecode();

    int numSegments = size();
    for (int i = 0; i < numSegments; i++) {
      EncodableSegment<E> segment = getSegment(i);
      E key = segment.resolveKey(fieldName);
      if (key != null) {
        return true;
      }
    }

    return false;
  }

  public final boolean hasField(E fieldName) {
    ensureDecode();

    int numSegments = size();
    for (int i = 0; i < numSegments; i++) {
      EncodableSegment<E> segment = getSegment(i);
      if (segment.hasField(fieldName)) {
        return true;
      }
    }

    return false;
  }

  public final Object getFieldValue(FieldKey fieldName) {
    ensureDecode();

    int numSegments = size();
    for (int i = 0; i < numSegments; i++) {
      EncodableSegment<E> segment = getSegment(i);
      E key = segment.resolveKey(fieldName);
      if (key != null) {
        return segment.getFieldValue(key);
      }
    }

    throw new InvalidFieldException("Invalid field: '" + fieldName + "'");
  }

  public final Object getFieldValue(E fieldName) {
    ensureDecode();

    int numSegments = size();
    for (int i = 0; i < numSegments; i++) {
      EncodableSegment<E> segment = getSegment(i);
      if (segment.hasField(fieldName)) {
        return segment.getFieldValue(fieldName);
      }
    }

    throw new InvalidFieldException("Invalid field: '" + fieldName + "'");
  }

  public final void setFieldValue(FieldKey fieldName, Object value) {
    ensureDecode();

    int numSegments = size();
    for (int i = 0; i < numSegments; i++) {
      EncodableSegment<E> segment = getSegment(i);
      E key = segment.resolveKey(fieldName);
      if (key != null) {
        segment.setFieldValue(key, value);
        onSet(key);
        return;
      }
    }

    throw new InvalidFieldException("Invalid field: '" + fieldName + "'");
  }

  public final void setFieldValue(E fieldName, Object value) {
    ensureDecode();

    int numSegments = size();
    for (int i = 0; i < numSegments; i++) {
      EncodableSegment<E> segment = getSegment(i);
      if(segment.hasField(fieldName)) {
        segment.setFieldValue(fieldName, value);
        onSet(fieldName);
        return;
      }
    }

    throw new InvalidFieldException("Invalid field: '" + fieldName + "'");
  }

  protected void onSet(E fieldName) {
    // pass: override this to set last modified fields
  }

  @Override
  public final boolean isDirty() {
    int numSegments = size();
    for (int i = 0; i < numSegments; i++) {
      if (getSegment(i).isDirty()) {
        return true;
      }
    }
    return false;
  }

  @Override
  public final void setDirty(boolean dirty) {
    int numSegments = size();
    for (int i = 0; i < numSegments; i++) {
      getSegment(i).setDirty(dirty);
    }
  }

  @Override
  public final String toString() {
    ensureDecode();
    StringBuilder sb = new StringBuilder();
    sb.append("{id=").append(getId()).append(", name=").append(getName()).append(", version=").append(getVersion());
    int numSegments = size();
    for (int i = 0; i < numSegments; i++) {
      sb.append(", ").append(getSegment(i).toString());
    }
    sb.append('}');
    return sb.toString();
  }

}
