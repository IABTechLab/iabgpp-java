package com.iab.gpp.encoder.section;

import java.util.ArrayList;
import java.util.List;
import com.iab.gpp.encoder.datatype.DataType;
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

  @Override
  protected void doDecode(CharSequence encodedString) {
    int numSegments = size();
    if (numSegments == 1) {
      getSegment(0).decode(encodedString);
      return;
    }
    List<CharSequence> encodedSegments = SlicedCharSequence.split(encodedString, '.');
    for (int i = 0; i < numSegments; i++) {
      getSegment(i).decode(encodedSegments.get(i));
    }
  }

  @Override
  protected CharSequence doEncode() {
    int numSegments = size();
    if (numSegments == 1) {
      return getSegment(0).encodeCharSequence();
    }
    List<CharSequence> encodedSegments = new ArrayList<>(numSegments);
    for (int i = 0; i < numSegments; i++) {
      encodedSegments.add(getSegment(i).encodeCharSequence());
    }
    return SlicedCharSequence.join('.',  encodedSegments);
  }

  public final boolean hasField(FieldKey fieldName) {
    ensureDecode();

    int numSegments = size();
    for (int i = 0; i < numSegments; i++) {
      EncodableSegment<E> segment = getSegment(i);
      E key = segment.resolveKey(fieldName);
      if (key != null && segment.getField(key) != null) {
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
      if (segment.getField(fieldName) != null) {
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
        DataType<?> field = segment.getField(key);
        if (field != null) {
          return field.getValue();
        }
      }
    }

    throw new InvalidFieldException("Invalid field: '" + fieldName + "'");
  }

  public final Object getFieldValue(E fieldName) {
    ensureDecode();

    int numSegments = size();
    for (int i = 0; i < numSegments; i++) {
      EncodableSegment<E> segment = getSegment(i);
      DataType<?> field = segment.getField(fieldName);
      if (field != null) {
        return field.getValue();
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
        DataType<?> field = segment.getField(key);
        if (field != null) {
          field.setValue(value);
          onSet(key);
          return;
        }
      }
    }

    throw new InvalidFieldException("Invalid field: '" + fieldName + "'");
  }

  public final void setFieldValue(E fieldName, Object value) {
    ensureDecode();

    int numSegments = size();
    for (int i = 0; i < numSegments; i++) {
      EncodableSegment<E> segment = getSegment(i);
      DataType<?> field = segment.getField(fieldName);
      if(field != null) {
        field.setValue(value);
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
