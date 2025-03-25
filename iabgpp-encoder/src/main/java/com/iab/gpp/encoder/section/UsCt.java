package com.iab.gpp.encoder.section;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.iab.gpp.encoder.field.UsCtField;
import com.iab.gpp.encoder.segment.EncodableSegment;
import com.iab.gpp.encoder.segment.UsCtCoreSegment;
import com.iab.gpp.encoder.segment.UsCtGpcSegment;

public class UsCt extends AbstractLazilyEncodableSection {

  public static final int ID = 12;
  public static final int VERSION = 1;
  public static final String NAME = "usct";

  public UsCt() {
    super();
  }

  public UsCt(CharSequence encodedString) {
    super();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsCt.ID;
  }

  @Override
  public String getName() {
    return UsCt.NAME;
  }

  @Override
  public int getVersion() {
    return UsCt.VERSION;
  }

  @Override
  protected List<EncodableSegment> initializeSegments() {
    return Arrays.asList(new UsCtCoreSegment(), new UsCtGpcSegment());
  }

  @Override
  protected List<EncodableSegment> decodeSection(CharSequence encodedString) {
    if (encodedString != null && encodedString.length() > 0) {
      List<CharSequence> encodedSegments = SlicedCharSequence.split(encodedString, '.');
  
      if (encodedSegments.size() > 0) {
        segments.get(0).decode(encodedSegments.get(0));
      }
      
      if (encodedSegments.size() > 1) {
        segments.get(1).setFieldValue(UsCtField.GPC_SEGMENT_INCLUDED, true);
        segments.get(1).decode(encodedSegments.get(1));
      } else {
        segments.get(1).setFieldValue(UsCtField.GPC_SEGMENT_INCLUDED, false);
      }
    }

    return segments;
  }

  @Override
  protected String encodeSection(List<EncodableSegment> segments) {
    List<String> encodedSegments = new ArrayList<>(segments.size());
    
    if(!segments.isEmpty()) {
      encodedSegments.add(segments.get(0).encode());
      if(segments.size() >= 2 && segments.get(1).getFieldValue(UsCtField.GPC_SEGMENT_INCLUDED).equals(true)) {
        encodedSegments.add(segments.get(1).encode());
      }
    }
    
    return String.join(".", encodedSegments);
  }


  public Integer getSharingNotice() {
    return (Integer) this.getFieldValue(UsCtField.SHARING_NOTICE);
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsCtField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.getFieldValue(UsCtField.TARGETED_ADVERTISING_OPT_OUT_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsCtField.SALE_OPT_OUT);
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.getFieldValue(UsCtField.TARGETED_ADVERTISING_OPT_OUT);
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getSensitiveDataProcessing() {
    return (List<Integer>) this.getFieldValue(UsCtField.SENSITIVE_DATA_PROCESSING);
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getKnownChildSensitiveDataConsents() {
    return (List<Integer>) this.getFieldValue(UsCtField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsCtField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.getFieldValue(UsCtField.MSPA_OPT_OUT_OPTION_MODE);
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.getFieldValue(UsCtField.MSPA_SERVICE_PROVIDER_MODE);
  }

  public Integer getGpcSegmentType() {
    return (Integer) this.getFieldValue(UsCtField.GPC_SEGMENT_TYPE);
  }

  public Boolean getGpcSegmentIncluded() {
    return (Boolean) this.getFieldValue(UsCtField.GPC_SEGMENT_INCLUDED);
  }

  public Boolean getGpc() {
    return (Boolean) this.getFieldValue(UsCtField.GPC);
  }
}
