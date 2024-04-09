package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.datatype.encoder.FixedIntegerEncoder;

public class EncodableFixedInteger extends AbstractEncodableBitStringDataType<Integer> {

  private int bitStringLength;

  protected EncodableFixedInteger(int bitStringLength) {
    super();
    this.bitStringLength = bitStringLength;
  }

  public EncodableFixedInteger(int bitStringLength, Integer value) {
    super();
    this.bitStringLength = bitStringLength;
    setValue(value);
  }

  public String encode() {
    return FixedIntegerEncoder.encode(this.value, this.bitStringLength);
  }

  public void decode(String bitString) {
    this.value = FixedIntegerEncoder.decode(bitString);
  }

  public String substring(String bitString, int fromIndex) {
    // TODO: validate
    return bitString.substring(fromIndex, fromIndex + this.bitStringLength);
  }
}
