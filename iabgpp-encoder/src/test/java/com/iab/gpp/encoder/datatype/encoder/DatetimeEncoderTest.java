package com.iab.gpp.encoder.datatype.encoder;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;

class DatetimeEncoderTest {

  @Test
  void test1() throws DecodingException {
    ZonedDateTime date1 = ZonedDateTime.now(ZoneId.of("UTC"));
    String encodedDate1 = DatetimeEncoder.encode(date1);
    ZonedDateTime date2 = DatetimeEncoder.decode(encodedDate1);

    Assertions.assertEquals((date1.toInstant().toEpochMilli() / 100L) * 100L, date2.toInstant().toEpochMilli());
  }
}
