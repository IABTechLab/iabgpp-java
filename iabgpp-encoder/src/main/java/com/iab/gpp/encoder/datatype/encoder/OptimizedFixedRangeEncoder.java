package com.iab.gpp.encoder.datatype.encoder;

import java.util.ArrayList;
import java.util.List;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class OptimizedFixedRangeEncoder {

  public static String encode(List<Integer> value) throws EncodingException {
    // TODO: encoding the range before choosing the shortest is inefficient. There is probably a way
    // to identify in advance which will be shorter based on the array length and values
    int max = value.size() > 0 ? value.get(value.size() - 1) : 0;
    String rangeBitString = FixedIntegerRangeEncoder.encode(value);
    int rangeLength = rangeBitString.length();
    int bitFieldLength = max;

    if (rangeLength <= bitFieldLength) {
      return FixedIntegerEncoder.encode(max, 16) + BitString.TRUE_STRING + rangeBitString;
    } else {
      List<Boolean> bits = new ArrayList<>(max);
      int index = 0;
      for (int i = 0; i < max; i++) {
        if (i == value.get(index) - 1) {
          bits.add(true);
          index++;
        } else {
          bits.add(false);
        }
      }

      return FixedIntegerEncoder.encode(max, 16) + BitString.FALSE_STRING + FixedBitfieldEncoder.encode(bits, bitFieldLength);
    }
  }

  public static List<Integer> decode(BitString bitString) throws DecodingException {
    if (bitString.length() < 12) {
      throw new DecodingException("Undecodable FixedIntegerRange '" + bitString + "'");
    }

    if (bitString.getValue(16)) {
      return FixedIntegerRangeEncoder.decode(bitString.substring(17));
    } else {
      BitString bits = bitString.substring(17);
      int length = bits.length();
      List<Integer> value = new ArrayList<>(length);
      for (int i = 0; i < length; i++) {
        if (bits.getValue(i)) {
          value.add(IntegerCache.valueOf(i + 1));
        }
      }
      return value;
    }
  }
}
