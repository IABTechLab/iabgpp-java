package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.UspCtV1Field;

public class UspCtV1Test {

  @Test
  public void testEncode1() throws EncodingException {
    UspCtV1 uspCtV1 = new UspCtV1();
    Assertions.assertEquals("BAAAAAAA.Q", uspCtV1.encode());
  }

  @Test
  public void testEncode2() throws EncodingException {
    UspCtV1 uspCtV1 = new UspCtV1();
    
    uspCtV1.setFieldValue(UspCtV1Field.SHARING_NOTICE, 1);
    uspCtV1.setFieldValue(UspCtV1Field.SALE_OPT_OUT_NOTICE, 2);
    uspCtV1.setFieldValue(UspCtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
    uspCtV1.setFieldValue(UspCtV1Field.SALE_OPT_OUT, 1);
    uspCtV1.setFieldValue(UspCtV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
    uspCtV1.setFieldValue(UspCtV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 0, 1, 2, 3));
    uspCtV1.setFieldValue(UspCtV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(1, 2, 3));
    uspCtV1.setFieldValue(UspCtV1Field.MSPA_COVERED_TRANSACTION, 1);
    uspCtV1.setFieldValue(UspCtV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    uspCtV1.setFieldValue(UspCtV1Field.MSPA_SERVICE_PROVIDER_MODE, 3);
    uspCtV1.setFieldValue(UspCtV1Field.GPC, true);
    
    Assertions.assertEquals("BbYbG22w.Y", uspCtV1.encode());    
  }
  
  @Test
  public void testDecode1() throws DecodingException {
    UspCtV1 uspCtV1 = new UspCtV1("BbYbG22w.Y");
    
    Assertions.assertEquals(1, uspCtV1.getSharingNotice()); 
    Assertions.assertEquals(2, uspCtV1.getSaleOptOutNotice()); 
    Assertions.assertEquals(3, uspCtV1.getTargetedAdvertisingOptOutNotice()); 
    Assertions.assertEquals(1, uspCtV1.getSaleOptOut()); 
    Assertions.assertEquals(2, uspCtV1.getTargetedAdvertisingOptOut()); 
    Assertions.assertEquals(Arrays.asList(0, 1, 2, 3, 0, 1, 2, 3), uspCtV1.getSensitiveDataProcessing()); 
    Assertions.assertEquals(Arrays.asList(1, 2, 3), uspCtV1.getKnownChildSensitiveDataConsents()); 
    Assertions.assertEquals(1, uspCtV1.getMspaCoveredTransaction()); 
    Assertions.assertEquals(2, uspCtV1.getMspaOptOutOptionMode()); 
    Assertions.assertEquals(3, uspCtV1.getMspaServiceProviderMode()); 
    Assertions.assertEquals(true, uspCtV1.getGpc()); 
  }
}
