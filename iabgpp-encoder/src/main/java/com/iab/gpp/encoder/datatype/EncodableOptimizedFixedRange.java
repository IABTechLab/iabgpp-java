package com.iab.gpp.encoder.datatype;

import java.util.ArrayList;
import java.util.List;
import com.iab.gpp.encoder.datatype.encoder.FixedBitfieldEncoder;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerEncoder;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerRangeEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class EncodableOptimizedFixedRange extends AbstractEncodableBitStringDataType<List<Integer>> {

  public EncodableOptimizedFixedRange() {
    super();
  }

  public EncodableOptimizedFixedRange(List<Integer> value) {
    super(value);
  }

  public String encode() throws EncodingException {
    // TODO: encoding the range before choosing the shortest is inefficient. There is probably a way
    // to identify in advance which will be shorter based on the array length and values
    int max = this.value.size() > 0 ? this.value.get(this.value.size() - 1) : 0;
    String rangeBitString = FixedIntegerRangeEncoder.encode(this.value);
    int rangeLength = rangeBitString.length();
    int bitFieldLength = max;

    if (rangeLength <= bitFieldLength) {
      return FixedIntegerEncoder.encode(max, 16) + "1" + rangeBitString;
    } else {
      List<Boolean> bits = new ArrayList<>();
      int index = 0;
      for (int i = 0; i < max; i++) {
        if (i == this.value.get(index) - 1) {
          bits.add(true);
          index++;
        } else {
          bits.add(false);
        }
      }

      return FixedIntegerEncoder.encode(max, 16) + "0" + FixedBitfieldEncoder.encode(bits, bitFieldLength);
    }
  }

  public void decode(String bitString) throws DecodingException {
    if (bitString.charAt(16) == '1') {
      this.value = FixedIntegerRangeEncoder.decode(bitString.substring(17));
    } else {
      List<Integer> value = new ArrayList<>();
      List<Boolean> bits = FixedBitfieldEncoder.decode(bitString.substring(17));
      for (int i = 0; i < bits.size(); i++) {
        if (bits.get(i) == true) {
          value.add(i + 1);
        }
      }
      this.value = value;
    }
  }

  public String substring(String bitString, int fromIndex) throws DecodingException {
    int max = FixedIntegerEncoder.decode(bitString.substring(fromIndex, fromIndex + 16));
    if (bitString.charAt(fromIndex + 16) == '1') {
      return bitString.substring(fromIndex, fromIndex + 17) + new EncodableFixedIntegerRange().substring(bitString, fromIndex + 17);
    } else {
      return bitString.substring(fromIndex, fromIndex + 17 + max);
    }
  }
}
