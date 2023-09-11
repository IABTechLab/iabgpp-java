package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.datatype.encoder.BooleanEncoder;

public class EncodableBoolean extends AbstractEncodableBitStringDataType<Boolean> {

  protected EncodableBoolean() {
    super();
  }

  public EncodableBoolean(Boolean value) {
    super();
    setValue(value);
  }

  public String encode() {
    return BooleanEncoder.encode(this.value);
  }

  public void decode(String bitString) {
    this.value = BooleanEncoder.decode(bitString);
  }

  public String substring(String bitString, int fromIndex) {
    return bitString.substring(fromIndex, fromIndex + 1);
  }
}
