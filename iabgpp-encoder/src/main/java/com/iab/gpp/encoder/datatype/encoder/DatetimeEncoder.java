package com.iab.gpp.encoder.datatype.encoder;

import java.time.Instant;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
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

  public static Instant decode(BitString bitString) throws DecodingException {
    if (bitString.length() != 36) {
      throw new DecodingException("Undecodable Datetime '" + bitString + "'");
    }

    return Instant.ofEpochMilli(FixedLongEncoder.decode(bitString) * 100L);
  }
}
