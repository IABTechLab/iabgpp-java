package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.section.UsOr;

public enum UsOrField implements FieldKey {
  VERSION(new EncodableFixedInteger<>("Version", 6, UsOr.VERSION)),
  PROCESSING_NOTICE(
      new EncodableFixedInteger<>("ProcessingNotice", 2, 0, VALIDATOR_012)),
  SALE_OPT_OUT_NOTICE(
      new EncodableFixedInteger<>("SaleOptOutNotice", 2, 0, VALIDATOR_012)),
  TARGETED_ADVERTISING_OPT_OUT_NOTICE(
      new EncodableFixedInteger<>("TargetedAdvertisingOptOutNotice", 2, 0, VALIDATOR_012)),
  SALE_OPT_OUT(
      new EncodableFixedInteger<>("SaleOptOut", 2, 0, VALIDATOR_012)),
  TARGETED_ADVERTISING_OPT_OUT(
      new EncodableFixedInteger<>("TargetedAdvertisingOptOut", 2, 0, VALIDATOR_012)),
  SENSITIVE_DATA_PROCESSING(
      new EncodableFixedIntegerList<>("SensitiveDataProcessing", 2, 11, VALIDATOR_LIST_012)),
  KNOWN_CHILD_SENSITIVE_DATA_CONSENTS(new EncodableFixedIntegerList<>("KnownChildSensitiveDataConsents", 2, 3, VALIDATOR_LIST_012)),
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

  private final DataType<UsOrField, ?> type;

  UsOrField(DataType<UsOrField, ?> type) {
    this.type = type;
  }

  @Override
  public DataType<UsOrField, ?> getType() {
    return type;
  }

  //@formatter:off
  public static final FieldNames<UsOrField> USOR_CORE_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsOrField.VERSION,
      UsOrField.PROCESSING_NOTICE,
      UsOrField.SALE_OPT_OUT_NOTICE,
      UsOrField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsOrField.SALE_OPT_OUT,
      UsOrField.TARGETED_ADVERTISING_OPT_OUT,
      UsOrField.SENSITIVE_DATA_PROCESSING,
      UsOrField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsOrField.ADDITIONAL_DATA_PROCESSING_CONSENT,
      UsOrField.MSPA_COVERED_TRANSACTION,
      UsOrField.MSPA_OPT_OUT_OPTION_MODE,
      UsOrField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames<UsOrField> USOR_GPC_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsOrField.GPC_SEGMENT_TYPE,
      UsOrField.GPC_SEGMENT_INCLUDED,
      UsOrField.GPC
  );
  //@formatter:on
}
