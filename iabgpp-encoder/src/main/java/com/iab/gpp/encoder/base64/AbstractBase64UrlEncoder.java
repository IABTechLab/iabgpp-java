package com.iab.gpp.encoder.base64;

import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public abstract class AbstractBase64UrlEncoder {

  abstract protected String pad(String bitString);

  /**
   * Base 64 URL character set. Different from standard Base64 char set in that '+' and '/' are
   * replaced with '-' and '_'.
   */
  private static String DICT = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
  // prettier-ignore
  private static Map<Character, Integer> REVERSE_DICT = Stream
      .of(new Object[][] {{'A', 0}, {'B', 1}, {'C', 2}, {'D', 3}, {'E', 4}, {'F', 5}, {'G', 6}, {'H', 7}, {'I', 8},
          {'J', 9}, {'K', 10}, {'L', 11}, {'M', 12}, {'N', 13}, {'O', 14}, {'P', 15}, {'Q', 16}, {'R', 17}, {'S', 18},
          {'T', 19}, {'U', 20}, {'V', 21}, {'W', 22}, {'X', 23}, {'Y', 24}, {'Z', 25}, {'a', 26}, {'b', 27}, {'c', 28},
          {'d', 29}, {'e', 30}, {'f', 31}, {'g', 32}, {'h', 33}, {'i', 34}, {'j', 35}, {'k', 36}, {'l', 37}, {'m', 38},
          {'n', 39}, {'o', 40}, {'p', 41}, {'q', 42}, {'r', 43}, {'s', 44}, {'t', 45}, {'u', 46}, {'v', 47}, {'w', 48},
          {'x', 49}, {'y', 50}, {'z', 51}, {'0', 52}, {'1', 53}, {'2', 54}, {'3', 55}, {'4', 56}, {'5', 57}, {'6', 58},
          {'7', 59}, {'8', 60}, {'9', 61}, {'-', 62}, {'_', 63}})
      .collect(Collectors.toMap(data -> (Character) data[0], data -> (Integer) data[1]));

  private static Pattern BITSTRING_VERIFICATION_PATTERN = Pattern.compile("^[0-1]*$", Pattern.CASE_INSENSITIVE);
  private static Pattern BASE64URL_VERIFICATION_PATTERN =
      Pattern.compile("^[A-Za-z0-9\\-_]*$", Pattern.CASE_INSENSITIVE);

  public String encode(String bitString) {
    // should only be 0 or 1
    if (!BITSTRING_VERIFICATION_PATTERN.matcher(bitString).matches()) {
      throw new EncodingException("Unencodable Base64Url '" + bitString + "'");
    }

    bitString = pad(bitString);

    String str = "";

    int index = 0;
    while (index <= bitString.length() - 6) {
      String s = bitString.substring(index, index + 6);

      try {
        int n = FixedIntegerEncoder.decode(s);
        Character c = AbstractBase64UrlEncoder.DICT.charAt(n);
        str += c;
        index += 6;
      } catch (DecodingException e) {
        throw new EncodingException("Unencodable Base64Url '" + bitString + "'");
      }
    }

    return str;
  }

  public String decode(String str) {
    // should contain only characters from the base64url set
    if (!BASE64URL_VERIFICATION_PATTERN.matcher(str).matches()) {
      throw new DecodingException("Undecodable Base64URL string");
    }

    String bitString = "";

    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      Integer n = AbstractBase64UrlEncoder.REVERSE_DICT.get(c);
      String s = FixedIntegerEncoder.encode(n, 6);
      bitString += s;
    }

    return bitString;
  }
}
