package com.iab.gpp.encoder.datatype.encoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;

public class FibonacciIntegerRangeEncoder {

  public static String encode(List<Integer> value) {
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

    StringBuilder bitString = new StringBuilder(FixedIntegerEncoder.encode(groups.size(), 12));
    for (int i = 0; i < groups.size(); i++) {
      if (groups.get(i).size() == 1) {
        int v = groups.get(i).get(0) - offset;
        offset = groups.get(i).get(0);
        bitString.append(BitString.FALSE).append(FibonacciIntegerEncoder.encode(v));
      } else {
        int startVal = groups.get(i).get(0) - offset;
        offset = groups.get(i).get(0);
        int endVal = groups.get(i).get(groups.get(i).size() - 1) - offset;
        offset = groups.get(i).get(groups.get(i).size() - 1);
        bitString.append(BitString.TRUE).append(FibonacciIntegerEncoder.encode(startVal)).append(FibonacciIntegerEncoder.encode(endVal));
      }
    }
    return bitString.toString();
  }

  public static List<Integer> decode(BitString bitString) throws DecodingException {
    if (bitString.length() < 12) {
      throw new DecodingException("Undecodable FibonacciIntegerRange '" + bitString + "'");
    }

    int count = FixedIntegerEncoder.decode(bitString, 0, 12);
    List<Integer> value = new ArrayList<>(count);

    int offset = 0;
    int startIndex = 12;
    for (int i = 0; i < count; i++) {
      boolean group = BooleanEncoder.decode(bitString, startIndex, 1);
      startIndex++;

      if (group == true) {
        int index = bitString.indexOf("11", startIndex);
        int start = FibonacciIntegerEncoder.decode(bitString.substring(startIndex, index + 2)) + offset;
        offset = start;
        startIndex = index + 2;

        index = bitString.indexOf("11", startIndex);
        int end = FibonacciIntegerEncoder.decode(bitString.substring(startIndex, index + 2)) + offset;
        offset = end;
        startIndex = index + 2;

        for (int j = start; j <= end; j++) {
          value.add(IntegerCache.valueOf(j));
        }
      } else {
        int index = bitString.indexOf("11", startIndex);
        int val = FibonacciIntegerEncoder.decode(bitString.substring(startIndex, index + 2)) + offset;
        offset = val;
        value.add(IntegerCache.valueOf(val));
        startIndex = index + 2;
      }
    }

    return value;
  }
}
