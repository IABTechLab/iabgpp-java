package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsTxField;

public class UsTxTest {

  @Test
  public void testEncode1() {
    UsTx usTx = new UsTx();
    Assertions.assertEquals("BAAAAAQA.QA", usTx.encode());
  }

  @Test
  public void testEncode2() {
    UsTx usTx = new UsTx();

    usTx.setFieldValue(UsTxField.PROCESSING_NOTICE, 1);
    usTx.setFieldValue(UsTxField.SALE_OPT_OUT_NOTICE, 1);
    usTx.setFieldValue(UsTxField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usTx.setFieldValue(UsTxField.SALE_OPT_OUT, 1);
    usTx.setFieldValue(UsTxField.TARGETED_ADVERTISING_OPT_OUT, 1);
    usTx.setFieldValue(UsTxField.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1));
    usTx.setFieldValue(UsTxField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 1);
    usTx.setFieldValue(UsTxField.ADDITIONAL_DATA_PROCESSING_CONSENT, 1);
    usTx.setFieldValue(UsTxField.MSPA_COVERED_TRANSACTION, 1);
    usTx.setFieldValue(UsTxField.MSPA_OPT_OUT_OPTION_MODE, 1);
    usTx.setFieldValue(UsTxField.MSPA_SERVICE_PROVIDER_MODE, 2);
    usTx.setFieldValue(UsTxField.GPC, true);

    Assertions.assertEquals("BVWSSVWA.YA", usTx.encode());
  }
  
  @Test
  public void testSetInvalidValues() {
    UsTx usTx = new UsTx();

    try {
      usTx.setFieldValue(UsTxField.PROCESSING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTx.setFieldValue(UsTxField.SALE_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTx.setFieldValue(UsTxField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTx.setFieldValue(UsTxField.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTx.setFieldValue(UsTxField.TARGETED_ADVERTISING_OPT_OUT, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTx.setFieldValue(UsTxField.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 1, 2, 0, 1));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTx.setFieldValue(UsTxField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTx.setFieldValue(UsTxField.ADDITIONAL_DATA_PROCESSING_CONSENT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTx.setFieldValue(UsTxField.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTx.setFieldValue(UsTxField.MSPA_OPT_OUT_OPTION_MODE, 4);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usTx.setFieldValue(UsTxField.MSPA_SERVICE_PROVIDER_MODE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
  }

  @Test
  public void testEncodeWithGpcSegmentExcluded() {
    UsTx usTx = new UsTx();
    usTx.setFieldValue(UsTxField.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BAAAAAQA", usTx.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsTx usTx = new UsTx("BVWSSVWA.YA");

    Assertions.assertEquals(1, usTx.getProcessingNotice());
    Assertions.assertEquals(1, usTx.getSaleOptOutNotice());
    Assertions.assertEquals(1, usTx.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usTx.getSaleOptOut());
    Assertions.assertEquals(1, usTx.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usTx.getSensitiveDataProcessing());
    Assertions.assertEquals(1, usTx.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usTx.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usTx.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usTx.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usTx.getMspaServiceProviderMode());
    Assertions.assertEquals(true, usTx.getGpc());
  }

  @Test
  public void testDecodeWithGpcSegmentExcluded() throws DecodingException {
    UsTx usTx = new UsTx("BVWSSVWA");

    Assertions.assertEquals(1, usTx.getProcessingNotice());
    Assertions.assertEquals(1, usTx.getSaleOptOutNotice());
    Assertions.assertEquals(1, usTx.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usTx.getSaleOptOut());
    Assertions.assertEquals(1, usTx.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usTx.getSensitiveDataProcessing());
    Assertions.assertEquals(1, usTx.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usTx.getAdditionalDataProcessingConsent());
    Assertions.assertEquals(1, usTx.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usTx.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usTx.getMspaServiceProviderMode());
    Assertions.assertEquals(false, usTx.getGpcSegmentIncluded());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsTx("z").getProcessingNotice();
    });
  }
}
