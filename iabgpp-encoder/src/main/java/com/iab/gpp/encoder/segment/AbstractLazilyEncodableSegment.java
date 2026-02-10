package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.field.FieldNames;

abstract class AbstractLazilyEncodableSegment<E extends Enum<E> & FieldKey> extends EncodableSegment<E> {

  protected final FieldNames<E> fieldNames;
  protected final Object[] values;
  private boolean dirty;

  protected AbstractLazilyEncodableSegment(FieldNames<E> fieldNames) {
    this.fieldNames = fieldNames;
    this.values = new Object[fieldNames.size()];
  }

  @Override
  public final E resolveKey(FieldKey fieldName) {
    return fieldNames.resolveKey(fieldName);
  }

  @Override
  public final boolean hasField(E key) {
    return fieldNames.getIndex(key) != null;
  }

  @Override
  public final boolean isDirty() {
    if (dirty) {
      return dirty;
    }
    int size = fieldNames.size();
    for (int i = 0; i < size; i++) {
      if (fieldNames.get(i).isDirty(values, i)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public final void setDirty(boolean dirty) {
    this.dirty = dirty;
    int size = fieldNames.size();
    for (int i = 0; i < size; i++) {
      fieldNames.get(i).setDirty(values, i, dirty);
    }
  }

  @Override
  public final Object getFieldValue(E fieldName) {
    ensureDecode();
    return getFieldValueUnsafe(fieldName);
  }

  @Override
  protected final Object getFieldValueUnsafe(E fieldName) {
    Integer index = fieldNames.getIndex(fieldName);
    if (index != null) {
      return fieldNames.get(index).get(values, index);
    } else {
      throw new InvalidFieldException("Invalid field: '" + fieldName + "'");
    }
  }

  @Override
  public final void setFieldValue(E fieldName, Object value) {
    ensureDecode();
    setFieldValueUnsafe(fieldName, value);
  }

  protected final void setFieldValueUnsafe(E fieldName, Object value) {
    Integer index = fieldNames.getIndex(fieldName);
    if (index != null) {
      fieldNames.get(index).set(values, index, value);
      dirty = true;
    } else {
      throw new InvalidFieldException(fieldName + " not found");
    }
  }

  @Override
  public String toString() {
    ensureDecode();
    StringBuilder sb = new StringBuilder();
    sb.append("{name=").append(getClass().getSimpleName());
    int size = fieldNames.size();
    for (int i = 0; i < size; i++) {
      sb.append(", ").append(fieldNames.get(i).getName()).append('=').append(values[i]);
    }
    sb.append('}');
    return sb.toString();
  }

}
