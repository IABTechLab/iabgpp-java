package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.segment.EncodableSegment;

public final class UnencodableBoolean<E extends Enum<E> & FieldKey>
    extends AbstractEncodableBitStringDataType<E, Boolean> {

  private final Boolean initial;

  public UnencodableBoolean(String name, Boolean initial) {
    super(name, null);
    this.initial = initial;
  }

  @Override
  protected Boolean initialize() {
    return initial;
  }

  @Override
  protected void encode(BitString writer, Boolean value, EncodableSegment<E> segment) {
    // pass
  }

  @Override
  protected Boolean decode(BitString reader, EncodableSegment<E> segment) {
    return Boolean.FALSE;
  }
}
