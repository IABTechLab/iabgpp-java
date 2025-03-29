package com.iab.gpp.encoder.datatype;

import java.util.Collection;
import java.util.List;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerEncoder;
import com.iab.gpp.encoder.datatype.encoder.IntegerSet;
import com.iab.gpp.encoder.datatype.encoder.ManagedSet;
import com.iab.gpp.encoder.datatype.encoder.OptimizedFibonacciRangeEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class EncodableOptimizedFibonacciRange extends AbstractEncodableBitStringDataType<IntegerSet> {

  protected EncodableOptimizedFibonacciRange() {
    super(true);
  }

  public EncodableOptimizedFibonacciRange(List<Integer> value) {
    super(true);
    setValue(value);
  }

  public EncodableOptimizedFibonacciRange(List<Integer> value, boolean hardFailIfMissing) {
    super(hardFailIfMissing);
    setValue(value);
  }

  public void encode(BitStringBuilder builder) {
    try {
      OptimizedFibonacciRangeEncoder.encode(builder, this.value);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  public void decode(BitString bitString) {
    try {
      this.value = OptimizedFibonacciRangeEncoder.decode(bitString);
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }

  public BitString substring(BitString bitString, int fromIndex) throws SubstringException {
    try {
      int max = FixedIntegerEncoder.decode(bitString, fromIndex, 16);
      if (bitString.getValue(fromIndex + 16)) {
        BitStringBuilder out = new BitStringBuilder();
        out.append(bitString.substring(fromIndex, fromIndex + 17));
        out.append(new EncodableFibonacciIntegerRange().substring(bitString, fromIndex + 17));
        return out.build();
      } else {
        return bitString.substring(fromIndex, fromIndex + 17 + max);
      }
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
    return new ManagedSet(this, super.getValue());
  }
}
