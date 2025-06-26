package com.iab.gpp.encoder.datatype.encoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.error.DecodingException;

public class FibonacciIntegerRangeEncoderTest {

  @Test
  public void testEncode1() {
    BitStringBuilder builder = new BitStringBuilder();
    FibonacciIntegerRangeEncoder.encode(builder, new ArrayList<>());
    Assertions.assertEquals("000000000000", builder.build().toString());
  }

  @Test
  public void testEncode2() {
    BitStringBuilder builder = new BitStringBuilder();
    FibonacciIntegerRangeEncoder.encode(builder, Arrays.asList(2));
    Assertions.assertEquals("0000000000010011", builder.build().toString());
  }

  @Test
  public void testEncode3() {
    BitStringBuilder builder = new BitStringBuilder();
    FibonacciIntegerRangeEncoder.encode(builder, Arrays.asList(2, 3, 4, 5, 6));
    Assertions.assertEquals("00000000000110111011", builder.build().toString());
  }

  @Test
  public void testEncode4() {
    BitStringBuilder builder = new BitStringBuilder();
    FibonacciIntegerRangeEncoder.encode(builder, Arrays.asList(2, 5, 6, 7));
    Assertions.assertEquals("000000000010001110011011", builder.build().toString());
  }

  @Test
  public void testEncode5() {
    BitStringBuilder builder = new BitStringBuilder();
    IntegerSet set = new IntegerBitSet();
    set.addAll(Arrays.asList(6, 7, 2, 5));
    FibonacciIntegerRangeEncoder.encode(builder, set);
    Assertions.assertEquals("000000000010001110011011", builder.build().toString());
  }

  @Test
  public void testEncode6() {
    BitStringBuilder builder = new BitStringBuilder();
    FibonacciIntegerRangeEncoder.encode(builder, Arrays.asList(3, 5, 6, 7, 8));
    Assertions.assertEquals("0000000000100001110110011", builder.build().toString());
  }

  @Test
  public void testEncode7() {
    BitStringBuilder builder = new BitStringBuilder();
    FibonacciIntegerRangeEncoder.encode(builder, Arrays.asList(2, 5, 6, 7, 8, 9, 10, 11, 12));
    Assertions.assertEquals("00000000001000111001101011", builder.build().toString());
  }

  @Test
  public void testDecode1() throws DecodingException {
    Assertions.assertEquals(Set.of(), FibonacciIntegerRangeEncoder.decode(BitString.of("000000000000")));
  }

  @Test
  public void testDecode2() throws DecodingException {
    Assertions.assertEquals(Set.of(2), FibonacciIntegerRangeEncoder.decode(BitString.of("0000000000010011")));
  }

  @Test
  public void testDecode3() throws DecodingException {
    Assertions.assertEquals(Set.of(2, 3, 4, 5, 6), FibonacciIntegerRangeEncoder.decode(BitString.of("00000000000110111011")));
  }

  @Test
  public void testDecode4() throws DecodingException {
    Assertions.assertEquals(Set.of(2, 5, 6, 7), FibonacciIntegerRangeEncoder.decode(BitString.of("000000000010001110011011")));
  }

  @Test
  public void testDecode5() throws DecodingException {
    Assertions.assertEquals(Set.of(3, 5, 6, 7, 8),
        FibonacciIntegerRangeEncoder.decode(BitString.of("0000000000100001110110011")));
  }

  @Test
  public void testDecode6() throws DecodingException {
    Assertions.assertEquals(Set.of(2, 5, 6, 7, 8, 9, 10, 11, 12),
        FibonacciIntegerRangeEncoder.decode(BitString.of("00000000001000111001101011")));
  }

  @Test
  public void testDecode7() {
    try {
      FibonacciIntegerRangeEncoder.decode(BitString.of("0011"));
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

  @Test
  public void testDecode8() {
    try {
      FibonacciIntegerRangeEncoder.decode(BitString.of("000000000002"));
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }
}
