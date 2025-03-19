package com.iab.gpp.encoder.datatype;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iab.gpp.encoder.bitstring.BitString;

public class EncodableFixedIntegerListTest {

  @Test
  public void testSubstring1() throws SubstringException {
    Assertions.assertEquals("1000", new EncodableFixedIntegerList(2, 2).substring(BitString.of("10001"), 0).toString());
  }

  @Test
  public void testSubstring2() throws SubstringException {
    Assertions.assertEquals("1110", new EncodableFixedIntegerList(2, 2).substring(BitString.of("01110"), 1).toString());
  }

}
