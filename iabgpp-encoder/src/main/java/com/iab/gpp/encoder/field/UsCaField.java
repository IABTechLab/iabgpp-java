package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.section.UsCa;

public enum UsCaField implements FieldKey {
  VERSION("Version", new EncodableFixedInteger(6, UsCa.VERSION)),
  SALE_OPT_OUT_NOTICE("SaleOptOutNotice",
      new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator)),
  SHARING_OPT_OUT_NOTICE("SharingOptOutNotice",
      new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator)),
  SENSITIVE_DATA_LIMIT_USE_NOTICE("SensitiveDataLimitUseNotice",
      new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator)),
  SALE_OPT_OUT("SaleOptOut",
      new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator)),
  SHARING_OPT_OUT("SharingOptOut",
      new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator)),
  SENSITIVE_DATA_PROCESSING("SensitiveDataProcessing",
      new EncodableFixedIntegerList(2, 9)
      .withValidator(nullableBooleanAsTwoBitIntegerListValidator)),
  KNOWN_CHILD_SENSITIVE_DATA_CONSENTS("KnownChildSensitiveDataConsents", new EncodableFixedIntegerList(2, 2)
      .withValidator(nullableBooleanAsTwoBitIntegerListValidator)),
  PERSONAL_DATA_CONSENTS("PersonalDataConsents",
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

  UsCaField(String name, DataType<?> type) {
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
  public static final FieldNames<UsCaField> USCA_CORE_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsCaField.VERSION,
      UsCaField.SALE_OPT_OUT_NOTICE,
      UsCaField.SHARING_OPT_OUT_NOTICE,
      UsCaField.SENSITIVE_DATA_LIMIT_USE_NOTICE,
      UsCaField.SALE_OPT_OUT,
      UsCaField.SHARING_OPT_OUT,
      UsCaField.SENSITIVE_DATA_PROCESSING,
      UsCaField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsCaField.PERSONAL_DATA_CONSENTS,
      UsCaField.MSPA_COVERED_TRANSACTION,
      UsCaField.MSPA_OPT_OUT_OPTION_MODE,
      UsCaField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames<UsCaField> USCA_GPC_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsCaField.GPC_SEGMENT_TYPE,
      UsCaField.GPC_SEGMENT_INCLUDED,
      UsCaField.GPC
  );
  //@formatter:on
}
