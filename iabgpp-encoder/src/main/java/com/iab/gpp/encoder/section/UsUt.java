package com.iab.gpp.encoder.section;

import java.util.ArrayList;
import java.util.List;
import com.iab.gpp.encoder.field.UsUtField;
import com.iab.gpp.encoder.segment.EncodableSegment;
import com.iab.gpp.encoder.segment.UsUtCoreSegment;

public class UsUt extends AbstractLazilyEncodableSection {
  
  public static int ID = 11;
  public static int VERSION = 1;
  public static String NAME = "usut";

  public UsUt() {
    super();
  }

  public UsUt(String encodedString) {
    super();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsUt.ID;
  }

  @Override
  public String getName() {
    return UsUt.NAME;
  }

  @Override
  public int getVersion() {
    return UsUt.VERSION;
  }

  @Override
  protected List<EncodableSegment> initializeSegments() {
    List<EncodableSegment> segments = new ArrayList<>();
    segments.add(new UsUtCoreSegment());
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

  
  public Integer getSharingNotice() {
    return (Integer) this.getFieldValue(UsUtField.SHARING_NOTICE);
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsUtField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.getFieldValue(UsUtField.TARGETED_ADVERTISING_OPT_OUT_NOTICE);
  }

  public Integer getSensitiveDataProcessingOptOutNotice() {
    return (Integer) this.getFieldValue(UsUtField.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsUtField.SALE_OPT_OUT);
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.getFieldValue(UsUtField.TARGETED_ADVERTISING_OPT_OUT);
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getSensitiveDataProcessing() {
    return (List<Integer>) this.getFieldValue(UsUtField.SENSITIVE_DATA_PROCESSING);
  }

  public Integer getKnownChildSensitiveDataConsents() {
    return (Integer) this.getFieldValue(UsUtField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsUtField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.getFieldValue(UsUtField.MSPA_OPT_OUT_OPTION_MODE);
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.getFieldValue(UsUtField.MSPA_SERVICE_PROVIDER_MODE);
  }
  
  
}
