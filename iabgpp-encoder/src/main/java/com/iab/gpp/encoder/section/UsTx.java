package com.iab.gpp.encoder.section;

import java.util.ArrayList;
import java.util.List;
import com.iab.gpp.encoder.field.UsTxField;
import com.iab.gpp.encoder.segment.EncodableSegment;
import com.iab.gpp.encoder.segment.UsTxCoreSegment;
import com.iab.gpp.encoder.segment.UsTxGpcSegment;

public class UsTx extends AbstractLazilyEncodableSection {

  public static int ID = 16;
  public static int VERSION = 1;
  public static String NAME = "ustx";

  public UsTx() {
    super();
  }

  public UsTx(String encodedString) {
    super();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsTx.ID;
  }

  @Override
  public String getName() {
    return UsTx.NAME;
  }

  @Override
  public int getVersion() {
    return UsTx.VERSION;
  }

  @Override
  protected List<EncodableSegment> initializeSegments() {
    List<EncodableSegment> segments = new ArrayList<>();
    segments.add(new UsTxCoreSegment());
    segments.add(new UsTxGpcSegment());
    return segments;
  }

  @Override
  protected List<EncodableSegment> decodeSection(String encodedString) {
    List<EncodableSegment> segments = initializeSegments();

    if(encodedString != null && !encodedString.isEmpty()) {
      String[] encodedSegments = encodedString.split("\\.");
  
      if(encodedSegments.length > 0) {
        segments.get(0).decode(encodedSegments[0]);
      }
      
      if(encodedSegments.length > 1) {
        segments.get(1).setFieldValue(UsTxField.GPC_SEGMENT_INCLUDED, true);
        segments.get(1).decode(encodedSegments[1]);
      } else {
        segments.get(1).setFieldValue(UsTxField.GPC_SEGMENT_INCLUDED, false);
      }
    }

    return segments;
  }

  @Override
  protected String encodeSection(List<EncodableSegment> segments) {
    List<String> encodedSegments = new ArrayList<>();
    
    if(!segments.isEmpty()) {
      encodedSegments.add(segments.get(0).encode());
      if(segments.size() >= 2 && segments.get(1).getFieldValue(UsTxField.GPC_SEGMENT_INCLUDED).equals(true)) {
        encodedSegments.add(segments.get(1).encode());
      }
    }
    
    return String.join(".", encodedSegments);
  }


  public Integer getProcessingNotice() {
    return (Integer) this.getFieldValue(UsTxField.PROCESSING_NOTICE);
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsTxField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.getFieldValue(UsTxField.TARGETED_ADVERTISING_OPT_OUT_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsTxField.SALE_OPT_OUT);
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.getFieldValue(UsTxField.TARGETED_ADVERTISING_OPT_OUT);
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getSensitiveDataProcessing() {
    return (List<Integer>) this.getFieldValue(UsTxField.SENSITIVE_DATA_PROCESSING);
  }

  public Integer getKnownChildSensitiveDataConsents() {
    return (Integer) this.getFieldValue(UsTxField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
  }

  public Integer getAdditionalDataProcessingConsent() {
    return (Integer) this.getFieldValue(UsTxField.ADDITIONAL_DATA_PROCESSING_CONSENT);
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsTxField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.getFieldValue(UsTxField.MSPA_OPT_OUT_OPTION_MODE);
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.getFieldValue(UsTxField.MSPA_SERVICE_PROVIDER_MODE);
  }

  public Integer getGpcSegmentType() {
    return (Integer) this.getFieldValue(UsTxField.GPC_SEGMENT_TYPE);
  }

  public Boolean getGpcSegmentIncluded() {
    return (Boolean) this.getFieldValue(UsTxField.GPC_SEGMENT_INCLUDED);
  }

  public Boolean getGpc() {
    return (Boolean) this.getFieldValue(UsTxField.GPC);
  }
}
