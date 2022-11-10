package com.iab.gpp.encoder.datatype;

import java.util.List;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerListEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class EncodableFixedIntegerList extends AbstractEncodableBitStringDataType<List<Integer>> {

  private int elementBitStringLength;
  private int numElements;

  public EncodableFixedIntegerList(int elementBitStringLength, int size) {
    super();
    this.elementBitStringLength = elementBitStringLength;
    this.numElements = size;
  }

  public EncodableFixedIntegerList(int elementBitStringLength, List<Integer> value) {
    super(value);
    this.elementBitStringLength = elementBitStringLength;
    this.numElements = value.size();
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
}
