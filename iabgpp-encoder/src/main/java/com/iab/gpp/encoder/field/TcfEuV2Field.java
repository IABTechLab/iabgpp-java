package com.iab.gpp.encoder.field;

public final class TcfEuV2Field {
  private TcfEuV2Field() {}

  public static final String VERSION = "Version";
  public static final String CREATED = "Created";
  public static final String LAST_UPDATED = "LastUpdated";
  public static final String CMP_ID = "CmpId";
  public static final String CMP_VERSION = "CmpVersion";
  public static final String CONSENT_SCREEN = "ConsentScreen";
  public static final String CONSENT_LANGUAGE = "ConsentLanguage";
  public static final String VENDOR_LIST_VERSION = "VendorListVersion";
  public static final String POLICY_VERSION = "PolicyVersion";
  public static final String IS_SERVICE_SPECIFIC = "IsServiceSpecific";
  public static final String USE_NON_STANDARD_STACKS = "UseNonStandardStacks";
  public static final String SPECIAL_FEATURE_OPTINS = "SpecialFeatureOptins";
  public static final String PURPOSE_CONSENTS = "PurposeConsents";
  public static final String PURPOSE_LEGITIMATE_INTERESTS = "PurposeLegitimateInterests";
  public static final String PURPOSE_ONE_TREATMENT = "PurposeOneTreatment";
  public static final String PUBLISHER_COUNTRY_CODE = "PublisherCountryCode";
  public static final String VENDOR_CONSENTS = "VendorConsents";
  public static final String VENDOR_LEGITIMATE_INTERESTS = "VendorLegitimateInterests";
  public static final String PUBLISHER_RESTRICTIONS = "PublisherRestrictions";
  public static final String PUBLISHER_PURPOSES_SEGMENT_TYPE = "PublisherPurposesSegmentType";
  public static final String PUBLISHER_CONSENTS = "PublisherConsents";
  public static final String PUBLISHER_LEGITIMATE_INTERESTS = "PublisherLegitimateInterests";
  public static final String NUM_CUSTOM_PURPOSES = "NumCustomPurposes";
  public static final String PUBLISHER_CUSTOM_CONSENTS = "PublisherCustomConsents";
  public static final String PUBLISHER_CUSTOM_LEGITIMATE_INTERESTS = "PublisherCustomLegitimateInterests";
  public static final String VENDORS_ALLOWED_SEGMENT_TYPE = "VendorsAllowedSegmentType";
  public static final String VENDORS_ALLOWED = "VendorsAllowed";
  public static final String VENDORS_DISCLOSED_SEGMENT_TYPE = "VendorsDisclosedSegmentType";
  public static final String VENDORS_DISCLOSED = "VendorsDisclosed";

  //@formatter:off
  public static final FieldNames TCFEUV2_CORE_SEGMENT_FIELD_NAMES = FieldNames.of(
      TcfEuV2Field.VERSION,
      TcfEuV2Field.CREATED,
      TcfEuV2Field.LAST_UPDATED,
      TcfEuV2Field.CMP_ID,
      TcfEuV2Field.CMP_VERSION,
      TcfEuV2Field.CONSENT_SCREEN,
      TcfEuV2Field.CONSENT_LANGUAGE,
      TcfEuV2Field.VENDOR_LIST_VERSION,
      TcfEuV2Field.POLICY_VERSION,
      TcfEuV2Field.IS_SERVICE_SPECIFIC,
      TcfEuV2Field.USE_NON_STANDARD_STACKS,
      TcfEuV2Field.SPECIAL_FEATURE_OPTINS,
      TcfEuV2Field.PURPOSE_CONSENTS,
      TcfEuV2Field.PURPOSE_LEGITIMATE_INTERESTS,
      TcfEuV2Field.PURPOSE_ONE_TREATMENT,
      TcfEuV2Field.PUBLISHER_COUNTRY_CODE,
      TcfEuV2Field.VENDOR_CONSENTS,
      TcfEuV2Field.VENDOR_LEGITIMATE_INTERESTS,
      TcfEuV2Field.PUBLISHER_RESTRICTIONS
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames TCFEUV2_PUBLISHER_PURPOSES_SEGMENT_FIELD_NAMES = FieldNames.of(
      TcfEuV2Field.PUBLISHER_PURPOSES_SEGMENT_TYPE,
      TcfEuV2Field.PUBLISHER_CONSENTS,
      TcfEuV2Field.PUBLISHER_LEGITIMATE_INTERESTS,
      TcfEuV2Field.NUM_CUSTOM_PURPOSES,
      TcfEuV2Field.PUBLISHER_CUSTOM_CONSENTS,
      TcfEuV2Field.PUBLISHER_CUSTOM_LEGITIMATE_INTERESTS
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames TCFEUV2_VENDORS_ALLOWED_SEGMENT_FIELD_NAMES = FieldNames.of(
      TcfEuV2Field.VENDORS_ALLOWED_SEGMENT_TYPE,
      TcfEuV2Field.VENDORS_ALLOWED
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames TCFEUV2_VENDORS_DISCLOSED_SEGMENT_FIELD_NAMES = FieldNames.of(
      TcfEuV2Field.VENDORS_DISCLOSED_SEGMENT_TYPE,
      TcfEuV2Field.VENDORS_DISCLOSED
  );
  //@formatter:on
}
