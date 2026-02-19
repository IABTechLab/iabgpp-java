package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.datatype.FixedIntegerList;
import com.iab.gpp.encoder.field.UsNhField;
import com.iab.gpp.encoder.segment.Base64Segment;

public class UsNh extends AbstractUsSectionWithGpc<UsNhField> {

  public static final int ID = 20;
  public static final int VERSION = 1;
  public static final String NAME = "usnh";

  public UsNh() {
    super(
        new Base64Segment<>(UsNhField.USNH_CORE_SEGMENT_FIELD_NAMES),
        new Base64Segment<>(UsNhField.USNH_GPC_SEGMENT_FIELD_NAMES));
  }

  public UsNh(CharSequence encodedString) {
    this();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsNh.ID;
  }

  @Override
  public String getName() {
    return UsNh.NAME;
  }

  @Override
  public int getVersion() {
    return UsNh.VERSION;
  }

  @Override
  protected final UsNhField getGpcSegmentIncludedKey() {
    return UsNhField.GPC_SEGMENT_INCLUDED;
  }

  public Integer getProcessingNotice() {
    return (Integer) this.getFieldValue(UsNhField.PROCESSING_NOTICE);
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsNhField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.getFieldValue(UsNhField.TARGETED_ADVERTISING_OPT_OUT_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsNhField.SALE_OPT_OUT);
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.getFieldValue(UsNhField.TARGETED_ADVERTISING_OPT_OUT);
  }

  public FixedIntegerList getSensitiveDataProcessing() {
    return (FixedIntegerList) this.getFieldValue(UsNhField.SENSITIVE_DATA_PROCESSING);
  }

  public FixedIntegerList getKnownChildSensitiveDataConsents() {
    return (FixedIntegerList) this.getFieldValue(UsNhField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
  }

  public Integer getAdditionalDataProcessingConsent() {
    return (Integer) this.getFieldValue(UsNhField.ADDITIONAL_DATA_PROCESSING_CONSENT);
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsNhField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.getFieldValue(UsNhField.MSPA_OPT_OUT_OPTION_MODE);
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.getFieldValue(UsNhField.MSPA_SERVICE_PROVIDER_MODE);
  }

  public Integer getGpcSegmentType() {
    return (Integer) this.getFieldValue(UsNhField.GPC_SEGMENT_TYPE);
  }

  public Boolean getGpc() {
    return (Boolean) this.getFieldValue(UsNhField.GPC);
  }
}
