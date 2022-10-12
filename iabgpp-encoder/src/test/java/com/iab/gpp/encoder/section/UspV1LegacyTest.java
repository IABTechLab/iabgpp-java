package com.iab.gpp.encoder.section;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.EncodingException;

public class UspV1LegacyTest {

  @Test
  public void testEncode1() throws EncodingException {
    UspV1Legacy uspv1 = new UspV1Legacy();
    Assertions.assertEquals("1---", uspv1.encode());
  }

  @Test
  public void testEncode2() throws EncodingException {
    UspV1Legacy uspv1 = new UspV1Legacy();
    uspv1.setFieldValue("Notice", "Y");
    uspv1.setFieldValue("OptOutSale", "N");
    uspv1.setFieldValue("LspaCovered", "N");

    Assertions.assertEquals("1YNN", uspv1.encode());
  }

  @Test
  public void testEncode3() throws EncodingException {
    UspV1Legacy uspv1 = new UspV1Legacy();
    uspv1.setFieldValue("Version", 2);
    uspv1.setFieldValue("Notice", "N");
    uspv1.setFieldValue("OptOutSale", "Y");
    uspv1.setFieldValue("LspaCovered", "Y");

    Assertions.assertEquals("2NYY", uspv1.encode());
  }

  @Test
  public void testDecode1() throws EncodingException {
    UspV1Legacy uspv1 = new UspV1Legacy("1NYN");
    Assertions.assertEquals(1, uspv1.getFieldValue("Version"));
    Assertions.assertEquals("N", uspv1.getFieldValue("Notice"));
    Assertions.assertEquals("Y", uspv1.getFieldValue("OptOutSale"));
    Assertions.assertEquals("N", uspv1.getFieldValue("LspaCovered"));

    Assertions.assertEquals(uspv1.getFieldValue("Version"), uspv1.getVersion());
    Assertions.assertEquals(uspv1.getFieldValue("Notice"), uspv1.getNotice());
    Assertions.assertEquals(uspv1.getFieldValue("OptOutSale"), uspv1.getOptOutSale());
    Assertions.assertEquals(uspv1.getFieldValue("LspaCovered"), uspv1.getLspaCovered());
  }

  @Test
  public void testDecode2() throws EncodingException {
    UspV1Legacy uspv1 = new UspV1Legacy("2YNY");
    Assertions.assertEquals(2, uspv1.getFieldValue("Version"));
    Assertions.assertEquals("Y", uspv1.getFieldValue("Notice"));
    Assertions.assertEquals("N", uspv1.getFieldValue("OptOutSale"));
    Assertions.assertEquals("Y", uspv1.getFieldValue("LspaCovered"));

    Assertions.assertEquals(uspv1.getFieldValue("Version"), uspv1.getVersion());
    Assertions.assertEquals(uspv1.getFieldValue("Notice"), uspv1.getNotice());
    Assertions.assertEquals(uspv1.getFieldValue("OptOutSale"), uspv1.getOptOutSale());
    Assertions.assertEquals(uspv1.getFieldValue("LspaCovered"), uspv1.getLspaCovered());
  }

}
