package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;

public final class UnencodableBoolean extends AbstractEncodableBitStringDataType<Boolean> {

  protected UnencodableBoolean() {
    super(true);
  }

  public UnencodableBoolean(Boolean value) {
    super(true);
    setValue(value, false);
  }

  public void encode(BitString builder){
    // pass
  }

  public void decode(BitString reader) {
    // pass
  }

}
