package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.section.UsIa;

public enum UsIaField implements FieldKey {
  VERSION("Version", new EncodableFixedInteger<>(6, UsIa.VERSION)),
  PROCESSING_NOTICE("ProcessingNotice",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SALE_OPT_OUT_NOTICE("SaleOptOutNotice",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  TARGETED_ADVERTISING_OPT_OUT_NOTICE("TargetedAdvertisingOptOutNotice",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SENSITIVE_DATA_OPT_OUT_NOTICE("SensitiveDataOptOutNotice",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SALE_OPT_OUT("SaleOptOut",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  TARGETED_ADVERTISING_OPT_OUT("TargetedAdvertisingOptOut",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SENSITIVE_DATA_PROCESSING("SensitiveDataProcessing",
      new EncodableFixedIntegerList<>(2, 8, nullableBooleanAsTwoBitIntegerListValidator)),
  KNOWN_CHILD_SENSITIVE_DATA_CONSENTS("KnownChildSensitiveDataConsents",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  MSPA_COVERED_TRANSACTION("MspaCoveredTransaction",
      new EncodableFixedInteger<>(2, 1, nonNullableBooleanAsTwoBitIntegerValidator)),
  MSPA_OPT_OUT_OPTION_MODE("MspaOptOutOptionMode",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  MSPA_SERVICE_PROVIDER_MODE("MspaServiceProviderMode",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator)),

  GPC_SEGMENT_TYPE("GpcSegmentType", new EncodableFixedInteger<>(2, 1)),
  GPC_SEGMENT_INCLUDED("GpcSegmentIncluded", new UnencodableBoolean<>(true)),
  GPC("Gpc", new EncodableBoolean<>(false));

  private final String name;
  private final DataType<UsIaField, ?> type;

  UsIaField(String name, DataType<UsIaField, ?> type) {
    this.name = name;
    this.type = type;
  }

  @Override
  public String getName() {
    return name;
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
