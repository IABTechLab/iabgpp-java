package com.iab.gpp.encoder.bitstring;

import java.util.BitSet;

public final class BitStringBuilder {
  private final BitSet bitSet;
  private int length;

  public BitStringBuilder(int initialCapacity) {
    this.bitSet = new BitSet(initialCapacity);
  }

  public BitStringBuilder() {
    this.bitSet = new BitSet();
  }

  public BitString build() {
    return new BitString(bitSet, 0, length);
  }

  public BitStringBuilder append(boolean value) {
    int idx = length++;
    if (value) {
      bitSet.set(idx);
    }
    return this;
  }

  public BitStringBuilder append(BitString other) {
    int otherLength = other.length();
    for (int i = 0; i < otherLength; i++) {
      append(other.getValue(i));
    }
    return this;
  }

  public int length() {
    return length;
  }

  public BitStringBuilder append(BitStringBuilder other) {
    int otherLength = other.length();
    for (int i = 0; i < otherLength; i++) {
      append(other.bitSet.get(i));
    }
    return this;
  }
}
