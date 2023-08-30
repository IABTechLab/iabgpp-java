package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.field.UsCaV1Field;

public class UsCaV1Test {

  @Test
  public void testEncode1() throws EncodingException {

    UsCaV1 usCaV1 = new UsCaV1();
    Assertions.assertEquals("BAAAAAAA.QA", usCaV1.encode());
  }

  @Test
  public void testEncode2() throws EncodingException, InvalidFieldException {
    UsCaV1 usCaV1 = new UsCaV1();

    usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT_NOTICE, 1);
    usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT_NOTICE, 2);
    usCaV1.setFieldValue(UsCaV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE, 3);
    usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT, 1);
    usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT, 2);
    usCaV1.setFieldValue(UsCaV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 0, 1, 2, 3, 0));
    usCaV1.setFieldValue(UsCaV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(0, 1));
    usCaV1.setFieldValue(UsCaV1Field.PERSONAL_DATA_CONSENTS, 1);
    usCaV1.setFieldValue(UsCaV1Field.MSPA_COVERED_TRANSACTION, 3);
    usCaV1.setFieldValue(UsCaV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 2);
    usCaV1.setFieldValue(UsCaV1Field.GPC, true);

    Assertions.assertEquals("BbYbGwXY.YA", usCaV1.encode());
  }

  @Test
  public void testEncode3() throws EncodingException, InvalidFieldException {
    UsCaV1 usCaV1 = new UsCaV1();

    usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT_NOTICE, 1);
    usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT_NOTICE, 1);
    usCaV1.setFieldValue(UsCaV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE, 0);
    usCaV1.setFieldValue(UsCaV1Field.SALE_OPT_OUT, 2);
    usCaV1.setFieldValue(UsCaV1Field.SHARING_OPT_OUT, 2);
    usCaV1.setFieldValue(UsCaV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0));
    usCaV1.setFieldValue(UsCaV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(0, 0));
    usCaV1.setFieldValue(UsCaV1Field.PERSONAL_DATA_CONSENTS, 0);
    usCaV1.setFieldValue(UsCaV1Field.MSPA_COVERED_TRANSACTION, 1);
    usCaV1.setFieldValue(UsCaV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usCaV1.setFieldValue(UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usCaV1.setFieldValue(UsCaV1Field.GPC, true);

    Assertions.assertEquals("BUoAAABQ.YA", usCaV1.encode());
  }

  @Test
  public void testEncodeWithGpcSegmentExcluded() throws EncodingException, InvalidFieldException {

    UsCaV1 usCaV1 = new UsCaV1();
    usCaV1.setFieldValue(UsCaV1Field.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BAAAAAAA", usCaV1.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsCaV1 usCaV1 = new UsCaV1("BbYbGwXY.YA");

    Assertions.assertEquals(1, usCaV1.getSaleOptOutNotice());
    Assertions.assertEquals(2, usCaV1.getSharingOptOut());
    Assertions.assertEquals(3, usCaV1.getSensitiveDataLimitUseNotice());
    Assertions.assertEquals(1, usCaV1.getSaleOptOut());
    Assertions.assertEquals(2, usCaV1.getSharingOptOut());
    Assertions.assertEquals(Arrays.asList(0, 1, 2, 3, 0, 1, 2, 3, 0), usCaV1.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(0, 1), usCaV1.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usCaV1.getPersonalDataConsents());
    Assertions.assertEquals(3, usCaV1.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usCaV1.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usCaV1.getMspaServiceProviderMode());
    Assertions.assertEquals(true, usCaV1.getGpc());
  }

  @Test
  public void testDecodeWithGpcSegmentExcluded() throws DecodingException {
    UsCaV1 usCaV1 = new UsCaV1("BbYbGwXY");

    Assertions.assertEquals(1, usCaV1.getSaleOptOutNotice());
    Assertions.assertEquals(2, usCaV1.getSharingOptOut());
    Assertions.assertEquals(3, usCaV1.getSensitiveDataLimitUseNotice());
    Assertions.assertEquals(1, usCaV1.getSaleOptOut());
    Assertions.assertEquals(2, usCaV1.getSharingOptOut());
    Assertions.assertEquals(Arrays.asList(0, 1, 2, 3, 0, 1, 2, 3, 0), usCaV1.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(0, 1), usCaV1.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usCaV1.getPersonalDataConsents());
    Assertions.assertEquals(3, usCaV1.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usCaV1.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usCaV1.getMspaServiceProviderMode());
    Assertions.assertEquals(false, usCaV1.getGpcSegmentIncluded());
  }
}
