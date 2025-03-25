package com.iab.gpp.encoder.datatype.encoder;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.error.DecodingException;

public class FixedIntegerRangeEncoder {

  public static void encode(BitStringBuilder builder, List<Integer> value) {
    Collections.sort(value);

    List<List<Integer>> groups = new ArrayList<>();

    int groupStartIndex = 0;
    while (groupStartIndex < value.size()) {
      int groupEndIndex = groupStartIndex;
      while (groupEndIndex < value.size() - 1 && value.get(groupEndIndex) + 1 == value.get(groupEndIndex + 1)) {
        groupEndIndex++;
      }

      groups.add(value.subList(groupStartIndex, groupEndIndex + 1));

      groupStartIndex = groupEndIndex + 1;
    }

    FixedIntegerEncoder.encode(builder, groups.size(), 12);
    for (int i = 0; i < groups.size(); i++) {
      if (groups.get(i).size() == 1) {
        builder.append(false);
        FixedIntegerEncoder.encode(builder, groups.get(i).get(0), 16);
      } else {
        builder.append(true);
        FixedIntegerEncoder.encode(builder, groups.get(i).get(0), 16);
        FixedIntegerEncoder.encode(builder, groups.get(i).get(groups.get(i).size() - 1), 16);
      }
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
