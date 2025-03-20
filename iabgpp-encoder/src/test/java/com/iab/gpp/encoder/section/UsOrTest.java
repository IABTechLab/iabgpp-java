package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsOrField;

public class UsOrTest {

  @Test
  public void testEncode1() {
    UsOr usOr = new UsOr();
    Assertions.assertEquals("BAAAAAABAA.QA", usOr.encode());
  }

  @Test
  public void testEncode2() {
    UsOr usOr = new UsOr();

    usOr.setFieldValue(UsOrField.PROCESSING_NOTICE, 1);
    usOr.setFieldValue(UsOrField.SALE_OPT_OUT_NOTICE, 1);
    usOr.setFieldValue(UsOrField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usOr.setFieldValue(UsOrField.SALE_OPT_OUT, 1);
    usOr.setFieldValue(UsOrField.TARGETED_ADVERTISING_OPT_OUT, 1);
    usOr.setFieldValue(UsOrField.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0, 1, 2));
    usOr.setFieldValue(UsOrField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(2, 1, 0));
    usOr.setFieldValue(UsOrField.ADDITIONAL_DATA_PROCESSING_CONSENT, 1);
    usOr.setFieldValue(UsOrField.MSPA_COVERED_TRANSACTION, 1);
    usOr.setFieldValue(UsOrField.MSPA_OPT_OUT_OPTION_MODE, 1);
    usOr.setFieldValue(UsOrField.MSPA_SERVICE_PROVIDER_MODE, 2);
    usOr.setFieldValue(UsOrField.GPC, true);

    Assertions.assertEquals("BVWSSRpFYA.YA", usOr.encode());
  }
  
  @Test
  public void testSetInvalidValues() {
    UsOr usOr = new UsOr();

    try {
      usOr.setFieldValue(UsOrField.PROCESSING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOr.setFieldValue(UsOrField.SALE_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOr.setFieldValue(UsOrField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOr.setFieldValue(UsOrField.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOr.setFieldValue(UsOrField.TARGETED_ADVERTISING_OPT_OUT, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOr.setFieldValue(UsOrField.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 1, 2, 0, 1));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOr.setFieldValue(UsOrField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(1, 2, 3));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOr.setFieldValue(UsOrField.ADDITIONAL_DATA_PROCESSING_CONSENT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOr.setFieldValue(UsOrField.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOr.setFieldValue(UsOrField.MSPA_OPT_OUT_OPTION_MODE, 4);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usOr.setFieldValue(UsOrField.MSPA_SERVICE_PROVIDER_MODE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
  }

  @Test
  public void testEncodeWithGpcSegmentExcluded() {
    UsOr usOr = new UsOr();
    usOr.setFieldValue(UsOrField.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BAAAAAABAA", usOr.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsOr usOr = new UsOr("BVWSSRpFYA.YA");

    Assertions.assertEquals(1, usOr.getProcessingNotice());
    Assertions.assertEquals(1, usOr.getSaleOptOutNotice());
    Assertions.assertEquals(1, usOr.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usOr.getSaleOptOut());
    Assertions.assertEquals(1, usOr.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0, 1, 2), usOr.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1, 0), usOr.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usOr.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usOr.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usOr.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usOr.getMspaServiceProviderMode());
    Assertions.assertEquals(true, usOr.getGpc());
  }

  @Test
  public void testDecodeWithGpcSegmentExcluded() throws DecodingException {
    UsOr usOr = new UsOr("BVWSSRpFYA");

    Assertions.assertEquals(1, usOr.getProcessingNotice());
    Assertions.assertEquals(1, usOr.getSaleOptOutNotice());
    Assertions.assertEquals(1, usOr.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usOr.getSaleOptOut());
    Assertions.assertEquals(1, usOr.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1, 0, 1, 2), usOr.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(2, 1, 0), usOr.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usOr.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usOr.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usOr.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usOr.getMspaServiceProviderMode());
    Assertions.assertEquals(false, usOr.getGpcSegmentIncluded());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsOr("z").getProcessingNotice();
    });
  }
}
