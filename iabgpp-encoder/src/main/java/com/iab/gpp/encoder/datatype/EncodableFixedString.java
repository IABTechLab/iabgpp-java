package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.datatype.encoder.FixedStringEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

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

  public String encode() throws EncodingException {
    return FixedStringEncoder.encode(this.value, this.stringLength);
  }

  public void decode(String bitString) throws DecodingException {
    this.value = FixedStringEncoder.decode(bitString);
  }

  public String substring(String bitString, int fromIndex) {
    // TODO: validate
    return bitString.substring(fromIndex, fromIndex + this.stringLength * 6);
  }
}
