package com.iab.gpp.encoder.bitstring;

import java.util.BitSet;
import com.iab.gpp.encoder.datatype.encoder.IntegerBitSet;
import com.iab.gpp.encoder.datatype.encoder.IntegerSet;
import com.iab.gpp.encoder.error.DecodingException;

public final class BitString {
  public static final char TRUE = '1';
  public static final char FALSE = '0';
  public static final String TRUE_STRING = new String(new char[] {TRUE});
  public static final String FALSE_STRING = new String(new char[] {FALSE});
  private static final BitString EMPTY = new BitString(new BitSet(), 0, 0);

  private final BitSet bitSet;
  private final int from;
  private final int to;

  BitString(BitSet bitSet, int from, int to) {
    this.bitSet = bitSet;
    this.from = from;
    this.to = to;
  }

  public static final BitString empty(int size) {
    if (size == 0) {
      return EMPTY;
    }
    return new BitString(new BitSet(size), 0, size);
  }

  public static final BitString of(String str) {
    int length = str.length();
    BitStringBuilder builder = new BitStringBuilder(length);
    for (int i = 0; i < length; i++) {
      char c = str.charAt(i);
      if (c == TRUE) {
        builder.append(true);
      } else if (c == FALSE) {
        builder.append(false);
      } else {
        throw new DecodingException("Invalid bit string");
      }
    }
    return builder.build();
  }

  public IntegerSet toIntegerSet() {
    return new IntegerBitSet(bitSet, from, to);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(length());
    for (int i = from; i < to; i++) {
      sb.append(bitSet.get(i) ? TRUE : FALSE);
    }
    return sb.toString();
  }

  public boolean getValue(int i) {
    return bitSet.get(from + i);
  }

  public int length() {
    return to - from;
  }

  public BitString substring(int i) {
    return substring(i, length());
  }

  public BitString substring(int newFrom, int newTo) {
    int length = length();
    if (newFrom > newTo || newFrom < 0 || newFrom > length || newTo > length) {
      throw new IllegalArgumentException("Invalid substring");
    }
    int oldFrom = this.from;
    return new BitString(bitSet, oldFrom + newFrom, oldFrom + newTo);
  }

  public BitString expandTo(int target) {
    int needed = target - length();
    if (needed == 0) {
      return this;
    }
    if (needed < 0) {
      return substring(0, target);
    }
    BitStringBuilder sb = new BitStringBuilder(target);
    sb.append(this);
    for (int i = 0; i < needed; i++) {
      sb.append(false);
    }
    return sb.build();
  }

}
