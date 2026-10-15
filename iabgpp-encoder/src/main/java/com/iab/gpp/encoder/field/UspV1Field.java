package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.UnencodableCharacter;
import com.iab.gpp.encoder.datatype.UnencodableInteger;
import com.iab.gpp.encoder.section.UspV1;

public enum UspV1Field implements FieldKey {
  VERSION(new UnencodableInteger<>("Version", UspV1.VERSION)),
  NOTICE(new UnencodableCharacter<>("Notice", '-', (v -> v == 'Y' || v == 'N' || v == '-'))),
  OPT_OUT_SALE(
      new UnencodableCharacter<>("OptOutSale", '-', (v -> v == 'Y' || v == 'N' || v == '-'))),
  LSPA_COVERED(
      new UnencodableCharacter<>("LspaCovered", '-', (v -> v == 'Y' || v == 'N' || v == '-')));

  private final DataType<UspV1Field, ?> type;

  UspV1Field(DataType<UspV1Field, ?> type) {
    this.type = type;
  }

  @Override
  public DataType<UspV1Field, ?> getType() {
    return type;
  }

  public static final FieldNames<UspV1Field> USPV1_CORE_SEGMENT_FIELD_NAMES =
      new FieldNames<>(
          UspV1Field.VERSION, UspV1Field.NOTICE, UspV1Field.OPT_OUT_SALE, UspV1Field.LSPA_COVERED);
}
