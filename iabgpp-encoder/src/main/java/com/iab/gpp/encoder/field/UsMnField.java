package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.section.UsMn;

public enum UsMnField implements FieldKey {
  VERSION(new EncodableFixedInteger<>("Version", 6, UsMn.VERSION)),
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
      new EncodableFixedInteger<>("KnownChildSensitiveDataConsents", 2, 0, VALIDATOR_012)),
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

  private final DataType<UsMnField, ?> type;

  UsMnField(DataType<UsMnField, ?> type) {
    this.type = type;
  }

  @Override
  public DataType<UsMnField, ?> getType() {
    return type;
  }

  public static final FieldNames<UsMnField> USMN_CORE_SEGMENT_FIELD_NAMES =
      new FieldNames<>(
          UsMnField.VERSION,
          UsMnField.PROCESSING_NOTICE,
          UsMnField.SALE_OPT_OUT_NOTICE,
          UsMnField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
          UsMnField.SALE_OPT_OUT,
          UsMnField.TARGETED_ADVERTISING_OPT_OUT,
          UsMnField.SENSITIVE_DATA_PROCESSING,
          UsMnField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
          UsMnField.ADDITIONAL_DATA_PROCESSING_CONSENT,
          UsMnField.MSPA_COVERED_TRANSACTION,
          UsMnField.MSPA_OPT_OUT_OPTION_MODE,
          UsMnField.MSPA_SERVICE_PROVIDER_MODE);

  public static final FieldNames<UsMnField> USMN_GPC_SEGMENT_FIELD_NAMES =
      new FieldNames<>(UsMnField.GPC_SEGMENT_TYPE, UsMnField.GPC_SEGMENT_INCLUDED, UsMnField.GPC);
}
