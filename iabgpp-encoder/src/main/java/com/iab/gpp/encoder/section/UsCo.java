package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.datatype.FixedIntegerList;
import com.iab.gpp.encoder.field.UsCoField;
import com.iab.gpp.encoder.segment.Base64Segment;

public class UsCo extends AbstractUsSectionWithGpc<UsCoField> {

  public static final int ID = 10;
  public static final int VERSION = 1;
  public static final String NAME = "usco";

  public UsCo() {
    super(
        new Base64Segment<>(UsCoField.USCO_CORE_SEGMENT_FIELD_NAMES),
        new Base64Segment<>(UsCoField.USCO_GPC_SEGMENT_FIELD_NAMES));
  }

  public UsCo(CharSequence encodedString) {
    this();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsCo.ID;
  }

  @Override
  public String getName() {
    return UsCo.NAME;
  }

  @Override
  public int getVersion() {
    return (Integer) this.getFieldValue(UsCoField.VERSION);
  }

  @Override
  protected final UsCoField getGpcSegmentIncludedKey() {
    return UsCoField.GPC_SEGMENT_INCLUDED;
  }

  public Integer getSharingNotice() {
    return (Integer) this.getFieldValue(UsCoField.SHARING_NOTICE);
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsCoField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.getFieldValue(UsCoField.TARGETED_ADVERTISING_OPT_OUT_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsCoField.SALE_OPT_OUT);
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.getFieldValue(UsCoField.TARGETED_ADVERTISING_OPT_OUT);
  }

  public FixedIntegerList getSensitiveDataProcessing() {
    return (FixedIntegerList) this.getFieldValue(UsCoField.SENSITIVE_DATA_PROCESSING);
  }

  public Integer getKnownChildSensitiveDataConsents() {
    return (Integer) this.getFieldValue(UsCoField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsCoField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.getFieldValue(UsCoField.MSPA_OPT_OUT_OPTION_MODE);
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.getFieldValue(UsCoField.MSPA_SERVICE_PROVIDER_MODE);
  }

  public Boolean getGpc() {
    return (Boolean) this.getFieldValue(UsCoField.GPC);
  }
}
