package com.iab.gpp.encoder.datatype.encoder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import com.iab.gpp.encoder.error.DecodingException;

public class FixedBitfieldEncoder {

  private static Pattern BITSTRING_VERIFICATION_PATTERN = Pattern.compile("^[0-1]*$", Pattern.CASE_INSENSITIVE);

  public static String encode(List<Integer> value, int bitStringLength) {
    String bitString = "";
    for (int i = 0; i < value.size(); i++) {
      bitString += FixedIntegerEncoder.encode(value.get(i), 1);
    }

    while (bitString.length() < bitStringLength) {
      bitString += "0";
    }

    return bitString;
  }

  public static List<Integer> decode(String bitString) throws DecodingException {
    if (!BITSTRING_VERIFICATION_PATTERN.matcher(bitString).matches()) {
      throw new DecodingException("Undecodable FixedBitfield '" + bitString + "'");
    }

    List<Integer> value = new ArrayList<>();
    for (int i = 0; i < bitString.length(); i++) {
      value.add(FixedIntegerEncoder.decode(bitString.substring(i, i + 1)));
    }
    return value;
  }
}
