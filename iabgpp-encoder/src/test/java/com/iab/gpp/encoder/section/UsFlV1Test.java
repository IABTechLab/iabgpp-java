package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsFlV1Field;

public class UsFlV1Test {

  @Test
  public void testEncode1() {
    UsFlV1 usFlV1 = new UsFlV1();
    Assertions.assertEquals("BAAAAABA", usFlV1.encode());
  }

  @Test
  public void testEncode2() {
    UsFlV1 usFlV1 = new UsFlV1();

    try {
      usFlV1.setFieldValue(UsFlV1Field.PROCESSING_NOTICE, 1);
      usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT_NOTICE, 1);
      usFlV1.setFieldValue(UsFlV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
      usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT, 1);
      usFlV1.setFieldValue(UsFlV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
      usFlV1.setFieldValue(UsFlV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1));
      usFlV1.setFieldValue(UsFlV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(2, 1, 2));
      usFlV1.setFieldValue(UsFlV1Field.ADDITIONAL_DATA_PROCESSING_CONSENT, 1);
      usFlV1.setFieldValue(UsFlV1Field.MSPA_COVERED_TRANSACTION, 1);
      usFlV1.setFieldValue(UsFlV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
      usFlV1.setFieldValue(UsFlV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    } catch (InvalidFieldException e) {
      throw new EncodingException(e);
    }

    Assertions.assertEquals("BVWSSZlY", usFlV1.encode());
  }

  @Test
  public void testSetInvalidValues() {
    UsFlV1 usFlV1 = new UsFlV1();

    try {
      usFlV1.setFieldValue(UsFlV1Field.PROCESSING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT_NOTICE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFlV1.setFieldValue(UsFlV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 4);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFlV1.setFieldValue(UsFlV1Field.TARGETED_ADVERTISING_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFlV1.setFieldValue(UsFlV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 1, 2, 0, 1));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFlV1.setFieldValue(UsFlV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(0, 3, 0));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFlV1.setFieldValue(UsFlV1Field.ADDITIONAL_DATA_PROCESSING_CONSENT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFlV1.setFieldValue(UsFlV1Field.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFlV1.setFieldValue(UsFlV1Field.MSPA_OPT_OUT_OPTION_MODE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFlV1.setFieldValue(UsFlV1Field.MSPA_SERVICE_PROVIDER_MODE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

  }

  @Test
  public void testValidateSale() {
    UsFlV1 usFlV1 = new UsFlV1();

    try {
      usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT_NOTICE, 0);
      usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT, 1);
      usFlV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT_NOTICE, 0);
      usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT, 2);
      usFlV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT_NOTICE, 1);
      usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT, 0);
      usFlV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT_NOTICE, 2);
      usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT, 0);
      usFlV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT_NOTICE, 2);
      usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT, 2);
      usFlV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT_NOTICE, 0);
    usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT, 0);
    usFlV1.encode();

    usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT_NOTICE, 1);
    usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT, 1);
    usFlV1.encode();

    usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT_NOTICE, 1);
    usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT, 2);
    usFlV1.encode();

    usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT_NOTICE, 2);
    usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT, 1);
    usFlV1.encode();
  }

  @Test
  public void testValidateTargetedAdvertising() {
    UsFlV1 usFlV1 = new UsFlV1();

    try {
      usFlV1.setFieldValue(UsFlV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
      usFlV1.setFieldValue(UsFlV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
      usFlV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFlV1.setFieldValue(UsFlV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
      usFlV1.setFieldValue(UsFlV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
      usFlV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFlV1.setFieldValue(UsFlV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
      usFlV1.setFieldValue(UsFlV1Field.TARGETED_ADVERTISING_OPT_OUT, 0);
      usFlV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFlV1.setFieldValue(UsFlV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
      usFlV1.setFieldValue(UsFlV1Field.TARGETED_ADVERTISING_OPT_OUT, 0);
      usFlV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usFlV1.setFieldValue(UsFlV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
      usFlV1.setFieldValue(UsFlV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
      usFlV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    usFlV1.setFieldValue(UsFlV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
    usFlV1.setFieldValue(UsFlV1Field.TARGETED_ADVERTISING_OPT_OUT, 0);
    usFlV1.encode();

    usFlV1.setFieldValue(UsFlV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usFlV1.setFieldValue(UsFlV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
    usFlV1.encode();

    usFlV1.setFieldValue(UsFlV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usFlV1.setFieldValue(UsFlV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
    usFlV1.encode();

    usFlV1.setFieldValue(UsFlV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
    usFlV1.setFieldValue(UsFlV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
    usFlV1.encode();
  }
  
  @Test
  public void testValidateMspaServiceProviderMode() {
    UsFlV1 usFlV1 = new UsFlV1();
    
    try {
      usFlV1.setFieldValue(UsFlV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usFlV1.setFieldValue(UsFlV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
      usFlV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usFlV1.setFieldValue(UsFlV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usFlV1.setFieldValue(UsFlV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
      usFlV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usFlV1.setFieldValue(UsFlV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
      usFlV1.setFieldValue(UsFlV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
      usFlV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usFlV1.setFieldValue(UsFlV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
      usFlV1.setFieldValue(UsFlV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
      usFlV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usFlV1.setFieldValue(UsFlV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
      usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT_NOTICE, 1);
      usFlV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usFlV1.setFieldValue(UsFlV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
      usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT_NOTICE, 2);
      usFlV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    


    try {
      usFlV1.setFieldValue(UsFlV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT_NOTICE, 1);
      usFlV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usFlV1.setFieldValue(UsFlV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT_NOTICE, 2);
      usFlV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    
    usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT_NOTICE, 0);
    
    usFlV1.setFieldValue(UsFlV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usFlV1.setFieldValue(UsFlV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
    usFlV1.encode();
    
    usFlV1.setFieldValue(UsFlV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usFlV1.setFieldValue(UsFlV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usFlV1.encode();
    
    usFlV1.setFieldValue(UsFlV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usFlV1.setFieldValue(UsFlV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    usFlV1.encode();
    
    usFlV1.setFieldValue(UsFlV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
    usFlV1.setFieldValue(UsFlV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    usFlV1.encode();
    
    usFlV1.setFieldValue(UsFlV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usFlV1.setFieldValue(UsFlV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usFlV1.encode();
    
    
    usFlV1.setFieldValue(UsFlV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
    
    usFlV1.setFieldValue(UsFlV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT_NOTICE, 0);
    usFlV1.encode();
    
    usFlV1.setFieldValue(UsFlV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
    usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT_NOTICE, 0);
    usFlV1.encode();
    
    usFlV1.setFieldValue(UsFlV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT_NOTICE, 0);
    usFlV1.encode();
    
    usFlV1.setFieldValue(UsFlV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT_NOTICE, 1);
    usFlV1.encode();
    
    usFlV1.setFieldValue(UsFlV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usFlV1.setFieldValue(UsFlV1Field.SALE_OPT_OUT_NOTICE, 2);
    usFlV1.encode();
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsFlV1 usFlV1 = new UsFlV1("BVWSSZlY");

    Assertions.assertEquals(1, usFlV1.getProcessingNotice());
    Assertions.assertEquals(1, usFlV1.getSaleOptOutNotice());
    Assertions.assertEquals(1, usFlV1.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usFlV1.getSaleOptOut());
    Assertions.assertEquals(1, usFlV1.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usFlV1.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1, 2), usFlV1.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usFlV1.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usFlV1.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usFlV1.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usFlV1.getMspaServiceProviderMode());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsFlV1("z").getMspaCoveredTransaction();
    });
  }
}
