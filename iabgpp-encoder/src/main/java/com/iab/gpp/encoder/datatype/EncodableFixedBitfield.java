package com.iab.gpp.encoder.datatype;

import java.util.ArrayList;
import java.util.List;
import com.iab.gpp.encoder.datatype.encoder.FixedBitfieldEncoder;

public class EncodableFixedBitfield extends AbstractEncodableBitStringDataType<List<Boolean>> {

  private int numElements;

  protected EncodableFixedBitfield(int numElements) {
    super();
    this.numElements = numElements;
  }

  public EncodableFixedBitfield(List<Boolean> value) {
    super();
    this.numElements = value.size();
    setValue(value);
  }

  public String encode() {
    return FixedBitfieldEncoder.encode(this.value, this.numElements);
  }

  public void decode(String bitString) {
    this.value = FixedBitfieldEncoder.decode(bitString);
  }

  public String substring(String bitString, int fromIndex) {
    // TODO: validate
    return bitString.substring(fromIndex, fromIndex + this.numElements);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void setValue(Object value) {
    List<Boolean> v = new ArrayList<>((List<Boolean>) value);
    for (int i = v.size(); i < numElements; i++) {
      v.add(false);
    }
    if (v.size() > numElements) {
      v = v.subList(0, numElements);
    }
    super.setValue(v);
  }

  @Override
  public List<Boolean> getValue() {
    return new ArrayList<>(super.getValue());
  }
}
