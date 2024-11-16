package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsNhField;

public class UsNhTest {

  @Test
  public void testEncode1() {
    UsNh usNh = new UsNh();
    Assertions.assertEquals("BAAAAABA.QA", usNh.encode());
  }

  @Test
  public void testEncode2() {
    UsNh usNh = new UsNh();

    usNh.setFieldValue(UsNhField.PROCESSING_NOTICE, 1);
    usNh.setFieldValue(UsNhField.SALE_OPT_OUT_NOTICE, 1);
    usNh.setFieldValue(UsNhField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usNh.setFieldValue(UsNhField.SALE_OPT_OUT, 1);
    usNh.setFieldValue(UsNhField.TARGETED_ADVERTISING_OPT_OUT, 1);
    usNh.setFieldValue(UsNhField.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1));
    usNh.setFieldValue(UsNhField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(2, 1, 0));
    usNh.setFieldValue(UsNhField.ADDITIONAL_DATA_PROCESSING_CONSENT, 1);
    usNh.setFieldValue(UsNhField.MSPA_COVERED_TRANSACTION, 1);
    usNh.setFieldValue(UsNhField.MSPA_OPT_OUT_OPTION_MODE, 1);
    usNh.setFieldValue(UsNhField.MSPA_SERVICE_PROVIDER_MODE, 2);
    usNh.setFieldValue(UsNhField.GPC, true);

    Assertions.assertEquals("BVWSSZFY.YA", usNh.encode());
  }
  
  @Test
  public void testSetInvalidValues() {
    UsNh usNh = new UsNh();

    try {
      usNh.setFieldValue(UsNhField.PROCESSING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNh.setFieldValue(UsNhField.SALE_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNh.setFieldValue(UsNhField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNh.setFieldValue(UsNhField.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNh.setFieldValue(UsNhField.TARGETED_ADVERTISING_OPT_OUT, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNh.setFieldValue(UsNhField.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 1, 2, 0, 1));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNh.setFieldValue(UsNhField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(1, 2, 3));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNh.setFieldValue(UsNhField.ADDITIONAL_DATA_PROCESSING_CONSENT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNh.setFieldValue(UsNhField.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNh.setFieldValue(UsNhField.MSPA_OPT_OUT_OPTION_MODE, 4);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNh.setFieldValue(UsNhField.MSPA_SERVICE_PROVIDER_MODE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
  }

  @Test
  public void testEncodeWithGpcSegmentExcluded() {
    UsNh usNh = new UsNh();
    usNh.setFieldValue(UsNhField.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BAAAAABA", usNh.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsNh usNh = new UsNh("BVWSSZFY.YA");

    Assertions.assertEquals(1, usNh.getProcessingNotice());
    Assertions.assertEquals(1, usNh.getSaleOptOutNotice());
    Assertions.assertEquals(1, usNh.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usNh.getSaleOptOut());
    Assertions.assertEquals(1, usNh.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usNh.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1, 0), usNh.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usNh.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usNh.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usNh.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usNh.getMspaServiceProviderMode());
    Assertions.assertEquals(true, usNh.getGpc());
  }

  @Test
  public void testDecodeWithGpcSegmentExcluded() throws DecodingException {
    UsNh usNh = new UsNh("BVWSSZFY");

    Assertions.assertEquals(1, usNh.getProcessingNotice());
    Assertions.assertEquals(1, usNh.getSaleOptOutNotice());
    Assertions.assertEquals(1, usNh.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usNh.getSaleOptOut());
    Assertions.assertEquals(1, usNh.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usNh.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1, 0), usNh.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usNh.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usNh.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usNh.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usNh.getMspaServiceProviderMode());
    Assertions.assertEquals(false, usNh.getGpcSegmentIncluded());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsNh("z").getProcessingNotice();
    });
  }
}
