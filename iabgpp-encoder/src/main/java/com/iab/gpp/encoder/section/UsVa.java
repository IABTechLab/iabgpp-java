package com.iab.gpp.encoder.section;

import java.util.Collections;
import com.iab.gpp.encoder.datatype.FixedIntegerList;
import com.iab.gpp.encoder.field.UsVaField;
import com.iab.gpp.encoder.segment.UsVaCoreSegment;

public class UsVa extends EncodableSection<UsVaField> {

  public static final int ID = 9;
  public static final int VERSION = 1;
  public static final String NAME = "usva";

  public UsVa() {
    super(Collections.singletonList(new UsVaCoreSegment()));
  }

  public UsVa(CharSequence encodedString) {
    this();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsVa.ID;
  }

  @Override
  public String getName() {
    return UsVa.NAME;
  }

  @Override
  public int getVersion() {
    return UsVa.VERSION;
  }

  public Integer getSharingNotice() {
    return (Integer) this.getFieldValue(UsVaField.SHARING_NOTICE);
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsVaField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.getFieldValue(UsVaField.TARGETED_ADVERTISING_OPT_OUT_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsVaField.SALE_OPT_OUT);
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.getFieldValue(UsVaField.TARGETED_ADVERTISING_OPT_OUT);
  }

  public FixedIntegerList getSensitiveDataProcessing() {
    return (FixedIntegerList) this.getFieldValue(UsVaField.SENSITIVE_DATA_PROCESSING);
  }

  public Integer getKnownChildSensitiveDataConsents() {
    return (Integer) this.getFieldValue(UsVaField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsVaField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.getFieldValue(UsVaField.MSPA_OPT_OUT_OPTION_MODE);
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.getFieldValue(UsVaField.MSPA_SERVICE_PROVIDER_MODE);
  }
}
