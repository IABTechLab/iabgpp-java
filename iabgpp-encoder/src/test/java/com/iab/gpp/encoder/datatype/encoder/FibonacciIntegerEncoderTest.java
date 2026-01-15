package com.iab.gpp.encoder.datatype.encoder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.bitstring.BitStringReader;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class FibonacciIntegerEncoderTest {

  @Test
  public void testEncode1() {
    BitStringBuilder builder = new BitStringBuilder();
    FibonacciIntegerEncoder.encode(builder, 1);
    Assertions.assertEquals("11", builder.build().toString());
  }

  @Test
  public void testEncode2() {
    BitStringBuilder builder = new BitStringBuilder();
    FibonacciIntegerEncoder.encode(builder, 2);
    Assertions.assertEquals("011", builder.build().toString());
  }

  @Test
  public void testEncode3() {
    BitStringBuilder builder = new BitStringBuilder();
    FibonacciIntegerEncoder.encode(builder, 3);
    Assertions.assertEquals("0011", builder.build().toString());
  }

  @Test
  public void testEncode4() {
    BitStringBuilder builder = new BitStringBuilder();
    FibonacciIntegerEncoder.encode(builder, 4);
    Assertions.assertEquals("1011", builder.build().toString());
  }

  @Test
  public void testEncode5() {
    BitStringBuilder builder = new BitStringBuilder();
    FibonacciIntegerEncoder.encode(builder, 5);
    Assertions.assertEquals("00011", builder.build().toString());
  }

  @Test
  public void testEncode6() {
    BitStringBuilder builder = new BitStringBuilder();
    FibonacciIntegerEncoder.encode(builder, 6);
    Assertions.assertEquals("10011", builder.build().toString());
  }

  @Test
  public void testEncode7() {
    BitStringBuilder builder = new BitStringBuilder();
    FibonacciIntegerEncoder.encode(builder, 7);
    Assertions.assertEquals("01011", builder.build().toString());
  }

  @Test
  public void testEncodeTooLarge() {
    BitStringBuilder builder = new BitStringBuilder();
    Assertions.assertThrows(EncodingException.class, () -> 
      FibonacciIntegerEncoder.encode(builder, Integer.MAX_VALUE));
  }

  private int decode(String str) {
    return new BitStringReader(BitString.of(str)).readFibonacci();
  }

  @Test
  public void testDecode1() throws DecodingException {
    Assertions.assertEquals(1, decode("11"));
  }

  @Test
  public void testDecode2() throws DecodingException {
    Assertions.assertEquals(2, decode("011"));
  }

  @Test
  public void testDecode3() throws DecodingException {
    Assertions.assertEquals(3, decode("0011"));
  }

  @Test
  public void testDecode4() throws DecodingException {
    Assertions.assertEquals(4, decode("1011"));
  }

  @Test
  public void testDecode5() throws DecodingException {
    Assertions.assertEquals(5, decode("00011"));
  }

  @Test
  public void testDecode6() throws DecodingException {
    Assertions.assertEquals(6, decode("10011"));
  }

  @Test
  public void testDecode7() throws DecodingException {
    Assertions.assertEquals(7, decode("01011"));
  }

  @Test
  public void testDecode8() {
    try {
      decode("110");
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

  @Test
  public void testDecode9() {
    try {
      decode("1100");
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

  @Test
  public void testDecode10() {
    try {
      decode("0110000000");
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

  @Test
  public void testDecodeTooLarge() {
    Assertions.assertThrows(DecodingException.class, () -> 
      decode("0001010001000101001000001001000100001000100011"));
  }

}
