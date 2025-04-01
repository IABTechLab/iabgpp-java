package com.iab.gpp.encoder.section;


import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.datatype.RangeEntry;
import com.iab.gpp.encoder.datatype.encoder.BitStringSet;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.field.TcfCaV1Field;

public class TcfCaV1Test {

  @Test
  public void testEncode1() {

    TcfCaV1 tcfCaV1 = new TcfCaV1();
    tcfCaV1.setFieldValue(TcfCaV1Field.CREATED, ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")));
    tcfCaV1.setFieldValue(TcfCaV1Field.LAST_UPDATED, ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")));
    Assertions.assertEquals("BPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAAAAAA.YAAAAAAAAAA", tcfCaV1.encode());
  }

  @Test
  public void testEncode2() {

    TcfCaV1 tcfCaV1 = new TcfCaV1();
    tcfCaV1.setFieldValue(TcfCaV1Field.CMP_ID, 50);
    tcfCaV1.setFieldValue(TcfCaV1Field.CMP_VERSION, 2);
    tcfCaV1.setFieldValue(TcfCaV1Field.VENDOR_LIST_VERSION, 413);
    tcfCaV1.setFieldValue(TcfCaV1Field.USE_NON_STANDARD_STACKS, true);
    tcfCaV1.setFieldValue(TcfCaV1Field.SPECIAL_FEATURE_EXPRESS_CONSENT,
        Arrays.asList(6, 7, 8, 9, 10, 11));
    tcfCaV1.setFieldValue(TcfCaV1Field.PURPOSES_EXPRESS_CONSENT,
        Arrays.asList(0, 1, 2, 3, 4, 5, 12, 13, 14, 15, 16, 17));
    tcfCaV1.setFieldValue(TcfCaV1Field.PURPOSES_IMPLIED_CONSENT,
        Arrays.asList(6, 7, 8, 9, 10, 11, 18, 19, 20, 21, 22, 23));
    tcfCaV1.setFieldValue(TcfCaV1Field.VENDOR_EXPRESS_CONSENT, Arrays.asList(12, 24, 48));
    tcfCaV1.setFieldValue(TcfCaV1Field.VENDOR_IMPLIED_CONSENT, Arrays.asList(18, 30));
    tcfCaV1.setFieldValue(TcfCaV1Field.PUB_PURPOSES_EXPRESS_CONSENT,
        Arrays.asList(0, 1, 2, 6, 7, 8, 12, 13, 14, 18, 19, 20));
    tcfCaV1.setFieldValue(TcfCaV1Field.PUB_PURPOSES_IMPLIED_CONSENT,
        Arrays.asList(3, 4, 5, 9, 10, 11, 15, 16, 17, 21, 22, 23));
    tcfCaV1.setFieldValue(TcfCaV1Field.NUM_CUSTOM_PURPOSES, 3);
    tcfCaV1.setFieldValue(TcfCaV1Field.CUSTOM_PURPOSES_EXPRESS_CONSENT, Set.of(1));
    tcfCaV1.setFieldValue(TcfCaV1Field.CUSTOM_PURPOSES_IMPLIED_CONSENT, Set.of(0,2));

    tcfCaV1.setFieldValue(TcfCaV1Field.CREATED, ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")));
    tcfCaV1.setFieldValue(TcfCaV1Field.LAST_UPDATED, ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")));

    Assertions.assertEquals("BPSG_8APSG_8AAyACAENGdCgf_gfgAfgfgBgABABAAABAB4AACACAAA.fHHHA4444ao", tcfCaV1.encode());
  }

  @Test
  public void testEncode3() throws EncodingException, InvalidFieldException {

    TcfCaV1 tcfCaV1 = new TcfCaV1();
    tcfCaV1.setFieldValue(TcfCaV1Field.DISCLOSED_VENDORS, Arrays.asList(1, 2, 3, 5, 6, 7, 10, 11, 12));

    tcfCaV1.setFieldValue(TcfCaV1Field.CREATED, ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")));
    tcfCaV1.setFieldValue(TcfCaV1Field.LAST_UPDATED, ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")));

    Assertions.assertEquals("BPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAAAAAA.YAAAAAAAAAA.IAGO5w", tcfCaV1.encode());
  }

  @Test
  public void testEncode4() throws EncodingException, InvalidFieldException {

    List<RangeEntry> pubRestrictions = new ArrayList<>();
    pubRestrictions.add(new RangeEntry(1, 1, Set.of(1, 2, 3, 5, 6, 7, 9)));

    TcfCaV1 tcfCaV1 = new TcfCaV1();
    tcfCaV1.setFieldValue(TcfCaV1Field.PUB_RESTRICTIONS, pubRestrictions);

    tcfCaV1.setFieldValue(TcfCaV1Field.CREATED, ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")));
    tcfCaV1.setFieldValue(TcfCaV1Field.LAST_UPDATED, ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")));
    Assertions.assertEquals("BPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAAAACCgBwABAAOAAoADgAJA.YAAAAAAAAAA", tcfCaV1.encode());
  }

  @Test
  public void testDecode1() {
    TcfCaV1 tcfCaV1 = new TcfCaV1("BPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAAAAAA.YAAAAAAAAAA");

    Assertions.assertEquals(0, tcfCaV1.getCmpId());
    Assertions.assertEquals(0, tcfCaV1.getCmpVersion());
    Assertions.assertEquals(0, tcfCaV1.getVendorListVersion());
    Assertions.assertEquals(false, tcfCaV1.getUseNonStandardStacks());
    Assertions.assertEquals(
        Set.of(),
        tcfCaV1.getSpecialFeatureExpressConsent());
    Assertions
        .assertEquals(
            Set.of(),
            tcfCaV1.getPurposesExpressConsent());
    Assertions
        .assertEquals(
            Set.of(),
            tcfCaV1.getPurposesImpliedConsent());
    Assertions.assertEquals(Set.of(), tcfCaV1.getVendorExpressConsent());
    Assertions.assertEquals(Set.of(), tcfCaV1.getVendorImpliedConsent());
    Assertions.assertEquals(
        Set.of(),
        tcfCaV1.getPubPurposesExpressConsent());
    Assertions.assertEquals(
        Set.of(),
        tcfCaV1.getPubPurposesImpliedConsent());
    Assertions.assertEquals(0, tcfCaV1.getNumCustomPurposes());
    Assertions.assertEquals(Set.of(), tcfCaV1.getCustomPurposesExpressConsent());
    Assertions.assertEquals(Set.of(), tcfCaV1.getCustomPurposesImpliedConsent());
    Assertions.assertEquals(ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")), tcfCaV1.getCreated());
    Assertions.assertEquals(ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")), tcfCaV1.getLastUpdated());

    Assertions.assertEquals("EN", tcfCaV1.getConsentLanguage());
    Assertions.assertEquals(5, tcfCaV1.getId());
    Assertions.assertEquals(3, tcfCaV1.getPubPurposesSegmentType());
  }

  @Test
  public void testDecode2() {
    TcfCaV1 tcfCaV1 = new TcfCaV1("BPSG_8APSG_8AAyACAENGdCgf_gfgAfgfgBgABABAAABAB4AACACAAA.fHHHA4444ao");

    Assertions.assertEquals(50, tcfCaV1.getCmpId());
    Assertions.assertEquals(2, tcfCaV1.getCmpVersion());
    Assertions.assertEquals(413, tcfCaV1.getVendorListVersion());
    Assertions.assertEquals(true, tcfCaV1.getUseNonStandardStacks());
    Assertions.assertEquals(Set.of(6, 7, 8, 9, 10, 11),
        tcfCaV1.getSpecialFeatureExpressConsent());
    Assertions.assertEquals(Set.of(0, 1, 2, 3, 4, 5, 12, 13, 14, 15, 16, 17),
        tcfCaV1.getPurposesExpressConsent());
    Assertions.assertEquals(Set.of(6, 7, 8, 9, 10, 11, 18, 19, 20, 21, 22, 23),
        tcfCaV1.getPurposesImpliedConsent());
    Assertions.assertEquals(Set.of(12, 24, 48), tcfCaV1.getVendorExpressConsent());
    Assertions.assertEquals(Set.of(18, 30), tcfCaV1.getVendorImpliedConsent());
    Assertions
        .assertEquals(
            Set.of(0, 1, 2, 6, 7, 8, 12, 13, 14, 18, 19, 20),
            tcfCaV1.getPubPurposesExpressConsent());
    Assertions
        .assertEquals(
            Set.of(3, 4, 5, 9, 10, 11, 15, 16, 17, 21, 22, 23),
            tcfCaV1.getPubPurposesImpliedConsent());
    Assertions.assertEquals(3, tcfCaV1.getNumCustomPurposes());
    Assertions.assertEquals(Set.of(1), tcfCaV1.getCustomPurposesExpressConsent());
    Assertions.assertEquals(Set.of(0, 2), tcfCaV1.getCustomPurposesImpliedConsent());
    Assertions.assertEquals(ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")), tcfCaV1.getCreated());
    Assertions.assertEquals(ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")), tcfCaV1.getLastUpdated());

    Assertions.assertEquals("EN", tcfCaV1.getConsentLanguage());
    Assertions.assertEquals(5, tcfCaV1.getId());
    Assertions.assertEquals(3, tcfCaV1.getPubPurposesSegmentType());
  }

  @Test
  public void testDecode3() throws DecodingException {
    TcfCaV1 tcfCaV1 = new TcfCaV1("BPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAAAAAA.YAAAAAAAAAA.IAGO5w");

    Assertions.assertEquals(1, tcfCaV1.getDisclosedVendorsSegmentType());
    Assertions.assertEquals(Set.of(1, 2, 3, 5, 6, 7, 10, 11, 12), tcfCaV1.getDisclosedVendors());
  }

  @Test
  public void testDecode4() throws DecodingException {
    TcfCaV1 tcfCaV1 = new TcfCaV1("BPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAAAACCgBwABAAOAAoADgAJA.YAAAAAAAAAA");

    List<RangeEntry> pubRestictions = tcfCaV1.getPubRestrictions();
    Assertions.assertEquals(1, pubRestictions.size());
    Assertions.assertEquals(1, pubRestictions.get(0).getKey());
    Assertions.assertEquals(1, pubRestictions.get(0).getType());
    Assertions.assertEquals(Set.of(1, 2, 3, 5, 6, 7, 9), pubRestictions.get(0).getIds());
  }
  
  @Test()
  public void testDecodeGarbage1() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new TcfCaV1("A").getPubRestrictions();
    });
  }
  
  @Test()
  public void testDecodeGarbage2() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new TcfCaV1("z").getPubRestrictions();
    });
  }
}
