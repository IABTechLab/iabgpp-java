package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsKyField;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UsKyTest {

  @Test
  public void testEncode1() {
    UsKy usKy = new UsKy();
    Assertions.assertEquals("aBAA.AQAA", usKy.encode());
  }

  @Test
  public void testEncode2() {
    UsKy usKy = new UsKy();

    usKy.setFieldValue(UsKyField.PROCESSING_NOTICE, 1);
    usKy.setFieldValue(UsKyField.SALE_OPT_OUT_NOTICE, 1);
    usKy.setFieldValue(UsKyField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usKy.setFieldValue(UsKyField.SALE_OPT_OUT, 1);
    usKy.setFieldValue(UsKyField.TARGETED_ADVERTISING_OPT_OUT, 1);
    usKy.setFieldValue(UsKyField.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1));
    usKy.setFieldValue(UsKyField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 1);
    usKy.setFieldValue(UsKyField.ADDITIONAL_DATA_PROCESSING_CONSENT, 1);
    usKy.setFieldValue(UsKyField.MSPA_COVERED_TRANSACTION, 1);
    usKy.setFieldValue(UsKyField.MSPA_MODE, 1);
    usKy.setFieldValue(UsKyField.MSPA_VERSION, 1);

    Assertions.assertEquals("aBABYA.BVVV.kkk", usKy.encode());
  }

  @Test
  public void testSetInvalidValues() {
    UsKy usKy = new UsKy();

    try {
      usKy.setFieldValue(UsKyField.PROCESSING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usKy.setFieldValue(UsKyField.SALE_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usKy.setFieldValue(UsKyField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usKy.setFieldValue(UsKyField.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usKy.setFieldValue(UsKyField.TARGETED_ADVERTISING_OPT_OUT, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usKy.setFieldValue(
          UsKyField.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 1, 2, 0, 1));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usKy.setFieldValue(UsKyField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usKy.setFieldValue(UsKyField.ADDITIONAL_DATA_PROCESSING_CONSENT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usKy.setFieldValue(UsKyField.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usKy.setFieldValue(UsKyField.MSPA_MODE, 4);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsKy usKy = new UsKy("aBABYA.BVVV.kkk");

    Assertions.assertEquals(1, usKy.getVersion());
    Assertions.assertEquals(1, usKy.getProcessingNotice());
    Assertions.assertEquals(1, usKy.getSaleOptOutNotice());
    Assertions.assertEquals(1, usKy.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usKy.getSaleOptOut());
    Assertions.assertEquals(1, usKy.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(
        Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usKy.getSensitiveDataProcessing());
    Assertions.assertEquals(1, usKy.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usKy.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usKy.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usKy.getMspaMode());
    Assertions.assertEquals(1, usKy.getMspaVersion());
  }

  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(
        DecodingException.class,
        () -> {
          new UsKy("z").getProcessingNotice();
        });
  }
}
