package com.iab.gpp.encoder.datatype.encoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;

public class FixedIntegerRangeEncoder {

  public static String encode(List<Integer> value) {
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

    StringBuilder bitString = new StringBuilder(FixedIntegerEncoder.encode(groups.size(), 12));
    for (int i = 0; i < groups.size(); i++) {
      if (groups.get(i).size() == 1) {
        bitString.append(BitString.FALSE).append(FixedIntegerEncoder.encode(groups.get(i).get(0), 16));
      } else {
        bitString.append(BitString.TRUE).append(FixedIntegerEncoder.encode(groups.get(i).get(0), 16))
            .append(FixedIntegerEncoder.encode(groups.get(i).get(groups.get(i).size() - 1), 16));
      }
    }
    return bitString.toString();
  }

  public static List<Integer> decode(BitString bitString) throws DecodingException {
    if (bitString.length() < 12) {
      throw new DecodingException("Undecodable FixedIntegerRange '" + bitString + "'");
    }

    int count = FixedIntegerEncoder.decode(bitString, 0, 12);
    List<Integer> value = new ArrayList<>(count);
    int startIndex = 12;
    for (int i = 0; i < count; i++) {
      boolean group = BooleanEncoder.decode(bitString, startIndex, 1);
      startIndex++;

      if (group == true) {
        int start = FixedIntegerEncoder.decode(bitString, startIndex, 16);
        startIndex += 16;

        int end = FixedIntegerEncoder.decode(bitString, startIndex, 16);
        startIndex += 16;

        if (end < start) {
          throw new DecodingException("FixedIntegerRange has invalid range");
        }
        if (value.size() + (end - start) > MAX_SIZE) {
          LOGGER.warning("FixedIntegerRange has too many values");
          break;
        }
        for (int j = start; j <= end; j++) {
          value.add(IntegerCache.valueOf(j));
        }
      } else {
        int val = FixedIntegerEncoder.decode(bitString, startIndex, 16);
        value.add(IntegerCache.valueOf(val));
        startIndex += 16;
      }
    }

    return value;
  }
}
