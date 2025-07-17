package com.iab.gpp.encoder.datatype;

import java.util.Arrays;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class EncodableFixedIntegerRangeTest {

  @Test
  public void testSubstring1() throws DecodingException, SubstringException {
    Assertions.assertEquals("00000000001000000000000000011100000000000001010000000000001000",
        new EncodableFixedIntegerRange().substring(BitString.of("1000000000010000000000000000111000000000000010100000000000010001"),
            1).toString());
  }

  @Test
  public void testSubstring2() throws DecodingException, SubstringException {
    Assertions.assertEquals("00000000000100000000000011101", new EncodableFixedIntegerRange().substring(
    		BitString.of("000010001111010010000110111111111100000000001111010010000110111111111100000000000000000000000000000000000000000100001101000000000000000010000000000000000000000000000000000000000000000000000000000000000000000000000000000000001110110000000000010000000000001110100000000000000000000000000000"),
        230).toString());
  }

  @Test
  public void testEncode1() throws EncodingException {
    EncodableFixedIntegerRange encodableFixedIntegerRange = new EncodableFixedIntegerRange();
    encodableFixedIntegerRange.setValue(Arrays.asList(28));
    BitStringBuilder builder = new BitStringBuilder();
    encodableFixedIntegerRange.encode(builder);
    Assertions.assertEquals("00000000000100000000000011100", builder.build().toString());
  }

  @Test
  public void testEncode2() throws EncodingException {
    EncodableFixedIntegerRange encodableFixedIntegerRange = new EncodableFixedIntegerRange();
    encodableFixedIntegerRange.setValue(Arrays.asList(29));
    BitStringBuilder builder = new BitStringBuilder();
    encodableFixedIntegerRange.encode(builder);
    Assertions.assertEquals("00000000000100000000000011101", builder.build().toString());
  }

  @Test
  public void testDecode1() throws DecodingException {
    EncodableFixedIntegerRange encodableFixedIntegerRange = new EncodableFixedIntegerRange();
    encodableFixedIntegerRange.decode(BitString.of("00000000000100000000000011100"));
    Assertions.assertEquals(Set.of(28), encodableFixedIntegerRange.getValue());
  }

  @Test
  public void testDecode2() throws DecodingException {
    EncodableFixedIntegerRange encodableFixedIntegerRange = new EncodableFixedIntegerRange();
    encodableFixedIntegerRange.decode(BitString.of("00000000000100000000000011101"));
    Assertions.assertEquals(Set.of(29), encodableFixedIntegerRange.getValue());
  }

}
