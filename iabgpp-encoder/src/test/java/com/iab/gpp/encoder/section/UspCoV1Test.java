package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.UspCoV1Field;

public class UspCoV1Test {

  @Test
  public void testEncode1() throws EncodingException {
    UspCoV1 uspCoV1 = new UspCoV1();
    Assertions.assertEquals("BAAAAAAA.QAAA", uspCoV1.encode());
  }

  @Test
  public void testEncode2() throws EncodingException {
    UspCoV1 uspCoV1 = new UspCoV1();
    
    uspCoV1.setFieldValue(UspCoV1Field.SHARING_NOTICE, 1);
    uspCoV1.setFieldValue(UspCoV1Field.SALE_OPT_OUT_NOTICE, 2);
    uspCoV1.setFieldValue(UspCoV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
    uspCoV1.setFieldValue(UspCoV1Field.SALE_OPT_OUT, 1);
    uspCoV1.setFieldValue(UspCoV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
    uspCoV1.setFieldValue(UspCoV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 0, 1, 2));
    uspCoV1.setFieldValue(UspCoV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 3);
    uspCoV1.setFieldValue(UspCoV1Field.MSPA_COVERED_TRANSACTION, 1);
    uspCoV1.setFieldValue(UspCoV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    uspCoV1.setFieldValue(UspCoV1Field.MSPA_SERVICE_PROVIDER_MODE, 3);
    uspCoV1.setFieldValue(UspCoV1Field.GPC, true);
    
    Assertions.assertEquals("BbYbG2wA.YAAA", uspCoV1.encode());    
  }
  
  @Test
  public void testDecode1() throws DecodingException {
    UspCoV1 uspCoV1 = new UspCoV1("BbYbG2wA.YAAA");
    
    Assertions.assertEquals(1, uspCoV1.getSharingNotice()); 
    Assertions.assertEquals(2, uspCoV1.getSaleOptOutNotice()); 
    Assertions.assertEquals(3, uspCoV1.getTargetedAdvertisingOptOutNotice()); 
    Assertions.assertEquals(1, uspCoV1.getSaleOptOut()); 
    Assertions.assertEquals(2, uspCoV1.getTargetedAdvertisingOptOut()); 
    Assertions.assertEquals(Arrays.asList(0, 1, 2, 3, 0, 1, 2), uspCoV1.getSensitiveDataProcessing()); 
    Assertions.assertEquals(3, uspCoV1.getKnownChildSensitiveDataConsents()); 
    Assertions.assertEquals(1, uspCoV1.getMspaCoveredTransaction()); 
    Assertions.assertEquals(2, uspCoV1.getMspaOptOutOptionMode()); 
    Assertions.assertEquals(3, uspCoV1.getMspaServiceProviderMode()); 
    Assertions.assertEquals(true, uspCoV1.getGpc()); 
  }
}
