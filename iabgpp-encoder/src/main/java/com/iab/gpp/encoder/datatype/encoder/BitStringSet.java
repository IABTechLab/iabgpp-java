package com.iab.gpp.encoder.datatype.encoder;

import java.util.BitSet;
import java.util.NoSuchElementException;
import java.util.PrimitiveIterator.OfInt;

public final class BitStringSet extends IntegerSet {
  static final int MAX_COLLECTION_SIZE = 16384;

  protected final BitSet bitSet;
  protected final int from;
  protected final int to;

  public BitStringSet(BitSet bitSet, int from, int to) {
    this.bitSet = bitSet;
    this.from = from;
    this.to = to;
  }
  
  public static final BitStringSet withLimit(int limit) {
    return new BitStringSet(new BitSet(0), 0, limit);
  }
  
  public BitStringSet(int size) {
    this(new BitSet(size), 0, MAX_COLLECTION_SIZE);
  }
  
  public BitStringSet() {
    this(0);
  }

  public static final BitStringSet of(int... values) {
    BitStringSet out = new BitStringSet();
    for (int value : values) {
      out.addInt(value);
    }
    return out;
  }

  @Override
  public int size() {
    OfInt it = iterator();
    int count = 0;
    while (it.hasNext()) {
      it.next();
      count++;
    }
    return count;
  }
  
  @Override
  public void clear() {
    bitSet.clear(from, to);
  }
  
  @Override
  public boolean isEmpty() {
    return bitSet.nextSetBit(from) == -1;
  }

  @Override
  public boolean containsInt(int value) {
    int offset = from + value;
    return offset < to && bitSet.get(offset);
  }

  @Override
  public OfInt iterator() {
    return new OfInt() {
      int cursor = bitSet.nextSetBit(from);
  
      @Override
      public boolean hasNext() {
        return cursor < to && cursor != -1;
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
        return next - from;
      }
    };
  }

  public void addRange(int start, int end) {
    int realStart = from + start;
    int realEnd = from + end;
    if (realStart >= to || realEnd > to) {
      throw new IndexOutOfBoundsException();
    }
    bitSet.set(realStart, realEnd);
  }
  
  public boolean addInt(int value) {
    int offset = from + value;
    if (offset >= to) {
      throw new IndexOutOfBoundsException();
    }
    boolean present = bitSet.get(offset);
    if (present) {
      return false;
    }
    bitSet.set(offset, true);
    return true;
  }

  public boolean removeInt(int value) {
    int offset = from + value;
    if (offset >= to) {
      throw new IndexOutOfBoundsException();
    }
    boolean present = bitSet.get(offset);
    if (!present) {
      return false;
    }
    bitSet.set(offset, false);
    return true;
  }
}
