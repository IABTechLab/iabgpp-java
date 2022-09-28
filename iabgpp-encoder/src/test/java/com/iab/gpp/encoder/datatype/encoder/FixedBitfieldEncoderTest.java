package com.iab.gpp.encoder.datatype.encoder;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

class FixedBitfieldEncoderTest {

  @Test
  void testEncode1() throws EncodingException {
    Assertions.assertEquals("00", FixedBitfieldEncoder.encode(new ArrayList<>(), 2));
  }

  @Test
  void testEncode2() throws EncodingException {
    Assertions.assertEquals("0", FixedBitfieldEncoder.encode(Arrays.asList(false), 1));
  }

  @Test
  void testEncode3() throws EncodingException {
    Assertions.assertEquals("1", FixedBitfieldEncoder.encode(Arrays.asList(true), 1));
  }

  @Test
  void testEncode4() throws EncodingException {
    Assertions.assertEquals("00", FixedBitfieldEncoder.encode(Arrays.asList(false, false), 2));
  }

  @Test
  void testEncode5() throws EncodingException {
    Assertions.assertEquals("01", FixedBitfieldEncoder.encode(Arrays.asList(false, true), 2));
  }

  @Test
  void testEncode6() throws EncodingException {
    Assertions.assertEquals("10", FixedBitfieldEncoder.encode(Arrays.asList(true, false), 2));
  }

  @Test
  void testEncode7() throws EncodingException {
    Assertions.assertEquals("11", FixedBitfieldEncoder.encode(Arrays.asList(true, true), 2));
  }

  @Test
  void testDecode1() throws DecodingException {
    Assertions.assertEquals(new ArrayList<>(), FixedBitfieldEncoder.decode(""));
  }

  @Test
  void testDecode2() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(false), FixedBitfieldEncoder.decode("0"));
  }

  @Test
  void testDecode3() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(true), FixedBitfieldEncoder.decode("1"));
  }

  @Test
  void testDecode4() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(false, false), FixedBitfieldEncoder.decode("00"));
  }

  @Test
  void testDecode5() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(false, true), FixedBitfieldEncoder.decode("01"));
  }

  @Test
  void testDecode6() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(true, false), FixedBitfieldEncoder.decode("10"));
  }

  @Test
  void testDecode7() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(true, true), FixedBitfieldEncoder.decode("11"));
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
