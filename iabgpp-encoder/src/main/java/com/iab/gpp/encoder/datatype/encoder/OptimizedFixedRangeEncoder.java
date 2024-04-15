package com.iab.gpp.encoder.datatype.encoder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class OptimizedFixedRangeEncoder {

  private static Pattern BITSTRING_VERIFICATION_PATTERN = Pattern.compile("^[0-1]*$", Pattern.CASE_INSENSITIVE);

  public static String encode(List<Integer> value) throws EncodingException {
    // TODO: encoding the range before choosing the shortest is inefficient. There is probably a way
    // to identify in advance which will be shorter based on the array length and values
    int max = value.size() > 0 ? value.get(value.size() - 1) : 0;
    String rangeBitString = FixedIntegerRangeEncoder.encode(value);
    int rangeLength = rangeBitString.length();
    int bitFieldLength = max;

    if (rangeLength <= bitFieldLength) {
      return FixedIntegerEncoder.encode(max, 16) + "1" + rangeBitString;
    } else {
      List<Boolean> bits = new ArrayList<>();
      int index = 0;
      for (int i = 0; i < max; i++) {
        if (i == value.get(index) - 1) {
          bits.add(true);
          index++;
        } else {
          bits.add(false);
        }
      }

      return FixedIntegerEncoder.encode(max, 16) + "0" + FixedBitfieldEncoder.encode(bits, bitFieldLength);
    }
  }

  public static List<Integer> decode(String bitString) throws DecodingException {
    if (!BITSTRING_VERIFICATION_PATTERN.matcher(bitString).matches() || bitString.length() < 12) {
      throw new DecodingException("Undecodable FixedIntegerRange '" + bitString + "'");
    }

    if (bitString.charAt(16) == '1') {
      return FixedIntegerRangeEncoder.decode(bitString.substring(17));
    } else {
      List<Integer> value = new ArrayList<>();
      List<Boolean> bits = FixedBitfieldEncoder.decode(bitString.substring(17));
      for (int i = 0; i < bits.size(); i++) {
        if (bits.get(i) == true) {
          value.add(i + 1);
        }
      }
      return value;
    }
  }
}
