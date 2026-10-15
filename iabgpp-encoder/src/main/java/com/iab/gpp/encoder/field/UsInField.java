package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableFibonacciIntegerRange;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.section.UsIn;

public enum UsInField implements FieldKey {
  SECTION_ID(new EncodableFixedInteger<>("SectionID", 6, UsIn.ID)),
  VERSION(new EncodableFixedInteger<>("Version", 6, UsIn.VERSION)),
  SUB_SECTIONS(new EncodableFibonacciIntegerRange<>("SubSections")),

  MSPA_VERSION(new EncodableFixedInteger<>("MspaVersion", 6, 0)),
  MSPA_COVERED_TRANSACTION(
      new EncodableFixedInteger<>("MspaCoveredTransaction", 2, 1, VALIDATOR_12)),
  MSPA_MODE(new EncodableFixedInteger<>("MspaMode", 2, 0, VALIDATOR_012)),
  PROCESSING_NOTICE(new EncodableFixedInteger<>("ProcessingNotice", 2, 0, VALIDATOR_012)),
  SALE_OPT_OUT_NOTICE(new EncodableFixedInteger<>("SaleOptOutNotice", 2, 0, VALIDATOR_012)),
  TARGETED_ADVERTISING_OPT_OUT_NOTICE(
      new EncodableFixedInteger<>("TargetedAdvertisingOptOutNotice", 2, 0, VALIDATOR_012)),
  SALE_OPT_OUT(new EncodableFixedInteger<>("SaleOptOut", 2, 0, VALIDATOR_012)),
  TARGETED_ADVERTISING_OPT_OUT(
      new EncodableFixedInteger<>("TargetedAdvertisingOptOut", 2, 0, VALIDATOR_012)),
  KNOWN_CHILD_SENSITIVE_DATA_CONSENTS(
      new EncodableFixedInteger<>("KnownChildSensitiveDataConsents", 2, 0, VALIDATOR_012)),
  ADDITIONAL_DATA_PROCESSING_CONSENT(
      new EncodableFixedInteger<>("AdditionalDataProcessingConsent", 2, 0, VALIDATOR_012)),

  SENSITIVE_DATA_PROCESSING(
      new EncodableFixedIntegerList<>("SensitiveDataProcessing", 2, 8, VALIDATOR_LIST_012));

  private final DataType<UsInField, ?> type;

  UsInField(DataType<UsInField, ?> type) {
    this.type = type;
  }

  @Override
  public DataType<UsInField, ?> getType() {
    return type;
  }

  public static final FieldNames<UsInField> USIN_HEADER_SEGMENT_FIELD_NAMES =
      new FieldNames<>(UsInField.SECTION_ID, UsInField.VERSION, UsInField.SUB_SECTIONS);

  public static final FieldNames<UsInField> USIN_CORE_SEGMENT_FIELD_NAMES =
      new FieldNames<>(
          UsInField.MSPA_VERSION,
          UsInField.MSPA_COVERED_TRANSACTION,
          UsInField.MSPA_MODE,
          UsInField.PROCESSING_NOTICE,
          UsInField.SALE_OPT_OUT_NOTICE,
          UsInField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
          UsInField.SALE_OPT_OUT,
          UsInField.TARGETED_ADVERTISING_OPT_OUT,
          UsInField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
          UsInField.ADDITIONAL_DATA_PROCESSING_CONSENT);

  public static final FieldNames<UsInField> USIN_SENSITIVE_DATA_CONSENTS_SEGMENT_FIELD_NAMES =
      new FieldNames<>(UsInField.SENSITIVE_DATA_PROCESSING);
}
