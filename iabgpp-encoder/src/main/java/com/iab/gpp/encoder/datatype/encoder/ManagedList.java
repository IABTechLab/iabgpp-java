package com.iab.gpp.encoder.datatype.encoder;

import java.util.AbstractList;
import java.util.List;
import com.iab.gpp.encoder.datatype.DataType;

public final class ManagedList<T> extends AbstractList<T> {
  
  private final DataType<?> parent;
  private final List<T> delegate;
  
  public ManagedList(DataType<?> parent, List<T> delegate) {
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
}
