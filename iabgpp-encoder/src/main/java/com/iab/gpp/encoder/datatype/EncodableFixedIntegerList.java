package com.iab.gpp.encoder.datatype;

import java.util.List;

import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.bitstring.BitStringReader;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerListEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public final class EncodableFixedIntegerList extends AbstractDirtyableBitStringDataType<FixedIntegerList> {

  private int elementBitStringLength;
  private int numElements;

  public EncodableFixedIntegerList(int elementBitStringLength, int numElements) {
    super(true);
    this.elementBitStringLength = elementBitStringLength;
    this.numElements = numElements;
  }

  @Override
  protected FixedIntegerList getDefaultValue() {
    return new FixedIntegerList(elementBitStringLength, numElements);
  }

  public void encode(BitStringBuilder builder) {
    try {
      FixedIntegerListEncoder.encode(builder, this.getValue(), this.elementBitStringLength, this.numElements);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  public void decode(BitStringReader reader) {
    try {
      this.value = reader.readFixedIntegerList(elementBitStringLength, numElements);
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public void setValue(Object newValue) {
    FixedIntegerList value = this.getValue();
    List<Integer> list = (List<Integer>) newValue;
    int size = list.size();
    for (int i = 0; i < numElements; i++) {
      value.set(i, i < size ? list.get(i) : 0);
    }
    // call validator
    super.setValue(value);
  }
}
