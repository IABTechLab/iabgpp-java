package com.iab.gpp.encoder.datatype.encoder;

import java.util.List;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.EncodingException;

public class FixedBitfieldEncoder {

  public static String encode(List<Boolean> value, int bitStringLength) {
    int length = value.size();
    if (length > bitStringLength) {
      throw new EncodingException("Too many values '" + length + "'");
    }

    StringBuilder bitString = new StringBuilder(length);
    for (int i = 0; i < bitStringLength; i++) {
      if (i < length) {
        bitString.append(BooleanEncoder.encode(value.get(i)));
      } else {
        bitString.append(BitString.FALSE);
      }
    }

    return bitString.toString();
  }

  public static List<Boolean> decode(BitString bitString) {
    return bitString;
  }
}
