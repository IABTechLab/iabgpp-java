package com.iab.gpp.encoder.datatype.encoder;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;
import java.time.Instant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DatetimeEncoderTest {

  @Test
  public void test1() throws DecodingException {
    Instant date1 = Instant.now();
    BitString builder = new BitString();
    DatetimeEncoder.encode(builder, date1);
    String encodedDate1 = builder.toString();
    Instant date2 = DatetimeEncoder.decode(BitString.of(encodedDate1));

    Assertions.assertEquals((date1.toEpochMilli() / 100L) * 100L, date2.toEpochMilli());
  }
}
