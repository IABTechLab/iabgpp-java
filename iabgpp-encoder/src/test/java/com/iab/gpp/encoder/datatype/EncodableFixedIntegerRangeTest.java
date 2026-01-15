package com.iab.gpp.encoder.datatype;

import java.util.Arrays;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.bitstring.BitStringReader;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class EncodableFixedIntegerRangeTest {

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
    encodableFixedIntegerRange.decode(new BitStringReader(BitString.of("00000000000100000000000011100")));
    Assertions.assertEquals(Set.of(28), encodableFixedIntegerRange.getValue());
  }

  @Test
  public void testDecode2() throws DecodingException {
    EncodableFixedIntegerRange encodableFixedIntegerRange = new EncodableFixedIntegerRange();
    encodableFixedIntegerRange.decode(new BitStringReader(BitString.of("00000000000100000000000011101")));
    Assertions.assertEquals(Set.of(29), encodableFixedIntegerRange.getValue());
  }

}
