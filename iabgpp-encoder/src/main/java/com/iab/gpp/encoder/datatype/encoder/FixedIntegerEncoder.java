package com.iab.gpp.encoder.datatype.encoder;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class FixedIntegerEncoder {
  private FixedIntegerEncoder() {}

  public static void encode(BitStringBuilder builder, int value, int bitStringLength) {
    int mask = 1 << bitStringLength;
    if (value >= mask) {
      throw new EncodingException(
        "Numeric value '" + value + "' is too large for a bit string length of '" + bitStringLength + "'");
    }
    for (int i = 0; i < bitStringLength; i++) {
      mask >>= 1;
      builder.append((value & mask) > 0);
    }
  }

  public static int decode(BitString bitString) throws DecodingException {
    return decode(bitString, 0, bitString.length());
  }
  
  public static int decode(BitString bitString, int fromIndex, int length) throws DecodingException {
    int value = 0;

    int base = fromIndex + length - 1;
    for (int i = 0; i < length; i++) {
      if (bitString.getValue(base - i)) {
        value += 1 << i;
      }
    }

    return value;
  }

  public static int decode(String bitString, int fromIndex, int length) throws DecodingException {
    int value = 0;

    int base = fromIndex + length - 1;
    for (int i = 0; i < length; i++) {
      char c = bitString.charAt(base - i);
      if (c == BitString.TRUE) {
        value += 1 << i;
      } else if (c != BitString.FALSE) {
        throw new DecodingException("Unencodable Base64Url '" + bitString + "'");
      }
    }

    return value;
  }
}
