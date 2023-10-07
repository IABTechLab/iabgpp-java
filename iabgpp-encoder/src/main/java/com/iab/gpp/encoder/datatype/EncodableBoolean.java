package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.datatype.encoder.BooleanEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class EncodableBoolean extends AbstractEncodableBitStringDataType<Boolean> {

  protected EncodableBoolean() {
    super(true);
  }

  public EncodableBoolean(Boolean value) {
    super(true);
    setValue(value);
  }
  
  public EncodableBoolean(Boolean value, boolean hardFailIfMissing) {
    super(hardFailIfMissing);
    setValue(value);
  }

  public String encode() throws EncodingException {
    try {
      return BooleanEncoder.encode(this.value);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  public void decode(String bitString) throws DecodingException {
    try {
      this.value = BooleanEncoder.decode(bitString);
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }

  public String substring(String bitString, int fromIndex) throws SubstringException {
    // TODO: validate
    try {
      return bitString.substring(fromIndex, fromIndex + 1);
    } catch (Exception e) {
      throw new SubstringException(e);
    }
  }
}
