package com.iab.gpp.encoder.section;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class UspV1Test {

  @Test
  public void testEncode1() throws EncodingException {
    UspV1 uspv1 = new UspV1();
    uspv1.setFieldValue("Notice", 1);
    uspv1.setFieldValue("OptOutSale", 2);
    uspv1.setFieldValue("LspaCovered", 3);

    Assertions.assertEquals("000001011011", uspv1.encodeToBitString());
  }

  @Test
  public void testEncode2() throws EncodingException {
    UspV1 uspv1 = new UspV1();
    uspv1.setFieldValue("Notice", 1);
    uspv1.setFieldValue("OptOutSale", 2);
    uspv1.setFieldValue("LspaCovered", 3);

    Assertions.assertEquals("BbA", uspv1.encode());
  }

  @Test
  public void testdecode1() throws DecodingException {
    UspV1 uspv1 = new UspV1();
    uspv1.decodeFromBitString("000001011011");

    Assertions.assertEquals(1, uspv1.getFieldValue("Notice"));
    Assertions.assertEquals(2, uspv1.getFieldValue("OptOutSale"));
    Assertions.assertEquals(3, uspv1.getFieldValue("LspaCovered"));

    Assertions.assertEquals(uspv1.getFieldValue("Version"), uspv1.getVersion());
    Assertions.assertEquals(uspv1.getFieldValue("Notice"), uspv1.getNotice());
    Assertions.assertEquals(uspv1.getFieldValue("OptOutSale"), uspv1.getOptOutSale());
    Assertions.assertEquals(uspv1.getFieldValue("LspaCovered"), uspv1.getLspaCovered());
  }

  @Test
  public void testDecode2() throws DecodingException {
    UspV1 uspv1 = new UspV1("BbA");
    Assertions.assertEquals(1, uspv1.getFieldValue("Notice"));
    Assertions.assertEquals(2, uspv1.getFieldValue("OptOutSale"));
    Assertions.assertEquals(3, uspv1.getFieldValue("LspaCovered"));

    Assertions.assertEquals(uspv1.getFieldValue("Version"), uspv1.getVersion());
    Assertions.assertEquals(uspv1.getFieldValue("Notice"), uspv1.getNotice());
    Assertions.assertEquals(uspv1.getFieldValue("OptOutSale"), uspv1.getOptOutSale());
    Assertions.assertEquals(uspv1.getFieldValue("LspaCovered"), uspv1.getLspaCovered());
  }
}
