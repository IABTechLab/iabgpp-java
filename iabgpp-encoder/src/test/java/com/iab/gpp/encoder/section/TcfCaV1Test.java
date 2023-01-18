package com.iab.gpp.encoder.section;


import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.field.TcfCaV1Field;

public class TcfCaV1Test {

  @Test
  public void testEncode1() throws EncodingException {

    TcfCaV1 tcfCaV1 = new TcfCaV1();
    Assertions.assertEquals("CAAAAAAAAAAAAAAAAAENAACAAAAAAAAAAAAAAAAA.YAAAAAAAAAA", tcfCaV1.encode());
  }

  @Test
  public void testEncode2() throws EncodingException, InvalidFieldException {

    TcfCaV1 tcfCaV1 = new TcfCaV1();
    tcfCaV1.setFieldValue(TcfCaV1Field.CMP_ID, 50);
    tcfCaV1.setFieldValue(TcfCaV1Field.CMP_VERSION, 2);
    tcfCaV1.setFieldValue(TcfCaV1Field.VENDOR_LIST_VERSION, 413);
    tcfCaV1.setFieldValue(TcfCaV1Field.USE_NON_STANDARD_STACKS, true);
    tcfCaV1.setFieldValue(TcfCaV1Field.SPECIAL_FEATURE_EXPRESS_CONSENT,
        Arrays.asList(false, false, false, false, false, false, true, true, true, true, true, true));
    tcfCaV1.setFieldValue(TcfCaV1Field.PURPOSES_EXPRESS_CONSENT,
        Arrays.asList(true, true, true, true, true, true, false, false, false, false, false, false, true, true, true,
            true, true, true, false, false, false, false, false, false));
    tcfCaV1.setFieldValue(TcfCaV1Field.PURPOSES_IMPLIED_CONSENT,
        Arrays.asList(false, false, false, false, false, false, true, true, true, true, true, true, false, false, false,
            false, false, false, true, true, true, true, true, true));
    tcfCaV1.setFieldValue(TcfCaV1Field.VENDOR_EXPRESS_CONSENT, Arrays.asList(12, 24, 48));
    tcfCaV1.setFieldValue(TcfCaV1Field.VENDOR_IMPLIED_CONSENT, Arrays.asList(18, 30));
    tcfCaV1.setFieldValue(TcfCaV1Field.PUB_PURPOSES_EXPRESS_CONSENT,
        Arrays.asList(true, true, true, false, false, false, true, true, true, false, false, false, true, true, true,
            false, false, false, true, true, true, false, false, false));
    tcfCaV1.setFieldValue(TcfCaV1Field.PUB_PURPOSES_IMPLIED_CONSENT,
        Arrays.asList(false, false, false, true, true, true, false, false, false, true, true, true, false, false, false,
            true, true, true, false, false, false, true, true, true));
    tcfCaV1.setFieldValue(TcfCaV1Field.NUM_CUSTOM_PURPOSES, 3);
    tcfCaV1.setFieldValue(TcfCaV1Field.CUSTOM_PURPOSES_EXPRESS_CONSENT, Arrays.asList(false, true, false));
    tcfCaV1.setFieldValue(TcfCaV1Field.CUSTOM_PURPOSES_IMPLIED_CONSENT, Arrays.asList(true, false, true));

    tcfCaV1.setFieldValue(TcfCaV1Field.CREATED, ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")));
    tcfCaV1.setFieldValue(TcfCaV1Field.LAST_UPDATED, ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")));

    Assertions.assertEquals("CPSG_8APSG_8AAyACAENGdCgf_gfgAfgfgBgABABAAABAB4AACAC.fHHHA4444ao", tcfCaV1.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    TcfCaV1 tcfCaV1 = new TcfCaV1("CAAAAAAAAAAAAAAAAAENAACAAAAAAAAAAAAAAAAA.YAAAAAAAAAA");

    Assertions.assertEquals(0, tcfCaV1.getCmpId());
    Assertions.assertEquals(0, tcfCaV1.getCmpVersion());
    Assertions.assertEquals(0, tcfCaV1.getVendorListVersion());
    Assertions.assertEquals(false, tcfCaV1.getUseNonStandardStacks());
    Assertions.assertEquals(
        Arrays.asList(false, false, false, false, false, false, false, false, false, false, false, false),
        tcfCaV1.getSpecialFeatureExpressConsent());
    Assertions
        .assertEquals(
            Arrays.asList(false, false, false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false),
            tcfCaV1.getPurposesExpressConsent());
    Assertions
        .assertEquals(
            Arrays.asList(false, false, false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false),
            tcfCaV1.getPurposesImpliedConsent());
    Assertions.assertEquals(Arrays.asList(), tcfCaV1.getVendorExpressConsent());
    Assertions.assertEquals(Arrays.asList(), tcfCaV1.getVendorImpliedConsent());
    Assertions.assertEquals(
        Arrays.asList(false, false, false, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false),
        tcfCaV1.getPubPurposesExpressConsent());
    Assertions.assertEquals(
        Arrays.asList(false, false, false, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false),
        tcfCaV1.getPubPurposesImpliedConsent());
    Assertions.assertEquals(0, tcfCaV1.getNumCustomPurposes());
    Assertions.assertEquals(Arrays.asList(), tcfCaV1.getCustomPurposesExpressConsent());
    Assertions.assertEquals(Arrays.asList(), tcfCaV1.getCustomPurposesImpliedConsent());
    Assertions.assertEquals(ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")), tcfCaV1.getCreated());
    Assertions.assertEquals(ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")), tcfCaV1.getLastUpdated());

    Assertions.assertEquals("EN", tcfCaV1.getConsentLanguage());
    Assertions.assertEquals(5, tcfCaV1.getId());
    Assertions.assertEquals(3, tcfCaV1.getPubPurposesSegmentType());
  }

  @Test
  public void testDecode2() throws DecodingException {
    TcfCaV1 tcfCaV1 = new TcfCaV1("CPSG_8APSG_8AAyACAENGdCgf_gfgAfgfgBgABABAAABAB4AACAC.fHHHA4444ao");

    Assertions.assertEquals(50, tcfCaV1.getCmpId());
    Assertions.assertEquals(2, tcfCaV1.getCmpVersion());
    Assertions.assertEquals(413, tcfCaV1.getVendorListVersion());
    Assertions.assertEquals(true, tcfCaV1.getUseNonStandardStacks());
    Assertions.assertEquals(Arrays.asList(false, false, false, false, false, false, true, true, true, true, true, true),
        tcfCaV1.getSpecialFeatureExpressConsent());
    Assertions.assertEquals(Arrays.asList(true, true, true, true, true, true, false, false, false, false, false, false,
        true, true, true, true, true, true, false, false, false, false, false, false),
        tcfCaV1.getPurposesExpressConsent());
    Assertions.assertEquals(Arrays.asList(false, false, false, false, false, false, true, true, true, true, true, true,
        false, false, false, false, false, false, true, true, true, true, true, true),
        tcfCaV1.getPurposesImpliedConsent());
    Assertions.assertEquals(Arrays.asList(12, 24, 48), tcfCaV1.getVendorExpressConsent());
    Assertions.assertEquals(Arrays.asList(18, 30), tcfCaV1.getVendorImpliedConsent());
    Assertions
        .assertEquals(
            Arrays.asList(true, true, true, false, false, false, true, true, true, false, false, false, true, true,
                true, false, false, false, true, true, true, false, false, false),
            tcfCaV1.getPubPurposesExpressConsent());
    Assertions
        .assertEquals(
            Arrays.asList(false, false, false, true, true, true, false, false, false, true, true, true, false, false,
                false, true, true, true, false, false, false, true, true, true),
            tcfCaV1.getPubPurposesImpliedConsent());
    Assertions.assertEquals(3, tcfCaV1.getNumCustomPurposes());
    Assertions.assertEquals(Arrays.asList(false, true, false), tcfCaV1.getCustomPurposesExpressConsent());
    Assertions.assertEquals(Arrays.asList(true, false, true), tcfCaV1.getCustomPurposesImpliedConsent());
    Assertions.assertEquals(ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")), tcfCaV1.getCreated());
    Assertions.assertEquals(ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")), tcfCaV1.getLastUpdated());

    Assertions.assertEquals("EN", tcfCaV1.getConsentLanguage());
    Assertions.assertEquals(5, tcfCaV1.getId());
    Assertions.assertEquals(3, tcfCaV1.getPubPurposesSegmentType());
  }
}
