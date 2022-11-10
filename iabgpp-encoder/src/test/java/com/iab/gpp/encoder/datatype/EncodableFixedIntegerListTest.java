package com.iab.gpp.encoder.datatype;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EncodableFixedIntegerListTest {

  @Test
  void testSubstring1() {
    Assertions.assertEquals("000", new EncodableFixedBitfield(3).substring("10001", 1));
  }

  @Test
  void testSubstring2() {
    Assertions.assertEquals("111", new EncodableFixedBitfield(3).substring("01110", 1));
  }

}
