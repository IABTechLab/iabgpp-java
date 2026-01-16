package com.iab.gpp.encoder.base64;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;

public class TraditionalBase64UrlEncoderTest {

  private TraditionalBase64UrlEncoder base64UrlEncoder = TraditionalBase64UrlEncoder.getInstance();

 

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
