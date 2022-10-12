package com.iab.gpp.encoder.datatype.encoder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

class Base64UrlEncoderTest {

  @Test
  void testEncode1() throws EncodingException {
    Assertions.assertEquals("DBABMA", Base64UrlEncoder.encode("0000110000010000000000010011"));
  }

  @Test
  void testEncode2() throws EncodingException {
    Assertions.assertEquals("DBACNYA", Base64UrlEncoder.encode("000011000001000000000010001101011"));
  }

  @Test
  void testEncode3() throws EncodingException {
    Assertions.assertEquals("DBABjw", Base64UrlEncoder.encode("00001100000100000000000110001111"));
  }

  @Test
  void testDecode1() throws DecodingException {
    Assertions.assertEquals("000011000001000000000001001100000000", Base64UrlEncoder.decode("DBABMA"));
  }

  @Test
  void testDecode2() throws DecodingException {
    Assertions.assertEquals("000011000001000000000010001101011000000000", Base64UrlEncoder.decode("DBACNYA"));
  }

  @Test
  void testDecode3() throws DecodingException {
    Assertions.assertEquals("000011000001000000000001100011110000", Base64UrlEncoder.decode("DBABjw"));
  }

}
