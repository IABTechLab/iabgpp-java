package com.iab.gpp.encoder.section;

import java.util.ArrayList;
import java.util.List;
import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.segment.EncodableSegment;

public abstract class EncodableSection<E extends Enum<E> & FieldKey> extends AbstractEncodable {

  protected final List<EncodableSegment<E>> segments;

  protected EncodableSection(List<EncodableSegment<E>> segments) {
    this.segments = segments;
  }

  public abstract int getId();

  public abstract String getName();

  public abstract int getVersion();

  @Override
  protected void doDecode(CharSequence encodedString) {
    int numSegments = segments.size();
    if (numSegments == 1) {
      segments.get(0).decode(encodedString);
      return;
    }
    List<CharSequence> encodedSegments = SlicedCharSequence.split(encodedString, '.');
    for (int i = 0; i < numSegments; i++) {
      segments.get(i).decode(encodedSegments.get(i));
    }
  }

  @Override
  protected CharSequence doEncode() {
    int numSegments = segments.size();
    if (numSegments == 1) {
      return segments.get(0).encodeCharSequence();
    }
    List<CharSequence> encodedSegments = new ArrayList<>(numSegments);
    for (int i = 0; i < numSegments; i++) {
      encodedSegments.add(segments.get(i).encodeCharSequence());
    }
    return SlicedCharSequence.join('.',  encodedSegments);
  }

  public final boolean hasField(FieldKey fieldName) {
    ensureDecode();

    int numSegments = segments.size();
    for (int i = 0; i < numSegments; i++) {
      EncodableSegment<E> segment = segments.get(i);
      E key = segment.resolveKey(fieldName);
      if (key != null && segment.getField(key) != null) {
        return true;
      }
    }

    return false;
  }

  public final boolean hasField(E fieldName) {
    ensureDecode();

    int numSegments = segments.size();
    for (int i = 0; i < numSegments; i++) {
      EncodableSegment<E> segment = segments.get(i);
      if (segment.getField(fieldName) != null) {
        return true;
      }
    }

    return false;
  }

  public final Object getFieldValue(FieldKey fieldName) {
    ensureDecode();

    int numSegments = segments.size();
    for (int i = 0; i < numSegments; i++) {
      EncodableSegment<E> segment = segments.get(i);
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

    int numSegments = segments.size();
    for (int i = 0; i < numSegments; i++) {
      EncodableSegment<E> segment = segments.get(i);
      DataType<?> field = segment.getField(fieldName);
      if (field != null) {
        return field.getValue();
      }
    }

    throw new InvalidFieldException("Invalid field: '" + fieldName + "'");
  }

  public final void setFieldValue(FieldKey fieldName, Object value) {
    ensureDecode();

    int numSegments = segments.size();
    for (int i = 0; i < numSegments; i++) {
      EncodableSegment<E> segment = segments.get(i);
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

    int numSegments = segments.size();
    for (int i = 0; i < numSegments; i++) {
      EncodableSegment<E> segment = segments.get(i);
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
    int numSegments = segments.size();
    for (int i = 0; i < numSegments; i++) {
      if (segments.get(i).isDirty()) {
        return true;
      }
    }
    return false;
  }

  @Override
  public final void setDirty(boolean dirty) {
    int numSegments = segments.size();
    for (int i = 0; i < numSegments; i++) {
      segments.get(i).setDirty(dirty);
    }
  }

  @Override
  public final String toString() {
    ensureDecode();
    StringBuilder sb = new StringBuilder();
    sb.append("{id=").append(getId()).append(", name=").append(getName()).append(", version=").append(getVersion());
    for (EncodableSegment<E> segment : segments) {
      sb.append(", ").append(segment.toString());
    }
    sb.append('}');
    return sb.toString();
  }

}
