package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.section.FieldKey;

public enum TcfCaV1Field implements FieldKey {
  VERSION("Version"),
  CREATED("Created"),
  LAST_UPDATED("LastUpdated"),
  CMP_ID("CmpId"),
  CMP_VERSION("CmpVersion"),
  CONSENT_SCREEN("ConsentScreen"),
  CONSENT_LANGUAGE("ConsentLanguage"),
  VENDOR_LIST_VERSION("VendorListVersion"),
  TCF_POLICY_VERSION("TcfPolicyVersion"),
  USE_NON_STANDARD_STACKS("UseNonStandardStacks"),
  SPECIAL_FEATURE_EXPRESS_CONSENT("SpecialFeatureExpressConsent"),
  PURPOSES_EXPRESS_CONSENT("PurposesExpressConsent"),
  PURPOSES_IMPLIED_CONSENT("PurposesImpliedConsent"),
  VENDOR_EXPRESS_CONSENT("VendorExpressConsent"),
  VENDOR_IMPLIED_CONSENT("VendorImpliedConsent"),
  PUB_RESTRICTIONS("PubRestrictions"),

  PUB_PURPOSES_SEGMENT_TYPE("PubPurposesSegmentType"),
  PUB_PURPOSES_EXPRESS_CONSENT("PubPurposesExpressConsent"),
  PUB_PURPOSES_IMPLIED_CONSENT("PubPurposesImpliedConsent"),
  NUM_CUSTOM_PURPOSES("NumCustomPurposes"),
  CUSTOM_PURPOSES_EXPRESS_CONSENT("CustomPurposesExpressConsent"),
  CUSTOM_PURPOSES_IMPLIED_CONSENT("CustomPurposesImpliedConsent"),

  DISCLOSED_VENDORS_SEGMENT_TYPE("DisclosedVendorsSegmentType"),
  DISCLOSED_VENDORS("DisclosedVendors");

  private String name;

  TcfCaV1Field(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  //@formatter:off
  public static final FieldNames<TcfCaV1Field> TCFCAV1_CORE_SEGMENT_FIELD_NAMES = FieldNames.of(
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
  public static final FieldNames<TcfCaV1Field> TCFCAV1_PUBLISHER_PURPOSES_SEGMENT_FIELD_NAMES = FieldNames.of(
    TcfCaV1Field.PUB_PURPOSES_SEGMENT_TYPE,
    TcfCaV1Field.PUB_PURPOSES_EXPRESS_CONSENT,
    TcfCaV1Field.PUB_PURPOSES_IMPLIED_CONSENT,
    TcfCaV1Field.NUM_CUSTOM_PURPOSES,
    TcfCaV1Field.CUSTOM_PURPOSES_EXPRESS_CONSENT,
    TcfCaV1Field.CUSTOM_PURPOSES_IMPLIED_CONSENT
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames<TcfCaV1Field> TCFCAV1_DISCLOSED_VENDORS_SEGMENT_FIELD_NAMES = FieldNames.of(
    TcfCaV1Field.DISCLOSED_VENDORS_SEGMENT_TYPE,
    TcfCaV1Field.DISCLOSED_VENDORS
  );
  //@formatter:on
}
