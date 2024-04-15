package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.datatype.encoder.FixedStringEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class EncodableFixedString extends AbstractEncodableBitStringDataType<String> {

  private int stringLength;

  protected EncodableFixedString(int stringLength) {
    super(true);
    this.stringLength = stringLength;
  }

  public EncodableFixedString(int stringLength, String value) {
    super(true);
    this.stringLength = stringLength;
    setValue(value);
  }

  public EncodableFixedString(int stringLength, String value, boolean hardFailIfMissing) {
    super(hardFailIfMissing);
    this.stringLength = stringLength;
    setValue(value);
  }

  public String encode() {
    try {
      return FixedStringEncoder.encode(this.value, this.stringLength);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  public void decode(String bitString) {
    try {
      this.value = FixedStringEncoder.decode(bitString);
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }

  public String substring(String bitString, int fromIndex) throws SubstringException {
    try {
      return bitString.substring(fromIndex, fromIndex + this.stringLength * 6);
    } catch (Exception e) {
      throw new SubstringException(e);
    }
  }
}
