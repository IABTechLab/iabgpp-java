package com.iab.gpp.encoder.datatype.encoder;

import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.IntegerSet;
import com.iab.gpp.encoder.error.DecodingException;

public class FixedBitfieldEncoderTest {

  @Test
  public void testEncode1() {
    BitString builder = new BitString();
    IntegerSet set = new IntegerSet();
    FixedBitfieldEncoder.encode(builder, set, 2);
    Assertions.assertEquals("00", builder.toString());
  }

  @Test
  public void testEncode2() {
    BitString builder = new BitString();
    IntegerSet set = new IntegerSet();
    FixedBitfieldEncoder.encode(builder, set, 1);
    Assertions.assertEquals("0", builder.toString());
  }

  @Test
  public void testEncode3() {
    BitString builder = new BitString();
    IntegerSet set = new IntegerSet();
    set.add(0);
    FixedBitfieldEncoder.encode(builder, set, 1);
    Assertions.assertEquals("1", builder.toString());
  }

  @Test
  public void testEncode4() {
    BitString builder = new BitString();
    IntegerSet set = new IntegerSet();
    FixedBitfieldEncoder.encode(builder, set, 2);
    Assertions.assertEquals("00", builder.toString());
  }

  @Test
  public void testEncode5() {
    BitString builder = new BitString();
    IntegerSet set = new IntegerSet();
    set.addInt(1);
    FixedBitfieldEncoder.encode(builder, set, 2);
    Assertions.assertEquals("01", builder.toString());
  }

  @Test
  public void testEncode6() {
    BitString builder = new BitString();
    IntegerSet set = new IntegerSet();
    set.addInt(0);
    FixedBitfieldEncoder.encode(builder, set, 2);
    Assertions.assertEquals("10", builder.toString());
  }

  @Test
  public void testEncode7() {
    BitString builder = new BitString();
    IntegerSet set = new IntegerSet();
    set.addInt(0);
    set.addInt(1);
    FixedBitfieldEncoder.encode(builder, set, 2);
    Assertions.assertEquals("11", builder.toString());
  }

  @Test
  public void testEncode8() {
    IntegerSet set = new IntegerSet(5);
    for(int i = 0; i <= 10; i++) {
      set.addInt(i);
    }
    Assertions.assertEquals(Set.of(0,1,2,3,4), set);
  }

  @Test
  public void testEncode9() {
    IntegerSet set = new IntegerSet(5);
    set.addRange(0,10);
    Assertions.assertEquals(Set.of(0,1,2,3,4), set);
  }

  private IntegerSet decode(String str) {
    return BitString.of(str).readIntegerSet(str.length());
  }

  @Test
  public void testDecode1() {
    Assertions.assertEquals(Set.of(), decode(""));
  }

  @Test
  public void testDecode2() {
    Assertions.assertEquals(Set.of(), decode("0"));
  }

  @Test
  public void testDecode3() {
    Assertions.assertEquals(Set.of(1), decode("1"));
  }

  @Test
  public void testDecode4() {
    Assertions.assertEquals(Set.of(), decode("00"));
  }

  @Test
  public void testDecode5() {
    Assertions.assertEquals(Set.of(2), decode("01"));
  }

  @Test
  public void testDecode6() {
    Assertions.assertEquals(Set.of(1), decode("10"));
  }

  @Test
  public void testDecode7() {
    Assertions.assertEquals(Set.of(1, 2), decode("11"));
  }

  @Test
  public void testDecode8() {
    try {
      decode("2");
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }
}
