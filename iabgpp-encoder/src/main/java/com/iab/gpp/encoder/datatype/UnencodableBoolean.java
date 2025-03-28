package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;

public class UnencodableBoolean extends AbstractEncodableBitStringDataType<Boolean> {

  protected UnencodableBoolean() {
    super(true);
  }

  public UnencodableBoolean(Boolean value) {
    super(true);
    setValue(value);
  }

  public void encode(BitStringBuilder builder){
    // pass
  }

  public void decode(BitString bitString) {
    // pass
  }

  public BitString substring(BitString bitString, int fromIndex) throws SubstringException {
    return BitString.empty(0);
  }
}
