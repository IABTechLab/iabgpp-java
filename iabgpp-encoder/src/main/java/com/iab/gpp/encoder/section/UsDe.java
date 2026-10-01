package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.datatype.FixedIntegerList;
import com.iab.gpp.encoder.field.UsDeField;
import com.iab.gpp.encoder.segment.Base64Segment;

public class UsDe extends AbstractUsSectionWithGpc<UsDeField> {

  public static final int ID = 17;
  public static final int VERSION = 1;
  public static final String NAME = "usde";

  public UsDe() {
    super(
        new Base64Segment<>(UsDeField.USDE_CORE_SEGMENT_FIELD_NAMES),
        new Base64Segment<>(UsDeField.USDE_GPC_SEGMENT_FIELD_NAMES));
  }

  public UsDe(CharSequence encodedString) {
    this();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsDe.ID;
  }

  @Override
  public String getName() {
    return UsDe.NAME;
  }

  @Override
  public int getVersion() {
    return (Integer) this.getFieldValue(UsDeField.VERSION);
  }

  @Override
  protected final UsDeField getGpcSegmentIncludedKey() {
    return UsDeField.GPC_SEGMENT_INCLUDED;
  }

  public Integer getProcessingNotice() {
    return (Integer) this.getFieldValue(UsDeField.PROCESSING_NOTICE);
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsDeField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.getFieldValue(UsDeField.TARGETED_ADVERTISING_OPT_OUT_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsDeField.SALE_OPT_OUT);
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.getFieldValue(UsDeField.TARGETED_ADVERTISING_OPT_OUT);
  }

  public FixedIntegerList getSensitiveDataProcessing() {
    return (FixedIntegerList) this.getFieldValue(UsDeField.SENSITIVE_DATA_PROCESSING);
  }

  public FixedIntegerList getKnownChildSensitiveDataConsents() {
    return (FixedIntegerList) this.getFieldValue(UsDeField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
  }

  public Integer getAdditionalDataProcessingConsent() {
    return (Integer) this.getFieldValue(UsDeField.ADDITIONAL_DATA_PROCESSING_CONSENT);
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsDeField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.getFieldValue(UsDeField.MSPA_OPT_OUT_OPTION_MODE);
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.getFieldValue(UsDeField.MSPA_SERVICE_PROVIDER_MODE);
  }

  public Boolean getGpc() {
    return (Boolean) this.getFieldValue(UsDeField.GPC);
  }
}
