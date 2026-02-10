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
  VERSION("Version", new EncodableFixedInteger<>(6, TcfEuV2.VERSION)),
  CREATED("Created", new EncodableDatetime<>()),
  LAST_UPDATED("LastUpdated", new EncodableDatetime<>()),
  CMP_ID("CmpId", new EncodableFixedInteger<>(12, 0)),
  CMP_VERSION("CmpVersion", new EncodableFixedInteger<>(12, 0)),
  CONSENT_SCREEN("ConsentScreen", new EncodableFixedInteger<>(6, 0)),
  CONSENT_LANGUAGE("ConsentLanguage", new EncodableFixedString<>(2, "EN")),
  VENDOR_LIST_VERSION("VendorListVersion", new EncodableFixedInteger<>(12, 0)),
  POLICY_VERSION("PolicyVersion", new EncodableFixedInteger<>(6, 2)),
  IS_SERVICE_SPECIFIC("IsServiceSpecific", new EncodableBoolean<>(false)),
  USE_NON_STANDARD_STACKS("UseNonStandardStacks", new EncodableBoolean<>(false)),
  SPECIAL_FEATURE_OPTINS("SpecialFeatureOptins", new EncodableFixedBitfield<>(12)),
  PURPOSE_CONSENTS("PurposeConsents", new EncodableFixedBitfield<>(24)),
  PURPOSE_LEGITIMATE_INTERESTS("PurposeLegitimateInterests", new EncodableFixedBitfield<>(24)),
  PURPOSE_ONE_TREATMENT("PurposeOneTreatment", new EncodableBoolean<>(false)),
  PUBLISHER_COUNTRY_CODE("PublisherCountryCode", new EncodableFixedString<>(2, "AA")),
  VENDOR_CONSENTS("VendorConsents", new EncodableOptimizedFixedRange<>()),
  VENDOR_LEGITIMATE_INTERESTS("VendorLegitimateInterests", new EncodableOptimizedFixedRange<>()),
  PUBLISHER_RESTRICTIONS("PublisherRestrictions", new EncodableArrayOfFixedIntegerRanges<>(6, 2, false)),
  PUBLISHER_PURPOSES_SEGMENT_TYPE("PublisherPurposesSegmentType", new EncodableFixedInteger<>(3, 3)),
  PUBLISHER_CONSENTS("PublisherConsents", new EncodableFixedBitfield<>(24)),
  PUBLISHER_LEGITIMATE_INTERESTS("PublisherLegitimateInterests", new EncodableFixedBitfield<>(24)),
  NUM_CUSTOM_PURPOSES("NumCustomPurposes", new EncodableFixedInteger<>(6, 0)),
  PUBLISHER_CUSTOM_CONSENTS("PublisherCustomConsents", new EncodableFlexibleBitfield<TcfEuV2Field>(segment -> (Integer) segment.getFieldValueUnsafe(TcfEuV2Field.NUM_CUSTOM_PURPOSES))),
  PUBLISHER_CUSTOM_LEGITIMATE_INTERESTS("PublisherCustomLegitimateInterests", new EncodableFlexibleBitfield<TcfEuV2Field>(segment -> (Integer) segment.getFieldValueUnsafe(TcfEuV2Field.NUM_CUSTOM_PURPOSES))),
  VENDORS_ALLOWED_SEGMENT_TYPE("VendorsAllowedSegmentType", new EncodableFixedInteger<>(3, 2)),
  VENDORS_ALLOWED("VendorsAllowed", new EncodableOptimizedFixedRange<>()),
  VENDORS_DISCLOSED_SEGMENT_TYPE("VendorsDisclosedSegmentType", new EncodableFixedInteger<>(3, 1)),
  VENDORS_DISCLOSED("VendorsDisclosed", new EncodableOptimizedFixedRange<>());

  private final String name;
  private final DataType<TcfEuV2Field, ?> type;

  TcfEuV2Field(String name, DataType<TcfEuV2Field, ?> type) {
    this.name = name;
    this.type = type;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public DataType<TcfEuV2Field, ?> getType() {
    return type;
  }

  //@formatter:off
  public static final FieldNames<TcfEuV2Field> TCFEUV2_CORE_SEGMENT_FIELD_NAMES = new FieldNames<>(
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
  public static final FieldNames<TcfEuV2Field> TCFEUV2_PUBLISHER_PURPOSES_SEGMENT_FIELD_NAMES = new FieldNames<>(
      TcfEuV2Field.PUBLISHER_PURPOSES_SEGMENT_TYPE,
      TcfEuV2Field.PUBLISHER_CONSENTS,
      TcfEuV2Field.PUBLISHER_LEGITIMATE_INTERESTS,
      TcfEuV2Field.NUM_CUSTOM_PURPOSES,
      TcfEuV2Field.PUBLISHER_CUSTOM_CONSENTS,
      TcfEuV2Field.PUBLISHER_CUSTOM_LEGITIMATE_INTERESTS
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames<TcfEuV2Field> TCFEUV2_VENDORS_ALLOWED_SEGMENT_FIELD_NAMES = new FieldNames<>(
      TcfEuV2Field.VENDORS_ALLOWED_SEGMENT_TYPE,
      TcfEuV2Field.VENDORS_ALLOWED
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames<TcfEuV2Field> TCFEUV2_VENDORS_DISCLOSED_SEGMENT_FIELD_NAMES = new FieldNames<>(
      TcfEuV2Field.VENDORS_DISCLOSED_SEGMENT_TYPE,
      TcfEuV2Field.VENDORS_DISCLOSED
  );
  //@formatter:on
}
