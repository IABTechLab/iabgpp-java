package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsVaField;

public class UsVaTest {

  @Test
  public void testEncode1() {
    UsVa usVa = new UsVa();
    Assertions.assertEquals("BAAAABA", usVa.encode());
  }

  @Test
  public void testEncode2() {
    UsVa usVa = new UsVa();

    try {
      usVa.setFieldValue(UsVaField.SHARING_NOTICE, 1);
      usVa.setFieldValue(UsVaField.SALE_OPT_OUT_NOTICE, 1);
      usVa.setFieldValue(UsVaField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
      usVa.setFieldValue(UsVaField.SALE_OPT_OUT, 1);
      usVa.setFieldValue(UsVaField.TARGETED_ADVERTISING_OPT_OUT, 1);
      usVa.setFieldValue(UsVaField.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1));
      usVa.setFieldValue(UsVaField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 1);
      usVa.setFieldValue(UsVaField.MSPA_COVERED_TRANSACTION, 1);
      usVa.setFieldValue(UsVaField.MSPA_OPT_OUT_OPTION_MODE, 1);
      usVa.setFieldValue(UsVaField.MSPA_SERVICE_PROVIDER_MODE, 2);
    } catch (InvalidFieldException e) {
      throw new EncodingException(e);
    }

    Assertions.assertEquals("BVWSSVY", usVa.encode());
  }

  @Test
  public void testSetInvalidValues() {
    UsVa usVa = new UsVa();

    try {
      usVa.setFieldValue(UsVaField.SHARING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVa.setFieldValue(UsVaField.SALE_OPT_OUT_NOTICE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVa.setFieldValue(UsVaField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 4);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVa.setFieldValue(UsVaField.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVa.setFieldValue(UsVaField.TARGETED_ADVERTISING_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVa.setFieldValue(UsVaField.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 1, 2, 0, 1));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVa.setFieldValue(UsVaField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVa.setFieldValue(UsVaField.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVa.setFieldValue(UsVaField.MSPA_OPT_OUT_OPTION_MODE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVa.setFieldValue(UsVaField.MSPA_SERVICE_PROVIDER_MODE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

  }

  @Test
  public void testDecode1() throws DecodingException {
    UsVa usVa = new UsVa("BVWSSVY");

    Assertions.assertEquals(1, usVa.getSharingNotice());
    Assertions.assertEquals(1, usVa.getSaleOptOutNotice());
    Assertions.assertEquals(1, usVa.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usVa.getSaleOptOut());
    Assertions.assertEquals(1, usVa.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usVa.getSensitiveDataProcessing());
    Assertions.assertEquals(1, usVa.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usVa.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usVa.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usVa.getMspaServiceProviderMode());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsVa("z").getMspaCoveredTransaction();
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
    UsVa usVa = new UsVa();
    usVa.setFieldValue(UsVaField.SALE_OPT_OUT_NOTICE, saleOptOutNotice);
    usVa.setFieldValue(UsVaField.SALE_OPT_OUT, saleOptOut);
    usVa.setFieldValue(UsVaField.MSPA_SERVICE_PROVIDER_MODE, mspaServiceProviderMode);
    usVa.setFieldValue(UsVaField.MSPA_OPT_OUT_OPTION_MODE, mspaOptOutOptionMode);

    if (valid) {
      try {
        usVa.encode();
      } catch (ValidationException e) {
        String msg = String.format(
            "Unexpected ValidationException. {saleOptOutNotice=%d, saleOptOut=%d, mspaServiceProviderMode=%d, mspaOptOutOptionMode=%d}, valid=%b",
            saleOptOutNotice, saleOptOut, mspaServiceProviderMode, mspaOptOutOptionMode, valid);
        Assertions.fail(msg, e);
      }
    } else {
      try {
        usVa.encode();
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
    UsVa usVa = new UsVa();
    usVa.setFieldValue(UsVaField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, targetedAdvertisingOptOutNotice);
    usVa.setFieldValue(UsVaField.TARGETED_ADVERTISING_OPT_OUT, targetedAdvertisingOptOut);

    if (valid) {
      try {
        usVa.encode();
      } catch (ValidationException e) {
        String msg = String.format(
            "Unexpected ValidationException. {targetedAdvertisingOptOutNotice=%d, targetedAdvertisingOptOut=%d, valid=%b",
            targetedAdvertisingOptOutNotice, targetedAdvertisingOptOut, valid);
        Assertions.fail(msg, e);
      }
    } else {
      try {
        usVa.encode();
        String msg = String.format(
            "Expected Validation. {targetedAdvertisingOptOutNotice=%d, targetedAdvertisingOptOut=%d, valid=%b",
            targetedAdvertisingOptOutNotice, targetedAdvertisingOptOut, valid);
        Assertions.fail(msg);
      } catch (ValidationException e) {

      }
    }
  }
}
