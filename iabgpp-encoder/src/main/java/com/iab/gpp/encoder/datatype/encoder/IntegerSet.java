package com.iab.gpp.encoder.datatype.encoder;

import java.util.PrimitiveIterator;
import java.util.Set;

public interface IntegerSet extends Set<Integer> {
  boolean containsInt(int value);

  boolean addInt(int value);

  boolean removeInt(int value);
  
  @Override
  PrimitiveIterator.OfInt iterator();
}
