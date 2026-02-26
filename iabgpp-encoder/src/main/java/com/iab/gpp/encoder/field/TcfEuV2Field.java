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
import com.iab.gpp.encoder.section.TcfEuV2;

public enum TcfEuV2Field implements FieldKey {
  VERSION(new EncodableFixedInteger<>("Version", 6, TcfEuV2.VERSION)),
  CREATED(new EncodableDatetime<>("Created")),
  LAST_UPDATED(new EncodableDatetime<>("LastUpdated")),
  CMP_ID(new EncodableFixedInteger<>("CmpId", 12, 0)),
  CMP_VERSION(new EncodableFixedInteger<>("CmpVersion", 12, 0)),
  CONSENT_SCREEN(new EncodableFixedInteger<>("ConsentScreen", 6, 0)),
  CONSENT_LANGUAGE(new EncodableFixedString<>("ConsentLanguage", 2, "EN")),
  VENDOR_LIST_VERSION(new EncodableFixedInteger<>("VendorListVersion", 12, 0)),
  POLICY_VERSION(new EncodableFixedInteger<>("PolicyVersion", 6, 2)),
  IS_SERVICE_SPECIFIC(new EncodableBoolean<>("IsServiceSpecific", false)),
  USE_NON_STANDARD_STACKS(new EncodableBoolean<>("UseNonStandardStacks", false)),
  SPECIAL_FEATURE_OPTINS(new EncodableFixedBitfield<>("SpecialFeatureOptins", 12)),
  PURPOSE_CONSENTS(new EncodableFixedBitfield<>("PurposeConsents", 24)),
  PURPOSE_LEGITIMATE_INTERESTS(new EncodableFixedBitfield<>("PurposeLegitimateInterests", 24)),
  PURPOSE_ONE_TREATMENT(new EncodableBoolean<>("PurposeOneTreatment", false)),
  PUBLISHER_COUNTRY_CODE(new EncodableFixedString<>("PublisherCountryCode", 2, "AA")),
  VENDOR_CONSENTS(new EncodableOptimizedFixedRange<>("VendorConsents")),
  VENDOR_LEGITIMATE_INTERESTS(new EncodableOptimizedFixedRange<>("VendorLegitimateInterests")),
  PUBLISHER_RESTRICTIONS(new EncodableArrayOfFixedIntegerRanges<>("PublisherRestrictions", 6, 2)),
  PUBLISHER_PURPOSES_SEGMENT_TYPE(
      new EncodableFixedInteger<>("PublisherPurposesSegmentType", 3, 3)),
  PUBLISHER_CONSENTS(new EncodableFixedBitfield<>("PublisherConsents", 24)),
  PUBLISHER_LEGITIMATE_INTERESTS(new EncodableFixedBitfield<>("PublisherLegitimateInterests", 24)),
  NUM_CUSTOM_PURPOSES(new EncodableFixedInteger<>("NumCustomPurposes", 6, 0)),
  PUBLISHER_CUSTOM_CONSENTS(
      new EncodableFlexibleBitfield<>("PublisherCustomConsents", TcfEuV2Field.NUM_CUSTOM_PURPOSES)),
  PUBLISHER_CUSTOM_LEGITIMATE_INTERESTS(
      new EncodableFlexibleBitfield<>(
          "PublisherCustomLegitimateInterests", TcfEuV2Field.NUM_CUSTOM_PURPOSES)),
  VENDORS_ALLOWED_SEGMENT_TYPE(new EncodableFixedInteger<>("VendorsAllowedSegmentType", 3, 2)),
  VENDORS_ALLOWED(new EncodableOptimizedFixedRange<>("VendorsAllowed")),
  VENDORS_DISCLOSED_SEGMENT_TYPE(new EncodableFixedInteger<>("VendorsDisclosedSegmentType", 3, 1)),
  VENDORS_DISCLOSED(new EncodableOptimizedFixedRange<>("VendorsDisclosed"));

  private final DataType<TcfEuV2Field, ?> type;

  TcfEuV2Field(DataType<TcfEuV2Field, ?> type) {
    this.type = type;
  }

  @Override
  public DataType<TcfEuV2Field, ?> getType() {
    return type;
  }

  public static final FieldNames<TcfEuV2Field> TCFEUV2_CORE_SEGMENT_FIELD_NAMES =
      new FieldNames<>(
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
          TcfEuV2Field.PUBLISHER_RESTRICTIONS);

  public static final FieldNames<TcfEuV2Field> TCFEUV2_PUBLISHER_PURPOSES_SEGMENT_FIELD_NAMES =
      new FieldNames<>(
          TcfEuV2Field.PUBLISHER_PURPOSES_SEGMENT_TYPE,
          TcfEuV2Field.PUBLISHER_CONSENTS,
          TcfEuV2Field.PUBLISHER_LEGITIMATE_INTERESTS,
          TcfEuV2Field.NUM_CUSTOM_PURPOSES,
          TcfEuV2Field.PUBLISHER_CUSTOM_CONSENTS,
          TcfEuV2Field.PUBLISHER_CUSTOM_LEGITIMATE_INTERESTS);

  public static final FieldNames<TcfEuV2Field> TCFEUV2_VENDORS_ALLOWED_SEGMENT_FIELD_NAMES =
      new FieldNames<>(TcfEuV2Field.VENDORS_ALLOWED_SEGMENT_TYPE, TcfEuV2Field.VENDORS_ALLOWED);

  public static final FieldNames<TcfEuV2Field> TCFEUV2_VENDORS_DISCLOSED_SEGMENT_FIELD_NAMES =
      new FieldNames<>(TcfEuV2Field.VENDORS_DISCLOSED_SEGMENT_TYPE, TcfEuV2Field.VENDORS_DISCLOSED);
}
