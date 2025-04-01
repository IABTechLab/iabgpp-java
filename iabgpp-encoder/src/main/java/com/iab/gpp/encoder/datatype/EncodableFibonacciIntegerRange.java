package com.iab.gpp.encoder.datatype;

import java.util.Collection;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.datatype.encoder.BitStringSet;
import com.iab.gpp.encoder.datatype.encoder.FibonacciIntegerEncoder;
import com.iab.gpp.encoder.datatype.encoder.FibonacciIntegerRangeEncoder;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerEncoder;
import com.iab.gpp.encoder.datatype.encoder.IntegerSet;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class EncodableFibonacciIntegerRange extends AbstractEncodableBitStringDataType<IntegerSet> {

  public EncodableFibonacciIntegerRange() {
    super(true);
    this.value = new BitStringSet();
  }

  public void encode(BitStringBuilder builder) {
    try {
      FibonacciIntegerRangeEncoder.encode(builder, this.value);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  public void decode(BitString bitString) {
    try {
      this.value = FibonacciIntegerRangeEncoder.decode(bitString);
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }

  public BitString substring(BitString bitString, int fromIndex) throws SubstringException {
    try {
      int count = FixedIntegerEncoder.decode(bitString, fromIndex, 12);
      int index = fromIndex + 12;
      for (int i = 0; i < count; i++) {
        if (bitString.getValue(index)) {
          index = FibonacciIntegerEncoder.indexOfEndTag(bitString, FibonacciIntegerEncoder.indexOfEndTag(bitString, index + 1) + 2) + 2;
        } else {
          index = FibonacciIntegerEncoder.indexOfEndTag(bitString, index + 1) + 2;
        }
      }
      return bitString.substring(fromIndex, index);
    } catch (Exception e) {
      throw new SubstringException(e);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public void setValue(Object value) {
    this.value.clear();
    this.value.addAll((Collection<Integer>) value);
  }

  @Override
  public IntegerSet getValue() {
    return new ManagedIntegerSet(this, super.getValue());
  }
}
