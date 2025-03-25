package com.iab.gpp.encoder.datatype;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerEncoder;
import com.iab.gpp.encoder.datatype.encoder.OptimizedFixedRangeEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;


public class EncodableOptimizedFixedRange extends AbstractEncodableBitStringDataType<List<Integer>> {

  protected EncodableOptimizedFixedRange() {
    super(true);
  }

  public EncodableOptimizedFixedRange(List<Integer> value) {
    super(true);
    setValue(value);
  }

  public EncodableOptimizedFixedRange(List<Integer> value, boolean hardFailIfMissing) {
    super(hardFailIfMissing);
    setValue(value);
  }

  public void encode(BitStringBuilder builder) {
    try {
      OptimizedFixedRangeEncoder.encode(builder, this.value);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  public void decode(BitString bitString) {
    try {
      this.value = OptimizedFixedRangeEncoder.decode(bitString);
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
        out.append(new EncodableFixedIntegerRange().substring(bitString, fromIndex + 17));
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
    super.setValue(new ArrayList<>(new TreeSet<>((List<Integer>) value)));
  }

  @Override
  public List<Integer> getValue() {
    return new ArrayList<>(super.getValue());
  }
}
