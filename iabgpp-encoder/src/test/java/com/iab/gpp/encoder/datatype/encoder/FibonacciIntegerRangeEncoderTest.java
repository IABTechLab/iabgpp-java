package com.iab.gpp.encoder.datatype.encoder;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;

class FibonacciIntegerRangeEncoderTest {

  @Test
  void testEncode1() {
    Assertions.assertEquals("000000000000", FibonacciIntegerRangeEncoder.encode(new ArrayList<>()));
  }

  @Test
  void testEncode2() {
    Assertions.assertEquals("0000000000010011", FibonacciIntegerRangeEncoder.encode(Arrays.asList(2)));
  }

  @Test
  void testEncode3() {
    Assertions.assertEquals("00000000000110111011", FibonacciIntegerRangeEncoder.encode(Arrays.asList(2, 3, 4, 5, 6)));
  }

  @Test
  void testEncode4() {
    Assertions.assertEquals("000000000010001110011011", FibonacciIntegerRangeEncoder.encode(Arrays.asList(2, 5, 6, 7)));
  }

  @Test
  void testEncode5() {
    Assertions.assertEquals("000000000010001110011011", FibonacciIntegerRangeEncoder.encode(Arrays.asList(6, 7, 2, 5)));
  }

  @Test
  void testEncode6() {
    Assertions.assertEquals("0000000000100001110110011",
        FibonacciIntegerRangeEncoder.encode(Arrays.asList(3, 5, 6, 7, 8)));
  }

  @Test
  void testDecode1() throws DecodingException {
    Assertions.assertEquals(new ArrayList<>(), FibonacciIntegerRangeEncoder.decode("000000000000"));
  }

  @Test
  void testDecode2() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(2), FibonacciIntegerRangeEncoder.decode("0000000000010011"));
  }

  @Test
  void testDecode3() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(2, 3, 4, 5, 6), FibonacciIntegerRangeEncoder.decode("00000000000110111011"));
  }

  @Test
  void testDecode4() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(2, 5, 6, 7), FibonacciIntegerRangeEncoder.decode("000000000010001110011011"));
  }

  @Test
  void testDecode5() throws DecodingException {
    Assertions.assertEquals(Arrays.asList(3, 5, 6, 7, 8),
        FibonacciIntegerRangeEncoder.decode("0000000000100001110110011"));
  }

  @Test
  void testDecode6() {
    try {
      FibonacciIntegerRangeEncoder.decode("0011");
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }

  @Test
  void testDecode7() {
    try {
      FibonacciIntegerRangeEncoder.decode("000000000002");
      Assertions.fail("DecodingException expected");
    } catch (DecodingException e) {

    }
  }
}
