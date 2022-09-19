package com.iab.gpp.encoder.datatype.encoder;

import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class BooleanEncoder {
  public static String encode(Boolean value) throws EncodingException {
    if (value == true) {
      return "1";
    } else if (value == false) {
      return "0";
    } else {
      throw new EncodingException("Unencodable Boolean '" + value + "'");
    }
  }

  public static boolean decode(String bitString) throws DecodingException {
    if (bitString.equals("1")) {
      return true;
    } else if (bitString.equals("0")) {
      return false;
    } else {
      throw new DecodingException("Undecodable Boolean '" + bitString + "'");
    }
  }
}
