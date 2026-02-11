package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.section.UsIa;

public enum UsIaField implements FieldKey {
  VERSION(new EncodableFixedInteger<>("Version", 6, UsIa.VERSION)),
  PROCESSING_NOTICE(
      new EncodableFixedInteger<>("ProcessingNotice", 2, 0, VALIDATOR_012)),
  SALE_OPT_OUT_NOTICE(
      new EncodableFixedInteger<>("SaleOptOutNotice", 2, 0, VALIDATOR_012)),
  TARGETED_ADVERTISING_OPT_OUT_NOTICE(
      new EncodableFixedInteger<>("TargetedAdvertisingOptOutNotice", 2, 0, VALIDATOR_012)),
  SENSITIVE_DATA_OPT_OUT_NOTICE(
      new EncodableFixedInteger<>("SensitiveDataOptOutNotice", 2, 0, VALIDATOR_012)),
  SALE_OPT_OUT(
      new EncodableFixedInteger<>("SaleOptOut", 2, 0, VALIDATOR_012)),
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
      new EncodableFixedInteger<>("MspaServiceProviderMode", 2, 0, VALIDATOR_012)),

  GPC_SEGMENT_TYPE(new EncodableFixedInteger<>("GpcSegmentType", 2, 1)),
  GPC_SEGMENT_INCLUDED(new UnencodableBoolean<>("GpcSegmentIncluded", true)),
  GPC(new EncodableBoolean<>("Gpc", false));

  private final DataType<UsIaField, ?> type;

  UsIaField(DataType<UsIaField, ?> type) {
    this.type = type;
  }

  @Override
  public DataType<UsIaField, ?> getType() {
    return type;
  }

  //@formatter:off
  public static final FieldNames<UsIaField> USIA_CORE_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsIaField.VERSION,
      UsIaField.PROCESSING_NOTICE,
      UsIaField.SALE_OPT_OUT_NOTICE,
      UsIaField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsIaField.SENSITIVE_DATA_OPT_OUT_NOTICE,
      UsIaField.SALE_OPT_OUT,
      UsIaField.TARGETED_ADVERTISING_OPT_OUT,
      UsIaField.SENSITIVE_DATA_PROCESSING,
      UsIaField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsIaField.MSPA_COVERED_TRANSACTION,
      UsIaField.MSPA_OPT_OUT_OPTION_MODE,
      UsIaField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames<UsIaField> USIA_GPC_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsIaField.GPC_SEGMENT_TYPE,
      UsIaField.GPC_SEGMENT_INCLUDED,
      UsIaField.GPC
  );
  //@formatter:on
}
