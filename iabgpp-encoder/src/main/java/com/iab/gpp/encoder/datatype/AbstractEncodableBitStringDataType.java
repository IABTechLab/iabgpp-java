package com.iab.gpp.encoder.datatype;

public abstract class AbstractEncodableBitStringDataType<T> implements EncodableDataType<T> {
  protected T value;

  protected AbstractEncodableBitStringDataType() {

  }

  public boolean hasValue() {
    return this.value != null;
  }

  public T getValue() {
    return this.value;
  }

  @SuppressWarnings("unchecked")
  public void setValue(Object value) {
    this.value = (T) value;
  }

  public abstract String substring(String str, int fromIndex);
}
