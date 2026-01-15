package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.bitstring.BitStringReader;
import com.iab.gpp.encoder.datatype.encoder.FibonacciIntegerEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public final class EncodableFibonacciInteger extends AbstractEncodableBitStringDataType<Integer> {

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

  public void encode(BitStringBuilder builder) {
    try {
      FibonacciIntegerEncoder.encode(builder, this.value);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  public void decode(BitStringReader reader) {
    try {
      this.value = reader.readFibonacci();
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }
}
