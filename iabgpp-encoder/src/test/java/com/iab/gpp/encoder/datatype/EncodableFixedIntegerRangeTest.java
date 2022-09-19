package com.iab.gpp.encoder.datatype;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;

class EncodableFixedIntegerRangeTest {

  @Test
  void testSubstring1() throws DecodingException {
    Assertions.assertEquals("00000000001000000000000000011100000000000001010000000000001000",
        new EncodableFixedIntegerRange().substring("1000000000010000000000000000111000000000000010100000000000010001",
            1));
  }

}
