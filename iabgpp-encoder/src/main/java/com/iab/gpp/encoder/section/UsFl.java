package com.iab.gpp.encoder.section;

import java.util.ArrayList;
import java.util.List;
import com.iab.gpp.encoder.field.UsFlField;
import com.iab.gpp.encoder.segment.EncodableSegment;
import com.iab.gpp.encoder.segment.UsFlCoreSegment;

public class UsFl extends AbstractLazilyEncodableSection {

  public static int ID = 13;
  public static int VERSION = 1;
  public static String NAME = "usfl";

  public UsFl() {
    super();
  }

  public UsFl(String encodedString) {
    super();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsFl.ID;
  }

  @Override
  public String getName() {
    return UsFl.NAME;
  }

  @Override
  public int getVersion() {
    return UsFl.VERSION;
  }

  @Override
  protected List<EncodableSegment> initializeSegments() {
    List<EncodableSegment> segments = new ArrayList<>();
    segments.add(new UsFlCoreSegment());
    return segments;
  }

  @Override
  protected List<EncodableSegment> decodeSection(String encodedString) {
    List<EncodableSegment> segments = initializeSegments();

    if(encodedString != null && !encodedString.isEmpty()) {
      String[] encodedSegments = encodedString.split("\\.");
  
      for (int i = 0; i < segments.size(); i++) {
        if (encodedSegments.length > i) {
          segments.get(i).decode(encodedSegments[i]);
        }
      }
    }

    return segments;
  }

  @Override
  protected String encodeSection(List<EncodableSegment> segments) {
    List<String> encodedSegments = new ArrayList<>();
    for (EncodableSegment segment : segments) {
      encodedSegments.add(segment.encode());
    }
    return String.join(".", encodedSegments);
  }


  public Integer getProcessingNotice() {
    return (Integer) this.getFieldValue(UsFlField.PROCESSING_NOTICE);
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsFlField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.getFieldValue(UsFlField.TARGETED_ADVERTISING_OPT_OUT_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsFlField.SALE_OPT_OUT);
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.getFieldValue(UsFlField.TARGETED_ADVERTISING_OPT_OUT);
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getSensitiveDataProcessing() {
    return (List<Integer>) this.getFieldValue(UsFlField.SENSITIVE_DATA_PROCESSING);
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getKnownChildSensitiveDataConsents() {
    return (List<Integer>) this.getFieldValue(UsFlField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
  }

  public Integer getAdditionalDataProcessingConsent() {
    return (Integer) this.getFieldValue(UsFlField.ADDITIONAL_DATA_PROCESSING_CONSENT);
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsFlField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.getFieldValue(UsFlField.MSPA_OPT_OUT_OPTION_MODE);
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.getFieldValue(UsFlField.MSPA_SERVICE_PROVIDER_MODE);
  }
}
