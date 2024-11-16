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
}
