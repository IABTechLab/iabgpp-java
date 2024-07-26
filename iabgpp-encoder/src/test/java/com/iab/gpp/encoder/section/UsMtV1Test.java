package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsMtV1Field;

public class UsMtV1Test {

  @Test
  public void testEncode1() {
    UsMtV1 usMtV1 = new UsMtV1();
    Assertions.assertEquals("BAAAAABA.QA", usMtV1.encode());
  }

  @Test
  public void testEncode2() {
    UsMtV1 usMtV1 = new UsMtV1();

    usMtV1.setFieldValue(UsMtV1Field.SHARING_NOTICE, 1);
    usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT_NOTICE, 1);
    usMtV1.setFieldValue(UsMtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT, 1);
    usMtV1.setFieldValue(UsMtV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
    usMtV1.setFieldValue(UsMtV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1));
    usMtV1.setFieldValue(UsMtV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(2, 1, 0));
    usMtV1.setFieldValue(UsMtV1Field.ADDITIONAL_DATA_PROCESSING_CONSENT, 1);
    usMtV1.setFieldValue(UsMtV1Field.MSPA_COVERED_TRANSACTION, 1);
    usMtV1.setFieldValue(UsMtV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usMtV1.setFieldValue(UsMtV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usMtV1.setFieldValue(UsMtV1Field.GPC, true);

    Assertions.assertEquals("BVWSSZFY.YA", usMtV1.encode());
  }
  
  @Test
  public void testSetInvalidValues() {
    UsMtV1 usMtV1 = new UsMtV1();

    try {
      usMtV1.setFieldValue(UsMtV1Field.SHARING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.TARGETED_ADVERTISING_OPT_OUT, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 1, 2, 0, 1));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(1, 2, 3));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.ADDITIONAL_DATA_PROCESSING_CONSENT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.MSPA_OPT_OUT_OPTION_MODE, 4);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.MSPA_SERVICE_PROVIDER_MODE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
  }

  @Test
  public void testValidateSale() {
    UsMtV1 usMtV1 = new UsMtV1();
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT_NOTICE, 0);
      usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT, 1);
      usMtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT_NOTICE, 0);
      usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT, 2);
      usMtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT_NOTICE, 1);
      usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT, 0);
      usMtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT_NOTICE, 2);
      usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT, 0);
      usMtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT_NOTICE, 2);
      usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT, 2);
      usMtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }

    usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT_NOTICE, 0);
    usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT, 0);
    usMtV1.encode();
    
    usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT_NOTICE, 1);
    usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT, 1);
    usMtV1.encode();
    
    usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT_NOTICE, 1);
    usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT, 2);
    usMtV1.encode();
    
    usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT_NOTICE, 2);
    usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT, 1);
    usMtV1.encode();
  }
  
  @Test
  public void testValidateTargetedAdvertising() {
    UsMtV1 usMtV1 = new UsMtV1();
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
      usMtV1.setFieldValue(UsMtV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
      usMtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
      usMtV1.setFieldValue(UsMtV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
      usMtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
      usMtV1.setFieldValue(UsMtV1Field.TARGETED_ADVERTISING_OPT_OUT, 0);
      usMtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
      usMtV1.setFieldValue(UsMtV1Field.TARGETED_ADVERTISING_OPT_OUT, 0);
      usMtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
      usMtV1.setFieldValue(UsMtV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
      usMtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }

    usMtV1.setFieldValue(UsMtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
    usMtV1.setFieldValue(UsMtV1Field.TARGETED_ADVERTISING_OPT_OUT, 0);
    usMtV1.encode();
    
    usMtV1.setFieldValue(UsMtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usMtV1.setFieldValue(UsMtV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
    usMtV1.encode();
    
    usMtV1.setFieldValue(UsMtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usMtV1.setFieldValue(UsMtV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
    usMtV1.encode();
    
    usMtV1.setFieldValue(UsMtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
    usMtV1.setFieldValue(UsMtV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
    usMtV1.encode();
  }
  
  @Test
  public void testValidateMspaServiceProviderMode() {
    UsMtV1 usMtV1 = new UsMtV1();
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usMtV1.setFieldValue(UsMtV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
      usMtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usMtV1.setFieldValue(UsMtV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
      usMtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
      usMtV1.setFieldValue(UsMtV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
      usMtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
      usMtV1.setFieldValue(UsMtV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
      usMtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
      usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT_NOTICE, 1);
      usMtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
      usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT_NOTICE, 2);
      usMtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    

    try {
      usMtV1.setFieldValue(UsMtV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT_NOTICE, 1);
      usMtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usMtV1.setFieldValue(UsMtV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT_NOTICE, 2);
      usMtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    
    
    usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT_NOTICE, 0);
    
    usMtV1.setFieldValue(UsMtV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usMtV1.setFieldValue(UsMtV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
    usMtV1.encode();
    
    usMtV1.setFieldValue(UsMtV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usMtV1.setFieldValue(UsMtV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usMtV1.encode();
    
    usMtV1.setFieldValue(UsMtV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usMtV1.setFieldValue(UsMtV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    usMtV1.encode();
    
    usMtV1.setFieldValue(UsMtV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
    usMtV1.setFieldValue(UsMtV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    usMtV1.encode();
    
    usMtV1.setFieldValue(UsMtV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usMtV1.setFieldValue(UsMtV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usMtV1.encode();
    
    
    usMtV1.setFieldValue(UsMtV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
    
    usMtV1.setFieldValue(UsMtV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT_NOTICE, 0);
    usMtV1.encode();
    
    usMtV1.setFieldValue(UsMtV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
    usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT_NOTICE, 0);
    usMtV1.encode();
    
    usMtV1.setFieldValue(UsMtV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT_NOTICE, 0);
    usMtV1.encode();
    
    usMtV1.setFieldValue(UsMtV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT_NOTICE, 1);
    usMtV1.encode();
    
    usMtV1.setFieldValue(UsMtV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usMtV1.setFieldValue(UsMtV1Field.SALE_OPT_OUT_NOTICE, 2);
    usMtV1.encode();
  }
  
  @Test
  public void testEncodeWithGpcSegmentExcluded() {
    UsMtV1 usMtV1 = new UsMtV1();
    usMtV1.setFieldValue(UsMtV1Field.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BAAAAABA", usMtV1.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsMtV1 usMtV1 = new UsMtV1("BVWSSZFY.YA");

    Assertions.assertEquals(1, usMtV1.getSharingNotice());
    Assertions.assertEquals(1, usMtV1.getSaleOptOutNotice());
    Assertions.assertEquals(1, usMtV1.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usMtV1.getSaleOptOut());
    Assertions.assertEquals(1, usMtV1.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usMtV1.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1, 0), usMtV1.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usMtV1.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usMtV1.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usMtV1.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usMtV1.getMspaServiceProviderMode());
    Assertions.assertEquals(true, usMtV1.getGpc());
  }

  @Test
  public void testDecodeWithGpcSegmentExcluded() throws DecodingException {
    UsMtV1 usMtV1 = new UsMtV1("BVWSSZFY");

    Assertions.assertEquals(1, usMtV1.getSharingNotice());
    Assertions.assertEquals(1, usMtV1.getSaleOptOutNotice());
    Assertions.assertEquals(1, usMtV1.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usMtV1.getSaleOptOut());
    Assertions.assertEquals(1, usMtV1.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usMtV1.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1, 0), usMtV1.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usMtV1.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usMtV1.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usMtV1.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usMtV1.getMspaServiceProviderMode());
    Assertions.assertEquals(false, usMtV1.getGpcSegmentIncluded());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsMtV1("z").getSharingNotice();
    });
  }
}
