package com.iab.gpp.encoder.section;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.TcfEuV2Field;

public class TcfEuV2Test {

  @Test
  public void testEncode1() throws EncodingException {
    TcfEuV2 tcfEuV2 = new TcfEuV2();
    Assertions.assertEquals("CAAAAAAAAAAAAAAAAAENAACAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA", tcfEuV2.encode());
  }

  @Test
  public void testEncode2() throws EncodingException {
    TcfEuV2 tcfEuV2 = new TcfEuV2();
    tcfEuV2.setFieldValue(TcfEuV2Field.IS_SERVICE_SPECIFIC, true);
    tcfEuV2.setFieldValue(TcfEuV2Field.CREATED, ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")));
    tcfEuV2.setFieldValue(TcfEuV2Field.LAST_UPDATED, ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")));
    Assertions.assertEquals("CPSG_8APSG_8AAAAAAENAACgAAAAAAAAAAAAAAAAAAA.YAAAAAAAAAA", tcfEuV2.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    TcfEuV2 tcfEuV2 = new TcfEuV2("CAAAAAAAAAAAAAAAAAENAACAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA");

    Assertions.assertEquals(2, tcfEuV2.getVersion());
    Assertions.assertEquals(ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")), tcfEuV2.getCreated());
    Assertions.assertEquals(ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")), tcfEuV2.getLastUpdated());
    Assertions.assertEquals(0, tcfEuV2.getCmpId());
    Assertions.assertEquals(0, tcfEuV2.getCmpVersion());
    Assertions.assertEquals(0, tcfEuV2.getConsentScreen());
    Assertions.assertEquals("EN", tcfEuV2.getConsentLanguage());
    Assertions.assertEquals(0, tcfEuV2.getVendorListVersion());
    Assertions.assertEquals(2, tcfEuV2.getPolicyVersion());
    Assertions.assertEquals(false, tcfEuV2.getIsServiceSpecific());
    Assertions.assertEquals(false, tcfEuV2.getUseNonStandardStacks());
    Assertions.assertEquals(
        Arrays.asList(false, false, false, false, false, false, false, false, false, false, false, false),
        tcfEuV2.getSpecialFeatureOptins());
    Assertions
        .assertEquals(
            Arrays.asList(false, false, false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false),
            tcfEuV2.getPurposeConsents());
    Assertions.assertEquals(
        Arrays.asList(false, false, false, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false),
        tcfEuV2.getPurposeLegitimateInterests());
    Assertions.assertEquals(false, tcfEuV2.getPurposeOneTreatment());
    Assertions.assertEquals("AA", tcfEuV2.getPublisherCountryCode());
    Assertions.assertEquals(Arrays.asList(), tcfEuV2.getVendorConsents());
    Assertions.assertEquals(Arrays.asList(), tcfEuV2.getVendorLegitimateInterests());
    Assertions.assertEquals(Arrays.asList(), tcfEuV2.getPublisherRestrictions());
    Assertions.assertEquals(3, tcfEuV2.getPublisherPurposesSegmentType());
    Assertions.assertEquals(Arrays.asList(), tcfEuV2.getPublisherConsents());
    Assertions.assertEquals(Arrays.asList(), tcfEuV2.getPublisherLegitimateInterests());
    Assertions.assertEquals(0, tcfEuV2.getNumCustomPurposes());
    Assertions.assertEquals(Arrays.asList(), tcfEuV2.getPublisherCustomConsents());
    Assertions.assertEquals(Arrays.asList(), tcfEuV2.getPublisherCustomLegitimateInterests());
    Assertions.assertEquals(2, tcfEuV2.getVendorsAllowedSegmentType());
    Assertions.assertEquals(Arrays.asList(), tcfEuV2.getVendorsAllowed());
    Assertions.assertEquals(1, tcfEuV2.getVendorsDisclosedSegmentType());
    Assertions.assertEquals(Arrays.asList(), tcfEuV2.getVendorsDisclosed());

    Assertions.assertEquals(2, tcfEuV2.getId());
  }

  @Test
  public void testDecode2() throws DecodingException {
    TcfEuV2 tcfEuV2 = new TcfEuV2("CPSG_8APSG_8AAAAAAENAACgAAAAAAAAAAAAAAAAAAA.YAAAAAAAAAA");

    Assertions.assertEquals(2, tcfEuV2.getVersion());
    Assertions.assertEquals(ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")), tcfEuV2.getCreated());
    Assertions.assertEquals(ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")), tcfEuV2.getLastUpdated());
    Assertions.assertEquals(0, tcfEuV2.getCmpId());
    Assertions.assertEquals(0, tcfEuV2.getCmpVersion());
    Assertions.assertEquals(0, tcfEuV2.getConsentScreen());
    Assertions.assertEquals("EN", tcfEuV2.getConsentLanguage());
    Assertions.assertEquals(0, tcfEuV2.getVendorListVersion());
    Assertions.assertEquals(2, tcfEuV2.getPolicyVersion());
    Assertions.assertEquals(true, tcfEuV2.getIsServiceSpecific());
    Assertions.assertEquals(false, tcfEuV2.getUseNonStandardStacks());
    Assertions.assertEquals(
        Arrays.asList(false, false, false, false, false, false, false, false, false, false, false, false),
        tcfEuV2.getSpecialFeatureOptins());
    Assertions
        .assertEquals(
            Arrays.asList(false, false, false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false),
            tcfEuV2.getPurposeConsents());
    Assertions.assertEquals(
        Arrays.asList(false, false, false, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false),
        tcfEuV2.getPurposeLegitimateInterests());
    Assertions.assertEquals(false, tcfEuV2.getPurposeOneTreatment());
    Assertions.assertEquals("AA", tcfEuV2.getPublisherCountryCode());
    Assertions.assertEquals(Arrays.asList(), tcfEuV2.getVendorConsents());
    Assertions.assertEquals(Arrays.asList(), tcfEuV2.getVendorLegitimateInterests());
    Assertions.assertEquals(Arrays.asList(), tcfEuV2.getPublisherRestrictions());
    Assertions.assertEquals(3, tcfEuV2.getPublisherPurposesSegmentType());
    Assertions
        .assertEquals(
            Arrays.asList(false, false, false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false),
            tcfEuV2.getPublisherConsents());
    Assertions.assertEquals(
        Arrays.asList(false, false, false, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false),
        tcfEuV2.getPublisherLegitimateInterests());
    Assertions.assertEquals(0, tcfEuV2.getNumCustomPurposes());
    Assertions.assertEquals(Arrays.asList(), tcfEuV2.getPublisherCustomConsents());
    Assertions.assertEquals(Arrays.asList(), tcfEuV2.getPublisherCustomLegitimateInterests());
    Assertions.assertEquals(2, tcfEuV2.getVendorsAllowedSegmentType());
    Assertions.assertEquals(Arrays.asList(), tcfEuV2.getVendorsAllowed());
    Assertions.assertEquals(1, tcfEuV2.getVendorsDisclosedSegmentType());
    Assertions.assertEquals(Arrays.asList(), tcfEuV2.getVendorsDisclosed());

    Assertions.assertEquals(2, tcfEuV2.getId());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testDecode3() throws DecodingException {
    TcfEuV2 tcfEuV2 = new TcfEuV2(
        "CPcqBNJPcqBNJNwAAAENAwCAAAAAAAAAAAAAAAAAAAAA.YAAAAAAAAAA.QGLtV_T9fb2vj-_Z99_tkeYwf95y3p-wzhheMs-8NyZeH_B4Wv2MyvBX4JiQKGRgksjLBAQdtHGlcTQgBwIlViTLMYk2MjzNKJrJEilsbO2dYGD9Pn8HT3ZCY70-vv__7v3ff_3g.IGLtV_T9fb2vj-_Z99_tkeYwf95y3p-wzhheMs-8NyZeH_B4Wv2MyvBX4JiQKGRgksjLBAQdtHGlcTQgBwIlViTLMYk2MjzNKJrJEilsbO2dYGD9Pn8HT3ZCY70-vv__7v3ff_3g");


    Assertions.assertEquals(2, tcfEuV2.getFieldValue("Version"));
    Assertions.assertEquals(880, tcfEuV2.getFieldValue("CmpId"));
    Assertions.assertEquals(0, tcfEuV2.getFieldValue("CmpVersion"));
    Assertions.assertEquals(0, tcfEuV2.getFieldValue("ConsentScreen"));
    Assertions.assertEquals("EN", tcfEuV2.getFieldValue("ConsentLanguage"));
    Assertions.assertEquals(48, tcfEuV2.getFieldValue("VendorListVersion"));
    Assertions.assertEquals(2, tcfEuV2.getFieldValue("PolicyVersion"));
    Assertions.assertEquals(false, tcfEuV2.getFieldValue("IsServiceSpecific"));
    Assertions.assertEquals(false, tcfEuV2.getFieldValue("UseNonStandardStacks"));
    Assertions.assertEquals(false, tcfEuV2.getFieldValue("PurposeOneTreatment"));
    Assertions.assertEquals("AA", tcfEuV2.getFieldValue("PublisherCountryCode"));

    Assertions.assertEquals(3, tcfEuV2.getFieldValue("PublisherPurposesSegmentType"));

    Assertions.assertEquals(2, tcfEuV2.getFieldValue("VendorsAllowedSegmentType"));
    List<Integer> vendorsAllowed = (List<Integer>) tcfEuV2.getFieldValue("VendorsAllowed");
    Assertions.assertEquals(434, vendorsAllowed.size());
    Assertions.assertEquals(1, vendorsAllowed.get(0));
    Assertions.assertEquals(2, vendorsAllowed.get(1));
    Assertions.assertEquals(4, vendorsAllowed.get(2));
    Assertions.assertEquals(6, vendorsAllowed.get(3));
    Assertions.assertEquals(8, vendorsAllowed.get(4));
    Assertions.assertEquals(10, vendorsAllowed.get(5));
    Assertions.assertEquals(11, vendorsAllowed.get(6));
    Assertions.assertEquals(12, vendorsAllowed.get(7));
    Assertions.assertEquals(13, vendorsAllowed.get(8));
    Assertions.assertEquals(14, vendorsAllowed.get(9));
    Assertions.assertEquals(782, vendorsAllowed.get(vendorsAllowed.size() - 9));
    Assertions.assertEquals(783, vendorsAllowed.get(vendorsAllowed.size() - 8));
    Assertions.assertEquals(784, vendorsAllowed.get(vendorsAllowed.size() - 7));
    Assertions.assertEquals(785, vendorsAllowed.get(vendorsAllowed.size() - 6));
    Assertions.assertEquals(786, vendorsAllowed.get(vendorsAllowed.size() - 5));
    Assertions.assertEquals(788, vendorsAllowed.get(vendorsAllowed.size() - 4));
    Assertions.assertEquals(789, vendorsAllowed.get(vendorsAllowed.size() - 3));
    Assertions.assertEquals(790, vendorsAllowed.get(vendorsAllowed.size() - 2));
    Assertions.assertEquals(791, vendorsAllowed.get(vendorsAllowed.size() - 1));

    Assertions.assertEquals(1, tcfEuV2.getFieldValue("VendorsDisclosedSegmentType"));
    List<Integer> vendorsDisclosed = (List<Integer>) tcfEuV2.getFieldValue("VendorsDisclosed");
    Assertions.assertEquals(434, vendorsDisclosed.size());
    Assertions.assertEquals(1, vendorsDisclosed.get(0));
    Assertions.assertEquals(2, vendorsDisclosed.get(1));
    Assertions.assertEquals(4, vendorsDisclosed.get(2));
    Assertions.assertEquals(6, vendorsDisclosed.get(3));
    Assertions.assertEquals(8, vendorsDisclosed.get(4));
    Assertions.assertEquals(10, vendorsDisclosed.get(5));
    Assertions.assertEquals(11, vendorsDisclosed.get(6));
    Assertions.assertEquals(12, vendorsDisclosed.get(7));
    Assertions.assertEquals(13, vendorsDisclosed.get(8));
    Assertions.assertEquals(14, vendorsDisclosed.get(9));
    Assertions.assertEquals(782, vendorsDisclosed.get(vendorsDisclosed.size() - 9));
    Assertions.assertEquals(783, vendorsDisclosed.get(vendorsDisclosed.size() - 8));
    Assertions.assertEquals(784, vendorsDisclosed.get(vendorsDisclosed.size() - 7));
    Assertions.assertEquals(785, vendorsDisclosed.get(vendorsDisclosed.size() - 6));
    Assertions.assertEquals(786, vendorsDisclosed.get(vendorsDisclosed.size() - 5));
    Assertions.assertEquals(788, vendorsDisclosed.get(vendorsDisclosed.size() - 4));
    Assertions.assertEquals(789, vendorsDisclosed.get(vendorsDisclosed.size() - 3));
    Assertions.assertEquals(790, vendorsDisclosed.get(vendorsDisclosed.size() - 2));
    Assertions.assertEquals(791, vendorsDisclosed.get(vendorsDisclosed.size() - 1));

    Assertions.assertEquals(tcfEuV2.getFieldValue("Version"), tcfEuV2.getVersion());
    Assertions.assertEquals(tcfEuV2.getFieldValue("Created"), tcfEuV2.getCreated());
    Assertions.assertEquals(tcfEuV2.getFieldValue("LastUpdated"), tcfEuV2.getLastUpdated());
    Assertions.assertEquals(tcfEuV2.getFieldValue("CmpId"), tcfEuV2.getCmpId());
    Assertions.assertEquals(tcfEuV2.getFieldValue("CmpVersion"), tcfEuV2.getCmpVersion());
    Assertions.assertEquals(tcfEuV2.getFieldValue("ConsentScreen"), tcfEuV2.getConsentScreen());
    Assertions.assertEquals(tcfEuV2.getFieldValue("ConsentLanguage"), tcfEuV2.getConsentLanguage());
    Assertions.assertEquals(tcfEuV2.getFieldValue("VendorListVersion"), tcfEuV2.getVendorListVersion());
    Assertions.assertEquals(tcfEuV2.getFieldValue("PolicyVersion"), tcfEuV2.getPolicyVersion());
    Assertions.assertEquals(tcfEuV2.getFieldValue("IsServiceSpecific"), tcfEuV2.getIsServiceSpecific());
    Assertions.assertEquals(tcfEuV2.getFieldValue("UseNonStandardStacks"), tcfEuV2.getUseNonStandardStacks());
    Assertions.assertEquals(tcfEuV2.getFieldValue("SpecialFeatureOptins"), tcfEuV2.getSpecialFeatureOptins());
    Assertions.assertEquals(tcfEuV2.getFieldValue("PurposeConsents"), tcfEuV2.getPurposeConsents());
    Assertions.assertEquals(tcfEuV2.getFieldValue("PurposeLegitimateInterests"),
        tcfEuV2.getPurposeLegitimateInterests());
    Assertions.assertEquals(tcfEuV2.getFieldValue("PurposeOneTreatment"), tcfEuV2.getPurposeOneTreatment());
    Assertions.assertEquals(tcfEuV2.getFieldValue("PublisherCountryCode"), tcfEuV2.getPublisherCountryCode());
    Assertions.assertEquals(tcfEuV2.getFieldValue("VendorConsents"), tcfEuV2.getVendorConsents());
    Assertions.assertEquals(tcfEuV2.getFieldValue("VendorLegitimateInterests"), tcfEuV2.getVendorLegitimateInterests());
    Assertions.assertEquals(tcfEuV2.getFieldValue("PublisherRestrictions"), tcfEuV2.getPublisherRestrictions());
    Assertions.assertEquals(tcfEuV2.getFieldValue("PublisherPurposesSegmentType"),
        tcfEuV2.getPublisherPurposesSegmentType());
    Assertions.assertEquals(tcfEuV2.getFieldValue("PublisherConsents"), tcfEuV2.getPublisherConsents());
    Assertions.assertEquals(tcfEuV2.getFieldValue("PublisherLegitimateInterests"),
        tcfEuV2.getPublisherLegitimateInterests());
    Assertions.assertEquals(tcfEuV2.getFieldValue("NumCustomPurposes"), tcfEuV2.getNumCustomPurposes());
    Assertions.assertEquals(tcfEuV2.getFieldValue("PublisherCustomConsents"), tcfEuV2.getPublisherCustomConsents());
    Assertions.assertEquals(tcfEuV2.getFieldValue("PublisherCustomLegitimateInterests"),
        tcfEuV2.getPublisherCustomLegitimateInterests());
    Assertions.assertEquals(tcfEuV2.getFieldValue("VendorsAllowedSegmentType"), tcfEuV2.getVendorsAllowedSegmentType());
    Assertions.assertEquals(tcfEuV2.getFieldValue("VendorsAllowed"), tcfEuV2.getVendorsAllowed());
    Assertions.assertEquals(tcfEuV2.getFieldValue("VendorsDisclosedSegmentType"),
        tcfEuV2.getVendorsDisclosedSegmentType());
    Assertions.assertEquals(tcfEuV2.getFieldValue("VendorsDisclosed"), tcfEuV2.getVendorsDisclosed());
  }
}
