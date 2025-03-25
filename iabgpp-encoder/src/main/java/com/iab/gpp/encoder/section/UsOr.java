package com.iab.gpp.encoder.section;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import com.iab.gpp.encoder.field.UsOrField;
import com.iab.gpp.encoder.segment.EncodableSegment;
import com.iab.gpp.encoder.segment.UsOrCoreSegment;
import com.iab.gpp.encoder.segment.UsOrGpcSegment;

public class UsOr extends AbstractLazilyEncodableSection {

  public static final int ID = 15;
  public static final int VERSION = 1;
  public static final String NAME = "usor";

  public UsOr() {
    super();
  }

  public UsOr(CharSequence encodedString) {
    super();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsOr.ID;
  }

  @Override
  public String getName() {
    return UsOr.NAME;
  }

  @Override
  public int getVersion() {
    return UsOr.VERSION;
  }

  @Override
  protected List<EncodableSegment> initializeSegments() {
    return Arrays.asList(new UsOrCoreSegment(), new UsOrGpcSegment());
  }

  @Override
  protected List<EncodableSegment> decodeSection(CharSequence encodedString) {
    if(encodedString != null && !encodedString.isEmpty()) {
      List<CharSequence> encodedSegments = SlicedCharSequence.split(encodedString, '.');
  
      if (encodedSegments.size() > 0) {
        segments.get(0).decode(encodedSegments.get(0));
      }
      
      if (encodedSegments.size() > 1) {
        segments.get(1).setFieldValue(UsOrField.GPC_SEGMENT_INCLUDED, true);
        segments.get(1).decode(encodedSegments.get(1));
      } else {
        segments.get(1).setFieldValue(UsOrField.GPC_SEGMENT_INCLUDED, false);
      }
    }

    return segments;
  }

  @Override
  protected String encodeSection(List<EncodableSegment> segments) {
    List<String> encodedSegments = new ArrayList<>(segments.size());
    
    if(!segments.isEmpty()) {
      encodedSegments.add(segments.get(0).encode());
      if(segments.size() >= 2 && segments.get(1).getFieldValue(UsOrField.GPC_SEGMENT_INCLUDED).equals(true)) {
        encodedSegments.add(segments.get(1).encode());
      }
    }
    
    return String.join(".", encodedSegments);
  }


  public Integer getProcessingNotice() {
    return (Integer) this.getFieldValue(UsOrField.PROCESSING_NOTICE);
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsOrField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.getFieldValue(UsOrField.TARGETED_ADVERTISING_OPT_OUT_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsOrField.SALE_OPT_OUT);
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.getFieldValue(UsOrField.TARGETED_ADVERTISING_OPT_OUT);
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getSensitiveDataProcessing() {
    return (List<Integer>) this.getFieldValue(UsOrField.SENSITIVE_DATA_PROCESSING);
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getKnownChildSensitiveDataConsents() {
    return (List<Integer>) this.getFieldValue(UsOrField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
  }

  public Integer getAdditionalDataProcessingConsent() {
    return (Integer) this.getFieldValue(UsOrField.ADDITIONAL_DATA_PROCESSING_CONSENT);
  }
  
  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsOrField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.getFieldValue(UsOrField.MSPA_OPT_OUT_OPTION_MODE);
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.getFieldValue(UsOrField.MSPA_SERVICE_PROVIDER_MODE);
  }

  public Integer getGpcSegmentType() {
    return (Integer) this.getFieldValue(UsOrField.GPC_SEGMENT_TYPE);
  }

  public Boolean getGpcSegmentIncluded() {
    return (Boolean) this.getFieldValue(UsOrField.GPC_SEGMENT_INCLUDED);
  }

  public Boolean getGpc() {
    return (Boolean) this.getFieldValue(UsOrField.GPC);
  }
}
