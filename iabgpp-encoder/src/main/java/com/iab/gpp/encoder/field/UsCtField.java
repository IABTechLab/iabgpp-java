package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.section.UsCt;

public enum UsCtField implements FieldKey {
  VERSION("Version", new EncodableFixedInteger<>(6, UsCt.VERSION)),
  SHARING_NOTICE("SharingNotice",
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
  private final DataType<UsCtField, ?> type;

  UsCtField(String name, DataType<UsCtField, ?> type) {
    this.name = name;
    this.type = type;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public DataType<UsCtField, ?> getType() {
    return type;
  }

  //@formatter:off
  public static final FieldNames<UsCtField> USCT_CORE_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsCtField.VERSION,
      UsCtField.SHARING_NOTICE,
      UsCtField.SALE_OPT_OUT_NOTICE,
      UsCtField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsCtField.SALE_OPT_OUT,
      UsCtField.TARGETED_ADVERTISING_OPT_OUT,
      UsCtField.SENSITIVE_DATA_PROCESSING,
      UsCtField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsCtField.MSPA_COVERED_TRANSACTION,
      UsCtField.MSPA_OPT_OUT_OPTION_MODE,
      UsCtField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames<UsCtField> USCT_GPC_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsCtField.GPC_SEGMENT_TYPE,
      UsCtField.GPC_SEGMENT_INCLUDED,
      UsCtField.GPC
  );
  //@formatter:on
}
