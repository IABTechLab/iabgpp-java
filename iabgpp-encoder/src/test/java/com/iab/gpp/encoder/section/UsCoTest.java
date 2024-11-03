package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsCoField;

public class UsCoTest {

  @Test
  public void testEncode1() {
    UsCo usCo = new UsCo();
    Assertions.assertEquals("BAAAAEA.QA", usCo.encode());
  }

  @Test
  public void testEncode2() {
    UsCo usCo = new UsCo();

    usCo.setFieldValue(UsCoField.SHARING_NOTICE, 1);
    usCo.setFieldValue(UsCoField.SALE_OPT_OUT_NOTICE, 1);
    usCo.setFieldValue(UsCoField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usCo.setFieldValue(UsCoField.SALE_OPT_OUT, 1);
    usCo.setFieldValue(UsCoField.TARGETED_ADVERTISING_OPT_OUT, 1);
    usCo.setFieldValue(UsCoField.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2));
    usCo.setFieldValue(UsCoField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 1);
    usCo.setFieldValue(UsCoField.MSPA_COVERED_TRANSACTION, 1);
    usCo.setFieldValue(UsCoField.MSPA_OPT_OUT_OPTION_MODE, 1);
    usCo.setFieldValue(UsCoField.MSPA_SERVICE_PROVIDER_MODE, 2);
    usCo.setFieldValue(UsCoField.GPC, true);

    Assertions.assertEquals("BVWSSVg.YA", usCo.encode());
  }

  @Test
  public void testSetInvalidValues() {
    UsCo usCo = new UsCo();

    try {
      usCo.setFieldValue(UsCoField.SHARING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usCo.setFieldValue(UsCoField.SALE_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usCo.setFieldValue(UsCoField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usCo.setFieldValue(UsCoField.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usCo.setFieldValue(UsCoField.TARGETED_ADVERTISING_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usCo.setFieldValue(UsCoField.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 0, 1, 2));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usCo.setFieldValue(UsCoField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usCo.setFieldValue(UsCoField.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usCo.setFieldValue(UsCoField.MSPA_OPT_OUT_OPTION_MODE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usCo.setFieldValue(UsCoField.MSPA_SERVICE_PROVIDER_MODE, 5);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

  }

  @Test
  public void testEncodeWithGpcSegmentExcluded() {
    UsCo usCo = new UsCo();
    usCo.setFieldValue(UsCoField.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BAAAAEA", usCo.encode());
  }

  @Test
  public void testDecode1() {
    UsCo usCo = new UsCo("BVWSSVg.YA");

    Assertions.assertEquals(1, usCo.getSharingNotice());
    Assertions.assertEquals(1, usCo.getSaleOptOutNotice());
    Assertions.assertEquals(1, usCo.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usCo.getSaleOptOut());
    Assertions.assertEquals(1, usCo.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2), usCo.getSensitiveDataProcessing());
    Assertions.assertEquals(1, usCo.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usCo.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usCo.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usCo.getMspaServiceProviderMode());
    Assertions.assertEquals(true, usCo.getGpc());
  }

  @Test
  public void testDecodeWithGpcSegmentExcluded() {
    UsCo usCo = new UsCo("BVWSSVg");

    Assertions.assertEquals(1, usCo.getSharingNotice());
    Assertions.assertEquals(1, usCo.getSaleOptOutNotice());
    Assertions.assertEquals(1, usCo.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usCo.getSaleOptOut());
    Assertions.assertEquals(1, usCo.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2), usCo.getSensitiveDataProcessing());
    Assertions.assertEquals(1, usCo.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usCo.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usCo.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usCo.getMspaServiceProviderMode());
    Assertions.assertEquals(false, usCo.getGpcSegmentIncluded());
  }

  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsCo("z").getTargetedAdvertisingOptOut();
    });
  }

  @Test
  public void testValidate() {
    testValidate(0, 0, 0, 0, true);
    testValidate(0, 0, 0, 1, true);
    testValidate(0, 0, 0, 2, true);
    testValidate(0, 0, 1, 0, false);
    testValidate(0, 0, 1, 1, false);
    testValidate(0, 0, 1, 2, true);
    testValidate(0, 0, 2, 0, false);
    testValidate(0, 0, 2, 1, true);
    testValidate(0, 0, 2, 2, false);
    testValidate(0, 1, 0, 0, false);
    testValidate(0, 1, 0, 1, false);
    testValidate(0, 1, 0, 2, false);
    testValidate(0, 1, 1, 0, false);
    testValidate(0, 1, 1, 1, false);
    testValidate(0, 1, 1, 2, false);
    testValidate(0, 1, 2, 0, false);
    testValidate(0, 1, 2, 1, false);
    testValidate(0, 1, 2, 2, false);
    testValidate(0, 2, 0, 0, false);
    testValidate(0, 2, 0, 1, false);
    testValidate(0, 2, 0, 2, false);
    testValidate(0, 2, 1, 0, false);
    testValidate(0, 2, 1, 1, false);
    testValidate(0, 2, 1, 2, false);
    testValidate(0, 2, 2, 0, false);
    testValidate(0, 2, 2, 1, false);
    testValidate(0, 2, 2, 2, false);
    testValidate(1, 0, 0, 0, false);
    testValidate(1, 0, 0, 1, false);
    testValidate(1, 0, 0, 2, false);
    testValidate(1, 0, 1, 0, false);
    testValidate(1, 0, 1, 1, false);
    testValidate(1, 0, 1, 2, false);
    testValidate(1, 0, 2, 0, false);
    testValidate(1, 0, 2, 1, false);
    testValidate(1, 0, 2, 2, false);
    testValidate(1, 1, 0, 0, false);
    testValidate(1, 1, 0, 1, false);
    testValidate(1, 1, 0, 2, false);
    testValidate(1, 1, 1, 0, false);
    testValidate(1, 1, 1, 1, false);
    testValidate(1, 1, 1, 2, false);
    testValidate(1, 1, 2, 0, false);
    testValidate(1, 1, 2, 1, true);
    testValidate(1, 1, 2, 2, false);
    testValidate(1, 2, 0, 0, false);
    testValidate(1, 2, 0, 1, false);
    testValidate(1, 2, 0, 2, false);
    testValidate(1, 2, 1, 0, false);
    testValidate(1, 2, 1, 1, false);
    testValidate(1, 2, 1, 2, false);
    testValidate(1, 2, 2, 0, false);
    testValidate(1, 2, 2, 1, true);
    testValidate(1, 2, 2, 2, false);
    testValidate(2, 0, 0, 0, false);
    testValidate(2, 0, 0, 1, false);
    testValidate(2, 0, 0, 2, false);
    testValidate(2, 0, 1, 0, false);
    testValidate(2, 0, 1, 1, false);
    testValidate(2, 0, 1, 2, false);
    testValidate(2, 0, 2, 0, false);
    testValidate(2, 0, 2, 1, false);
    testValidate(2, 0, 2, 2, false);
    testValidate(2, 1, 0, 0, false);
    testValidate(2, 1, 0, 1, false);
    testValidate(2, 1, 0, 2, false);
    testValidate(2, 1, 1, 0, false);
    testValidate(2, 1, 1, 1, false);
    testValidate(2, 1, 1, 2, false);
    testValidate(2, 1, 2, 0, false);
    testValidate(2, 1, 2, 1, true);
    testValidate(2, 1, 2, 2, false);
    testValidate(2, 2, 0, 0, false);
    testValidate(2, 2, 0, 1, false);
    testValidate(2, 2, 0, 2, false);
    testValidate(2, 2, 1, 0, false);
    testValidate(2, 2, 1, 1, false);
    testValidate(2, 2, 1, 2, false);
    testValidate(2, 2, 2, 0, false);
    testValidate(2, 2, 2, 1, false);
    testValidate(2, 2, 2, 2, false);
  }

  private void testValidate(int saleOptOutNotice, int saleOptOut, int mspaServiceProviderMode, int mspaOptOutOptionMode,
      boolean valid) {
    UsCo usCo = new UsCo();
    usCo.setFieldValue(UsCoField.SALE_OPT_OUT_NOTICE, saleOptOutNotice);
    usCo.setFieldValue(UsCoField.SALE_OPT_OUT, saleOptOut);
    usCo.setFieldValue(UsCoField.MSPA_SERVICE_PROVIDER_MODE, mspaServiceProviderMode);
    usCo.setFieldValue(UsCoField.MSPA_OPT_OUT_OPTION_MODE, mspaOptOutOptionMode);

    if (valid) {
      try {
        usCo.encode();
      } catch (ValidationException e) {
        String msg = String.format(
            "Unexpected ValidationException. {saleOptOutNotice=%d, saleOptOut=%d, mspaServiceProviderMode=%d, mspaOptOutOptionMode=%d}, valid=%b",
            saleOptOutNotice, saleOptOut, mspaServiceProviderMode, mspaOptOutOptionMode, valid);
        Assertions.fail(msg, e);
      }
    } else {
      try {
        usCo.encode();
        String msg = String.format(
            "Expected Validation. {saleOptOutNotice=%d, saleOptOut=%d, mspaServiceProviderMode=%d, mspaOptOutOptionMode=%d}, valid=%b",
            saleOptOutNotice, saleOptOut, mspaServiceProviderMode, mspaOptOutOptionMode, valid);
        Assertions.fail(msg);
      } catch (ValidationException e) {

      }
    }
  }

  @Test
  public void testValidateTargetedAdvertising() {
    testValidateTargetedAdvertising(0, 0, true);
    testValidateTargetedAdvertising(0, 1, false);
    testValidateTargetedAdvertising(0, 2, false);
    testValidateTargetedAdvertising(1, 0, false);
    testValidateTargetedAdvertising(1, 1, true);
    testValidateTargetedAdvertising(1, 2, true);
    testValidateTargetedAdvertising(2, 0, false);
    testValidateTargetedAdvertising(2, 1, true);
    testValidateTargetedAdvertising(2, 2, false);
  }

  private void testValidateTargetedAdvertising(int targetedAdvertisingOptOutNotice, int targetedAdvertisingOptOut,
      boolean valid) {
    UsCo usCo = new UsCo();
    usCo.setFieldValue(UsCoField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, targetedAdvertisingOptOutNotice);
    usCo.setFieldValue(UsCoField.TARGETED_ADVERTISING_OPT_OUT, targetedAdvertisingOptOut);

    if (valid) {
      try {
        usCo.encode();
      } catch (ValidationException e) {
        String msg = String.format(
            "Unexpected ValidationException. {targetedAdvertisingOptOutNotice=%d, targetedAdvertisingOptOut=%d, valid=%b",
            targetedAdvertisingOptOutNotice, targetedAdvertisingOptOut, valid);
        Assertions.fail(msg, e);
      }
    } else {
      try {
        usCo.encode();
        String msg = String.format(
            "Expected Validation. {targetedAdvertisingOptOutNotice=%d, targetedAdvertisingOptOut=%d, valid=%b",
            targetedAdvertisingOptOutNotice, targetedAdvertisingOptOut, valid);
        Assertions.fail(msg);
      } catch (ValidationException e) {

      }
    }
  }



}
