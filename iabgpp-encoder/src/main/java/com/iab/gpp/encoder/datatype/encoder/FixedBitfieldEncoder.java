package com.iab.gpp.encoder.datatype.encoder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import com.iab.gpp.encoder.error.DecodingException;

public class FixedBitfieldEncoder {

  private static Pattern BITSTRING_VERIFICATION_PATTERN = Pattern.compile("^[0-1]*$", Pattern.CASE_INSENSITIVE);

  public static String encode(List<Boolean> value, int bitStringLength) {
    String bitString = "";
    for (int i = 0; i < value.size(); i++) {
      bitString += BooleanEncoder.encode(value.get(i));
    }

    while (bitString.length() < bitStringLength) {
      bitString += "0";
    }

    return bitString;
  }

  public static List<Boolean> decode(String bitString) {
    if (!BITSTRING_VERIFICATION_PATTERN.matcher(bitString).matches()) {
      throw new DecodingException("Undecodable FixedBitfield '" + bitString + "'");
    }

    List<Boolean> value = new ArrayList<>();
    for (int i = 0; i < bitString.length(); i++) {
      value.add(BooleanEncoder.decode(bitString.substring(i, i + 1)));
    }
    return value;
  }
}
