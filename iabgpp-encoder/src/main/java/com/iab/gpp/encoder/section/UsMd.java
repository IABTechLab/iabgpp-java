package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.field.UsMdField;
import com.iab.gpp.encoder.segment.Base64Segment;

public class UsMd extends AbstractUsSectionWithHeader<UsMdField> {

  public static final int ID = 24;
  public static final int VERSION = 1;
  public static final String NAME = "usmd";

  public UsMd() {
    super(
        new Base64Segment<>(UsMdField.USMD_HEADER_SEGMENT_FIELD_NAMES),
        new Base64Segment<>(UsMdField.USMD_CORE_SEGMENT_FIELD_NAMES),
        new Base64Segment<>(UsMdField.USMD_GPC_SEGMENT_FIELD_NAMES, true));
  }

  public UsMd(String encodedString) {
    this();
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
    return (Integer) this.getFieldValue(UsMdField.VERSION);
  }

  @Override
  protected final UsMdField getSubSectionsKey() {
    return UsMdField.SUB_SECTIONS;
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

  public Boolean getGpc() {
    return (Boolean) this.getFieldValue(UsMdField.GPC);
  }
}
