package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.datatype.FixedIntegerList;
import com.iab.gpp.encoder.field.UsInField;
import com.iab.gpp.encoder.segment.Base64Segment;

public class UsIn extends AbstractUsSectionWithHeader<UsInField> {

  public static final int ID = 25;
  public static final int VERSION = 1;
  public static final String NAME = "usin";

  public UsIn() {
    super(
        new Base64Segment<>(UsInField.USIN_HEADER_SEGMENT_FIELD_NAMES),
        new Base64Segment<>(UsInField.USIN_CORE_SEGMENT_FIELD_NAMES),
        new Base64Segment<>(UsInField.USIN_SENSITIVE_DATA_CONSENTS_SEGMENT_FIELD_NAMES, true));
  }

  public UsIn(String encodedString) {
    this();
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
    return (Integer) this.getFieldValue(UsInField.VERSION);
  }

  @Override
  protected final UsInField getSubSectionsKey() {
    return UsInField.SUB_SECTIONS;
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

  public FixedIntegerList getSensitiveDataProcessing() {
    return (FixedIntegerList) this.getFieldValue(UsInField.SENSITIVE_DATA_PROCESSING);
  }
}
