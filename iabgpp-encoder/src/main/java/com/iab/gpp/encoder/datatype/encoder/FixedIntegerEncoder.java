package com.iab.gpp.encoder.datatype.encoder;

import java.util.regex.Pattern;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

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

    if (bitString.length() > bitStringLength) {
      throw new EncodingException(
          "Numeric value '" + value + "' is too large for a bit string length of '" + bitStringLength + "'");
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
    int value = 0;

    for (int i = 0; i < bitString.length(); i++) {
      if (bitString.charAt(bitString.length() - (i + 1)) == '1') {
        value += 1 << i;
      }
    }

    return value;
  }
}
