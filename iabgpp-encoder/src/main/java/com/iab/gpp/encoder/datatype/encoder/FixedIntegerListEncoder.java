package com.iab.gpp.encoder.datatype.encoder;

import java.util.List;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.EncodingException;

public class FixedIntegerListEncoder {
  private FixedIntegerListEncoder() {}

  public static void encode(BitString builder, List<Integer> value, int elementBitStringLength, int numElements) {
    int length = value.size();
    if (length > numElements) {
      throw new EncodingException("Too many values '" + value.size() + "'");
    }

    for (int i = 0; i < numElements; i++) {
      if (i < length) {
        builder.writeInt(value.get(i), elementBitStringLength);
      } else {
        for (int j = 0; j < elementBitStringLength; j++) {
          builder.writeBoolean(false);
        }
      }
    }
  }
}
