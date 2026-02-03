package com.iab.gpp.encoder.datatype;

import java.util.Arrays;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iab.gpp.encoder.bitstring.BitString;

public class EncodableOptimizedFixedRangeTest {

  private EncodableOptimizedFixedRange encodableOptimizedFixedRange = new EncodableOptimizedFixedRange();

  @Test
  public void testEncode1() {
    encodableOptimizedFixedRange.setValue(Arrays.asList(12, 24, 48));
    BitString builder = new BitString();
    encodableOptimizedFixedRange.encode(builder);
    Assertions.assertEquals("00000000001100000000000000001000000000001000000000000000000000001",
        builder.toString());
  }

  @Test
  public void testEncode2() {
    encodableOptimizedFixedRange.setValue(Arrays.asList(18, 30));
    BitString builder = new BitString();
    encodableOptimizedFixedRange.encode(builder);
    Assertions.assertEquals("00000000000111100000000000000000001000000000001", builder.toString());
  }

  @Test
  public void testEncode3() {
    encodableOptimizedFixedRange.setValue(Arrays.asList(28));
    BitString builder = new BitString();
    encodableOptimizedFixedRange.encode(builder);
    Assertions.assertEquals("000000000001110000000000000000000000000000001", builder.toString());
  }

  @Test
  public void testEncode4() {
    encodableOptimizedFixedRange.setValue(Arrays.asList(29));
    BitString builder = new BitString();
    encodableOptimizedFixedRange.encode(builder);
    Assertions.assertEquals("0000000000011101100000000000100000000000011101", builder.toString());
  }

  @Test
  public void testDecode1() {
    encodableOptimizedFixedRange.decode(BitString.of("00000000001100000000000000001000000000001000000000000000000000001"));
    Assertions.assertEquals(Set.of(12, 24, 48), encodableOptimizedFixedRange.getValue());
  }

  @Test
  public void testDecode2() {
    encodableOptimizedFixedRange.decode(BitString.of("00000000000111100000000000000000001000000000001"));
    Assertions.assertEquals(Set.of(18, 30), encodableOptimizedFixedRange.getValue());
  }

  @Test
  public void testDecode3() {
    encodableOptimizedFixedRange.decode(BitString.of("000000000001110000000000000000000000000000001"));
    Assertions.assertEquals(Set.of(28), encodableOptimizedFixedRange.getValue());
  }

  @Test
  public void testDecode4() {
    encodableOptimizedFixedRange.decode(BitString.of("0000000000011101100000000000100000000000011101"));
    Assertions.assertEquals(Set.of(29), encodableOptimizedFixedRange.getValue());
  }
}
