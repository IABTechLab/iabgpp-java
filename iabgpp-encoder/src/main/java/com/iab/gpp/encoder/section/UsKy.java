package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.datatype.FixedIntegerList;
import com.iab.gpp.encoder.field.UsKyField;
import com.iab.gpp.encoder.segment.Base64Segment;

public class UsKy extends AbstractUsSectionWithHeader<UsKyField> {

  public static final int ID = 26;
  public static final int VERSION = 1;
  public static final String NAME = "usky";

  public UsKy() {
    super(
        new Base64Segment<>(UsKyField.USKY_HEADER_SEGMENT_FIELD_NAMES),
        new Base64Segment<>(UsKyField.USKY_CORE_SEGMENT_FIELD_NAMES),
        new Base64Segment<>(UsKyField.USKY_SENSITIVE_DATA_CONSENTS_SEGMENT_FIELD_NAMES, true));
  }

  public UsKy(String encodedString) {
    this();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsKy.ID;
  }

  @Override
  public String getName() {
    return UsKy.NAME;
  }

  @Override
  public int getVersion() {
    return (Integer) this.getFieldValue(UsKyField.VERSION);
  }

  @Override
  protected final UsKyField getSubSectionsKey() {
    return UsKyField.SUB_SECTIONS;
  }

  public Integer getMspaVersion() {
    return (Integer) this.getFieldValue(UsKyField.MSPA_VERSION);
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsKyField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaMode() {
    return (Integer) this.getFieldValue(UsKyField.MSPA_MODE);
  }

  public Integer getProcessingNotice() {
    return (Integer) this.getFieldValue(UsKyField.PROCESSING_NOTICE);
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsKyField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.getFieldValue(UsKyField.TARGETED_ADVERTISING_OPT_OUT_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsKyField.SALE_OPT_OUT);
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.getFieldValue(UsKyField.TARGETED_ADVERTISING_OPT_OUT);
  }

  public Integer getKnownChildSensitiveDataConsents() {
    return (Integer) this.getFieldValue(UsKyField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
  }

  public Integer getAdditionalDataProcessingConsent() {
    return (Integer) this.getFieldValue(UsKyField.ADDITIONAL_DATA_PROCESSING_CONSENT);
  }

  public FixedIntegerList getSensitiveDataProcessing() {
    return (FixedIntegerList) this.getFieldValue(UsKyField.SENSITIVE_DATA_PROCESSING);
  }
}
