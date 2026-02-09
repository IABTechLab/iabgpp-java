package com.iab.gpp.encoder.datatype;

import java.time.Instant;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.encoder.DatetimeEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public final class EncodableDatetime extends AbstractEncodableBitStringDataType<Instant> {

  @Override
  protected Instant initialize() {
    return Instant.EPOCH;
  }

  @Override
  protected void encode(BitString builder, Instant value) {
    try {
      DatetimeEncoder.encode(builder, value);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  @Override
  protected Instant decode(BitString reader) {
    try {
      return DatetimeEncoder.decode(reader);
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }
}
