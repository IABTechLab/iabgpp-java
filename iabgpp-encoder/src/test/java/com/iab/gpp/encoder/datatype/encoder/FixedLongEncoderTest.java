package com.iab.gpp.encoder.datatype.encoder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class FixedLongEncoderTest {

  @Test
  public void testEncode1() {
    BitStringBuilder builder = new BitStringBuilder();
    FixedLongEncoder.encode(builder, 0, 1);
    Assertions.assertEquals("0", builder.build().toString());
  }

  @Test
  public void testEncode2() {
    BitStringBuilder builder = new BitStringBuilder();
    FixedLongEncoder.encode(builder, 0, 6);
    Assertions.assertEquals("000000", builder.build().toString());
  }

  @Test
  public void testEncode3() {
    BitStringBuilder builder = new BitStringBuilder();
    FixedLongEncoder.encode(builder, 1, 1);
    Assertions.assertEquals("1", builder.build().toString());
  }

  @Test
  public void testEncode4() {
    BitStringBuilder builder = new BitStringBuilder();
    FixedLongEncoder.encode(builder, 1, 4);
    Assertions.assertEquals("0001", builder.build().toString());
  }

  @Test
  public void testEncode5() {
    BitStringBuilder builder = new BitStringBuilder();
    FixedLongEncoder.encode(builder, 7, 8);
    Assertions.assertEquals("00000111", builder.build().toString());
  }

  
  @Test
  public void testEncode6() {
    BitStringBuilder builder = new BitStringBuilder();
    FixedLongEncoder.encode(builder, 16630898421L, 36);
    Assertions.assertEquals("001111011111010001110101111011110101", builder.build().toString());
  }
  
  @Test
  public void testEncode7() {
    try {
      BitStringBuilder builder = new BitStringBuilder();
      FixedIntegerEncoder.encode(builder, 8, 1);
      Assertions.fail("EncodingException expected");
    } catch (EncodingException e) {

    }
  }

  @Test
  public void testDecode1() throws DecodingException {
    Assertions.assertEquals(0, FixedLongEncoder.decode(BitString.of("")));
  }

  @Test
  public void testDecode2() throws DecodingException {
    Assertions.assertEquals(0, FixedLongEncoder.decode(BitString.of("0")));
  }

  @Test
  public void testDecode3() throws DecodingException {
    Assertions.assertEquals(0, FixedLongEncoder.decode(BitString.of("000000")));
  }

  @Test
  public void testDecode4() throws DecodingException {
    Assertions.assertEquals(1, FixedLongEncoder.decode(BitString.of("1")));
  }

  @Test
  public void testDecode5() throws DecodingException {
    Assertions.assertEquals(1, FixedLongEncoder.decode(BitString.of("000001")));
  }

  @Test
  public void testDecode6() throws DecodingException {
    Assertions.assertEquals(16630898421L, FixedLongEncoder.decode(BitString.of("001111011111010001110101111011110101")));
  }

  @Test
  public void testDecode7() throws DecodingException {
    Assertions.assertEquals(8, FixedLongEncoder.decode(BitString.of("1000")));
  }

  @Test
  public void testDecode8() throws DecodingException {
    Assertions.assertEquals(8, FixedLongEncoder.decode(BitString.of("0000001000")));
  }
}
