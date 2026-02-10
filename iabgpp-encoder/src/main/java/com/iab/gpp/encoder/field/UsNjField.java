package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.section.UsNj;

public enum UsNjField implements FieldKey {
  VERSION("Version", new EncodableFixedInteger<>(6, UsNj.VERSION)),
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
      new EncodableFixedIntegerList<>(2, 10, nullableBooleanAsTwoBitIntegerListValidator)),
  KNOWN_CHILD_SENSITIVE_DATA_CONSENTS("KnownChildSensitiveDataConsents", new EncodableFixedIntegerList<>(2, 5, nullableBooleanAsTwoBitIntegerListValidator)),
  ADDITIONAL_DATA_PROCESSING_CONSENT("AdditionalDataProcessingConsent",
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
  private final DataType<UsNjField, ?> type;

  UsNjField(String name, DataType<UsNjField, ?> type) {
    this.name = name;
    this.type = type;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public DataType<UsNjField, ?> getType() {
    return type;
  }

  //@formatter:off
  public static final FieldNames<UsNjField> USNJ_CORE_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsNjField.VERSION,
      UsNjField.PROCESSING_NOTICE,
      UsNjField.SALE_OPT_OUT_NOTICE,
      UsNjField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsNjField.SALE_OPT_OUT,
      UsNjField.TARGETED_ADVERTISING_OPT_OUT,
      UsNjField.SENSITIVE_DATA_PROCESSING,
      UsNjField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsNjField.ADDITIONAL_DATA_PROCESSING_CONSENT,
      UsNjField.MSPA_COVERED_TRANSACTION,
      UsNjField.MSPA_OPT_OUT_OPTION_MODE,
      UsNjField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames<UsNjField> USNJ_GPC_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsNjField.GPC_SEGMENT_TYPE,
      UsNjField.GPC_SEGMENT_INCLUDED,
      UsNjField.GPC
  );
  //@formatter:on
}
