package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.encoder.FibonacciIntegerEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class EncodableFibonacciInteger extends AbstractEncodableBitStringDataType<Integer> {

  protected EncodableFibonacciInteger() {
    super(true);
  }

  public EncodableFibonacciInteger(Integer value) {
    super(true);
    setValue(value);
  }

  public EncodableFibonacciInteger(Integer value, boolean hardFailIfMissing) {
    super(hardFailIfMissing);
    setValue(value);
  }

  public String encode() {
    try {
      return FibonacciIntegerEncoder.encode(this.value);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  public void decode(BitString bitString) {
    try {
      this.value = FibonacciIntegerEncoder.decode(bitString);
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }

  public BitString substring(BitString bitString, int fromIndex) throws SubstringException {
    try {
      int index = FibonacciIntegerEncoder.indexOfEndTag(bitString, fromIndex);
      if (index > 0) {
        return bitString.substring(fromIndex, index + 2);
      } else {
        return bitString;
      }
    } catch (Exception e) {
      throw new SubstringException(e);
    }
  }
}
