package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.datatype.FixedIntegerList;
import com.iab.gpp.encoder.field.UsTxField;
import com.iab.gpp.encoder.segment.Base64Segment;

public class UsTx extends AbstractUsSectionWithGpc<UsTxField> {

  public static final int ID = 16;
  public static final int VERSION = 1;
  public static final String NAME = "ustx";

  public UsTx() {
    super(
        new Base64Segment<>(UsTxField.USTX_CORE_SEGMENT_FIELD_NAMES),
        new Base64Segment<>(UsTxField.USTX_GPC_SEGMENT_FIELD_NAMES));
  }

  public UsTx(CharSequence encodedString) {
    this();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsTx.ID;
  }

  @Override
  public String getName() {
    return UsTx.NAME;
  }

  @Override
  public int getVersion() {
    return UsTx.VERSION;
  }

  @Override
  protected final UsTxField getGpcSegmentIncludedKey() {
    return UsTxField.GPC_SEGMENT_INCLUDED;
  }

  public Integer getProcessingNotice() {
    return (Integer) this.getFieldValue(UsTxField.PROCESSING_NOTICE);
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsTxField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.getFieldValue(UsTxField.TARGETED_ADVERTISING_OPT_OUT_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsTxField.SALE_OPT_OUT);
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.getFieldValue(UsTxField.TARGETED_ADVERTISING_OPT_OUT);
  }

  public FixedIntegerList getSensitiveDataProcessing() {
    return (FixedIntegerList) this.getFieldValue(UsTxField.SENSITIVE_DATA_PROCESSING);
  }

  public Integer getKnownChildSensitiveDataConsents() {
    return (Integer) this.getFieldValue(UsTxField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
  }

  public Integer getAdditionalDataProcessingConsent() {
    return (Integer) this.getFieldValue(UsTxField.ADDITIONAL_DATA_PROCESSING_CONSENT);
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsTxField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.getFieldValue(UsTxField.MSPA_OPT_OUT_OPTION_MODE);
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.getFieldValue(UsTxField.MSPA_SERVICE_PROVIDER_MODE);
  }

  public Integer getGpcSegmentType() {
    return (Integer) this.getFieldValue(UsTxField.GPC_SEGMENT_TYPE);
  }

  public Boolean getGpc() {
    return (Boolean) this.getFieldValue(UsTxField.GPC);
  }
}
