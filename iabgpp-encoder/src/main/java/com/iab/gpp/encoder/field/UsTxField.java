package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.section.UsTx;

public enum UsTxField implements FieldKey {
  VERSION("Version", new EncodableFixedInteger<>(6, UsTx.VERSION)),
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
  KNOWN_CHILD_SENSITIVE_DATA_CONSENTS("KnownChildSensitiveDataConsents",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator)),
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
  private final DataType<UsTxField, ?> type;

  UsTxField(String name, DataType<UsTxField, ?> type) {
    this.name = name;
    this.type = type;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public DataType<UsTxField, ?> getType() {
    return type;
  }

  //@formatter:off
  public static final FieldNames<UsTxField> USTX_CORE_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsTxField.VERSION,
      UsTxField.PROCESSING_NOTICE,
      UsTxField.SALE_OPT_OUT_NOTICE,
      UsTxField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsTxField.SALE_OPT_OUT,
      UsTxField.TARGETED_ADVERTISING_OPT_OUT,
      UsTxField.SENSITIVE_DATA_PROCESSING,
      UsTxField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsTxField.ADDITIONAL_DATA_PROCESSING_CONSENT,
      UsTxField.MSPA_COVERED_TRANSACTION,
      UsTxField.MSPA_OPT_OUT_OPTION_MODE,
      UsTxField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on

  //@formatter:off
  public static FieldNames<UsTxField> USTX_GPC_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsTxField.GPC_SEGMENT_TYPE,
      UsTxField.GPC_SEGMENT_INCLUDED,
      UsTxField.GPC
  );
  //@formatter:on
}
