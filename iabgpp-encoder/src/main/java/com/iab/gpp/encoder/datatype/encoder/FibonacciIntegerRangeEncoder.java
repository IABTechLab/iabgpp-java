package com.iab.gpp.encoder.datatype.encoder;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.error.DecodingException;

public class FibonacciIntegerRangeEncoder {

  public static void encode(BitStringBuilder builder, List<Integer> value) {
    Collections.sort(value);

    List<List<Integer>> groups = new ArrayList<>();

    int offset = 0;
    int groupStartIndex = 0;
    while (groupStartIndex < value.size()) {
      int groupEndIndex = groupStartIndex;
      while (groupEndIndex < value.size() - 1 && value.get(groupEndIndex) + 1 == value.get(groupEndIndex + 1)) {
        groupEndIndex++;
      }

      groups.add(value.subList(groupStartIndex, groupEndIndex + 1));

      groupStartIndex = groupEndIndex + 1;
    }

    FixedIntegerEncoder.encode(builder,groups.size(), 12);
    for (int i = 0; i < groups.size(); i++) {
      if (groups.get(i).size() == 1) {
        int v = groups.get(i).get(0) - offset;
        offset = groups.get(i).get(0);
        builder.append(false);
        FibonacciIntegerEncoder.encode(builder, v);
      } else {
        int startVal = groups.get(i).get(0) - offset;
        offset = groups.get(i).get(0);
        int endVal = groups.get(i).get(groups.get(i).size() - 1) - offset;
        offset = groups.get(i).get(groups.get(i).size() - 1);
        builder.append(true);
        FibonacciIntegerEncoder.encode(builder, startVal);
        FibonacciIntegerEncoder.encode(builder, endVal);
      }
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
