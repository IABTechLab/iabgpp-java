package com.iab.gpp.encoder.datatype;

import java.util.function.Predicate;
import com.iab.gpp.encoder.datatype.encoder.FibonacciIntegerEncoder;

public class EncodableFibonacciInteger extends AbstractEncodableBitStringDataType<Integer> {

  protected EncodableFibonacciInteger() {
    super();
  }

  public EncodableFibonacciInteger(Integer value) {
    super();
    setValue(value);
  }

  public EncodableFibonacciInteger(Integer value, Predicate<Integer> validator) {
    super(validator);
    setValue(value);
  }

  public String encode() {
    return FibonacciIntegerEncoder.encode(this.value);
  }

  public void decode(String bitString) {
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
