package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.section.UsNe;

public enum UsNeField implements FieldKey {
  VERSION(new EncodableFixedInteger<>("Version", 6, UsNe.VERSION)),
  PROCESSING_NOTICE(
      new EncodableFixedInteger<>("ProcessingNotice", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SALE_OPT_OUT_NOTICE(
      new EncodableFixedInteger<>("SaleOptOutNotice", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  TARGETED_ADVERTISING_OPT_OUT_NOTICE(
      new EncodableFixedInteger<>("TargetedAdvertisingOptOutNotice", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SALE_OPT_OUT(
      new EncodableFixedInteger<>("SaleOptOut", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  TARGETED_ADVERTISING_OPT_OUT(
      new EncodableFixedInteger<>("TargetedAdvertisingOptOut", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SENSITIVE_DATA_PROCESSING(
      new EncodableFixedIntegerList<>("SensitiveDataProcessing", 2, 8, nullableBooleanAsTwoBitIntegerListValidator)),
  KNOWN_CHILD_SENSITIVE_DATA_CONSENTS(
      new EncodableFixedInteger<>("KnownChildSensitiveDataConsents", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  ADDITIONAL_DATA_PROCESSING_CONSENT(
      new EncodableFixedInteger<>("AdditionalDataProcessingConsent", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  MSPA_COVERED_TRANSACTION(
      new EncodableFixedInteger<>("MspaCoveredTransaction", 2, 1, nonNullableBooleanAsTwoBitIntegerValidator)),
  MSPA_OPT_OUT_OPTION_MODE(
      new EncodableFixedInteger<>("MspaOptOutOptionMode", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  MSPA_SERVICE_PROVIDER_MODE(
      new EncodableFixedInteger<>("MspaServiceProviderMode", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),

  GPC_SEGMENT_TYPE(new EncodableFixedInteger<>("GpcSegmentType", 2, 1)),
  GPC_SEGMENT_INCLUDED(new UnencodableBoolean<>("GpcSegmentIncluded", true)),
  GPC(new EncodableBoolean<>("Gpc", false));

  private final DataType<UsNeField, ?> type;

  UsNeField(DataType<UsNeField, ?> type) {
    this.type = type;
  }

  @Override
  public DataType<UsNeField, ?> getType() {
    return type;
  }

  //@formatter:off
  public static final FieldNames<UsNeField> USNE_CORE_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsNeField.VERSION,
      UsNeField.PROCESSING_NOTICE,
      UsNeField.SALE_OPT_OUT_NOTICE,
      UsNeField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsNeField.SALE_OPT_OUT,
      UsNeField.TARGETED_ADVERTISING_OPT_OUT,
      UsNeField.SENSITIVE_DATA_PROCESSING,
      UsNeField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsNeField.ADDITIONAL_DATA_PROCESSING_CONSENT,
      UsNeField.MSPA_COVERED_TRANSACTION,
      UsNeField.MSPA_OPT_OUT_OPTION_MODE,
      UsNeField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames<UsNeField> USNE_GPC_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsNeField.GPC_SEGMENT_TYPE,
      UsNeField.GPC_SEGMENT_INCLUDED,
      UsNeField.GPC
  );
  //@formatter:on
}
