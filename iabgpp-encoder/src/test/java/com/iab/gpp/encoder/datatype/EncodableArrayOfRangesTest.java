package com.iab.gpp.encoder.datatype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class EncodableArrayOfRangesTest {

  @Test
  public void testEncode1() throws EncodingException {
    List<RangeEntry> value = new ArrayList<>();
    EncodableArrayOfRanges encodableArrayOfRanges = new EncodableArrayOfRanges(6, 2, value);
    Assertions.assertEquals("000000000000",
        encodableArrayOfRanges.encode());
  }
  
  @Test
  public void testEncode2() throws EncodingException {
    List<RangeEntry> value = new ArrayList<>();
    value.add(new RangeEntry(5, 2, Arrays.asList(12, 24, 48)));
    EncodableArrayOfRanges encodableArrayOfRanges = new EncodableArrayOfRanges(6, 2, value);
    Assertions.assertEquals("0000000000010001011000000000001100000000000000001000000000001000000000000000000000001",
        encodableArrayOfRanges.encode());
  }
  
  @Test
  public void testEncode3() throws EncodingException {
    List<RangeEntry> value = new ArrayList<>();
    value.add(new RangeEntry(5, 2, Arrays.asList(12, 24, 48)));
    value.add(new RangeEntry(5, 2, Arrays.asList(18, 30)));
    EncodableArrayOfRanges encodableArrayOfRanges = new EncodableArrayOfRanges(6, 2, value);
    Assertions.assertEquals("00000000001000010110000000000011000000000000000010000000000010000000000000000000000010001011000000000000111100000000000000000001000000000001",
        encodableArrayOfRanges.encode());
  }
  
  @Test
  public void testEncode4() throws EncodingException {
    List<RangeEntry> value = new ArrayList<>();
    value.add(new RangeEntry(5, 2, Arrays.asList(12, 24, 48)));
    value.add(new RangeEntry(5, 2, Arrays.asList(18, 30)));
    value.add(new RangeEntry(5, 2, Arrays.asList(28)));
    EncodableArrayOfRanges encodableArrayOfRanges = new EncodableArrayOfRanges(6, 2, value);
    Assertions.assertEquals("0000000000110001011000000000001100000000000000001000000000001000000000000000000000001000101100000000000011110000000000000000000100000000000100010110000000000001110000000000000000000000000000001",
        encodableArrayOfRanges.encode());
  }
  
  @Test
  public void testEncode5() throws EncodingException {
    List<RangeEntry> value = new ArrayList<>();
    value.add(new RangeEntry(5, 2, Arrays.asList(12, 24, 48)));
    value.add(new RangeEntry(5, 2, Arrays.asList(18, 30)));
    value.add(new RangeEntry(5, 2, Arrays.asList(28)));
    value.add(new RangeEntry(5, 2, Arrays.asList(29)));
    EncodableArrayOfRanges encodableArrayOfRanges = new EncodableArrayOfRanges(6, 2, value);
    Assertions.assertEquals("0000000001000001011000000000001100000000000000001000000000001000000000000000000000001000101100000000000011110000000000000000000100000000000100010110000000000001110000000000000000000000000000001000101100000000000011101100000000000100000000000011101",
        encodableArrayOfRanges.encode());
  }
  
  @Test
  public void testDecode1() throws DecodingException {
    EncodableArrayOfRanges encodableArrayOfRanges = new EncodableArrayOfRanges(6, 2);
    encodableArrayOfRanges.decode("000000000000");
    Assertions.assertTrue(encodableArrayOfRanges.getValue().isEmpty());
  }
  
  @Test
  public void testDecode2() throws DecodingException {
    EncodableArrayOfRanges encodableArrayOfRanges = new EncodableArrayOfRanges(6, 2);
    encodableArrayOfRanges.decode("0000000000010001011000000000001100000000000000001000000000001000000000000000000000001");
    Assertions.assertEquals(1, encodableArrayOfRanges.getValue().size());
    Assertions.assertEquals(5, encodableArrayOfRanges.getValue().get(0).getKey());
    Assertions.assertEquals(2, encodableArrayOfRanges.getValue().get(0).getType());
    Assertions.assertEquals(Arrays.asList(12, 24, 48), encodableArrayOfRanges.getValue().get(0).getIds());
  }
  
  @Test
  public void testDecode3() throws DecodingException {
    EncodableArrayOfRanges encodableArrayOfRanges = new EncodableArrayOfRanges(6, 2);
    encodableArrayOfRanges.decode("00000000001000010110000000000011000000000000000010000000000010000000000000000000000010001011000000000000111100000000000000000001000000000001");
    Assertions.assertEquals(2, encodableArrayOfRanges.getValue().size());
    Assertions.assertEquals(5, encodableArrayOfRanges.getValue().get(0).getKey());
    Assertions.assertEquals(2, encodableArrayOfRanges.getValue().get(0).getType());
    Assertions.assertEquals(encodableArrayOfRanges.getValue().get(0).getIds(), Arrays.asList(12, 24, 48));
    Assertions.assertEquals(5, encodableArrayOfRanges.getValue().get(1).getKey());
    Assertions.assertEquals(2, encodableArrayOfRanges.getValue().get(1).getType());
   Assertions.assertEquals(encodableArrayOfRanges.getValue().get(1).getIds(), Arrays.asList(18, 30));
  }
  
  @Test
  public void testDecode4() throws DecodingException {
    EncodableArrayOfRanges encodableArrayOfRanges = new EncodableArrayOfRanges(6, 2);
    encodableArrayOfRanges.decode("0000000000110001011000000000001100000000000000001000000000001000000000000000000000001000101100000000000011110000000000000000000100000000000100010110000000000001110000000000000000000000000000001");
    Assertions.assertEquals(3, encodableArrayOfRanges.getValue().size());
    Assertions.assertEquals(5, encodableArrayOfRanges.getValue().get(0).getKey());
    Assertions.assertEquals(2, encodableArrayOfRanges.getValue().get(0).getType());
    Assertions.assertEquals(encodableArrayOfRanges.getValue().get(0).getIds(), Arrays.asList(12, 24, 48));
    Assertions.assertEquals(5, encodableArrayOfRanges.getValue().get(1).getKey());
    Assertions.assertEquals(2, encodableArrayOfRanges.getValue().get(1).getType());
    Assertions.assertEquals(encodableArrayOfRanges.getValue().get(1).getIds(), Arrays.asList(18, 30));
    Assertions.assertEquals(5, encodableArrayOfRanges.getValue().get(2).getKey());
    Assertions.assertEquals(2, encodableArrayOfRanges.getValue().get(2).getType());
    Assertions.assertEquals(encodableArrayOfRanges.getValue().get(2).getIds(), Arrays.asList(28));
  }
  
  @Test
  public void testDecode5() throws DecodingException {
    EncodableArrayOfRanges encodableArrayOfRanges = new EncodableArrayOfRanges(6, 2);
    encodableArrayOfRanges.decode("0000000001000001011000000000001100000000000000001000000000001000000000000000000000001000101100000000000011110000000000000000000100000000000100010110000000000001110000000000000000000000000000001000101100000000000011101100000000000100000000000011101");
    Assertions.assertEquals(4, encodableArrayOfRanges.getValue().size());
    Assertions.assertEquals(5, encodableArrayOfRanges.getValue().get(0).getKey());
    Assertions.assertEquals(2, encodableArrayOfRanges.getValue().get(0).getType());
    Assertions.assertEquals(Arrays.asList(12, 24, 48), encodableArrayOfRanges.getValue().get(0).getIds());
    Assertions.assertEquals(5, encodableArrayOfRanges.getValue().get(1).getKey());
    Assertions.assertEquals(2, encodableArrayOfRanges.getValue().get(1).getType());
    Assertions.assertEquals(Arrays.asList(18, 30), encodableArrayOfRanges.getValue().get(1).getIds());
    Assertions.assertEquals(5, encodableArrayOfRanges.getValue().get(2).getKey());
    Assertions.assertEquals(2, encodableArrayOfRanges.getValue().get(2).getType());
    Assertions.assertEquals(Arrays.asList(28), encodableArrayOfRanges.getValue().get(2).getIds());
    Assertions.assertEquals(5, encodableArrayOfRanges.getValue().get(3).getKey());
    Assertions.assertEquals(2, encodableArrayOfRanges.getValue().get(3).getType());
    Assertions.assertEquals(Arrays.asList(29), encodableArrayOfRanges.getValue().get(3).getIds());
  }
  
  @Test
  public void testSubstring1() throws DecodingException {
    Assertions.assertEquals("000000000000", new EncodableArrayOfRanges(6, 2).substring(
        "01010100000000000011111111000000000001110000000000000000000000000000001000000000000000000000000000000", 6));
  }

  @Test
  public void testSubstring2() throws DecodingException {
    Assertions.assertEquals("00000000000111111111000000000001110000000000000000000000000000001",
        new EncodableArrayOfRanges(6, 2).substring(
            "01010100000000000111111111000000000001110000000000000000000000000000001000000000000000000000000000000",
            6));
  }

  @Test
  public void testSubstring3() throws DecodingException {
    Assertions.assertEquals("000000000001111111110000000000011101100000000000100000000000011101",
        new EncodableArrayOfRanges(6, 2).substring(
            "01010100000000000111111111000000000001110110000000000010000000000001110100000000000000000000000000000",
            6));
  }

  @Test
  public void testSubstring4() throws DecodingException {
    Assertions.assertEquals(
        "0000000001000001011100000000001100000000000000001000000000001000000000000000000000001000101110000000000011110000000000000000000100000000000100010111000000000001110000000000000000000000000000001000101110000000000011101100000000000100000000000011101",
        new EncodableArrayOfRanges(6, 2).substring(
            "0101010000000001000001011100000000001100000000000000001000000000001000000000000000000000001000101110000000000011110000000000000000000100000000000100010111000000000001110000000000000000000000000000001000101110000000000011101100000000000100000000000011101010101",
            6));
  }
}
