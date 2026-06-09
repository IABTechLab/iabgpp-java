package com.iab.gpp.encoder.section;


import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.datatype.RangeEntry;
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

    Assertions.assertEquals("BPSG_8APSG_8AAyACAENGdCgf_gfgAfgfgBhADVqxGAD0AILVgAA.fHHHA4444ao", tcfCaV1.encode());
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
    pubRestrictions.add(new RangeEntry(1, 1, Arrays.asList(1, 2, 3, 5, 6, 7, 9)));

    TcfCaV1 tcfCaV1 = new TcfCaV1();
    tcfCaV1.setFieldValue(TcfCaV1Field.PUB_RESTRICTIONS, pubRestrictions);

    tcfCaV1.setFieldValue(TcfCaV1Field.CREATED, ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")));
    tcfCaV1.setFieldValue(TcfCaV1Field.LAST_UPDATED, ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")));
    Assertions.assertEquals("BPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAAAACCgAS7o.YAAAAAAAAAA", tcfCaV1.encode());
  }

  @Test
  public void testDecode1() {
    TcfCaV1 tcfCaV1 = new TcfCaV1("BPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAAAAAA.YAAAAAAAAAA");

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
    Assertions.assertEquals(ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")), tcfCaV1.getCreated());
    Assertions.assertEquals(ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")), tcfCaV1.getLastUpdated());

    Assertions.assertEquals("EN", tcfCaV1.getConsentLanguage());
    Assertions.assertEquals(5, tcfCaV1.getId());
    Assertions.assertEquals(3, tcfCaV1.getPubPurposesSegmentType());
  }

  @Test
  public void testDecode2() {
    TcfCaV1 tcfCaV1 = new TcfCaV1("BPSG_8APSG_8AAyACAENGdCgf_gfgAfgfgBhADVqxGAD0AILVgAA.fHHHA4444ao");

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

  @Test
  public void testDecode3() throws DecodingException {
    TcfCaV1 tcfCaV1 = new TcfCaV1("BPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAAAAAA.YAAAAAAAAAA.IAGO5w");

    Assertions.assertEquals(1, tcfCaV1.getDisclosedVendorsSegmentType());
    Assertions.assertEquals(Arrays.asList(1, 2, 3, 5, 6, 7, 10, 11, 12), tcfCaV1.getDisclosedVendors());
  }

  @Test
  public void testDecode4() throws DecodingException {
    TcfCaV1 tcfCaV1 = new TcfCaV1("BPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAAAACCgAS7o.YAAAAAAAAAA");

    List<RangeEntry> pubRestictions = tcfCaV1.getPubRestrictions();
    Assertions.assertEquals(1, pubRestictions.size());
    Assertions.assertEquals(1, pubRestictions.get(0).getKey());
    Assertions.assertEquals(1, pubRestictions.get(0).getType());
    Assertions.assertEquals(Arrays.asList(1, 2, 3, 5, 6, 7, 9), pubRestictions.get(0).getIds());
  }
  
  @Test
  public void testEncodeDecodeVendorRangeRoundTrip() {
    // Sparse, high vendor IDs force the OptimizedRange to choose the (Fibonacci) range
    // representation over a bitfield, exercising the Fibonacci range encode/decode path.
    List<RangeEntry> pubRestrictions = new ArrayList<>();
    pubRestrictions.add(new RangeEntry(1, 0, Arrays.asList(5, 100, 101, 102, 800)));
    pubRestrictions.add(new RangeEntry(2, 2, Arrays.asList(3, 500)));

    TcfCaV1 tcfCaV1 = new TcfCaV1();
    tcfCaV1.setFieldValue(TcfCaV1Field.VENDOR_EXPRESS_CONSENT, Arrays.asList(1, 100, 200));
    tcfCaV1.setFieldValue(TcfCaV1Field.VENDOR_IMPLIED_CONSENT, Arrays.asList(50, 51, 52, 999));
    tcfCaV1.setFieldValue(TcfCaV1Field.DISCLOSED_VENDORS, Arrays.asList(2, 250, 600));
    tcfCaV1.setFieldValue(TcfCaV1Field.PUB_RESTRICTIONS, pubRestrictions);

    TcfCaV1 decoded = new TcfCaV1(tcfCaV1.encode());
    Assertions.assertEquals(Arrays.asList(1, 100, 200), decoded.getVendorExpressConsent());
    Assertions.assertEquals(Arrays.asList(50, 51, 52, 999), decoded.getVendorImpliedConsent());
    Assertions.assertEquals(Arrays.asList(2, 250, 600), decoded.getDisclosedVendors());

    List<RangeEntry> decodedPubRestrictions = decoded.getPubRestrictions();
    Assertions.assertEquals(2, decodedPubRestrictions.size());
    Assertions.assertEquals(1, decodedPubRestrictions.get(0).getKey());
    Assertions.assertEquals(0, decodedPubRestrictions.get(0).getType());
    Assertions.assertEquals(Arrays.asList(5, 100, 101, 102, 800), decodedPubRestrictions.get(0).getIds());
    Assertions.assertEquals(2, decodedPubRestrictions.get(1).getKey());
    Assertions.assertEquals(2, decodedPubRestrictions.get(1).getType());
    Assertions.assertEquals(Arrays.asList(3, 500), decodedPubRestrictions.get(1).getIds());
  }

  @Test
  public void testDecodeLegacyFixedRangeVendors() {
    // String produced by the pre-fix encoder, which used fixed-integer ranges for the
    // VendorExpressConsent / VendorImpliedConsent OptimizedRange fields. The decoder must still
    // read it correctly via the backwards-compatible fallback.
    TcfCaV1 tcfCaV1 = new TcfCaV1("BPSG_8APSG_8AAyACAENGdCgf_gfgAfgfgBgABABAAABAB4AACACAAA.fHHHA4444ao");

    Assertions.assertEquals(Arrays.asList(12, 24, 48), tcfCaV1.getVendorExpressConsent());
    Assertions.assertEquals(Arrays.asList(18, 30), tcfCaV1.getVendorImpliedConsent());
  }

  @Test
  public void testDecodeLegacyFixedRangePubRestrictions() {
    // String produced by the pre-fix encoder, which used fixed-integer ranges for the
    // PubRestrictions ids.
    TcfCaV1 tcfCaV1 = new TcfCaV1("BPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAAAACCgBwABAAOAAoADgAJA.YAAAAAAAAAA");

    List<RangeEntry> pubRestrictions = tcfCaV1.getPubRestrictions();
    Assertions.assertEquals(1, pubRestrictions.size());
    Assertions.assertEquals(1, pubRestrictions.get(0).getKey());
    Assertions.assertEquals(1, pubRestrictions.get(0).getType());
    Assertions.assertEquals(Arrays.asList(1, 2, 3, 5, 6, 7, 9), pubRestrictions.get(0).getIds());
  }

  @Test
  public void testDecodeLegacyStringAndReencodeToSpecCompliant() {
    // A real TcfCaV1 string produced by the pre-fix encoder (fixed-integer OptimizedRange). The
    // backwards-compatible decoder reads it, and re-encoding emits the spec-compliant Fibonacci form.
    String legacy =
        "BQliWsAQliWsAPoABAELC9CoAKgAAJIAAApNAOABUAC0AGgAQwAlgBQAC6AG0AO4AfgBBATAAnMBSYEwYFgAXQBOwC3ALgAc4A7gCAAEmAJ2AT8AxQBmgDOgGfANeAcQA6oCJgEngJyAT-Ao8BUQCpQFvALhAXQAvcBf4DMAGggNNAbUA3EBxoDlgHiAPNAfIBAQCEgEbgI_gSlgmACYIAA.YAAAAAAAAAA";
    TcfCaV1 tcfCaV1 = new TcfCaV1(legacy);

    Assertions.assertEquals(1000, tcfCaV1.getCmpId());
    Assertions.assertEquals(1, tcfCaV1.getCmpVersion());
    Assertions.assertEquals("EL", tcfCaV1.getConsentLanguage());
    Assertions.assertEquals(189, tcfCaV1.getVendorListVersion());
    Assertions.assertEquals(true, tcfCaV1.getUseNonStandardStacks());
    Assertions.assertEquals(Arrays.asList(42, 45, 52, 67, 75, 80, 93, 109, 119, 126, 130, 1216, 1254, 1318),
        tcfCaV1.getVendorExpressConsent());
    Assertions.assertEquals(
        Arrays.asList(93, 157, 183, 184, 231, 238, 256, 294, 315, 319, 394, 410, 413, 415, 431, 452, 469, 550, 591, 626,
            639, 655, 674, 677, 734, 737, 744, 759, 767, 816, 833, 845, 874, 881, 909, 918, 964, 973, 996, 1028, 1060,
            1134, 1151, 1189, 1216, 1217),
        tcfCaV1.getVendorImpliedConsent());

    // Touching the timestamps (preserving their values) marks the core segment dirty so encode()
    // re-emits the string; setting Created/LastUpdated does not trigger the automatic "now" update.
    tcfCaV1.setFieldValue(TcfCaV1Field.CREATED, tcfCaV1.getCreated());
    tcfCaV1.setFieldValue(TcfCaV1Field.LAST_UPDATED, tcfCaV1.getLastUpdated());

    Assertions.assertEquals(
        "BQliWsAQliWsAPoABAELC9CoAKgAAJIAAApNAOBMZZGDDAxMmWskIahojBMGBYoGiOJ4FlhahgNZUxMZiYDUwllGgYGJpYyBjLIwZFqasFllNGqaMhisVpTU1DyeAAA.YAAAAAAAAAA",
        tcfCaV1.encode());
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
