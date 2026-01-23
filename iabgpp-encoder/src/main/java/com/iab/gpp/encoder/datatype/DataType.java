package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.datatype.encoder.Dirtyable;

public abstract class DataType<T> implements Dirtyable {
  public abstract boolean hasValue();
  public abstract T getValue();
  public abstract void setValue(Object value);
}
