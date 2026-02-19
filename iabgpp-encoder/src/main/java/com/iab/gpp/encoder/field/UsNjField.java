package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.section.UsNj;

public enum UsNjField implements FieldKey {
  VERSION(new EncodableFixedInteger<>("Version", 6, UsNj.VERSION)),
  PROCESSING_NOTICE(new EncodableFixedInteger<>("ProcessingNotice", 2, 0, VALIDATOR_012)),
  SALE_OPT_OUT_NOTICE(new EncodableFixedInteger<>("SaleOptOutNotice", 2, 0, VALIDATOR_012)),
  TARGETED_ADVERTISING_OPT_OUT_NOTICE(
      new EncodableFixedInteger<>("TargetedAdvertisingOptOutNotice", 2, 0, VALIDATOR_012)),
  SALE_OPT_OUT(new EncodableFixedInteger<>("SaleOptOut", 2, 0, VALIDATOR_012)),
  TARGETED_ADVERTISING_OPT_OUT(
      new EncodableFixedInteger<>("TargetedAdvertisingOptOut", 2, 0, VALIDATOR_012)),
  SENSITIVE_DATA_PROCESSING(
      new EncodableFixedIntegerList<>("SensitiveDataProcessing", 2, 10, VALIDATOR_LIST_012)),
  KNOWN_CHILD_SENSITIVE_DATA_CONSENTS(
      new EncodableFixedIntegerList<>("KnownChildSensitiveDataConsents", 2, 5, VALIDATOR_LIST_012)),
  ADDITIONAL_DATA_PROCESSING_CONSENT(
      new EncodableFixedInteger<>("AdditionalDataProcessingConsent", 2, 0, VALIDATOR_012)),
  MSPA_COVERED_TRANSACTION(
      new EncodableFixedInteger<>("MspaCoveredTransaction", 2, 1, VALIDATOR_12)),
  MSPA_OPT_OUT_OPTION_MODE(
      new EncodableFixedInteger<>("MspaOptOutOptionMode", 2, 0, VALIDATOR_012)),
  MSPA_SERVICE_PROVIDER_MODE(
      new EncodableFixedInteger<>("MspaServiceProviderMode", 2, 0, VALIDATOR_012)),

  GPC_SEGMENT_TYPE(new EncodableFixedInteger<>("GpcSegmentType", 2, 1)),
  GPC_SEGMENT_INCLUDED(new UnencodableBoolean<>("GpcSegmentIncluded", true)),
  GPC(new EncodableBoolean<>("Gpc", false));

  private final DataType<UsNjField, ?> type;

  UsNjField(DataType<UsNjField, ?> type) {
    this.type = type;
  }

  @Override
  public DataType<UsNjField, ?> getType() {
    return type;
  }

  // @formatter:off
  public static final FieldNames<UsNjField> USNJ_CORE_SEGMENT_FIELD_NAMES =
      new FieldNames<>(
          UsNjField.VERSION,
          UsNjField.PROCESSING_NOTICE,
          UsNjField.SALE_OPT_OUT_NOTICE,
          UsNjField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
          UsNjField.SALE_OPT_OUT,
          UsNjField.TARGETED_ADVERTISING_OPT_OUT,
          UsNjField.SENSITIVE_DATA_PROCESSING,
          UsNjField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
          UsNjField.ADDITIONAL_DATA_PROCESSING_CONSENT,
          UsNjField.MSPA_COVERED_TRANSACTION,
          UsNjField.MSPA_OPT_OUT_OPTION_MODE,
          UsNjField.MSPA_SERVICE_PROVIDER_MODE);
  // @formatter:on

  // @formatter:off
  public static final FieldNames<UsNjField> USNJ_GPC_SEGMENT_FIELD_NAMES =
      new FieldNames<>(UsNjField.GPC_SEGMENT_TYPE, UsNjField.GPC_SEGMENT_INCLUDED, UsNjField.GPC);
  // @formatter:on
}
