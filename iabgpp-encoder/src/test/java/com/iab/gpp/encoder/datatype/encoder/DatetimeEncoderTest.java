package com.iab.gpp.encoder.datatype.encoder;

import java.time.Instant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.error.DecodingException;

public class DatetimeEncoderTest {

  @Test
  public void test1() throws DecodingException {
    Instant date1 = Instant.now();
    BitStringBuilder builder = new BitStringBuilder();
    DatetimeEncoder.encode(builder, date1);
    String encodedDate1 = builder.build().toString();
    Instant date2 = DatetimeEncoder.decode(BitString.of(encodedDate1));

    Assertions.assertEquals((date1.toEpochMilli() / 100L) * 100L, date2.toEpochMilli());
  }
}
