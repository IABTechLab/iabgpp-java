package com.iab.gpp.encoder.bitstring;

import java.util.BitSet;
import com.iab.gpp.encoder.datatype.encoder.IntegerSet;
import com.iab.gpp.encoder.error.DecodingException;

public final class BitString {
  public static final char TRUE = '1';
  public static final char FALSE = '0';
  public static final String TRUE_STRING = new String(new char[] {TRUE});
  public static final String FALSE_STRING = new String(new char[] {FALSE});

  private final BitSet bitSet;
  private final int length;

  BitString(BitSet bitSet, int length) {
    this.bitSet = bitSet;
    this.length = length;
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

  public IntegerSet toIntegerSet(int from, int to) {
    return new IntegerSet(bitSet, from, to, 1);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(length());
    for (int i = 0; i < length; i++) {
      sb.append(bitSet.get(i) ? TRUE : FALSE);
    }
    return sb.toString();
  }

  public boolean getValue(int i) {
    return bitSet.get(i);
  }

  public int length() {
    return length;
  }

}
