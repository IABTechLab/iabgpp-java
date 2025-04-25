package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsUtField;

public class UsUtTest {

  @Test
  public void testEncode1() {
    UsUt usUt = new UsUt();
    Assertions.assertEquals("BAAAAAQA", usUt.encode());
  }

  @Test
  public void testEncode2() {
    UsUt usUt = new UsUt();

    usUt.setFieldValue(UsUtField.SHARING_NOTICE, 1);
    usUt.setFieldValue(UsUtField.SALE_OPT_OUT_NOTICE, 1);
    usUt.setFieldValue(UsUtField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usUt.setFieldValue(UsUtField.SALE_OPT_OUT, 1);
    usUt.setFieldValue(UsUtField.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE, 1);
    usUt.setFieldValue(UsUtField.TARGETED_ADVERTISING_OPT_OUT, 1);
    usUt.setFieldValue(UsUtField.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1));
    usUt.setFieldValue(UsUtField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 1);
    usUt.setFieldValue(UsUtField.MSPA_COVERED_TRANSACTION, 1);
    usUt.setFieldValue(UsUtField.MSPA_OPT_OUT_OPTION_MODE, 1);
    usUt.setFieldValue(UsUtField.MSPA_SERVICE_PROVIDER_MODE, 2);

    Assertions.assertEquals("BVVkklWA", usUt.encode());
  }
  
  @Test
  public void testSetInvalidValues() {
    UsUt usUt = new UsUt();

    try {
      usUt.setFieldValue(UsUtField.SHARING_NOTICE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUt.setFieldValue(UsUtField.SALE_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUt.setFieldValue(UsUtField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUt.setFieldValue(UsUtField.SALE_OPT_OUT, 4);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUt.setFieldValue(UsUtField.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUt.setFieldValue(UsUtField.TARGETED_ADVERTISING_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUt.setFieldValue(UsUtField.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 0, 1, 2, 0));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUt.setFieldValue(UsUtField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUt.setFieldValue(UsUtField.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUt.setFieldValue(UsUtField.MSPA_OPT_OUT_OPTION_MODE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
    
    try {
      usUt.setFieldValue(UsUtField.MSPA_SERVICE_PROVIDER_MODE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {
      
    }
      
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsUt usUt = new UsUt("BVVkklWA");

    Assertions.assertEquals(1, usUt.getSharingNotice());
    Assertions.assertEquals(1, usUt.getSaleOptOutNotice());
    Assertions.assertEquals(1, usUt.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usUt.getSaleOptOut());
    Assertions.assertEquals(1, usUt.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usUt.getSensitiveDataProcessing());
    Assertions.assertEquals(1, usUt.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usUt.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usUt.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usUt.getMspaServiceProviderMode());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsUt("z").getKnownChildSensitiveDataConsents();
    });
  }
}
