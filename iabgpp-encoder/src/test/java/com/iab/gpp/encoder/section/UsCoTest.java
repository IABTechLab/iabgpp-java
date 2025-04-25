package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsCoField;

public class UsCoTest {

  @Test
  public void testEncode1() {
    UsCo usCo = new UsCo();
    Assertions.assertEquals("BAAAAEA.QA", usCo.encode());
  }

  @Test
  public void testEncode2() {
    UsCo usCo = new UsCo();

    usCo.setFieldValue(UsCoField.SHARING_NOTICE, 1);
    usCo.setFieldValue(UsCoField.SALE_OPT_OUT_NOTICE, 1);
    usCo.setFieldValue(UsCoField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usCo.setFieldValue(UsCoField.SALE_OPT_OUT, 1);
    usCo.setFieldValue(UsCoField.TARGETED_ADVERTISING_OPT_OUT, 1);
    usCo.setFieldValue(UsCoField.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2));
    usCo.setFieldValue(UsCoField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 1);
    usCo.setFieldValue(UsCoField.MSPA_COVERED_TRANSACTION, 1);
    usCo.setFieldValue(UsCoField.MSPA_OPT_OUT_OPTION_MODE, 1);
    usCo.setFieldValue(UsCoField.MSPA_SERVICE_PROVIDER_MODE, 2);
    usCo.setFieldValue(UsCoField.GPC, true);

    Assertions.assertEquals("BVWSSVg.YA", usCo.encode());
  }
  
  @Test
  public void testSetInvalidValues() {
    UsCo usCo = new UsCo();

    try {
      usCo.setFieldValue(UsCoField.SHARING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCo.setFieldValue(UsCoField.SALE_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCo.setFieldValue(UsCoField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCo.setFieldValue(UsCoField.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCo.setFieldValue(UsCoField.TARGETED_ADVERTISING_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCo.setFieldValue(UsCoField.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 0, 1, 2));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCo.setFieldValue(UsCoField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCo.setFieldValue(UsCoField.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCo.setFieldValue(UsCoField.MSPA_OPT_OUT_OPTION_MODE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCo.setFieldValue(UsCoField.MSPA_SERVICE_PROVIDER_MODE, 5);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }

  }

  @Test
  public void testEncodeWithGpcSegmentExcluded() {
    UsCo usCo = new UsCo();
    usCo.setFieldValue(UsCoField.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BAAAAEA", usCo.encode());
  }

  @Test
  public void testDecode1() {
    UsCo usCo = new UsCo("BVWSSVg.YA");

    Assertions.assertEquals(1, usCo.getSharingNotice());
    Assertions.assertEquals(1, usCo.getSaleOptOutNotice());
    Assertions.assertEquals(1, usCo.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usCo.getSaleOptOut());
    Assertions.assertEquals(1, usCo.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2), usCo.getSensitiveDataProcessing());
    Assertions.assertEquals(1, usCo.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usCo.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usCo.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usCo.getMspaServiceProviderMode());
    Assertions.assertEquals(true, usCo.getGpc());
  }

  @Test
  public void testDecodeWithGpcSegmentExcluded() {
    UsCo usCo = new UsCo("BVWSSVg");

    Assertions.assertEquals(1, usCo.getSharingNotice());
    Assertions.assertEquals(1, usCo.getSaleOptOutNotice());
    Assertions.assertEquals(1, usCo.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usCo.getSaleOptOut());
    Assertions.assertEquals(1, usCo.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2), usCo.getSensitiveDataProcessing());
    Assertions.assertEquals(1, usCo.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usCo.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usCo.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usCo.getMspaServiceProviderMode());
    Assertions.assertEquals(false, usCo.getGpcSegmentIncluded());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsCo("z").getTargetedAdvertisingOptOut();
    });
  }
}
