package com.iab.gpp.encoder.base64;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TraditionalBase64UrlEncoderTest {

  private TraditionalBase64UrlEncoder base64UrlEncoder = TraditionalBase64UrlEncoder.getInstance();

  @Test
  public void testEncode1() {
    Assertions.assertEquals("DBABMAAA", base64UrlEncoder.encode("0000110000010000000000010011"));
  }

  @Test
  public void testEncode2() {
    Assertions.assertEquals("DBACNYAA", base64UrlEncoder.encode("000011000001000000000010001101011"));
  }

  @Test
  public void testEncode3() {
    Assertions.assertEquals("DBABjwAA", base64UrlEncoder.encode("00001100000100000000000110001111"));
  }

  @Test
  public void testDecode1() {
    Assertions.assertEquals("000011000001000000000001001100000000000000000000", base64UrlEncoder.decode("DBABMAAA").toString());
  }

  @Test
  public void testDecode2() {
    Assertions.assertEquals("000011000001000000000010001101011000000000000000", base64UrlEncoder.decode("DBACNYAA").toString());
  }

  @Test
  public void testDecode3() {
    Assertions.assertEquals("000011000001000000000001100011110000000000000000", base64UrlEncoder.decode("DBABjwAA").toString());
  }
  
}
