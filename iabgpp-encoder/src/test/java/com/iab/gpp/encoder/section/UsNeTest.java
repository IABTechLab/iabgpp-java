package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsNeField;

public class UsNeTest {

  @Test
  public void testEncode1() {
    UsNe usNe = new UsNe();
    Assertions.assertEquals("BAAAAAQA.QA", usNe.encode());
  }

  @Test
  public void testEncode2() {
    UsNe usNe = new UsNe();

    usNe.setFieldValue(UsNeField.PROCESSING_NOTICE, 1);
    usNe.setFieldValue(UsNeField.SALE_OPT_OUT_NOTICE, 1);
    usNe.setFieldValue(UsNeField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usNe.setFieldValue(UsNeField.SALE_OPT_OUT, 1);
    usNe.setFieldValue(UsNeField.TARGETED_ADVERTISING_OPT_OUT, 1);
    usNe.setFieldValue(UsNeField.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1));
    usNe.setFieldValue(UsNeField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 1);
    usNe.setFieldValue(UsNeField.ADDITIONAL_DATA_PROCESSING_CONSENT, 1);
    usNe.setFieldValue(UsNeField.MSPA_COVERED_TRANSACTION, 1);
    usNe.setFieldValue(UsNeField.MSPA_OPT_OUT_OPTION_MODE, 1);
    usNe.setFieldValue(UsNeField.MSPA_SERVICE_PROVIDER_MODE, 2);
    usNe.setFieldValue(UsNeField.GPC, true);

    Assertions.assertEquals("BVWSSVWA.YA", usNe.encode());
  }
  
  @Test
  public void testSetInvalidValues() {
    UsNe usNe = new UsNe();

    try {
      usNe.setFieldValue(UsNeField.PROCESSING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNe.setFieldValue(UsNeField.SALE_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNe.setFieldValue(UsNeField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNe.setFieldValue(UsNeField.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNe.setFieldValue(UsNeField.TARGETED_ADVERTISING_OPT_OUT, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNe.setFieldValue(UsNeField.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 1, 2, 0, 1));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNe.setFieldValue(UsNeField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNe.setFieldValue(UsNeField.ADDITIONAL_DATA_PROCESSING_CONSENT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNe.setFieldValue(UsNeField.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNe.setFieldValue(UsNeField.MSPA_OPT_OUT_OPTION_MODE, 4);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usNe.setFieldValue(UsNeField.MSPA_SERVICE_PROVIDER_MODE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
  }

  @Test
  public void testEncodeWithGpcSegmentExcluded() {
    UsNe usNe = new UsNe();
    usNe.setFieldValue(UsNeField.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BAAAAAQA", usNe.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsNe usNe = new UsNe("BVWSSVWA.YA");

    Assertions.assertEquals(1, usNe.getProcessingNotice());
    Assertions.assertEquals(1, usNe.getSaleOptOutNotice());
    Assertions.assertEquals(1, usNe.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usNe.getSaleOptOut());
    Assertions.assertEquals(1, usNe.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usNe.getSensitiveDataProcessing());
    Assertions.assertEquals(1, usNe.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usNe.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usNe.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usNe.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usNe.getMspaServiceProviderMode());
    Assertions.assertEquals(true, usNe.getGpc());
  }

  @Test
  public void testDecodeWithGpcSegmentExcluded() throws DecodingException {
    UsNe usNe = new UsNe("BVWSSVWA");

    Assertions.assertEquals(1, usNe.getProcessingNotice());
    Assertions.assertEquals(1, usNe.getSaleOptOutNotice());
    Assertions.assertEquals(1, usNe.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usNe.getSaleOptOut());
    Assertions.assertEquals(1, usNe.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usNe.getSensitiveDataProcessing());
    Assertions.assertEquals(1, usNe.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usNe.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usNe.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usNe.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usNe.getMspaServiceProviderMode());
    Assertions.assertEquals(false, usNe.getGpcSegmentIncluded());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsNe("z").getProcessingNotice();
    });
  }
}
