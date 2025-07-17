package com.iab.gpp.encoder.datatype;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iab.gpp.encoder.bitstring.BitString;

public class EncodableFixedStringTest {

  @Test
  public void testSubstring1() throws SubstringException {
    Assertions.assertEquals("000000000000", new EncodableFixedString(2).substring(BitString.of("10000000000001"), 1).toString());
  }

  @Test
  public void testSubstring2() throws SubstringException {
    Assertions.assertEquals("111111111111", new EncodableFixedString(2).substring(BitString.of("01111111111110"), 1).toString());
  }

}
