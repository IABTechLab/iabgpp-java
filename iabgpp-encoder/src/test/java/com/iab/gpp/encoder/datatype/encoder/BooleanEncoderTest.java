package com.iab.gpp.encoder.datatype.encoder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

class BooleanEncoderTest {

  @Test
  void testEncode1() throws EncodingException {
    Assertions.assertEquals("0", BooleanEncoder.encode(false));
  }

  @Test
  void testEncode2() throws EncodingException {
    Assertions.assertEquals("1", BooleanEncoder.encode(true));
  }
  
  @Test
  void testDecode1() throws DecodingException {
    Assertions.assertEquals(false, BooleanEncoder.decode("0"));
  }
  
  @Test
  void testDecode2() throws DecodingException {
    Assertions.assertEquals(true, BooleanEncoder.decode("1"));
  }
  
  @Test
  void testDecode3() {
    try {
      BooleanEncoder.decode("");
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {
      
    }
  }
  
  @Test
  void testDecode4() {
    try {
      BooleanEncoder.decode("2");
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {
      
    }
  }
  
  @Test
  void testDecode5() {
    try {
      BooleanEncoder.decode("00");
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {
      
    }
  }
  
  @Test
  void testDecode6() {
    try {
      BooleanEncoder.decode("01");
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {
      
    }
  }
  
  @Test
  void testDecode7() {
    try {
      BooleanEncoder.decode("10");
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {
      
    }
  }
  
}
