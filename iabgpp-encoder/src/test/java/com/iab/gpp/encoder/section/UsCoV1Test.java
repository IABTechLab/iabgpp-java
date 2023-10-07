package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.field.UsCoV1Field;

public class UsCoV1Test {

  @Test
  public void testEncode1() throws EncodingException {
    UsCoV1 usCoV1 = new UsCoV1();
    Assertions.assertEquals("BAAAAAA.QA", usCoV1.encode());
  }

  @Test
  public void testEncode2() throws EncodingException, InvalidFieldException {
    UsCoV1 usCoV1 = new UsCoV1();

    usCoV1.setFieldValue(UsCoV1Field.SHARING_NOTICE, 1);
    usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT_NOTICE, 2);
    usCoV1.setFieldValue(UsCoV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
    usCoV1.setFieldValue(UsCoV1Field.SALE_OPT_OUT, 1);
    usCoV1.setFieldValue(UsCoV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
    usCoV1.setFieldValue(UsCoV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 0, 1, 2));
    usCoV1.setFieldValue(UsCoV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 3);
    usCoV1.setFieldValue(UsCoV1Field.MSPA_COVERED_TRANSACTION, 1);
    usCoV1.setFieldValue(UsCoV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    usCoV1.setFieldValue(UsCoV1Field.MSPA_SERVICE_PROVIDER_MODE, 3);
    usCoV1.setFieldValue(UsCoV1Field.GPC, true);

    Assertions.assertEquals("BbYbG2w.YA", usCoV1.encode());
  }

  @Test
  public void testEncodeWithGpcSegmentExcluded() throws EncodingException, InvalidFieldException {
    UsCoV1 usCoV1 = new UsCoV1();
    usCoV1.setFieldValue(UsCoV1Field.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BAAAAAA", usCoV1.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsCoV1 usCoV1 = new UsCoV1("BbYbG2wA.YA");

    Assertions.assertEquals(1, usCoV1.getSharingNotice());
    Assertions.assertEquals(2, usCoV1.getSaleOptOutNotice());
    Assertions.assertEquals(3, usCoV1.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usCoV1.getSaleOptOut());
    Assertions.assertEquals(2, usCoV1.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(0, 1, 2, 3, 0, 1, 2), usCoV1.getSensitiveDataProcessing());
    Assertions.assertEquals(3, usCoV1.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usCoV1.getMspaCoveredTransaction());
    Assertions.assertEquals(2, usCoV1.getMspaOptOutOptionMode());
    Assertions.assertEquals(3, usCoV1.getMspaServiceProviderMode());
    Assertions.assertEquals(true, usCoV1.getGpc());
  }

  @Test
  public void testDecodeWithGpcSegmentExcluded() throws DecodingException {
    UsCoV1 usCoV1 = new UsCoV1("BbYbG2wA");

    Assertions.assertEquals(1, usCoV1.getSharingNotice());
    Assertions.assertEquals(2, usCoV1.getSaleOptOutNotice());
    Assertions.assertEquals(3, usCoV1.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usCoV1.getSaleOptOut());
    Assertions.assertEquals(2, usCoV1.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(0, 1, 2, 3, 0, 1, 2), usCoV1.getSensitiveDataProcessing());
    Assertions.assertEquals(3, usCoV1.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usCoV1.getMspaCoveredTransaction());
    Assertions.assertEquals(2, usCoV1.getMspaOptOutOptionMode());
    Assertions.assertEquals(3, usCoV1.getMspaServiceProviderMode());
    Assertions.assertEquals(false, usCoV1.getGpcSegmentIncluded());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsCoV1("z");
    });
  }
}
