package com.iab.gpp.encoder.base64;

import java.util.Arrays;
import com.iab.gpp.encoder.bitstring.BitSet;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public abstract class AbstractBase64UrlEncoder {

  protected abstract void pad(BitStringBuilder bitString);

  private static final int BASE64_BITS = 6;
  private static final int NO_SYMBOL = -1;
  /**
   * Base 64 URL character set. Different from standard Base64 char set in that '+' and '/' are
   * replaced with '-' and '_'.
   */
  private static final String DICT = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
  private static final int REVERSE_DICT_SIZE = 128;
  private static final int[] REVERSE_DICT = new int[REVERSE_DICT_SIZE];
  static {
    Arrays.fill(REVERSE_DICT, NO_SYMBOL);
    for (int i = 0; i < DICT.length(); i++) {
      // NOTE: the bit string is stored in a long[] and read from LSB to MSB
      // but each base64 digit is read from MSB to LSB
      // so they need to be reversed.
      int value  = Integer.reverse(i) >>> -BASE64_BITS;
      REVERSE_DICT[DICT.charAt(i)] = value;
    }
  }

  public StringBuilder encode(BitStringBuilder bitStringBuilder) {
    pad(bitStringBuilder);
    BitString bitString = bitStringBuilder.build();
    int length = bitString.length();
    StringBuilder str = new StringBuilder(length / BASE64_BITS);

    int index = 0;
    while (index <= length - BASE64_BITS) {
      try {
        int nextIndex = index + BASE64_BITS;
        int n = FixedIntegerEncoder.decode(bitString, index, nextIndex);
        str.append(DICT.charAt(n));
        index = nextIndex;
      } catch (DecodingException e) {
        throw new EncodingException("Unencodable Base64Url '" + bitString + "'");
      }
    }

    return str;
  }

  public BitString decode(CharSequence str) {
    try {
      int length = str.length();
      int bitLength = length * BASE64_BITS;
      int numBlocks = length >> 2;
      byte[] words = new byte[(numBlocks + 1) * 3];
      int limit = numBlocks << 2;
      int dst = 0;
      int src = 0;
      while (src < limit) {
        int b1 = REVERSE_DICT[str.charAt(src++)];
        int b2 = REVERSE_DICT[str.charAt(src++)];
        int b3 = REVERSE_DICT[str.charAt(src++)];
        int b4 = REVERSE_DICT[str.charAt(src++)];
        if ((b1 | b2 | b3 | b4) < 0) {
          throw new DecodingException("Undecodable Base64URL string");
        }
        int bits0 = b4 << 18 | b3 << 12 | b2 << 6 | b1;
        words[dst++] = (byte)(bits0);
        words[dst++] = (byte)(bits0 >>  8);
        words[dst++] = (byte)(bits0 >> 16);
      }
      if (length > limit) {
        remainder(str, words, length, src, dst);
      }
      return new BitString(new BitSet(words), bitLength);
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new DecodingException("Undecodable Base64URL string");
    }
  }

  private static final void remainder(CharSequence str, byte[] words, int length, int src, int dst) {
    int b1 = src < length ? REVERSE_DICT[str.charAt(src)] : 0;
    src++;
    int b2 = src < length ? REVERSE_DICT[str.charAt(src)] : 0;
    src++;
    int b3 = src < length ? REVERSE_DICT[str.charAt(src)] : 0;
    src++;
    int b4 = src < length ? REVERSE_DICT[str.charAt(src)] : 0;
    src++;
    if ((b1 | b2 | b3 | b4) < 0) {
      throw new DecodingException("Undecodable Base64URL string");
    }
    int bits0 = b4 << 18 | b3 << 12 | b2 << 6 | b1;
    words[dst++] = (byte)(bits0);
    words[dst++] = (byte)(bits0 >>  8);
    words[dst++] = (byte)(bits0 >> 16);
  }
}
