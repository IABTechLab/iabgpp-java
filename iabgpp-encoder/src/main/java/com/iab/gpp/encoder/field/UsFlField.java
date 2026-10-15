package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.section.UsFl;

public enum UsFlField implements FieldKey {
  VERSION(new EncodableFixedInteger<>("Version", 6, UsFl.VERSION)),
  PROCESSING_NOTICE(new EncodableFixedInteger<>("ProcessingNotice", 2, 0, VALIDATOR_012)),
  SALE_OPT_OUT_NOTICE(new EncodableFixedInteger<>("SaleOptOutNotice", 2, 0, VALIDATOR_012)),
  TARGETED_ADVERTISING_OPT_OUT_NOTICE(
      new EncodableFixedInteger<>("TargetedAdvertisingOptOutNotice", 2, 0, VALIDATOR_012)),
  SALE_OPT_OUT(new EncodableFixedInteger<>("SaleOptOut", 2, 0, VALIDATOR_012)),
  TARGETED_ADVERTISING_OPT_OUT(
      new EncodableFixedInteger<>("TargetedAdvertisingOptOut", 2, 0, VALIDATOR_012)),
  SENSITIVE_DATA_PROCESSING(
      new EncodableFixedIntegerList<>("SensitiveDataProcessing", 2, 8, VALIDATOR_LIST_012)),
  KNOWN_CHILD_SENSITIVE_DATA_CONSENTS(
      new EncodableFixedIntegerList<>("KnownChildSensitiveDataConsents", 2, 3, VALIDATOR_LIST_012)),
  ADDITIONAL_DATA_PROCESSING_CONSENT(
      new EncodableFixedInteger<>("AdditionalDataProcessingConsent", 2, 0, VALIDATOR_012)),
  MSPA_COVERED_TRANSACTION(
      new EncodableFixedInteger<>("MspaCoveredTransaction", 2, 1, VALIDATOR_12)),
  MSPA_OPT_OUT_OPTION_MODE(
      new EncodableFixedInteger<>("MspaOptOutOptionMode", 2, 0, VALIDATOR_012)),
  MSPA_SERVICE_PROVIDER_MODE(
      new EncodableFixedInteger<>("MspaServiceProviderMode", 2, 0, VALIDATOR_012));

  private final DataType<UsFlField, ?> type;

  UsFlField(DataType<UsFlField, ?> type) {
    this.type = type;
  }

  @Override
  public DataType<UsFlField, ?> getType() {
    return type;
  }

  public static final FieldNames<UsFlField> USFL_CORE_SEGMENT_FIELD_NAMES =
      new FieldNames<>(
          UsFlField.VERSION,
          UsFlField.PROCESSING_NOTICE,
          UsFlField.SALE_OPT_OUT_NOTICE,
          UsFlField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
          UsFlField.SALE_OPT_OUT,
          UsFlField.TARGETED_ADVERTISING_OPT_OUT,
          UsFlField.SENSITIVE_DATA_PROCESSING,
          UsFlField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
          UsFlField.ADDITIONAL_DATA_PROCESSING_CONSENT,
          UsFlField.MSPA_COVERED_TRANSACTION,
          UsFlField.MSPA_OPT_OUT_OPTION_MODE,
          UsFlField.MSPA_SERVICE_PROVIDER_MODE);
}
