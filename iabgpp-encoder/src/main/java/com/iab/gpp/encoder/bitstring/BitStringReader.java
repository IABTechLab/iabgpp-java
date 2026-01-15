package com.iab.gpp.encoder.bitstring;

import com.iab.gpp.encoder.datatype.encoder.FibonacciIntegerEncoder;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerEncoder;
import com.iab.gpp.encoder.datatype.encoder.FixedLongEncoder;
import com.iab.gpp.encoder.datatype.encoder.IntegerSet;

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
    int indexOfEndTag = FibonacciIntegerEncoder.indexOfEndTag(bitString, offset) + 2;
    int out = FibonacciIntegerEncoder.decode(bitString, offset, indexOfEndTag);
    offset = indexOfEndTag;
    return out;
  }

  public IntegerSet readIntegerSet(int length) {
    int newOffset = offset + length;
    IntegerSet out = bitString.toIntegerSet(offset, newOffset);
    offset = newOffset;
    return out;
  }

}
