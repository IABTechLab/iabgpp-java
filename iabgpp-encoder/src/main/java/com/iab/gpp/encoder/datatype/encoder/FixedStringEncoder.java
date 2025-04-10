package com.iab.gpp.encoder.datatype.encoder;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class FixedStringEncoder {
  private FixedStringEncoder() {}
  private static final char SPACE = ' ';

  public static void encode(BitStringBuilder builder, String value, int stringLength) {
    int length = value.length();
    for (int i = 0; i < stringLength; i++) {
      int code = SPACE;
      if (i < length) {
        code = value.charAt(i);
      }
      if (code == SPACE) {
        FixedIntegerEncoder.encode(builder, 63, 6);
      } else if (code >= 65) {
        FixedIntegerEncoder.encode(builder, ((int) value.charAt(i)) - 65, 6);
      } else {
        throw new EncodingException("Unencodable FixedString '" + value + "'");
      }
    }
  }

  public static String decode(BitString bitString) {
    int length = bitString.length();
    if (length % 6 != 0) {
      throw new DecodingException("Undecodable FixedString '" + bitString + "'");
    }

    StringBuilder value = new StringBuilder(length);

    for (int i = 0; i < length; i += 6) {
      int code = FixedIntegerEncoder.decode(bitString, i, 6);
      if (code == 63) {
        value.append(SPACE);
      } else {
        value.append((char) (code + 65));
      }
    }

    return value.toString().trim();
  }
}
