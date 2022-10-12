package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.datatype.encoder.FixedIntegerEncoder;
import com.iab.gpp.encoder.error.DecodingException;

public class EncodableFixedInteger extends AbstractEncodableBitStringDataType<Integer> {

  private int bitStringLength;

  public EncodableFixedInteger(int bitStringLength) {
    super();
    this.bitStringLength = bitStringLength;
  }

  public EncodableFixedInteger(int bitStringLength, Integer value) {
    super(value);
    this.bitStringLength = bitStringLength;
  }

  public String encode() {
    return FixedIntegerEncoder.encode(this.value, this.bitStringLength);
  }

  public void decode(String bitString) throws DecodingException {
    this.value = FixedIntegerEncoder.decode(bitString);
  }

  public String substring(String bitString, int fromIndex) {
    // TODO: validate
    return bitString.substring(fromIndex, fromIndex + this.bitStringLength);
  }
}
