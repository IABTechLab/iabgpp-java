package com.iab.gpp.encoder.datatype.encoder;

import java.util.ArrayList;
import java.util.List;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class FixedIntegerListEncoder {

  public static String encode(List<Integer> value, int elementBitStringLength, int numElements) {
    int length = value.size();
    if (length > numElements) {
      throw new EncodingException("Too many values '" + value.size() + "'");
    }

    int expectedLength = elementBitStringLength * numElements;

    StringBuilder bitString = new StringBuilder(expectedLength);
    for (int i = 0; i < numElements; i++) {
      if (i < length) {
        bitString.append(FixedIntegerEncoder.encode(value.get(i), elementBitStringLength));
      } else {
        for (int j = 0; j < elementBitStringLength; j++) {
          bitString.append(BitString.FALSE);
        }
      }
    }

    return bitString.toString();
  }

  public static List<Integer> decode(BitString bitString, int elementBitStringLength, int numElements)
      throws DecodingException {
    int length = bitString.length();
    if (length > elementBitStringLength * numElements) {
      throw new DecodingException("Undecodable FixedIntegerList '" + bitString + "'");
    }

    if (length % elementBitStringLength != 0) {
      throw new DecodingException("Undecodable FixedIntegerList '" + bitString + "'");
    }

    bitString = bitString.expandTo(elementBitStringLength * numElements);

    List<Integer> value = new ArrayList<>(numElements);
    length = bitString.length();
    for (int i = 0; i < length; i += elementBitStringLength) {
      value.add(IntegerCache.valueOf(FixedIntegerEncoder.decode(bitString, i, elementBitStringLength)));
    }

    while (value.size() < numElements) {
      value.add(0);
    }

    return value;
  }
}
