package com.iab.gpp.encoder.datatype;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EncodableFixedIntegerListTest {

  @Test
  public void testSubstring1() throws SubstringException {
    Assertions.assertEquals("1000", new EncodableFixedIntegerList(2, 2).substring("10001", 0));
  }

  @Test
  public void testSubstring2() throws SubstringException {
    Assertions.assertEquals("1110", new EncodableFixedIntegerList(2, 2).substring("01110", 1));
  }

}
