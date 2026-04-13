package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.datatype.FixedIntegerList;
import com.iab.gpp.encoder.field.UsNatField;
import com.iab.gpp.encoder.segment.Base64Segment;
import com.iab.gpp.encoder.segment.UsNatCoreSegment;

public class UsNat extends AbstractUsSectionWithGpc<UsNatField> {

  public static final int ID = 7;
  public static final int VERSION = 2;
  public static final String NAME = "usnat";

  public UsNat() {
    // NOTE: the core segment has inconsistent decoding due to a version mixup
    super(new UsNatCoreSegment(), new Base64Segment<>(UsNatField.USNAT_GPC_SEGMENT_FIELD_NAMES));
  }

  public UsNat(CharSequence encodedString) {
    this();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsNat.ID;
  }

  @Override
  public String getName() {
    return UsNat.NAME;
  }

  @Override
  public int getVersion() {
    return (Integer) this.getFieldValue(UsNatField.VERSION);
  }

  @Override
  protected final UsNatField getGpcSegmentIncludedKey() {
    return UsNatField.GPC_SEGMENT_INCLUDED;
  }

  public Integer getSharingNotice() {
    return (Integer) this.getFieldValue(UsNatField.SHARING_NOTICE);
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsNatField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getSharingOptOutNotice() {
    return (Integer) this.getFieldValue(UsNatField.SHARING_OPT_OUT_NOTICE);
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.getFieldValue(UsNatField.TARGETED_ADVERTISING_OPT_OUT_NOTICE);
  }

  public Integer getSensitiveDataProcessingOptOutNotice() {
    return (Integer) this.getFieldValue(UsNatField.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE);
  }

  public Integer getSensitiveDataLimitUseNotice() {
    return (Integer) this.getFieldValue(UsNatField.SENSITIVE_DATA_LIMIT_USE_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsNatField.SALE_OPT_OUT);
  }

  public Integer getSharingOptOut() {
    return (Integer) this.getFieldValue(UsNatField.SHARING_OPT_OUT);
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.getFieldValue(UsNatField.TARGETED_ADVERTISING_OPT_OUT);
  }

  public FixedIntegerList getSensitiveDataProcessing() {
    return (FixedIntegerList) this.getFieldValue(UsNatField.SENSITIVE_DATA_PROCESSING);
  }

  public FixedIntegerList getKnownChildSensitiveDataConsents() {
    return (FixedIntegerList) this.getFieldValue(UsNatField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
  }

  public Integer getPersonalDataConsents() {
    return (Integer) this.getFieldValue(UsNatField.PERSONAL_DATA_CONSENTS);
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsNatField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.getFieldValue(UsNatField.MSPA_OPT_OUT_OPTION_MODE);
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.getFieldValue(UsNatField.MSPA_SERVICE_PROVIDER_MODE);
  }

  public Boolean getGpc() {
    return (Boolean) this.getFieldValue(UsNatField.GPC);
  }
}
