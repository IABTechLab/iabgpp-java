package com.iab.gpp.encoder.datatype.encoder;

import java.util.Collection;
import java.util.PrimitiveIterator;
import java.util.Set;
import java.util.stream.IntStream;

public interface IntegerSet extends Set<Integer> {
  boolean containsInt(int value);

  default boolean containsAny(Collection<?> c) {
    for (Object e : c) {
      if (!contains(e)) {
          return false;
      }
    }
    return true;
  }

  boolean addInt(int value);

  boolean removeInt(int value);

  IntStream intStream();

  @Override
  PrimitiveIterator.OfInt iterator();
}
