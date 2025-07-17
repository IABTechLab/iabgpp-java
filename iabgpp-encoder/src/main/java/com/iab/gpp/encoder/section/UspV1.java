package com.iab.gpp.encoder.section;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.iab.gpp.encoder.field.UspV1Field;
import com.iab.gpp.encoder.segment.EncodableSegment;
import com.iab.gpp.encoder.segment.UspV1CoreSegment;

public class UspV1 extends AbstractLazilyEncodableSection {

  public static final int ID = 6;
  public static final int VERSION = 1;
  public static final String NAME = "uspv1";

  public UspV1() {
    super();
  }

  public UspV1(CharSequence encodedString) {
    super();
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

  @Override
  protected List<EncodableSegment> initializeSegments() {
    return Collections.singletonList(new UspV1CoreSegment());
  }

  @Override
  protected List<EncodableSegment> decodeSection(CharSequence encodedString) {
    if (encodedString != null && encodedString.length() > 0) {
      List<CharSequence> encodedSegments = SlicedCharSequence.split(encodedString, '.');

      for (int i=0; i < segments.size(); i++) {
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
