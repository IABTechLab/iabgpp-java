package com.iab.gpp.encoder.datatype;

public interface DataType<T> {
  boolean hasValue();
  T getValue();
  void setValue(Object value);
  boolean isDirty();
  void setDirty(boolean dirty);
}
