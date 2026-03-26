package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.datatype.FixedIntegerList;
import com.iab.gpp.encoder.field.UsMnField;
import com.iab.gpp.encoder.segment.Base64Segment;

public class UsMn extends AbstractUsSectionWithGpc<UsMnField> {

  public static final int ID = 23;
  public static final int VERSION = 1;
  public static final String NAME = "usmn";

  public UsMn() {
    super(
        new Base64Segment<>(UsMnField.USMN_CORE_SEGMENT_FIELD_NAMES),
        new Base64Segment<>(UsMnField.USMN_GPC_SEGMENT_FIELD_NAMES));
  }

  public UsMn(String encodedString) {
    this();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsMn.ID;
  }

  @Override
  public String getName() {
    return UsMn.NAME;
  }

  @Override
  public int getVersion() {
    return (Integer) this.getFieldValue(UsMnField.VERSION);
  }

  @Override
  protected final UsMnField getGpcSegmentIncludedKey() {
    return UsMnField.GPC_SEGMENT_INCLUDED;
  }

  public Integer getProcessingNotice() {
    return (Integer) this.getFieldValue(UsMnField.PROCESSING_NOTICE);
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsMnField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.getFieldValue(UsMnField.TARGETED_ADVERTISING_OPT_OUT_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsMnField.SALE_OPT_OUT);
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.getFieldValue(UsMnField.TARGETED_ADVERTISING_OPT_OUT);
  }

  public FixedIntegerList getSensitiveDataProcessing() {
    return (FixedIntegerList) this.getFieldValue(UsMnField.SENSITIVE_DATA_PROCESSING);
  }

  public Integer getKnownChildSensitiveDataConsents() {
    return (Integer) this.getFieldValue(UsMnField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
  }

  public Integer getAdditionalDataProcessingConsent() {
    return (Integer) this.getFieldValue(UsMnField.ADDITIONAL_DATA_PROCESSING_CONSENT);
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsMnField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.getFieldValue(UsMnField.MSPA_OPT_OUT_OPTION_MODE);
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.getFieldValue(UsMnField.MSPA_SERVICE_PROVIDER_MODE);
  }

  public Boolean getGpc() {
    return (Boolean) this.getFieldValue(UsMnField.GPC);
  }
}
