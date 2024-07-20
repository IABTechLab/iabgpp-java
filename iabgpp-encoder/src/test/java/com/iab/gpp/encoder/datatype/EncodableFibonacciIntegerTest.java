package com.iab.gpp.encoder.datatype;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;

public class EncodableFibonacciIntegerTest {

  @Test
  public void testSubstring1() throws DecodingException, SubstringException {
    Assertions.assertEquals("0011", new EncodableFibonacciInteger().substring(BitString.of("100111"), 1).toString());
  }

}
