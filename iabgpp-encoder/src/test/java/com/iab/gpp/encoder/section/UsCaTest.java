package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsCaField;

public class UsCaTest {

  @Test
  public void testEncode1() {

    UsCa usCa = new UsCa();
    Assertions.assertEquals("BAAAAABA.QA", usCa.encode());
  }

  @Test
  public void testEncode2() {
    UsCa usCa = new UsCa();

    usCa.setFieldValue(UsCaField.SALE_OPT_OUT_NOTICE, 1);
    usCa.setFieldValue(UsCaField.SHARING_OPT_OUT_NOTICE, 1);
    usCa.setFieldValue(UsCaField.SENSITIVE_DATA_LIMIT_USE_NOTICE, 1);
    usCa.setFieldValue(UsCaField.SALE_OPT_OUT, 1);
    usCa.setFieldValue(UsCaField.SHARING_OPT_OUT, 1);
    usCa.setFieldValue(UsCaField.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0));
    usCa.setFieldValue(UsCaField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(2, 1));
    usCa.setFieldValue(UsCaField.PERSONAL_DATA_CONSENTS, 1);
    usCa.setFieldValue(UsCaField.MSPA_COVERED_TRANSACTION, 1);
    usCa.setFieldValue(UsCaField.MSPA_OPT_OUT_OPTION_MODE, 1);
    usCa.setFieldValue(UsCaField.MSPA_SERVICE_PROVIDER_MODE, 2);
    usCa.setFieldValue(UsCaField.GPC, true);

    Assertions.assertEquals("BVWSSSVY.YA", usCa.encode());
  }

  @Test
  public void testEncode3() {
    UsCa usCa = new UsCa();

    usCa.setFieldValue(UsCaField.SALE_OPT_OUT_NOTICE, 1);
    usCa.setFieldValue(UsCaField.SHARING_OPT_OUT_NOTICE, 1);
    usCa.setFieldValue(UsCaField.SENSITIVE_DATA_LIMIT_USE_NOTICE, 1);
    usCa.setFieldValue(UsCaField.SALE_OPT_OUT, 1);
    usCa.setFieldValue(UsCaField.SHARING_OPT_OUT, 1);
    usCa.setFieldValue(UsCaField.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0));
    usCa.setFieldValue(UsCaField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(2, 1));
    usCa.setFieldValue(UsCaField.PERSONAL_DATA_CONSENTS, 1);
    usCa.setFieldValue(UsCaField.MSPA_COVERED_TRANSACTION, 1);
    usCa.setFieldValue(UsCaField.MSPA_OPT_OUT_OPTION_MODE, 1);
    usCa.setFieldValue(UsCaField.MSPA_SERVICE_PROVIDER_MODE, 2);
    usCa.setFieldValue(UsCaField.GPC, true);

    Assertions.assertEquals("BVWSSSVY.YA", usCa.encode());
  }
  
  @Test
  public void testSetInvalidValues() {
    UsCa usCa = new UsCa();

    try {
      usCa.setFieldValue(UsCaField.SALE_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCa.setFieldValue(UsCaField.SHARING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCa.setFieldValue(UsCaField.SENSITIVE_DATA_LIMIT_USE_NOTICE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCa.setFieldValue(UsCaField.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCa.setFieldValue(UsCaField.SHARING_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCa.setFieldValue(UsCaField.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 0, 0, 0, 3, 0, 0, 0, 0));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCa.setFieldValue(UsCaField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(0, 3));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCa.setFieldValue(UsCaField.PERSONAL_DATA_CONSENTS, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCa.setFieldValue(UsCaField.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCa.setFieldValue(UsCaField.MSPA_OPT_OUT_OPTION_MODE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usCa.setFieldValue(UsCaField.MSPA_SERVICE_PROVIDER_MODE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
  }
  
  @Test
  public void testEncodeWithGpcSegmentExcluded() {

    UsCa usCa = new UsCa();
    usCa.setFieldValue(UsCaField.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BAAAAABA", usCa.encode());
  }

  @Test
  public void testDecode1() {
    UsCa usCa = new UsCa("BVWSSSVY.YA");

    Assertions.assertEquals(1, usCa.getSaleOptOutNotice());
    Assertions.assertEquals(1, usCa.getSharingOptOut());
    Assertions.assertEquals(1, usCa.getSensitiveDataLimitUseNotice());
    Assertions.assertEquals(1, usCa.getSaleOptOut());
    Assertions.assertEquals(1, usCa.getSharingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0), usCa.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1), usCa.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usCa.getPersonalDataConsents());
    Assertions.assertEquals(1, usCa.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usCa.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usCa.getMspaServiceProviderMode());
    Assertions.assertEquals(true, usCa.getGpc());
  }

  @Test
  public void testDecodeWithGpcSegmentExcluded() {
    UsCa usCa = new UsCa("BVWSSSVY");

    Assertions.assertEquals(1, usCa.getSaleOptOutNotice());
    Assertions.assertEquals(1, usCa.getSharingOptOut());
    Assertions.assertEquals(1, usCa.getSensitiveDataLimitUseNotice());
    Assertions.assertEquals(1, usCa.getSaleOptOut());
    Assertions.assertEquals(1, usCa.getSharingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0), usCa.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1), usCa.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usCa.getPersonalDataConsents());
    Assertions.assertEquals(1, usCa.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usCa.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usCa.getMspaServiceProviderMode());
    Assertions.assertEquals(false, usCa.getGpcSegmentIncluded());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsCa("z").getPersonalDataConsents();
    });
  }
}
