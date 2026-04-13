package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.datatype.FixedIntegerList;
import com.iab.gpp.encoder.field.UsIaField;
import com.iab.gpp.encoder.segment.Base64Segment;

public class UsIa extends AbstractUsSectionWithGpc<UsIaField> {

  public static final int ID = 18;
  public static final int VERSION = 1;
  public static final String NAME = "usia";

  public UsIa() {
    super(
        new Base64Segment<>(UsIaField.USIA_CORE_SEGMENT_FIELD_NAMES),
        new Base64Segment<>(UsIaField.USIA_GPC_SEGMENT_FIELD_NAMES));
  }

  public UsIa(CharSequence encodedString) {
    this();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsIa.ID;
  }

  @Override
  public String getName() {
    return UsIa.NAME;
  }

  @Override
  public int getVersion() {
    return (Integer) this.getFieldValue(UsIaField.VERSION);
  }

  @Override
  protected final UsIaField getGpcSegmentIncludedKey() {
    return UsIaField.GPC_SEGMENT_INCLUDED;
  }

  public Integer getProcessingNotice() {
    return (Integer) this.getFieldValue(UsIaField.PROCESSING_NOTICE);
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsIaField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.getFieldValue(UsIaField.TARGETED_ADVERTISING_OPT_OUT_NOTICE);
  }

  public Integer getSensitiveDataOptOutNotice() {
    return (Integer) this.getFieldValue(UsIaField.SENSITIVE_DATA_OPT_OUT_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsIaField.SALE_OPT_OUT);
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.getFieldValue(UsIaField.TARGETED_ADVERTISING_OPT_OUT);
  }

  public FixedIntegerList getSensitiveDataProcessing() {
    return (FixedIntegerList) this.getFieldValue(UsIaField.SENSITIVE_DATA_PROCESSING);
  }

  public Integer getKnownChildSensitiveDataConsents() {
    return (Integer) this.getFieldValue(UsIaField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsIaField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.getFieldValue(UsIaField.MSPA_OPT_OUT_OPTION_MODE);
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.getFieldValue(UsIaField.MSPA_SERVICE_PROVIDER_MODE);
  }

  public Boolean getGpc() {
    return (Boolean) this.getFieldValue(UsIaField.GPC);
  }
}
