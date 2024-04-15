package com.iab.gpp.encoder.section;

import java.util.ArrayList;
import java.util.List;
import com.iab.gpp.encoder.field.UspV1Field;
import com.iab.gpp.encoder.segment.EncodableSegment;
import com.iab.gpp.encoder.segment.UspV1CoreSegment;

public class UspV1 extends AbstractLazilyEncodableSection {
  
  public static int ID = 6;
  public static int VERSION = 1;
  public static String NAME = "uspv1";

  public UspV1() {
    super();
  }

  public UspV1(String encodedString) {
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
    List<EncodableSegment> segments = new ArrayList<>();
    segments.add(new UspV1CoreSegment());
    return segments;
  }
  
  @Override
  protected List<EncodableSegment> decodeSection(String encodedString) {
    List<EncodableSegment> segments = initializeSegments();
    
    if(encodedString != null && !encodedString.isEmpty()) {
      String[] encodedSegments = encodedString.split("\\.");
      
      for(int i=0; i<segments.size(); i++) {
        if(encodedSegments.length > i) {
          segments.get(i).decode(encodedSegments[i]);
        }
      }
    }
    
    return segments;
  }

  @Override
  protected String encodeSection(List<EncodableSegment> segments) {
    List<String> encodedSegments = new ArrayList<>();
    for(EncodableSegment segment : segments) {
      encodedSegments.add(segment.encode());
    }
    return String.join(".", encodedSegments);
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
