package com.iab.gpp.encoder.datatype.encoder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;

class FixedIntegerEncoderTest {

  @Test
  void testEncode1() {
    Assertions.assertEquals("0", FixedIntegerEncoder.encode(0, 1));
  }
  
  @Test
  void testEncode2() {
    Assertions.assertEquals("000000", FixedIntegerEncoder.encode(0, 6));
  }

  @Test
  void testEncode3() {
    Assertions.assertEquals("1", FixedIntegerEncoder.encode(1, 1));
  }
  
  @Test
  void testEncode4() {
    Assertions.assertEquals("0001", FixedIntegerEncoder.encode(1, 4));
  }
  
  @Test
  void testEncode5() {
    Assertions.assertEquals("00000111", FixedIntegerEncoder.encode(7, 8));
  }
  
  @Test
  void testDecode1() throws DecodingException {
    Assertions.assertEquals(0, FixedIntegerEncoder.decode(""));
  }
  
  @Test
  void testDecode2() throws DecodingException {
    Assertions.assertEquals(0, FixedIntegerEncoder.decode("0"));
  }
  
  @Test
  void testDecode3() throws DecodingException {
    Assertions.assertEquals(0, FixedIntegerEncoder.decode("000000"));
  }
  
  @Test
  void testDecode4() throws DecodingException {
    Assertions.assertEquals(1, FixedIntegerEncoder.decode("1"));
  }
  
  @Test
  void testDecode5() throws DecodingException {
    Assertions.assertEquals(1, FixedIntegerEncoder.decode("000001"));
  }
  
  @Test
  void testDecode6() throws DecodingException {
    Assertions.assertEquals(8, FixedIntegerEncoder.decode("1000"));
  }
  
  @Test
  void testDecode7() throws DecodingException {
    Assertions.assertEquals(8, FixedIntegerEncoder.decode("0000001000"));
  }
}
