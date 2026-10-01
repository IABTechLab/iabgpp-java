package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitSet;
import java.util.AbstractList;

/**
 * An optimized implementation of {@literal List<Integer>} of fixed size. Use {@link #getInt(int)}
 * and {@link #setInt(int, int)} for efficient access.
 */
public final class FixedIntegerList extends AbstractList<Integer> implements Dirtyable {

  private boolean dirty;
  private final BitSet bitSet;
  private final int offset;
  private final int elementBitStringLength;
  private final int numElements;

  public FixedIntegerList(BitSet bitSet, int offset, int elementBitStringLength, int numElements) {
    this.bitSet = bitSet;
    this.offset = offset;
    this.elementBitStringLength = elementBitStringLength;
    this.numElements = numElements;
  }

  public FixedIntegerList(int elementBitStringLength, int numElements) {
    this(new BitSet(elementBitStringLength * numElements), 0, elementBitStringLength, numElements);
  }

  public boolean isPresent() {
    int start = offset;
    int end = start + elementBitStringLength * numElements;
    for (int i = start; i < end; i++) {
      if (bitSet.get(i)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int size() {
    return numElements;
  }

  @Override
  public Integer get(int index) {
    return getInt(index);
  }

  public int getInt(int index) {
    int length = elementBitStringLength;
    int mask = 1 << length;
    int from = offset + index * length;
    int to = from + length;
    int value = 0;
    for (int i = from; i < to; i++) {
      mask >>= 1;
      if (bitSet.get(i)) {
        value |= mask;
      }
    }
    return value;
  }

  @Override
  public Integer set(int index, Integer value) {
    return setInt(index, value);
  }

  public int setInt(int index, int value) {
    int length = elementBitStringLength;
    int mask = 1 << length;
    if (value < 0 || value >= mask) {
      throw new IllegalArgumentException(
          "Numeric value '"
              + value
              + "' is too large for a bit string length of '"
              + elementBitStringLength
              + "'");
    }
    int from = offset + index * length;
    int to = from + length;
    for (int i = from; i < to; i++) {
      mask >>= 1;
      if (bitSet.set(i, (value & mask) != 0)) {
        value |= mask;
      }
    }
    dirty = true;
    return value;
  }

  @Override
  public boolean isDirty() {
    return dirty;
  }

  @Override
  public void setDirty(boolean dirty) {
    this.dirty = dirty;
  }
}
