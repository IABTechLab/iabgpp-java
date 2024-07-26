package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsTxV1Field;

public class UsTxV1Test {

  @Test
  public void testEncode1() {
    UsTxV1 usTxV1 = new UsTxV1();
    Assertions.assertEquals("BAAAAAQA.QA", usTxV1.encode());
  }

  @Test
  public void testEncode2() {
    UsTxV1 usTxV1 = new UsTxV1();

    usTxV1.setFieldValue(UsTxV1Field.PROCESSING_NOTICE, 1);
    usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT_NOTICE, 1);
    usTxV1.setFieldValue(UsTxV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT, 1);
    usTxV1.setFieldValue(UsTxV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
    usTxV1.setFieldValue(UsTxV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1));
    usTxV1.setFieldValue(UsTxV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 1);
    usTxV1.setFieldValue(UsTxV1Field.ADDITIONAL_DATA_PROCESSING_CONSENT, 1);
    usTxV1.setFieldValue(UsTxV1Field.MSPA_COVERED_TRANSACTION, 1);
    usTxV1.setFieldValue(UsTxV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usTxV1.setFieldValue(UsTxV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usTxV1.setFieldValue(UsTxV1Field.GPC, true);

    Assertions.assertEquals("BVWSSVWA.YA", usTxV1.encode());
  }
  
  @Test
  public void testSetInvalidValues() {
    UsTxV1 usTxV1 = new UsTxV1();

    try {
      usTxV1.setFieldValue(UsTxV1Field.PROCESSING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.TARGETED_ADVERTISING_OPT_OUT, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 1, 2, 0, 1));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.ADDITIONAL_DATA_PROCESSING_CONSENT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.MSPA_OPT_OUT_OPTION_MODE, 4);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.MSPA_SERVICE_PROVIDER_MODE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
  }

  @Test
  public void testValidateSale() {
    UsTxV1 usTxV1 = new UsTxV1();
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT_NOTICE, 0);
      usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT, 1);
      usTxV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT_NOTICE, 0);
      usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT, 2);
      usTxV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT_NOTICE, 1);
      usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT, 0);
      usTxV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT_NOTICE, 2);
      usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT, 0);
      usTxV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT_NOTICE, 2);
      usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT, 2);
      usTxV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }

    usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT_NOTICE, 0);
    usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT, 0);
    usTxV1.encode();
    
    usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT_NOTICE, 1);
    usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT, 1);
    usTxV1.encode();
    
    usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT_NOTICE, 1);
    usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT, 2);
    usTxV1.encode();
    
    usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT_NOTICE, 2);
    usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT, 1);
    usTxV1.encode();
  }
  
  @Test
  public void testValidateTargetedAdvertising() {
    UsTxV1 usTxV1 = new UsTxV1();
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
      usTxV1.setFieldValue(UsTxV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
      usTxV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
      usTxV1.setFieldValue(UsTxV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
      usTxV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
      usTxV1.setFieldValue(UsTxV1Field.TARGETED_ADVERTISING_OPT_OUT, 0);
      usTxV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
      usTxV1.setFieldValue(UsTxV1Field.TARGETED_ADVERTISING_OPT_OUT, 0);
      usTxV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
      usTxV1.setFieldValue(UsTxV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
      usTxV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }

    usTxV1.setFieldValue(UsTxV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
    usTxV1.setFieldValue(UsTxV1Field.TARGETED_ADVERTISING_OPT_OUT, 0);
    usTxV1.encode();
    
    usTxV1.setFieldValue(UsTxV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usTxV1.setFieldValue(UsTxV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
    usTxV1.encode();
    
    usTxV1.setFieldValue(UsTxV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usTxV1.setFieldValue(UsTxV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
    usTxV1.encode();
    
    usTxV1.setFieldValue(UsTxV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
    usTxV1.setFieldValue(UsTxV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
    usTxV1.encode();
  }
  
  @Test
  public void testValidateMspaServiceProviderMode() {
    UsTxV1 usTxV1 = new UsTxV1();
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usTxV1.setFieldValue(UsTxV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
      usTxV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usTxV1.setFieldValue(UsTxV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
      usTxV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
      usTxV1.setFieldValue(UsTxV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
      usTxV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
      usTxV1.setFieldValue(UsTxV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
      usTxV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
      usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT_NOTICE, 1);
      usTxV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
      usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT_NOTICE, 2);
      usTxV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    

    try {
      usTxV1.setFieldValue(UsTxV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT_NOTICE, 1);
      usTxV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTxV1.setFieldValue(UsTxV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT_NOTICE, 2);
      usTxV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    
    
    usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT_NOTICE, 0);
    
    usTxV1.setFieldValue(UsTxV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usTxV1.setFieldValue(UsTxV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
    usTxV1.encode();
    
    usTxV1.setFieldValue(UsTxV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usTxV1.setFieldValue(UsTxV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usTxV1.encode();
    
    usTxV1.setFieldValue(UsTxV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usTxV1.setFieldValue(UsTxV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    usTxV1.encode();
    
    usTxV1.setFieldValue(UsTxV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
    usTxV1.setFieldValue(UsTxV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    usTxV1.encode();
    
    usTxV1.setFieldValue(UsTxV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usTxV1.setFieldValue(UsTxV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usTxV1.encode();
    
    
    usTxV1.setFieldValue(UsTxV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
    
    usTxV1.setFieldValue(UsTxV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT_NOTICE, 0);
    usTxV1.encode();
    
    usTxV1.setFieldValue(UsTxV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
    usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT_NOTICE, 0);
    usTxV1.encode();
    
    usTxV1.setFieldValue(UsTxV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT_NOTICE, 0);
    usTxV1.encode();
    
    usTxV1.setFieldValue(UsTxV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT_NOTICE, 1);
    usTxV1.encode();
    
    usTxV1.setFieldValue(UsTxV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usTxV1.setFieldValue(UsTxV1Field.SALE_OPT_OUT_NOTICE, 2);
    usTxV1.encode();
  }
  
  @Test
  public void testEncodeWithGpcSegmentExcluded() {
    UsTxV1 usTxV1 = new UsTxV1();
    usTxV1.setFieldValue(UsTxV1Field.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BAAAAAQA", usTxV1.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsTxV1 usTxV1 = new UsTxV1("BVWSSVWA.YA");

    Assertions.assertEquals(1, usTxV1.getProcessingNotice());
    Assertions.assertEquals(1, usTxV1.getSaleOptOutNotice());
    Assertions.assertEquals(1, usTxV1.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usTxV1.getSaleOptOut());
    Assertions.assertEquals(1, usTxV1.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usTxV1.getSensitiveDataProcessing());
    Assertions.assertEquals(1, usTxV1.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usTxV1.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usTxV1.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usTxV1.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usTxV1.getMspaServiceProviderMode());
    Assertions.assertEquals(true, usTxV1.getGpc());
  }

  @Test
  public void testDecodeWithGpcSegmentExcluded() throws DecodingException {
    UsTxV1 usTxV1 = new UsTxV1("BVWSSVWA");

    Assertions.assertEquals(1, usTxV1.getProcessingNotice());
    Assertions.assertEquals(1, usTxV1.getSaleOptOutNotice());
    Assertions.assertEquals(1, usTxV1.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usTxV1.getSaleOptOut());
    Assertions.assertEquals(1, usTxV1.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usTxV1.getSensitiveDataProcessing());
    Assertions.assertEquals(1, usTxV1.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usTxV1.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usTxV1.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usTxV1.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usTxV1.getMspaServiceProviderMode());
    Assertions.assertEquals(false, usTxV1.getGpcSegmentIncluded());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsTxV1("z").getProcessingNotice();
    });
  }
}
