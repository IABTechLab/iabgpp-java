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
    Assertions.assertEquals(false, BitString.of("0").readBool());
  }

  @Test
  public void testDecode2() {
    Assertions.assertEquals(true, BitString.of("1").readBool());
  }

  @Test
  public void testDecode3() {
    try {
      BitString.of("").readBool();
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

  @Test
  public void testDecode4() {
    try {
      BitString.of("2").readBool();
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

}
