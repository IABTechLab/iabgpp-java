package com.iab.gpp.encoder.datatype.encoder;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.IntegerSet;
import com.iab.gpp.encoder.error.DecodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FixedIntegerRangeEncoderTest {

  @Test
  public void testEncode1() {
    BitString builder = new BitString();
    FixedIntegerRangeEncoder.encode(builder, new ArrayList<>());
    Assertions.assertEquals("000000000000", builder.toString());
  }

  @Test
  public void testEncode2() {
    BitString builder = new BitString();
    FixedIntegerRangeEncoder.encode(builder, Arrays.asList(2));
    Assertions.assertEquals("00000000000100000000000000010", builder.toString());
  }

  @Test
  public void testEncode3() {
    BitString builder = new BitString();
    FixedIntegerRangeEncoder.encode(builder, Arrays.asList(2, 3, 4, 5, 6));
    Assertions.assertEquals("000000000001100000000000000100000000000000110", builder.toString());
  }

  @Test
  public void testEncode4() {
    BitString builder = new BitString();
    FixedIntegerRangeEncoder.encode(builder, Arrays.asList(2, 5, 6, 7));
    Assertions.assertEquals(
        "00000000001000000000000000010100000000000001010000000000000111", builder.toString());
  }

  @Test
  public void testEncode5() {
    BitString builder = new BitString();
    IntegerSet set = new IntegerSet();
    set.addAll(Arrays.asList(6, 7, 2, 5));
    FixedIntegerRangeEncoder.encode(builder, set);
    Assertions.assertEquals(
        "00000000001000000000000000010100000000000001010000000000000111", builder.toString());
  }

  @Test
  public void testEncode6() {
    BitString builder = new BitString();
    FixedIntegerRangeEncoder.encode(builder, Arrays.asList(3, 5, 6, 7, 8));
    Assertions.assertEquals(
        "00000000001000000000000000011100000000000001010000000000001000", builder.toString());
  }

  @Test
  public void testEncode7() {
    BitString builder = new BitString();
    FixedIntegerRangeEncoder.encode(builder, Arrays.asList(12, 24, 48));
    Assertions.assertEquals(
        "000000000011000000000000011000000000000001100000000000000110000", builder.toString());
  }

  @Test
  public void testEncode8() {
    BitString builder = new BitString();
    FixedIntegerRangeEncoder.encode(builder, Arrays.asList(12, 24, 48, 49));
    Assertions.assertEquals(
        "0000000000110000000000000110000000000000011000100000000001100000000000000110001",
        builder.toString());
  }

  @Test
  public void testEncode9() {
    BitString builder = new BitString();
    FixedIntegerRangeEncoder.encode(builder, Arrays.asList(2, 6, 8, 12, 18, 23, 24, 25, 37, 42));
    Assertions.assertEquals(
        "00000000100000000000000000010000000000000001100000000000000100000000000000001100000000000000100101000000000001011100000000000110010000000000010010100000000000101010",
        builder.toString());
  }

  private IntegerSet decode(String str) {
    return FixedIntegerRangeEncoder.decode(BitString.of(str));
  }

  @Test
  public void testDecode1() throws DecodingException {
    Assertions.assertEquals(Set.of(), decode("000000000000"));
  }

  @Test
  public void testDecode2() throws DecodingException {
    Assertions.assertEquals(Set.of(2), decode("00000000000100000000000000010"));
  }

  @Test
  public void testDecode3() throws DecodingException {
    Assertions.assertEquals(
        Set.of(2, 3, 4, 5, 6), decode("000000000001100000000000000100000000000000110"));
  }

  @Test
  public void testDecode4() throws DecodingException {
    Assertions.assertEquals(
        Set.of(2, 5, 6, 7),
        decode("00000000001000000000000000010100000000000001010000000000000111"));
  }

  @Test
  public void testDecode5() throws DecodingException {
    Assertions.assertEquals(
        Set.of(3, 5, 6, 7, 8),
        decode("00000000001000000000000000011100000000000001010000000000001000"));
  }

  @Test
  public void testDecode6() throws DecodingException {
    Assertions.assertEquals(
        Set.of(12, 24, 48),
        decode("000000000011000000000000011000000000000001100000000000000110000"));
  }

  @Test
  public void testDecode7() throws DecodingException {
    Assertions.assertEquals(
        Set.of(12, 24, 48, 49),
        decode("0000000000110000000000000110000000000000011000100000000001100000000000000110001"));
  }

  @Test
  public void testDecode8() throws DecodingException {
    Assertions.assertEquals(
        Set.of(2, 6, 8, 12, 18, 23, 24, 25, 37, 42),
        decode(
            "00000000100000000000000000010000000000000001100000000000000100000000000000001100000000000000100101000000000001011100000000000110010000000000010010100000000000101010"));
  }

  @Test
  public void testDecode9() {
    try {
      decode("0011");
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

  @Test
  public void testDecode10() {
    try {
      decode("000000000002");
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }
}
