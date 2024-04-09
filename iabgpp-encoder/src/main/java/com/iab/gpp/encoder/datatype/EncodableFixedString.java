package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.datatype.encoder.FixedStringEncoder;

public class EncodableFixedString extends AbstractEncodableBitStringDataType<String> {

  private int stringLength;

  protected EncodableFixedString(int stringLength) {
    super();
    this.stringLength = stringLength;
  }

  public EncodableFixedString(int stringLength, String value) {
    super();
    this.stringLength = stringLength;
    setValue(value);
  }

  public String encode() {
    return FixedStringEncoder.encode(this.value, this.stringLength);
  }

  public void decode(String bitString) {
    this.value = FixedStringEncoder.decode(bitString);
  }

  public String substring(String bitString, int fromIndex) {
    // TODO: validate
    return bitString.substring(fromIndex, fromIndex + this.stringLength * 6);
  }
}
