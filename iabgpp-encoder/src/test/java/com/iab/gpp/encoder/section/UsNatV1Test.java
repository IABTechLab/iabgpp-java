package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.field.UsNatV1Field;

public class UsNatV1Test {

  @Test
  public void testEncode1() throws EncodingException {

    UsNatV1 usNatV1 = new UsNatV1();
    Assertions.assertEquals("BAAAAAAAAAA.QA", usNatV1.encode());
  }

  @Test
  public void testEncode2() throws EncodingException, InvalidFieldException {
    UsNatV1 usNatV1 = new UsNatV1();

    usNatV1.setFieldValue(UsNatV1Field.SHARING_NOTICE, 1);
    usNatV1.setFieldValue(UsNatV1Field.SALE_OPT_OUT_NOTICE, 2);
    usNatV1.setFieldValue(UsNatV1Field.SHARING_OPT_OUT_NOTICE, 3);
    usNatV1.setFieldValue(UsNatV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usNatV1.setFieldValue(UsNatV1Field.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE, 2);
    usNatV1.setFieldValue(UsNatV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE, 3);
    usNatV1.setFieldValue(UsNatV1Field.SALE_OPT_OUT, 1);
    usNatV1.setFieldValue(UsNatV1Field.SHARING_OPT_OUT, 2);
    usNatV1.setFieldValue(UsNatV1Field.TARGETED_ADVERTISING_OPT_OUT, 3);
    usNatV1.setFieldValue(UsNatV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3));
    usNatV1.setFieldValue(UsNatV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(0, 1));
    usNatV1.setFieldValue(UsNatV1Field.PERSONAL_DATA_CONSENTS, 1);
    usNatV1.setFieldValue(UsNatV1Field.MSPA_COVERED_TRANSACTION, 1);
    usNatV1.setFieldValue(UsNatV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    usNatV1.setFieldValue(UsNatV1Field.MSPA_SERVICE_PROVIDER_MODE, 3);
    usNatV1.setFieldValue(UsNatV1Field.GPC, true);

    Assertions.assertEquals("BbbbGxsbFbA.YA", usNatV1.encode());
  }

  @Test
  public void testEncode3() throws EncodingException, InvalidFieldException {
    UsNatV1 usNatV1 = new UsNatV1();

    usNatV1.setFieldValue(UsNatV1Field.SHARING_NOTICE, 1);
    usNatV1.setFieldValue(UsNatV1Field.SALE_OPT_OUT_NOTICE, 1);
    usNatV1.setFieldValue(UsNatV1Field.SHARING_OPT_OUT_NOTICE, 1);
    usNatV1.setFieldValue(UsNatV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usNatV1.setFieldValue(UsNatV1Field.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE, 0);
    usNatV1.setFieldValue(UsNatV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE, 0);
    usNatV1.setFieldValue(UsNatV1Field.SALE_OPT_OUT, 1);
    usNatV1.setFieldValue(UsNatV1Field.SHARING_OPT_OUT, 1);
    usNatV1.setFieldValue(UsNatV1Field.TARGETED_ADVERTISING_OPT_OUT, 1);
    usNatV1.setFieldValue(UsNatV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    usNatV1.setFieldValue(UsNatV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(0, 0));
    usNatV1.setFieldValue(UsNatV1Field.PERSONAL_DATA_CONSENTS, 0);
    usNatV1.setFieldValue(UsNatV1Field.MSPA_COVERED_TRANSACTION, 1);
    usNatV1.setFieldValue(UsNatV1Field.MSPA_OPT_OUT_OPTION_MODE, 1);
    usNatV1.setFieldValue(UsNatV1Field.MSPA_SERVICE_PROVIDER_MODE, 0);
    usNatV1.setFieldValue(UsNatV1Field.GPC, true);

    Assertions.assertEquals("BVQVAAAAAUA.YA", usNatV1.encode());
  }

  @Test
  public void testEncodeWithGpcSegmentIncluded() throws EncodingException, InvalidFieldException {

    UsNatV1 usNatV1 = new UsNatV1();
    usNatV1.setFieldValue(UsNatV1Field.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BAAAAAAAAAA", usNatV1.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsNatV1 usNatV1 = new UsNatV1("BbbbGxsbFbA.YA");

    Assertions.assertEquals(1, usNatV1.getSharingNotice());
    Assertions.assertEquals(2, usNatV1.getSaleOptOutNotice());
    Assertions.assertEquals(3, usNatV1.getSharingOptOutNotice());
    Assertions.assertEquals(1, usNatV1.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(2, usNatV1.getSensitiveDataProcessingOptOutNotice());
    Assertions.assertEquals(3, usNatV1.getSensitiveDataLimitUseNotice());
    Assertions.assertEquals(1, usNatV1.getSaleOptOut());
    Assertions.assertEquals(2, usNatV1.getSharingOptOut());
    Assertions.assertEquals(3, usNatV1.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3), usNatV1.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(0, 1), usNatV1.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usNatV1.getPersonalDataConsents());
    Assertions.assertEquals(1, usNatV1.getMspaCoveredTransaction());
    Assertions.assertEquals(2, usNatV1.getMspaOptOutOptionMode());
    Assertions.assertEquals(3, usNatV1.getMspaServiceProviderMode());
    Assertions.assertEquals(true, usNatV1.getGpc());
  }

  @Test
  public void testDecodeWithGpcSegmentExcluded() throws DecodingException {
    UsNatV1 usNatV1 = new UsNatV1("BbbbGxsbFbA");

    Assertions.assertEquals(1, usNatV1.getSharingNotice());
    Assertions.assertEquals(2, usNatV1.getSaleOptOutNotice());
    Assertions.assertEquals(3, usNatV1.getSharingOptOutNotice());
    Assertions.assertEquals(1, usNatV1.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(2, usNatV1.getSensitiveDataProcessingOptOutNotice());
    Assertions.assertEquals(3, usNatV1.getSensitiveDataLimitUseNotice());
    Assertions.assertEquals(1, usNatV1.getSaleOptOut());
    Assertions.assertEquals(2, usNatV1.getSharingOptOut());
    Assertions.assertEquals(3, usNatV1.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3), usNatV1.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(0, 1), usNatV1.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usNatV1.getPersonalDataConsents());
    Assertions.assertEquals(1, usNatV1.getMspaCoveredTransaction());
    Assertions.assertEquals(2, usNatV1.getMspaOptOutOptionMode());
    Assertions.assertEquals(3, usNatV1.getMspaServiceProviderMode());
    Assertions.assertEquals(false, usNatV1.getGpcSegmentIncluded());
  }
}
