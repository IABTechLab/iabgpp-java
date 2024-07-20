package com.iab.gpp.encoder.datatype.encoder;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class FixedIntegerListEncoderTest {

  @Test
  public void testEncode1() {
    Assertions.assertEquals("0000", FixedIntegerListEncoder.encode(new ArrayList<>(), 2, 2));
  }

  @Test
  public void testEncode2() {
    Assertions.assertEquals("0000", FixedIntegerListEncoder.encode(Arrays.asList(0), 2, 2));
  }

  @Test
  public void testEncode3() {
    Assertions.assertEquals("0000", FixedIntegerListEncoder.encode(Arrays.asList(0, 0), 2, 2));
  }

  @Test
  public void testEncode4() {
    Assertions.assertEquals("0001", FixedIntegerListEncoder.encode(Arrays.asList(0, 1), 2, 2));
  }

  @Test
  public void testEncode5() {
    Assertions.assertEquals("0010", FixedIntegerListEncoder.encode(Arrays.asList(0, 2), 2, 2));
  }

  @Test
  public void testEncode6() {
    Assertions.assertEquals("0011", FixedIntegerListEncoder.encode(Arrays.asList(0, 3), 2, 2));
  }

  @Test
  public void testEncode7() {
    Assertions.assertEquals("0100", FixedIntegerListEncoder.encode(Arrays.asList(1, 0), 2, 2));
  }

  @Test
  public void testEncode8() {
    Assertions.assertEquals("0101", FixedIntegerListEncoder.encode(Arrays.asList(1, 1), 2, 2));
  }

  @Test
  public void testEncode9() {
    Assertions.assertEquals("0110", FixedIntegerListEncoder.encode(Arrays.asList(1, 2), 2, 2));
  }

  @Test
  public void testEncode10() {
    Assertions.assertEquals("0111", FixedIntegerListEncoder.encode(Arrays.asList(1, 3), 2, 2));
  }

  @Test
  public void testEncode11() {
    Assertions.assertEquals("1000", FixedIntegerListEncoder.encode(Arrays.asList(2, 0), 2, 2));
  }

  @Test
  public void testEncode12() {
    Assertions.assertEquals("1001", FixedIntegerListEncoder.encode(Arrays.asList(2, 1), 2, 2));
  }

  @Test
  public void testEncode13() {
    Assertions.assertEquals("1010", FixedIntegerListEncoder.encode(Arrays.asList(2, 2), 2, 2));
  }

  @Test
  public void testEncode14() {
    Assertions.assertEquals("1011", FixedIntegerListEncoder.encode(Arrays.asList(2, 3), 2, 2));
  }

  @Test
  public void testEncode15() {
    Assertions.assertEquals("1100", FixedIntegerListEncoder.encode(Arrays.asList(3, 0), 2, 2));
  }

  @Test
  public void testEncode16() {
    Assertions.assertEquals("1101", FixedIntegerListEncoder.encode(Arrays.asList(3, 1), 2, 2));
  }

  @Test
  public void testEncode17() {
    Assertions.assertEquals("1110", FixedIntegerListEncoder.encode(Arrays.asList(3, 2), 2, 2));
  }

  @Test
  public void testEncode18() {
    Assertions.assertEquals("1111", FixedIntegerListEncoder.encode(Arrays.asList(3, 3), 2, 2));
  }

  @Test
  public void testEncode19() {
    try {
      FixedIntegerListEncoder.encode(Arrays.asList(3, 3), 1, 1);
      Assertions.fail("EncodingException expected");
    } catch (EncodingException e) {

    }
  }
  
  @Test
  public void testDecode1() {
    Assertions.assertEquals(Arrays.asList(0, 0), FixedIntegerListEncoder.decode(BitString.of(""), 2, 2));
  }

  @Test
  public void testDecode2() {
    Assertions.assertEquals(Arrays.asList(0, 0), FixedIntegerListEncoder.decode(BitString.of("0000"), 2, 2));
  }

  @Test
  public void testDecode3() {
    Assertions.assertEquals(Arrays.asList(0, 1), FixedIntegerListEncoder.decode(BitString.of("0001"), 2, 2));
  }

  @Test
  public void testDecode4() {
    Assertions.assertEquals(Arrays.asList(0, 2), FixedIntegerListEncoder.decode(BitString.of("0010"), 2, 2));
  }

  @Test
  public void testDecode5() {
    Assertions.assertEquals(Arrays.asList(0, 3), FixedIntegerListEncoder.decode(BitString.of("0011"), 2, 2));
  }

  @Test
  public void testDecode6() {
    Assertions.assertEquals(Arrays.asList(1, 0), FixedIntegerListEncoder.decode(BitString.of("0100"), 2, 2));
  }

  @Test
  public void testDecode7() {
    Assertions.assertEquals(Arrays.asList(1, 1), FixedIntegerListEncoder.decode(BitString.of("0101"), 2, 2));
  }

  @Test
  public void testDecode8() {
    Assertions.assertEquals(Arrays.asList(1, 2), FixedIntegerListEncoder.decode(BitString.of("0110"), 2, 2));
  }

  @Test
  public void testDecode9() {
    Assertions.assertEquals(Arrays.asList(1, 3), FixedIntegerListEncoder.decode(BitString.of("0111"), 2, 2));
  }

  @Test
  public void testDecode10() {
    Assertions.assertEquals(Arrays.asList(2, 0), FixedIntegerListEncoder.decode(BitString.of("1000"), 2, 2));
  }

  @Test
  public void testDecode11() {
    Assertions.assertEquals(Arrays.asList(2, 1), FixedIntegerListEncoder.decode(BitString.of("1001"), 2, 2));
  }

  @Test
  public void testDecode12() {
    Assertions.assertEquals(Arrays.asList(2, 2), FixedIntegerListEncoder.decode(BitString.of("1010"), 2, 2));
  }

  @Test
  public void testDecode13() {
    Assertions.assertEquals(Arrays.asList(2, 3), FixedIntegerListEncoder.decode(BitString.of("1011"), 2, 2));
  }

  @Test
  public void testDecode14() {
    Assertions.assertEquals(Arrays.asList(3, 0), FixedIntegerListEncoder.decode(BitString.of("1100"), 2, 2));
  }

  @Test
  public void testDecode15() {
    Assertions.assertEquals(Arrays.asList(3, 1), FixedIntegerListEncoder.decode(BitString.of("1101"), 2, 2));
  }

  @Test
  public void testDecode16() {
    Assertions.assertEquals(Arrays.asList(3, 2), FixedIntegerListEncoder.decode(BitString.of("1110"), 2, 2));
  }

  @Test
  public void testDecode17() {
    Assertions.assertEquals(Arrays.asList(3, 3), FixedIntegerListEncoder.decode(BitString.of("1111"), 2, 2));
  }

  @Test
  public void testDecode18() {
    try {
      FixedIntegerListEncoder.decode(BitString.of("111111"), 2, 2);
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

  @Test
  public void testDecode19() {
    try {
      FixedIntegerListEncoder.decode(BitString.of("2"), 2, 2);
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

  @Test
  public void testDecode20() {
    try {
      FixedIntegerListEncoder.decode(BitString.of("111"), 2, 2);
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }
}
