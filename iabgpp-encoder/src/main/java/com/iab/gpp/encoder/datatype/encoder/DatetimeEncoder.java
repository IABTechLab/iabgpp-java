package com.iab.gpp.encoder.datatype.encoder;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;

public class DatetimeEncoder {

  public static final ZoneId UTC = ZoneId.of("UTC");

  public static String encode(ZonedDateTime value) {
    if (value != null) {
      return FixedLongEncoder.encode(value.toInstant().toEpochMilli() / 100, 36);
    } else {
      return FixedLongEncoder.encode(0, 36);
    }
  }

  public static ZonedDateTime decode(BitString bitString) throws DecodingException {
    if (bitString.length() != 36) {
      throw new DecodingException("Undecodable Datetime '" + bitString + "'");
    }

    return ZonedDateTime.ofInstant(Instant.ofEpochMilli(FixedLongEncoder.decode(bitString) * 100L), UTC);
  }
}
