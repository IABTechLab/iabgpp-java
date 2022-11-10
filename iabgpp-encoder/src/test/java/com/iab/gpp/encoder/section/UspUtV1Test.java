package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.UspUtV1Field;

public class UspUtV1Test {

  @Test
  public void testEncode1() throws EncodingException {
    UspUtV1 uspUtV1 = new UspUtV1();
    Assertions.assertEquals("BAAAAAAA", uspUtV1.encode());
  }

  @Test
  public void testEncode2() throws EncodingException {
    UspUtV1 uspUtV1 = new UspUtV1();
    
    uspUtV1.setFieldValue(UspUtV1Field.SHARING_NOTICE, 1);
    uspUtV1.setFieldValue(UspUtV1Field.SALE_OPT_OUT_NOTICE, 2);
    uspUtV1.setFieldValue(UspUtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
    uspUtV1.setFieldValue(UspUtV1Field.SALE_OPT_OUT, 1);
    uspUtV1.setFieldValue(UspUtV1Field.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE, 1);
    uspUtV1.setFieldValue(UspUtV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
    uspUtV1.setFieldValue(UspUtV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 0, 1, 2, 3));
    uspUtV1.setFieldValue(UspUtV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 3);
    uspUtV1.setFieldValue(UspUtV1Field.MSPA_COVERED_TRANSACTION, 1);
    uspUtV1.setFieldValue(UspUtV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    uspUtV1.setFieldValue(UspUtV1Field.MSPA_SERVICE_PROVIDER_MODE, 3);
    
    Assertions.assertEquals("BbWGxvbA", uspUtV1.encode());    
  }
  
  @Test
  public void testDecode1() throws DecodingException {
    UspUtV1 uspUtV1 = new UspUtV1("BbWGxvbA");
    
    Assertions.assertEquals(1, uspUtV1.getSharingNotice()); 
    Assertions.assertEquals(2, uspUtV1.getSaleOptOutNotice()); 
    Assertions.assertEquals(3, uspUtV1.getTargetedAdvertisingOptOutNotice()); 
    Assertions.assertEquals(1, uspUtV1.getSaleOptOut()); 
    Assertions.assertEquals(2, uspUtV1.getTargetedAdvertisingOptOut()); 
    Assertions.assertEquals(Arrays.asList(0, 1, 2, 3, 0, 1, 2, 3), uspUtV1.getSensitiveDataProcessing()); 
    Assertions.assertEquals(3, uspUtV1.getKnownChildSensitiveDataConsents()); 
    Assertions.assertEquals(1, uspUtV1.getMspaCoveredTransaction()); 
    Assertions.assertEquals(2, uspUtV1.getMspaOptOutOptionMode()); 
    Assertions.assertEquals(3, uspUtV1.getMspaServiceProviderMode()); 
  }
}
