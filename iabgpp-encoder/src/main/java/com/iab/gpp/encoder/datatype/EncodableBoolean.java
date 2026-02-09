package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public final class EncodableBoolean extends AbstractEncodableBitStringDataType<Boolean> {

  private final Boolean initial;

  public EncodableBoolean(Boolean initial) {
    this.initial = initial;
  }

  @Override
  protected Boolean initialize() {
    return initial;
  }

  @Override
  protected void encode(BitString builder, Boolean value){
    try {
      builder.writeBoolean(value);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  @Override
  protected Boolean decode(BitString reader) {
    try {
      return reader.readBoolean();
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }
}
