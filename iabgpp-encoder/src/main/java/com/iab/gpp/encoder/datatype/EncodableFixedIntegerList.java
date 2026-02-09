package com.iab.gpp.encoder.datatype;

import java.util.List;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerListEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public final class EncodableFixedIntegerList extends AbstractDirtyableBitStringDataType<FixedIntegerList> {

  private final int elementBitStringLength;
  private final int numElements;

  public EncodableFixedIntegerList(int elementBitStringLength, int numElements) {
    this.elementBitStringLength = elementBitStringLength;
    this.numElements = numElements;
  }

  @Override
  protected FixedIntegerList initialize() {
    return new FixedIntegerList(elementBitStringLength, numElements);
  }

  @Override
  protected void encode(BitString builder, FixedIntegerList value) {
    try {
      FixedIntegerListEncoder.encode(builder, value, this.elementBitStringLength, this.numElements);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  @Override
  protected FixedIntegerList decode(BitString reader) {
    try {
      return reader.readFixedIntegerList(elementBitStringLength, numElements);
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  protected FixedIntegerList processValue(FixedIntegerList oldValue, Object newValue) {
    List<Integer> list = (List<Integer>) newValue;
    int size = list.size();
    for (int i = 0; i < numElements; i++) {
      oldValue.set(i, i < size ? list.get(i) : 0);
    }
    return oldValue;
  }
}
