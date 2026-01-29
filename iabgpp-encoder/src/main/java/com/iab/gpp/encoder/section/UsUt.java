package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.datatype.FixedIntegerList;
import com.iab.gpp.encoder.field.UsUtField;
import com.iab.gpp.encoder.segment.UsUtCoreSegment;

public class UsUt extends EncodableSection<UsUtField> {

  public static final int ID = 11;
  public static final int VERSION = 1;
  public static final String NAME = "usut";

  public UsUt() {
    super(new UsUtCoreSegment());
  }

  public UsUt(CharSequence encodedString) {
    this();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsUt.ID;
  }

  @Override
  public String getName() {
    return UsUt.NAME;
  }

  @Override
  public int getVersion() {
    return UsUt.VERSION;
  }

  public Integer getSharingNotice() {
    return (Integer) this.getFieldValue(UsUtField.SHARING_NOTICE);
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsUtField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.getFieldValue(UsUtField.TARGETED_ADVERTISING_OPT_OUT_NOTICE);
  }

  public Integer getSensitiveDataProcessingOptOutNotice() {
    return (Integer) this.getFieldValue(UsUtField.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsUtField.SALE_OPT_OUT);
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.getFieldValue(UsUtField.TARGETED_ADVERTISING_OPT_OUT);
  }

  public FixedIntegerList getSensitiveDataProcessing() {
    return (FixedIntegerList) this.getFieldValue(UsUtField.SENSITIVE_DATA_PROCESSING);
  }

  public Integer getKnownChildSensitiveDataConsents() {
    return (Integer) this.getFieldValue(UsUtField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsUtField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.getFieldValue(UsUtField.MSPA_OPT_OUT_OPTION_MODE);
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.getFieldValue(UsUtField.MSPA_SERVICE_PROVIDER_MODE);
  }


}
