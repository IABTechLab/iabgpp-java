package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.bitstring.BitStringReader;

public final class UnencodableBoolean extends AbstractEncodableBitStringDataType<Boolean> {

  protected UnencodableBoolean() {
    super(true);
  }

  public UnencodableBoolean(Boolean value) {
    super(true);
    setValue(value, false);
  }

  public void encode(BitStringBuilder builder){
    // pass
  }

  public void decode(BitStringReader reader) {
    // pass
  }

}
