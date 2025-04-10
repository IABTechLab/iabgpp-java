package com.iab.gpp.encoder.datatype.encoder;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.error.DecodingException;

public class DatetimeEncoder {

  private DatetimeEncoder() {}
  public static final ZoneId UTC = ZoneId.of("UTC");

  public static void encode(BitStringBuilder builder, ZonedDateTime value) {
    if (value != null) {
      FixedLongEncoder.encode(builder, value.toInstant().toEpochMilli() / 100, 36);
    } else {
      FixedLongEncoder.encode(builder, 0, 36);
    }
  }

  public static ZonedDateTime decode(BitString bitString) throws DecodingException {
    if (bitString.length() != 36) {
      throw new DecodingException("Undecodable Datetime '" + bitString + "'");
    }

    return ZonedDateTime.ofInstant(Instant.ofEpochMilli(FixedLongEncoder.decode(bitString) * 100L), UTC);
  }
}
