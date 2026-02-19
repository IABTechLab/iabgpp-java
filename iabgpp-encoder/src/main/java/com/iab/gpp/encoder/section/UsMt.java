package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.datatype.FixedIntegerList;
import com.iab.gpp.encoder.field.UsMtField;
import com.iab.gpp.encoder.segment.Base64Segment;

public class UsMt extends AbstractUsSectionWithGpc<UsMtField> {

  public static final int ID = 14;
  public static final int VERSION = 1;
  public static final String NAME = "usmt";

  public UsMt() {
    super(
        new Base64Segment<>(UsMtField.USMT_CORE_SEGMENT_FIELD_NAMES),
        new Base64Segment<>(UsMtField.USMT_GPC_SEGMENT_FIELD_NAMES));
  }

  public UsMt(CharSequence encodedString) {
    this();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsMt.ID;
  }

  @Override
  public String getName() {
    return UsMt.NAME;
  }

  @Override
  public int getVersion() {
    return UsMt.VERSION;
  }

  @Override
  protected final UsMtField getGpcSegmentIncludedKey() {
    return UsMtField.GPC_SEGMENT_INCLUDED;
  }

  public Integer getSharingNotice() {
    return (Integer) this.getFieldValue(UsMtField.SHARING_NOTICE);
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsMtField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.getFieldValue(UsMtField.TARGETED_ADVERTISING_OPT_OUT_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsMtField.SALE_OPT_OUT);
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.getFieldValue(UsMtField.TARGETED_ADVERTISING_OPT_OUT);
  }

  public FixedIntegerList getSensitiveDataProcessing() {
    return (FixedIntegerList) this.getFieldValue(UsMtField.SENSITIVE_DATA_PROCESSING);
  }

  public FixedIntegerList getKnownChildSensitiveDataConsents() {
    return (FixedIntegerList) this.getFieldValue(UsMtField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
  }

  public Integer getAdditionalDataProcessingConsent() {
    return (Integer) this.getFieldValue(UsMtField.ADDITIONAL_DATA_PROCESSING_CONSENT);
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsMtField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.getFieldValue(UsMtField.MSPA_OPT_OUT_OPTION_MODE);
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.getFieldValue(UsMtField.MSPA_SERVICE_PROVIDER_MODE);
  }

  public Integer getGpcSegmentType() {
    return (Integer) this.getFieldValue(UsMtField.GPC_SEGMENT_TYPE);
  }

  public Boolean getGpc() {
    return (Boolean) this.getFieldValue(UsMtField.GPC);
  }
}
