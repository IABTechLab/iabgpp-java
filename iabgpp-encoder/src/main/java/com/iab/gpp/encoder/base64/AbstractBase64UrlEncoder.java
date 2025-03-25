package com.iab.gpp.encoder.base64;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public abstract class AbstractBase64UrlEncoder {

  abstract protected void pad(BitStringBuilder bitString);

  private static final int BASE64_BITS = 6;
  /**
   * Base 64 URL character set. Different from standard Base64 char set in that '+' and '/' are
   * replaced with '-' and '_'.
   */
  private static final String DICT = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
  private static final int REVERSE_DICT_SIZE = 128;
  private static final BitString[] REVERSE_DICT = new BitString[REVERSE_DICT_SIZE];
  static {
    for (int i = 0; i < DICT.length(); i++) {
      BitStringBuilder builder = new BitStringBuilder();
      FixedIntegerEncoder.encode(builder, i, 6);
      REVERSE_DICT[DICT.charAt(i)] = builder.build();
    }
  }

  public String encode(BitStringBuilder bitStringBuilder) {
    pad(bitStringBuilder);
    BitString bitString = bitStringBuilder.build();
    int length = bitString.length();
    StringBuilder str = new StringBuilder(length / BASE64_BITS);

    int index = 0;
    while (index <= length - BASE64_BITS) {
      try {
        int n = FixedIntegerEncoder.decode(bitString, index, BASE64_BITS);
        str.append(DICT.charAt(n));
        index += BASE64_BITS;
      } catch (DecodingException e) {
        throw new EncodingException("Unencodable Base64Url '" + bitString + "'");
      }
    }

    return str.toString();
  }

  public BitString decode(CharSequence str) {
    int length = str.length();
    BitStringBuilder sb = new BitStringBuilder(length * BASE64_BITS);
    for (int i = 0; i < length; i++) {
      char c = str.charAt(i);
      BitString n = null;
      if (c < REVERSE_DICT_SIZE) {
        n = REVERSE_DICT[c];
      }
      if (n == null) {
        throw new DecodingException("Undecodable Base64URL string");
      }
      sb.append(n);

    }
    return sb.build();
  }
}
