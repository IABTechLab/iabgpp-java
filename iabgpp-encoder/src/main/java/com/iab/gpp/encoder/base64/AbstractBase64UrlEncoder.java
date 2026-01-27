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
  private static final long NO_SYMBOL = -1;
  /**
   * Base 64 URL character set. Different from standard Base64 char set in that '+' and '/' are
   * replaced with '-' and '_'.
   */
  private static final String DICT = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
  private static final int REVERSE_DICT_SIZE = 128;
  private static final long[] REVERSE_DICT = new long[REVERSE_DICT_SIZE];
  static {
    Arrays.fill(REVERSE_DICT, NO_SYMBOL);
    for (int i = 0; i < DICT.length(); i++) {
      // NOTE: the bit string is stored in a long[] and read from LSB to MSB
      // but each base64 digit is read from MSB to LSB
      // so they need to be reversed.
      REVERSE_DICT[DICT.charAt(i)] = Long.reverse(i) >>> -BASE64_BITS;
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
        index = nextIndex;;
      } catch (DecodingException e) {
        throw new EncodingException("Unencodable Base64Url '" + bitString + "'");
      }
    }

    return str;
  }

  public BitString decode(CharSequence str) {
    int length = str.length();
    int bitLength = length * BASE64_BITS;
    long [] words = new long[bitLength / BitSet.BITS_PER_WORD + 1];
    int bitIndex = 0;
    for (int i = 0; i < length; i++) {
      char c = str.charAt(i);
      long n = NO_SYMBOL;
      if (c < REVERSE_DICT_SIZE) {
        n = REVERSE_DICT[c];
      }
      if (n == NO_SYMBOL) {
        throw new DecodingException("Undecodable Base64URL string");
      }
      int wordIndex = BitSet.wordIndex(bitIndex);
      words[wordIndex] |= (n << bitIndex);
      int nextBitIndex = bitIndex + BASE64_BITS;
      int nextWordIndex = BitSet.wordIndex(nextBitIndex);
      if(wordIndex != nextWordIndex) {
        words[nextWordIndex] = n >>> (BitSet.BITS_PER_WORD - bitIndex);
      }
      bitIndex = nextBitIndex;
    }
    return new BitString(new BitSet(words), bitLength);
  }
}
