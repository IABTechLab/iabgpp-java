package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.datatype.encoder.Dirtyable;

public interface DataType<T> extends Dirtyable {
  boolean hasValue();
  T getValue();
  void setValue(Object value);
}
