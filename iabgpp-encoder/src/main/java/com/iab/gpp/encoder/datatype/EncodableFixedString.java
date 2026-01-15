package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.bitstring.BitStringReader;
import com.iab.gpp.encoder.datatype.encoder.FixedStringEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public final class EncodableFixedString extends AbstractEncodableBitStringDataType<String> {

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

  public void encode(BitStringBuilder builder) {
    try {
      FixedStringEncoder.encode(builder, this.value, this.stringLength);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  public void decode(BitStringReader reader) {
    try {
      this.value = FixedStringEncoder.decode(reader, this.stringLength);
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }
}
