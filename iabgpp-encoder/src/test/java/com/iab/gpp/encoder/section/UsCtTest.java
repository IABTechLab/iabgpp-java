package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsCtField;

public class UsCtTest {

  @Test
  public void testEncode1() {
    UsCt usCt = new UsCt();
    Assertions.assertEquals("BAAAAAEA.QA", usCt.encode());
  }

  @Test
  public void testEncode2() {
    UsCt usCt = new UsCt();

    usCt.setFieldValue(UsCtField.SHARING_NOTICE, 1);
    usCt.setFieldValue(UsCtField.SALE_OPT_OUT_NOTICE, 1);
    usCt.setFieldValue(UsCtField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usCt.setFieldValue(UsCtField.SALE_OPT_OUT, 1);
    usCt.setFieldValue(UsCtField.TARGETED_ADVERTISING_OPT_OUT, 1);
    usCt.setFieldValue(UsCtField.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1));
    usCt.setFieldValue(UsCtField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(2, 1, 0));
    usCt.setFieldValue(UsCtField.MSPA_COVERED_TRANSACTION, 1);
    usCt.setFieldValue(UsCtField.MSPA_OPT_OUT_OPTION_MODE, 1);
    usCt.setFieldValue(UsCtField.MSPA_SERVICE_PROVIDER_MODE, 2);
    usCt.setFieldValue(UsCtField.GPC, true);

    Assertions.assertEquals("BVWSSZFg.YA", usCt.encode());
  }
  
  @Test
  public void testSetInvalidValues() {
    UsCt usCt = new UsCt();

    try {
      usCt.setFieldValue(UsCtField.SHARING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCt.setFieldValue(UsCtField.SALE_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCt.setFieldValue(UsCtField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCt.setFieldValue(UsCtField.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCt.setFieldValue(UsCtField.TARGETED_ADVERTISING_OPT_OUT, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCt.setFieldValue(UsCtField.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 1, 2, 0, 1));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCt.setFieldValue(UsCtField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(1, 2, 3));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCt.setFieldValue(UsCtField.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCt.setFieldValue(UsCtField.MSPA_OPT_OUT_OPTION_MODE, 4);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCt.setFieldValue(UsCtField.MSPA_SERVICE_PROVIDER_MODE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
  }

  @Test
  public void testEncodeWithGpcSegmentExcluded() {
    UsCt usCt = new UsCt();
    usCt.setFieldValue(UsCtField.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BAAAAAEA", usCt.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsCt usCt = new UsCt("BVWSSZFg.YA");

    Assertions.assertEquals(1, usCt.getSharingNotice());
    Assertions.assertEquals(1, usCt.getSaleOptOutNotice());
    Assertions.assertEquals(1, usCt.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usCt.getSaleOptOut());
    Assertions.assertEquals(1, usCt.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usCt.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1, 0), usCt.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usCt.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usCt.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usCt.getMspaServiceProviderMode());
    Assertions.assertEquals(true, usCt.getGpc());
  }

  @Test
  public void testDecodeWithGpcSegmentExcluded() throws DecodingException {
    UsCt usCt = new UsCt("BVWSSZFg");

    Assertions.assertEquals(1, usCt.getSharingNotice());
    Assertions.assertEquals(1, usCt.getSaleOptOutNotice());
    Assertions.assertEquals(1, usCt.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usCt.getSaleOptOut());
    Assertions.assertEquals(1, usCt.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usCt.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1, 0), usCt.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usCt.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usCt.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usCt.getMspaServiceProviderMode());
    Assertions.assertEquals(false, usCt.getGpcSegmentIncluded());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsCt("z").getSharingNotice();
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
    UsCt usCt = new UsCt();
    usCt.setFieldValue(UsCtField.SALE_OPT_OUT_NOTICE, saleOptOutNotice);
    usCt.setFieldValue(UsCtField.SALE_OPT_OUT, saleOptOut);
    usCt.setFieldValue(UsCtField.MSPA_SERVICE_PROVIDER_MODE, mspaServiceProviderMode);
    usCt.setFieldValue(UsCtField.MSPA_OPT_OUT_OPTION_MODE, mspaOptOutOptionMode);

    if (valid) {
      try {
        usCt.encode();
      } catch (ValidationException e) {
        String msg = String.format(
            "Unexpected ValidationException. {saleOptOutNotice=%d, saleOptOut=%d, mspaServiceProviderMode=%d, mspaOptOutOptionMode=%d}, valid=%b",
            saleOptOutNotice, saleOptOut, mspaServiceProviderMode, mspaOptOutOptionMode, valid);
        Assertions.fail(msg, e);
      }
    } else {
      try {
        usCt.encode();
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
    UsCt usCt = new UsCt();
    usCt.setFieldValue(UsCtField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, targetedAdvertisingOptOutNotice);
    usCt.setFieldValue(UsCtField.TARGETED_ADVERTISING_OPT_OUT, targetedAdvertisingOptOut);

    if (valid) {
      try {
        usCt.encode();
      } catch (ValidationException e) {
        String msg = String.format(
            "Unexpected ValidationException. {targetedAdvertisingOptOutNotice=%d, targetedAdvertisingOptOut=%d, valid=%b",
            targetedAdvertisingOptOutNotice, targetedAdvertisingOptOut, valid);
        Assertions.fail(msg, e);
      }
    } else {
      try {
        usCt.encode();
        String msg = String.format(
            "Expected Validation. {targetedAdvertisingOptOutNotice=%d, targetedAdvertisingOptOut=%d, valid=%b",
            targetedAdvertisingOptOutNotice, targetedAdvertisingOptOut, valid);
        Assertions.fail(msg);
      } catch (ValidationException e) {

      }
    }
  }
}
