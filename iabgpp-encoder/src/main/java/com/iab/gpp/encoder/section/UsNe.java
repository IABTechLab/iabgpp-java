package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.datatype.FixedIntegerList;
import com.iab.gpp.encoder.field.UsNeField;
import com.iab.gpp.encoder.segment.Base64Segment;

public class UsNe extends AbstractUsSectionWithGpc<UsNeField> {

  public static final int ID = 19;
  public static final int VERSION = 1;
  public static final String NAME = "usne";

  public UsNe() {
    super(
        new Base64Segment<>(UsNeField.USNE_CORE_SEGMENT_FIELD_NAMES),
        new Base64Segment<>(UsNeField.USNE_GPC_SEGMENT_FIELD_NAMES));
  }

  public UsNe(CharSequence encodedString) {
    this();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsNe.ID;
  }

  @Override
  public String getName() {
    return UsNe.NAME;
  }

  @Override
  public int getVersion() {
    return (Integer) this.getFieldValue(UsNeField.VERSION);
  }

  @Override
  protected final UsNeField getGpcSegmentIncludedKey() {
    return UsNeField.GPC_SEGMENT_INCLUDED;
  }

  public Integer getProcessingNotice() {
    return (Integer) this.getFieldValue(UsNeField.PROCESSING_NOTICE);
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsNeField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.getFieldValue(UsNeField.TARGETED_ADVERTISING_OPT_OUT_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsNeField.SALE_OPT_OUT);
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.getFieldValue(UsNeField.TARGETED_ADVERTISING_OPT_OUT);
  }

  public FixedIntegerList getSensitiveDataProcessing() {
    return (FixedIntegerList) this.getFieldValue(UsNeField.SENSITIVE_DATA_PROCESSING);
  }

  public Integer getKnownChildSensitiveDataConsents() {
    return (Integer) this.getFieldValue(UsNeField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
  }

  public Integer getAdditionalDataProcessingConsent() {
    return (Integer) this.getFieldValue(UsNeField.ADDITIONAL_DATA_PROCESSING_CONSENT);
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsNeField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.getFieldValue(UsNeField.MSPA_OPT_OUT_OPTION_MODE);
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.getFieldValue(UsNeField.MSPA_SERVICE_PROVIDER_MODE);
  }

  public Boolean getGpc() {
    return (Boolean) this.getFieldValue(UsNeField.GPC);
  }
}
