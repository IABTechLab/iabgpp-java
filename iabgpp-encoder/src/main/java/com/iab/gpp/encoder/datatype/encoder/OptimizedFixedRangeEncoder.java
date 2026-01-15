package com.iab.gpp.encoder.datatype.encoder;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.bitstring.BitStringReader;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class OptimizedFixedRangeEncoder {

  public static void encode(BitStringBuilder builder, IntegerSet value) throws EncodingException {
    // TODO: encoding the range before choosing the shortest is inefficient. There is probably a way
    // to identify in advance which will be shorter based on the array length and values
    BitStringBuilder rangeBitString = new BitStringBuilder();
    int max = FixedIntegerRangeEncoder.encode(rangeBitString, value);
    int rangeLength = rangeBitString.length();
    int bitFieldLength = max;

    if (rangeLength <= bitFieldLength) {
      FixedIntegerEncoder.encode(builder, max, 16);
      builder.append(true).append(rangeBitString);
    } else {
      FixedIntegerEncoder.encode(builder, max, 16);
      builder.append(false);
      for (int i = 0; i < max; i++) {
        builder.append(value.containsInt(i + 1));
      }
    }
  }

  public static IntegerSet decode(BitStringReader reader) throws DecodingException {
    int size = reader.readInt(16);
    if (reader.readBool()) {
      return FixedIntegerRangeEncoder.decode(reader);
    } else {
      return reader.readIntegerSet(size);
    }
  }
}
