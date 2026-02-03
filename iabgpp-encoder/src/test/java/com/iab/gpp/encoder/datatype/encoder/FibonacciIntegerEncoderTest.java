package com.iab.gpp.encoder.datatype.encoder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class FibonacciIntegerEncoderTest {

  @Test
  public void testEncode1() {
    BitString builder = new BitString();
    builder.writeFibonacci(1);
    Assertions.assertEquals("11", builder.toString());
  }

  @Test
  public void testEncode2() {
    BitString builder = new BitString();
    builder.writeFibonacci(2);
    Assertions.assertEquals("011", builder.toString());
  }

  @Test
  public void testEncode3() {
    BitString builder = new BitString();
    builder.writeFibonacci(3);
    Assertions.assertEquals("0011", builder.toString());
  }

  @Test
  public void testEncode4() {
    BitString builder = new BitString();
    builder.writeFibonacci(4);
    Assertions.assertEquals("1011", builder.toString());
  }

  @Test
  public void testEncode5() {
    BitString builder = new BitString();
    builder.writeFibonacci(5);
    Assertions.assertEquals("00011", builder.toString());
  }

  @Test
  public void testEncode6() {
    BitString builder = new BitString();
    builder.writeFibonacci(6);
    Assertions.assertEquals("10011", builder.toString());
  }

  @Test
  public void testEncode7() {
    BitString builder = new BitString();
    builder.writeFibonacci(7);
    Assertions.assertEquals("01011", builder.toString());
  }

  @Test
  public void testEncodeTooLarge() {
    BitString builder = new BitString();
    Assertions.assertThrows(EncodingException.class, () -> 
      builder.writeFibonacci(Integer.MAX_VALUE));
  }

  private int decode(String str) {
    return BitString.of(str).readFibonacci();
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
  public void testDecodeTooLarge() {
    Assertions.assertThrows(DecodingException.class, () -> 
      decode("0001010001000101001000001001000100001000100011"));
  }

}
