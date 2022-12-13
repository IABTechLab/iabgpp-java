package com.iab.gpp.encoder.section;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class HeaderV1Test {

  @Test
  public void testEncodeToBitString1() throws EncodingException {
    HeaderV1 headerV1 = new HeaderV1();
    headerV1.setFieldValue("SectionIds", new ArrayList<>());
    Assertions.assertEquals("000011000001000000000000", headerV1.encodeToBitString());
  }

  @Test
  public void testEncodeToBitString2() throws EncodingException {
    HeaderV1 headerV1 = new HeaderV1();
    headerV1.setFieldValue("SectionIds", Arrays.asList(2));
    Assertions.assertEquals("0000110000010000000000010011", headerV1.encodeToBitString());
  }

  @Test
  public void testEncodeToBitString3() throws EncodingException {
    HeaderV1 headerV1 = new HeaderV1();
    headerV1.setFieldValue("SectionIds", Arrays.asList(2, 6));
    Assertions.assertEquals("000011000001000000000010001101011", headerV1.encodeToBitString());
  }

  @Test
  public void testDecodeFromBitString1() throws DecodingException {
    HeaderV1 headerV1 = new HeaderV1();
    headerV1.decodeFromBitString("000011000001000000000000");
    Assertions.assertEquals(new ArrayList<>(), headerV1.getFieldValue("SectionIds"));
    Assertions.assertEquals(headerV1.getFieldValue("SectionIds"), headerV1.getSectionsIds());
  }

  @Test
  public void testDecodeFromBitString2() throws DecodingException {
    HeaderV1 headerV1 = new HeaderV1();
    headerV1.decodeFromBitString("0000110000010000000000010011");
    Assertions.assertEquals(Arrays.asList(2), headerV1.getFieldValue("SectionIds"));
    Assertions.assertEquals(headerV1.getFieldValue("SectionIds"), headerV1.getSectionsIds());
  }

  @Test
  public void testDecodeFromBitString3() throws DecodingException {
    HeaderV1 headerV1 = new HeaderV1();
    headerV1.decodeFromBitString("000001000011000000000010001101011");
    Assertions.assertEquals(Arrays.asList(2, 6), headerV1.getFieldValue("SectionIds"));
    Assertions.assertEquals(headerV1.getFieldValue("SectionIds"), headerV1.getSectionsIds());
  }

  @Test
  public void testEncode1() throws EncodingException {
    HeaderV1 headerV1 = new HeaderV1();
    headerV1.setFieldValue("SectionIds", new ArrayList<>());
    Assertions.assertEquals("DBAA", headerV1.encode());
  }

  @Test
  public void testEncode2() throws EncodingException {
    HeaderV1 headerV1 = new HeaderV1();
    headerV1.setFieldValue("SectionIds", Arrays.asList(2));
    Assertions.assertEquals("DBABMAAA", headerV1.encode());
  }

  @Test
  public void testEncode3() throws EncodingException {
    HeaderV1 headerV1 = new HeaderV1();
    headerV1.setFieldValue("SectionIds", Arrays.asList(2, 7));
    Assertions.assertEquals("DBACMMAA", headerV1.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    HeaderV1 headerV1 = new HeaderV1();
    headerV1.decode("DBAA");
    Assertions.assertEquals(new ArrayList<>(), headerV1.getFieldValue("SectionIds"));
    Assertions.assertEquals(headerV1.getFieldValue("Version"), headerV1.getVersion());
    Assertions.assertEquals(headerV1.getFieldValue("SectionIds"), headerV1.getSectionsIds());
  }

  @Test
  public void testDecode2() throws DecodingException {
    HeaderV1 headerV1 = new HeaderV1();
    headerV1.decode("DBABMAAA");
    Assertions.assertEquals(Arrays.asList(2), headerV1.getFieldValue("SectionIds"));
    Assertions.assertEquals(headerV1.getFieldValue("Version"), headerV1.getVersion());
    Assertions.assertEquals(headerV1.getFieldValue("SectionIds"), headerV1.getSectionsIds());
  }

  @Test
  public void testDecode3() throws DecodingException {
    HeaderV1 headerV1 = new HeaderV1();
    headerV1.decode("BDACNYAA");
    Assertions.assertEquals(Arrays.asList(2, 6), headerV1.getFieldValue("SectionIds"));
    Assertions.assertEquals(headerV1.getFieldValue("Version"), headerV1.getVersion());
    Assertions.assertEquals(headerV1.getFieldValue("SectionIds"), headerV1.getSectionsIds());
  }
}
