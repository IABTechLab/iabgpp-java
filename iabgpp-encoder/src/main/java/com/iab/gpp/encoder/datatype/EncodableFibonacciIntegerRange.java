package com.iab.gpp.encoder.datatype;

import java.util.Collection;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.bitstring.BitStringReader;
import com.iab.gpp.encoder.datatype.encoder.FibonacciIntegerRangeEncoder;
import com.iab.gpp.encoder.datatype.encoder.IntegerSet;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public final class EncodableFibonacciIntegerRange extends AbstractDirtyableBitStringDataType<IntegerSet> {

  public EncodableFibonacciIntegerRange() {
    super(true);
    this.value = new IntegerSet();
  }

  public void encode(BitStringBuilder builder) {
    try {
      FibonacciIntegerRangeEncoder.encode(builder, this.value);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  public void decode(BitStringReader reader) {
    try {
      this.value = FibonacciIntegerRangeEncoder.decode(reader);
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public void setValue(Object value) {
    this.value.clear();
    this.value.addAll((Collection<Integer>) value);
  }
}
