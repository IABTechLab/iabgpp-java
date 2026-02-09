package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public final class EncodableFixedInteger extends AbstractEncodableBitStringDataType<Integer> {

  private final int bitStringLength;
  private final Integer initial;
  
  public EncodableFixedInteger(int bitStringLength, Integer initial) {
    this.bitStringLength = bitStringLength;
    this.initial = initial;
  }

  @Override
  protected Integer initialize() {
    return initial;
  }

  @Override
  protected void encode(BitString builder, Integer value) {
    try {
      builder.writeInt(value, this.bitStringLength);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  @Override
  protected Integer decode(BitString reader) {
    try {
      return IntegerCache.valueOf(reader.readInt(bitStringLength));
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }
}
