package com.iab.gpp.encoder.bitstring;

import com.iab.gpp.encoder.datatype.FixedIntegerList;
import com.iab.gpp.encoder.datatype.encoder.FibonacciIntegerEncoder;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerEncoder;
import com.iab.gpp.encoder.datatype.encoder.FixedLongEncoder;
import com.iab.gpp.encoder.datatype.encoder.IntegerSet;
import com.iab.gpp.encoder.error.DecodingException;

public final class BitString {
  public static final char TRUE = '1';
  public static final char FALSE = '0';
  public static final String TRUE_STRING = new String(new char[] {TRUE});
  public static final String FALSE_STRING = new String(new char[] {FALSE});

  private final BitSet bitSet;
  private int readIndex;
  private int writeIndex;

  public BitString(BitSet bitSet, int length) {
    this.bitSet = bitSet;
    this.writeIndex = length;
  }
  
  public BitString() {
    this(new BitSet(), 0);
  }

  public static final BitString of(String str) {
    int length = str.length();
    
    BitString out = new BitString(new BitSet(length), 0);
    for (int i = 0; i < length; i++) {
      char c = str.charAt(i);
      if (c == TRUE) {
        out.append(true);
      } else if (c == FALSE) {
        out.append(false);
      } else {
        throw new DecodingException("Invalid bit string");
      }
    }
    return out;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(length());
    for (int i = 0; i < writeIndex; i++) {
      sb.append(bitSet.get(i) ? TRUE : FALSE);
    }
    return sb.toString();
  }

  public boolean getValue(int i) {
    if (i >= writeIndex) {
      throw new DecodingException("Bit string access out of range");
    }
    return bitSet.get(i);
  }

  public int length() {
    return writeIndex;
  }
  
  
  public BitString extend(int length) {
    this.writeIndex += length;
    return this;
  }

  public BitString append(boolean value) {
    int idx = writeIndex++;
    if (value) {
      bitSet.set(idx);
    }
    return this;
  }

  public BitString append(BitString other, int from, int to) {
    for (int i = from; i < to; i++) {
      append(other.getValue(i));
    }
    return this;
  }

  public BitString append(BitString other) {
    int otherLength = other.length();
    for (int i = 0; i < otherLength; i++) {
      append(other.bitSet.get(i));
    }
    return this;
  }
  
  public int readInt(int length) {
    int newReadIndex = readIndex + length;
    int out = FixedIntegerEncoder.decode(this, readIndex, newReadIndex);
    readIndex = newReadIndex;
    return out;
  }

  public long readLong(int length) {
    int newReadIndex = readIndex + length;
    long out = FixedLongEncoder.decode(this, readIndex, newReadIndex);
    readIndex = newReadIndex;
    return out;
  }
  
  public boolean readBool() {
    return getValue(readIndex++);
  }

  public int readFibonacci() {
    int value = 0;
    int readIndex = this.readIndex;
    for (int i = 0; i < FibonacciIntegerEncoder.FIBONACCI_LIMIT; i++) {
      if (getValue(readIndex + i)) {
        // 1X
        value += FibonacciIntegerEncoder.FIBONACCI_NUMBERS[i];
        i++;
        int next = readIndex + i;
        if (getValue(next)) {
          // 11
          this.readIndex = next + 1;
          return value;
        }
      }
    }
    throw new DecodingException("Invalid FibonacciInteger");
  }

  public IntegerSet readIntegerSet(int length) {
    int newReadIndex = readIndex + length;
    if (newReadIndex > writeIndex) {
      throw new DecodingException("Bit string access out of range");
    }
    IntegerSet out = new IntegerSet(bitSet, readIndex, newReadIndex, 1);
    readIndex = newReadIndex;
    return out;
  }

  public FixedIntegerList readFixedIntegerList(int elementLength, int length) {
    int newReadIndex = readIndex + elementLength * length;
    if (newReadIndex > writeIndex) {
      throw new DecodingException("Bit string access out of range");
    }
    FixedIntegerList out = new FixedIntegerList(bitSet, readIndex, elementLength, length);
    readIndex = newReadIndex;
    return out;
  }

}
