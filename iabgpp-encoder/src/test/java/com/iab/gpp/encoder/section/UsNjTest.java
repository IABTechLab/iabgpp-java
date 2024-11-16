package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsNjField;

public class UsNjTest {

  @Test
  public void testEncode1() {
    UsNj usNj = new UsNj();
    Assertions.assertEquals("BAAAAAAAQA.QA", usNj.encode());
  }

  @Test
  public void testEncode2() {
    UsNj usNj = new UsNj();

    usNj.setFieldValue(UsNjField.PROCESSING_NOTICE, 1);
    usNj.setFieldValue(UsNjField.SALE_OPT_OUT_NOTICE, 1);
    usNj.setFieldValue(UsNjField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usNj.setFieldValue(UsNjField.SALE_OPT_OUT, 1);
    usNj.setFieldValue(UsNjField.TARGETED_ADVERTISING_OPT_OUT, 1);
    usNj.setFieldValue(UsNjField.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0, 1));
    usNj.setFieldValue(UsNjField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(2, 1, 0, 2, 1));
    usNj.setFieldValue(UsNjField.ADDITIONAL_DATA_PROCESSING_CONSENT, 1);
    usNj.setFieldValue(UsNjField.MSPA_COVERED_TRANSACTION, 1);
    usNj.setFieldValue(UsNjField.MSPA_OPT_OUT_OPTION_MODE, 1);
    usNj.setFieldValue(UsNjField.MSPA_SERVICE_PROVIDER_MODE, 2);
    usNj.setFieldValue(UsNjField.GPC, true);

    Assertions.assertEquals("BVWSSRklWA.YA", usNj.encode());
  }
  
  @Test
  public void testSetInvalidValues() {
    UsNj usNj = new UsNj();

    try {
      usNj.setFieldValue(UsNjField.PROCESSING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNj.setFieldValue(UsNjField.SALE_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNj.setFieldValue(UsNjField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNj.setFieldValue(UsNjField.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNj.setFieldValue(UsNjField.TARGETED_ADVERTISING_OPT_OUT, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNj.setFieldValue(UsNjField.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 1, 2, 0, 1));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNj.setFieldValue(UsNjField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(1, 2, 3));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNj.setFieldValue(UsNjField.ADDITIONAL_DATA_PROCESSING_CONSENT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNj.setFieldValue(UsNjField.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNj.setFieldValue(UsNjField.MSPA_OPT_OUT_OPTION_MODE, 4);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNj.setFieldValue(UsNjField.MSPA_SERVICE_PROVIDER_MODE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
  }

  @Test
  public void testEncodeWithGpcSegmentExcluded() {
    UsNj usNj = new UsNj();
    usNj.setFieldValue(UsNjField.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BAAAAAAAQA", usNj.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsNj usNj = new UsNj("BVWSSRklWA.YA");

    Assertions.assertEquals(1, usNj.getProcessingNotice());
    Assertions.assertEquals(1, usNj.getSaleOptOutNotice());
    Assertions.assertEquals(1, usNj.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usNj.getSaleOptOut());
    Assertions.assertEquals(1, usNj.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0, 1), usNj.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1), usNj.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usNj.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usNj.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usNj.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usNj.getMspaServiceProviderMode());
    Assertions.assertEquals(true, usNj.getGpc());
  }

  @Test
  public void testDecodeWithGpcSegmentExcluded() throws DecodingException {
    UsNj usNj = new UsNj("BVWSSRklWA");

    Assertions.assertEquals(1, usNj.getProcessingNotice());
    Assertions.assertEquals(1, usNj.getSaleOptOutNotice());
    Assertions.assertEquals(1, usNj.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usNj.getSaleOptOut());
    Assertions.assertEquals(1, usNj.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0, 1), usNj.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1), usNj.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usNj.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usNj.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usNj.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usNj.getMspaServiceProviderMode());
    Assertions.assertEquals(false, usNj.getGpcSegmentIncluded());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsNj("z").getProcessingNotice();
    });
  }
}
