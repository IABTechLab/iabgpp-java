package com.iab.gpp.encoder.datatype.encoder;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.regex.Pattern;
import com.iab.gpp.encoder.error.DecodingException;

public class DatetimeEncoder {
  private static Pattern BITSTRING_VERIFICATION_PATTERN = Pattern.compile("^[0-1]*$", Pattern.CASE_INSENSITIVE);

  public static String encode(ZonedDateTime value) {
    if(value != null) {
      return FixedLongEncoder.encode(value.toInstant().toEpochMilli() / 100, 36);
    } else {
      return FixedLongEncoder.encode(0, 36);
    }
  }

  public static ZonedDateTime decode(String bitString) throws DecodingException {
    if (!BITSTRING_VERIFICATION_PATTERN.matcher(bitString).matches() || bitString.length() != 36) {
      throw new DecodingException("Undecodable Datetime '" + bitString + "'");
    }

    return ZonedDateTime.ofInstant(Instant.ofEpochMilli(FixedLongEncoder.decode(bitString) * 100L), ZoneId.of("UTC"));
  }
}
