package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.field.UsRiField;
import com.iab.gpp.encoder.segment.*;

import java.util.ArrayList;
import java.util.List;

public class UsRi extends AbstractLazilyEncodableSection {

  public static int ID = 27;
  public static int VERSION = 1;
  public static String NAME = "usri";

  public UsRi() {
    super();
  }

  public UsRi(String encodedString) {
    super();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsRi.ID;
  }

  @Override
  public String getName() {
    return UsRi.NAME;
  }

  @Override
  public int getVersion() {
    return UsRi.VERSION;
  }

  @Override
  protected List<EncodableSegment> initializeSegments() {
    List<EncodableSegment> segments = new ArrayList<>();
    segments.add(new UsRiCoreSegment());
    segments.add(new UsRiGpcSegment());
    return segments;
  }

  @Override
  protected List<EncodableSegment> decodeSection(String encodedString) {
    List<EncodableSegment> segments = initializeSegments();

    if (encodedString != null && !encodedString.isEmpty()) {
      String[] encodedSegments = encodedString.split("\\.");

      if (encodedSegments.length > 0) {
        segments.get(0).decode(encodedSegments[0]);
      }

      if (encodedSegments.length > 1) {
        segments.get(1).setFieldValue(UsRiField.GPC_SEGMENT_INCLUDED, true);
        segments.get(1).decode(encodedSegments[1]);
      } else {
        segments.get(1).setFieldValue(UsRiField.GPC_SEGMENT_INCLUDED, false);
      }
    }

    return segments;
  }

  @Override
  protected String encodeSection(List<EncodableSegment> segments) {
    List<String> encodedSegments = new ArrayList<>();

    if (!segments.isEmpty()) {
      encodedSegments.add(segments.get(0).encode());
      if (segments.size() >= 2 && segments.get(1).getFieldValue(UsRiField.GPC_SEGMENT_INCLUDED).equals(true)) {
        encodedSegments.add(segments.get(1).encode());
      }
    }

    return String.join(".", encodedSegments);
  }


  public Integer getMspaVersion() {
    return (Integer) this.getFieldValue(UsRiField.MSPA_VERSION);
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsRiField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaMode() {
    return (Integer) this.getFieldValue(UsRiField.MSPA_MODE);
  }

  public Integer getProcessingNotice() {
    return (Integer) this.getFieldValue(UsRiField.PROCESSING_NOTICE);
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsRiField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.getFieldValue(UsRiField.TARGETED_ADVERTISING_OPT_OUT_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsRiField.SALE_OPT_OUT);
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.getFieldValue(UsRiField.TARGETED_ADVERTISING_OPT_OUT);
  }

  public Integer getKnownChildSensitiveDataConsents() {
    return (Integer) this.getFieldValue(UsRiField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
  }

  public Integer getAdditionalDataProcessingConsent() {
    return (Integer) this.getFieldValue(UsRiField.ADDITIONAL_DATA_PROCESSING_CONSENT);
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getSensitiveDataProcessing() {
    return (List<Integer>) this.getFieldValue(UsRiField.SENSITIVE_DATA_PROCESSING);
  }

  public Integer getGpcSegmentType() {
    return (Integer) this.getFieldValue(UsRiField.GPC_SEGMENT_TYPE);
  }

  public Boolean getGpcSegmentIncluded() {
    return (Boolean) this.getFieldValue(UsRiField.GPC_SEGMENT_INCLUDED);
  }

  public Boolean getGpc() {
    return (Boolean) this.getFieldValue(UsRiField.GPC);
  }
}
