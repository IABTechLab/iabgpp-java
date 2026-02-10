package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.datatype.FixedIntegerList;
import com.iab.gpp.encoder.field.UsTnField;
import com.iab.gpp.encoder.segment.Base64Segment;

public class UsTn extends AbstractUsSectionWithGpc<UsTnField> {

  public static final int ID = 22;
  public static final int VERSION = 1;
  public static final String NAME = "ustn";

  public UsTn() {
    super(new Base64Segment<>(UsTnField.USTN_CORE_SEGMENT_FIELD_NAMES), new Base64Segment<>(UsTnField.USTN_GPC_SEGMENT_FIELD_NAMES));
  }

  public UsTn(CharSequence encodedString) {
    this();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsTn.ID;
  }

  @Override
  public String getName() {
    return UsTn.NAME;
  }

  @Override
  public int getVersion() {
    return UsTn.VERSION;
  }

  @Override
  protected final UsTnField getGpcSegmentIncludedKey() {
    return UsTnField.GPC_SEGMENT_INCLUDED;
  }

  public Integer getProcessingNotice() {
    return (Integer) this.getFieldValue(UsTnField.PROCESSING_NOTICE);
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsTnField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.getFieldValue(UsTnField.TARGETED_ADVERTISING_OPT_OUT_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsTnField.SALE_OPT_OUT);
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.getFieldValue(UsTnField.TARGETED_ADVERTISING_OPT_OUT);
  }

  public FixedIntegerList getSensitiveDataProcessing() {
    return (FixedIntegerList) this.getFieldValue(UsTnField.SENSITIVE_DATA_PROCESSING);
  }

  public Integer getKnownChildSensitiveDataConsents() {
    return (Integer) this.getFieldValue(UsTnField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
  }

  public Integer getAdditionalDataProcessingConsent() {
    return (Integer) this.getFieldValue(UsTnField.ADDITIONAL_DATA_PROCESSING_CONSENT);
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsTnField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.getFieldValue(UsTnField.MSPA_OPT_OUT_OPTION_MODE);
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.getFieldValue(UsTnField.MSPA_SERVICE_PROVIDER_MODE);
  }

  public Integer getGpcSegmentType() {
    return (Integer) this.getFieldValue(UsTnField.GPC_SEGMENT_TYPE);
  }

  public Boolean getGpc() {
    return (Boolean) this.getFieldValue(UsTnField.GPC);
  }
}
