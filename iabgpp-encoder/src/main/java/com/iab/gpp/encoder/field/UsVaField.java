package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.section.UsVa;

public enum UsVaField implements FieldKey {
  VERSION(new EncodableFixedInteger<>("Version", 6, UsVa.VERSION)),
  SHARING_NOTICE(
      new EncodableFixedInteger<>("SharingNotice", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SALE_OPT_OUT_NOTICE(
      new EncodableFixedInteger<>("SaleOptOutNotice", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  TARGETED_ADVERTISING_OPT_OUT_NOTICE(
      new EncodableFixedInteger<>("TargetedAdvertisingOptOutNotice", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SALE_OPT_OUT(
      new EncodableFixedInteger<>("SaleOptOut", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  TARGETED_ADVERTISING_OPT_OUT(
      new EncodableFixedInteger<>("TargetedAdvertisingOptOut", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SENSITIVE_DATA_PROCESSING(
      new EncodableFixedIntegerList<>("SensitiveDataProcessing", 2, 8, nullableBooleanAsTwoBitIntegerListValidator)),
  KNOWN_CHILD_SENSITIVE_DATA_CONSENTS(
      new EncodableFixedInteger<>("KnownChildSensitiveDataConsents", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  MSPA_COVERED_TRANSACTION(
      new EncodableFixedInteger<>("MspaCoveredTransaction", 2, 1, nonNullableBooleanAsTwoBitIntegerValidator)),
  MSPA_OPT_OUT_OPTION_MODE(
      new EncodableFixedInteger<>("MspaOptOutOptionMode", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  MSPA_SERVICE_PROVIDER_MODE(
      new EncodableFixedInteger<>("MspaServiceProviderMode", 2, 0, nullableBooleanAsTwoBitIntegerValidator));

  private final DataType<UsVaField, ?> type;

  UsVaField(DataType<UsVaField, ?> type) {
    this.type = type;
  }

  @Override
  public DataType<UsVaField, ?> getType() {
    return type;
  }

  //@formatter:off
  public static final FieldNames<UsVaField> USVA_CORE_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsVaField.VERSION,
      UsVaField.SHARING_NOTICE,
      UsVaField.SALE_OPT_OUT_NOTICE,
      UsVaField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsVaField.SALE_OPT_OUT,
      UsVaField.TARGETED_ADVERTISING_OPT_OUT,
      UsVaField.SENSITIVE_DATA_PROCESSING,
      UsVaField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsVaField.MSPA_COVERED_TRANSACTION,
      UsVaField.MSPA_OPT_OUT_OPTION_MODE,
      UsVaField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on
}
