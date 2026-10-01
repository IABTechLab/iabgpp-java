package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.datatype.FixedIntegerList;
import com.iab.gpp.encoder.field.UsCtField;
import com.iab.gpp.encoder.segment.Base64Segment;

public class UsCt extends AbstractUsSectionWithGpc<UsCtField> {

  public static final int ID = 12;
  public static final int VERSION = 1;
  public static final String NAME = "usct";

  public UsCt() {
    super(
        new Base64Segment<>(UsCtField.USCT_CORE_SEGMENT_FIELD_NAMES),
        new Base64Segment<>(UsCtField.USCT_GPC_SEGMENT_FIELD_NAMES));
  }

  public UsCt(CharSequence encodedString) {
    this();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsCt.ID;
  }

  @Override
  public String getName() {
    return UsCt.NAME;
  }

  @Override
  public int getVersion() {
    return (Integer) this.getFieldValue(UsCtField.VERSION);
  }

  @Override
  protected final UsCtField getGpcSegmentIncludedKey() {
    return UsCtField.GPC_SEGMENT_INCLUDED;
  }

  public Integer getSharingNotice() {
    return (Integer) this.getFieldValue(UsCtField.SHARING_NOTICE);
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsCtField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.getFieldValue(UsCtField.TARGETED_ADVERTISING_OPT_OUT_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsCtField.SALE_OPT_OUT);
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.getFieldValue(UsCtField.TARGETED_ADVERTISING_OPT_OUT);
  }

  public FixedIntegerList getSensitiveDataProcessing() {
    return (FixedIntegerList) this.getFieldValue(UsCtField.SENSITIVE_DATA_PROCESSING);
  }

  public FixedIntegerList getKnownChildSensitiveDataConsents() {
    return (FixedIntegerList) this.getFieldValue(UsCtField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsCtField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.getFieldValue(UsCtField.MSPA_OPT_OUT_OPTION_MODE);
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.getFieldValue(UsCtField.MSPA_SERVICE_PROVIDER_MODE);
  }

  public Boolean getGpc() {
    return (Boolean) this.getFieldValue(UsCtField.GPC);
  }
}
