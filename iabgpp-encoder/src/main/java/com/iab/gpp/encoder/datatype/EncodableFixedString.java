package com.iab.gpp.encoder.datatype;

import java.util.function.Predicate;
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

  protected EncodableFixedString(int stringLength, Predicate<String> validator) {
    super(validator);
    this.stringLength = stringLength;
  }

  public EncodableFixedString(int stringLength, String value, Predicate<String> validator) {
    super(validator);
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
    return bitString.substring(fromIndex, fromIndex + this.stringLength * 6);
  }
}
