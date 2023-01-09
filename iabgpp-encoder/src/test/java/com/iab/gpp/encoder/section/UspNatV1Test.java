package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.UspNatV1Field;

public class UspNatV1Test {

  @Test
  public void testEncode1() throws EncodingException {

    UspNatV1 uspNatV1 = new UspNatV1();
    Assertions.assertEquals("BAAAAAAAAAA.QA", uspNatV1.encode());
  }

  @Test
  public void testEncode2() throws EncodingException {
    UspNatV1 uspNatV1 = new UspNatV1();
    
    uspNatV1.setFieldValue(UspNatV1Field.SHARING_NOTICE, 1);
    uspNatV1.setFieldValue(UspNatV1Field.SALE_OPT_OUT_NOTICE, 2);
    uspNatV1.setFieldValue(UspNatV1Field.SHARING_OPT_OUT_NOTICE, 3);
    uspNatV1.setFieldValue(UspNatV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    uspNatV1.setFieldValue(UspNatV1Field.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE, 2);
    uspNatV1.setFieldValue(UspNatV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE, 3);
    uspNatV1.setFieldValue(UspNatV1Field.SALE_OPT_OUT, 1);
    uspNatV1.setFieldValue(UspNatV1Field.SHARING_OPT_OUT, 2);
    uspNatV1.setFieldValue(UspNatV1Field.TARGETED_ADVERTISING_OPT_OUT, 3);
    uspNatV1.setFieldValue(UspNatV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3));
    uspNatV1.setFieldValue(UspNatV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(0, 1));
    uspNatV1.setFieldValue(UspNatV1Field.PERSONAL_DATA_CONSENTS, 1);
    uspNatV1.setFieldValue(UspNatV1Field.MSPA_COVERED_TRANSACTION, 1);
    uspNatV1.setFieldValue(UspNatV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    uspNatV1.setFieldValue(UspNatV1Field.MSPA_SERVICE_PROVIDER_MODE, 3);
    uspNatV1.setFieldValue(UspNatV1Field.GPC, true);
    
    Assertions.assertEquals("BbbbGxsbFbA.YA", uspNatV1.encode());
  }
  
  @Test
  public void testEncode3() throws EncodingException {
    UspNatV1 uspNatV1 = new UspNatV1();
    
    uspNatV1.setFieldValue(UspNatV1Field.SHARING_NOTICE, 1);
    uspNatV1.setFieldValue(UspNatV1Field.SALE_OPT_OUT_NOTICE, 1);
    uspNatV1.setFieldValue(UspNatV1Field.SHARING_OPT_OUT_NOTICE, 1);
    uspNatV1.setFieldValue(UspNatV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    uspNatV1.setFieldValue(UspNatV1Field.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE, 0);
    uspNatV1.setFieldValue(UspNatV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE, 0);
    uspNatV1.setFieldValue(UspNatV1Field.SALE_OPT_OUT, 1);
    uspNatV1.setFieldValue(UspNatV1Field.SHARING_OPT_OUT, 1);
    uspNatV1.setFieldValue(UspNatV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
    uspNatV1.setFieldValue(UspNatV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    uspNatV1.setFieldValue(UspNatV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(0, 0));
    uspNatV1.setFieldValue(UspNatV1Field.PERSONAL_DATA_CONSENTS, 0);
    uspNatV1.setFieldValue(UspNatV1Field.MSPA_COVERED_TRANSACTION, 1);
    uspNatV1.setFieldValue(UspNatV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    uspNatV1.setFieldValue(UspNatV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    uspNatV1.setFieldValue(UspNatV1Field.GPC, true);
    
    Assertions.assertEquals("BVQVAAAAAUA.YA", uspNatV1.encode());
  }
  
  @Test
  public void testDecode1() throws DecodingException {
    UspNatV1 uspNatV1 = new UspNatV1("BbbbGxsbFbA.YA");
    
    Assertions.assertEquals(1, uspNatV1.getSharingNotice());
    Assertions.assertEquals(2, uspNatV1.getSaleOptOutNotice());
    Assertions.assertEquals(3, uspNatV1.getSharingOptOutNotice());
    Assertions.assertEquals(1, uspNatV1.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(2, uspNatV1.getSensitiveDataProcessingOptOutNotice());
    Assertions.assertEquals(3, uspNatV1.getSensitiveDataLimitUseNotice());
    Assertions.assertEquals(1, uspNatV1.getSaleOptOut());
    Assertions.assertEquals(2, uspNatV1.getSharingOptOut());
    Assertions.assertEquals(3, uspNatV1.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3), uspNatV1.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(0, 1), uspNatV1.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, uspNatV1.getPersonalDataConsents());
    Assertions.assertEquals(1, uspNatV1.getMspaCoveredTransaction());
    Assertions.assertEquals(2, uspNatV1.getMspaOptOutOptionMode());
    Assertions.assertEquals(3, uspNatV1.getMspaServiceProviderMode());
    Assertions.assertEquals(true, uspNatV1.getGpc());
  }
}
