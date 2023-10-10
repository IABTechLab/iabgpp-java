package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsVaV1Field;

public class UsVaV1Test {

  @Test
  public void testEncode1() {
    UsVaV1 usVaV1 = new UsVaV1();
    Assertions.assertEquals("BAAAABA", usVaV1.encode());
  }

  @Test
  public void testEncode2() {
    UsVaV1 usVaV1 = new UsVaV1();

    try {
      usVaV1.setFieldValue(UsVaV1Field.SHARING_NOTICE, 1);
      usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT_NOTICE, 1);
      usVaV1.setFieldValue(UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
      usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT, 1);
      usVaV1.setFieldValue(UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
      usVaV1.setFieldValue(UsVaV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1));
      usVaV1.setFieldValue(UsVaV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 1);
      usVaV1.setFieldValue(UsVaV1Field.MSPA_COVERED_TRANSACTION, 1);
      usVaV1.setFieldValue(UsVaV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
      usVaV1.setFieldValue(UsVaV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    } catch (InvalidFieldException e) {
      throw new EncodingException(e);
    }

    Assertions.assertEquals("BVWSSVY", usVaV1.encode());
  }

  @Test
  public void testSetInvalidValues() {
    UsVaV1 usVaV1 = new UsVaV1();

    try {
      usVaV1.setFieldValue(UsVaV1Field.SHARING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT_NOTICE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVaV1.setFieldValue(UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 4);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVaV1.setFieldValue(UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVaV1.setFieldValue(UsVaV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 1, 2, 0, 1));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVaV1.setFieldValue(UsVaV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVaV1.setFieldValue(UsVaV1Field.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVaV1.setFieldValue(UsVaV1Field.MSPA_OPT_OUT_OPTION_MODE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVaV1.setFieldValue(UsVaV1Field.MSPA_SERVICE_PROVIDER_MODE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

  }

  @Test
  public void testValidateSale() {
    UsVaV1 usVaV1 = new UsVaV1();

    try {
      usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT_NOTICE, 0);
      usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT, 1);
      usVaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT_NOTICE, 0);
      usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT, 2);
      usVaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT_NOTICE, 1);
      usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT, 0);
      usVaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT_NOTICE, 2);
      usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT, 0);
      usVaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT_NOTICE, 2);
      usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT, 2);
      usVaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT_NOTICE, 0);
    usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT, 0);
    usVaV1.encode();

    usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT_NOTICE, 1);
    usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT, 1);
    usVaV1.encode();

    usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT_NOTICE, 1);
    usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT, 2);
    usVaV1.encode();

    usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT_NOTICE, 2);
    usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT, 1);
    usVaV1.encode();
  }

  @Test
  public void testValidateTargetedAdvertising() {
    UsVaV1 usVaV1 = new UsVaV1();

    try {
      usVaV1.setFieldValue(UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
      usVaV1.setFieldValue(UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
      usVaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVaV1.setFieldValue(UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
      usVaV1.setFieldValue(UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
      usVaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVaV1.setFieldValue(UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
      usVaV1.setFieldValue(UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT, 0);
      usVaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVaV1.setFieldValue(UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
      usVaV1.setFieldValue(UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT, 0);
      usVaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVaV1.setFieldValue(UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
      usVaV1.setFieldValue(UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
      usVaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    usVaV1.setFieldValue(UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
    usVaV1.setFieldValue(UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT, 0);
    usVaV1.encode();

    usVaV1.setFieldValue(UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usVaV1.setFieldValue(UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
    usVaV1.encode();

    usVaV1.setFieldValue(UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usVaV1.setFieldValue(UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
    usVaV1.encode();

    usVaV1.setFieldValue(UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
    usVaV1.setFieldValue(UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
    usVaV1.encode();
  }
  
  @Test
  public void testValidateMspaServiceProviderMode() {
    UsVaV1 usVaV1 = new UsVaV1();
    
    try {
      usVaV1.setFieldValue(UsVaV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usVaV1.setFieldValue(UsVaV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
      usVaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usVaV1.setFieldValue(UsVaV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usVaV1.setFieldValue(UsVaV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
      usVaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usVaV1.setFieldValue(UsVaV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
      usVaV1.setFieldValue(UsVaV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
      usVaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usVaV1.setFieldValue(UsVaV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
      usVaV1.setFieldValue(UsVaV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
      usVaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usVaV1.setFieldValue(UsVaV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
      usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT_NOTICE, 1);
      usVaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usVaV1.setFieldValue(UsVaV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
      usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT_NOTICE, 2);
      usVaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    


    try {
      usVaV1.setFieldValue(UsVaV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT_NOTICE, 1);
      usVaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usVaV1.setFieldValue(UsVaV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT_NOTICE, 2);
      usVaV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    
    usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT_NOTICE, 0);
    
    usVaV1.setFieldValue(UsVaV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usVaV1.setFieldValue(UsVaV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
    usVaV1.encode();
    
    usVaV1.setFieldValue(UsVaV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usVaV1.setFieldValue(UsVaV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usVaV1.encode();
    
    usVaV1.setFieldValue(UsVaV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usVaV1.setFieldValue(UsVaV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    usVaV1.encode();
    
    usVaV1.setFieldValue(UsVaV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
    usVaV1.setFieldValue(UsVaV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    usVaV1.encode();
    
    usVaV1.setFieldValue(UsVaV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usVaV1.setFieldValue(UsVaV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usVaV1.encode();
    
    
    usVaV1.setFieldValue(UsVaV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
    
    usVaV1.setFieldValue(UsVaV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT_NOTICE, 0);
    usVaV1.encode();
    
    usVaV1.setFieldValue(UsVaV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
    usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT_NOTICE, 0);
    usVaV1.encode();
    
    usVaV1.setFieldValue(UsVaV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT_NOTICE, 0);
    usVaV1.encode();
    
    usVaV1.setFieldValue(UsVaV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT_NOTICE, 1);
    usVaV1.encode();
    
    usVaV1.setFieldValue(UsVaV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usVaV1.setFieldValue(UsVaV1Field.SALE_OPT_OUT_NOTICE, 2);
    usVaV1.encode();
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsVaV1 usVaV1 = new UsVaV1("BVWSSVY");

    Assertions.assertEquals(1, usVaV1.getSharingNotice());
    Assertions.assertEquals(1, usVaV1.getSaleOptOutNotice());
    Assertions.assertEquals(1, usVaV1.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usVaV1.getSaleOptOut());
    Assertions.assertEquals(1, usVaV1.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usVaV1.getSensitiveDataProcessing());
    Assertions.assertEquals(1, usVaV1.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usVaV1.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usVaV1.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usVaV1.getMspaServiceProviderMode());
  }
}
