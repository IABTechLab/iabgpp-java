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

  protected boolean containsKey(E key) {
    Integer index = fieldNames.getIndex(key);
    return index != null && values[index] != null;
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

  protected boolean isDirty() {
    int size = fieldNames.size();
    for (int i = 0; i < size; i++) {
      T value = get(i);
      if (value != null && value.isDirty()) {
        return true;
      }
    }
    return false;
  }

  protected void markClean() {
    int size = fieldNames.size();
    for (int i = 0; i < size; i++) {
      T value = get(i);
      if (value != null) {
        value.setDirty(false);
      }
    }
  }

  private CharSequence encodedString = null;

  private boolean decoded = true;

  protected AbstractLazilyEncodableSegment(FieldNames<E> fieldNames) {
    this.fieldNames = fieldNames;
    this.values = new Object[fieldNames.size()];
  }

  protected abstract StringBuilder encodeSegment();

  protected abstract void decodeSegment(CharSequence encodedString);

  public boolean hasField(String fieldName) {
    E key = fieldNames.convertKey(fieldName);
    return key != null && hasField(key);
  }

  public boolean hasField(E fieldName) {
    return this.containsKey(fieldName);
  }

  public Object getFieldValue(String fieldName) {
    return getFieldValue(fieldNames.convertKey(fieldName));
  }
  
  public Object getFieldValue(E fieldName) {
    if (!this.decoded) {
      this.decodeSegment(this.encodedString);
      this.markClean();
      this.decoded = true;
    }

    DataType<?> field = this.get(fieldName);
    if (field != null) {
      return field.getValue();
    } else {
      throw new InvalidFieldException("Invalid field: '" + fieldName + "'");
    }
  }

  public void setFieldValue(String fieldName, Object value) {
    setFieldValue(fieldNames.convertKey(fieldName), value);
  }

  public void setFieldValue(E fieldName, Object value) {
    if (!this.decoded) {
      this.decodeSegment(this.encodedString);
      this.markClean();
      this.decoded = true;
    }

    DataType<?> field = this.get(fieldName);
    if (field != null) {
      field.setValue(value);
    } else {
      throw new InvalidFieldException(fieldName + " not found");
    }
  }

  public CharSequence encodeCharSequence() {
    if (this.encodedString == null || this.encodedString.length() == 0 || this.isDirty()) {
      this.encodedString = encodeSegment();
      this.markClean();
      this.decoded = true;
    }

    return this.encodedString;
  }

  public void decode(CharSequence encodedString) {
    this.encodedString = encodedString;
    this.markClean();
    this.decoded = false;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{name=").append(getClass().getSimpleName());
    int size = fieldNames.size();
    for (int i = 0; i < size; i++) {
      E field = fieldNames.get(i);
      if (hasField(field)) {
        sb.append(", ").append(field).append('=').append(getFieldValue(field));
      }
    }
    sb.append('}');
    return sb.toString();
  }

}
