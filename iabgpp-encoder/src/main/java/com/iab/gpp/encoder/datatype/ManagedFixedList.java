package com.iab.gpp.encoder.datatype;

import java.util.AbstractList;
import java.util.List;

final class ManagedFixedList<T> extends AbstractList<T> {

  private final DataType<?> parent;
  private final List<T> delegate;

  ManagedFixedList(DataType<?> parent, List<T> delegate) {
    this.parent = parent;
    this.delegate = delegate;
  }

  @Override
  public int size() {
    return delegate.size();
  }

  @Override
  public T get(int index) {
    return delegate.get(index);
  }

  @Override
  public T set(int index, T value) {
    T prior = delegate.set(index, value);
    if (prior != null) {
      parent.setDirty(true);
    }
    return prior;
  }

  @Override
  public String toString() {
    return delegate.toString();
  }
}
