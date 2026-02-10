package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.section.UsNat;

public enum UsNatField implements FieldKey {
  VERSION("Version", new EncodableFixedInteger<>(6, UsNat.VERSION)),
  SHARING_NOTICE("SharingNotice",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SALE_OPT_OUT_NOTICE("SaleOptOutNotice",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SHARING_OPT_OUT_NOTICE("SharingOptOutNotice",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  TARGETED_ADVERTISING_OPT_OUT_NOTICE("TargetedAdvertisingOptOutNotice",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE("SensitiveDataProcessingOptOutNotice",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SENSITIVE_DATA_LIMIT_USE_NOTICE("SensitiveDataLimitUseNotice",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SALE_OPT_OUT("SaleOptOut",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SHARING_OPT_OUT("SharingOptOut",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  TARGETED_ADVERTISING_OPT_OUT("TargetedAdvertisingOptOut",
      new EncodableFixedInteger<>(2, 0, nullableBooleanAsTwoBitIntegerValidator)),
  SENSITIVE_DATA_PROCESSING("SensitiveDataProcessing",
      new EncodableFixedIntegerList<>(2, 16, nullableBooleanAsTwoBitIntegerListValidator)),
  KNOWN_CHILD_SENSITIVE_DATA_CONSENTS("KnownChildSensitiveDataConsents", new EncodableFixedIntegerList<>(2, 3, nullableBooleanAsTwoBitIntegerListValidator)),
  PERSONAL_DATA_CONSENTS("PersonalDataConsents",
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
  private final DataType<UsNatField, ?> type;

  UsNatField(String name, DataType<UsNatField, ?> type) {
    this.name = name;
    this.type = type;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public DataType<UsNatField, ?> getType() {
    return type;
  }

  //@formatter:off
  public static final FieldNames<UsNatField> USNAT_CORE_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsNatField.VERSION,
      UsNatField.SHARING_NOTICE,
      UsNatField.SALE_OPT_OUT_NOTICE,
      UsNatField.SHARING_OPT_OUT_NOTICE,
      UsNatField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsNatField.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE,
      UsNatField.SENSITIVE_DATA_LIMIT_USE_NOTICE,
      UsNatField.SALE_OPT_OUT,
      UsNatField.SHARING_OPT_OUT,
      UsNatField.TARGETED_ADVERTISING_OPT_OUT,
      UsNatField.SENSITIVE_DATA_PROCESSING,
      UsNatField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsNatField.PERSONAL_DATA_CONSENTS,
      UsNatField.MSPA_COVERED_TRANSACTION,
      UsNatField.MSPA_OPT_OUT_OPTION_MODE,
      UsNatField.MSPA_SERVICE_PROVIDER_MODE
   );
  //@formatter:on

  //@formatter:off
  public static final FieldNames<UsNatField> USNAT_GPC_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsNatField.GPC_SEGMENT_TYPE,
      UsNatField.GPC_SEGMENT_INCLUDED,
      UsNatField.GPC
  );
  //@formatter:on
}
