package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.field.UsCtV1Field;

public class UsCtV1Test {

  @Test
  public void testEncode1() throws EncodingException {
    UsCtV1 usCtV1 = new UsCtV1();
    Assertions.assertEquals("BAAAAAAA.QA", usCtV1.encode());
  }

  @Test
  public void testEncode2() throws EncodingException, InvalidFieldException {
    UsCtV1 usCtV1 = new UsCtV1();

    usCtV1.setFieldValue(UsCtV1Field.SHARING_NOTICE, 1);
    usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT_NOTICE, 2);
    usCtV1.setFieldValue(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
    usCtV1.setFieldValue(UsCtV1Field.SALE_OPT_OUT, 1);
    usCtV1.setFieldValue(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
    usCtV1.setFieldValue(UsCtV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 0, 1, 2, 3));
    usCtV1.setFieldValue(UsCtV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, Arrays.asList(1, 2, 3));
    usCtV1.setFieldValue(UsCtV1Field.MSPA_COVERED_TRANSACTION, 1);
    usCtV1.setFieldValue(UsCtV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    usCtV1.setFieldValue(UsCtV1Field.MSPA_SERVICE_PROVIDER_MODE, 3);
    usCtV1.setFieldValue(UsCtV1Field.GPC, true);

    Assertions.assertEquals("BbYbG22w.YA", usCtV1.encode());
  }

  @Test
  public void testEncodeWithGpcSegmentExcluded() throws EncodingException, InvalidFieldException {
    UsCtV1 usCtV1 = new UsCtV1();
    usCtV1.setFieldValue(UsCtV1Field.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BAAAAAAA", usCtV1.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsCtV1 usCtV1 = new UsCtV1("BbYbG22w.YAAA");

    Assertions.assertEquals(1, usCtV1.getSharingNotice());
    Assertions.assertEquals(2, usCtV1.getSaleOptOutNotice());
    Assertions.assertEquals(3, usCtV1.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usCtV1.getSaleOptOut());
    Assertions.assertEquals(2, usCtV1.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(0, 1, 2, 3, 0, 1, 2, 3), usCtV1.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(1, 2, 3), usCtV1.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usCtV1.getMspaCoveredTransaction());
    Assertions.assertEquals(2, usCtV1.getMspaOptOutOptionMode());
    Assertions.assertEquals(3, usCtV1.getMspaServiceProviderMode());
    Assertions.assertEquals(true, usCtV1.getGpc());
  }

  @Test
  public void testDecodeWithGpcSegmentExcluded() throws DecodingException {
    UsCtV1 usCtV1 = new UsCtV1("BbYbG22w");

    Assertions.assertEquals(1, usCtV1.getSharingNotice());
    Assertions.assertEquals(2, usCtV1.getSaleOptOutNotice());
    Assertions.assertEquals(3, usCtV1.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usCtV1.getSaleOptOut());
    Assertions.assertEquals(2, usCtV1.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(0, 1, 2, 3, 0, 1, 2, 3), usCtV1.getSensitiveDataProcessing());
    Assertions.assertEquals(Arrays.asList(1, 2, 3), usCtV1.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usCtV1.getMspaCoveredTransaction());
    Assertions.assertEquals(2, usCtV1.getMspaOptOutOptionMode());
    Assertions.assertEquals(3, usCtV1.getMspaServiceProviderMode());
    Assertions.assertEquals(false, usCtV1.getGpcSegmentIncluded());
  }
}
