package com.iab.gpp.encoder.datatype;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import com.iab.gpp.encoder.datatype.encoder.Dirtyable;

// This class tracks whether a list has been modified.
final class DirtyableList<T extends Dirtyable> extends AbstractList<T> implements Dirtyable {

  private boolean dirty;
  private final ArrayList<T> delegate;

  DirtyableList() {
    this.delegate = new ArrayList<>();
  }

  @Override
  public int size() {
    return delegate.size();
  }

  @Override
  public boolean add(T value) {
    boolean result = delegate.add(value);
    dirty = true;
    return result;
  }

  @Override
  public T get(int index) {
    return delegate.get(index);
  }

  @Override
  public T set(int index, T value) {
    T prior = delegate.set(index, value);
    dirty = true;
    return prior;
  }

  @Override
  public void add(int index, T element) {
    delegate.add(index, element);
    dirty = true;
  }

  @Override
  public T remove(int index) {
    T old = delegate.remove(index);
    dirty = true;
    return old;
  }

  @Override
  public int indexOf(Object o) {
    return delegate.indexOf(o);
  }

  @Override
  public int lastIndexOf(Object o) {
    return delegate.lastIndexOf(o);
  }

  @Override
  public void clear() {
    delegate.clear();
    dirty = true;
  }

  @Override
  public boolean addAll(Collection<? extends T> c) {
    boolean result = delegate.addAll(c);
    dirty = true;
    return result;
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
      if (value.isDirty()) {
        return true;
      }
    }
    return dirty;
  }

  @Override
  public void setDirty(boolean dirty) {
    int size = delegate.size();
    for (int i = 0; i < size; i++) {
      delegate.get(i).setDirty(dirty);
    }
    this.dirty = dirty;
  }
}
