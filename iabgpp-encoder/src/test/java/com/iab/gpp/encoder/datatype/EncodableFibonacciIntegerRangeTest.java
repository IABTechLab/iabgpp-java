package com.iab.gpp.encoder.datatype;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;

public class EncodableFibonacciIntegerRangeTest {

  @Test
  public void testSubstring1() throws DecodingException {
    Assertions.assertEquals("0000000000100001110110011",
        new EncodableFibonacciIntegerRange().substring("100000000001000011101100110", 1));
  }
}
