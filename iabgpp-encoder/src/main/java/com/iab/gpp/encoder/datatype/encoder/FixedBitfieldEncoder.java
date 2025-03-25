package com.iab.gpp.encoder.datatype.encoder;

import java.util.List;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.error.EncodingException;

public class FixedBitfieldEncoder {

  public static void encode(BitStringBuilder builder, List<Boolean> value, int bitStringLength) {
    int length = value.size();
    if (length > bitStringLength) {
      throw new EncodingException("Too many values '" + length + "'");
    }

    for (int i = 0; i < bitStringLength; i++) {
      if (i < length) {
        BooleanEncoder.encode(builder, value.get(i));
      } else {
        builder.append(false);
      }
    }
  }

  public static List<Boolean> decode(BitString bitString) {
    return bitString;
  }
}
