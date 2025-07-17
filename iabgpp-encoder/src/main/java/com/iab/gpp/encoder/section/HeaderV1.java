package com.iab.gpp.encoder.section;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.iab.gpp.encoder.datatype.encoder.IntegerSet;
import com.iab.gpp.encoder.field.HeaderV1Field;
import com.iab.gpp.encoder.segment.EncodableSegment;
import com.iab.gpp.encoder.segment.HeaderV1CoreSegment;

public class HeaderV1 extends AbstractLazilyEncodableSection {

  public static final int ID = 3;
  public static final int VERSION = 1;
  public static final String NAME = "header";

  public HeaderV1() {
    super();
  }

  public HeaderV1(CharSequence encodedString) {
    super();
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

  @Override
  protected List<EncodableSegment> initializeSegments() {
    return Collections.singletonList(new HeaderV1CoreSegment());
  }

  @Override
  protected List<EncodableSegment> decodeSection(CharSequence encodedString) {
    if(encodedString != null && encodedString.length() > 0) {
      List<CharSequence> encodedSegments = SlicedCharSequence.split(encodedString, '.');

      for (int i=0; i<segments.size(); i++) {
        if (encodedSegments.size() > i) {
          segments.get(i).decode(encodedSegments.get(i));
        }
      }
    }

    return segments;
  }

  @Override
  protected CharSequence encodeSection(List<EncodableSegment> segments) {
    List<CharSequence> encodedSegments = new ArrayList<>(segments.size());
    for(EncodableSegment segment : segments) {
      encodedSegments.add(segment.encodeCharSequence());
    }
    return SlicedCharSequence.join('.',  encodedSegments);
  }


  public IntegerSet getSectionsIds() {
    return (IntegerSet) this.getFieldValue(HeaderV1Field.SECTION_IDS);
  }


}
