package com.iab.gpp.encoder.section;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.field.UspV1Field;

public class UspV1Test {

  @Test
  public void testEncode1() {
    UspV1 uspv1 = new UspV1();
    Assertions.assertEquals("1---", uspv1.encode());
  }

  @Test
  public void testEncode2() {
    UspV1 uspv1 = new UspV1();
    uspv1.setFieldValue(UspV1Field.NOTICE, 'Y');
    uspv1.setFieldValue(UspV1Field.OPT_OUT_SALE, 'N');
    uspv1.setFieldValue(UspV1Field.LSPA_COVERED, 'N');

    Assertions.assertEquals("1YNN", uspv1.encode());
  }

  @Test
  public void testEncode3() {
    UspV1 uspv1 = new UspV1();
    uspv1.setFieldValue(UspV1Field.VERSION, 2);
    uspv1.setFieldValue(UspV1Field.NOTICE, 'N');
    uspv1.setFieldValue(UspV1Field.OPT_OUT_SALE, 'Y');
    uspv1.setFieldValue(UspV1Field.LSPA_COVERED, 'Y');

    Assertions.assertEquals("2NYY", uspv1.encode());
  }

  @Test
  public void testDecode1() throws DecodingException, InvalidFieldException {
    UspV1 uspv1 = new UspV1("1NYN");
    Assertions.assertEquals(1, uspv1.getFieldValue(UspV1Field.VERSION));
    Assertions.assertEquals('N', uspv1.getFieldValue(UspV1Field.NOTICE));
    Assertions.assertEquals('Y', uspv1.getFieldValue(UspV1Field.OPT_OUT_SALE));
    Assertions.assertEquals('N', uspv1.getFieldValue(UspV1Field.LSPA_COVERED));

    Assertions.assertEquals(uspv1.getFieldValue(UspV1Field.VERSION), uspv1.getVersion());
    Assertions.assertEquals(uspv1.getFieldValue(UspV1Field.NOTICE), uspv1.getNotice());
    Assertions.assertEquals(uspv1.getFieldValue(UspV1Field.OPT_OUT_SALE), uspv1.getOptOutSale());
    Assertions.assertEquals(uspv1.getFieldValue(UspV1Field.LSPA_COVERED), uspv1.getLspaCovered());
  }

  @Test
  public void testDecode2() throws DecodingException, InvalidFieldException {
    UspV1 uspv1 = new UspV1("1YNY");
    Assertions.assertEquals(1, uspv1.getFieldValue(UspV1Field.VERSION));
    Assertions.assertEquals('Y', uspv1.getFieldValue(UspV1Field.NOTICE));
    Assertions.assertEquals('N', uspv1.getFieldValue(UspV1Field.OPT_OUT_SALE));
    Assertions.assertEquals('Y', uspv1.getFieldValue(UspV1Field.LSPA_COVERED));

    Assertions.assertEquals(uspv1.getFieldValue(UspV1Field.VERSION), uspv1.getVersion());
    Assertions.assertEquals(uspv1.getFieldValue(UspV1Field.NOTICE), uspv1.getNotice());
    Assertions.assertEquals(uspv1.getFieldValue(UspV1Field.OPT_OUT_SALE), uspv1.getOptOutSale());
    Assertions.assertEquals(uspv1.getFieldValue(UspV1Field.LSPA_COVERED), uspv1.getLspaCovered());
  }

}
