package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.section.UsFl;

public enum UsFlField implements FieldKey {
  VERSION("Version", new EncodableFixedInteger<>(6, UsFl.VERSION)),
  PROCESSING_NOTICE("ProcessingNotice",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SALE_OPT_OUT_NOTICE("SaleOptOutNotice",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  TARGETED_ADVERTISING_OPT_OUT_NOTICE("TargetedAdvertisingOptOutNotice",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SALE_OPT_OUT("SaleOptOut",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  TARGETED_ADVERTISING_OPT_OUT("TargetedAdvertisingOptOut",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SENSITIVE_DATA_PROCESSING("SensitiveDataProcessing",
      new EncodableFixedIntegerList<>(2, 8, nullableBooleanAsTwoBitIntegerListValidator)),
  KNOWN_CHILD_SENSITIVE_DATA_CONSENTS("KnownChildSensitiveDataConsents", new EncodableFixedIntegerList<>(2, 3, nullableBooleanAsTwoBitIntegerListValidator)),
  ADDITIONAL_DATA_PROCESSING_CONSENT("AdditionalDataProcessingConsent",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  MSPA_COVERED_TRANSACTION("MspaCoveredTransaction",
      new EncodableFixedInteger<>(2, 1, nonNullableBooleanAsTwoBitIntegerValidator)),
  MSPA_OPT_OUT_OPTION_MODE("MspaOptOutOptionMode",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  MSPA_SERVICE_PROVIDER_MODE("MspaServiceProviderMode",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator));

  private final String name;
  private final DataType<UsFlField, ?> type;

  UsFlField(String name, DataType<UsFlField, ?> type) {
    this.name = name;
    this.type = type;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public DataType<UsFlField, ?> getType() {
    return type;
  }

  //@formatter:off
  public static final FieldNames<UsFlField> USFL_CORE_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsFlField.VERSION,
      UsFlField.PROCESSING_NOTICE,
      UsFlField.SALE_OPT_OUT_NOTICE,
      UsFlField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsFlField.SALE_OPT_OUT,
      UsFlField.TARGETED_ADVERTISING_OPT_OUT,
      UsFlField.SENSITIVE_DATA_PROCESSING,
      UsFlField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsFlField.ADDITIONAL_DATA_PROCESSING_CONSENT,
      UsFlField.MSPA_COVERED_TRANSACTION,
      UsFlField.MSPA_OPT_OUT_OPTION_MODE,
      UsFlField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on
}
