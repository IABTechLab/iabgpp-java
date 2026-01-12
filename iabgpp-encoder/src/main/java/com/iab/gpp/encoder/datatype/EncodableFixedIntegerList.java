package com.iab.gpp.encoder.datatype;

import java.util.Arrays;
import java.util.List;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerListEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public final class EncodableFixedIntegerList extends AbstractDirtyableBitStringDataType<FixedList<Integer>> {

  private int elementBitStringLength;
  private int numElements;

  protected EncodableFixedIntegerList(int elementBitStringLength, int numElements) {
    super(true);
    this.elementBitStringLength = elementBitStringLength;
    this.numElements = numElements;
  }

  public EncodableFixedIntegerList(int elementBitStringLength, List<Integer> value) {
    super(true);
    this.elementBitStringLength = elementBitStringLength;
    this.numElements = value.size();
    setValue(value);
  }

  public void encode(BitStringBuilder builder) {
    try {
      FixedIntegerListEncoder.encode(builder, this.value, this.elementBitStringLength, this.numElements);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  public void decode(BitString bitString) {
    try {
      this.value = new FixedList<>(FixedIntegerListEncoder.decode(bitString, this.elementBitStringLength, this.numElements));
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }

  public BitString substring(BitString bitString, int fromIndex) throws SubstringException {
    try {
      return bitString.substring(fromIndex, fromIndex + (this.elementBitStringLength * numElements));
    } catch (Exception e) {
      throw new SubstringException(e);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public void setValue(Object value) {
    List<Integer> list = (List<Integer>) value;
    int size = list.size();
    if (size != numElements) {
      Integer[] newList = new Integer[numElements];
      for (int i = 0; i < numElements; i++) {
          newList[i] = i < size ? list.get(i) : 0;
      }
      list = Arrays.asList(newList);
    }
    super.setValue(new FixedList<>(list));
  }
}
