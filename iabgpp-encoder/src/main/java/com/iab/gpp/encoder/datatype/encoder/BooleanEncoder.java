package com.iab.gpp.encoder.datatype.encoder;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.EncodingException;

public class BooleanEncoder {
  private BooleanEncoder() {}
  public static void encode(BitString builder, Boolean value) {
    if (value == null) {
      throw new EncodingException("Unencodable Boolean '" + value + "'");
    }
    builder.append(value);
  }
}
