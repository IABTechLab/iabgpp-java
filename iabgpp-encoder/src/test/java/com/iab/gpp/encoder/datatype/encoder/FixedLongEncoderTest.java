package com.iab.gpp.encoder.datatype.encoder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;

class FixedLongEncoderTest {

  @Test
  void testEncode1() {
    Assertions.assertEquals("0", FixedLongEncoder.encode(0, 1));
  }
  
  @Test
  void testEncode2() {
    Assertions.assertEquals("000000", FixedLongEncoder.encode(0, 6));
  }

  @Test
  void testEncode3() {
    Assertions.assertEquals("1", FixedLongEncoder.encode(1, 1));
  }
  
  @Test
  void testEncode4() {
    Assertions.assertEquals("0001", FixedLongEncoder.encode(1, 4));
  }
  
  @Test
  void testEncode5() {
    Assertions.assertEquals("00000111", FixedLongEncoder.encode(7, 8));
  }
  
  @Test
  void testEncode6() {
    Assertions.assertEquals("001111011111010001110101111011110101", FixedLongEncoder.encode(16630898421L, 36));
  }
  
  @Test
  void testDecode1() throws DecodingException {
    Assertions.assertEquals(0, FixedLongEncoder.decode(""));
  }
  
  @Test
  void testDecode2() throws DecodingException {
    Assertions.assertEquals(0, FixedLongEncoder.decode("0"));
  }
  
  @Test
  void testDecode3() throws DecodingException {
    Assertions.assertEquals(0, FixedLongEncoder.decode("000000"));
  }
  
  @Test
  void testDecode4() throws DecodingException {
    Assertions.assertEquals(1, FixedLongEncoder.decode("1"));
  }
  
  @Test
  void testDecode5() throws DecodingException {
    Assertions.assertEquals(1, FixedLongEncoder.decode("000001"));
  }
  
  @Test
  void testDecode6() throws DecodingException {
    Assertions.assertEquals(16630898421L, FixedLongEncoder.decode("001111011111010001110101111011110101"));
  }
  
  @Test
  void testDecode7() throws DecodingException {
    Assertions.assertEquals(8, FixedLongEncoder.decode("1000"));
  }
  
  @Test
  void testDecode8() throws DecodingException {
    Assertions.assertEquals(8, FixedLongEncoder.decode("0000001000"));
  }
}
