package com.iab.gpp.encoder.field;

import java.util.Arrays;
import java.util.List;

public class TcfCaV1Field {

  public static String VERSION = "Version";
  public static String CREATED = "Created";
  public static String LAST_UPDATED = "LastUpdated";
  public static String CMP_ID = "CmpId";
  public static String CMP_VERSION = "CmpVersion";
  public static String CONSENT_SCREEN = "ConsentScreen";
  public static String CONSENT_LANGUAGE = "ConsentLanguage";
  public static String VENDOR_LIST_VERSION = "VendorListVersion";
  public static String TCF_POLICY_VERSION = "TcfPolicyVersion";
  public static String USE_NON_STANDARD_STACKS = "UseNonStandardStacks";
  public static String SPECIAL_FEATURE_EXPRESS_CONSENT = "SpecialFeatureExpressConsent";
  public static String PURPOSES_EXPRESS_CONSENT = "PurposesExpressConsent";
  public static String PURPOSES_IMPLIED_CONSENT = "PurposesImpliedConsent";
  public static String VENDOR_EXPRESS_CONSENT = "VendorExpressConsent";
  public static String VENDOR_IMPLIED_CONSENT = "VendorImpliedConsent";
  public static String PUB_RESTRICTIONS = "PubRestrictions";

  public static String PUB_PURPOSES_SEGMENT_TYPE = "PubPurposesSegmentType";
  public static String PUB_PURPOSES_EXPRESS_CONSENT = "PubPurposesExpressConsent";
  public static String PUB_PURPOSES_IMPLIED_CONSENT = "PubPurposesImpliedConsent";
  public static String NUM_CUSTOM_PURPOSES = "NumCustomPurposes";
  public static String CUSTOM_PURPOSES_EXPRESS_CONSENT = "CustomPurposesExpressConsent";
  public static String CUSTOM_PURPOSES_IMPLIED_CONSENT = "CustomPurposesImpliedConsent";

  public static String DISCLOSED_VENDORS_SEGMENT_TYPE = "DisclosedVendorsSegmentType";
  public static String DISCLOSED_VENDORS = "DisclosedVendors";
  
  //@formatter:off
  public static List<String> TCFCAV1_CORE_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      TcfCaV1Field.VERSION,
      TcfCaV1Field.CREATED,
      TcfCaV1Field.LAST_UPDATED,
      TcfCaV1Field.CMP_ID,
      TcfCaV1Field.CMP_VERSION,
      TcfCaV1Field.CONSENT_SCREEN,
      TcfCaV1Field.CONSENT_LANGUAGE,
      TcfCaV1Field.VENDOR_LIST_VERSION,
      TcfCaV1Field.TCF_POLICY_VERSION,
      TcfCaV1Field.USE_NON_STANDARD_STACKS,
      TcfCaV1Field.SPECIAL_FEATURE_EXPRESS_CONSENT,
      TcfCaV1Field.PURPOSES_EXPRESS_CONSENT,
      TcfCaV1Field.PURPOSES_IMPLIED_CONSENT,
      TcfCaV1Field.VENDOR_EXPRESS_CONSENT,
      TcfCaV1Field.VENDOR_IMPLIED_CONSENT,
      TcfCaV1Field.PUB_RESTRICTIONS
  });
  //@formatter:on

  //@formatter:off
  public static List<String> TCFCAV1_PUBLISHER_PURPOSES_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
    TcfCaV1Field.PUB_PURPOSES_SEGMENT_TYPE,
    TcfCaV1Field.PUB_PURPOSES_EXPRESS_CONSENT,
    TcfCaV1Field.PUB_PURPOSES_IMPLIED_CONSENT,
    TcfCaV1Field.NUM_CUSTOM_PURPOSES,
    TcfCaV1Field.CUSTOM_PURPOSES_EXPRESS_CONSENT,
    TcfCaV1Field.CUSTOM_PURPOSES_IMPLIED_CONSENT,
  });
  //@formatter:on
  
  //@formatter:off
  public static List<String> TCFCAV1_DISCLOSED_VENDORS_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
    TcfCaV1Field.DISCLOSED_VENDORS_SEGMENT_TYPE,
    TcfCaV1Field.DISCLOSED_VENDORS,
  });
  //@formatter:on
}
