package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.UspCaV1Field;

public class UspCaV1Test {

  @Test
  public void testEncode1() throws EncodingException {

    UspCaV1 uspCaV1 = new UspCaV1();
    Assertions.assertEquals("BAAAAAAA.QA", uspCaV1.encode());
  }

  @Test
  public void testEncode2() throws EncodingException {
    UspCaV1 uspCaV1 = new UspCaV1();
    
    uspCaV1.setFieldValue(UspCaV1Field.SALE_OPT_OUT_NOTICE, 1);
    uspCaV1.setFieldValue(UspCaV1Field.SHARING_OPT_OUT_NOTICE, 2);
    uspCaV1.setFieldValue(UspCaV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE, 3);
    uspCaV1.setFieldValue(UspCaV1Field.SALE_OPT_OUT, 1);
    uspCaV1.setFieldValue(UspCaV1Field.SHARING_OPT_OUT, 2);
    uspCaV1.setFieldValue(UspCaV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 0, 1, 2, 3, 0));
    uspCaV1.setFieldValue(UspCaV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(0, 1));
    uspCaV1.setFieldValue(UspCaV1Field.PERSONAL_DATA_CONSENTS, 1);
    uspCaV1.setFieldValue(UspCaV1Field.MSPA_COVERED_TRANSACTION, 3);
    uspCaV1.setFieldValue(UspCaV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    uspCaV1.setFieldValue(UspCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    uspCaV1.setFieldValue(UspCaV1Field.GPC, true);
    
    Assertions.assertEquals("BbYbGwXY.YA", uspCaV1.encode());
  }
  
  @Test
  public void testEncode3() throws EncodingException {
    UspCaV1 uspCaV1 = new UspCaV1();
    
    uspCaV1.setFieldValue(UspCaV1Field.SALE_OPT_OUT_NOTICE, 1);
    uspCaV1.setFieldValue(UspCaV1Field.SHARING_OPT_OUT_NOTICE, 1);
    uspCaV1.setFieldValue(UspCaV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE, 0);
    uspCaV1.setFieldValue(UspCaV1Field.SALE_OPT_OUT, 2);
    uspCaV1.setFieldValue(UspCaV1Field.SHARING_OPT_OUT, 2);
    uspCaV1.setFieldValue(UspCaV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0));
    uspCaV1.setFieldValue(UspCaV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(0, 0));
    uspCaV1.setFieldValue(UspCaV1Field.PERSONAL_DATA_CONSENTS, 0);
    uspCaV1.setFieldValue(UspCaV1Field.MSPA_COVERED_TRANSACTION, 1);
    uspCaV1.setFieldValue(UspCaV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    uspCaV1.setFieldValue(UspCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    uspCaV1.setFieldValue(UspCaV1Field.GPC, true);
    
    Assertions.assertEquals("BUoAAABQ.YA", uspCaV1.encode());
  }
  
  @Test
  public void testDecode1() throws DecodingException {
    UspCaV1 uspCaV1 = new UspCaV1("BbYbGwXY.YA");
    
    Assertions.assertEquals(1, uspCaV1.getSaleOptOutNotice());
    Assertions.assertEquals(2, uspCaV1.getSharingOptOut());
    Assertions.assertEquals(3, uspCaV1.getSensitiveDataLimitUseNotice());
    Assertions.assertEquals(1, uspCaV1.getSaleOptOut());
    Assertions.assertEquals(2, uspCaV1.getSharingOptOut());
    Assertions.assertEquals(Arrays.asList(0, 1, 2, 3, 0, 1, 2, 3, 0), uspCaV1.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(0, 1), uspCaV1.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, uspCaV1.getPersonalDataConsents());
    Assertions.assertEquals(3, uspCaV1.getMspaCoveredTransaction());
    Assertions.assertEquals(1, uspCaV1.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, uspCaV1.getMspaServiceProviderMode());
    Assertions.assertEquals(true, uspCaV1.getGpc());
  }
}
