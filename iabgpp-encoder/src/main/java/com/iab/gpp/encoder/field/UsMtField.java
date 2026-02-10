package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.section.UsMt;

public enum UsMtField implements FieldKey {
  VERSION("Version", new EncodableFixedInteger(6, UsMt.VERSION)),
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

  UsMtField(String name, DataType<?> type) {
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
  public static final FieldNames<UsMtField> USMT_CORE_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsMtField.VERSION,
      UsMtField.SHARING_NOTICE,
      UsMtField.SALE_OPT_OUT_NOTICE,
      UsMtField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsMtField.SALE_OPT_OUT,
      UsMtField.TARGETED_ADVERTISING_OPT_OUT,
      UsMtField.SENSITIVE_DATA_PROCESSING,
      UsMtField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsMtField.ADDITIONAL_DATA_PROCESSING_CONSENT,
      UsMtField.MSPA_COVERED_TRANSACTION,
      UsMtField.MSPA_OPT_OUT_OPTION_MODE,
      UsMtField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on

  //@formatter:off
  public static FieldNames<UsMtField> USMT_GPC_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsMtField.GPC_SEGMENT_TYPE,
      UsMtField.GPC_SEGMENT_INCLUDED,
      UsMtField.GPC
  );
  //@formatter:on
}
