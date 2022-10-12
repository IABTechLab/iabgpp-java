package com.iab.gpp.encoder.datatype.encoder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import com.iab.gpp.encoder.error.DecodingException;

public class FibonacciIntegerEncoder {
  private static Pattern BITSTRING_VERIFICATION_PATTERN = Pattern.compile("^[0-1]*$", Pattern.CASE_INSENSITIVE);

  public static String encode(int value) {
    List<Integer> fib = new ArrayList<Integer>();
    if (value >= 1) {
      fib.add(1);

      if (value >= 2) {
        fib.add(2);

        int i = 2;
        while (value >= fib.get(i - 1) + fib.get(i - 2)) {
          fib.add(fib.get(i - 1) + fib.get(i - 2));
          i++;
        }
      }
    }

    String bitString = "1";
    for (int i = fib.size() - 1; i >= 0; i--) {
      int f = fib.get(i);
      if (value >= f) {
        bitString = "1" + bitString;
        value -= f;
      } else {
        bitString = "0" + bitString;
      }
    }

    return bitString;
  }

  public static int decode(String bitString) throws DecodingException {
    if (!BITSTRING_VERIFICATION_PATTERN.matcher(bitString).matches() || bitString.length() < 2
        || bitString.indexOf("11") != bitString.length() - 2) {
      throw new DecodingException("Undecodable FibonacciInteger '" + bitString + "'");
    }

    int value = 0;

    List<Integer> fib = new ArrayList<>();
    for (int i = 0; i < bitString.length() - 1; i++) {
      if (i == 0) {
        fib.add(1);
      } else if (i == 1) {
        fib.add(2);
      } else {
        fib.add(fib.get(i - 1) + fib.get(i - 2));
      }
    }

    for (int i = 0; i < bitString.length() - 1; i++) {
      if (bitString.charAt(i) == '1') {
        value += fib.get(i);
      }
    }
    return value;
  }
}
