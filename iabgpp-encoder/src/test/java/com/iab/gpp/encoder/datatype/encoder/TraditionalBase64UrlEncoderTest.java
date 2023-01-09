package com.iab.gpp.encoder.datatype.encoder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class TraditionalBase64UrlEncoderTest {

  private TraditionalBase64UrlEncoder base64UrlEncoder = new TraditionalBase64UrlEncoder();

  @Test
  public void testEncode1() throws EncodingException {
    Assertions.assertEquals("DBABMAAA", base64UrlEncoder.encode("0000110000010000000000010011"));
  }

  @Test
  public void testEncode2() throws EncodingException {
    Assertions.assertEquals("DBACNYAA", base64UrlEncoder.encode("000011000001000000000010001101011"));
  }

  @Test
  public void testEncode3() throws EncodingException {
    Assertions.assertEquals("DBABjwAA", base64UrlEncoder.encode("00001100000100000000000110001111"));
  }

  @Test
  public void testDecode1() throws DecodingException {
    Assertions.assertEquals("000011000001000000000001001100000000000000000000", base64UrlEncoder.decode("DBABMAAA"));
  }

  @Test
  public void testDecode2() throws DecodingException {
    Assertions.assertEquals("000011000001000000000010001101011000000000000000", base64UrlEncoder.decode("DBACNYAA"));
  }

  @Test
  public void testDecode3() throws DecodingException {
    Assertions.assertEquals("000011000001000000000001100011110000000000000000", base64UrlEncoder.decode("DBABjwAA"));
  }

}
