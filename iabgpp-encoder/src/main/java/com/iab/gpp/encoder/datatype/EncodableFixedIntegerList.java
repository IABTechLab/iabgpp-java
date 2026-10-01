package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerListEncoder;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.segment.EncodableSegment;
import java.util.List;
import java.util.function.Predicate;

public final class EncodableFixedIntegerList<E extends Enum<E> & FieldKey>
    extends AbstractDirtyableBitStringDataType<E, FixedIntegerList> {

  private final int elementBitStringLength;
  private final int numElements;

  public EncodableFixedIntegerList(
      String name,
      int elementBitStringLength,
      int numElements,
      Predicate<FixedIntegerList> validator) {
    super(name, validator);
    this.elementBitStringLength = elementBitStringLength;
    this.numElements = numElements;
  }

  @Override
  public String toString() {
    return name + "=Int(" + elementBitStringLength + "," + numElements + ")";
  }

  @Override
  protected FixedIntegerList initialize() {
    return new FixedIntegerList(elementBitStringLength, numElements);
  }

  @Override
  protected boolean isPresent(FixedIntegerList value) {
    return value.isPresent();
  }

  @Override
  protected void encode(BitString builder, FixedIntegerList value, EncodableSegment<E> segment) {
    FixedIntegerListEncoder.encode(builder, value, this.elementBitStringLength, this.numElements);
  }

  @Override
  protected FixedIntegerList decode(BitString reader, EncodableSegment<E> segment) {
    return reader.readFixedIntegerList(elementBitStringLength, numElements);
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
