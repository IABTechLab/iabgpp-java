package com.iab.gpp.encoder.datatype;

import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.UsNatField;

public class EncodableFixedIntegerRangeTest {

  @Test
  public void testEncode1() throws EncodingException {
    EncodableFixedIntegerRange<UsNatField> encodableFixedIntegerRange = new EncodableFixedIntegerRange<>("");
    IntegerSet integerSet = new IntegerSet();
    integerSet.add(28);
    BitString builder = new BitString();
    encodableFixedIntegerRange.encode(builder, integerSet, null);
    Assertions.assertEquals("00000000000100000000000011100", builder.toString());
  }

  @Test
  public void testEncode2() throws EncodingException {
    EncodableFixedIntegerRange<UsNatField> encodableFixedIntegerRange = new EncodableFixedIntegerRange<>("");
    IntegerSet integerSet = new IntegerSet();
    integerSet.add(29);
    BitString builder = new BitString();
    encodableFixedIntegerRange.encode(builder, integerSet, null);
    Assertions.assertEquals("00000000000100000000000011101", builder.toString());
  }

  @Test
  public void testDecode1() throws DecodingException {
    EncodableFixedIntegerRange<UsNatField> encodableFixedIntegerRange = new EncodableFixedIntegerRange<>("");
    Assertions.assertEquals(Set.of(28), encodableFixedIntegerRange.decode(BitString.of("00000000000100000000000011100"), null));
  }

  @Test
  public void testDecode2() throws DecodingException {
    EncodableFixedIntegerRange<UsNatField> encodableFixedIntegerRange = new EncodableFixedIntegerRange<>("");
    Assertions.assertEquals(Set.of(29), encodableFixedIntegerRange.decode(BitString.of("00000000000100000000000011101"), null));
  }

}
