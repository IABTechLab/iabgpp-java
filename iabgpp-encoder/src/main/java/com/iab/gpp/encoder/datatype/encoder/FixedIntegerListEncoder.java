package com.iab.gpp.encoder.datatype.encoder;

import java.util.List;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.bitstring.BitStringReader;
import com.iab.gpp.encoder.datatype.FixedIntegerList;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class FixedIntegerListEncoder {
  private FixedIntegerListEncoder() {}

  public static void encode(BitStringBuilder builder, List<Integer> value, int elementBitStringLength, int numElements) {
    int length = value.size();
    if (length > numElements) {
      throw new EncodingException("Too many values '" + value.size() + "'");
    }

    for (int i = 0; i < numElements; i++) {
      if (i < length) {
        FixedIntegerEncoder.encode(builder, value.get(i), elementBitStringLength);
      } else {
        for (int j = 0; j < elementBitStringLength; j++) {
          builder.append(false);
        }
      }
    }
  }

  public static void decode(FixedIntegerList out, BitStringReader reader, int elementBitStringLength)
      throws DecodingException {
    int size = out.size();
    for (int i = 0; i < size; i++) {
      out.setInt(i, reader.readInt(elementBitStringLength));
    }
  }
}
