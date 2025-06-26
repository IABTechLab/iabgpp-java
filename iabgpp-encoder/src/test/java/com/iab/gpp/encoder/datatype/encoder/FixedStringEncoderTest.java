package com.iab.gpp.encoder.datatype.encoder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class FixedStringEncoderTest {

  @Test
  public void testEncode1() {
    BitStringBuilder builder = new BitStringBuilder();
    FixedStringEncoder.encode(builder, "AB", 2);
    Assertions.assertEquals("000000000001", builder.build().toString());
  }

  @Test
  public void testEncode2() {
    BitStringBuilder builder = new BitStringBuilder();
    FixedStringEncoder.encode(builder, "a", 2);
    Assertions.assertEquals("100000111111", builder.build().toString());
  }

  @Test
  public void testEncode3() {
    try {
      BitStringBuilder builder = new BitStringBuilder();
      FixedStringEncoder.encode(builder, "1", 2);
      Assertions.fail("DecodingException expected");
    } catch (EncodingException e) {

    }
  }

  @Test
  public void testDecode1() {
    Assertions.assertEquals("AB", FixedStringEncoder.decode(BitString.of("000000000001")));
  }

  @Test
  public void testDecode2() {
    Assertions.assertEquals("a", FixedStringEncoder.decode(BitString.of("100000111111")));
  }

  @Test
  public void testDecode3() {
    try {
      FixedStringEncoder.decode(BitString.of("2"));
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }
}
