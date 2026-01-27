package com.iab.gpp.encoder.bitstring;

import com.iab.gpp.encoder.datatype.encoder.FibonacciIntegerEncoder;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerEncoder;
import com.iab.gpp.encoder.datatype.encoder.FixedLongEncoder;
import com.iab.gpp.encoder.datatype.encoder.IntegerSet;
import com.iab.gpp.encoder.error.DecodingException;

public final class BitStringReader {
  
  private int offset = 0;
  private final BitString bitString;
  
  public BitStringReader(BitString bitString) {
    this.bitString = bitString;
  }

  public int readInt(int length) {
    int newOffset = offset + length;
    int out = FixedIntegerEncoder.decode(bitString, offset, newOffset);
    offset = newOffset;
    return out;
  }

  public long readLong(int length) {
    int newOffset = offset + length;
    long out = FixedLongEncoder.decode(bitString, offset, newOffset);
    offset = newOffset;
    return out;
  }
  
  public boolean readBool() {
    return bitString.getValue(offset++);
  }

  public int readFibonacci() {
    int value = 0;
    int offset = this.offset;
    for (int i = 0; i < FibonacciIntegerEncoder.FIBONACCI_LIMIT; i++) {
      if (bitString.getValue(offset + i)) {
        // 1X
        value += FibonacciIntegerEncoder.FIBONACCI_NUMBERS[i];
        i++;
        int next = offset + i;
        if (bitString.getValue(next)) {
          // 11
          this.offset = next + 1;
          return value;
        }
      }
    }
    throw new DecodingException("Invalid FibonacciInteger");
  }

  public IntegerSet readIntegerSet(int length) {
    int newOffset = offset + length;
    IntegerSet out = bitString.toIntegerSet(offset, newOffset);
    offset = newOffset;
    return out;
  }

}
