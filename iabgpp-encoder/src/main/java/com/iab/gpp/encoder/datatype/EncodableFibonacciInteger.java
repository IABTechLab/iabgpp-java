package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.segment.EncodableSegment;

public final class EncodableFibonacciInteger<E extends Enum<E> & FieldKey> extends AbstractEncodableBitStringDataType<E, Integer> {

  private final Integer initial;

  public EncodableFibonacciInteger(Integer initial) {
    this.initial = initial;
  }

  @Override
  protected Integer initialize() {
    return initial;
  }

  @Override
  protected void encode(BitString builder, Integer value, EncodableSegment<E> segment) {
    try {
      builder.writeFibonacci(value);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  @Override
  protected Integer decode(BitString reader, EncodableSegment<E> segment) {
    try {
      return reader.readFibonacci();
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }
}
