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
  VERSION("Version", new EncodableFixedInteger(6, TcfCaV1.VERSION)),
  CREATED("Created", new EncodableDatetime()),
  LAST_UPDATED("LastUpdated", new EncodableDatetime()),
  CMP_ID("CmpId", new EncodableFixedInteger(12, 0)),
  CMP_VERSION("CmpVersion", new EncodableFixedInteger(12, 0)),
  CONSENT_SCREEN("ConsentScreen", new EncodableFixedInteger(6, 0)),
  CONSENT_LANGUAGE("ConsentLanguage", new EncodableFixedString(2, "EN")),
  VENDOR_LIST_VERSION("VendorListVersion", new EncodableFixedInteger(12, 0)),
  TCF_POLICY_VERSION("TcfPolicyVersion", new EncodableFixedInteger(6, 2)),
  USE_NON_STANDARD_STACKS("UseNonStandardStacks", new EncodableBoolean(false)),
  SPECIAL_FEATURE_EXPRESS_CONSENT("SpecialFeatureExpressConsent", new EncodableFixedBitfield(12)),
  PURPOSES_EXPRESS_CONSENT("PurposesExpressConsent", new EncodableFixedBitfield(24)),
  PURPOSES_IMPLIED_CONSENT("PurposesImpliedConsent", new EncodableFixedBitfield(24)),
  VENDOR_EXPRESS_CONSENT("VendorExpressConsent", new EncodableOptimizedFixedRange()),
  VENDOR_IMPLIED_CONSENT("VendorImpliedConsent", new EncodableOptimizedFixedRange()),
  PUB_RESTRICTIONS("PubRestrictions", new EncodableArrayOfFixedIntegerRanges(6, 2, false)),

  PUB_PURPOSES_SEGMENT_TYPE("PubPurposesSegmentType", new EncodableFixedInteger(3, 3)),
  PUB_PURPOSES_EXPRESS_CONSENT("PubPurposesExpressConsent", new EncodableFixedBitfield(24)),
  PUB_PURPOSES_IMPLIED_CONSENT("PubPurposesImpliedConsent", new EncodableFixedBitfield(24)),
  NUM_CUSTOM_PURPOSES("NumCustomPurposes", new EncodableFixedInteger(6, 0)),
  CUSTOM_PURPOSES_EXPRESS_CONSENT("CustomPurposesExpressConsent", new EncodableFlexibleBitfield<TcfCaV1Field>(segment -> (Integer) segment.getFieldValue(TcfCaV1Field.NUM_CUSTOM_PURPOSES))),
  CUSTOM_PURPOSES_IMPLIED_CONSENT("CustomPurposesImpliedConsent", new EncodableFlexibleBitfield<TcfCaV1Field>(segment -> (Integer) segment.getFieldValue(TcfCaV1Field.NUM_CUSTOM_PURPOSES))),

  DISCLOSED_VENDORS_SEGMENT_TYPE("DisclosedVendorsSegmentType", new EncodableFixedInteger(3, 1)),
  DISCLOSED_VENDORS("DisclosedVendors", new EncodableOptimizedFixedRange());

  private final String name;
  private final DataType<?> type;

  TcfCaV1Field(String name, DataType<?> type) {
    this.name = name;
    this.type = type;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public DataType<?> getType() {
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
