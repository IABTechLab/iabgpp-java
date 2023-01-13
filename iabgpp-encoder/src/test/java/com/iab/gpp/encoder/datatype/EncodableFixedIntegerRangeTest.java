package com.iab.gpp.encoder.datatype;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;

public class EncodableFixedIntegerRangeTest {

  @Test
  public void testSubstring1() throws DecodingException {
    Assertions.assertEquals("00000000001000000000000000011100000000000001010000000000001000",
        new EncodableFixedIntegerRange().substring("1000000000010000000000000000111000000000000010100000000000010001",
            1));
  }
  
  @Test
  public void testSubstring2() throws DecodingException {
    Assertions.assertEquals("00000000000100000000000011101",
        new EncodableFixedIntegerRange().substring("000010001111010010000110111111111100000000001111010010000110111111111100000000000000000000000000000000000000000100001101000000000000000010000000000000000000000000000000000000000000000000000000000000000000000000000000000000001110110000000000010000000000001110100000000000000000000000000000",
            230));
  }

  @Test
  public void testEncode1() {
    EncodableFixedIntegerRange encodableFixedIntegerRange = new EncodableFixedIntegerRange();
    encodableFixedIntegerRange.setValue(Arrays.asList(28));
    Assertions.assertEquals("00000000000100000000000011100", encodableFixedIntegerRange.encode());
  }
  
  @Test
  public void testEncode2() {
    EncodableFixedIntegerRange encodableFixedIntegerRange = new EncodableFixedIntegerRange();
    encodableFixedIntegerRange.setValue(Arrays.asList(29));
    Assertions.assertEquals("00000000000100000000000011101", encodableFixedIntegerRange.encode());
  }
  
  @Test
  public void testDecode1() throws DecodingException {
    EncodableFixedIntegerRange encodableFixedIntegerRange = new EncodableFixedIntegerRange();
    encodableFixedIntegerRange.decode("00000000000100000000000011100");
    Assertions.assertEquals(Arrays.asList(28), encodableFixedIntegerRange.getValue());
  }
  
  @Test
  public void testDecode2() throws DecodingException {
    EncodableFixedIntegerRange encodableFixedIntegerRange = new EncodableFixedIntegerRange();
    encodableFixedIntegerRange.decode("00000000000100000000000011101");
    Assertions.assertEquals(Arrays.asList(29), encodableFixedIntegerRange.getValue());
  }
  
}
