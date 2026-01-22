package com.iab.gpp.encoder.section;

import java.util.Collections;
import com.iab.gpp.encoder.datatype.encoder.IntegerSet;
import com.iab.gpp.encoder.field.HeaderV1Field;
import com.iab.gpp.encoder.segment.HeaderV1CoreSegment;

public class HeaderV1 extends AbstractLazilyEncodableSection<HeaderV1Field> {

  public static final int ID = 3;
  public static final int VERSION = 1;
  public static final String NAME = "header";

  public HeaderV1() {
    super(Collections.singletonList(new HeaderV1CoreSegment()));
  }

  public HeaderV1(CharSequence encodedString) {
    this();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return HeaderV1.ID;
  }

  @Override
  public String getName() {
    return HeaderV1.NAME;
  }

  @Override
  public int getVersion() {
    return HeaderV1.VERSION;
  }

  public IntegerSet getSectionsIds() {
    return (IntegerSet) this.getFieldValue(HeaderV1Field.SECTION_IDS);
  }
}
