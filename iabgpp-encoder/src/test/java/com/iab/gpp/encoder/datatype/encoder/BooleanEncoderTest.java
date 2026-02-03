package com.iab.gpp.encoder.datatype.encoder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;

public class BooleanEncoderTest {

  @Test
  public void testEncode1() {
    BitString builder = new BitString();
    BooleanEncoder.encode(builder, false);
    Assertions.assertEquals("0", builder.toString());
  }

  @Test
  public void testEncode2() {
    BitString builder = new BitString();
    BooleanEncoder.encode(builder, true);
    Assertions.assertEquals("1", builder.toString());
  }

  @Test
  public void testDecode1() {
     BitString x = BitString.of("0");
     System.out.println(x);
    Assertions.assertEquals(false, BooleanEncoder.decode(x));
  }

  @Test
  public void testDecode2() {
    Assertions.assertEquals(true, BooleanEncoder.decode(BitString.of("1")));
  }

  @Test
  public void testDecode3() {
    try {
      BooleanEncoder.decode(BitString.of(""));
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

  @Test
  public void testDecode4() {
    try {
      BooleanEncoder.decode(BitString.of("2"));
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

  @Test
  public void testDecode5() {
    try {
      BooleanEncoder.decode(BitString.of("00"));
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

  @Test
  public void testDecode6() {
    try {
      BooleanEncoder.decode(BitString.of("01"));
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

  @Test
  public void testDecode7() {
    try {
      BooleanEncoder.decode(BitString.of("10"));
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

}
