package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsMdField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UsMdTest {

  @Test
  public void testEncode1() {
    UsMd usMd = new UsMd();
    Assertions.assertEquals("BQAA.QA", usMd.encode());
  }

  @Test
  public void testEncode2() {
    UsMd usMd = new UsMd();

    usMd.setFieldValue(UsMdField.MSPA_COVERED_TRANSACTION, 1);
    usMd.setFieldValue(UsMdField.MSPA_MODE, 1);
    usMd.setFieldValue(UsMdField.PROCESSING_NOTICE, 1);
    usMd.setFieldValue(UsMdField.SALE_OPT_OUT_NOTICE, 1);
    usMd.setFieldValue(UsMdField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usMd.setFieldValue(UsMdField.SALE_OPT_OUT, 1);
    usMd.setFieldValue(UsMdField.TARGETED_ADVERTISING_OPT_OUT, 1);
    usMd.setFieldValue(UsMdField.ADDITIONAL_DATA_PROCESSING_CONSENT, 1);
    usMd.setFieldValue(UsMdField.GPC, true);

    Assertions.assertEquals("BVVU.YA", usMd.encode());
  }

  @Test
  public void testSetInvalidValues() {
    UsMd usMd = new UsMd();

    try {
      usMd.setFieldValue(UsMdField.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usMd.setFieldValue(UsMdField.MSPA_MODE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usMd.setFieldValue(UsMdField.PROCESSING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usMd.setFieldValue(UsMdField.ADDITIONAL_DATA_PROCESSING_CONSENT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }
  }

  @Test
  public void testEncodeWithGpcSegmentExcluded() {
    UsMd usMd = new UsMd();
    usMd.setFieldValue(UsMdField.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BQAA", usMd.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsMd usMd = new UsMd("BVVU.YA");

    Assertions.assertEquals(1, usMd.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usMd.getMspaMode());
    Assertions.assertEquals(1, usMd.getProcessingNotice());
    Assertions.assertEquals(1, usMd.getSaleOptOutNotice());
    Assertions.assertEquals(1, usMd.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usMd.getSaleOptOut());
    Assertions.assertEquals(1, usMd.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(1, usMd.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(true, usMd.getGpc());
  }

  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(
        DecodingException.class,
        () -> {
          new UsMd("z").getProcessingNotice();
        });
  }
}
