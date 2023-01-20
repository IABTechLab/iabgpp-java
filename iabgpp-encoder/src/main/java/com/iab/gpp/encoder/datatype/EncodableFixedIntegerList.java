package com.iab.gpp.encoder.datatype;

import java.util.ArrayList;
import java.util.List;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerListEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class EncodableFixedIntegerList extends AbstractEncodableBitStringDataType<List<Integer>> {

  private int elementBitStringLength;
  private int numElements;

  protected EncodableFixedIntegerList(int elementBitStringLength, int numElements) {
    super();
    this.elementBitStringLength = elementBitStringLength;
    this.numElements = numElements;
  }

  public EncodableFixedIntegerList(int elementBitStringLength, List<Integer> value) {
    super();
    this.elementBitStringLength = elementBitStringLength;
    this.numElements = value.size();
    setValue(value);
  }

  public String encode() throws EncodingException {
    return FixedIntegerListEncoder.encode(this.value, this.elementBitStringLength, this.numElements);
  }

  public void decode(String bitString) throws DecodingException {
    this.value = FixedIntegerListEncoder.decode(bitString, this.elementBitStringLength, this.numElements);
  }

  public String substring(String bitString, int fromIndex) {
    // TODO: validate
    return bitString.substring(fromIndex, fromIndex + (this.elementBitStringLength * numElements));
  }

  @SuppressWarnings("unchecked")
  @Override
  public void setValue(Object value) {
    List<Integer> v = new ArrayList<>((List<Integer>) value);
    for (int i = v.size(); i < numElements; i++) {
      v.add(0);
    }
    if (v.size() > numElements) {
      v = v.subList(0, numElements);
    }
    super.setValue(v);
  }

  @Override
  public List<Integer> getValue() {
    return new ArrayList<>(super.getValue());
  }
}
