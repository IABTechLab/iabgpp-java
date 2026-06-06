package com.iab.gpp.encoder.section;


import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsRiField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class UsRiTest {

  @Test
  public void testEncode1() {
    UsRi usRi = new UsRi();
    Assertions.assertEquals("BQAAAAA.QA", usRi.encode());
  }

  @Test
  public void testEncode2() {
    UsRi usRi = new UsRi();

    usRi.setFieldValue(UsRiField.MSPA_COVERED_TRANSACTION, 1);
    usRi.setFieldValue(UsRiField.MSPA_MODE, 1);
    usRi.setFieldValue(UsRiField.PROCESSING_NOTICE, 1);
    usRi.setFieldValue(UsRiField.SALE_OPT_OUT_NOTICE, 1);
    usRi.setFieldValue(UsRiField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
    usRi.setFieldValue(UsRiField.SALE_OPT_OUT, 1);
    usRi.setFieldValue(UsRiField.TARGETED_ADVERTISING_OPT_OUT, 1);
    usRi.setFieldValue(UsRiField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 1);
    usRi.setFieldValue(UsRiField.ADDITIONAL_DATA_PROCESSING_CONSENT, 1);
    usRi.setFieldValue(UsRiField.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1));
    usRi.setFieldValue(UsRiField.GPC, true);

    Assertions.assertEquals("BVVVkkk.YA", usRi.encode());
  }

  @Test
  public void testSetInvalidValues() {
    UsRi usRi = new UsRi();

    try {
      usRi.setFieldValue(UsRiField.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usRi.setFieldValue(UsRiField.MSPA_MODE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usRi.setFieldValue(UsRiField.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 1, 2, 0, 1));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }
  }

  @Test
  public void testEncodeWithGpcSegmentExcluded() {
    UsRi usRi = new UsRi();
    usRi.setFieldValue(UsRiField.GPC_SEGMENT_INCLUDED, false);
    Assertions.assertEquals("BQAAAAA", usRi.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    UsRi usRi = new UsRi("BVVVkkk.YA");

    Assertions.assertEquals(1, usRi.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usRi.getMspaMode());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usRi.getSensitiveDataProcessing());
    Assertions.assertEquals(true, usRi.getGpc());
  }

  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsRi("z").getProcessingNotice();
    });
  }
}
