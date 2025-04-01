package com.iab.gpp.encoder.datatype.encoder;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.PrimitiveIterator;

public abstract class BaseIntegerSet extends AbstractSet<Integer> implements IntegerSet {
  public abstract boolean containsInt(int value);
  
  @Override
  public final boolean contains(Object value) {
    if (value instanceof Integer) {
      return containsInt((Integer) value);
    }
    return false; 
  }
  
  @Override
  public final boolean add(Integer value) {
    if (value == null) {
      return false;
    }
    return addInt((Integer) value);
  }
  
  public abstract boolean addInt(int value);
  
  @Override
  public final boolean remove(Object value) {
    if (value instanceof Integer) {
      return removeInt((Integer) value);
    }
    return false;
  }
  
  @Override
  public boolean removeAll(Collection<?> c) {
    boolean modified = false;
    for (Integer i : this) {
      if (c.contains(i)) {
        remove(i);
        modified = true;
      }
    }
    return modified;
  }
  
  @Override
  public boolean retainAll(Collection<?> c) {
    boolean modified = false;
    for (Integer i : this) {
      if (!c.contains(i)) {
        remove(i);
        modified = true;
      }
    }
    return modified;
  }

  public abstract boolean removeInt(int value);
  
  @Override
  public abstract PrimitiveIterator.OfInt iterator();
}
