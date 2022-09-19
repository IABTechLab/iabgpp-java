package com.iab.gpp.encoder.datatype.encoder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;

class FibonacciIntegerEncoderTest {

  @Test
  void testEncode1() {
    Assertions.assertEquals("11", FibonacciIntegerEncoder.encode(1));
  }

  void testEncode2() {
    Assertions.assertEquals("011", FibonacciIntegerEncoder.encode(2));
  }

  void testEncode3() {
    Assertions.assertEquals("0011", FibonacciIntegerEncoder.encode(3));
  }

  void testEncode4() {
    Assertions.assertEquals("1011", FibonacciIntegerEncoder.encode(4));
  }

  void testEncode5() {
    Assertions.assertEquals("00011", FibonacciIntegerEncoder.encode(5));
  }

  void testEncode6() {
    Assertions.assertEquals("10011", FibonacciIntegerEncoder.encode(6));
  }

  void testEncode7() {
    Assertions.assertEquals("01011", FibonacciIntegerEncoder.encode(7));
  }

  @Test
  void testDecode1() throws DecodingException {
    Assertions.assertEquals(1, FibonacciIntegerEncoder.decode("11"));
  }

  @Test
  void testDecode2() throws DecodingException {
    Assertions.assertEquals(2, FibonacciIntegerEncoder.decode("011"));
  }

  @Test
  void testDecode3() throws DecodingException {
    Assertions.assertEquals(3, FibonacciIntegerEncoder.decode("0011"));
  }

  @Test
  void testDecode4() throws DecodingException {
    Assertions.assertEquals(4, FibonacciIntegerEncoder.decode("1011"));
  }

  @Test
  void testDecode5() throws DecodingException {
    Assertions.assertEquals(5, FibonacciIntegerEncoder.decode("00011"));
  }

  @Test
  void testDecode6() throws DecodingException {
    Assertions.assertEquals(6, FibonacciIntegerEncoder.decode("10011"));
  }

  @Test
  void testDecode7() throws DecodingException {
    Assertions.assertEquals(7, FibonacciIntegerEncoder.decode("01011"));
  }

  @Test
  void testDecode8() {
    try {
      FibonacciIntegerEncoder.decode("110");
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

  @Test
  void testDecode9() {
    try {
      FibonacciIntegerEncoder.decode("1100");
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

  @Test
  void testDecode10() {
    try {
      FibonacciIntegerEncoder.decode("0110000000");
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

}
