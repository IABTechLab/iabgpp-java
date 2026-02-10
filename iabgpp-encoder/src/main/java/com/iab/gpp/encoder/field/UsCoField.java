package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.section.UsCo;

public enum UsCoField implements FieldKey {
  VERSION("Version", new EncodableFixedInteger(6, UsCo.VERSION)),
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
  SENSITIVE_DATA_PROCESSING("SensitiveDataProcessing", new EncodableFixedIntegerList(2, 7)
      .withValidator(nullableBooleanAsTwoBitIntegerListValidator)),
  KNOWN_CHILD_SENSITIVE_DATA_CONSENTS("KnownChildSensitiveDataConsents",
      new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator)),
  MSPA_COVERED_TRANSACTION("MspaCoveredTransaction",
      new EncodableFixedInteger(2, 1).withValidator(nonNullableBooleanAsTwoBitIntegerValidator)),
  MSPA_OPT_OUT_OPTION_MODE("MspaOptOutOptionMode",
      new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator)),
  MSPA_SERVICE_PROVIDER_MODE("MspaServiceProviderMode",
      new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator)),

  GPC_SEGMENT_TYPE("GpcSegmentType", new EncodableFixedInteger(2, 1)),
  GPC_SEGMENT_INCLUDED("GpcSegmentIncluded", new UnencodableBoolean(true)),
  GPC("Gpc", new EncodableBoolean(false));

  private final String name;
  private final DataType<?> type;

  UsCoField(String name, DataType<?> type) {
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
  public static final FieldNames<UsCoField> USCO_CORE_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsCoField.VERSION,
      UsCoField.SHARING_NOTICE,
      UsCoField.SALE_OPT_OUT_NOTICE,
      UsCoField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsCoField.SALE_OPT_OUT,
      UsCoField.TARGETED_ADVERTISING_OPT_OUT,
      UsCoField.SENSITIVE_DATA_PROCESSING,
      UsCoField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsCoField.MSPA_COVERED_TRANSACTION,
      UsCoField.MSPA_OPT_OUT_OPTION_MODE,
      UsCoField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames<UsCoField> USCO_GPC_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsCoField.GPC_SEGMENT_TYPE,
      UsCoField.GPC_SEGMENT_INCLUDED,
      UsCoField.GPC
  );
  //@formatter:on
}
