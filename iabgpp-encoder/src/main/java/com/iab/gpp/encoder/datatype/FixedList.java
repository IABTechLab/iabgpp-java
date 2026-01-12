package com.iab.gpp.encoder.datatype;

import java.util.AbstractList;
import java.util.List;
import com.iab.gpp.encoder.datatype.encoder.Dirtyable;

public final class FixedList<T> extends AbstractList<T> implements Dirtyable {

  private boolean dirty;
  private final List<T> delegate;

  FixedList(List<T> delegate) {
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
      dirty = true;
    }
    return prior;
  }

  @Override
  public String toString() {
    return delegate.toString();
  }

  @Override
  public boolean isDirty() {
    int size = delegate.size();
    for (int i = 0; i < size; i++) {
      T value = delegate.get(i);
      if (value instanceof Dirtyable && ((Dirtyable) value).isDirty()) {
        return true;
      }
    }
    return dirty;
  }

  @Override
  public void setDirty(boolean dirty) {
    int size = delegate.size();
    for (int i = 0; i < size; i++) {
      T value = delegate.get(i);
      if (value instanceof Dirtyable) {
        ((Dirtyable) value).setDirty(dirty);
      }
    }
    this.dirty = dirty;
  }
}
