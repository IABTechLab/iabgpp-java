package com.iab.gpp.encoder.section;

import java.util.Collections;
import com.iab.gpp.encoder.field.UspV1Field;
import com.iab.gpp.encoder.segment.UspV1CoreSegment;

public class UspV1 extends AbstractLazilyEncodableSection<UspV1Field> {

  public static final int ID = 6;
  public static final int VERSION = 1;
  public static final String NAME = "uspv1";

  public UspV1() {
    super(Collections.singletonList(new UspV1CoreSegment()));
  }

  public UspV1(CharSequence encodedString) {
    this();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UspV1.ID;
  }

  @Override
  public String getName() {
    return UspV1.NAME;
  }

  @Override
  public int getVersion() {
    return UspV1.VERSION;
  }

  public Character getNotice() {
    return (Character) this.getFieldValue(UspV1Field.NOTICE);
  }

  public Character getOptOutSale() {
    return (Character) this.getFieldValue(UspV1Field.OPT_OUT_SALE);
  }

  public Character getLspaCovered() {
    return (Character) this.getFieldValue(UspV1Field.LSPA_COVERED);
  }
}
