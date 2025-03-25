package com.iab.gpp.encoder.datatype;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;

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
    encodableOptimizedFixedRange.decode(BitString.of("00000000001100000000000000001000000000001000000000000000000000001"));
    Assertions.assertEquals(Arrays.asList(12, 24, 48), encodableOptimizedFixedRange.getValue());
  }

  @Test
  public void testDecode2() {
    encodableOptimizedFixedRange.decode(BitString.of("00000000000111100000000000000000001000000000001"));
    Assertions.assertEquals(Arrays.asList(18, 30), encodableOptimizedFixedRange.getValue());
  }

  @Test
  public void testDecode3() {
    encodableOptimizedFixedRange.decode(BitString.of("000000000001110000000000000000000000000000001"));
    Assertions.assertEquals(Arrays.asList(28), encodableOptimizedFixedRange.getValue());
  }

  @Test
  public void testDecode4() {
    encodableOptimizedFixedRange.decode(BitString.of("0000000000011101100000000000100000000000011101"));
    Assertions.assertEquals(Arrays.asList(29), encodableOptimizedFixedRange.getValue());
  }

  @Test
  public void testSubstring1() throws SubstringException {
    Assertions.assertEquals("000000000001110000000000000000000000000000001", encodableOptimizedFixedRange.substring(
    		BitString.of("000010001111010010000110111111111100000000001111010010000110111111111100000000000000000000000000000000000000000100001101000000000000000010000000000000000000000000000000000000000000000000000000000000000000000000000000000000001110000000000000000000000000000001000000000000000000000000000000"),
        213).toString());
  }

  @Test
  public void testSubstring2() throws SubstringException {
    Assertions.assertEquals("0000000000011101100000000000100000000000011101", encodableOptimizedFixedRange.substring(
    		BitString.of("000010001111010010000110111111111100000000001111010010000110111111111100000000000000000000000000000000000000000100001101000000000000000010000000000000000000000000000000000000000000000000000000000000000000000000000000000000001110110000000000010000000000001110100000000000000000000000000000"),
        213).toString());
  }
}
