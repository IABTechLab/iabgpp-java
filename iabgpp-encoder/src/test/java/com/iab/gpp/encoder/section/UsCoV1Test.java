package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsCoV1Field;

public class UsCoV1Test {

  @Test
  public void testEncode1() {
    UsCoV1 usCoV1 = new UsCoV1();
    Assertions.assertEquals("BAAAAEA.QA", usCoV1.encode());
  }

  @Test
  public void testEncode2() {
    UsCoV1 usCoV1 = new UsCoV1();

    usCoV1.setFieldValue(UsCoV1Field.SHARING_NOTICE, 1);
    usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT_NOTICE, 1);
    usCoV1.setFieldValue(UsCoV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT, 1);
    usCoV1.setFieldValue(UsCoV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
    usCoV1.setFieldValue(UsCoV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2));
    usCoV1.setFieldValue(UsCoV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 1);
    usCoV1.setFieldValue(UsCoV1Field.MSPA_COVERED_TRANSACTION, 1);
    usCoV1.setFieldValue(UsCoV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usCoV1.setFieldValue(UsCoV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usCoV1.setFieldValue(UsCoV1Field.GPC, true);

    Assertions.assertEquals("BVWSSVg.YA", usCoV1.encode());
  }
  
  @Test
  public void testSetInvalidValues() {
    UsCoV1 usCoV1 = new UsCoV1();

    try {
      usCoV1.setFieldValue(UsCoV1Field.SHARING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCoV1.setFieldValue(UsCoV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCoV1.setFieldValue(UsCoV1Field.TARGETED_ADVERTISING_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCoV1.setFieldValue(UsCoV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 0, 1, 2));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCoV1.setFieldValue(UsCoV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCoV1.setFieldValue(UsCoV1Field.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCoV1.setFieldValue(UsCoV1Field.MSPA_OPT_OUT_OPTION_MODE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCoV1.setFieldValue(UsCoV1Field.MSPA_SERVICE_PROVIDER_MODE, 5);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }

  }

  @Test
  public void testValidateSale() {
    UsCoV1 usCoV1 = new UsCoV1();
    
    try {
      usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT_NOTICE, 0);
      usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT, 1);
      usCoV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT_NOTICE, 0);
      usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT, 2);
      usCoV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT_NOTICE, 1);
      usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT, 0);
      usCoV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT_NOTICE, 2);
      usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT, 0);
      usCoV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT_NOTICE, 2);
      usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT, 2);
      usCoV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }

    usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT_NOTICE, 0);
    usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT, 0);
    usCoV1.encode();
    
    usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT_NOTICE, 1);
    usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT, 1);
    usCoV1.encode();
    
    usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT_NOTICE, 1);
    usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT, 2);
    usCoV1.encode();
    
    usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT_NOTICE, 2);
    usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT, 1);
    usCoV1.encode();
  }
  
  @Test
  public void testValidateTargetedAdvertising() {
    UsCoV1 usCoV1 = new UsCoV1();
    
    try {
      usCoV1.setFieldValue(UsCoV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
      usCoV1.setFieldValue(UsCoV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
      usCoV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCoV1.setFieldValue(UsCoV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
      usCoV1.setFieldValue(UsCoV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
      usCoV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCoV1.setFieldValue(UsCoV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
      usCoV1.setFieldValue(UsCoV1Field.TARGETED_ADVERTISING_OPT_OUT, 0);
      usCoV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCoV1.setFieldValue(UsCoV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
      usCoV1.setFieldValue(UsCoV1Field.TARGETED_ADVERTISING_OPT_OUT, 0);
      usCoV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCoV1.setFieldValue(UsCoV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
      usCoV1.setFieldValue(UsCoV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
      usCoV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }

    usCoV1.setFieldValue(UsCoV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
    usCoV1.setFieldValue(UsCoV1Field.TARGETED_ADVERTISING_OPT_OUT, 0);
    usCoV1.encode();
    
    usCoV1.setFieldValue(UsCoV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usCoV1.setFieldValue(UsCoV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
    usCoV1.encode();
    
    usCoV1.setFieldValue(UsCoV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usCoV1.setFieldValue(UsCoV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
    usCoV1.encode();
    
    usCoV1.setFieldValue(UsCoV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
    usCoV1.setFieldValue(UsCoV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
    usCoV1.encode();
  }
  
  @Test
  public void testValidateMspaServiceProviderMode() {
    UsCoV1 usCoV1 = new UsCoV1();
    
    try {
      usCoV1.setFieldValue(UsCoV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usCoV1.setFieldValue(UsCoV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
      usCoV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCoV1.setFieldValue(UsCoV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usCoV1.setFieldValue(UsCoV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
      usCoV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCoV1.setFieldValue(UsCoV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
      usCoV1.setFieldValue(UsCoV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
      usCoV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCoV1.setFieldValue(UsCoV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
      usCoV1.setFieldValue(UsCoV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
      usCoV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCoV1.setFieldValue(UsCoV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
      usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT_NOTICE, 1);
      usCoV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCoV1.setFieldValue(UsCoV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
      usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT_NOTICE, 2);
      usCoV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    


    try {
      usCoV1.setFieldValue(UsCoV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT_NOTICE, 1);
      usCoV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCoV1.setFieldValue(UsCoV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT_NOTICE, 2);
      usCoV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    
    
    usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT_NOTICE, 0);
    
    usCoV1.setFieldValue(UsCoV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usCoV1.setFieldValue(UsCoV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
    usCoV1.encode();
    
    usCoV1.setFieldValue(UsCoV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usCoV1.setFieldValue(UsCoV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usCoV1.encode();
    
    usCoV1.setFieldValue(UsCoV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usCoV1.setFieldValue(UsCoV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    usCoV1.encode();
    
    usCoV1.setFieldValue(UsCoV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
    usCoV1.setFieldValue(UsCoV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    usCoV1.encode();
    
    usCoV1.setFieldValue(UsCoV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usCoV1.setFieldValue(UsCoV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usCoV1.encode();
    
    
    usCoV1.setFieldValue(UsCoV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
    
    usCoV1.setFieldValue(UsCoV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT_NOTICE, 0);
    usCoV1.encode();
    
    usCoV1.setFieldValue(UsCoV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
    usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT_NOTICE, 0);
    usCoV1.encode();
    
    usCoV1.setFieldValue(UsCoV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT_NOTICE, 0);
    usCoV1.encode();
    
    usCoV1.setFieldValue(UsCoV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT_NOTICE, 1);
    usCoV1.encode();
    
    usCoV1.setFieldValue(UsCoV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT_NOTICE, 2);
    usCoV1.encode();
  }
  
  @Test
  public void testEncodeWithGpcSegmentExcluded() {
    UsCoV1 usCoV1 = new UsCoV1();
    usCoV1.setFieldValue(UsCoV1Field.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BAAAAEA", usCoV1.encode());
  }

  @Test
  public void testDecode1() {
    UsCoV1 usCoV1 = new UsCoV1("BVWSSVg.YA");

    Assertions.assertEquals(1, usCoV1.getSharingNotice());
    Assertions.assertEquals(1, usCoV1.getSaleOptOutNotice());
    Assertions.assertEquals(1, usCoV1.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usCoV1.getSaleOptOut());
    Assertions.assertEquals(1, usCoV1.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2), usCoV1.getSensitiveDataProcessing());
    Assertions.assertEquals(1, usCoV1.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usCoV1.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usCoV1.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usCoV1.getMspaServiceProviderMode());
    Assertions.assertEquals(true, usCoV1.getGpc());
  }

  @Test
  public void testDecodeWithGpcSegmentExcluded() {
    UsCoV1 usCoV1 = new UsCoV1("BVWSSVg");

    Assertions.assertEquals(1, usCoV1.getSharingNotice());
    Assertions.assertEquals(1, usCoV1.getSaleOptOutNotice());
    Assertions.assertEquals(1, usCoV1.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usCoV1.getSaleOptOut());
    Assertions.assertEquals(1, usCoV1.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2), usCoV1.getSensitiveDataProcessing());
    Assertions.assertEquals(1, usCoV1.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usCoV1.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usCoV1.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usCoV1.getMspaServiceProviderMode());
    Assertions.assertEquals(false, usCoV1.getGpcSegmentIncluded());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsCoV1("z").getTargetedAdvertisingOptOut();
    });
  }
}
