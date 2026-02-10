package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.UnencodableCharacter;
import com.iab.gpp.encoder.datatype.UnencodableInteger;
import com.iab.gpp.encoder.section.UspV1;

public enum UspV1Field implements FieldKey {
  VERSION("Version", new UnencodableInteger<>(UspV1.VERSION)),
  NOTICE("Notice", new UnencodableCharacter<>('-', (v -> v == 'Y' || v == 'N' || v == '-'))),
  OPT_OUT_SALE("OptOutSale", new UnencodableCharacter<>('-', (v -> v == 'Y' || v == 'N' || v == '-'))),
  LSPA_COVERED("LspaCovered", new UnencodableCharacter<>('-', (v -> v == 'Y' || v == 'N' || v == '-')));

  private final String name;
  private final DataType<UspV1Field, ?> type;

  UspV1Field(String name, DataType<UspV1Field, ?> type) {
    this.name = name;
    this.type = type;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public DataType<UspV1Field, ?> getType() {
    return type;
  }

  //@formatter:off
  public static final FieldNames<UspV1Field> USPV1_CORE_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UspV1Field.VERSION,
      UspV1Field.NOTICE,
      UspV1Field.OPT_OUT_SALE,
      UspV1Field.LSPA_COVERED
  );
  //@formatter:on
}
