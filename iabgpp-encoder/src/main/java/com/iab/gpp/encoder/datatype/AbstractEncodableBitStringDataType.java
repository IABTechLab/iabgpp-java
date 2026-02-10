package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.segment.EncodableSegment;

public abstract class AbstractEncodableBitStringDataType<E extends Enum<E> & FieldKey, T> extends DataType<E, T> {

  @Override
  public final void encode(BitString writer, Object[] values, int index, EncodableSegment<E> segment) {
    encode(writer, get(values, index), segment);
  }

  protected abstract void encode(BitString writer, T value, EncodableSegment<E> segment);

  @Override
  public final void decode(BitString reader, Object[] values, int index, EncodableSegment<E> segment) {
    values[index] = decode(reader, segment);
  }

  protected abstract T decode(BitString reader, EncodableSegment<E> segment);
}
