package com.iab.gpp.encoder.datatype.encoder;

import com.iab.gpp.encoder.bitstring.BitString;

public class FixedBitfieldEncoder {
  private FixedBitfieldEncoder() {}

  public static void encode(BitString builder, IntegerSet value, int bitStringLength) {
    for (int i = 0; i < bitStringLength; i++) {
      BooleanEncoder.encode(builder, value.containsInt(i));
    }
  }
}
