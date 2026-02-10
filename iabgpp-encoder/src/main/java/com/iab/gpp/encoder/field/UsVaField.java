package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.section.UsVa;

public enum UsVaField implements FieldKey {
  VERSION("Version", new EncodableFixedInteger(6, UsVa.VERSION)),
  SHARING_NOTICE("SharingNotice",
      new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator)),
  SALE_OPT_OUT_NOTICE("SaleOptOutNotice",
      new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator)),
  TARGETED_ADVERTISING_OPT_OUT_NOTICE("TargetedAdvertisingOptOutNotice",
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

  UsVaField(String name, DataType<?> type) {
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
  public static final FieldNames<UsVaField> USVA_CORE_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsVaField.VERSION,
      UsVaField.SHARING_NOTICE,
      UsVaField.SALE_OPT_OUT_NOTICE,
      UsVaField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsVaField.SALE_OPT_OUT,
      UsVaField.TARGETED_ADVERTISING_OPT_OUT,
      UsVaField.SENSITIVE_DATA_PROCESSING,
      UsVaField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsVaField.MSPA_COVERED_TRANSACTION,
      UsVaField.MSPA_OPT_OUT_OPTION_MODE,
      UsVaField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on
}
