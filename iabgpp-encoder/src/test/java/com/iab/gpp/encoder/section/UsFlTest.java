package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsFlField;

public class UsFlTest {

  @Test
  public void testEncode1() {
    UsFl usFl = new UsFl();
    Assertions.assertEquals("BAAAAABA", usFl.encode());
  }

  @Test
  public void testEncode2() {
    UsFl usFl = new UsFl();

    try {
      usFl.setFieldValue(UsFlField.PROCESSING_NOTICE, 1);
      usFl.setFieldValue(UsFlField.SALE_OPT_OUT_NOTICE, 1);
      usFl.setFieldValue(UsFlField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
      usFl.setFieldValue(UsFlField.SALE_OPT_OUT, 1);
      usFl.setFieldValue(UsFlField.TARGETED_ADVERTISING_OPT_OUT, 1);
      usFl.setFieldValue(UsFlField.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1));
      usFl.setFieldValue(UsFlField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(2, 1, 2));
      usFl.setFieldValue(UsFlField.ADDITIONAL_DATA_PROCESSING_CONSENT, 1);
      usFl.setFieldValue(UsFlField.MSPA_COVERED_TRANSACTION, 1);
      usFl.setFieldValue(UsFlField.MSPA_OPT_OUT_OPTION_MODE, 1);
      usFl.setFieldValue(UsFlField.MSPA_SERVICE_PROVIDER_MODE, 2);
    } catch (InvalidFieldException e) {
      throw new EncodingException(e);
    }

    Assertions.assertEquals("BVWSSZlY", usFl.encode());
  }

  @Test
  public void testSetInvalidValues() {
    UsFl usFl = new UsFl();

    try {
      usFl.setFieldValue(UsFlField.PROCESSING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFl.setFieldValue(UsFlField.SALE_OPT_OUT_NOTICE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFl.setFieldValue(UsFlField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 4);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFl.setFieldValue(UsFlField.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFl.setFieldValue(UsFlField.TARGETED_ADVERTISING_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFl.setFieldValue(UsFlField.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 1, 2, 0, 1));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFl.setFieldValue(UsFlField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(0, 3, 0));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFl.setFieldValue(UsFlField.ADDITIONAL_DATA_PROCESSING_CONSENT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFl.setFieldValue(UsFlField.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFl.setFieldValue(UsFlField.MSPA_OPT_OUT_OPTION_MODE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFl.setFieldValue(UsFlField.MSPA_SERVICE_PROVIDER_MODE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

  }

  @Test
  public void testDecode1() throws DecodingException {
    UsFl usFl = new UsFl("BVWSSZlY");

    Assertions.assertEquals(1, usFl.getProcessingNotice());
    Assertions.assertEquals(1, usFl.getSaleOptOutNotice());
    Assertions.assertEquals(1, usFl.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usFl.getSaleOptOut());
    Assertions.assertEquals(1, usFl.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usFl.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1, 2), usFl.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usFl.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usFl.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usFl.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usFl.getMspaServiceProviderMode());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsFl("z").getMspaCoveredTransaction();
    });
  }
}
