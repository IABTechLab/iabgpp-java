package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableArrayOfFixedIntegerRanges;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableDatetime;
import com.iab.gpp.encoder.datatype.EncodableFixedBitfield;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedString;
import com.iab.gpp.encoder.datatype.EncodableFlexibleBitfield;
import com.iab.gpp.encoder.datatype.EncodableOptimizedFixedRange;
import com.iab.gpp.encoder.section.TcfCaV1;

public enum TcfCaV1Field implements FieldKey {
  VERSION(new EncodableFixedInteger<>("Version", 6, TcfCaV1.VERSION)),
  CREATED(new EncodableDatetime<>("Created")),
  LAST_UPDATED(new EncodableDatetime<>("LastUpdated")),
  CMP_ID(new EncodableFixedInteger<>("CmpId", 12, 0)),
  CMP_VERSION(new EncodableFixedInteger<>("CmpVersion", 12, 0)),
  CONSENT_SCREEN(new EncodableFixedInteger<>("ConsentScreen", 6, 0)),
  CONSENT_LANGUAGE(new EncodableFixedString<>("ConsentLanguage", 2, "EN")),
  VENDOR_LIST_VERSION(new EncodableFixedInteger<>("VendorListVersion", 12, 0)),
  TCF_POLICY_VERSION(new EncodableFixedInteger<>("TcfPolicyVersion", 6, 2)),
  USE_NON_STANDARD_STACKS(new EncodableBoolean<>("UseNonStandardStacks", false)),
  SPECIAL_FEATURE_EXPRESS_CONSENT(new EncodableFixedBitfield<>("SpecialFeatureExpressConsent", 12)),
  PURPOSES_EXPRESS_CONSENT(new EncodableFixedBitfield<>("PurposesExpressConsent", 24)),
  PURPOSES_IMPLIED_CONSENT(new EncodableFixedBitfield<>("PurposesImpliedConsent", 24)),
  VENDOR_EXPRESS_CONSENT(new EncodableOptimizedFixedRange<>("VendorExpressConsent")),
  VENDOR_IMPLIED_CONSENT(new EncodableOptimizedFixedRange<>("VendorImpliedConsent")),
  PUB_RESTRICTIONS(new EncodableArrayOfFixedIntegerRanges<>("PubRestrictions", 6, 2)),

  PUB_PURPOSES_SEGMENT_TYPE(new EncodableFixedInteger<>("PubPurposesSegmentType", 3, 3)),
  PUB_PURPOSES_EXPRESS_CONSENT(new EncodableFixedBitfield<>("PubPurposesExpressConsent", 24)),
  PUB_PURPOSES_IMPLIED_CONSENT(new EncodableFixedBitfield<>("PubPurposesImpliedConsent", 24)),
  NUM_CUSTOM_PURPOSES(new EncodableFixedInteger<>("NumCustomPurposes", 6, 0)),
  CUSTOM_PURPOSES_EXPRESS_CONSENT(new EncodableFlexibleBitfield<>("CustomPurposesExpressConsent", TcfCaV1Field.NUM_CUSTOM_PURPOSES)),
  CUSTOM_PURPOSES_IMPLIED_CONSENT(new EncodableFlexibleBitfield<>("CustomPurposesImpliedConsent", TcfCaV1Field.NUM_CUSTOM_PURPOSES)),

  DISCLOSED_VENDORS_SEGMENT_TYPE(new EncodableFixedInteger<>("DisclosedVendorsSegmentType", 3, 1)),
  DISCLOSED_VENDORS(new EncodableOptimizedFixedRange<>("DisclosedVendors"));

  private final DataType<TcfCaV1Field, ?> type;

  TcfCaV1Field(DataType<TcfCaV1Field, ?> type) {
    this.type = type;
  }

  @Override
  public DataType<TcfCaV1Field, ?> getType() {
    return type;
  }
  //@formatter:off
  public static final FieldNames<TcfCaV1Field> TCFCAV1_CORE_SEGMENT_FIELD_NAMES = new FieldNames<>(
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
  public static final FieldNames<TcfCaV1Field> TCFCAV1_PUBLISHER_PURPOSES_SEGMENT_FIELD_NAMES = new FieldNames<>(
    TcfCaV1Field.PUB_PURPOSES_SEGMENT_TYPE,
    TcfCaV1Field.PUB_PURPOSES_EXPRESS_CONSENT,
    TcfCaV1Field.PUB_PURPOSES_IMPLIED_CONSENT,
    TcfCaV1Field.NUM_CUSTOM_PURPOSES,
    TcfCaV1Field.CUSTOM_PURPOSES_EXPRESS_CONSENT,
    TcfCaV1Field.CUSTOM_PURPOSES_IMPLIED_CONSENT
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames<TcfCaV1Field> TCFCAV1_DISCLOSED_VENDORS_SEGMENT_FIELD_NAMES = new FieldNames<>(
    TcfCaV1Field.DISCLOSED_VENDORS_SEGMENT_TYPE,
    TcfCaV1Field.DISCLOSED_VENDORS
  );
  //@formatter:on
}
