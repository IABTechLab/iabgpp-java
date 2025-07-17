package com.iab.gpp.encoder.datatype.encoder;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;

public class FixedBitfieldEncoder {
  private FixedBitfieldEncoder() {}

  public static void encode(BitStringBuilder builder, IntegerSet value, int bitStringLength) {
    for (int i = 0; i < bitStringLength; i++) {
      BooleanEncoder.encode(builder, value.containsInt(i));
    }
  }

  public static IntegerSet decode(BitString bitString) {
    return bitString.toIntegerSet();
  }
}
