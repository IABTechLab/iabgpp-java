package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsCaV1Field;

public class UsCaV1Test {

  @Test
  public void testEncode1() {

    UsCaV1 usCaV1 = new UsCaV1();
    Assertions.assertEquals("BAAAAABA.QA", usCaV1.encode());
  }

  @Test
  public void testEncode2() {
    UsCaV1 usCaV1 = new UsCaV1();

    usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT_NOTICE, 1);
    usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT_NOTICE, 1);
    usCaV1.setFieldValue(UsCaV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE, 1);
    usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT, 1);
    usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT, 1);
    usCaV1.setFieldValue(UsCaV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0));
    usCaV1.setFieldValue(UsCaV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(2, 1));
    usCaV1.setFieldValue(UsCaV1Field.PERSONAL_DATA_CONSENTS, 1);
    usCaV1.setFieldValue(UsCaV1Field.MSPA_COVERED_TRANSACTION, 1);
    usCaV1.setFieldValue(UsCaV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usCaV1.setFieldValue(UsCaV1Field.GPC, true);

    Assertions.assertEquals("BVWSSSVY.YA", usCaV1.encode());
  }

  @Test
  public void testEncode3() {
    UsCaV1 usCaV1 = new UsCaV1();

    usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT_NOTICE, 1);
    usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT_NOTICE, 1);
    usCaV1.setFieldValue(UsCaV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE, 1);
    usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT, 1);
    usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT, 1);
    usCaV1.setFieldValue(UsCaV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0));
    usCaV1.setFieldValue(UsCaV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(2, 1));
    usCaV1.setFieldValue(UsCaV1Field.PERSONAL_DATA_CONSENTS, 1);
    usCaV1.setFieldValue(UsCaV1Field.MSPA_COVERED_TRANSACTION, 1);
    usCaV1.setFieldValue(UsCaV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usCaV1.setFieldValue(UsCaV1Field.GPC, true);

    Assertions.assertEquals("BVWSSSVY.YA", usCaV1.encode());
  }
  
  @Test
  public void testSetInvalidValues() {
    UsCaV1 usCaV1 = new UsCaV1();

    try {
      usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 0, 0, 0, 3, 0, 0, 0, 0));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(0, 3));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.PERSONAL_DATA_CONSENTS, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.MSPA_OPT_OUT_OPTION_MODE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
  }
  
  @Test
  public void testValidateSharing() {
    UsCaV1 usCaV1 = new UsCaV1();
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT_NOTICE, 0);
      usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT, 1);
      usCaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT_NOTICE, 0);
      usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT, 2);
      usCaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT_NOTICE, 1);
      usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT, 0);
      usCaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT_NOTICE, 2);
      usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT, 0);
      usCaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT_NOTICE, 2);
      usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT, 2);
      usCaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }

    usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT_NOTICE, 0);
    usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT, 0);
    usCaV1.encode();
    
    usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT_NOTICE, 1);
    usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT, 1);
    usCaV1.encode();
    
    usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT_NOTICE, 1);
    usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT, 2);
    usCaV1.encode();
    
    usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT_NOTICE, 2);
    usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT, 1);
    usCaV1.encode();
  }
  
  @Test
  public void testValidateSale() {
    UsCaV1 usCaV1 = new UsCaV1();
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT_NOTICE, 0);
      usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT, 1);
      usCaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT_NOTICE, 0);
      usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT, 2);
      usCaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT_NOTICE, 1);
      usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT, 0);
      usCaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT_NOTICE, 2);
      usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT, 0);
      usCaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT_NOTICE, 2);
      usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT, 2);
      usCaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }

    usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT_NOTICE, 0);
    usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT, 0);
    usCaV1.encode();
    
    usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT_NOTICE, 1);
    usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT, 1);
    usCaV1.encode();
    
    usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT_NOTICE, 1);
    usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT, 2);
    usCaV1.encode();
    
    usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT_NOTICE, 2);
    usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT, 1);
    usCaV1.encode();
  }
  
  @Test
  public void testValidateMspaServiceProviderMode() {
    UsCaV1 usCaV1 = new UsCaV1();
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usCaV1.setFieldValue(UsCaV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
      usCaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usCaV1.setFieldValue(UsCaV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
      usCaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
      usCaV1.setFieldValue(UsCaV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
      usCaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
      usCaV1.setFieldValue(UsCaV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
      usCaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
      usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT_NOTICE, 1);
      usCaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
      usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT_NOTICE, 2);
      usCaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
      usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT_NOTICE, 1);
      usCaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
      usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT_NOTICE, 2);
      usCaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
      usCaV1.setFieldValue(UsCaV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE, 1);
      usCaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
      usCaV1.setFieldValue(UsCaV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE, 2);
      usCaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    


    try {
      usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT_NOTICE, 1);
      usCaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT_NOTICE, 2);
      usCaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT_NOTICE, 1);
      usCaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT_NOTICE, 2);
      usCaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usCaV1.setFieldValue(UsCaV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE, 1);
      usCaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usCaV1.setFieldValue(UsCaV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE, 2);
      usCaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    
    usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT_NOTICE, 0);
    usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT_NOTICE, 0);
    usCaV1.setFieldValue(UsCaV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE, 0);
    
    usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usCaV1.setFieldValue(UsCaV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
    usCaV1.encode();
    
    usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usCaV1.setFieldValue(UsCaV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usCaV1.encode();
    
    usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usCaV1.setFieldValue(UsCaV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    usCaV1.encode();
    
    usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
    usCaV1.setFieldValue(UsCaV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    usCaV1.encode();
    
    usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usCaV1.setFieldValue(UsCaV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usCaV1.encode();
    
    
    usCaV1.setFieldValue(UsCaV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
    
    usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT_NOTICE, 0);
    usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT_NOTICE, 0);
    usCaV1.setFieldValue(UsCaV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE, 0);
    usCaV1.encode();
    
    usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
    usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT_NOTICE, 0);
    usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT_NOTICE, 0);
    usCaV1.setFieldValue(UsCaV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE, 0);
    usCaV1.encode();
    
    usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT_NOTICE, 0);
    usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT_NOTICE, 0);
    usCaV1.setFieldValue(UsCaV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE, 0);
    usCaV1.encode();
    
    usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT_NOTICE, 1);
    usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT_NOTICE, 1);
    usCaV1.setFieldValue(UsCaV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE, 1);
    usCaV1.encode();
    
    usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT_NOTICE, 2);
    usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT_NOTICE, 2);
    usCaV1.setFieldValue(UsCaV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE, 2);
    usCaV1.encode();
  }
  
  @Test
  public void testEncodeWithGpcSegmentExcluded() {

    UsCaV1 usCaV1 = new UsCaV1();
    usCaV1.setFieldValue(UsCaV1Field.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BAAAAABA", usCaV1.encode());
  }

  @Test
  public void testDecode1() {
    UsCaV1 usCaV1 = new UsCaV1("BVWSSSVY.YA");

    Assertions.assertEquals(1, usCaV1.getSaleOptOutNotice());
    Assertions.assertEquals(1, usCaV1.getSharingOptOut());
    Assertions.assertEquals(1, usCaV1.getSensitiveDataLimitUseNotice());
    Assertions.assertEquals(1, usCaV1.getSaleOptOut());
    Assertions.assertEquals(1, usCaV1.getSharingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0), usCaV1.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1), usCaV1.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usCaV1.getPersonalDataConsents());
    Assertions.assertEquals(1, usCaV1.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usCaV1.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usCaV1.getMspaServiceProviderMode());
    Assertions.assertEquals(true, usCaV1.getGpc());
  }

  @Test
  public void testDecodeWithGpcSegmentExcluded() {
    UsCaV1 usCaV1 = new UsCaV1("BVWSSSVY");

    Assertions.assertEquals(1, usCaV1.getSaleOptOutNotice());
    Assertions.assertEquals(1, usCaV1.getSharingOptOut());
    Assertions.assertEquals(1, usCaV1.getSensitiveDataLimitUseNotice());
    Assertions.assertEquals(1, usCaV1.getSaleOptOut());
    Assertions.assertEquals(1, usCaV1.getSharingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0), usCaV1.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1), usCaV1.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usCaV1.getPersonalDataConsents());
    Assertions.assertEquals(1, usCaV1.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usCaV1.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usCaV1.getMspaServiceProviderMode());
    Assertions.assertEquals(false, usCaV1.getGpcSegmentIncluded());
  }
}
