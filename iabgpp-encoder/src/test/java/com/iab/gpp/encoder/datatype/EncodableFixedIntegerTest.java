package com.iab.gpp.encoder.datatype;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EncodableFixedIntegerTest {

  @Test
  public void testSubstring1() throws SubstringException {
    Assertions.assertEquals("000", new EncodableFixedInteger(3).substring("10001", 1));
  }

  @Test
  public void testSubstring2() throws SubstringException {
    Assertions.assertEquals("111", new EncodableFixedInteger(3).substring("01110", 1));
  }

}
