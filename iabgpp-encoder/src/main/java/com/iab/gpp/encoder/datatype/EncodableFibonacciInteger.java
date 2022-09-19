package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.datatype.encoder.FibonacciIntegerEncoder;
import com.iab.gpp.encoder.error.DecodingException;

public class EncodableFibonacciInteger extends AbstractEncodableBitStringDataType<Integer> {
  
  public EncodableFibonacciInteger() {
    super();
  }
  
  public EncodableFibonacciInteger(Integer value) {
    super(value);
  }
  
  public String encode() {
    return FibonacciIntegerEncoder.encode(this.value);
  }

  public void decode(String bitString) throws DecodingException {
    this.value = FibonacciIntegerEncoder.decode(bitString);
  }

  public String substring(String bitString, int fromIndex) {
    int index = bitString.indexOf("11", fromIndex);
    if (index > 0) {
      return bitString.substring(fromIndex, index + 2);
    } else {
      return bitString;
    }
  }
}
