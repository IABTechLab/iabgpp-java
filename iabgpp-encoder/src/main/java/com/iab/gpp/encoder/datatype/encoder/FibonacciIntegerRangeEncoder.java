package com.iab.gpp.encoder.datatype.encoder;

import java.util.BitSet;
import java.util.Collections;
import java.util.List;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.error.DecodingException;

public class FibonacciIntegerRangeEncoder {

  public static void encode(BitStringBuilder builder, List<Integer> value) {
    Collections.sort(value);
    BitStringBuilder rangeBuilder = new BitStringBuilder();
    int groupStart = -1;
    int last = Integer.MIN_VALUE;
    int offset = 0;
    int groupCount = 0;
    for (Integer item : value) {
      if (last != (item - 1)) {
        if (groupStart > 0) {
          groupCount++;
          writeGroup(rangeBuilder, groupStart, last, offset);
          offset = last;
        }
        groupStart = item;
      }
      last = item;
    }
    if (groupStart > 0) {
      groupCount++;
      writeGroup(rangeBuilder, groupStart, last, offset);
    }
    FixedIntegerEncoder.encode(builder,groupCount, 12);
    builder.append(rangeBuilder);
  }

  private static void writeGroup(BitStringBuilder builder, int groupStart, int last, int offset) {
    int base = groupStart - offset;
    int span = last - groupStart;
    if (span == 0) {
      builder.append(false);
      FibonacciIntegerEncoder.encode(builder, base);
    } else {
      builder.append(true);
      FibonacciIntegerEncoder.encode(builder, base);
      FibonacciIntegerEncoder.encode(builder, span);
    }
  }

  public static List<Integer> decode(BitString bitString) throws DecodingException {
    if (bitString.length() < 12) {
      throw new DecodingException("Undecodable FibonacciIntegerRange '" + bitString + "'");
    }

    int count = FixedIntegerEncoder.decode(bitString, 0, 12);
    BitSet value = new BitSet();

    int offset = 0;
    int startIndex = 12;
    for (int i = 0; i < count; i++) {
      boolean group = BooleanEncoder.decode(bitString, startIndex, 1);
      startIndex++;

      if (group == true) {
        int index = FibonacciIntegerEncoder.indexOfEndTag(bitString, startIndex);
        int start = FibonacciIntegerEncoder.decode(bitString, startIndex, index + 2 - startIndex) + offset;
        offset = start;
        startIndex = index + 2;

        index = FibonacciIntegerEncoder.indexOfEndTag(bitString, startIndex);
        int end = FibonacciIntegerEncoder.decode(bitString, startIndex, index + 2 - startIndex) + offset;
        offset = end;
        startIndex = index + 2;
        value.set(start, end + 1);
      } else {
        int index = FibonacciIntegerEncoder.indexOfEndTag(bitString, startIndex);
        int val = FibonacciIntegerEncoder.decode(bitString, startIndex, index + 2 - startIndex) + offset;
        offset = val;
        value.set(val);
        startIndex = index + 2;
      }
    }

    return new IntegerList(value);
  }
}
