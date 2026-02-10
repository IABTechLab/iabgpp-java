package com.iab.gpp.encoder.datatype;

import java.util.Arrays;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.field.UsNatField;

public class EncodableOptimizedFixedRangeTest {

  private EncodableOptimizedFixedRange<UsNatField> encodableOptimizedFixedRange = new EncodableOptimizedFixedRange<>("");

  @Test
  public void testEncode1() {
    IntegerSet integerSet = new IntegerSet();
    integerSet.addAll(Arrays.asList(12, 24, 48));
    BitString builder = new BitString();
    encodableOptimizedFixedRange.encode(builder, integerSet, null);
    Assertions.assertEquals("00000000001100000000000000001000000000001000000000000000000000001",
        builder.toString());
  }

  @Test
  public void testEncode2() {
    IntegerSet integerSet = new IntegerSet();
    integerSet.addAll(Arrays.asList(18, 30));
    BitString builder = new BitString();
    encodableOptimizedFixedRange.encode(builder, integerSet, null);
    Assertions.assertEquals("00000000000111100000000000000000001000000000001", builder.toString());
  }

  @Test
  public void testEncode3() {
    IntegerSet integerSet = new IntegerSet();
    integerSet.addAll(Arrays.asList(28));
    BitString builder = new BitString();
    encodableOptimizedFixedRange.encode(builder, integerSet, null);
    Assertions.assertEquals("000000000001110000000000000000000000000000001", builder.toString());
  }

  @Test
  public void testEncode4() {
    IntegerSet integerSet = new IntegerSet();
    integerSet.addAll(Arrays.asList(29));
    BitString builder = new BitString();
    encodableOptimizedFixedRange.encode(builder, integerSet, null);
    Assertions.assertEquals("0000000000011101100000000000100000000000011101", builder.toString());
  }

  @Test
  public void testDecode1() {
    Assertions.assertEquals(Set.of(12, 24, 48), encodableOptimizedFixedRange.decode(BitString.of("00000000001100000000000000001000000000001000000000000000000000001"), null));
  }

  @Test
  public void testDecode2() {
    Assertions.assertEquals(Set.of(18, 30), encodableOptimizedFixedRange.decode(BitString.of("00000000000111100000000000000000001000000000001"), null));
  }

  @Test
  public void testDecode3() {
    Assertions.assertEquals(Set.of(28), encodableOptimizedFixedRange.decode(BitString.of("000000000001110000000000000000000000000000001"), null));
  }

  @Test
  public void testDecode4() {
    Assertions.assertEquals(Set.of(29), encodableOptimizedFixedRange.decode(BitString.of("0000000000011101100000000000100000000000011101"), null));
  }
}
