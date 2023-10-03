package com.iab.gpp.encoder.datatype;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerEncoder;
import com.iab.gpp.encoder.datatype.encoder.OptimizedFixedRangeEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class EncodableOptimizedFixedRange extends AbstractEncodableBitStringDataType<List<Integer>> {

  protected EncodableOptimizedFixedRange() {
    super();
  }

  public EncodableOptimizedFixedRange(List<Integer> value) {
    super();
    setValue(value);
  }

  public String encode() throws EncodingException {
    return OptimizedFixedRangeEncoder.encode(this.value);
  }

  public void decode(String bitString) throws DecodingException {
    this.value = OptimizedFixedRangeEncoder.decode(bitString);
  }

  public String substring(String bitString, int fromIndex) throws DecodingException {
    int max = FixedIntegerEncoder.decode(bitString.substring(fromIndex, fromIndex + 16));
    if (bitString.charAt(fromIndex + 16) == '1') {
      return bitString.substring(fromIndex, fromIndex + 17)
          + new EncodableFixedIntegerRange().substring(bitString, fromIndex + 17);
    } else {
      return bitString.substring(fromIndex, fromIndex + 17 + max);
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
