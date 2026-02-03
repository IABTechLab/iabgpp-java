package com.iab.gpp.encoder.datatype.encoder;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.EncodingException;

public class FixedLongEncoder {
  private FixedLongEncoder() {}

  public static void encode(BitString builder, long value, int bitStringLength) {
    long mask = 1L << bitStringLength;
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
