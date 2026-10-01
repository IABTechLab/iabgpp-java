package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.datatype.FixedIntegerList;
import com.iab.gpp.encoder.field.UsCaField;
import com.iab.gpp.encoder.segment.Base64Segment;

public class UsCa extends AbstractUsSectionWithGpc<UsCaField> {

  public static final int ID = 8;
  public static final int VERSION = 1;
  public static final String NAME = "usca";

  public UsCa() {
    super(
        new Base64Segment<>(UsCaField.USCA_CORE_SEGMENT_FIELD_NAMES),
        new Base64Segment<>(UsCaField.USCA_GPC_SEGMENT_FIELD_NAMES));
  }

  public UsCa(CharSequence encodedString) {
    this();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsCa.ID;
  }

  @Override
  public String getName() {
    return UsCa.NAME;
  }

  @Override
  public int getVersion() {
    return (Integer) this.getFieldValue(UsCaField.VERSION);
  }

  @Override
  protected final UsCaField getGpcSegmentIncludedKey() {
    return UsCaField.GPC_SEGMENT_INCLUDED;
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsCaField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getSensitiveDataLimitUseNotice() {
    return (Integer) this.getFieldValue(UsCaField.SENSITIVE_DATA_LIMIT_USE_NOTICE);
  }

  public Integer getSharingOptOutNotice() {
    return (Integer) this.getFieldValue(UsCaField.SHARING_OPT_OUT_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsCaField.SALE_OPT_OUT);
  }

  public Integer getSharingOptOut() {
    return (Integer) this.getFieldValue(UsCaField.SHARING_OPT_OUT);
  }

  public FixedIntegerList getSensitiveDataProcessing() {
    return (FixedIntegerList) this.getFieldValue(UsCaField.SENSITIVE_DATA_PROCESSING);
  }

  public FixedIntegerList getKnownChildSensitiveDataConsents() {
    return (FixedIntegerList) this.getFieldValue(UsCaField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
  }

  public Integer getPersonalDataConsents() {
    return (Integer) this.getFieldValue(UsCaField.PERSONAL_DATA_CONSENTS);
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsCaField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.getFieldValue(UsCaField.MSPA_OPT_OUT_OPTION_MODE);
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.getFieldValue(UsCaField.MSPA_SERVICE_PROVIDER_MODE);
  }

  public Boolean getGpc() {
    return (Boolean) this.getFieldValue(UsCaField.GPC);
  }
}
