package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.segment.EncodableSegment;

public final class EncodableBoolean<E extends Enum<E> & FieldKey> extends AbstractEncodableBitStringDataType<E, Boolean> {

  private final Boolean initial;

  public EncodableBoolean(String name, Boolean initial) {
    super(name, null);
    this.initial = initial;
  }

  @Override
  protected Boolean initialize() {
    return initial;
  }

  @Override
  protected void encode(BitString builder, Boolean value, EncodableSegment<E> segment){
    builder.writeBoolean(value);
  }

  @Override
  protected Boolean decode(BitString reader, EncodableSegment<E> segment) {
    return reader.readBoolean();
  }
}
