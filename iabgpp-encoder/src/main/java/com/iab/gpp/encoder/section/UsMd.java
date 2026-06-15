package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.field.UsMdField;
import com.iab.gpp.encoder.segment.*;

import java.util.ArrayList;
import java.util.List;

public class UsMd extends AbstractLazilyEncodableSection {

  public static int ID = 24;
  public static int VERSION = 1;
  public static String NAME = "usmd";

  public UsMd() {
    super();
  }

  public UsMd(String encodedString) {
    super();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsMd.ID;
  }

  @Override
  public String getName() {
    return UsMd.NAME;
  }

  @Override
  public int getVersion() {
    return UsMd.VERSION;
  }

  @Override
  protected List<EncodableSegment> initializeSegments() {
    List<EncodableSegment> segments = new ArrayList<>();
    segments.add(new UsMdCoreSegment());
    segments.add(new UsMdGpcSegment());
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
        segments.get(1).setFieldValue(UsMdField.GPC_SEGMENT_INCLUDED, true);
        segments.get(1).decode(encodedSegments[1]);
      } else {
        segments.get(1).setFieldValue(UsMdField.GPC_SEGMENT_INCLUDED, false);
      }
    }

    return segments;
  }

  @Override
  protected String encodeSection(List<EncodableSegment> segments) {
    List<String> encodedSegments = new ArrayList<>();

    if (!segments.isEmpty()) {
      encodedSegments.add(segments.get(0).encode());
      if (segments.size() >= 2 && segments.get(1).getFieldValue(UsMdField.GPC_SEGMENT_INCLUDED).equals(true)) {
        encodedSegments.add(segments.get(1).encode());
      }
    }

    return String.join(".", encodedSegments);
  }


  public Integer getMspaVersion() {
    return (Integer) this.getFieldValue(UsMdField.MSPA_VERSION);
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsMdField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaMode() {
    return (Integer) this.getFieldValue(UsMdField.MSPA_MODE);
  }

  public Integer getProcessingNotice() {
    return (Integer) this.getFieldValue(UsMdField.PROCESSING_NOTICE);
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsMdField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.getFieldValue(UsMdField.TARGETED_ADVERTISING_OPT_OUT_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsMdField.SALE_OPT_OUT);
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.getFieldValue(UsMdField.TARGETED_ADVERTISING_OPT_OUT);
  }

  public Integer getAdditionalDataProcessingConsent() {
    return (Integer) this.getFieldValue(UsMdField.ADDITIONAL_DATA_PROCESSING_CONSENT);
  }

  public Integer getGpcSegmentType() {
    return (Integer) this.getFieldValue(UsMdField.GPC_SEGMENT_TYPE);
  }

  public Boolean getGpcSegmentIncluded() {
    return (Boolean) this.getFieldValue(UsMdField.GPC_SEGMENT_INCLUDED);
  }

  public Boolean getGpc() {
    return (Boolean) this.getFieldValue(UsMdField.GPC);
  }
}
