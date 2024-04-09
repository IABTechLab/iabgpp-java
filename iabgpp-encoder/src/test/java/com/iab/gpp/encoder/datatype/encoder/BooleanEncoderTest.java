package com.iab.gpp.encoder.datatype.encoder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;

public class BooleanEncoderTest {

  @Test
  public void testEncode1() {
    Assertions.assertEquals("0", BooleanEncoder.encode(false));
  }

  @Test
  public void testEncode2() {
    Assertions.assertEquals("1", BooleanEncoder.encode(true));
  }

  @Test
  public void testDecode1() {
    Assertions.assertEquals(false, BooleanEncoder.decode("0"));
  }

  @Test
  public void testDecode2() {
    Assertions.assertEquals(true, BooleanEncoder.decode("1"));
  }

  @Test
  public void testDecode3() {
    try {
      BooleanEncoder.decode("");
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

  @Test
  public void testDecode4() {
    try {
      BooleanEncoder.decode("2");
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

  @Test
  public void testDecode5() {
    try {
      BooleanEncoder.decode("00");
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

  @Test
  public void testDecode6() {
    try {
      BooleanEncoder.decode("01");
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

  @Test
  public void testDecode7() {
    try {
      BooleanEncoder.decode("10");
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

}
