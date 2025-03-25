package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsDeField;

public class UsDeTest {

  @Test
  public void testEncode1() {
    UsDe usDe = new UsDe();
    Assertions.assertEquals("BAAAAAABAA.QA", usDe.encode());
  }

  @Test
  public void testEncode2() {
    UsDe usDe = new UsDe();

    usDe.setFieldValue(UsDeField.PROCESSING_NOTICE, 1);
    usDe.setFieldValue(UsDeField.SALE_OPT_OUT_NOTICE, 1);
    usDe.setFieldValue(UsDeField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usDe.setFieldValue(UsDeField.SALE_OPT_OUT, 1);
    usDe.setFieldValue(UsDeField.TARGETED_ADVERTISING_OPT_OUT, 1);
    usDe.setFieldValue(UsDeField.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0));
    usDe.setFieldValue(UsDeField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(2, 1, 0, 2, 1));
    usDe.setFieldValue(UsDeField.ADDITIONAL_DATA_PROCESSING_CONSENT, 1);
    usDe.setFieldValue(UsDeField.MSPA_COVERED_TRANSACTION, 1);
    usDe.setFieldValue(UsDeField.MSPA_OPT_OUT_OPTION_MODE, 1);
    usDe.setFieldValue(UsDeField.MSPA_SERVICE_PROVIDER_MODE, 2);
    usDe.setFieldValue(UsDeField.GPC, true);

    Assertions.assertEquals("BVWSSSSVYA.YA", usDe.encode());
  }
  
  @Test
  public void testSetInvalidValues() {
    UsDe usDe = new UsDe();

    try {
      usDe.setFieldValue(UsDeField.PROCESSING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usDe.setFieldValue(UsDeField.SALE_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usDe.setFieldValue(UsDeField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usDe.setFieldValue(UsDeField.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usDe.setFieldValue(UsDeField.TARGETED_ADVERTISING_OPT_OUT, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usDe.setFieldValue(UsDeField.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 1, 2, 0, 1));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usDe.setFieldValue(UsDeField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(1, 2, 3));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usDe.setFieldValue(UsDeField.ADDITIONAL_DATA_PROCESSING_CONSENT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usDe.setFieldValue(UsDeField.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usDe.setFieldValue(UsDeField.MSPA_OPT_OUT_OPTION_MODE, 4);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usDe.setFieldValue(UsDeField.MSPA_SERVICE_PROVIDER_MODE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
  }

  @Test
  public void testEncodeWithGpcSegmentExcluded() {
    UsDe usDe = new UsDe();
    usDe.setFieldValue(UsDeField.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BAAAAAABAA", usDe.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsDe usDe = new UsDe("BVWSSSSVYA.YA");

    Assertions.assertEquals(1, usDe.getProcessingNotice());
    Assertions.assertEquals(1, usDe.getSaleOptOutNotice());
    Assertions.assertEquals(1, usDe.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usDe.getSaleOptOut());
    Assertions.assertEquals(1, usDe.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0), usDe.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1), usDe.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usDe.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usDe.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usDe.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usDe.getMspaServiceProviderMode());
    Assertions.assertEquals(true, usDe.getGpc());
  }

  @Test
  public void testDecodeWithGpcSegmentExcluded() throws DecodingException {
    UsDe usDe = new UsDe("BVWSSSSVYA");

    Assertions.assertEquals(1, usDe.getProcessingNotice());
    Assertions.assertEquals(1, usDe.getSaleOptOutNotice());
    Assertions.assertEquals(1, usDe.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usDe.getSaleOptOut());
    Assertions.assertEquals(1, usDe.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0), usDe.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1), usDe.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usDe.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usDe.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usDe.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usDe.getMspaServiceProviderMode());
    Assertions.assertEquals(false, usDe.getGpcSegmentIncluded());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsDe("z").getProcessingNotice();
    });
  }
}
