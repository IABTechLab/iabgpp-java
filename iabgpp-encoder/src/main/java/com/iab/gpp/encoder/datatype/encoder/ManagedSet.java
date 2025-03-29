package com.iab.gpp.encoder.datatype.encoder;

import java.util.PrimitiveIterator;
import com.iab.gpp.encoder.datatype.DataType;

public final class ManagedSet extends IntegerSet {
  
  private final DataType<?> parent;
  private final IntegerSet delegate;
  
  public ManagedSet(DataType<?> parent, IntegerSet delegate) {
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

}
