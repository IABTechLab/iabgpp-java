package com.iab.gpp.encoder.datatype.encoder;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;
import java.time.Instant;

public class DatetimeEncoder {

  private DatetimeEncoder() {}

  public static void encode(BitString builder, Instant value) {
    if (value != null) {
      builder.writeLong(value.toEpochMilli() / 100, 36);
    } else {
      builder.writeLong(0, 36);
    }
  }

  public static Instant decode(BitString reader) throws DecodingException {
    return Instant.ofEpochMilli(reader.readLong(36) * 100L);
  }
}
