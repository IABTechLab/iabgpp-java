package com.iab.gpp.encoder.datatype.encoder;

import java.util.BitSet;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.logging.Logger;
import java.util.PrimitiveIterator.OfInt;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public final class IntegerBitSet extends BaseIntegerSet {
  private static final Logger LOGGER = Logger.getLogger(IntegerBitSet.class.getName());

  static final int MAX_COLLECTION_SIZE = 8192;

  protected final BitSet bitSet;
  protected final int from;
  protected final int to;
  private final int adjustment;

  public IntegerBitSet(BitSet bitSet, int from, int to, int adjustment) {
    this.bitSet = bitSet;
    this.from = from;
    this.to = to;
    this.adjustment = adjustment;
  }

  public  IntegerBitSet(int limit) {
    this(new BitSet(0), 0, limit, 0);
  }

  public IntegerBitSet() {
    this(MAX_COLLECTION_SIZE);
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

  private int getOffset(int value) {
    int offset  = from - adjustment + value;
    if (offset < from) {
      throw new IndexOutOfBoundsException();
    }
    return offset;
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
    int offset = getOffset(value);
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
        return next - from + adjustment;
      }
    };
  }

  @Override
  public Spliterator.OfInt spliterator(){
    return Spliterators.spliteratorUnknownSize(
        iterator(),
        Spliterator.ORDERED | Spliterator.DISTINCT | Spliterator.IMMUTABLE | Spliterator.NONNULL);
  }

  @Override
  public IntStream intStream() {
    return StreamSupport.intStream(spliterator(), false);
  }

  private static final void logOutOfRange(int value) {
    LOGGER.warning("Exceeding IntegerBitSet.MAX_COLLECTION_SIZE: "+ value);
  }

  public void addRange(int start, int end) {
    if (end < start) {
      throw new IllegalArgumentException("Negative length range");
    }
    int realStart = getOffset(start);
    int realEnd = getOffset(end);
    if (realStart >= to) {
      logOutOfRange(start);
      return;
    }
    if (realEnd > to) {
      logOutOfRange(end);
      realEnd = to;
    }
    bitSet.set(realStart, realEnd);
  }

  public boolean addInt(int value) {
    int offset = getOffset(value);
    if (offset >= to) {
      logOutOfRange(value);
      return false;
    }
    boolean present = bitSet.get(offset);
    if (present) {
      return false;
    }
    bitSet.set(offset, true);
    return true;
  }

  public boolean removeInt(int value) {
    int offset = getOffset(value);
    if (offset >= to) {
      logOutOfRange(value);
      return false;
    }
    boolean present = bitSet.get(offset);
    if (!present) {
      return false;
    }
    bitSet.set(offset, false);
    return true;
  }
}
