package com.iab.gpp.encoder.datatype.encoder;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;

public class ArrayOfRangesEntryEncoder {


  public static String encode(long value, int bitStringLength) {
    String bitString = "";
    while (value > 0) {
      if ((value & 1) == 1) {
        bitString = BitString.TRUE_STRING + bitString;
      } else {
        bitString = BitString.FALSE_STRING + bitString;
      }
      value = value >> 1;
    }

    while (bitString.length() < bitStringLength) {
      bitString = BitString.FALSE_STRING + bitString;
    }

    return bitString;
  }

  public static long decode(BitString bitString) throws DecodingException {
    long value = 0;

    int length = bitString.length();
    for (int i = 0; i < length; i++) {
      if (bitString.getValue(length - (i + 1))) {
        value += 1L << i;
      }
    }

    return value;
  }
}
