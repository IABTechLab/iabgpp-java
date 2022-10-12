package com.iab.gpp.encoder.datatype;

import java.util.List;
import com.iab.gpp.encoder.datatype.encoder.FibonacciIntegerRangeEncoder;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerEncoder;
import com.iab.gpp.encoder.error.DecodingException;

public class EncodableFibonacciIntegerRange extends AbstractEncodableBitStringDataType<List<Integer>> {

  public EncodableFibonacciIntegerRange() {
    super();
  }

  public EncodableFibonacciIntegerRange(List<Integer> value) {
    super(value);
  }

  public String encode() {
    return FibonacciIntegerRangeEncoder.encode(this.value);
  }

  public void decode(String bitString) throws DecodingException {
    this.value = FibonacciIntegerRangeEncoder.decode(bitString);
  }

  public String substring(String bitString, int fromIndex) throws DecodingException {
    // TODO: add some validation
    int count = FixedIntegerEncoder.decode(bitString.substring(fromIndex, fromIndex + 12));
    int index = fromIndex + 12;
    for (int i = 0; i < count; i++) {
      if (bitString.charAt(index) == '1') {
        index = bitString.indexOf("11", bitString.indexOf("11", index + 1) + 2) + 2;
      } else {
        index = bitString.indexOf("11", index + 1) + 2;
      }
    }
    return bitString.substring(fromIndex, index);
  }
}
