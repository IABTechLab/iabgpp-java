package com.iab.gpp.encoder.segment;

import java.util.function.Predicate;
import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.FixedIntegerList;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.field.FieldNames;

abstract class AbstractLazilyEncodableSegment<E extends Enum<E> & FieldKey, T extends DataType<?>> extends EncodableSegment<E> {

  protected static final Predicate<Integer> nullableBooleanAsTwoBitIntegerValidator = (n -> n >= 0 && n <= 2);
  protected static final Predicate<Integer> nonNullableBooleanAsTwoBitIntegerValidator = (n -> n >= 1 && n <= 2);
  protected static final Predicate<FixedIntegerList> nullableBooleanAsTwoBitIntegerListValidator = (l -> {
    for (int n : l) {
      if (n < 0 || n > 2) {
        return false;
      }
    }
    return true;
  });

  protected final FieldNames<E> fieldNames;
  private final Object[] types;
  protected final Object[] values;
  private boolean dirty;

  protected AbstractLazilyEncodableSegment(FieldNames<E> fieldNames) {
    this.fieldNames = fieldNames;
    this.values = new Object[fieldNames.size()];
    // TODO: move to FieldNames
    this.types = new Object[fieldNames.size()];
  }

  @Override
  public final E resolveKey(FieldKey fieldName) {
    return fieldNames.resolveKey(fieldName);
  }

  protected final void initialize(E key, T value) {
    Integer index = fieldNames.getIndex(key);
    if (index == null) {
      throw new IllegalArgumentException("invalid key "+ key);
    }
    types[index] = value;
  }

  @SuppressWarnings("unchecked")
  protected final T get(int index) {
    return (T) types[index];
  }

  protected final T get(E key) {
    Integer index = fieldNames.getIndex(key);
    if (index != null) {
      return get(index);
    }
    return null;
  }

  @Override
  public final boolean hasField(E key) {
    return get(key) != null;
  }

  @Override
  public final boolean isDirty() {
    if (dirty) {
      return dirty;
    }
    int size = fieldNames.size();
    for (int i = 0; i < size; i++) {
      if (get(i).isDirty(values, i)) {
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
      get(i).setDirty(values, i, dirty);
    }
  }

  @Override
  public final Object getFieldValue(E fieldName) {
    ensureDecode();
    return getFieldValueUnsafe(fieldName);
  }
  
  protected final Object getFieldValueUnsafe(E fieldName) {
    Integer index = fieldNames.getIndex(fieldName);
    if (index != null) {
      return get(index).get(values, index);
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
      get(index).set(values, index, value);
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
      E field = fieldNames.get(i);
      sb.append(", ").append(field.getName()).append('=').append(values[i]);
    }
    sb.append('}');
    return sb.toString();
  }

}
