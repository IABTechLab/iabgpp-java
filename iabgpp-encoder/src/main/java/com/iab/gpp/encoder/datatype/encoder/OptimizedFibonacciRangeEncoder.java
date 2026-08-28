package com.iab.gpp.encoder.datatype.encoder;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.IntegerSet;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

/**
 * Encodes an {@code OptimizedRange} that uses Fibonacci coding for the range representation, per
 * the GPP Consent String Specification. Counterpart to {@link OptimizedFixedRangeEncoder}, which
 * uses fixed-integer ranges for downward compatibility.
 */
public class OptimizedFibonacciRangeEncoder {

  public static void encode(BitString builder, IntegerSet value) throws EncodingException {
    // TODO: encoding the range before choosing the shortest is inefficient. There is probably a way
    // to identify in advance which will be shorter based on the array length and values
    BitString rangeBitString = new BitString();
    int max = FibonacciIntegerRangeEncoder.encode(rangeBitString, value);
    int rangeLength = rangeBitString.length();
    int bitFieldLength = max;

    if (rangeLength <= bitFieldLength) {
      builder.writeInt(max, 16);
      builder.writeBoolean(true);
      builder.write(rangeBitString);
    } else {
      builder.writeInt(max, 16);
      builder.writeBoolean(false);
      for (int i = 0; i < max; i++) {
        builder.writeBoolean(value.containsInt(i + 1));
      }
    }
  }

  public static IntegerSet decode(BitString reader) throws DecodingException {
    int size = reader.readInt(16);
    if (reader.readBoolean()) {
      return FibonacciIntegerRangeEncoder.decode(reader);
    } else {
      return reader.readIntegerSet(size);
    }
  }
}
