package com.iab.gpp.encoder.datatype;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerEncoder;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerRangeEncoder;
import com.iab.gpp.encoder.error.DecodingException;

public class EncodableFixedIntegerRange extends AbstractEncodableBitStringDataType<List<Integer>> {

  protected EncodableFixedIntegerRange() {
    super();
  }

  public EncodableFixedIntegerRange(List<Integer> value) {
    super();
    setValue(value);
  }

  public String encode() {
    return FixedIntegerRangeEncoder.encode(this.value);
  }

  public void decode(String bitString) throws DecodingException {
    this.value = FixedIntegerRangeEncoder.decode(bitString);
  }

  public String substring(String bitString, int fromIndex) throws DecodingException {
    // TODO: add some validation
    int count = FixedIntegerEncoder.decode(bitString.substring(fromIndex, fromIndex + 12));
    int index = fromIndex + 12;
    for (int i = 0; i < count; i++) {
      if (bitString.charAt(index) == '1') {
        index += 33;
      } else {
        index += 17;
      }
    }
    return bitString.substring(fromIndex, index);
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
