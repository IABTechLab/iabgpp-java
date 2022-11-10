package com.iab.gpp.encoder.datatype.encoder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class FixedIntegerListEncoder {

  private static Pattern BITSTRING_VERIFICATION_PATTERN = Pattern.compile("^[0-1]*$", Pattern.CASE_INSENSITIVE);

  public static String encode(List<Integer> value, int elementBitStringLength, int numElements)
      throws EncodingException {
    String bitString = "";
    for (int i = 0; i < value.size(); i++) {
      bitString += FixedIntegerEncoder.encode(value.get(i), elementBitStringLength);
    }

    while (bitString.length() < elementBitStringLength * numElements) {
      bitString += "0";
    }

    return bitString;
  }

  public static List<Integer> decode(String bitString, int elementBitStringLength, int numElements)
      throws DecodingException {
    if (!BITSTRING_VERIFICATION_PATTERN.matcher(bitString).matches()) {
      throw new DecodingException("Undecodable FixedIntegerList '" + bitString + "'");
    }

    if(bitString.length() > elementBitStringLength * numElements) {
      throw new DecodingException("Undecodable FixedIntegerList '" + bitString + "'");
    }
    
    if(bitString.length() % elementBitStringLength != 0) {
      throw new DecodingException("Undecodable FixedIntegerList '" + bitString + "'");
    }
    
    while (bitString.length() < elementBitStringLength * numElements) {
      bitString += "0";
    }
    
    if(bitString.length() > elementBitStringLength * numElements) {
      bitString = bitString.substring(0, elementBitStringLength * numElements);
    }
    
    List<Integer> value = new ArrayList<>();
    for (int i = 0; i < bitString.length(); i += elementBitStringLength) {
      value.add(FixedIntegerEncoder.decode(bitString.substring(i, i + elementBitStringLength)));
    }

    while (value.size() < numElements) {
      value.add(0);
    }

    return value;
  }
}
