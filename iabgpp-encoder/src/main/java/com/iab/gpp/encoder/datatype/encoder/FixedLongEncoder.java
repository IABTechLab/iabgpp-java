package com.iab.gpp.encoder.datatype.encoder;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class FixedLongEncoder {

  public static void encode(BitStringBuilder builder, long value, int bitStringLength) {
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
