package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableFibonacciIntegerRange;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.section.UsRi;

public enum UsRiField implements FieldKey {
  SECTION_ID(new EncodableFixedInteger<>("SectionID", 6, UsRi.ID)),
  VERSION(new EncodableFixedInteger<>("Version", 6, UsRi.VERSION)),
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

  private final DataType<UsRiField, ?> type;

  UsRiField(DataType<UsRiField, ?> type) {
    this.type = type;
  }

  @Override
  public DataType<UsRiField, ?> getType() {
    return type;
  }

  public static final FieldNames<UsRiField> USRI_HEADER_SEGMENT_FIELD_NAMES =
      new FieldNames<>(UsRiField.SECTION_ID, UsRiField.VERSION, UsRiField.SUB_SECTIONS);

  public static final FieldNames<UsRiField> USRI_CORE_SEGMENT_FIELD_NAMES =
      new FieldNames<>(
          UsRiField.MSPA_VERSION,
          UsRiField.MSPA_COVERED_TRANSACTION,
          UsRiField.MSPA_MODE,
          UsRiField.PROCESSING_NOTICE,
          UsRiField.SALE_OPT_OUT_NOTICE,
          UsRiField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
          UsRiField.SALE_OPT_OUT,
          UsRiField.TARGETED_ADVERTISING_OPT_OUT,
          UsRiField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
          UsRiField.ADDITIONAL_DATA_PROCESSING_CONSENT);

  public static final FieldNames<UsRiField> USRI_SENSITIVE_DATA_CONSENTS_SEGMENT_FIELD_NAMES =
      new FieldNames<>(UsRiField.SENSITIVE_DATA_PROCESSING);
}
