package com.iab.gpp.encoder;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.TcfCaV2Field;
import com.iab.gpp.encoder.field.TcfEuV2Field;
import com.iab.gpp.encoder.field.UspV1Field;
import com.iab.gpp.encoder.section.TcfCaV2;
import com.iab.gpp.encoder.section.TcfEuV2;
import com.iab.gpp.encoder.section.UspV1;

class GppModelTest {

  @Test
  public void testEncodeDefault() throws EncodingException {
    GppModel gppModel = new GppModel();
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

    String gppString = gppModel.encode();

    Assertions.assertEquals(new ArrayList<>(), gppModel.getSectionIds());
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

    Assertions.assertEquals("DBAA", gppString);
  }

  @Test
  public void testEncode2Uspv1() throws EncodingException {
    GppModel gppModel = new GppModel();
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

    gppModel.setFieldValue(UspV1.NAME, UspV1Field.NOTICE, 1);
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE, 2);
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED, 3);

    Assertions.assertEquals(Arrays.asList(7), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

    String gppString = gppModel.encode();
    Assertions.assertEquals("DBABLA~BbA", gppString);

    Assertions.assertEquals(Arrays.asList(7), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

  }

  @Test
  public void testEncodeTcfEuV2() throws EncodingException {
    GppModel gppModel = new GppModel();
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.CMP_ID, 880);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.CMP_VERSION, 0);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.CONSENT_SCREEN, 0);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.CONSENT_LANGUAGE, "EN");
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.VENDOR_LIST_VERSION, 48);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.POLICY_VERSION, 2);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.IS_SERVICE_SPECIFIC, false);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.USE_NON_STANDARD_STACKS, false);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.PURPOSE_ONE_TREATMENT, false);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.PUBLISHER_COUNTRY_CODE, "AA");

    Assertions.assertEquals(Arrays.asList(2), gppModel.getSectionIds());
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

    String gppString = gppModel.encode();
    Assertions.assertEquals(2, gppString.split("~").length);

    Assertions.assertEquals(Arrays.asList(2), gppModel.getSectionIds());
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

  }

  @Test
  public void testEncodeUspV1AndTcfEuV2() throws EncodingException {
    GppModel gppModel = new GppModel();
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));

    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.CMP_ID, 880);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.CMP_VERSION, 0);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.CONSENT_SCREEN, 0);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.CONSENT_LANGUAGE, "EN");
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.VENDOR_LIST_VERSION, 48);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.POLICY_VERSION, 2);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.IS_SERVICE_SPECIFIC, false);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.USE_NON_STANDARD_STACKS, false);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.PURPOSE_ONE_TREATMENT, false);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.PUBLISHER_COUNTRY_CODE, "AA");

    Assertions.assertEquals(Arrays.asList(2), gppModel.getSectionIds());
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

    gppModel.setFieldValue(UspV1.NAME, UspV1Field.NOTICE, 1);
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE, 2);
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED, 3);

    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

    String gppString = gppModel.encode();
    Assertions.assertEquals(3, gppString.split("~").length);

    Assertions.assertEquals(Arrays.asList(2, 7), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

  }

  @Test
  public void testEncodeUspV1AndTcfEuV2AndTcfCaV2() throws EncodingException {
    GppModel gppModel = new GppModel();
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.CMP_ID, 880);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.CMP_VERSION, 0);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.CONSENT_SCREEN, 0);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.CONSENT_LANGUAGE, "EN");
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.VENDOR_LIST_VERSION, 48);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.POLICY_VERSION, 2);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.IS_SERVICE_SPECIFIC, false);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.USE_NON_STANDARD_STACKS, false);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.PURPOSE_ONE_TREATMENT, false);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.PUBLISHER_COUNTRY_CODE, "AA");

    Assertions.assertEquals(Arrays.asList(2), gppModel.getSectionIds());
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

    gppModel.setFieldValue(UspV1.NAME, UspV1Field.NOTICE, 1);
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE, 2);
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED, 3);

    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.CMP_ID, 50);
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.CMP_VERSION, 2);
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.VENDOR_LIST_VERSION, 413);
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.USE_NON_STANDARD_STACKS, true);
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.SPECIAL_FEATURE_EXPRESS_CONSENT,
        Arrays.asList(0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1));
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.PURPOSES_EXPRESS_CONSENT,
        Arrays.asList(1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0));
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.PURPOSES_IMPLIED_CONSENT,
        Arrays.asList(0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1));
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.VENDOR_EXPRESS_CONSENT, Arrays.asList(12, 24, 48));
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.VENDOR_IMPLIED_CONSENT, Arrays.asList(18, 30));
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.PUB_PURPOSES_EXPRESS_CONSENT,
        Arrays.asList(1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0));
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.PUB_PURPOSES_IMPLIED_CONSENT,
        Arrays.asList(0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1));
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.NUM_CUSTOM_PURPOSES, 3);
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.CUSTOM_PURPOSES_EXPRESS_CONSENT, Arrays.asList(0, 1, 0));
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.CUSTOM_PURPOSES_IMPLIED_CONSENT, Arrays.asList(1, 0, 1));

    ZonedDateTime utcDateTime = ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC"));
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.CREATED, utcDateTime);
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.LAST_UPDATED, utcDateTime);

    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfCaV2.NAME));


    String gppString = gppModel.encode();
    Assertions.assertEquals(4, gppString.split("~").length);

    Assertions.assertEquals(Arrays.asList(2, 5, 7), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfCaV2.NAME));
  }

  @Test
  public void testDecodeDefaults() throws DecodingException {
    String gppString = "DBAA";
    GppModel gppModel = new GppModel(gppString);

    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

  }

  @Test
  public void testDecodeUspv1() throws DecodingException {
    String gppString = "DBABLA~BbA";
    GppModel gppModel = new GppModel(gppString);

    Assertions.assertEquals(Arrays.asList(7), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

    Assertions.assertEquals(1, gppModel.getFieldValue(UspV1.NAME, UspV1Field.NOTICE));
    Assertions.assertEquals(2, gppModel.getFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE));
    Assertions.assertEquals(3, gppModel.getFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED));
  }

  @Test
  public void testDecodeTcfEuV2() throws DecodingException {
    String gppString = "DBABMA~CPf_zglPf_zglNwAAAENAwCAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA";
    GppModel gppModel = new GppModel(gppString);

    Assertions.assertEquals(Arrays.asList(2), gppModel.getSectionIds());
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

    Assertions.assertEquals(2, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.VERSION));
    Assertions.assertEquals(880, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.CMP_ID));
    Assertions.assertEquals(0, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.CMP_VERSION));
    Assertions.assertEquals(0, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.CONSENT_SCREEN));
    Assertions.assertEquals("EN", gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.CONSENT_LANGUAGE));
    Assertions.assertEquals(48, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.VENDOR_LIST_VERSION));
    Assertions.assertEquals(2, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.POLICY_VERSION));
    Assertions.assertEquals(false, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.IS_SERVICE_SPECIFIC));
    Assertions.assertEquals(false, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.USE_NON_STANDARD_STACKS));
    Assertions.assertEquals(false, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.PURPOSE_ONE_TREATMENT));
    Assertions.assertEquals("AA", gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.PUBLISHER_COUNTRY_CODE));
  }

  @Test
  public void testDecodeUspv1AndTcfEuV2() throws DecodingException {
    String gppString = "DBACMMA~CPf_zglPf_zglNwAAAENAwCAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA~BbA";
    GppModel gppModel = new GppModel(gppString);

    Assertions.assertEquals(Arrays.asList(2, 7), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

    Assertions.assertEquals(1, gppModel.getFieldValue(UspV1.NAME, UspV1Field.NOTICE));
    Assertions.assertEquals(2, gppModel.getFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE));
    Assertions.assertEquals(3, gppModel.getFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED));

    Assertions.assertEquals(2, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.VERSION));
    Assertions.assertEquals(880, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.CMP_ID));
    Assertions.assertEquals(0, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.CMP_VERSION));
    Assertions.assertEquals(0, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.CONSENT_SCREEN));
    Assertions.assertEquals("EN", gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.CONSENT_LANGUAGE));
    Assertions.assertEquals(48, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.VENDOR_LIST_VERSION));
    Assertions.assertEquals(2, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.POLICY_VERSION));
    Assertions.assertEquals(false, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.IS_SERVICE_SPECIFIC));
    Assertions.assertEquals(false, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.USE_NON_STANDARD_STACKS));
    Assertions.assertEquals(false, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.PURPOSE_ONE_TREATMENT));
    Assertions.assertEquals("AA", gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.PUBLISHER_COUNTRY_CODE));

    TcfEuV2 tcfEuV2Section = (TcfEuV2) gppModel.getSection(TcfEuV2.NAME);
    Integer tcfEuV2Version = tcfEuV2Section.getVersion();
    String consentLanguage = tcfEuV2Section.getConsentLanguage();
    Assertions.assertEquals(2, tcfEuV2Version);
    Assertions.assertEquals("EN", consentLanguage);

    UspV1 uspV1Section = (UspV1) gppModel.getSection(UspV1.NAME);
    Integer uspV1Version = uspV1Section.getVersion();
    Integer notice = uspV1Section.getNotice();
    Assertions.assertEquals(1, uspV1Version);
    Assertions.assertEquals(1, notice);
  }

  @Test
  public void testDecodeUspv1AndTcfEuV2AndTcfCaV2() throws DecodingException {
    String gppString =
        "DBADMZg~CPf_zglPf_zglNwAAAENAwCAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA~CPSG_8APSG_8AAyACAENGdCgf_gfgAfgfjjjjgBgAMAAwADAAIACQAHg.fHHHA4444ao~BbA";
    GppModel gppModel = new GppModel(gppString);

    Assertions.assertEquals(Arrays.asList(2, 5, 7), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfCaV2.NAME));

    Assertions.assertEquals(1, gppModel.getFieldValue(UspV1.NAME, UspV1Field.NOTICE));
    Assertions.assertEquals(2, gppModel.getFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE));
    Assertions.assertEquals(3, gppModel.getFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED));

    Assertions.assertEquals(2, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.VERSION));
    Assertions.assertEquals(880, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.CMP_ID));
    Assertions.assertEquals(0, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.CMP_VERSION));
    Assertions.assertEquals(0, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.CONSENT_SCREEN));
    Assertions.assertEquals("EN", gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.CONSENT_LANGUAGE));
    Assertions.assertEquals(48, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.VENDOR_LIST_VERSION));
    Assertions.assertEquals(2, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.POLICY_VERSION));
    Assertions.assertEquals(false, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.IS_SERVICE_SPECIFIC));
    Assertions.assertEquals(false, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.USE_NON_STANDARD_STACKS));
    Assertions.assertEquals(false, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.PURPOSE_ONE_TREATMENT));
    Assertions.assertEquals("AA", gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.PUBLISHER_COUNTRY_CODE));

    TcfEuV2 tcfEuV2Section = (TcfEuV2) gppModel.getSection(TcfEuV2.NAME);
    Integer tcfEuV2Version = tcfEuV2Section.getVersion();
    String consentLanguage = tcfEuV2Section.getConsentLanguage();
    Assertions.assertEquals(2, tcfEuV2Version);
    Assertions.assertEquals("EN", consentLanguage);

    UspV1 uspV1Section = (UspV1) gppModel.getSection(UspV1.NAME);
    Integer uspV1Version = uspV1Section.getVersion();
    Integer notice = uspV1Section.getNotice();
    Assertions.assertEquals(1, uspV1Version);
    Assertions.assertEquals(1, notice);

    TcfCaV2 tcfCaV2Section = (TcfCaV2) gppModel.getSection(TcfCaV2.NAME);
    Assertions.assertEquals(50, tcfCaV2Section.getCmpId());
    Assertions.assertEquals(2, tcfCaV2Section.getCmpVersion());
    Assertions.assertEquals(413, tcfCaV2Section.getVendorListVersion());
    Assertions.assertEquals(true, tcfCaV2Section.getUseNonStandardStacks());
    Assertions.assertEquals(Arrays.asList(0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1),
        tcfCaV2Section.getSpecialFeatureExpressConsent());
    Assertions.assertEquals(Arrays.asList(1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0),
        tcfCaV2Section.getPurposesExpressConsent());
    Assertions.assertEquals(Arrays.asList(0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1),
        tcfCaV2Section.getPurposesImpliedConsent());
    Assertions.assertEquals(Arrays.asList(12, 24, 48), tcfCaV2Section.getVendorExpressConsent());
    Assertions.assertEquals(Arrays.asList(18, 30), tcfCaV2Section.getVendorImpliedConsent());
    Assertions.assertEquals(Arrays.asList(1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0),
        tcfCaV2Section.getPubPurposesExpressConsent());
    Assertions.assertEquals(Arrays.asList(0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1),
        tcfCaV2Section.getPubPurposesImpliedConsent());
    Assertions.assertEquals(3, tcfCaV2Section.getNumCustomPurposes());
    Assertions.assertEquals(Arrays.asList(0, 1, 0), tcfCaV2Section.getCustomPurposesExpressConsent());
    Assertions.assertEquals(Arrays.asList(1, 0, 1), tcfCaV2Section.getCustomPurposesImpliedConsent());
    Assertions.assertEquals(ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")), tcfCaV2Section.getCreated());
    Assertions.assertEquals(ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")),
        tcfCaV2Section.getLastUpdated());

    Assertions.assertEquals("EN", tcfCaV2Section.getConsentLanguage());
    Assertions.assertEquals(5, tcfCaV2Section.getId());
    Assertions.assertEquals(3, tcfCaV2Section.getSegmentType());
  }
}
