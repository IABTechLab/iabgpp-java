package com.iab.gpp.encoder.datatype.encoder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

class FixedStringEncoderTest {

  @Test
  void testEncode1() throws EncodingException {
    Assertions.assertEquals("000000000001", FixedStringEncoder.encode("AB", 2));
  }

  @Test
  void testEncode2() throws EncodingException {
    Assertions.assertEquals("100000111111", FixedStringEncoder.encode("a", 2));
  }

  @Test
  void testEncode3() {
    try {
      FixedStringEncoder.encode("1", 2);
      Assertions.fail("DecodingException expected");
    } catch (EncodingException e) {

    }
  }

  @Test
  void testDecode1() throws DecodingException {
    Assertions.assertEquals("AB", FixedStringEncoder.decode("000000000001"));
  }

  @Test
  void testDecode2() throws DecodingException {
    Assertions.assertEquals("a", FixedStringEncoder.decode("100000111111"));
  }

  @Test
  void testDecode3() {
    try {
      FixedStringEncoder.decode("2");
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }
}
