package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.section.UsTn;

public enum UsTnField implements FieldKey {
  VERSION("Version", new EncodableFixedInteger<>(6, UsTn.VERSION)),
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
  private final DataType<UsTnField, ?> type;

  UsTnField(String name, DataType<UsTnField, ?> type) {
    this.name = name;
    this.type = type;
  }

  @Override
  public String getName() {
    return name;
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
