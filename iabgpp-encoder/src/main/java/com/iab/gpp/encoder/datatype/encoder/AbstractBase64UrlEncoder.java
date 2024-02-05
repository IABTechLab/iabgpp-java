package com.iab.gpp.encoder.datatype.encoder;

import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import it.unimi.dsi.fastutil.chars.Char2IntMap;
import it.unimi.dsi.fastutil.chars.Char2IntOpenHashMap;

public abstract class AbstractBase64UrlEncoder {

  abstract protected String pad(String bitString);

  /**
   * Base 64 URL character set. Different from standard Base64 char set in that '+' and '/' are
   * replaced with '-' and '_'.
   */
  private static final String DICT = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
  // prettier-ignore
  private static final Char2IntMap REVERSE_DICT = new Char2IntOpenHashMap(Stream
      .of(new Object[][] {{'A', 0}, {'B', 1}, {'C', 2}, {'D', 3}, {'E', 4}, {'F', 5}, {'G', 6}, {'H', 7}, {'I', 8},
          {'J', 9}, {'K', 10}, {'L', 11}, {'M', 12}, {'N', 13}, {'O', 14}, {'P', 15}, {'Q', 16}, {'R', 17}, {'S', 18},
          {'T', 19}, {'U', 20}, {'V', 21}, {'W', 22}, {'X', 23}, {'Y', 24}, {'Z', 25}, {'a', 26}, {'b', 27}, {'c', 28},
          {'d', 29}, {'e', 30}, {'f', 31}, {'g', 32}, {'h', 33}, {'i', 34}, {'j', 35}, {'k', 36}, {'l', 37}, {'m', 38},
          {'n', 39}, {'o', 40}, {'p', 41}, {'q', 42}, {'r', 43}, {'s', 44}, {'t', 45}, {'u', 46}, {'v', 47}, {'w', 48},
          {'x', 49}, {'y', 50}, {'z', 51}, {'0', 52}, {'1', 53}, {'2', 54}, {'3', 55}, {'4', 56}, {'5', 57}, {'6', 58},
          {'7', 59}, {'8', 60}, {'9', 61}, {'-', 62}, {'_', 63}})
      .collect(Collectors.toMap(data -> (Character) data[0], data -> (Integer) data[1])));

  public String encode(String bitString) throws EncodingException {
    // should only be 0 or 1
    if (isInvalidBitString(bitString)) {
      throw new EncodingException("Unencodable Base64Url '" + bitString + "'");
    }

    bitString = pad(bitString);

    StringBuilder sb = new StringBuilder();

    int index = 0;
    while (index <= bitString.length() - 6) {
      String s = bitString.substring(index, index + 6);

      try {
        int n = FixedIntegerEncoder.decode(s);
        char c = AbstractBase64UrlEncoder.DICT.charAt(n);
        sb.append(c);
        index += 6;
      } catch (DecodingException e) {
        throw new EncodingException("Unencodable Base64Url '" + bitString + "'");
      }
    }

    return sb.toString();
  }

  public String decode(String str) throws DecodingException {
    StringBuilder bitString = new StringBuilder();

    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      int n = AbstractBase64UrlEncoder.REVERSE_DICT.getOrDefault(c, -1);
      if (n == -1) {
        throw new DecodingException("Undecodable Base64URL string");
      }
      String s = FixedIntegerEncoder.encode(n, 6);
      bitString.append(s);
    }

    return bitString.toString();
  }

  public static boolean isInvalidBitString(String bitString) {
    for(int i = 0; i < bitString.length(); i++) {
      char testChar = bitString.charAt(i);
      if (testChar != '0' && testChar != '1') {
        return true;
      }
    }
    return false;
  }
}
