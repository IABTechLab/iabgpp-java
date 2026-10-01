package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsRiField;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UsRiTest {

  @Test
  public void testEncode1() {
    UsRi usRi = new UsRi();
    Assertions.assertEquals("bBAA.AQAA", usRi.encode());
  }

  @Test
  public void testEncode2() {
    UsRi usRi = new UsRi();

    usRi.setFieldValue(UsRiField.PROCESSING_NOTICE, 1);
    usRi.setFieldValue(UsRiField.SALE_OPT_OUT_NOTICE, 1);
    usRi.setFieldValue(UsRiField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usRi.setFieldValue(UsRiField.SALE_OPT_OUT, 1);
    usRi.setFieldValue(UsRiField.TARGETED_ADVERTISING_OPT_OUT, 1);
    usRi.setFieldValue(UsRiField.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1));
    usRi.setFieldValue(UsRiField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 1);
    usRi.setFieldValue(UsRiField.ADDITIONAL_DATA_PROCESSING_CONSENT, 1);
    usRi.setFieldValue(UsRiField.MSPA_COVERED_TRANSACTION, 1);
    usRi.setFieldValue(UsRiField.MSPA_MODE, 1);
    usRi.setFieldValue(UsRiField.MSPA_VERSION, 1);

    Assertions.assertEquals("bBABYA.BVVV.kkk", usRi.encode());
  }

  @Test
  public void testSetInvalidValues() {
    UsRi usRi = new UsRi();

    try {
      usRi.setFieldValue(UsRiField.PROCESSING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usRi.setFieldValue(UsRiField.SALE_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usRi.setFieldValue(UsRiField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usRi.setFieldValue(UsRiField.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usRi.setFieldValue(UsRiField.TARGETED_ADVERTISING_OPT_OUT, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usRi.setFieldValue(
          UsRiField.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 1, 2, 0, 1));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usRi.setFieldValue(UsRiField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usRi.setFieldValue(UsRiField.ADDITIONAL_DATA_PROCESSING_CONSENT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usRi.setFieldValue(UsRiField.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usRi.setFieldValue(UsRiField.MSPA_MODE, 4);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsRi usRi = new UsRi("bBABYA.BVVV.kkk");

    Assertions.assertEquals(1, usRi.getVersion());
    Assertions.assertEquals(1, usRi.getProcessingNotice());
    Assertions.assertEquals(1, usRi.getSaleOptOutNotice());
    Assertions.assertEquals(1, usRi.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usRi.getSaleOptOut());
    Assertions.assertEquals(1, usRi.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(
        Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usRi.getSensitiveDataProcessing());
    Assertions.assertEquals(1, usRi.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usRi.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usRi.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usRi.getMspaMode());
    Assertions.assertEquals(1, usRi.getMspaVersion());
  }

  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(
        DecodingException.class,
        () -> {
          new UsRi("z").getProcessingNotice();
        });
  }
}
