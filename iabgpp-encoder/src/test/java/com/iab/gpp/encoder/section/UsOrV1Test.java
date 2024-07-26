package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsOrV1Field;

public class UsOrV1Test {

  @Test
  public void testEncode1() {
    UsOrV1 usOrV1 = new UsOrV1();
    Assertions.assertEquals("BAAAAAABAA.QA", usOrV1.encode());
  }

  @Test
  public void testEncode2() {
    UsOrV1 usOrV1 = new UsOrV1();

    usOrV1.setFieldValue(UsOrV1Field.PROCESSING_NOTICE, 1);
    usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT_NOTICE, 1);
    usOrV1.setFieldValue(UsOrV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT, 1);
    usOrV1.setFieldValue(UsOrV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
    usOrV1.setFieldValue(UsOrV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0, 1, 2));
    usOrV1.setFieldValue(UsOrV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(2, 1, 0));
    usOrV1.setFieldValue(UsOrV1Field.ADDITIONAL_DATA_PROCESSING_CONSENT, 1);
    usOrV1.setFieldValue(UsOrV1Field.MSPA_COVERED_TRANSACTION, 1);
    usOrV1.setFieldValue(UsOrV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usOrV1.setFieldValue(UsOrV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usOrV1.setFieldValue(UsOrV1Field.GPC, true);

    Assertions.assertEquals("BVWSSRpFYA.YA", usOrV1.encode());
  }
  
  @Test
  public void testSetInvalidValues() {
    UsOrV1 usOrV1 = new UsOrV1();

    try {
      usOrV1.setFieldValue(UsOrV1Field.PROCESSING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.TARGETED_ADVERTISING_OPT_OUT, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 1, 2, 0, 1));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(1, 2, 3));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.ADDITIONAL_DATA_PROCESSING_CONSENT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.MSPA_OPT_OUT_OPTION_MODE, 4);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.MSPA_SERVICE_PROVIDER_MODE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
  }

  @Test
  public void testValidateSale() {
    UsOrV1 usOrV1 = new UsOrV1();
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT_NOTICE, 0);
      usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT, 1);
      usOrV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT_NOTICE, 0);
      usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT, 2);
      usOrV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT_NOTICE, 1);
      usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT, 0);
      usOrV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT_NOTICE, 2);
      usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT, 0);
      usOrV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT_NOTICE, 2);
      usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT, 2);
      usOrV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }

    usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT_NOTICE, 0);
    usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT, 0);
    usOrV1.encode();
    
    usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT_NOTICE, 1);
    usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT, 1);
    usOrV1.encode();
    
    usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT_NOTICE, 1);
    usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT, 2);
    usOrV1.encode();
    
    usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT_NOTICE, 2);
    usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT, 1);
    usOrV1.encode();
  }
  
  @Test
  public void testValidateTargetedAdvertising() {
    UsOrV1 usOrV1 = new UsOrV1();
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
      usOrV1.setFieldValue(UsOrV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
      usOrV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
      usOrV1.setFieldValue(UsOrV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
      usOrV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
      usOrV1.setFieldValue(UsOrV1Field.TARGETED_ADVERTISING_OPT_OUT, 0);
      usOrV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
      usOrV1.setFieldValue(UsOrV1Field.TARGETED_ADVERTISING_OPT_OUT, 0);
      usOrV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
      usOrV1.setFieldValue(UsOrV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
      usOrV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }

    usOrV1.setFieldValue(UsOrV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
    usOrV1.setFieldValue(UsOrV1Field.TARGETED_ADVERTISING_OPT_OUT, 0);
    usOrV1.encode();
    
    usOrV1.setFieldValue(UsOrV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usOrV1.setFieldValue(UsOrV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
    usOrV1.encode();
    
    usOrV1.setFieldValue(UsOrV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usOrV1.setFieldValue(UsOrV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
    usOrV1.encode();
    
    usOrV1.setFieldValue(UsOrV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
    usOrV1.setFieldValue(UsOrV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
    usOrV1.encode();
  }
  
  @Test
  public void testValidateMspaServiceProviderMode() {
    UsOrV1 usOrV1 = new UsOrV1();
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usOrV1.setFieldValue(UsOrV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
      usOrV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usOrV1.setFieldValue(UsOrV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
      usOrV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
      usOrV1.setFieldValue(UsOrV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
      usOrV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
      usOrV1.setFieldValue(UsOrV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
      usOrV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
      usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT_NOTICE, 1);
      usOrV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
      usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT_NOTICE, 2);
      usOrV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    

    try {
      usOrV1.setFieldValue(UsOrV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT_NOTICE, 1);
      usOrV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOrV1.setFieldValue(UsOrV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT_NOTICE, 2);
      usOrV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    
    
    usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT_NOTICE, 0);
    
    usOrV1.setFieldValue(UsOrV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usOrV1.setFieldValue(UsOrV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
    usOrV1.encode();
    
    usOrV1.setFieldValue(UsOrV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usOrV1.setFieldValue(UsOrV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usOrV1.encode();
    
    usOrV1.setFieldValue(UsOrV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usOrV1.setFieldValue(UsOrV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    usOrV1.encode();
    
    usOrV1.setFieldValue(UsOrV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
    usOrV1.setFieldValue(UsOrV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    usOrV1.encode();
    
    usOrV1.setFieldValue(UsOrV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usOrV1.setFieldValue(UsOrV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usOrV1.encode();
    
    
    usOrV1.setFieldValue(UsOrV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
    
    usOrV1.setFieldValue(UsOrV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT_NOTICE, 0);
    usOrV1.encode();
    
    usOrV1.setFieldValue(UsOrV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
    usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT_NOTICE, 0);
    usOrV1.encode();
    
    usOrV1.setFieldValue(UsOrV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT_NOTICE, 0);
    usOrV1.encode();
    
    usOrV1.setFieldValue(UsOrV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT_NOTICE, 1);
    usOrV1.encode();
    
    usOrV1.setFieldValue(UsOrV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usOrV1.setFieldValue(UsOrV1Field.SALE_OPT_OUT_NOTICE, 2);
    usOrV1.encode();
  }
  
  @Test
  public void testEncodeWithGpcSegmentExcluded() {
    UsOrV1 usOrV1 = new UsOrV1();
    usOrV1.setFieldValue(UsOrV1Field.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BAAAAAABAA", usOrV1.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsOrV1 usOrV1 = new UsOrV1("BVWSSRpFYA.YA");

    Assertions.assertEquals(1, usOrV1.getProcessingNotice());
    Assertions.assertEquals(1, usOrV1.getSaleOptOutNotice());
    Assertions.assertEquals(1, usOrV1.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usOrV1.getSaleOptOut());
    Assertions.assertEquals(1, usOrV1.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0, 1, 2), usOrV1.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1, 0), usOrV1.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usOrV1.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usOrV1.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usOrV1.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usOrV1.getMspaServiceProviderMode());
    Assertions.assertEquals(true, usOrV1.getGpc());
  }

  @Test
  public void testDecodeWithGpcSegmentExcluded() throws DecodingException {
    UsOrV1 usOrV1 = new UsOrV1("BVWSSRpFYA");

    Assertions.assertEquals(1, usOrV1.getProcessingNotice());
    Assertions.assertEquals(1, usOrV1.getSaleOptOutNotice());
    Assertions.assertEquals(1, usOrV1.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usOrV1.getSaleOptOut());
    Assertions.assertEquals(1, usOrV1.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0, 1, 2), usOrV1.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1, 0), usOrV1.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usOrV1.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usOrV1.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usOrV1.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usOrV1.getMspaServiceProviderMode());
    Assertions.assertEquals(false, usOrV1.getGpcSegmentIncluded());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsOrV1("z").getProcessingNotice();
    });
  }
}
