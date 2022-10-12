package com.iab.gpp.encoder.datatype.encoder;

import java.util.regex.Pattern;
import com.iab.gpp.encoder.error.DecodingException;

public class FixedIntegerEncoder {

  private static Pattern BITSTRING_VERIFICATION_PATTERN = Pattern.compile("^[0-1]*$", Pattern.CASE_INSENSITIVE);

  public static String encode(int value, int bitStringLength) {
    // let bitString = value.toString(2);

    String bitString = "";
    while (value > 0) {
      if ((value & 1) == 1) {
        bitString = "1" + bitString;
      } else {
        bitString = "0" + bitString;
      }
      value = value >> 1;
    }

    while (bitString.length() < bitStringLength) {
      bitString = "0" + bitString;
    }

    return bitString;
  }

  public static int decode(String bitString) throws DecodingException {
    if (!BITSTRING_VERIFICATION_PATTERN.matcher(bitString).matches()) {
      throw new DecodingException("Undecodable FixedInteger '" + bitString + "'");
    }

    // return parseInt(bitString, 2);

    int value = 0;

    for (int i = 0; i < bitString.length(); i++) {
      if (bitString.charAt(bitString.length() - (i + 1)) == '1') {
        value += 1 << i;
      }
    }

    return value;
  }
}
