package com.iab.gpp.encoder.datatype.encoder;

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
}
