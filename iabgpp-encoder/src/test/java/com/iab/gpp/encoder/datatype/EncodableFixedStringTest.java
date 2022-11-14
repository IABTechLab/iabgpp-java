package com.iab.gpp.encoder.datatype;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EncodableFixedStringTest {

  @Test
  public void testSubstring1() {
    Assertions.assertEquals("000000000000", new EncodableFixedString(2).substring("10000000000001", 1));
  }

  @Test
  public void testSubstring2() {
    Assertions.assertEquals("111111111111", new EncodableFixedString(2).substring("01111111111110", 1));
  }

}
