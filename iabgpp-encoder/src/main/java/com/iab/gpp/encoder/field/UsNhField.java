package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.section.UsNh;

public enum UsNhField implements FieldKey {
  VERSION("Version", new EncodableFixedInteger(6, UsNh.VERSION)),
  PROCESSING_NOTICE("ProcessingNotice",
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
  KNOWN_CHILD_SENSITIVE_DATA_CONSENTS("KnownChildSensitiveDataConsents", new EncodableFixedIntegerList(2, 3)
      .withValidator(nullableBooleanAsTwoBitIntegerListValidator)),
  ADDITIONAL_DATA_PROCESSING_CONSENT("AdditionalDataProcessingConsent",
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

  UsNhField(String name, DataType<?> type) {
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
