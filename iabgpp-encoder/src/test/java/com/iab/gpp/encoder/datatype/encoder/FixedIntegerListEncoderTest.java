package com.iab.gpp.encoder.datatype.encoder;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

class FixedIntegerListEncoderTest {

  @Test
  void testEncode1() throws EncodingException {
    Assertions.assertEquals("0000", FixedIntegerListEncoder.encode(new ArrayList<>(), 2, 2));
  }

  @Test
  void testEncode2() throws EncodingException {
    Assertions.assertEquals("0000", FixedIntegerListEncoder.encode(Arrays.asList(0), 2, 2));
  }

  @Test
  void testEncode3() throws EncodingException {
    Assertions.assertEquals("0000", FixedIntegerListEncoder.encode(Arrays.asList(0, 0), 2, 2));
  }

  @Test
  void testEncode4() throws EncodingException {
    Assertions.assertEquals("0001", FixedIntegerListEncoder.encode(Arrays.asList(0, 1), 2, 2));
  }

  @Test
  void testEncode5() throws EncodingException {
    Assertions.assertEquals("0010", FixedIntegerListEncoder.encode(Arrays.asList(0, 2), 2, 2));
  }

  @Test
  void testEncode6() throws EncodingException {
    Assertions.assertEquals("0011", FixedIntegerListEncoder.encode(Arrays.asList(0, 3), 2, 2));
  }

  @Test
  void testEncode7() throws EncodingException {
    Assertions.assertEquals("0100", FixedIntegerListEncoder.encode(Arrays.asList(1, 0), 2, 2));
  }

  @Test
  void testEncode8() throws EncodingException {
    Assertions.assertEquals("0101", FixedIntegerListEncoder.encode(Arrays.asList(1, 1), 2, 2));
  }

  @Test
  void testEncode9() throws EncodingException {
    Assertions.assertEquals("0110", FixedIntegerListEncoder.encode(Arrays.asList(1, 2), 2, 2));
  }

  @Test
  void testEncode10() throws EncodingException {
    Assertions.assertEquals("0111", FixedIntegerListEncoder.encode(Arrays.asList(1, 3), 2, 2));
  }

  @Test
  void testEncode11() throws EncodingException {
    Assertions.assertEquals("1000", FixedIntegerListEncoder.encode(Arrays.asList(2, 0), 2, 2));
  }

  @Test
  void testEncode12() throws EncodingException {
    Assertions.assertEquals("1001", FixedIntegerListEncoder.encode(Arrays.asList(2, 1), 2, 2));
  }

  @Test
  void testEncode13() throws EncodingException {
    Assertions.assertEquals("1010", FixedIntegerListEncoder.encode(Arrays.asList(2, 2), 2, 2));
  }

  @Test
  void testEncode14() throws EncodingException {
    Assertions.assertEquals("1011", FixedIntegerListEncoder.encode(Arrays.asList(2, 3), 2, 2));
  }

  @Test
  void testEncode15() throws EncodingException {
    Assertions.assertEquals("1100", FixedIntegerListEncoder.encode(Arrays.asList(3, 0), 2, 2));
  }

  @Test
  void testEncode16() throws EncodingException {
    Assertions.assertEquals("1101", FixedIntegerListEncoder.encode(Arrays.asList(3, 1), 2, 2));
  }

  @Test
  void testEncode17() throws EncodingException {
    Assertions.assertEquals("1110", FixedIntegerListEncoder.encode(Arrays.asList(3, 2), 2, 2));
  }

  @Test
  void testEncode18() throws EncodingException {
    Assertions.assertEquals("1111", FixedIntegerListEncoder.encode(Arrays.asList(3, 3), 2, 2));
  }

  @Test
  void testDecode1() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(0, 0), FixedIntegerListEncoder.decode("", 2, 2));
  }

  @Test
  void testDecode2() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(0, 0), FixedIntegerListEncoder.decode("0000", 2, 2));
  }

  @Test
  void testDecode3() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(0, 1), FixedIntegerListEncoder.decode("0001", 2, 2));
  }

  @Test
  void testDecode4() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(0, 2), FixedIntegerListEncoder.decode("0010", 2, 2));
  }

  @Test
  void testDecode5() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(0, 3), FixedIntegerListEncoder.decode("0011", 2, 2));
  }

  @Test
  void testDecode6() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(1, 0), FixedIntegerListEncoder.decode("0100", 2, 2));
  }

  @Test
  void testDecode7() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(1, 1), FixedIntegerListEncoder.decode("0101", 2, 2));
  }

  @Test
  void testDecode8() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(1, 2), FixedIntegerListEncoder.decode("0110", 2, 2));
  }

  @Test
  void testDecode9() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(1, 3), FixedIntegerListEncoder.decode("0111", 2, 2));
  }

  @Test
  void testDecode10() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(2, 0), FixedIntegerListEncoder.decode("1000", 2, 2));
  }

  @Test
  void testDecode11() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(2, 1), FixedIntegerListEncoder.decode("1001", 2, 2));
  }

  @Test
  void testDecode12() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(2, 2), FixedIntegerListEncoder.decode("1010", 2, 2));
  }

  @Test
  void testDecode13() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(2, 3), FixedIntegerListEncoder.decode("1011", 2, 2));
  }

  @Test
  void testDecode14() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(3, 0), FixedIntegerListEncoder.decode("1100", 2, 2));
  }

  @Test
  void testDecode15() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(3, 1), FixedIntegerListEncoder.decode("1101", 2, 2));
  }

  @Test
  void testDecode16() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(3, 2), FixedIntegerListEncoder.decode("1110", 2, 2));
  }

  @Test
  void testDecode17() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(3, 3), FixedIntegerListEncoder.decode("1111", 2, 2));
  }

  @Test
  void testDecode18() {
    try {
      FixedIntegerListEncoder.decode("111111", 2, 2);
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }
  
  @Test
  void testDecode19() {
    try {
      FixedIntegerListEncoder.decode("2", 2, 2);
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }
  
  @Test
  void testDecode20() {
    try {
      FixedIntegerListEncoder.decode("111", 2, 2);
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }
}
