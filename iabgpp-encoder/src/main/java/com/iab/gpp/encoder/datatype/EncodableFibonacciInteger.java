package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public final class EncodableFibonacciInteger extends AbstractEncodableBitStringDataType<Integer> {

  protected EncodableFibonacciInteger() {
    super(true);
  }

  public EncodableFibonacciInteger(Integer value) {
    super(true);
    setValue(value, false);
  }

  public EncodableFibonacciInteger(Integer value, boolean hardFailIfMissing) {
    super(hardFailIfMissing);
    setValue(value, false);
  }

  public void encode(BitString builder) {
    try {
      builder.writeFibonacci(this.value);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  public void decode(BitString reader) {
    try {
      this.value = reader.readFibonacci();
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }
}
