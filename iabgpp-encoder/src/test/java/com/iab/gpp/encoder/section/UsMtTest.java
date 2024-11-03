package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsMtField;

public class UsMtTest {

  @Test
  public void testEncode1() {
    UsMt usMt = new UsMt();
    Assertions.assertEquals("BAAAAABA.QA", usMt.encode());
  }

  @Test
  public void testEncode2() {
    UsMt usMt = new UsMt();

    usMt.setFieldValue(UsMtField.SHARING_NOTICE, 1);
    usMt.setFieldValue(UsMtField.SALE_OPT_OUT_NOTICE, 1);
    usMt.setFieldValue(UsMtField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usMt.setFieldValue(UsMtField.SALE_OPT_OUT, 1);
    usMt.setFieldValue(UsMtField.TARGETED_ADVERTISING_OPT_OUT, 1);
    usMt.setFieldValue(UsMtField.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1));
    usMt.setFieldValue(UsMtField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(2, 1, 0));
    usMt.setFieldValue(UsMtField.ADDITIONAL_DATA_PROCESSING_CONSENT, 1);
    usMt.setFieldValue(UsMtField.MSPA_COVERED_TRANSACTION, 1);
    usMt.setFieldValue(UsMtField.MSPA_OPT_OUT_OPTION_MODE, 1);
    usMt.setFieldValue(UsMtField.MSPA_SERVICE_PROVIDER_MODE, 2);
    usMt.setFieldValue(UsMtField.GPC, true);

    Assertions.assertEquals("BVWSSZFY.YA", usMt.encode());
  }
  
  @Test
  public void testSetInvalidValues() {
    UsMt usMt = new UsMt();

    try {
      usMt.setFieldValue(UsMtField.SHARING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMt.setFieldValue(UsMtField.SALE_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMt.setFieldValue(UsMtField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMt.setFieldValue(UsMtField.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMt.setFieldValue(UsMtField.TARGETED_ADVERTISING_OPT_OUT, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMt.setFieldValue(UsMtField.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 1, 2, 0, 1));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMt.setFieldValue(UsMtField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(1, 2, 3));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMt.setFieldValue(UsMtField.ADDITIONAL_DATA_PROCESSING_CONSENT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMt.setFieldValue(UsMtField.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMt.setFieldValue(UsMtField.MSPA_OPT_OUT_OPTION_MODE, 4);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMt.setFieldValue(UsMtField.MSPA_SERVICE_PROVIDER_MODE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
  }

  @Test
  public void testEncodeWithGpcSegmentExcluded() {
    UsMt usMt = new UsMt();
    usMt.setFieldValue(UsMtField.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BAAAAABA", usMt.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsMt usMt = new UsMt("BVWSSZFY.YA");

    Assertions.assertEquals(1, usMt.getSharingNotice());
    Assertions.assertEquals(1, usMt.getSaleOptOutNotice());
    Assertions.assertEquals(1, usMt.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usMt.getSaleOptOut());
    Assertions.assertEquals(1, usMt.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usMt.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1, 0), usMt.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usMt.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usMt.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usMt.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usMt.getMspaServiceProviderMode());
    Assertions.assertEquals(true, usMt.getGpc());
  }

  @Test
  public void testDecodeWithGpcSegmentExcluded() throws DecodingException {
    UsMt usMt = new UsMt("BVWSSZFY");

    Assertions.assertEquals(1, usMt.getSharingNotice());
    Assertions.assertEquals(1, usMt.getSaleOptOutNotice());
    Assertions.assertEquals(1, usMt.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usMt.getSaleOptOut());
    Assertions.assertEquals(1, usMt.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usMt.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1, 0), usMt.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usMt.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usMt.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usMt.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usMt.getMspaServiceProviderMode());
    Assertions.assertEquals(false, usMt.getGpcSegmentIncluded());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsMt("z").getSharingNotice();
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
    UsMt usMt = new UsMt();
    usMt.setFieldValue(UsMtField.SALE_OPT_OUT_NOTICE, saleOptOutNotice);
    usMt.setFieldValue(UsMtField.SALE_OPT_OUT, saleOptOut);
    usMt.setFieldValue(UsMtField.MSPA_SERVICE_PROVIDER_MODE, mspaServiceProviderMode);
    usMt.setFieldValue(UsMtField.MSPA_OPT_OUT_OPTION_MODE, mspaOptOutOptionMode);

    if (valid) {
      try {
        usMt.encode();
      } catch (ValidationException e) {
        String msg = String.format(
            "Unexpected ValidationException. {saleOptOutNotice=%d, saleOptOut=%d, mspaServiceProviderMode=%d, mspaOptOutOptionMode=%d}, valid=%b",
            saleOptOutNotice, saleOptOut, mspaServiceProviderMode, mspaOptOutOptionMode, valid);
        Assertions.fail(msg, e);
      }
    } else {
      try {
        usMt.encode();
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
    UsMt usMt = new UsMt();
    usMt.setFieldValue(UsMtField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, targetedAdvertisingOptOutNotice);
    usMt.setFieldValue(UsMtField.TARGETED_ADVERTISING_OPT_OUT, targetedAdvertisingOptOut);

    if (valid) {
      try {
        usMt.encode();
      } catch (ValidationException e) {
        String msg = String.format(
            "Unexpected ValidationException. {targetedAdvertisingOptOutNotice=%d, targetedAdvertisingOptOut=%d, valid=%b",
            targetedAdvertisingOptOutNotice, targetedAdvertisingOptOut, valid);
        Assertions.fail(msg, e);
      }
    } else {
      try {
        usMt.encode();
        String msg = String.format(
            "Expected Validation. {targetedAdvertisingOptOutNotice=%d, targetedAdvertisingOptOut=%d, valid=%b",
            targetedAdvertisingOptOutNotice, targetedAdvertisingOptOut, valid);
        Assertions.fail(msg);
      } catch (ValidationException e) {

      }
    }
  }
}
