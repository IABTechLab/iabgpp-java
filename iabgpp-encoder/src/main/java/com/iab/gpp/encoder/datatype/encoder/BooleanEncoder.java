package com.iab.gpp.encoder.datatype.encoder;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class BooleanEncoder {
  public static String encode(Boolean value) {
    if (value == true) {
      return BitString.TRUE_STRING;
    } else if (value == false) {
      return BitString.FALSE_STRING;
    } else {
      throw new EncodingException("Unencodable Boolean '" + value + "'");
    }
  }

  public static boolean decode(BitString bitString) {
    return decode(bitString, 0, bitString.length());
  }

  public static boolean decode(BitString bitString, int fromIndex, int length) {
    if (length != 1) {
      throw new DecodingException("Undecodable Boolean '" + bitString + "'");
    }
    return bitString.getValue(fromIndex);
  }
}
