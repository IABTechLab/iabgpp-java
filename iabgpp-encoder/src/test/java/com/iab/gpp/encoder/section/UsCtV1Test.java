package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsCtV1Field;

public class UsCtV1Test {

  @Test
  public void testEncode1() {
    UsCtV1 usCtV1 = new UsCtV1();
    Assertions.assertEquals("BAAAAAEA.QA", usCtV1.encode());
  }

  @Test
  public void testEncode2() {
    UsCtV1 usCtV1 = new UsCtV1();

    usCtV1.setFieldValue(UsCtV1Field.SHARING_NOTICE, 1);
    usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT_NOTICE, 1);
    usCtV1.setFieldValue(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT, 1);
    usCtV1.setFieldValue(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
    usCtV1.setFieldValue(UsCtV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1));
    usCtV1.setFieldValue(UsCtV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(2, 1, 0));
    usCtV1.setFieldValue(UsCtV1Field.MSPA_COVERED_TRANSACTION, 1);
    usCtV1.setFieldValue(UsCtV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usCtV1.setFieldValue(UsCtV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usCtV1.setFieldValue(UsCtV1Field.GPC, true);

    Assertions.assertEquals("BVWSSZFg.YA", usCtV1.encode());
  }
  
  @Test
  public void testSetInvalidValues() {
    UsCtV1 usCtV1 = new UsCtV1();

    try {
      usCtV1.setFieldValue(UsCtV1Field.SHARING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCtV1.setFieldValue(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCtV1.setFieldValue(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCtV1.setFieldValue(UsCtV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 1, 2, 0, 1));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCtV1.setFieldValue(UsCtV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(1, 2, 3));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCtV1.setFieldValue(UsCtV1Field.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCtV1.setFieldValue(UsCtV1Field.MSPA_OPT_OUT_OPTION_MODE, 4);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCtV1.setFieldValue(UsCtV1Field.MSPA_SERVICE_PROVIDER_MODE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
  }

  @Test
  public void testValidateSale() {
    UsCtV1 usCtV1 = new UsCtV1();
    
    try {
      usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT_NOTICE, 0);
      usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT, 1);
      usCtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT_NOTICE, 0);
      usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT, 2);
      usCtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT_NOTICE, 1);
      usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT, 0);
      usCtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT_NOTICE, 2);
      usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT, 0);
      usCtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT_NOTICE, 2);
      usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT, 2);
      usCtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }

    usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT_NOTICE, 0);
    usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT, 0);
    usCtV1.encode();
    
    usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT_NOTICE, 1);
    usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT, 1);
    usCtV1.encode();
    
    usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT_NOTICE, 1);
    usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT, 2);
    usCtV1.encode();
    
    usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT_NOTICE, 2);
    usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT, 1);
    usCtV1.encode();
  }
  
  @Test
  public void testValidateTargetedAdvertising() {
    UsCtV1 usCtV1 = new UsCtV1();
    
    try {
      usCtV1.setFieldValue(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
      usCtV1.setFieldValue(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
      usCtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCtV1.setFieldValue(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
      usCtV1.setFieldValue(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
      usCtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCtV1.setFieldValue(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
      usCtV1.setFieldValue(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT, 0);
      usCtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCtV1.setFieldValue(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
      usCtV1.setFieldValue(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT, 0);
      usCtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCtV1.setFieldValue(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
      usCtV1.setFieldValue(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
      usCtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }

    usCtV1.setFieldValue(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 0);
    usCtV1.setFieldValue(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT, 0);
    usCtV1.encode();
    
    usCtV1.setFieldValue(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usCtV1.setFieldValue(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
    usCtV1.encode();
    
    usCtV1.setFieldValue(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usCtV1.setFieldValue(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
    usCtV1.encode();
    
    usCtV1.setFieldValue(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 2);
    usCtV1.setFieldValue(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
    usCtV1.encode();
  }
  
  @Test
  public void testValidateMspaServiceProviderMode() {
    UsCtV1 usCtV1 = new UsCtV1();
    
    try {
      usCtV1.setFieldValue(UsCtV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usCtV1.setFieldValue(UsCtV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
      usCtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCtV1.setFieldValue(UsCtV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usCtV1.setFieldValue(UsCtV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
      usCtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCtV1.setFieldValue(UsCtV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
      usCtV1.setFieldValue(UsCtV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
      usCtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCtV1.setFieldValue(UsCtV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
      usCtV1.setFieldValue(UsCtV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
      usCtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCtV1.setFieldValue(UsCtV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
      usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT_NOTICE, 1);
      usCtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCtV1.setFieldValue(UsCtV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
      usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT_NOTICE, 2);
      usCtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    

    try {
      usCtV1.setFieldValue(UsCtV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT_NOTICE, 1);
      usCtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCtV1.setFieldValue(UsCtV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
      usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT_NOTICE, 2);
      usCtV1.encode();
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    
    
    usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT_NOTICE, 0);
    
    usCtV1.setFieldValue(UsCtV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usCtV1.setFieldValue(UsCtV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
    usCtV1.encode();
    
    usCtV1.setFieldValue(UsCtV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usCtV1.setFieldValue(UsCtV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usCtV1.encode();
    
    usCtV1.setFieldValue(UsCtV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usCtV1.setFieldValue(UsCtV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    usCtV1.encode();
    
    usCtV1.setFieldValue(UsCtV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
    usCtV1.setFieldValue(UsCtV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    usCtV1.encode();
    
    usCtV1.setFieldValue(UsCtV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usCtV1.setFieldValue(UsCtV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usCtV1.encode();
    
    
    usCtV1.setFieldValue(UsCtV1Field.MSPA_OPT_OUT_OPTION_MODE, 0);
    
    usCtV1.setFieldValue(UsCtV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT_NOTICE, 0);
    usCtV1.encode();
    
    usCtV1.setFieldValue(UsCtV1Field.MSPA_SERVICE_PROVIDER_MODE, 1);
    usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT_NOTICE, 0);
    usCtV1.encode();
    
    usCtV1.setFieldValue(UsCtV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT_NOTICE, 0);
    usCtV1.encode();
    
    usCtV1.setFieldValue(UsCtV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT_NOTICE, 1);
    usCtV1.encode();
    
    usCtV1.setFieldValue(UsCtV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT_NOTICE, 2);
    usCtV1.encode();
  }
  
  @Test
  public void testEncodeWithGpcSegmentExcluded() {
    UsCtV1 usCtV1 = new UsCtV1();
    usCtV1.setFieldValue(UsCtV1Field.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BAAAAAEA", usCtV1.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsCtV1 usCtV1 = new UsCtV1("BVWSSZFg.YA");

    Assertions.assertEquals(1, usCtV1.getSharingNotice());
    Assertions.assertEquals(1, usCtV1.getSaleOptOutNotice());
    Assertions.assertEquals(1, usCtV1.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usCtV1.getSaleOptOut());
    Assertions.assertEquals(1, usCtV1.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usCtV1.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1, 0), usCtV1.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usCtV1.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usCtV1.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usCtV1.getMspaServiceProviderMode());
    Assertions.assertEquals(true, usCtV1.getGpc());
  }

  @Test
  public void testDecodeWithGpcSegmentExcluded() throws DecodingException {
    UsCtV1 usCtV1 = new UsCtV1("BVWSSZFg");

    Assertions.assertEquals(1, usCtV1.getSharingNotice());
    Assertions.assertEquals(1, usCtV1.getSaleOptOutNotice());
    Assertions.assertEquals(1, usCtV1.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usCtV1.getSaleOptOut());
    Assertions.assertEquals(1, usCtV1.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usCtV1.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1, 0), usCtV1.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usCtV1.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usCtV1.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usCtV1.getMspaServiceProviderMode());
    Assertions.assertEquals(false, usCtV1.getGpcSegmentIncluded());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsCtV1("z").getSharingNotice();
    });
  }
}
