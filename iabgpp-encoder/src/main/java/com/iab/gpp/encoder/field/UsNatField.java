package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.section.UsNat;

public enum UsNatField implements FieldKey {
  VERSION(new EncodableFixedInteger<>("Version", 6, UsNat.VERSION)),
  SHARING_NOTICE(
      new EncodableFixedInteger<>("SharingNotice", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SALE_OPT_OUT_NOTICE(
      new EncodableFixedInteger<>("SaleOptOutNotice", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SHARING_OPT_OUT_NOTICE(
      new EncodableFixedInteger<>("SharingOptOutNotice", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  TARGETED_ADVERTISING_OPT_OUT_NOTICE(
      new EncodableFixedInteger<>("TargetedAdvertisingOptOutNotice", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE(
      new EncodableFixedInteger<>("SensitiveDataProcessingOptOutNotice",2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SENSITIVE_DATA_LIMIT_USE_NOTICE(
      new EncodableFixedInteger<>("SensitiveDataLimitUseNotice", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SALE_OPT_OUT(
      new EncodableFixedInteger<>("SaleOptOut", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SHARING_OPT_OUT(
      new EncodableFixedInteger<>("SharingOptOut", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  TARGETED_ADVERTISING_OPT_OUT(
      new EncodableFixedInteger<>("TargetedAdvertisingOptOut", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SENSITIVE_DATA_PROCESSING(
      new EncodableFixedIntegerList<>("SensitiveDataProcessing", 2, 16, nullableBooleanAsTwoBitIntegerListValidator)),
  KNOWN_CHILD_SENSITIVE_DATA_CONSENTS(new EncodableFixedIntegerList<>("KnownChildSensitiveDataConsents", 2, 3, nullableBooleanAsTwoBitIntegerListValidator)),
  PERSONAL_DATA_CONSENTS(
      new EncodableFixedInteger<>("PersonalDataConsents", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  MSPA_COVERED_TRANSACTION(
      new EncodableFixedInteger<>("MspaCoveredTransaction", 2, 1, nonNullableBooleanAsTwoBitIntegerValidator)),
  MSPA_OPT_OUT_OPTION_MODE(
      new EncodableFixedInteger<>("MspaOptOutOptionMode", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  MSPA_SERVICE_PROVIDER_MODE(
      new EncodableFixedInteger<>("MspaServiceProviderMode", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),

  GPC_SEGMENT_TYPE(new EncodableFixedInteger<>("GpcSegmentType", 2, 1)),
  GPC_SEGMENT_INCLUDED(new UnencodableBoolean<>("GpcSegmentIncluded", true)),
  GPC(new EncodableBoolean<>("Gpc", false));

  private final DataType<UsNatField, ?> type;

  UsNatField(DataType<UsNatField, ?> type) {
    this.type = type;
  }

  @Override
  public DataType<UsNatField, ?> getType() {
    return type;
  }

  //@formatter:off
  public static final FieldNames<UsNatField> USNAT_CORE_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsNatField.VERSION,
      UsNatField.SHARING_NOTICE,
      UsNatField.SALE_OPT_OUT_NOTICE,
      UsNatField.SHARING_OPT_OUT_NOTICE,
      UsNatField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsNatField.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE,
      UsNatField.SENSITIVE_DATA_LIMIT_USE_NOTICE,
      UsNatField.SALE_OPT_OUT,
      UsNatField.SHARING_OPT_OUT,
      UsNatField.TARGETED_ADVERTISING_OPT_OUT,
      UsNatField.SENSITIVE_DATA_PROCESSING,
      UsNatField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsNatField.PERSONAL_DATA_CONSENTS,
      UsNatField.MSPA_COVERED_TRANSACTION,
      UsNatField.MSPA_OPT_OUT_OPTION_MODE,
      UsNatField.MSPA_SERVICE_PROVIDER_MODE
   );
  //@formatter:on

  //@formatter:off
  public static final FieldNames<UsNatField> USNAT_GPC_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsNatField.GPC_SEGMENT_TYPE,
      UsNatField.GPC_SEGMENT_INCLUDED,
      UsNatField.GPC
  );
  //@formatter:on
}
