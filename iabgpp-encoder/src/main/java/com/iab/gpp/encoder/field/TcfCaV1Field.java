package com.iab.gpp.encoder.field;

public class TcfCaV1Field {

  public static final String VERSION = "Version";
  public static final String CREATED = "Created";
  public static final String LAST_UPDATED = "LastUpdated";
  public static final String CMP_ID = "CmpId";
  public static final String CMP_VERSION = "CmpVersion";
  public static final String CONSENT_SCREEN = "ConsentScreen";
  public static final String CONSENT_LANGUAGE = "ConsentLanguage";
  public static final String VENDOR_LIST_VERSION = "VendorListVersion";
  public static final String TCF_POLICY_VERSION = "TcfPolicyVersion";
  public static final String USE_NON_STANDARD_STACKS = "UseNonStandardStacks";
  public static final String SPECIAL_FEATURE_EXPRESS_CONSENT = "SpecialFeatureExpressConsent";
  public static final String PURPOSES_EXPRESS_CONSENT = "PurposesExpressConsent";
  public static final String PURPOSES_IMPLIED_CONSENT = "PurposesImpliedConsent";
  public static final String VENDOR_EXPRESS_CONSENT = "VendorExpressConsent";
  public static final String VENDOR_IMPLIED_CONSENT = "VendorImpliedConsent";
  public static final String PUB_RESTRICTIONS = "PubRestrictions";

  public static final String PUB_PURPOSES_SEGMENT_TYPE = "PubPurposesSegmentType";
  public static final String PUB_PURPOSES_EXPRESS_CONSENT = "PubPurposesExpressConsent";
  public static final String PUB_PURPOSES_IMPLIED_CONSENT = "PubPurposesImpliedConsent";
  public static final String NUM_CUSTOM_PURPOSES = "NumCustomPurposes";
  public static final String CUSTOM_PURPOSES_EXPRESS_CONSENT = "CustomPurposesExpressConsent";
  public static final String CUSTOM_PURPOSES_IMPLIED_CONSENT = "CustomPurposesImpliedConsent";

  public static final String DISCLOSED_VENDORS_SEGMENT_TYPE = "DisclosedVendorsSegmentType";
  public static final String DISCLOSED_VENDORS = "DisclosedVendors";

  //@formatter:off
  public static final FieldNames TCFCAV1_CORE_SEGMENT_FIELD_NAMES = FieldNames.of(
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
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames TCFCAV1_PUBLISHER_PURPOSES_SEGMENT_FIELD_NAMES = FieldNames.of(
    TcfCaV1Field.PUB_PURPOSES_SEGMENT_TYPE,
    TcfCaV1Field.PUB_PURPOSES_EXPRESS_CONSENT,
    TcfCaV1Field.PUB_PURPOSES_IMPLIED_CONSENT,
    TcfCaV1Field.NUM_CUSTOM_PURPOSES,
    TcfCaV1Field.CUSTOM_PURPOSES_EXPRESS_CONSENT,
    TcfCaV1Field.CUSTOM_PURPOSES_IMPLIED_CONSENT
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames TCFCAV1_DISCLOSED_VENDORS_SEGMENT_FIELD_NAMES = FieldNames.of(
    TcfCaV1Field.DISCLOSED_VENDORS_SEGMENT_TYPE,
    TcfCaV1Field.DISCLOSED_VENDORS
  );
  //@formatter:on
}
