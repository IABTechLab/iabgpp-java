package com.iab.gpp.encoder.datatype.encoder;

import java.time.Instant;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.bitstring.BitStringReader;
import com.iab.gpp.encoder.error.DecodingException;

public class DatetimeEncoder {

  private DatetimeEncoder() {}

  public static void encode(BitStringBuilder builder, Instant value) {
    if (value != null) {
      FixedLongEncoder.encode(builder, value.toEpochMilli() / 100, 36);
    } else {
      FixedLongEncoder.encode(builder, 0, 36);
    }
  }

  public static Instant decode(BitStringReader reader) throws DecodingException {
    return Instant.ofEpochMilli(reader.readLong(36) * 100L);
  }
}
