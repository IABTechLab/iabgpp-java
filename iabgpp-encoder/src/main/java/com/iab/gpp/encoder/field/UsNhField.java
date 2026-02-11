package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.section.UsNh;

public enum UsNhField implements FieldKey {
  VERSION(new EncodableFixedInteger<>("Version", 6, UsNh.VERSION)),
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
      new EncodableFixedIntegerList<>("SensitiveDataProcessing", 2, 8, VALIDATOR_LIST_012)),
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

  private final DataType<UsNhField, ?> type;

  UsNhField(DataType<UsNhField, ?> type) {
    this.type = type;
  }

  @Override
  public DataType<UsNhField, ?> getType() {
    return type;
  }

  //@formatter:off
  public static final FieldNames<UsNhField> USNH_CORE_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsNhField.VERSION,
      UsNhField.PROCESSING_NOTICE,
      UsNhField.SALE_OPT_OUT_NOTICE,
      UsNhField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsNhField.SALE_OPT_OUT,
      UsNhField.TARGETED_ADVERTISING_OPT_OUT,
      UsNhField.SENSITIVE_DATA_PROCESSING,
      UsNhField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsNhField.ADDITIONAL_DATA_PROCESSING_CONSENT,
      UsNhField.MSPA_COVERED_TRANSACTION,
      UsNhField.MSPA_OPT_OUT_OPTION_MODE,
      UsNhField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames<UsNhField> USNH_GPC_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsNhField.GPC_SEGMENT_TYPE,
      UsNhField.GPC_SEGMENT_INCLUDED,
      UsNhField.GPC
  );
  //@formatter:on
}
