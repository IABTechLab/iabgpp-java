package com.iab.gpp.encoder.section;


import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsMnField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class UsMnTest {

  @Test
  public void testEncode1() {
    UsMn usMn = new UsMn();
    Assertions.assertEquals("BAAAAAABAA.QA", usMn.encode());
  }

  @Test
  public void testEncode2() {
    UsMn usMn = new UsMn();

    usMn.setFieldValue(UsMnField.PROCESSING_NOTICE, 1);
    usMn.setFieldValue(UsMnField.SALE_OPT_OUT_NOTICE, 1);
    usMn.setFieldValue(UsMnField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usMn.setFieldValue(UsMnField.SALE_OPT_OUT, 1);
    usMn.setFieldValue(UsMnField.TARGETED_ADVERTISING_OPT_OUT, 1);
    usMn.setFieldValue(UsMnField.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0));
    usMn.setFieldValue(UsMnField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(2, 1, 0, 2, 1));
    usMn.setFieldValue(UsMnField.ADDITIONAL_DATA_PROCESSING_CONSENT, 1);
    usMn.setFieldValue(UsMnField.MSPA_COVERED_TRANSACTION, 1);
    usMn.setFieldValue(UsMnField.MSPA_OPT_OUT_OPTION_MODE, 1);
    usMn.setFieldValue(UsMnField.MSPA_SERVICE_PROVIDER_MODE, 2);
    usMn.setFieldValue(UsMnField.GPC, true);

    Assertions.assertEquals("BVWSSSSVYA.YA", usMn.encode());
  }
  
  @Test
  public void testSetInvalidValues() {
    UsMn usMn = new UsMn();

    try {
      usMn.setFieldValue(UsMnField.PROCESSING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMn.setFieldValue(UsMnField.SALE_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMn.setFieldValue(UsMnField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMn.setFieldValue(UsMnField.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMn.setFieldValue(UsMnField.TARGETED_ADVERTISING_OPT_OUT, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMn.setFieldValue(UsMnField.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 1, 2, 0, 1));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMn.setFieldValue(UsMnField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(1, 2, 3));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMn.setFieldValue(UsMnField.ADDITIONAL_DATA_PROCESSING_CONSENT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMn.setFieldValue(UsMnField.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMn.setFieldValue(UsMnField.MSPA_OPT_OUT_OPTION_MODE, 4);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMn.setFieldValue(UsMnField.MSPA_SERVICE_PROVIDER_MODE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
  }

  @Test
  public void testEncodeWithGpcSegmentExcluded() {
    UsMn usMn = new UsMn();
    usMn.setFieldValue(UsMnField.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BAAAAAABAA", usMn.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsMn usMn = new UsMn("BVWSSSSVYA.YA");

    Assertions.assertEquals(1, usMn.getProcessingNotice());
    Assertions.assertEquals(1, usMn.getSaleOptOutNotice());
    Assertions.assertEquals(1, usMn.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usMn.getSaleOptOut());
    Assertions.assertEquals(1, usMn.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0), usMn.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1), usMn.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usMn.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usMn.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usMn.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usMn.getMspaServiceProviderMode());
    Assertions.assertEquals(true, usMn.getGpc());
  }

  @Test
  public void testDecodeWithGpcSegmentExcluded() throws DecodingException {
    UsMn usMn = new UsMn("BVWSSSSVYA");

    Assertions.assertEquals(1, usMn.getProcessingNotice());
    Assertions.assertEquals(1, usMn.getSaleOptOutNotice());
    Assertions.assertEquals(1, usMn.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usMn.getSaleOptOut());
    Assertions.assertEquals(1, usMn.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0), usMn.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1), usMn.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usMn.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usMn.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usMn.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usMn.getMspaServiceProviderMode());
    Assertions.assertEquals(false, usMn.getGpcSegmentIncluded());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsMn("z").getProcessingNotice();
    });
  }
}
