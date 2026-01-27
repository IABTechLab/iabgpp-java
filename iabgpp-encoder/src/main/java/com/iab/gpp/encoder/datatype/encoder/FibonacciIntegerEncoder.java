package com.iab.gpp.encoder.datatype.encoder;

import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.error.EncodingException;

public class FibonacciIntegerEncoder {
  private FibonacciIntegerEncoder() {}

  // this is the length of the longest fibonacci encoded string of all 1's
  // which does not overflow a 32-bit integer
  public static final int FIBONACCI_LIMIT = 42;
  public static final int[] FIBONACCI_NUMBERS = new int[FIBONACCI_LIMIT];
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
}
