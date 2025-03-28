package com.iab.gpp.encoder.datatype.encoder;

import java.util.BitSet;
import java.util.Collections;
import java.util.List;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.error.DecodingException;

public class FixedIntegerRangeEncoder {

  public static void encode(BitStringBuilder builder, List<Integer> value) {
    Collections.sort(value);
    BitStringBuilder rangeBuilder = new BitStringBuilder();
    int groupStart = -1;
    int last = Integer.MIN_VALUE;
    int groupCount = 0;
    for (Integer item : value) {
      if (last != (item - 1)) {
        if (groupStart > 0) {
          groupCount++;
          writeGroup(rangeBuilder, groupStart, last);
        }
        groupStart = item;
      }
      last = item;
    }
    if (groupStart > 0) {
      groupCount++;
      writeGroup(rangeBuilder, groupStart, last);
    }
    FixedIntegerEncoder.encode(builder,groupCount, 12);
    builder.append(rangeBuilder);
  }

  private static void writeGroup(BitStringBuilder builder, int groupStart, int last) {
    if (groupStart == last) {
      builder.append(false);
      FixedIntegerEncoder.encode(builder, groupStart, 16);
    } else {
      builder.append(true);
      FixedIntegerEncoder.encode(builder, groupStart, 16);
      FixedIntegerEncoder.encode(builder, last, 16);
    }
  }

  public static List<Integer> decode(BitString bitString) throws DecodingException {
    if (bitString.length() < 12) {
      throw new DecodingException("Undecodable FixedIntegerRange '" + bitString + "'");
    }

    int count = FixedIntegerEncoder.decode(bitString, 0, 12);
    BitSet value = new BitSet();
    int startIndex = 12;
    for (int i = 0; i < count; i++) {
      boolean group = BooleanEncoder.decode(bitString, startIndex, 1);
      startIndex++;

      if (group == true) {
        int start = FixedIntegerEncoder.decode(bitString, startIndex, 16);
        startIndex += 16;

        int end = FixedIntegerEncoder.decode(bitString, startIndex, 16);
        startIndex += 16;

        value.set(start, end + 1);
      } else {
        int val = FixedIntegerEncoder.decode(bitString, startIndex, 16);
        value.set(val);
        startIndex += 16;
      }
    }

    return new IntegerList(value);
  }
}
