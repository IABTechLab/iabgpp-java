package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;

public final class UnencodableBoolean extends AbstractEncodableBitStringDataType<Boolean> {

  private final Boolean initial;

  public UnencodableBoolean(Boolean initial) {
    this.initial = initial;
  }
  
  @Override
  protected Boolean initialize() {
    return initial;
  }

  @Override
  protected void encode(BitString writer, Boolean value) {
    // pass
  }

  @Override
  protected Boolean decode(BitString reader) {
    return Boolean.FALSE;
  }

}
