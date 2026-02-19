package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.section.UsUt;

public enum UsUtField implements FieldKey {
  VERSION(new EncodableFixedInteger<>("Version", 6, UsUt.VERSION)),
  SHARING_NOTICE(new EncodableFixedInteger<>("SharingNotice", 2, 0, VALIDATOR_012)),
  SALE_OPT_OUT_NOTICE(new EncodableFixedInteger<>("SaleOptOutNotice", 2, 0, VALIDATOR_012)),
  TARGETED_ADVERTISING_OPT_OUT_NOTICE(
      new EncodableFixedInteger<>("TargetedAdvertisingOptOutNotice", 2, 0, VALIDATOR_012)),
  SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE(
      new EncodableFixedInteger<>("SensitiveDataProcessingOptOutNotice", 2, 0, VALIDATOR_012)),
  SALE_OPT_OUT(new EncodableFixedInteger<>("SaleOptOut", 2, 0, VALIDATOR_012)),
  TARGETED_ADVERTISING_OPT_OUT(
      new EncodableFixedInteger<>("TargetedAdvertisingOptOut", 2, 0, VALIDATOR_012)),
  SENSITIVE_DATA_PROCESSING(
      new EncodableFixedIntegerList<>("SensitiveDataProcessing", 2, 8, VALIDATOR_LIST_012)),
  KNOWN_CHILD_SENSITIVE_DATA_CONSENTS(
      new EncodableFixedInteger<>("KnownChildSensitiveDataConsents", 2, 0, VALIDATOR_012)),
  MSPA_COVERED_TRANSACTION(
      new EncodableFixedInteger<>("MspaCoveredTransaction", 2, 1, VALIDATOR_12)),
  MSPA_OPT_OUT_OPTION_MODE(
      new EncodableFixedInteger<>("MspaOptOutOptionMode", 2, 0, VALIDATOR_012)),
  MSPA_SERVICE_PROVIDER_MODE(
      new EncodableFixedInteger<>("MspaServiceProviderMode", 2, 0, VALIDATOR_012));

  private final DataType<UsUtField, ?> type;

  UsUtField(DataType<UsUtField, ?> type) {
    this.type = type;
  }

  @Override
  public DataType<UsUtField, ?> getType() {
    return type;
  }

  // @formatter:off
  public static final FieldNames<UsUtField> USUT_CORE_SEGMENT_FIELD_NAMES =
      new FieldNames<>(
          UsUtField.VERSION,
          UsUtField.SHARING_NOTICE,
          UsUtField.SALE_OPT_OUT_NOTICE,
          UsUtField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
          UsUtField.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE,
          UsUtField.SALE_OPT_OUT,
          UsUtField.TARGETED_ADVERTISING_OPT_OUT,
          UsUtField.SENSITIVE_DATA_PROCESSING,
          UsUtField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
          UsUtField.MSPA_COVERED_TRANSACTION,
          UsUtField.MSPA_OPT_OUT_OPTION_MODE,
          UsUtField.MSPA_SERVICE_PROVIDER_MODE);
  // @formatter:on
}
