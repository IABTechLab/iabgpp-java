package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.segment.EncodableSegment;

public final class EncodableFibonacciInteger<E extends Enum<E> & FieldKey>
    extends AbstractEncodableBitStringDataType<E, Integer> {

  private final Integer initial;

  public EncodableFibonacciInteger(String name, Integer initial) {
    super(name, null);
    this.initial = initial;
  }

  @Override
  protected Integer initialize() {
    return initial;
  }

  @Override
  protected void encode(BitString builder, Integer value, EncodableSegment<E> segment) {
    builder.writeFibonacci(value);
  }

  @Override
  protected Integer decode(BitString reader, EncodableSegment<E> segment) {
    return reader.readFibonacci();
  }
}
