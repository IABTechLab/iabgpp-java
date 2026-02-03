package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public final class EncodableFixedInteger extends AbstractEncodableBitStringDataType<Integer> {

  private int bitStringLength;

  protected EncodableFixedInteger(int bitStringLength) {
    super(true);
    this.bitStringLength = bitStringLength;
  }

  public EncodableFixedInteger(int bitStringLength, Integer value) {
    super(true);
    this.bitStringLength = bitStringLength;
    setValue(value, false);
  }

  public void encode(BitString builder) {
    try {
      builder.writeInt(this.value, this.bitStringLength);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  public void decode(BitString reader) {
    try {
      this.value = IntegerCache.valueOf(reader.readInt(bitStringLength));
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }
}
