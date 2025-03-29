package com.iab.gpp.encoder.datatype.encoder;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class OptimizedFibonacciRangeEncoder {

  public static void encode(BitStringBuilder builder, IntegerSet value) throws EncodingException {
    // TODO: encoding the range before choosing the shortest is inefficient. There is probably a way
    // to identify in advance which will be shorter based on the array length and values
    BitStringBuilder rangeBitString = new BitStringBuilder();
    int max = FibonacciIntegerRangeEncoder.encode(rangeBitString, value);
    int rangeLength = rangeBitString.length();
    int bitFieldLength = max;

    if (rangeLength <= bitFieldLength) {
      FixedIntegerEncoder.encode(builder, max, 16);
      builder.append(true).append(rangeBitString);
    } else {
      FixedIntegerEncoder.encode(builder, max, 16);
      builder.append(false);
      for (int i = 0; i < max; i++) {
        if (value.contains(i + 1)) {
          builder.append(true);
        } else {
          builder.append(false);
        }
      }
    }
  }

  public static IntegerSet decode(BitString bitString) throws DecodingException {
    if (bitString.length() < 12) {
      throw new DecodingException("Undecodable FibonacciIntegerRange '" + bitString + "'");
    }

    if (bitString.getValue(16)) {
      return FibonacciIntegerRangeEncoder.decode(bitString.substring(17));
    } else {
      BitString bits = bitString.substring(17);
      int length = bits.length();
      BitStringSet value = new BitStringSet(length + 1);
      for (int i = 0; i < length; i++) {
        if (bits.getValue(i)) {
          value.addInt(i + 1);
        }
      }
      return value;
    }
  }
}
