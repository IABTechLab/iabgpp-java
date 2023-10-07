package com.iab.gpp.encoder.datatype;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Predicate;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerEncoder;
import com.iab.gpp.encoder.datatype.encoder.OptimizedFibonacciRangeEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class EncodableOptimizedFibonacciRange extends AbstractEncodableBitStringDataType<List<Integer>> {

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

  public EncodableOptimizedFibonacciRange(List<Integer> value, Predicate<List<Integer>> validator) {
    super(validator);
    setValue(value);
  }

  public EncodableOptimizedFibonacciRange(List<Integer> value, boolean hardFailIfMissing,
      Predicate<List<Integer>> validator) {
    super(hardFailIfMissing, validator);
    setValue(value);
  }

  public String encode() {
    try {
      return OptimizedFibonacciRangeEncoder.encode(this.value);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  public void decode(String bitString) {
    try {
      this.value = OptimizedFibonacciRangeEncoder.decode(bitString);
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }

  public String substring(String bitString, int fromIndex) throws SubstringException {
    try {
      int max = FixedIntegerEncoder.decode(bitString.substring(fromIndex, fromIndex + 16));
      if (bitString.charAt(fromIndex + 16) == '1') {
        return (bitString.substring(fromIndex, fromIndex + 17)
            + new EncodableFibonacciIntegerRange().substring(bitString, fromIndex + 17));
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
