package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.field.UsInField;
import com.iab.gpp.encoder.segment.*;

import java.util.ArrayList;
import java.util.List;

public class UsIn extends AbstractLazilyEncodableSection {

  public static int ID = 25;
  public static int VERSION = 1;
  public static String NAME = "usin";

  public UsIn() {
    super();
  }

  public UsIn(String encodedString) {
    super();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsIn.ID;
  }

  @Override
  public String getName() {
    return UsIn.NAME;
  }

  @Override
  public int getVersion() {
    return UsIn.VERSION;
  }

  @Override
  protected List<EncodableSegment> initializeSegments() {
    List<EncodableSegment> segments = new ArrayList<>();
    segments.add(new UsInCoreSegment());
    segments.add(new UsInGpcSegment());
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
        segments.get(1).setFieldValue(UsInField.GPC_SEGMENT_INCLUDED, true);
        segments.get(1).decode(encodedSegments[1]);
      } else {
        segments.get(1).setFieldValue(UsInField.GPC_SEGMENT_INCLUDED, false);
      }
    }

    return segments;
  }

  @Override
  protected String encodeSection(List<EncodableSegment> segments) {
    List<String> encodedSegments = new ArrayList<>();

    if (!segments.isEmpty()) {
      encodedSegments.add(segments.get(0).encode());
      if (segments.size() >= 2 && segments.get(1).getFieldValue(UsInField.GPC_SEGMENT_INCLUDED).equals(true)) {
        encodedSegments.add(segments.get(1).encode());
      }
    }

    return String.join(".", encodedSegments);
  }


  public Integer getMspaVersion() {
    return (Integer) this.getFieldValue(UsInField.MSPA_VERSION);
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsInField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaMode() {
    return (Integer) this.getFieldValue(UsInField.MSPA_MODE);
  }

  public Integer getProcessingNotice() {
    return (Integer) this.getFieldValue(UsInField.PROCESSING_NOTICE);
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsInField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.getFieldValue(UsInField.TARGETED_ADVERTISING_OPT_OUT_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsInField.SALE_OPT_OUT);
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.getFieldValue(UsInField.TARGETED_ADVERTISING_OPT_OUT);
  }

  public Integer getKnownChildSensitiveDataConsents() {
    return (Integer) this.getFieldValue(UsInField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
  }

  public Integer getAdditionalDataProcessingConsent() {
    return (Integer) this.getFieldValue(UsInField.ADDITIONAL_DATA_PROCESSING_CONSENT);
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getSensitiveDataProcessing() {
    return (List<Integer>) this.getFieldValue(UsInField.SENSITIVE_DATA_PROCESSING);
  }

  public Integer getGpcSegmentType() {
    return (Integer) this.getFieldValue(UsInField.GPC_SEGMENT_TYPE);
  }

  public Boolean getGpcSegmentIncluded() {
    return (Boolean) this.getFieldValue(UsInField.GPC_SEGMENT_INCLUDED);
  }

  public Boolean getGpc() {
    return (Boolean) this.getFieldValue(UsInField.GPC);
  }
}
