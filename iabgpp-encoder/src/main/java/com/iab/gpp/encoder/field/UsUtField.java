package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.section.UsUt;

public enum UsUtField implements FieldKey {
  VERSION("Version", new EncodableFixedInteger(6, UsUt.VERSION)),
  SHARING_NOTICE("SharingNotice",
      new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator)),
  SALE_OPT_OUT_NOTICE("SaleOptOutNotice",
      new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator)),
  TARGETED_ADVERTISING_OPT_OUT_NOTICE("TargetedAdvertisingOptOutNotice",
      new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator)),
  SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE("SensitiveDataProcessingOptOutNotice",
      new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator)),
  SALE_OPT_OUT("SaleOptOut",
      new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator)),
  TARGETED_ADVERTISING_OPT_OUT("TargetedAdvertisingOptOut",
      new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator)),
  SENSITIVE_DATA_PROCESSING("SensitiveDataProcessing",
      new EncodableFixedIntegerList(2, 8)
      .withValidator(nullableBooleanAsTwoBitIntegerListValidator)),
  KNOWN_CHILD_SENSITIVE_DATA_CONSENTS("KnownChildSensitiveDataConsents",
      new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator)),
  MSPA_COVERED_TRANSACTION("MspaCoveredTransaction",
      new EncodableFixedInteger(2, 1).withValidator(nonNullableBooleanAsTwoBitIntegerValidator)),
  MSPA_OPT_OUT_OPTION_MODE("MspaOptOutOptionMode",
      new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator)),
  MSPA_SERVICE_PROVIDER_MODE("MspaServiceProviderMode",
      new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));

  private final String name;
  private final DataType<?> type;

  UsUtField(String name, DataType<?> type) {
    this.name = name;
    this.type = type;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public DataType<?> getType() {
    return type;
  }

  //@formatter:off
  public static final FieldNames<UsUtField> USUT_CORE_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsUtField.VERSION,
      UsUtField.SHARING_NOTICE,
      UsUtField.SALE_OPT_OUT_NOTICE,
      UsUtField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsUtField.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE,
      UsUtField.SALE_OPT_OUT,
      UsUtField.TARGETED_ADVERTISING_OPT_OUT,
      UsUtField.SENSITIVE_DATA_PROCESSING,
      UsUtField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsUtField.MSPA_COVERED_TRANSACTION,
      UsUtField.MSPA_OPT_OUT_OPTION_MODE,
      UsUtField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on
}
