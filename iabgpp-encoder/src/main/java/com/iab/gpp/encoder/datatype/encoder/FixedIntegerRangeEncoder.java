package com.iab.gpp.encoder.datatype.encoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import com.iab.gpp.encoder.error.DecodingException;

public class FixedIntegerRangeEncoder {

  private static final int MAX_SIZE = 8192;
  private static Pattern BITSTRING_VERIFICATION_PATTERN = Pattern.compile("^[0-1]*$", Pattern.CASE_INSENSITIVE);

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

    String bitString = FixedIntegerEncoder.encode(groups.size(), 12);
    for (int i = 0; i < groups.size(); i++) {
      if (groups.get(i).size() == 1) {
        bitString += "0" + FixedIntegerEncoder.encode(groups.get(i).get(0), 16);
      } else {
        bitString += "1" + FixedIntegerEncoder.encode(groups.get(i).get(0), 16)
            + FixedIntegerEncoder.encode(groups.get(i).get(groups.get(i).size() - 1), 16);
      }
    }
    return bitString;
  }

  public static List<Integer> decode(String bitString) throws DecodingException {
    if (!BITSTRING_VERIFICATION_PATTERN.matcher(bitString).matches() || bitString.length() < 12) {
      throw new DecodingException("Undecodable FixedIntegerRange '" + bitString + "'");
    }

    List<Integer> value = new ArrayList<>();
    int count = FixedIntegerEncoder.decode(bitString.substring(0, 12));
    int startIndex = 12;
    for (int i = 0; i < count; i++) {
      boolean group = BooleanEncoder.decode(bitString.substring(startIndex, startIndex + 1));
      startIndex++;

      if (group == true) {
        int start = FixedIntegerEncoder.decode(bitString.substring(startIndex, startIndex + 16));
        startIndex += 16;

        int end = FixedIntegerEncoder.decode(bitString.substring(startIndex, startIndex + 16));
        startIndex += 16;

        if (end < start) {
          throw new DecodingException("FixedIntegerRange has invalid range");
        }
        if (value.size() + (end - start) > MAX_SIZE) {
          throw new DecodingException("FixedIntegerRange has too many values");
        }
        for (int j = start; j <= end; j++) {
          value.add(j);
        }
      } else {
        int val = FixedIntegerEncoder.decode(bitString.substring(startIndex, startIndex + 16));
        if (value.size() == MAX_SIZE) {
          throw new DecodingException("FixedIntegerRange has too many values");
        }
        value.add(val);
        startIndex += 16;
      }
    }

    return value;
  }
}
