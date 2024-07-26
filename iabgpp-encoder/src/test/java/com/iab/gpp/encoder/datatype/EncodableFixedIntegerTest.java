package com.iab.gpp.encoder.datatype;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iab.gpp.encoder.bitstring.BitString;

public class EncodableFixedIntegerTest {

  @Test
  public void testSubstring1() throws SubstringException {
    Assertions.assertEquals("000", new EncodableFixedInteger(3).substring(BitString.of("10001"), 1).toString());
  }

  @Test
  public void testSubstring2() throws SubstringException {
    Assertions.assertEquals("111", new EncodableFixedInteger(3).substring(BitString.of("01110"), 1).toString());
  }

}
