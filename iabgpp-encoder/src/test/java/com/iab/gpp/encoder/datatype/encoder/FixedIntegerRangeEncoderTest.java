package com.iab.gpp.encoder.datatype.encoder;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;

public class FixedIntegerRangeEncoderTest {

  @Test
  public void testEncode1() {
    Assertions.assertEquals("000000000000", FixedIntegerRangeEncoder.encode(new ArrayList<>()));
  }

  @Test
  public void testEncode2() {
    Assertions.assertEquals("00000000000100000000000000010", FixedIntegerRangeEncoder.encode(Arrays.asList(2)));
  }

  @Test
  public void testEncode3() {
    Assertions.assertEquals("000000000001100000000000000100000000000000110",
        FixedIntegerRangeEncoder.encode(Arrays.asList(2, 3, 4, 5, 6)));
  }

  @Test
  public void testEncode4() {
    Assertions.assertEquals("00000000001000000000000000010100000000000001010000000000000111",
        FixedIntegerRangeEncoder.encode(Arrays.asList(2, 5, 6, 7)));
  }

  @Test
  public void testEncode5() {
    Assertions.assertEquals("00000000001000000000000000010100000000000001010000000000000111",
        FixedIntegerRangeEncoder.encode(Arrays.asList(5, 2, 7, 6)));
  }

  @Test
  public void testEncode6() {
    Assertions.assertEquals("00000000001000000000000000011100000000000001010000000000001000",
        FixedIntegerRangeEncoder.encode(Arrays.asList(3, 5, 6, 7, 8)));
  }

  @Test
  public void testEncode7() {
    Assertions.assertEquals("000000000011000000000000011000000000000001100000000000000110000",
        FixedIntegerRangeEncoder.encode(Arrays.asList(12, 24, 48)));
  }

  @Test
  public void testEncode8() {
    Assertions.assertEquals("0000000000110000000000000110000000000000011000100000000001100000000000000110001",
        FixedIntegerRangeEncoder.encode(Arrays.asList(12, 24, 48, 49)));
  }

  @Test
  public void testEncode9() {
    Assertions.assertEquals(
        "00000000100000000000000000010000000000000001100000000000000100000000000000001100000000000000100101000000000001011100000000000110010000000000010010100000000000101010",
        FixedIntegerRangeEncoder.encode(Arrays.asList(2, 6, 8, 12, 18, 23, 24, 25, 37, 42)));
  }

  @Test
  public void testDecode1() throws DecodingException {
    Assertions.assertEquals(new ArrayList<>(), FixedIntegerRangeEncoder.decode(BitString.of("000000000000")));
  }

  @Test
  public void testDecode2() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(2), FixedIntegerRangeEncoder.decode(BitString.of("00000000000100000000000000010")));
  }

  @Test
  public void testDecode3() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(2, 3, 4, 5, 6),
        FixedIntegerRangeEncoder.decode(BitString.of("000000000001100000000000000100000000000000110")));
  }

  @Test
  public void testDecode4() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(2, 5, 6, 7),
        FixedIntegerRangeEncoder.decode(BitString.of("00000000001000000000000000010100000000000001010000000000000111")));
  }

  @Test
  public void testDecode5() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(3, 5, 6, 7, 8),
        FixedIntegerRangeEncoder.decode(BitString.of("00000000001000000000000000011100000000000001010000000000001000")));
  }

  @Test
  public void testDecode6() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(12, 24, 48),
        FixedIntegerRangeEncoder.decode(BitString.of("000000000011000000000000011000000000000001100000000000000110000")));
  }

  @Test
  public void testDecode7() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(12, 24, 48, 49), FixedIntegerRangeEncoder
        .decode(BitString.of("0000000000110000000000000110000000000000011000100000000001100000000000000110001")));
  }

  @Test
  public void testDecode8() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(2, 6, 8, 12, 18, 23, 24, 25, 37, 42), FixedIntegerRangeEncoder.decode(
    		BitString.of("00000000100000000000000000010000000000000001100000000000000100000000000000001100000000000000100101000000000001011100000000000110010000000000010010100000000000101010")));
  }

  @Test
  public void testDecode9() {
    try {
      FixedIntegerRangeEncoder.decode(BitString.of("0011"));
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

  @Test
  public void testDecode10() {
    try {
      FixedIntegerRangeEncoder.decode(BitString.of("000000000002"));
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }
}
