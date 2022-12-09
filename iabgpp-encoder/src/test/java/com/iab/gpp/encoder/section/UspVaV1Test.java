package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.UspVaV1Field;

public class UspVaV1Test {

  @Test
  public void testEncode1() throws EncodingException {
    UspVaV1 uspVaV1 = new UspVaV1();
    Assertions.assertEquals("BAAAAAAA", uspVaV1.encode());
  }

  @Test
  public void testEncode2() throws EncodingException {
    UspVaV1 uspVaV1 = new UspVaV1();
    
    uspVaV1.setFieldValue(UspVaV1Field.SHARING_NOTICE, 1);
    uspVaV1.setFieldValue(UspVaV1Field.SALE_OPT_OUT_NOTICE, 2);
    uspVaV1.setFieldValue(UspVaV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
    uspVaV1.setFieldValue(UspVaV1Field.SALE_OPT_OUT, 1);
    uspVaV1.setFieldValue(UspVaV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
    uspVaV1.setFieldValue(UspVaV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 0, 1, 2, 3));
    uspVaV1.setFieldValue(UspVaV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 3);
    uspVaV1.setFieldValue(UspVaV1Field.MSPA_COVERED_TRANSACTION, 1);
    uspVaV1.setFieldValue(UspVaV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    uspVaV1.setFieldValue(UspVaV1Field.MSPA_SERVICE_PROVIDER_MODE, 3);
    
    Assertions.assertEquals("BbYbG9sA", uspVaV1.encode());    
  }
  
  @Test
  public void testDecode1() throws DecodingException {
    UspVaV1 uspVaV1 = new UspVaV1("BbYbG9sA");
    
    Assertions.assertEquals(1, uspVaV1.getSharingNotice()); 
    Assertions.assertEquals(2, uspVaV1.getSaleOptOutNotice()); 
    Assertions.assertEquals(3, uspVaV1.getTargetedAdvertisingOptOutNotice()); 
    Assertions.assertEquals(1, uspVaV1.getSaleOptOut()); 
    Assertions.assertEquals(2, uspVaV1.getTargetedAdvertisingOptOut()); 
    Assertions.assertEquals(Arrays.asList(0, 1, 2, 3, 0, 1, 2, 3), uspVaV1.getSensitiveDataProcessing()); 
    Assertions.assertEquals(3, uspVaV1.getKnownChildSensitiveDataConsents()); 
    Assertions.assertEquals(1, uspVaV1.getMspaCoveredTransaction()); 
    Assertions.assertEquals(2, uspVaV1.getMspaOptOutOptionMode()); 
    Assertions.assertEquals(3, uspVaV1.getMspaServiceProviderMode()); 
  }
}
