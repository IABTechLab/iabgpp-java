package com.iab.gpp.encoder.datatype.encoder;

import java.util.AbstractList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PrimitiveIterator.OfInt;

final class IntegerList extends AbstractList<Integer> {

  private static final int UNKNOWN = -1;

  private final BitSet bitSet;
  // Lazily initiate
  private int size;
  private int[] array;

  IntegerList(BitSet bitSet) {
    this.size = UNKNOWN;
    this.bitSet = bitSet;
  }

  @Override
  public int size() {
    int theSize = this.size;
    if (theSize == UNKNOWN) {
      theSize = bitSet.isEmpty() ? 0 : bitSet.cardinality();
      this.size = theSize;
    }
    return size;
  }

  @Override
  public boolean contains(Object o) {
    return o instanceof Integer && bitSet.get((Integer) o);
  }

  @Override
  public void sort(Comparator<? super Integer> c) {
    // This is already sorted
  }

  @Override
  public OfInt iterator(){
    return new OfInt() {
      int cursor = bitSet.nextSetBit(0);
  
      @Override
      public boolean hasNext() {
        return cursor != -1;
      }
  
      @Override
      public Integer next() {
        return IntegerCache.valueOf(nextInt());
      }
  
      @Override
      public int nextInt() {
        if (!this.hasNext()) {
          throw new NoSuchElementException();
        }
        int next = cursor;
        cursor = bitSet.nextSetBit(cursor + 1);
        return next;
      }
    };
  }

  @Override
  public Integer get(int index) {
    int[] theArray = this.array;
    if (theArray == null) {
      // Lazily construct a backing array
      theArray = new int[size()];
      int count = 0;
      for (OfInt it = iterator(); it.hasNext();) {
        theArray[count++] = it.nextInt();
      }
      array = theArray;
    }
    return IntegerCache.valueOf(theArray[index]);
  }
}
