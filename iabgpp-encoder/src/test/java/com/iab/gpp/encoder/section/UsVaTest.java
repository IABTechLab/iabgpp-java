package com.iab.gpp.encoder.section;


import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.UsVaField;

public class UsVaTest {

  @Test
  public void testEncode1() {
    UsVa usVa = new UsVa();
    Assertions.assertEquals("BAAAABA", usVa.encode());
  }

  @Test
  public void testEncode2() {
    UsVa usVa = new UsVa();

    try {
      usVa.setFieldValue(UsVaField.SHARING_NOTICE, 1);
      usVa.setFieldValue(UsVaField.SALE_OPT_OUT_NOTICE, 1);
      usVa.setFieldValue(UsVaField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 1);
      usVa.setFieldValue(UsVaField.SALE_OPT_OUT, 1);
      usVa.setFieldValue(UsVaField.TARGETED_ADVERTISING_OPT_OUT, 1);
      usVa.setFieldValue(UsVaField.SENSITIVE_DATA_PROCESSING, Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1));
      usVa.setFieldValue(UsVaField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 1);
      usVa.setFieldValue(UsVaField.MSPA_COVERED_TRANSACTION, 1);
      usVa.setFieldValue(UsVaField.MSPA_OPT_OUT_OPTION_MODE, 1);
      usVa.setFieldValue(UsVaField.MSPA_SERVICE_PROVIDER_MODE, 2);
    } catch (InvalidFieldException e) {
      throw new EncodingException(e);
    }

    Assertions.assertEquals("BVWSSVY", usVa.encode());
  }

  @Test
  public void testSetInvalidValues() {
    UsVa usVa = new UsVa();

    try {
      usVa.setFieldValue(UsVaField.SHARING_NOTICE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVa.setFieldValue(UsVaField.SALE_OPT_OUT_NOTICE, -1);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVa.setFieldValue(UsVaField.TARGETED_ADVERTISING_OPT_OUT_NOTICE, 4);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVa.setFieldValue(UsVaField.SALE_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVa.setFieldValue(UsVaField.TARGETED_ADVERTISING_OPT_OUT, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVa.setFieldValue(UsVaField.SENSITIVE_DATA_PROCESSING, Arrays.asList(0, 1, 2, 3, 1, 2, 0, 1));
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVa.setFieldValue(UsVaField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVa.setFieldValue(UsVaField.MSPA_COVERED_TRANSACTION, 0);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVa.setFieldValue(UsVaField.MSPA_OPT_OUT_OPTION_MODE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

    try {
      usVa.setFieldValue(UsVaField.MSPA_SERVICE_PROVIDER_MODE, 3);
      Assertions.fail("Expected ValidationException");
    } catch (ValidationException e) {

    }

  }

  @Test
  public void testDecode1() throws DecodingException {
    UsVa usVa = new UsVa("BVWSSVY");

    Assertions.assertEquals(1, usVa.getSharingNotice());
    Assertions.assertEquals(1, usVa.getSaleOptOutNotice());
    Assertions.assertEquals(1, usVa.getTargetedAdvertisingOptOutNotice());
    Assertions.assertEquals(1, usVa.getSaleOptOut());
    Assertions.assertEquals(1, usVa.getTargetedAdvertisingOptOut());
    Assertions.assertEquals(Arrays.asList(2, 1, 0, 2, 1, 0, 2, 1), usVa.getSensitiveDataProcessing());
    Assertions.assertEquals(1, usVa.getKnownChildSensitiveDataConsents());
    Assertions.assertEquals(1, usVa.getMspaCoveredTransaction());
    Assertions.assertEquals(1, usVa.getMspaOptOutOptionMode());
    Assertions.assertEquals(2, usVa.getMspaServiceProviderMode());
  }

  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new UsVa("z").getMspaCoveredTransaction();
    });
  }
}
