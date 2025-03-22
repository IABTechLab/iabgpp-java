package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsNatField;

public class UsNatTest {

  @Test
  public void testEncode1() {

    UsNat usNat = new UsNat();
    Assertions.assertEquals("BAAAAAAAAABA.QA", usNat.encode());
  }

  @Test
  public void testEncode2() {
    UsNat usNat = new UsNat();

    usNat.setFieldValue(UsNatField.SHARING_NOTICE, 1);
    usNat.setFieldValue(UsNatField.SALE_OPT_OUT_NOTICE, 1);
    usNat.setFieldValue(UsNatField.SHARING_OPT_OUT_NOTICE, 1);
    usNat.setFieldValue(UsNatField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usNat.setFieldValue(UsNatField.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE, 1);
    usNat.setFieldValue(UsNatField.SENSITIVE_DATA_LIMIT_USE_NOTICE, 1);
    usNat.setFieldValue(UsNatField.SALE_OPT_OUT, 1);
    usNat.setFieldValue(UsNatField.SHARING_OPT_OUT, 1);
    usNat.setFieldValue(UsNatField.TARGETED_ADVERTISING_OPT_OUT, 1);
    usNat.setFieldValue(UsNatField.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2));
    usNat.setFieldValue(UsNatField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(2, 1, 0));
    usNat.setFieldValue(UsNatField.PERSONAL_DATA_CONSENTS, 1);
    usNat.setFieldValue(UsNatField.MSPA_COVERED_TRANSACTION, 1);
    usNat.setFieldValue(UsNatField.MSPA_OPT_OUT_OPTION_MODE, 1);
    usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 2);
    usNat.setFieldValue(UsNatField.GPC, true);

    Assertions.assertEquals("BVVVkkkkkpFY.YA", usNat.encode());
  }
  
  @Test
  public void testSetInvalidValues() {
    UsNat usNat = new UsNat();

    try {
      usNat.setFieldValue(UsNatField.SHARING_NOTICE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.SALE_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.SHARING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 4);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.SENSITIVE_DATA_LIMIT_USE_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.SHARING_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.TARGETED_ADVERTISING_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 0, 1, 2, 0, 0, 1, 2, 0));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(0, 3));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.PERSONAL_DATA_CONSENTS, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.MSPA_OPT_OUT_OPTION_MODE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    
  }

  @Test
  public void testValidateSharing() {
    UsNat usNat = new UsNat();
    
    try {
      usNat.setFieldValue(UsNatField.SHARING_OPT_OUT_NOTICE, 0);
      usNat.setFieldValue(UsNatField.SHARING_OPT_OUT_NOTICE, 0);
      usNat.setFieldValue(UsNatField.SHARING_OPT_OUT, 1);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.SHARING_OPT_OUT_NOTICE, 0);
      usNat.setFieldValue(UsNatField.SHARING_OPT_OUT, 2);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.SHARING_OPT_OUT_NOTICE, 1);
      usNat.setFieldValue(UsNatField.SHARING_OPT_OUT, 0);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.SHARING_OPT_OUT_NOTICE, 2);
      usNat.setFieldValue(UsNatField.SHARING_OPT_OUT, 0);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.SHARING_OPT_OUT_NOTICE, 2);
      usNat.setFieldValue(UsNatField.SHARING_OPT_OUT, 2);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }

    usNat.setFieldValue(UsNatField.SHARING_OPT_OUT_NOTICE, 0);
    usNat.setFieldValue(UsNatField.SHARING_OPT_OUT, 0);
    usNat.encode();
    
    usNat.setFieldValue(UsNatField.SHARING_OPT_OUT_NOTICE, 1);
    usNat.setFieldValue(UsNatField.SHARING_OPT_OUT, 1);
    usNat.encode();
    
    usNat.setFieldValue(UsNatField.SHARING_OPT_OUT_NOTICE, 1);
    usNat.setFieldValue(UsNatField.SHARING_OPT_OUT, 2);
    usNat.encode();
    
    usNat.setFieldValue(UsNatField.SHARING_OPT_OUT_NOTICE, 2);
    usNat.setFieldValue(UsNatField.SHARING_OPT_OUT, 1);
    usNat.encode();
  }
  
  @Test
  public void testValidateSale() {
    UsNat usNat = new UsNat();
    
    try {
      usNat.setFieldValue(UsNatField.SALE_OPT_OUT_NOTICE, 0);
      usNat.setFieldValue(UsNatField.SALE_OPT_OUT, 1);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.SALE_OPT_OUT_NOTICE, 0);
      usNat.setFieldValue(UsNatField.SALE_OPT_OUT, 2);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.SALE_OPT_OUT_NOTICE, 1);
      usNat.setFieldValue(UsNatField.SALE_OPT_OUT, 0);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.SALE_OPT_OUT_NOTICE, 2);
      usNat.setFieldValue(UsNatField.SALE_OPT_OUT, 0);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.SALE_OPT_OUT_NOTICE, 2);
      usNat.setFieldValue(UsNatField.SALE_OPT_OUT, 2);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }

    usNat.setFieldValue(UsNatField.SALE_OPT_OUT_NOTICE, 0);
    usNat.setFieldValue(UsNatField.SALE_OPT_OUT, 0);
    usNat.encode();
    
    usNat.setFieldValue(UsNatField.SALE_OPT_OUT_NOTICE, 1);
    usNat.setFieldValue(UsNatField.SALE_OPT_OUT, 1);
    usNat.encode();
    
    usNat.setFieldValue(UsNatField.SALE_OPT_OUT_NOTICE, 1);
    usNat.setFieldValue(UsNatField.SALE_OPT_OUT, 2);
    usNat.encode();
    
    usNat.setFieldValue(UsNatField.SALE_OPT_OUT_NOTICE, 2);
    usNat.setFieldValue(UsNatField.SALE_OPT_OUT, 1);
    usNat.encode();
  }
  
  @Test
  public void testValidateTargetedAdvertising() {
    UsNat usNat = new UsNat();
    
    try {
      usNat.setFieldValue(UsNatField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
      usNat.setFieldValue(UsNatField.TARGETED_ADVERTISING_OPT_OUT, 1);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
      usNat.setFieldValue(UsNatField.TARGETED_ADVERTISING_OPT_OUT, 2);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
      usNat.setFieldValue(UsNatField.TARGETED_ADVERTISING_OPT_OUT, 0);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
      usNat.setFieldValue(UsNatField.TARGETED_ADVERTISING_OPT_OUT, 0);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
      usNat.setFieldValue(UsNatField.TARGETED_ADVERTISING_OPT_OUT, 2);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }

    usNat.setFieldValue(UsNatField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
    usNat.setFieldValue(UsNatField.TARGETED_ADVERTISING_OPT_OUT, 0);
    usNat.encode();
    
    usNat.setFieldValue(UsNatField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usNat.setFieldValue(UsNatField.TARGETED_ADVERTISING_OPT_OUT, 1);
    usNat.encode();
    
    usNat.setFieldValue(UsNatField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usNat.setFieldValue(UsNatField.TARGETED_ADVERTISING_OPT_OUT, 2);
    usNat.encode();
    
    usNat.setFieldValue(UsNatField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
    usNat.setFieldValue(UsNatField.TARGETED_ADVERTISING_OPT_OUT, 1);
    usNat.encode();
  }
  
  @Test
  public void testValidateMspaServiceProviderMode() {
    UsNat usNat = new UsNat();
    
    try {
      usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 1);
      usNat.setFieldValue(UsNatField.MSPA_OPT_OUT_OPTION_MODE, 0);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 1);
      usNat.setFieldValue(UsNatField.MSPA_OPT_OUT_OPTION_MODE, 1);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 2);
      usNat.setFieldValue(UsNatField.MSPA_OPT_OUT_OPTION_MODE, 0);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 2);
      usNat.setFieldValue(UsNatField.MSPA_OPT_OUT_OPTION_MODE, 2);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 0);
      usNat.setFieldValue(UsNatField.SALE_OPT_OUT_NOTICE, 1);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 0);
      usNat.setFieldValue(UsNatField.SALE_OPT_OUT_NOTICE, 2);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 0);
      usNat.setFieldValue(UsNatField.SHARING_OPT_OUT_NOTICE, 1);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 0);
      usNat.setFieldValue(UsNatField.SHARING_OPT_OUT_NOTICE, 2);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 0);
      usNat.setFieldValue(UsNatField.SENSITIVE_DATA_LIMIT_USE_NOTICE, 1);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 0);
      usNat.setFieldValue(UsNatField.SENSITIVE_DATA_LIMIT_USE_NOTICE, 2);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    


    try {
      usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 1);
      usNat.setFieldValue(UsNatField.SALE_OPT_OUT_NOTICE, 1);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 1);
      usNat.setFieldValue(UsNatField.SALE_OPT_OUT_NOTICE, 2);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 1);
      usNat.setFieldValue(UsNatField.SHARING_OPT_OUT_NOTICE, 1);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 1);
      usNat.setFieldValue(UsNatField.SHARING_OPT_OUT_NOTICE, 2);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 1);
      usNat.setFieldValue(UsNatField.SENSITIVE_DATA_LIMIT_USE_NOTICE, 1);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 1);
      usNat.setFieldValue(UsNatField.SENSITIVE_DATA_LIMIT_USE_NOTICE, 2);
      usNat.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    
    usNat.setFieldValue(UsNatField.SALE_OPT_OUT_NOTICE, 0);
    usNat.setFieldValue(UsNatField.SHARING_OPT_OUT_NOTICE, 0);
    usNat.setFieldValue(UsNatField.SENSITIVE_DATA_LIMIT_USE_NOTICE, 0);
    
    usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 0);
    usNat.setFieldValue(UsNatField.MSPA_OPT_OUT_OPTION_MODE, 0);
    usNat.encode();
    
    usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 0);
    usNat.setFieldValue(UsNatField.MSPA_OPT_OUT_OPTION_MODE, 1);
    usNat.encode();
    
    usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 0);
    usNat.setFieldValue(UsNatField.MSPA_OPT_OUT_OPTION_MODE, 2);
    usNat.encode();
    
    usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 1);
    usNat.setFieldValue(UsNatField.MSPA_OPT_OUT_OPTION_MODE, 2);
    usNat.encode();
    
    usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 2);
    usNat.setFieldValue(UsNatField.MSPA_OPT_OUT_OPTION_MODE, 1);
    usNat.encode();
    
    
    usNat.setFieldValue(UsNatField.MSPA_OPT_OUT_OPTION_MODE, 0);
    
    usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 0);
    usNat.setFieldValue(UsNatField.SALE_OPT_OUT_NOTICE, 0);
    usNat.setFieldValue(UsNatField.SHARING_OPT_OUT_NOTICE, 0);
    usNat.setFieldValue(UsNatField.SENSITIVE_DATA_LIMIT_USE_NOTICE, 0);
    usNat.encode();
    
    usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 1);
    usNat.setFieldValue(UsNatField.SALE_OPT_OUT_NOTICE, 0);
    usNat.setFieldValue(UsNatField.SHARING_OPT_OUT_NOTICE, 0);
    usNat.setFieldValue(UsNatField.SENSITIVE_DATA_LIMIT_USE_NOTICE, 0);
    usNat.encode();
    
    usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 2);
    usNat.setFieldValue(UsNatField.SALE_OPT_OUT_NOTICE, 0);
    usNat.setFieldValue(UsNatField.SHARING_OPT_OUT_NOTICE, 0);
    usNat.setFieldValue(UsNatField.SENSITIVE_DATA_LIMIT_USE_NOTICE, 0);
    usNat.encode();
    
    usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 2);
    usNat.setFieldValue(UsNatField.SALE_OPT_OUT_NOTICE, 1);
    usNat.setFieldValue(UsNatField.SHARING_OPT_OUT_NOTICE, 1);
    usNat.setFieldValue(UsNatField.SENSITIVE_DATA_LIMIT_USE_NOTICE, 1);
    usNat.encode();
    
    usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 2);
    usNat.setFieldValue(UsNatField.SALE_OPT_OUT_NOTICE, 2);
    usNat.setFieldValue(UsNatField.SHARING_OPT_OUT_NOTICE, 2);
    usNat.setFieldValue(UsNatField.SENSITIVE_DATA_LIMIT_USE_NOTICE, 2);
    usNat.encode();
  }
  
  
  @Test
  public void testEncode3() {
    UsNat usNat = new UsNat();

    usNat.setFieldValue(UsNatField.SHARING_NOTICE, 1);
    usNat.setFieldValue(UsNatField.SALE_OPT_OUT_NOTICE, 1);
    usNat.setFieldValue(UsNatField.SHARING_OPT_OUT_NOTICE, 1);
    usNat.setFieldValue(UsNatField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usNat.setFieldValue(UsNatField.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE, 1);
    usNat.setFieldValue(UsNatField.SENSITIVE_DATA_LIMIT_USE_NOTICE, 1);
    usNat.setFieldValue(UsNatField.SALE_OPT_OUT, 1);
    usNat.setFieldValue(UsNatField.SHARING_OPT_OUT, 1);
    usNat.setFieldValue(UsNatField.TARGETED_ADVERTISING_OPT_OUT, 1);
    usNat.setFieldValue(UsNatField.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2));
    usNat.setFieldValue(UsNatField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(2, 1, 0));
    usNat.setFieldValue(UsNatField.PERSONAL_DATA_CONSENTS, 1);
    usNat.setFieldValue(UsNatField.MSPA_COVERED_TRANSACTION, 1);
    usNat.setFieldValue(UsNatField.MSPA_OPT_OUT_OPTION_MODE, 1);
    usNat.setFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE, 2);
    usNat.setFieldValue(UsNatField.GPC, true);

    Assertions.assertEquals("BVVVkkkkkpFY.YA", usNat.encode());
  }

  @Test
  public void testEncodeWithGpcSegmentIncluded() {

    UsNat usNat = new UsNat();
    usNat.setFieldValue(UsNatField.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BAAAAAAAAABA", usNat.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsNat usNat = new UsNat("BVVVkkkkkpFY.YA");

    Assertions.assertEquals(1, usNat.getSharingNotice());
    Assertions.assertEquals(1, usNat.getSaleOptOutNotice());
    Assertions.assertEquals(1, usNat.getSharingOptOutNotice());
    Assertions.assertEquals(1, usNat.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usNat.getSensitiveDataProcessingOptOutNotice());
    Assertions.assertEquals(1, usNat.getSensitiveDataLimitUseNotice());
    Assertions.assertEquals(1, usNat.getSaleOptOut());
    Assertions.assertEquals(1, usNat.getSharingOptOut());
    Assertions.assertEquals(1, usNat.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2), usNat.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1, 0), usNat.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usNat.getPersonalDataConsents());
    Assertions.assertEquals(1, usNat.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usNat.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usNat.getMspaServiceProviderMode());
    Assertions.assertEquals(true, usNat.getGpc());
  }

  @Test
  public void testDecodeWithGpcSegmentExcluded() throws DecodingException {
    UsNat usNat = new UsNat("BVVVkkkkkpFY");

    Assertions.assertEquals(1, usNat.getSharingNotice());
    Assertions.assertEquals(1, usNat.getSaleOptOutNotice());
    Assertions.assertEquals(1, usNat.getSharingOptOutNotice());
    Assertions.assertEquals(1, usNat.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usNat.getSensitiveDataProcessingOptOutNotice());
    Assertions.assertEquals(1, usNat.getSensitiveDataLimitUseNotice());
    Assertions.assertEquals(1, usNat.getSaleOptOut());
    Assertions.assertEquals(1, usNat.getSharingOptOut());
    Assertions.assertEquals(1, usNat.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2, 1, 0, 2), usNat.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1, 0), usNat.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usNat.getPersonalDataConsents());
    Assertions.assertEquals(1, usNat.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usNat.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usNat.getMspaServiceProviderMode());
    Assertions.assertEquals(false, usNat.getGpcSegmentIncluded());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsNat("z").getSharingNotice();
    });
  }
}
