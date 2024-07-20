package com.iab.gpp.encoder.datatype.encoder;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class FixedIntegerEncoder {

  public static String encode(int value, int bitStringLength) {
    // let bitString = value.toString(2);

    String bitString = "";
    while (value > 0) {
      if ((value & 1) == 1) {
        bitString = BitString.TRUE_STRING + bitString;
      } else {
        bitString = BitString.FALSE_STRING + bitString;
      }
      value = value >> 1;
    }

    if (bitString.length() > bitStringLength) {
      throw new EncodingException(
          "Numeric value '" + value + "' is too large for a bit string length of '" + bitStringLength + "'");
    }

    while (bitString.length() < bitStringLength) {
      bitString = BitString.FALSE_STRING + bitString;
    }

    return bitString;
  }

  public static int decode(BitString bitString) throws DecodingException {
    return decode(bitString, 0, bitString.length());
  }
  
  public static int decode(BitString bitString, int fromIndex, int length) throws DecodingException {
    int value = 0;

    for (int i = 0; i < length; i++) {
      if (bitString.getValue(fromIndex + length - (i + 1))) {
        value += 1 << i;
      }
    }

    return value;
  }

  public static int decode(String bitString, int fromIndex, int length) throws DecodingException {
    int value = 0;

    for (int i = 0; i < length; i++) {
      char c = bitString.charAt(fromIndex + length - (i + 1));
      if (c == BitString.TRUE) {
        value += 1 << i;
      } else if (c != BitString.FALSE) {
        throw new DecodingException("Unencodable Base64Url '" + bitString + "'");
      }
    }

    return value;
  }
}
