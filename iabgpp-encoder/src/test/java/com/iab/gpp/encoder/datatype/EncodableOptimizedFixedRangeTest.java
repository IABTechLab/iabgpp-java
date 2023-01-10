package com.iab.gpp.encoder.datatype;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class EncodableOptimizedFixedRangeTest {

  private EncodableOptimizedFixedRange encodableOptimizedFixedRange = new EncodableOptimizedFixedRange();

  @Test
  public void testEncode1() throws EncodingException {
    encodableOptimizedFixedRange.setValue(Arrays.asList(12, 24, 48));
    Assertions.assertEquals("00000000001100000000000000001000000000001000000000000000000000001",
        encodableOptimizedFixedRange.encode());
  }

  @Test
  public void testEncode2() throws EncodingException {
    encodableOptimizedFixedRange.setValue(Arrays.asList(18, 30));
    Assertions.assertEquals("00000000000111100000000000000000001000000000001", encodableOptimizedFixedRange.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    encodableOptimizedFixedRange.decode("00000000001100000000000000001000000000001000000000000000000000001");
    Assertions.assertEquals(Arrays.asList(12, 24, 48), encodableOptimizedFixedRange.getValue());
  }

  @Test
  public void testDecode2() throws DecodingException {
    encodableOptimizedFixedRange.decode("00000000000111100000000000000000001000000000001");
    Assertions.assertEquals(Arrays.asList(18, 30), encodableOptimizedFixedRange.getValue());
  }
}
