package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.datatype.FixedIntegerList;
import com.iab.gpp.encoder.field.UsRiField;
import com.iab.gpp.encoder.segment.Base64Segment;

public class UsRi extends AbstractUsSectionWithHeader<UsRiField> {

  public static final int ID = 27;
  public static final int VERSION = 1;
  public static final String NAME = "usri";

  public UsRi() {
    super(
        new Base64Segment<>(UsRiField.USRI_HEADER_SEGMENT_FIELD_NAMES),
        new Base64Segment<>(UsRiField.USRI_CORE_SEGMENT_FIELD_NAMES),
        new Base64Segment<>(UsRiField.USRI_SENSITIVE_DATA_CONSENTS_SEGMENT_FIELD_NAMES, true));
  }

  public UsRi(String encodedString) {
    this();
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
    return (Integer) this.getFieldValue(UsRiField.VERSION);
  }

  @Override
  protected final UsRiField getSubSectionsKey() {
    return UsRiField.SUB_SECTIONS;
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

  public FixedIntegerList getSensitiveDataProcessing() {
    return (FixedIntegerList) this.getFieldValue(UsRiField.SENSITIVE_DATA_PROCESSING);
  }
}
