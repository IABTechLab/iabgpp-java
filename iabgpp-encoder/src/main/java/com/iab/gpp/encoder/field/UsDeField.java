package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.section.UsDe;

public enum UsDeField implements FieldKey {
  VERSION("Version", new EncodableFixedInteger(6, UsDe.VERSION)),
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
      new EncodableFixedIntegerList(2, 9)
      .withValidator(nullableBooleanAsTwoBitIntegerListValidator)),
  KNOWN_CHILD_SENSITIVE_DATA_CONSENTS("KnownChildSensitiveDataConsents",
      new EncodableFixedIntegerList(2, 5)
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

  UsDeField(String name, DataType<?> type) {
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
  public static final FieldNames<UsDeField> USDE_CORE_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsDeField.VERSION,
      UsDeField.PROCESSING_NOTICE,
      UsDeField.SALE_OPT_OUT_NOTICE,
      UsDeField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsDeField.SALE_OPT_OUT,
      UsDeField.TARGETED_ADVERTISING_OPT_OUT,
      UsDeField.SENSITIVE_DATA_PROCESSING,
      UsDeField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsDeField.ADDITIONAL_DATA_PROCESSING_CONSENT,
      UsDeField.MSPA_COVERED_TRANSACTION,
      UsDeField.MSPA_OPT_OUT_OPTION_MODE,
      UsDeField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames<UsDeField> USDE_GPC_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsDeField.GPC_SEGMENT_TYPE,
      UsDeField.GPC_SEGMENT_INCLUDED,
      UsDeField.GPC
  );
  //@formatter:on
}
