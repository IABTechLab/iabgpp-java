package com.iab.gpp.encoder.datatype;

import java.util.PrimitiveIterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import com.iab.gpp.encoder.datatype.encoder.BaseIntegerSet;
import com.iab.gpp.encoder.datatype.encoder.IntegerSet;

final class ManagedIntegerSet extends BaseIntegerSet {
  
  private final DataType<?> parent;
  private final IntegerSet delegate;
  
  ManagedIntegerSet(DataType<?> parent, IntegerSet delegate) {
    this.parent = parent;
    this.delegate = delegate;
  }
  
  @Override
  public void clear() {
    delegate.clear();
    parent.setDirty(true);
  }
  
  @Override
  public boolean isEmpty() {
    return delegate.isEmpty();
  }
  
  @Override
  public int size() {
    return delegate.size();
  }
  
  @Override
  public PrimitiveIterator.OfInt iterator() {
    return delegate.iterator();
  }
  
  @Override
  public Stream<Integer> stream(){
    return delegate.stream();
  }
  
  @Override
  public IntStream intStream() {
    return delegate.intStream();
  }
  
  @Override
  public boolean containsInt(int value) {
    return delegate.containsInt(value);
  }

  @Override
  public boolean addInt(int value) {
    boolean modified = delegate.addInt(value);
    if (modified) {
      parent.setDirty(true);
    }
    return modified;
  }

  @Override
  public boolean removeInt(int value) {
    boolean modified = delegate.removeInt(value);
    if (modified) {
      parent.setDirty(true);
    }
    return modified;
  }

  @Override
  public String toString() {
    return delegate.toString();
  }
}
