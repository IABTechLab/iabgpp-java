package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.datatype.encoder.BooleanEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class EncodableBoolean extends AbstractEncodableBitStringDataType<Boolean> {
  
  public EncodableBoolean() {
    super();
  }
  
  public EncodableBoolean(Boolean value) {
    super(value);
  }

  public String encode() throws EncodingException {
    return BooleanEncoder.encode(this.value);
  }

  public void decode(String bitString) throws DecodingException {
    this.value = BooleanEncoder.decode(bitString);
  }

  public String substring(String bitString, int fromIndex) {
    //TODO: validate
    return bitString.substring(fromIndex, fromIndex + 1);
  }
}
