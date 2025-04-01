package com.iab.gpp.encoder.datatype.encoder;

import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.error.DecodingException;

public class FixedBitfieldEncoderTest {

  @Test
  public void testEncode1() {
    BitStringBuilder builder = new BitStringBuilder();
    IntegerSet set = new IntegerBitSet();
    FixedBitfieldEncoder.encode(builder, set, 2);
    Assertions.assertEquals("00", builder.build().toString());
  }

  @Test
  public void testEncode2() {
    BitStringBuilder builder = new BitStringBuilder();
    IntegerSet set = new IntegerBitSet();
    FixedBitfieldEncoder.encode(builder, set, 1);
    Assertions.assertEquals("0", builder.build().toString());
  }

  @Test
  public void testEncode3() {
    BitStringBuilder builder = new BitStringBuilder();
    IntegerSet set = new IntegerBitSet();
    set.add(0);
    FixedBitfieldEncoder.encode(builder, set, 1);
    Assertions.assertEquals("1", builder.build().toString());
  }

  @Test
  public void testEncode4() {
    BitStringBuilder builder = new BitStringBuilder();
    IntegerSet set = new IntegerBitSet();
    FixedBitfieldEncoder.encode(builder, set, 2);
    Assertions.assertEquals("00", builder.build().toString());
  }

  @Test
  public void testEncode5() {
    BitStringBuilder builder = new BitStringBuilder();
    IntegerSet set = new IntegerBitSet();
    set.addInt(1);
    FixedBitfieldEncoder.encode(builder, set, 2);
    Assertions.assertEquals("01", builder.build().toString());
  }

  @Test
  public void testEncode6() {
    BitStringBuilder builder = new BitStringBuilder();
    IntegerSet set = new IntegerBitSet();
    set.addInt(0);
    FixedBitfieldEncoder.encode(builder, set, 2);
    Assertions.assertEquals("10", builder.build().toString());
  }

  @Test
  public void testEncode7() {
    BitStringBuilder builder = new BitStringBuilder();
    IntegerSet set = new IntegerBitSet();
    set.addInt(0);
    set.addInt(1);
    FixedBitfieldEncoder.encode(builder, set, 2);
    Assertions.assertEquals("11", builder.build().toString());
  }

  @Test
  public void testEncode8() {
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
      BitStringBuilder builder = new BitStringBuilder();
      IntegerSet set = new IntegerBitSet(IntegerBitSet.MAX_COLLECTION_SIZE);
      for(int i = 0; i <= IntegerBitSet.MAX_COLLECTION_SIZE; i++) {
        set.addInt(i);
      }
      FixedBitfieldEncoder.encode(builder, set, 2);
    });
  }
  
  @Test
  public void testDecode1() {
    Assertions.assertEquals(Set.of(), FixedBitfieldEncoder.decode(BitString.of("")));
  }

  @Test
  public void testDecode2() {
    Assertions.assertEquals(Set.of(), FixedBitfieldEncoder.decode(BitString.of("0")));
  }

  @Test
  public void testDecode3() {
    Assertions.assertEquals(Set.of(0), FixedBitfieldEncoder.decode(BitString.of("1")));
  }

  @Test
  public void testDecode4() {
    Assertions.assertEquals(Set.of(), FixedBitfieldEncoder.decode(BitString.of("00")));
  }

  @Test
  public void testDecode5() {
    Assertions.assertEquals(Set.of(1), FixedBitfieldEncoder.decode(BitString.of("01")));
  }

  @Test
  public void testDecode6() {
    Assertions.assertEquals(Set.of(0), FixedBitfieldEncoder.decode(BitString.of("10")));
  }

  @Test
  public void testDecode7() {
    Assertions.assertEquals(Set.of(0, 1), FixedBitfieldEncoder.decode(BitString.of("11")));
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
