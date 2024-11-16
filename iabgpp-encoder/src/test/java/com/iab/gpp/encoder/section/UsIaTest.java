package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsIaField;

public class UsIaTest {

  @Test
  public void testEncode1() {
    UsIa usIa = new UsIa();
    Assertions.assertEquals("BAAAAAQA.QA", usIa.encode());
  }

  @Test
  public void testEncode2() {
    UsIa usIa = new UsIa();

    usIa.setFieldValue(UsIaField.PROCESSING_NOTICE, 1);
    usIa.setFieldValue(UsIaField.SALE_OPT_OUT_NOTICE, 1);
    usIa.setFieldValue(UsIaField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usIa.setFieldValue(UsIaField.SENSITIVE_DATA_OPT_OUT_NOTICE, 1);
    usIa.setFieldValue(UsIaField.SALE_OPT_OUT, 1);
    usIa.setFieldValue(UsIaField.TARGETED_ADVERTISING_OPT_OUT, 1);
    usIa.setFieldValue(UsIaField.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1));
    usIa.setFieldValue(UsIaField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 1);
    usIa.setFieldValue(UsIaField.MSPA_COVERED_TRANSACTION, 1);
    usIa.setFieldValue(UsIaField.MSPA_OPT_OUT_OPTION_MODE, 1);
    usIa.setFieldValue(UsIaField.MSPA_SERVICE_PROVIDER_MODE, 2);
    usIa.setFieldValue(UsIaField.GPC, true);

    Assertions.assertEquals("BVVkklWA.YA", usIa.encode());
  }
  
  @Test
  public void testSetInvalidValues() {
    UsIa usIa = new UsIa();

    try {
      usIa.setFieldValue(UsIaField.PROCESSING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usIa.setFieldValue(UsIaField.SALE_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usIa.setFieldValue(UsIaField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usIa.setFieldValue(UsIaField.SENSITIVE_DATA_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usIa.setFieldValue(UsIaField.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usIa.setFieldValue(UsIaField.TARGETED_ADVERTISING_OPT_OUT, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usIa.setFieldValue(UsIaField.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 1, 2, 0, 1));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usIa.setFieldValue(UsIaField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usIa.setFieldValue(UsIaField.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usIa.setFieldValue(UsIaField.MSPA_OPT_OUT_OPTION_MODE, 4);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usIa.setFieldValue(UsIaField.MSPA_SERVICE_PROVIDER_MODE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
  }

  @Test
  public void testEncodeWithGpcSegmentExcluded() {
    UsIa usIa = new UsIa();
    usIa.setFieldValue(UsIaField.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BAAAAAQA", usIa.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsIa usIa = new UsIa("BVVkklWA.YA");

    Assertions.assertEquals(1, usIa.getProcessingNotice());
    Assertions.assertEquals(1, usIa.getSaleOptOutNotice());
    Assertions.assertEquals(1, usIa.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usIa.getSensitiveDataOptOutNotice());
    Assertions.assertEquals(1, usIa.getSaleOptOut());
    Assertions.assertEquals(1, usIa.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usIa.getSensitiveDataProcessing());
    Assertions.assertEquals(1, usIa.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usIa.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usIa.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usIa.getMspaServiceProviderMode());
    Assertions.assertEquals(true, usIa.getGpc());
  }

  @Test
  public void testDecodeWithGpcSegmentExcluded() throws DecodingException {
    UsIa usIa = new UsIa("BVVkklWA");

    Assertions.assertEquals(1, usIa.getProcessingNotice());
    Assertions.assertEquals(1, usIa.getSaleOptOutNotice());
    Assertions.assertEquals(1, usIa.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usIa.getSensitiveDataOptOutNotice());
    Assertions.assertEquals(1, usIa.getSaleOptOut());
    Assertions.assertEquals(1, usIa.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usIa.getSensitiveDataProcessing());
    Assertions.assertEquals(1, usIa.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usIa.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usIa.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usIa.getMspaServiceProviderMode());
    Assertions.assertEquals(false, usIa.getGpcSegmentIncluded());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsIa("z").getProcessingNotice();
    });
  }
}
