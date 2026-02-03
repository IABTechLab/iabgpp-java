package com.iab.gpp.encoder.bitstring;

import com.iab.gpp.encoder.datatype.FixedIntegerList;
import com.iab.gpp.encoder.datatype.IntegerSet;
import com.iab.gpp.encoder.datatype.encoder.FibonacciIntegerEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

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
        out.writeBoolean(true);
      } else if (c == FALSE) {
        out.writeBoolean(false);
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

  private boolean getValue(int i) {
    if (i >= writeIndex) {
      throw new DecodingException("Bit string access out of range");
    }
    return bitSet.get(i);
  }

  public int length() {
    return writeIndex;
  }

  public boolean hasRemaining() {
    return readIndex < writeIndex;
  }

  public void writeEmpty(int length) {
    this.writeIndex += length;
  }

  public void writeInt(int value, int length) {
    int mask = 1 << length;
    if (value >= mask) {
      throw new EncodingException(
        "Numeric value '" + value + "' is too large for a bit string length of '" + length + "'");
    }
    for (int i = 0; i < length; i++) {
      mask >>= 1;
      writeBoolean((mask & value) != 0);
    }
  }

  public void writeLong(long value, int length) {
    long mask = 1L << length;
    if (value >= mask) {
      throw new EncodingException(
        "Numeric value '" + value + "' is too large for a bit string length of '" + length + "'");
    }
    for (int i = 0; i < length; i++) {
      mask >>= 1;
      writeBoolean((mask & value) != 0);
    }
  }

  public void writeBoolean(boolean value) {
    int idx = writeIndex++;
    if (value) {
      bitSet.set(idx);
    }
  }

  public void writeFibonacci(int value) {
    int largestIndex = 0;
    for (int i = 0; i < FibonacciIntegerEncoder.FIBONACCI_LIMIT; i++) {
      if (value >= FibonacciIntegerEncoder.FIBONACCI_NUMBERS[i]) {
        largestIndex++;
      } else {
        break;
      }
    }
    if (largestIndex == FibonacciIntegerEncoder.FIBONACCI_LIMIT) {
      throw new EncodingException("Unencodable FibonacciInteger " + value);
    }

    int out = 1;
    int mask = 1;
    for (int i = largestIndex - 1; i >= 0; i--) {
      mask <<= 1;
      int f = FibonacciIntegerEncoder.FIBONACCI_NUMBERS[i];
      if (value >= f) {
        out |= mask;
        value -= f;
      }
    }
    writeInt(out, largestIndex + 1);
  }

  public void write(BitString other, int from, int to) {
    for (int i = from; i < to; i++) {
      writeBoolean(other.getValue(i));
    }
  }

  public void write(BitString other) {
    int otherLength = other.length();
    for (int i = 0; i < otherLength; i++) {
      writeBoolean(other.bitSet.get(i));
    }
  }

  public int readInt(int length) {
    int from = readIndex;
    int to = from + length;
    if (from >= writeIndex) {
      throw new DecodingException("Bit string access out of range");
    }
    readIndex = to;
    return bitSet.readInt(from, to);
  }

  public long readLong(int length) {
    int from = readIndex;
    int to = from + length;
    if (from >= writeIndex) {
      throw new DecodingException("Bit string access out of range");
    }
    readIndex = to;
    return bitSet.readLong(from, to);
  }
  
  public boolean readBoolean() {
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
