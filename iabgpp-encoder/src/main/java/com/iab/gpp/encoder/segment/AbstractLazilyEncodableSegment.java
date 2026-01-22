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
  private final Object[] values;

  
  public final DataType<?> getField(E fieldName) {
    ensureDecode();
    return get(fieldName);
  }
  
  public final DataType<?> getField(String fieldName) {
    return get(fieldNames.convertKey(fieldName));
  }

  protected void initialize(E key, T value) {
    Integer index = fieldNames.getIndex(key);
    if (index == null) {
      throw new IllegalArgumentException("invalid key "+ key);
    }
    values[index] = value;
  }

  @SuppressWarnings("unchecked")
  protected T get(int index) {
    return (T) values[index];
  }

  protected T get(E key) {
    Integer index = fieldNames.getIndex(key);
    if (index != null) {
      return get(index);
    }
    return null;
  }

  public final boolean isDirty() {
    int size = fieldNames.size();
    for (int i = 0; i < size; i++) {
      T value = get(i);
      if (value != null && value.isDirty()) {
        return true;
      }
    }
    return false;
  }

  public final void setDirty(boolean dirty) {
    int size = fieldNames.size();
    for (int i = 0; i < size; i++) {
      T value = get(i);
      if (value != null) {
        value.setDirty(dirty);
      }
    }
  }


  protected AbstractLazilyEncodableSegment(FieldNames<E> fieldNames) {
    this.fieldNames = fieldNames;
    this.values = new Object[fieldNames.size()];
  }

  public Object getFieldValue(E fieldName) {
    ensureDecode();

    DataType<?> field = this.get(fieldName);
    if (field != null) {
      return field.getValue();
    } else {
      throw new InvalidFieldException("Invalid field: '" + fieldName + "'");
    }
  }

  public void setFieldValue(E fieldName, Object value) {
    ensureDecode();

    DataType<?> field = this.get(fieldName);
    if (field != null) {
      field.setValue(value);
    } else {
      throw new InvalidFieldException(fieldName + " not found");
    }
  }

  public String toString() {
    ensureDecode();
    StringBuilder sb = new StringBuilder();
    sb.append("{name=").append(getClass().getSimpleName());
    int size = fieldNames.size();
    for (int i = 0; i < size; i++) {
      E field = fieldNames.get(i);
      sb.append(", ").append(field).append('=').append(get(field));
    }
    sb.append('}');
    return sb.toString();
  }

}
