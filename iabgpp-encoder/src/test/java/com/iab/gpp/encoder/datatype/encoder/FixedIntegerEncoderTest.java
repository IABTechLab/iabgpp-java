package com.iab.gpp.encoder.datatype.encoder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class FixedIntegerEncoderTest {

  @Test
  public void testEncode1() {
    Assertions.assertEquals("0", FixedIntegerEncoder.encode(0, 1));
  }

  @Test
  public void testEncode2() {
    Assertions.assertEquals("000000", FixedIntegerEncoder.encode(0, 6));
  }

  @Test
  public void testEncode3() {
    Assertions.assertEquals("1", FixedIntegerEncoder.encode(1, 1));
  }

  @Test
  public void testEncode4() {
    Assertions.assertEquals("0001", FixedIntegerEncoder.encode(1, 4));
  }

  @Test
  public void testEncode5() {
    Assertions.assertEquals("00000111", FixedIntegerEncoder.encode(7, 8));
  }
  
  @Test
  public void testEncode6() {
    try {
      FixedIntegerEncoder.encode(8, 1);
      Assertions.fail("EncodingException expected");
    } catch (EncodingException e) {

    }
  }

  @Test
  public void testDecode1() throws DecodingException {
    Assertions.assertEquals(0, FixedIntegerEncoder.decode(""));
  }

  @Test
  public void testDecode2() throws DecodingException {
    Assertions.assertEquals(0, FixedIntegerEncoder.decode("0"));
  }

  @Test
  public void testDecode3() throws DecodingException {
    Assertions.assertEquals(0, FixedIntegerEncoder.decode("000000"));
  }

  @Test
  public void testDecode4() throws DecodingException {
    Assertions.assertEquals(1, FixedIntegerEncoder.decode("1"));
  }

  @Test
  public void testDecode5() throws DecodingException {
    Assertions.assertEquals(1, FixedIntegerEncoder.decode("000001"));
  }

  @Test
  public void testDecode6() throws DecodingException {
    Assertions.assertEquals(8, FixedIntegerEncoder.decode("1000"));
  }

  @Test
  public void testDecode7() throws DecodingException {
    Assertions.assertEquals(8, FixedIntegerEncoder.decode("0000001000"));
  }
}
