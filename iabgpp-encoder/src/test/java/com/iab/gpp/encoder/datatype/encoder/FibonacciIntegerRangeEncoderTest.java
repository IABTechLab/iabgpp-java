package com.iab.gpp.encoder.datatype.encoder;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;

public class FibonacciIntegerRangeEncoderTest {

  @Test
  public void testEncode1() {
    Assertions.assertEquals("000000000000", FibonacciIntegerRangeEncoder.encode(new ArrayList<>()));
  }

  @Test
  public void testEncode2() {
    Assertions.assertEquals("0000000000010011", FibonacciIntegerRangeEncoder.encode(Arrays.asList(2)));
  }

  @Test
  public void testEncode3() {
    Assertions.assertEquals("00000000000110111011", FibonacciIntegerRangeEncoder.encode(Arrays.asList(2, 3, 4, 5, 6)));
  }

  @Test
  public void testEncode4() {
    Assertions.assertEquals("000000000010001110011011", FibonacciIntegerRangeEncoder.encode(Arrays.asList(2, 5, 6, 7)));
  }

  @Test
  public void testEncode5() {
    Assertions.assertEquals("000000000010001110011011", FibonacciIntegerRangeEncoder.encode(Arrays.asList(6, 7, 2, 5)));
  }

  @Test
  public void testEncode6() {
    Assertions.assertEquals("0000000000100001110110011",
        FibonacciIntegerRangeEncoder.encode(Arrays.asList(3, 5, 6, 7, 8)));
  }

  @Test
  public void testEncode7() {
    Assertions.assertEquals("00000000001000111001101011",
        FibonacciIntegerRangeEncoder.encode(Arrays.asList(2, 5, 6, 7, 8, 9, 10, 11, 12)));
  }

  @Test
  public void testDecode1() throws DecodingException {
    Assertions.assertEquals(new ArrayList<>(), FibonacciIntegerRangeEncoder.decode(BitString.of("000000000000")));
  }

  @Test
  public void testDecode2() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(2), FibonacciIntegerRangeEncoder.decode(BitString.of("0000000000010011")));
  }

  @Test
  public void testDecode3() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(2, 3, 4, 5, 6), FibonacciIntegerRangeEncoder.decode(BitString.of("00000000000110111011")));
  }

  @Test
  public void testDecode4() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(2, 5, 6, 7), FibonacciIntegerRangeEncoder.decode(BitString.of("000000000010001110011011")));
  }

  @Test
  public void testDecode5() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(3, 5, 6, 7, 8),
        FibonacciIntegerRangeEncoder.decode(BitString.of("0000000000100001110110011")));
  }

  @Test
  public void testDecode6() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(2, 5, 6, 7, 8, 9, 10, 11, 12),
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
