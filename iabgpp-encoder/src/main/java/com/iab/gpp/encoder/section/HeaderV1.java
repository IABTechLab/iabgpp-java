package com.iab.gpp.encoder.section;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

  public HeaderV1(String encodedString) {
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
    List<String> encodedSegments = new ArrayList<>(segments.size());
    for(EncodableSegment segment : segments) {
      encodedSegments.add(segment.encode());
    }
    return String.join(".", encodedSegments);
  }

  
  @SuppressWarnings("unchecked")
  public List<Integer> getSectionsIds() {
    return (List<Integer>) this.getFieldValue(HeaderV1Field.SECTION_IDS);
  }
  
  
}
