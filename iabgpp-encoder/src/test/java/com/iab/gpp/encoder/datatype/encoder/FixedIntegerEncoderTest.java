package com.iab.gpp.encoder.datatype.encoder;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FixedIntegerEncoderTest {

  @Test
  public void testEncode1() {
    BitString builder = new BitString();
    builder.writeInt(0, 1);
    Assertions.assertEquals("0", builder.toString());
  }

  @Test
  public void testEncode2() {
    BitString builder = new BitString();
    builder.writeInt(0, 6);
    Assertions.assertEquals("000000", builder.toString());
  }

  @Test
  public void testEncode3() {
    BitString builder = new BitString();
    builder.writeInt(1, 1);
    Assertions.assertEquals("1", builder.toString());
  }

  @Test
  public void testEncode4() {
    BitString builder = new BitString();
    builder.writeInt(1, 4);
    Assertions.assertEquals("0001", builder.toString());
  }

  @Test
  public void testEncode5() {
    BitString builder = new BitString();
    builder.writeInt(7, 8);
    Assertions.assertEquals("00000111", builder.toString());
  }

  @Test
  public void testEncode6() {
    try {
      BitString builder = new BitString();
      builder.writeInt(8, 1);
      Assertions.fail("EncodingException expected");
    } catch (EncodingException e) {

    }
  }

  private int decode(String str) {
    return BitString.of(str).readInt(str.length());
  }

  @Test
  public void testDecode2() throws DecodingException {
    Assertions.assertEquals(0, decode("0"));
  }

  @Test
  public void testDecode3() throws DecodingException {
    Assertions.assertEquals(0, decode("000000"));
  }

  @Test
  public void testDecode4() throws DecodingException {
    Assertions.assertEquals(1, decode("1"));
  }

  @Test
  public void testDecode5() throws DecodingException {
    Assertions.assertEquals(1, decode("000001"));
  }

  @Test
  public void testDecode6() throws DecodingException {
    Assertions.assertEquals(8, decode("1000"));
  }

  @Test
  public void testDecode7() throws DecodingException {
    Assertions.assertEquals(8, decode("0000001000"));
  }
}
