package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.encoder.FixedStringEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public final class EncodableFixedString extends AbstractEncodableBitStringDataType<String> {

  private final int stringLength;
  private final String initial;

  public EncodableFixedString(int stringLength, String initial) {
    this.stringLength = stringLength;
    this.initial = initial;
  }

  @Override
  protected String initialize() {
    return initial;
  }
  
  @Override
  protected void encode(BitString builder, String value) {
    try {
      FixedStringEncoder.encode(builder, value, this.stringLength);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  @Override
  protected String decode(BitString reader) {
    try {
      return FixedStringEncoder.decode(reader, this.stringLength);
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }
}
