package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.datatype.encoder.BooleanEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class EncodableBoolean extends AbstractEncodableBitStringDataType<Boolean> {

  public EncodableBoolean(Boolean value) {
    super(true);
    setValue(value);
  }

  public void encode(BitStringBuilder builder){
    try {
      BooleanEncoder.encode(builder, this.value);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  public void decode(BitString bitString) {
    try {
      this.value = BooleanEncoder.decode(bitString);
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }

  public BitString substring(BitString bitString, int fromIndex) throws SubstringException {
    try {
      return bitString.substring(fromIndex, fromIndex + 1);
    } catch (Exception e) {
      throw new SubstringException(e);
    }
  }
}
