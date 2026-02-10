package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.section.UsTn;

public enum UsTnField implements FieldKey {
  VERSION(new EncodableFixedInteger<>("Version", 6, UsTn.VERSION)),
  PROCESSING_NOTICE(
      new EncodableFixedInteger<>("ProcessingNotice", 2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SALE_OPT_OUT_NOTICE(
      new EncodableFixedInteger<>("SaleOptOutNotice",2, 0, nullableBooleanAsTwoBitIntegerValidator)),
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

  private final DataType<UsTnField, ?> type;

  UsTnField(DataType<UsTnField, ?> type) {
    this.type = type;
  }

  @Override
  public DataType<UsTnField, ?> getType() {
    return type;
  }

  //@formatter:off
  public static final FieldNames<UsTnField> USTN_CORE_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsTnField.VERSION,
      UsTnField.PROCESSING_NOTICE,
      UsTnField.SALE_OPT_OUT_NOTICE,
      UsTnField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsTnField.SALE_OPT_OUT,
      UsTnField.TARGETED_ADVERTISING_OPT_OUT,
      UsTnField.SENSITIVE_DATA_PROCESSING,
      UsTnField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsTnField.ADDITIONAL_DATA_PROCESSING_CONSENT,
      UsTnField.MSPA_COVERED_TRANSACTION,
      UsTnField.MSPA_OPT_OUT_OPTION_MODE,
      UsTnField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames<UsTnField> USTN_GPC_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsTnField.GPC_SEGMENT_TYPE,
      UsTnField.GPC_SEGMENT_INCLUDED,
      UsTnField.GPC
  );
  //@formatter:on
}
