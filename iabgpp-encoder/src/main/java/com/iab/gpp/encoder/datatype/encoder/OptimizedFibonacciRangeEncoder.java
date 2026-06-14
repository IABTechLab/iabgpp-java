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
      // Range form. It is Fibonacci-coded in the current spec, but strings produced by the previous
      // encoder used a fixed-integer range here. Decode as Fibonacci and, if the consumed bits do
      // not re-encode to that same Fibonacci range, re-read them as a fixed-integer range.
      int mark = reader.getReadIndex();
      try {
        IntegerSet value = FibonacciIntegerRangeEncoder.decode(reader);
        BitString consumed = new BitString();
        consumed.write(reader, mark, reader.getReadIndex());
        BitString reEncoded = new BitString();
        FibonacciIntegerRangeEncoder.encode(reEncoded, value);
        if (reEncoded.toString().equals(consumed.toString())) {
          return value;
        }
      } catch (RuntimeException e) {
        // not a valid Fibonacci range; fall back to the legacy fixed-integer range
      }
      reader.setReadIndex(mark);
      return FixedIntegerRangeEncoder.decode(reader);
    } else {
      return reader.readIntegerSet(size);
    }
  }
}
