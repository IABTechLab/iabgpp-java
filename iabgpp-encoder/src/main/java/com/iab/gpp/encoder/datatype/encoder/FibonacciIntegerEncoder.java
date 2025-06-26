package com.iab.gpp.encoder.datatype.encoder;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class FibonacciIntegerEncoder {
  private FibonacciIntegerEncoder() {}

  // this is the length of the longest fibonacci encoded string of all 1's
  // which does not overflow a 32-bit integer
  private static final int FIBONACCI_LIMIT = 42;
  private static final int[] FIBONACCI_NUMBERS = new int[FIBONACCI_LIMIT];
  static {
    for (int i = 0; i < FIBONACCI_LIMIT; i++) {
      if (i == 0) {
        FIBONACCI_NUMBERS[i] = 1;
      } else if (i == 1) {
        FIBONACCI_NUMBERS[i] = 2;
      } else {
        FIBONACCI_NUMBERS[i] = FIBONACCI_NUMBERS[i - 1] + FIBONACCI_NUMBERS[i - 2];
      }
    }
  }

  public static void encode(BitStringBuilder builder, int value) {
    int largestIndex = 0;
    for (int i = 0; i < FIBONACCI_LIMIT; i++) {
      if (value >= FIBONACCI_NUMBERS[i]) {
        largestIndex++;
      } else {
        break;
      }
    }
    if (largestIndex == FIBONACCI_LIMIT) {
      throw new EncodingException("Unencodable FibonacciInteger " + value);
    }

    int out = 1;
    int mask = 1;
    for (int i = largestIndex - 1; i >= 0; i--) {
      mask <<= 1;
      int f = FIBONACCI_NUMBERS[i];
      if (value >= f) {
        out |= mask;
        value -= f;
      }
    }
    FixedIntegerEncoder.encode(builder, out, largestIndex + 1);
  }

  public static int decode(BitString bitString) throws DecodingException {
    return decode(bitString, 0, bitString.length());
  }

  public static int decode(BitString bitString, int fromIndex, int length) throws DecodingException {
    int limit = length - 1;
    int end = fromIndex + length;
    // must not overflow and must end with "11"
    if (length < 2 || limit > FIBONACCI_LIMIT || !bitString.getValue(end - 2) || !bitString.getValue(end - 1)) {
      throw new DecodingException("Undecodable FibonacciInteger '" + bitString + "'");
    }

    int value = 0;
    for (int i = 0; i < limit; i++) {
      if (bitString.getValue(fromIndex + i)) {
        value += FIBONACCI_NUMBERS[i];
      }
    }
    return value;
  }

  public static int indexOfEndTag(BitString bitString, int fromIndex) {
    int limit = bitString.length() - 1;
    int i = fromIndex;
    while (i < limit) {
      if (bitString.getValue(i)) {
        // 1X
        if (bitString.getValue(i + 1)) {
          // 11
          return i;
        } else {
          // 10, the next can be skipped
          i += 2;
        }
      } else {
        // 0, next
        i++;
      }
    }
    return -1;
  }
}
