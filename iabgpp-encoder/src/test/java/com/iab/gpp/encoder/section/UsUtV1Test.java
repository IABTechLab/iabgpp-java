package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsUtV1Field;

public class UsUtV1Test {

  @Test
  public void testEncode1() {
    UsUtV1 usUtV1 = new UsUtV1();
    Assertions.assertEquals("BAAAAAQA", usUtV1.encode());
  }

  @Test
  public void testEncode2() {
    UsUtV1 usUtV1 = new UsUtV1();

    usUtV1.setFieldValue(UsUtV1Field.SHARING_NOTICE, 1);
    usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT_NOTICE, 1);
    usUtV1.setFieldValue(UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT, 1);
    usUtV1.setFieldValue(UsUtV1Field.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE, 1);
    usUtV1.setFieldValue(UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
    usUtV1.setFieldValue(UsUtV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1));
    usUtV1.setFieldValue(UsUtV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 1);
    usUtV1.setFieldValue(UsUtV1Field.MSPA_COVERED_TRANSACTION, 1);
    usUtV1.setFieldValue(UsUtV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usUtV1.setFieldValue(UsUtV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);

    Assertions.assertEquals("BVVkklWA", usUtV1.encode());
  }
  
  @Test
  public void testSetInvalidValues() {
    UsUtV1 usUtV1 = new UsUtV1();

    try {
      usUtV1.setFieldValue(UsUtV1Field.SHARING_NOTICE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT, 4);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 0, 1, 2, 0));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.MSPA_OPT_OUT_OPTION_MODE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.MSPA_SERVICE_PROVIDER_MODE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
      
  }

  @Test
  public void testValidateSale() {
    UsUtV1 usUtV1 = new UsUtV1();
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT_NOTICE, 0);
      usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT, 1);
      usUtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT_NOTICE, 0);
      usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT, 2);
      usUtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT_NOTICE, 1);
      usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT, 0);
      usUtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT_NOTICE, 2);
      usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT, 0);
      usUtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT_NOTICE, 2);
      usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT, 2);
      usUtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }

    usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT_NOTICE, 0);
    usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT, 0);
    usUtV1.encode();
    
    usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT_NOTICE, 1);
    usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT, 1);
    usUtV1.encode();
    
    usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT_NOTICE, 1);
    usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT, 2);
    usUtV1.encode();
    
    usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT_NOTICE, 2);
    usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT, 1);
    usUtV1.encode();
  }
  
  @Test
  public void testValidateTargetedAdvertising() {
    UsUtV1 usUtV1 = new UsUtV1();
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
      usUtV1.setFieldValue(UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
      usUtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
      usUtV1.setFieldValue(UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
      usUtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
      usUtV1.setFieldValue(UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT, 0);
      usUtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
      usUtV1.setFieldValue(UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT, 0);
      usUtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
      usUtV1.setFieldValue(UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
      usUtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }

    usUtV1.setFieldValue(UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
    usUtV1.setFieldValue(UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT, 0);
    usUtV1.encode();
    
    usUtV1.setFieldValue(UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usUtV1.setFieldValue(UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
    usUtV1.encode();
    
    usUtV1.setFieldValue(UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usUtV1.setFieldValue(UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
    usUtV1.encode();
    
    usUtV1.setFieldValue(UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
    usUtV1.setFieldValue(UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
    usUtV1.encode();
  }
  
  @Test
  public void testValidateMspaServiceProviderMode() {
    UsUtV1 usUtV1 = new UsUtV1();
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usUtV1.setFieldValue(UsUtV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
      usUtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usUtV1.setFieldValue(UsUtV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
      usUtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
      usUtV1.setFieldValue(UsUtV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
      usUtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
      usUtV1.setFieldValue(UsUtV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
      usUtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
      usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT_NOTICE, 1);
      usUtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
      usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT_NOTICE, 2);
      usUtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    


    try {
      usUtV1.setFieldValue(UsUtV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT_NOTICE, 1);
      usUtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUtV1.setFieldValue(UsUtV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT_NOTICE, 2);
      usUtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    
    usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT_NOTICE, 0);
    
    usUtV1.setFieldValue(UsUtV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usUtV1.setFieldValue(UsUtV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
    usUtV1.encode();
    
    usUtV1.setFieldValue(UsUtV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usUtV1.setFieldValue(UsUtV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usUtV1.encode();
    
    usUtV1.setFieldValue(UsUtV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usUtV1.setFieldValue(UsUtV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    usUtV1.encode();
    
    usUtV1.setFieldValue(UsUtV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
    usUtV1.setFieldValue(UsUtV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    usUtV1.encode();
    
    usUtV1.setFieldValue(UsUtV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usUtV1.setFieldValue(UsUtV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usUtV1.encode();
    
    
    usUtV1.setFieldValue(UsUtV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
    
    usUtV1.setFieldValue(UsUtV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT_NOTICE, 0);
    usUtV1.encode();
    
    usUtV1.setFieldValue(UsUtV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
    usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT_NOTICE, 0);
    usUtV1.encode();
    
    usUtV1.setFieldValue(UsUtV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT_NOTICE, 0);
    usUtV1.encode();
    
    usUtV1.setFieldValue(UsUtV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT_NOTICE, 1);
    usUtV1.encode();
    
    usUtV1.setFieldValue(UsUtV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT_NOTICE, 2);
    usUtV1.encode();
  }
  
  @Test
  public void testDecode1() throws DecodingException {
    UsUtV1 usUtV1 = new UsUtV1("BVVkklWA");

    Assertions.assertEquals(1, usUtV1.getSharingNotice());
    Assertions.assertEquals(1, usUtV1.getSaleOptOutNotice());
    Assertions.assertEquals(1, usUtV1.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usUtV1.getSaleOptOut());
    Assertions.assertEquals(1, usUtV1.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usUtV1.getSensitiveDataProcessing());
    Assertions.assertEquals(1, usUtV1.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usUtV1.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usUtV1.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usUtV1.getMspaServiceProviderMode());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsUtV1("z").getKnownChildSensitiveDataConsents();
    });
  }
}
