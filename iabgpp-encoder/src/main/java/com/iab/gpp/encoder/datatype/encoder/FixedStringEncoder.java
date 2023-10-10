package com.iab.gpp.encoder.datatype.encoder;

import java.util.regex.Pattern;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class FixedStringEncoder {

  private static Pattern BITSTRING_VERIFICATION_PATTERN = Pattern.compile("^[0-1]*$", Pattern.CASE_INSENSITIVE);

  public static String encode(String value, int stringLength) {
    while (value.length() < stringLength) {
      value += " ";
    }

    String bitString = "";
    for (int i = 0; i < value.length(); i++) {
      int code = (int) value.charAt(i);
      if (code == 32) {
        // space
        bitString += FixedIntegerEncoder.encode(63, 6);
      } else if (code >= 65) {
        bitString += FixedIntegerEncoder.encode(((int) value.charAt(i)) - 65, 6);
      } else {
        throw new EncodingException("Unencodable FixedString '" + value + "'");
      }
    }

    return bitString;
  }

  public static String decode(String bitString) {
    if (!BITSTRING_VERIFICATION_PATTERN.matcher(bitString).matches() || bitString.length() % 6 != 0) {
      throw new DecodingException("Undecodable FixedString '" + bitString + "'");
    }

    String value = "";

    for (int i = 0; i < bitString.length(); i += 6) {
      int code = FixedIntegerEncoder.decode(bitString.substring(i, i + 6));
      if (code == 63) {
        value += " ";
      } else {
        value += (char) (code + 65);
      }
    }

    return value.trim();
  }
}
