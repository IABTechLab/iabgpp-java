package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.field.UsUtV1Field;

public class UsUtV1Test {

  @Test
  public void testEncode1() throws EncodingException {
    UsUtV1 usUtV1 = new UsUtV1();
    Assertions.assertEquals("BAAAAAAA", usUtV1.encode());
  }

  @Test
  public void testEncode2() throws EncodingException, InvalidFieldException {
    UsUtV1 usUtV1 = new UsUtV1();

    usUtV1.setFieldValue(UsUtV1Field.SHARING_NOTICE, 1);
    usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT_NOTICE, 2);
    usUtV1.setFieldValue(UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 3);
    usUtV1.setFieldValue(UsUtV1Field.SALE_OPT_OUT, 1);
    usUtV1.setFieldValue(UsUtV1Field.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE, 1);
    usUtV1.setFieldValue(UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT, 2);
    usUtV1.setFieldValue(UsUtV1Field.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 0, 1, 2, 3));
    usUtV1.setFieldValue(UsUtV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 3);
    usUtV1.setFieldValue(UsUtV1Field.MSPA_COVERED_TRANSACTION, 1);
    usUtV1.setFieldValue(UsUtV1Field.MSPA_OPT_OUT_OPTION_MODE, 2);
    usUtV1.setFieldValue(UsUtV1Field.MSPA_SERVICE_PROVIDER_MODE, 3);

    Assertions.assertEquals("BbWGxvbA", usUtV1.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsUtV1 usUtV1 = new UsUtV1("BbWGxvbA");

    Assertions.assertEquals(1, usUtV1.getSharingNotice());
    Assertions.assertEquals(2, usUtV1.getSaleOptOutNotice());
    Assertions.assertEquals(3, usUtV1.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usUtV1.getSaleOptOut());
    Assertions.assertEquals(2, usUtV1.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(0, 1, 2, 3, 0, 1, 2, 3), usUtV1.getSensitiveDataProcessing());
    Assertions.assertEquals(3, usUtV1.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usUtV1.getMspaCoveredTransaction());
    Assertions.assertEquals(2, usUtV1.getMspaOptOutOptionMode());
    Assertions.assertEquals(3, usUtV1.getMspaServiceProviderMode());
  }
}
