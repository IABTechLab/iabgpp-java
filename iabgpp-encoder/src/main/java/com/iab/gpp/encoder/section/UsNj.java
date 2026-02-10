package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.datatype.FixedIntegerList;
import com.iab.gpp.encoder.field.UsNjField;
import com.iab.gpp.encoder.segment.Base64Segment;

public class UsNj extends AbstractUsSectionWithGpc<UsNjField> {

  public static final int ID = 21;
  public static final int VERSION = 1;
  public static final String NAME = "usnj";

  public UsNj() {
    super(new Base64Segment<>(UsNjField.USNJ_CORE_SEGMENT_FIELD_NAMES), new Base64Segment<>(UsNjField.USNJ_GPC_SEGMENT_FIELD_NAMES));
  }

  public UsNj(CharSequence encodedString) {
    this();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsNj.ID;
  }

  @Override
  public String getName() {
    return UsNj.NAME;
  }

  @Override
  public int getVersion() {
    return UsNj.VERSION;
  }

  @Override
  protected final UsNjField getGpcSegmentIncludedKey() {
    return UsNjField.GPC_SEGMENT_INCLUDED;
  }

  public Integer getProcessingNotice() {
    return (Integer) this.getFieldValue(UsNjField.PROCESSING_NOTICE);
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsNjField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.getFieldValue(UsNjField.TARGETED_ADVERTISING_OPT_OUT_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsNjField.SALE_OPT_OUT);
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.getFieldValue(UsNjField.TARGETED_ADVERTISING_OPT_OUT);
  }

  public FixedIntegerList getSensitiveDataProcessing() {
    return (FixedIntegerList) this.getFieldValue(UsNjField.SENSITIVE_DATA_PROCESSING);
  }

  public FixedIntegerList getKnownChildSensitiveDataConsents() {
    return (FixedIntegerList) this.getFieldValue(UsNjField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
  }

  public Integer getAdditionalDataProcessingConsent() {
    return (Integer) this.getFieldValue(UsNjField.ADDITIONAL_DATA_PROCESSING_CONSENT);
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsNjField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.getFieldValue(UsNjField.MSPA_OPT_OUT_OPTION_MODE);
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.getFieldValue(UsNjField.MSPA_SERVICE_PROVIDER_MODE);
  }

  public Integer getGpcSegmentType() {
    return (Integer) this.getFieldValue(UsNjField.GPC_SEGMENT_TYPE);
  }

  public Boolean getGpc() {
    return (Boolean) this.getFieldValue(UsNjField.GPC);
  }
}
