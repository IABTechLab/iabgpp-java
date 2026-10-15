package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableFibonacciIntegerRange;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.section.UsKy;

public enum UsKyField implements FieldKey {
  SECTION_ID(new EncodableFixedInteger<>("SectionID", 6, UsKy.ID)),
  VERSION(new EncodableFixedInteger<>("Version", 6, UsKy.VERSION)),
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

  private final DataType<UsKyField, ?> type;

  UsKyField(DataType<UsKyField, ?> type) {
    this.type = type;
  }

  @Override
  public DataType<UsKyField, ?> getType() {
    return type;
  }

  public static final FieldNames<UsKyField> USKY_HEADER_SEGMENT_FIELD_NAMES =
      new FieldNames<>(UsKyField.SECTION_ID, UsKyField.VERSION, UsKyField.SUB_SECTIONS);

  public static final FieldNames<UsKyField> USKY_CORE_SEGMENT_FIELD_NAMES =
      new FieldNames<>(
          UsKyField.MSPA_VERSION,
          UsKyField.MSPA_COVERED_TRANSACTION,
          UsKyField.MSPA_MODE,
          UsKyField.PROCESSING_NOTICE,
          UsKyField.SALE_OPT_OUT_NOTICE,
          UsKyField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
          UsKyField.SALE_OPT_OUT,
          UsKyField.TARGETED_ADVERTISING_OPT_OUT,
          UsKyField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
          UsKyField.ADDITIONAL_DATA_PROCESSING_CONSENT);

  public static final FieldNames<UsKyField> USKY_SENSITIVE_DATA_CONSENTS_SEGMENT_FIELD_NAMES =
      new FieldNames<>(UsKyField.SENSITIVE_DATA_PROCESSING);
}
