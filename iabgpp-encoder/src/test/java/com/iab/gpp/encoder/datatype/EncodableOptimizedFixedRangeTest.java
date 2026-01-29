package com.iab.gpp.encoder.datatype;

import java.util.Arrays;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.bitstring.BitStringReader;

public class EncodableOptimizedFixedRangeTest {

  private EncodableOptimizedFixedRange encodableOptimizedFixedRange = new EncodableOptimizedFixedRange();

  @Test
  public void testEncode1() {
    encodableOptimizedFixedRange.setValue(Arrays.asList(12, 24, 48));
    BitStringBuilder builder = new BitStringBuilder();
    encodableOptimizedFixedRange.encode(builder);
    Assertions.assertEquals("00000000001100000000000000001000000000001000000000000000000000001",
        builder.build().toString());
  }

  @Test
  public void testEncode2() {
    encodableOptimizedFixedRange.setValue(Arrays.asList(18, 30));
    BitStringBuilder builder = new BitStringBuilder();
    encodableOptimizedFixedRange.encode(builder);
    Assertions.assertEquals("00000000000111100000000000000000001000000000001", builder.build().toString());
  }

  @Test
  public void testEncode3() {
    encodableOptimizedFixedRange.setValue(Arrays.asList(28));
    BitStringBuilder builder = new BitStringBuilder();
    encodableOptimizedFixedRange.encode(builder);
    Assertions.assertEquals("000000000001110000000000000000000000000000001", builder.build().toString());
  }

  @Test
  public void testEncode4() {
    encodableOptimizedFixedRange.setValue(Arrays.asList(29));
    BitStringBuilder builder = new BitStringBuilder();
    encodableOptimizedFixedRange.encode(builder);
    Assertions.assertEquals("0000000000011101100000000000100000000000011101", builder.build().toString());
  }

  @Test
  public void testDecode1() {
    encodableOptimizedFixedRange.decode(new BitStringReader(BitString.of("00000000001100000000000000001000000000001000000000000000000000001")));
    Assertions.assertEquals(Set.of(12, 24, 48), encodableOptimizedFixedRange.getValue());
  }

  @Test
  public void testDecode2() {
    encodableOptimizedFixedRange.decode(new BitStringReader(BitString.of("00000000000111100000000000000000001000000000001")));
    Assertions.assertEquals(Set.of(18, 30), encodableOptimizedFixedRange.getValue());
  }

  @Test
  public void testDecode3() {
    encodableOptimizedFixedRange.decode(new BitStringReader(BitString.of("000000000001110000000000000000000000000000001")));
    Assertions.assertEquals(Set.of(28), encodableOptimizedFixedRange.getValue());
  }

  @Test
  public void testDecode4() {
    encodableOptimizedFixedRange.decode(new BitStringReader(BitString.of("0000000000011101100000000000100000000000011101")));
    Assertions.assertEquals(Set.of(29), encodableOptimizedFixedRange.getValue());
  }
}
