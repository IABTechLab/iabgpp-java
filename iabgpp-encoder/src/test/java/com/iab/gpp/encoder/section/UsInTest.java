package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsInField;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UsInTest {

  @Test
  public void testEncode1() {
    UsIn usIn = new UsIn();
    Assertions.assertEquals("ZBAA.AQAA", usIn.encode());
  }

  @Test
  public void testEncode2() {
    UsIn usIn = new UsIn();

    usIn.setFieldValue(UsInField.PROCESSING_NOTICE, 1);
    usIn.setFieldValue(UsInField.SALE_OPT_OUT_NOTICE, 1);
    usIn.setFieldValue(UsInField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usIn.setFieldValue(UsInField.SALE_OPT_OUT, 1);
    usIn.setFieldValue(UsInField.TARGETED_ADVERTISING_OPT_OUT, 1);
    usIn.setFieldValue(UsInField.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1));
    usIn.setFieldValue(UsInField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 1);
    usIn.setFieldValue(UsInField.ADDITIONAL_DATA_PROCESSING_CONSENT, 1);
    usIn.setFieldValue(UsInField.MSPA_COVERED_TRANSACTION, 1);
    usIn.setFieldValue(UsInField.MSPA_MODE, 1);
    usIn.setFieldValue(UsInField.MSPA_VERSION, 1);

    Assertions.assertEquals("ZBABYA.BVVV.kkk", usIn.encode());
  }

  @Test
  public void testSetInvalidValues() {
    UsIn usIn = new UsIn();

    try {
      usIn.setFieldValue(UsInField.PROCESSING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usIn.setFieldValue(UsInField.SALE_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usIn.setFieldValue(UsInField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usIn.setFieldValue(UsInField.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usIn.setFieldValue(UsInField.TARGETED_ADVERTISING_OPT_OUT, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usIn.setFieldValue(
          UsInField.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 1, 2, 0, 1));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usIn.setFieldValue(UsInField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usIn.setFieldValue(UsInField.ADDITIONAL_DATA_PROCESSING_CONSENT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usIn.setFieldValue(UsInField.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usIn.setFieldValue(UsInField.MSPA_MODE, 4);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsIn usIn = new UsIn("ZBABYA.BVVV.kkk");

    Assertions.assertEquals(1, usIn.getVersion());
    Assertions.assertEquals(1, usIn.getProcessingNotice());
    Assertions.assertEquals(1, usIn.getSaleOptOutNotice());
    Assertions.assertEquals(1, usIn.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usIn.getSaleOptOut());
    Assertions.assertEquals(1, usIn.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(
        Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usIn.getSensitiveDataProcessing());
    Assertions.assertEquals(1, usIn.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usIn.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usIn.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usIn.getMspaMode());
    Assertions.assertEquals(1, usIn.getMspaVersion());
  }

  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(
        DecodingException.class,
        () -> {
          new UsIn("z").getProcessingNotice();
        });
  }
}
