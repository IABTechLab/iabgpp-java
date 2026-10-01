package com.iab.gpp.encoder.datatype.encoder;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.EncodingException;

public class FixedStringEncoder {
  private FixedStringEncoder() {}

  private static final char SPACE = ' ';

  public static void encode(BitString builder, String value, int stringLength) {
    int length = value.length();
    for (int i = 0; i < stringLength; i++) {
      int code = SPACE;
      if (i < length) {
        code = value.charAt(i);
      }
      if (code == SPACE) {
        builder.writeInt(63, 6);
      } else if (code >= 65) {
        builder.writeInt(((int) value.charAt(i)) - 65, 6);
      } else {
        throw new EncodingException("Unencodable FixedString '" + value + "'");
      }
    }
  }

  public static String decode(BitString reader, int length) {
    StringBuilder value = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      int code = reader.readInt(6);
      if (code == 63) {
        value.append(SPACE);
      } else {
        value.append((char) (code + 65));
      }
    }

    return value.toString().trim();
  }
}
