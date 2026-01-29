package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.bitstring.BitStringReader;
import com.iab.gpp.encoder.datatype.encoder.BooleanEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public final class EncodableBoolean extends AbstractEncodableBitStringDataType<Boolean> {

  public EncodableBoolean(Boolean value) {
    super(true);
    setValue(value, false);
  }

  public void encode(BitStringBuilder builder){
    try {
      BooleanEncoder.encode(builder, this.value);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  public void decode(BitStringReader reader) {
    try {
      this.value = reader.readBool();
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }
}
