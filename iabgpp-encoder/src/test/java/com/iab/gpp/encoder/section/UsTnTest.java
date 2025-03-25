package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsTnField;

public class UsTnTest {

  @Test
  public void testEncode1() {
    UsTn usTn = new UsTn();
    Assertions.assertEquals("BAAAAAQA.QA", usTn.encode());
  }

  @Test
  public void testEncode2() {
    UsTn usTn = new UsTn();

    usTn.setFieldValue(UsTnField.PROCESSING_NOTICE, 1);
    usTn.setFieldValue(UsTnField.SALE_OPT_OUT_NOTICE, 1);
    usTn.setFieldValue(UsTnField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usTn.setFieldValue(UsTnField.SALE_OPT_OUT, 1);
    usTn.setFieldValue(UsTnField.TARGETED_ADVERTISING_OPT_OUT, 1);
    usTn.setFieldValue(UsTnField.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1));
    usTn.setFieldValue(UsTnField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 1);
    usTn.setFieldValue(UsTnField.ADDITIONAL_DATA_PROCESSING_CONSENT, 1);
    usTn.setFieldValue(UsTnField.MSPA_COVERED_TRANSACTION, 1);
    usTn.setFieldValue(UsTnField.MSPA_OPT_OUT_OPTION_MODE, 1);
    usTn.setFieldValue(UsTnField.MSPA_SERVICE_PROVIDER_MODE, 2);
    usTn.setFieldValue(UsTnField.GPC, true);

    Assertions.assertEquals("BVWSSVWA.YA", usTn.encode());
  }
  
  @Test
  public void testSetInvalidValues() {
    UsTn usTn = new UsTn();

    try {
      usTn.setFieldValue(UsTnField.PROCESSING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTn.setFieldValue(UsTnField.SALE_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTn.setFieldValue(UsTnField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTn.setFieldValue(UsTnField.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTn.setFieldValue(UsTnField.TARGETED_ADVERTISING_OPT_OUT, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTn.setFieldValue(UsTnField.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 1, 2, 0, 1));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTn.setFieldValue(UsTnField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTn.setFieldValue(UsTnField.ADDITIONAL_DATA_PROCESSING_CONSENT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTn.setFieldValue(UsTnField.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTn.setFieldValue(UsTnField.MSPA_OPT_OUT_OPTION_MODE, 4);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTn.setFieldValue(UsTnField.MSPA_SERVICE_PROVIDER_MODE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
  }

  @Test
  public void testEncodeWithGpcSegmentExcluded() {
    UsTn usTn = new UsTn();
    usTn.setFieldValue(UsTnField.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BAAAAAQA", usTn.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsTn usTn = new UsTn("BVWSSVWA.YA");

    Assertions.assertEquals(1, usTn.getProcessingNotice());
    Assertions.assertEquals(1, usTn.getSaleOptOutNotice());
    Assertions.assertEquals(1, usTn.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usTn.getSaleOptOut());
    Assertions.assertEquals(1, usTn.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usTn.getSensitiveDataProcessing());
    Assertions.assertEquals(1, usTn.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usTn.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usTn.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usTn.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usTn.getMspaServiceProviderMode());
    Assertions.assertEquals(true, usTn.getGpc());
  }

  @Test
  public void testDecodeWithGpcSegmentExcluded() throws DecodingException {
    UsTn usTn = new UsTn("BVWSSVWA");

    Assertions.assertEquals(1, usTn.getProcessingNotice());
    Assertions.assertEquals(1, usTn.getSaleOptOutNotice());
    Assertions.assertEquals(1, usTn.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usTn.getSaleOptOut());
    Assertions.assertEquals(1, usTn.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usTn.getSensitiveDataProcessing());
    Assertions.assertEquals(1, usTn.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usTn.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usTn.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usTn.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usTn.getMspaServiceProviderMode());
    Assertions.assertEquals(false, usTn.getGpcSegmentIncluded());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsTn("z").getProcessingNotice();
    });
  }
}
