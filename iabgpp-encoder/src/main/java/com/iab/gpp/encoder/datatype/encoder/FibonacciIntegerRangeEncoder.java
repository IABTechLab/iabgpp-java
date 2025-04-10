package com.iab.gpp.encoder.datatype.encoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import com.iab.gpp.encoder.error.DecodingException;

public class FibonacciIntegerRangeEncoder {

  private static final Logger LOGGER = Logger.getLogger(FibonacciIntegerRangeEncoder.class.getName());
  // NOTE: This is a value roughly the 2x the size of this list
  // https://tools.iabtechlab.com/transparencycenter/explorer/business/gpp
  static final int MAX_SIZE = 8192;
  private static Pattern BITSTRING_VERIFICATION_PATTERN = Pattern.compile("^[0-1]*$", Pattern.CASE_INSENSITIVE);

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

    String bitString = FixedIntegerEncoder.encode(groups.size(), 12);
    for (int i = 0; i < groups.size(); i++) {
      if (groups.get(i).size() == 1) {
        int v = groups.get(i).get(0) - offset;
        offset = groups.get(i).get(0);
        bitString += "0" + FibonacciIntegerEncoder.encode(v);
      } else {
        int startVal = groups.get(i).get(0) - offset;
        offset = groups.get(i).get(0);
        int endVal = groups.get(i).get(groups.get(i).size() - 1) - offset;
        offset = groups.get(i).get(groups.get(i).size() - 1);
        bitString += "1" + FibonacciIntegerEncoder.encode(startVal) + FibonacciIntegerEncoder.encode(endVal);
      }
    }
    return bitString;
  }

  public static List<Integer> decode(String bitString) throws DecodingException {
    if (!BITSTRING_VERIFICATION_PATTERN.matcher(bitString).matches() || bitString.length() < 12) {
      throw new DecodingException("Undecodable FibonacciIntegerRange '" + bitString + "'");
    }

    List<Integer> value = new ArrayList<>();
    int count = FixedIntegerEncoder.decode(bitString.substring(0, 12));

    int offset = 0;
    int startIndex = 12;
    for (int i = 0; i < count; i++) {
      boolean group = BooleanEncoder.decode(bitString.substring(startIndex, startIndex + 1));
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

        if (value.size() + (end - start) > MAX_SIZE) {
          LOGGER.warning("FibonacciIntegerRange has too many values");
          break;
        }
        for (int j = start; j <= end; j++) {
          value.add(j);
        }
      } else {
        int index = bitString.indexOf("11", startIndex);
        int val = FibonacciIntegerEncoder.decode(bitString.substring(startIndex, index + 2)) + offset;
        offset = val;
        if (value.size() == MAX_SIZE) {
          LOGGER.warning("FibonacciIntegerRange has too many values");
          break;
        }
        value.add(val);
        startIndex = index + 2;
      }
    }

    return value;
  }
}
