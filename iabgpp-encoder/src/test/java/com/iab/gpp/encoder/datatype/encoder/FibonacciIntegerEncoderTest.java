package com.iab.gpp.encoder.datatype.encoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class FibonacciIntegerEncoderTest {

  @Test
  public void testEncode1() {
    Assertions.assertEquals("11", FibonacciIntegerEncoder.encode(1));
  }

  public void testEncode2() {
    Assertions.assertEquals("011", FibonacciIntegerEncoder.encode(2));
  }

  public void testEncode3() {
    Assertions.assertEquals("0011", FibonacciIntegerEncoder.encode(3));
  }

  public void testEncode4() {
    Assertions.assertEquals("1011", FibonacciIntegerEncoder.encode(4));
  }

  public void testEncode5() {
    Assertions.assertEquals("00011", FibonacciIntegerEncoder.encode(5));
  }

  public void testEncode6() {
    Assertions.assertEquals("10011", FibonacciIntegerEncoder.encode(6));
  }

  public void testEncode7() {
    Assertions.assertEquals("01011", FibonacciIntegerEncoder.encode(7));
  }

  @Test
  public void testEncodeTooLarge() {
    Assertions.assertThrows(EncodingException.class, () -> 
      FibonacciIntegerEncoder.encode(Integer.MAX_VALUE));
  }

  @Test
  public void testDecode1() throws DecodingException {
    Assertions.assertEquals(1, FibonacciIntegerEncoder.decode(BitString.of("11")));
  }

  @Test
  public void testDecode2() throws DecodingException {
    Assertions.assertEquals(2, FibonacciIntegerEncoder.decode(BitString.of("011")));
  }

  @Test
  public void testDecode3() throws DecodingException {
    Assertions.assertEquals(3, FibonacciIntegerEncoder.decode(BitString.of("0011")));
  }

  @Test
  public void testDecode4() throws DecodingException {
    Assertions.assertEquals(4, FibonacciIntegerEncoder.decode(BitString.of("1011")));
  }

  @Test
  public void testDecode5() throws DecodingException {
    Assertions.assertEquals(5, FibonacciIntegerEncoder.decode(BitString.of("00011")));
  }

  @Test
  public void testDecode6() throws DecodingException {
    Assertions.assertEquals(6, FibonacciIntegerEncoder.decode(BitString.of("10011")));
  }

  @Test
  public void testDecode7() throws DecodingException {
    Assertions.assertEquals(7, FibonacciIntegerEncoder.decode(BitString.of("01011")));
  }

  @Test
  public void testDecode8() {
    try {
      FibonacciIntegerEncoder.decode(BitString.of("110"));
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

  @Test
  public void testDecode9() {
    try {
      FibonacciIntegerEncoder.decode(BitString.of("1100"));
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

  @Test
  public void testDecode10() {
    try {
      FibonacciIntegerEncoder.decode(BitString.of("0110000000"));
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

  @Test
  public void testDecodeTooLarge() {
    Assertions.assertThrows(DecodingException.class, () -> 
      FibonacciIntegerEncoder.decode(BitString.of("0001010001000101001000001001000100001000100011")));
  }

}
