package com.iab.gpp.encoder.datatype.encoder;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class FixedBitfieldEncoderTest {

  @Test
  public void testEncode1() {
    BitStringBuilder builder = new BitStringBuilder();
    FixedBitfieldEncoder.encode(builder, new ArrayList<>(), 2);
    Assertions.assertEquals("00", builder.build().toString());
  }

  @Test
  public void testEncode2() {
    BitStringBuilder builder = new BitStringBuilder();
    FixedBitfieldEncoder.encode(builder, Arrays.asList(false), 1);
    Assertions.assertEquals("0", builder.build().toString());
  }

  @Test
  public void testEncode3() {
    BitStringBuilder builder = new BitStringBuilder();
    FixedBitfieldEncoder.encode(builder, Arrays.asList(true), 1);
    Assertions.assertEquals("1", builder.build().toString());
  }

  @Test
  public void testEncode4() {
    BitStringBuilder builder = new BitStringBuilder();
    FixedBitfieldEncoder.encode(builder, Arrays.asList(false, false), 2);
    Assertions.assertEquals("00", builder.build().toString());
  }

  @Test
  public void testEncode5() {
    BitStringBuilder builder = new BitStringBuilder();
    FixedBitfieldEncoder.encode(builder, Arrays.asList(false, true), 2);
    Assertions.assertEquals("01", builder.build().toString());
  }

  @Test
  public void testEncode6() {
    BitStringBuilder builder = new BitStringBuilder();
    FixedBitfieldEncoder.encode(builder, Arrays.asList(true, false), 2);
    Assertions.assertEquals("10", builder.build().toString());
  }

  @Test
  public void testEncode7() {
    BitStringBuilder builder = new BitStringBuilder();
    FixedBitfieldEncoder.encode(builder, Arrays.asList(true, true), 2);
    Assertions.assertEquals("11", builder.build().toString());
  }

  @Test
  public void testEncode8() {
    try {
      BitStringBuilder builder = new BitStringBuilder();
      FixedBitfieldEncoder.encode(builder, Arrays.asList(true, true, true), 2);
      Assertions.fail("EncodingException expected");
    } catch (EncodingException e) {

    }
  }
  
  @Test
  public void testDecode1() {
    Assertions.assertEquals(new ArrayList<>(), FixedBitfieldEncoder.decode(BitString.of("")));
  }

  @Test
  public void testDecode2() {
    Assertions.assertEquals(Arrays.asList(false), FixedBitfieldEncoder.decode(BitString.of("0")));
  }

  @Test
  public void testDecode3() {
    Assertions.assertEquals(Arrays.asList(true), FixedBitfieldEncoder.decode(BitString.of("1")));
  }

  @Test
  public void testDecode4() {
    Assertions.assertEquals(Arrays.asList(false, false), FixedBitfieldEncoder.decode(BitString.of("00")));
  }

  @Test
  public void testDecode5() {
    Assertions.assertEquals(Arrays.asList(false, true), FixedBitfieldEncoder.decode(BitString.of("01")));
  }

  @Test
  public void testDecode6() {
    Assertions.assertEquals(Arrays.asList(true, false), FixedBitfieldEncoder.decode(BitString.of("10")));
  }

  @Test
  public void testDecode7() {
    Assertions.assertEquals(Arrays.asList(true, true), FixedBitfieldEncoder.decode(BitString.of("11")));
  }

  @Test
  public void testDecode8() {
    try {
      FixedBitfieldEncoder.decode(BitString.of("2"));
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }
}
