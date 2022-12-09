package com.iab.gpp.encoder.section;


import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.TcfCaV2Field;

public class TcfCaV2Test {

  @Test
  public void testEncode1() throws EncodingException {

    TcfCaV2 tcfCaV2 = new TcfCaV2();
    Assertions.assertEquals("CAAAAAAAAAAAAAAAAAENAACAAAAAAAAAAAAAAAAA.YAAAAAAAAAAA", tcfCaV2.encode());
  }

  @Test
  public void testEncode2() throws EncodingException {

    TcfCaV2 tcfCaV2 = new TcfCaV2();
    tcfCaV2.setFieldValue(TcfCaV2Field.CMP_ID, 50);
    tcfCaV2.setFieldValue(TcfCaV2Field.CMP_VERSION, 2);
    tcfCaV2.setFieldValue(TcfCaV2Field.VENDOR_LIST_VERSION, 413);
    tcfCaV2.setFieldValue(TcfCaV2Field.USE_NON_STANDARD_STACKS, true);
    tcfCaV2.setFieldValue(TcfCaV2Field.SPECIAL_FEATURE_EXPRESS_CONSENT,
        Arrays.asList(false, false, false, false, false, false, true, true, true, true, true, true));
    tcfCaV2.setFieldValue(TcfCaV2Field.PURPOSES_EXPRESS_CONSENT,
        Arrays.asList(true, true, true, true, true, true, false, false, false, false, false, false, true, true, true,
            true, true, true, false, false, false, false, false, false));
    tcfCaV2.setFieldValue(TcfCaV2Field.PURPOSES_IMPLIED_CONSENT,
        Arrays.asList(false, false, false, false, false, false, true, true, true, true, true, true, false, false, false,
            false, false, false, true, true, true, true, true, true));
    tcfCaV2.setFieldValue(TcfCaV2Field.VENDOR_EXPRESS_CONSENT, Arrays.asList(12, 24, 48));
    tcfCaV2.setFieldValue(TcfCaV2Field.VENDOR_IMPLIED_CONSENT, Arrays.asList(18, 30));
    tcfCaV2.setFieldValue(TcfCaV2Field.PUB_PURPOSES_EXPRESS_CONSENT,
        Arrays.asList(true, true, true, false, false, false, true, true, true, false, false, false, true, true, true,
            false, false, false, true, true, true, false, false, false));
    tcfCaV2.setFieldValue(TcfCaV2Field.PUB_PURPOSES_IMPLIED_CONSENT,
        Arrays.asList(false, false, false, true, true, true, false, false, false, true, true, true, false, false, false,
            true, true, true, false, false, false, true, true, true));
    tcfCaV2.setFieldValue(TcfCaV2Field.NUM_CUSTOM_PURPOSES, 3);
    tcfCaV2.setFieldValue(TcfCaV2Field.CUSTOM_PURPOSES_EXPRESS_CONSENT, Arrays.asList(false, true, false));
    tcfCaV2.setFieldValue(TcfCaV2Field.CUSTOM_PURPOSES_IMPLIED_CONSENT, Arrays.asList(true, false, true));

    tcfCaV2.setFieldValue(TcfCaV2Field.CREATED, ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")));
    tcfCaV2.setFieldValue(TcfCaV2Field.LAST_UPDATED, ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")));

    Assertions.assertEquals("CPSG_8APSG_8AAyACAENGdCgf_gfgAfgfgBgAMAAwADAAIACQAHg.fHHHA4444aoA", tcfCaV2.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    TcfCaV2 tcfCaV2 = new TcfCaV2("CAAAAAAAAAAAAAAAAAENAACAAAAAAAAAAAAAAAAA.YAAAAAAAAAAA");

    Assertions.assertEquals(0, tcfCaV2.getCmpId());
    Assertions.assertEquals(0, tcfCaV2.getCmpVersion());
    Assertions.assertEquals(0, tcfCaV2.getVendorListVersion());
    Assertions.assertEquals(false, tcfCaV2.getUseNonStandardStacks());
    Assertions.assertEquals(
        Arrays.asList(false, false, false, false, false, false, false, false, false, false, false, false),
        tcfCaV2.getSpecialFeatureExpressConsent());
    Assertions
        .assertEquals(
            Arrays.asList(false, false, false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false),
            tcfCaV2.getPurposesExpressConsent());
    Assertions
        .assertEquals(
            Arrays.asList(false, false, false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false),
            tcfCaV2.getPurposesImpliedConsent());
    Assertions.assertEquals(Arrays.asList(), tcfCaV2.getVendorExpressConsent());
    Assertions.assertEquals(Arrays.asList(), tcfCaV2.getVendorImpliedConsent());
    Assertions.assertEquals(
        Arrays.asList(false, false, false, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false),
        tcfCaV2.getPubPurposesExpressConsent());
    Assertions.assertEquals(
        Arrays.asList(false, false, false, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false),
        tcfCaV2.getPubPurposesImpliedConsent());
    Assertions.assertEquals(0, tcfCaV2.getNumCustomPurposes());
    Assertions.assertEquals(Arrays.asList(), tcfCaV2.getCustomPurposesExpressConsent());
    Assertions.assertEquals(Arrays.asList(), tcfCaV2.getCustomPurposesImpliedConsent());
    Assertions.assertEquals(ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")), tcfCaV2.getCreated());
    Assertions.assertEquals(ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")), tcfCaV2.getLastUpdated());

    Assertions.assertEquals("EN", tcfCaV2.getConsentLanguage());
    Assertions.assertEquals(5, tcfCaV2.getId());
    Assertions.assertEquals(3, tcfCaV2.getPubPurposesSegmentType());
  }

  @Test
  public void testDecode2() throws DecodingException {
    TcfCaV2 tcfCaV2 = new TcfCaV2("CPSG_8APSG_8AAyACAENGdCgf_gfgAfgfgBgAMAAwADAAIACQAHg.fHHHA4444aoA");

    Assertions.assertEquals(50, tcfCaV2.getCmpId());
    Assertions.assertEquals(2, tcfCaV2.getCmpVersion());
    Assertions.assertEquals(413, tcfCaV2.getVendorListVersion());
    Assertions.assertEquals(true, tcfCaV2.getUseNonStandardStacks());
    Assertions.assertEquals(Arrays.asList(false, false, false, false, false, false, true, true, true, true, true, true),
        tcfCaV2.getSpecialFeatureExpressConsent());
    Assertions.assertEquals(Arrays.asList(true, true, true, true, true, true, false, false, false, false, false, false,
        true, true, true, true, true, true, false, false, false, false, false, false),
        tcfCaV2.getPurposesExpressConsent());
    Assertions.assertEquals(Arrays.asList(false, false, false, false, false, false, true, true, true, true, true, true,
        false, false, false, false, false, false, true, true, true, true, true, true),
        tcfCaV2.getPurposesImpliedConsent());
    Assertions.assertEquals(Arrays.asList(12, 24, 48), tcfCaV2.getVendorExpressConsent());
    Assertions.assertEquals(Arrays.asList(18, 30), tcfCaV2.getVendorImpliedConsent());
    Assertions
        .assertEquals(
            Arrays.asList(true, true, true, false, false, false, true, true, true, false, false, false, true, true,
                true, false, false, false, true, true, true, false, false, false),
            tcfCaV2.getPubPurposesExpressConsent());
    Assertions
        .assertEquals(
            Arrays.asList(false, false, false, true, true, true, false, false, false, true, true, true, false, false,
                false, true, true, true, false, false, false, true, true, true),
            tcfCaV2.getPubPurposesImpliedConsent());
    Assertions.assertEquals(3, tcfCaV2.getNumCustomPurposes());
    Assertions.assertEquals(Arrays.asList(false, true, false), tcfCaV2.getCustomPurposesExpressConsent());
    Assertions.assertEquals(Arrays.asList(true, false, true), tcfCaV2.getCustomPurposesImpliedConsent());
    Assertions.assertEquals(ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")), tcfCaV2.getCreated());
    Assertions.assertEquals(ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")), tcfCaV2.getLastUpdated());

    Assertions.assertEquals("EN", tcfCaV2.getConsentLanguage());
    Assertions.assertEquals(5, tcfCaV2.getId());
    Assertions.assertEquals(3, tcfCaV2.getPubPurposesSegmentType());
  }
}
