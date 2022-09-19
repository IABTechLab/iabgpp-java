package com.iab.gpp.encoder.datatype.encoder;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;

class FixedBitfieldEncoderTest {

  @Test
  void testEncode1() {
    Assertions.assertEquals("00", FixedBitfieldEncoder.encode(new ArrayList<>(), 2));
  }

  @Test
  void testEncode2() {
    Assertions.assertEquals("0", FixedBitfieldEncoder.encode(Arrays.asList(0), 1));
  }

  @Test
  void testEncode3() {
    Assertions.assertEquals("1", FixedBitfieldEncoder.encode(Arrays.asList(1), 1));
  }

  @Test
  void testEncode4() {
    Assertions.assertEquals("00", FixedBitfieldEncoder.encode(Arrays.asList(0, 0), 2));
  }

  @Test
  void testEncode5() {
    Assertions.assertEquals("01", FixedBitfieldEncoder.encode(Arrays.asList(0, 1), 2));
  }

  @Test
  void testEncode6() {
    Assertions.assertEquals("10", FixedBitfieldEncoder.encode(Arrays.asList(1, 0), 2));
  }

  @Test
  void testEncode7() {
    Assertions.assertEquals("11", FixedBitfieldEncoder.encode(Arrays.asList(1, 1), 2));
  }

  @Test
  void testDecode1() throws DecodingException {
    Assertions.assertEquals(new ArrayList<>(), FixedBitfieldEncoder.decode(""));
  }

  @Test
  void testDecode2() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(0), FixedBitfieldEncoder.decode("0"));
  }

  @Test
  void testDecode3() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(1), FixedBitfieldEncoder.decode("1"));
  }

  @Test
  void testDecode4() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(0, 0), FixedBitfieldEncoder.decode("00"));
  }

  @Test
  void testDecode5() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(0, 1), FixedBitfieldEncoder.decode("01"));
  }

  @Test
  void testDecode6() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(1, 0), FixedBitfieldEncoder.decode("10"));
  }

  @Test
  void testDecode7() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(1, 1), FixedBitfieldEncoder.decode("11"));
  }

  @Test
  void testDecode8() {
    try {
      FixedBitfieldEncoder.decode("2");
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }
}
