package com.iab.gpp.encoder.datatype.encoder;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.EncodingException;

public class FixedIntegerEncoder {
  private FixedIntegerEncoder() {}

  public static void encode(BitString builder, int value, int bitStringLength) {
    int mask = 1 << bitStringLength;
    if (value >= mask) {
      throw new EncodingException(
        "Numeric value '" + value + "' is too large for a bit string length of '" + bitStringLength + "'");
    }
    for (int i = 0; i < bitStringLength; i++) {
      mask >>= 1;
      builder.append((value & mask) > 0);
    }
  }
}
