package com.iab.gpp.encoder.section;

import java.util.ArrayList;
import java.util.List;
import com.iab.gpp.encoder.datatype.FixedIntegerList;
import com.iab.gpp.encoder.field.UsCaField;
import com.iab.gpp.encoder.segment.UsCaCoreSegment;
import com.iab.gpp.encoder.segment.UsCaGpcSegment;

public class UsCa extends EncodableSection<UsCaField> {

  public static final int ID = 8;
  public static final int VERSION = 1;
  public static final String NAME = "usca";

  public UsCa() {
    super(new UsCaCoreSegment(), new UsCaGpcSegment());
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
    return UsCa.VERSION;
  }

  @Override
  protected void doDecode(CharSequence encodedString) {
    List<CharSequence> encodedSegments = SlicedCharSequence.split(encodedString, '.');
    int numEncodedSegments = encodedSegments.size();

    if (numEncodedSegments > 0) {
      getSegment(0).decode(encodedSegments.get(0));
    }

    if (numEncodedSegments > 1) {
      getSegment(1).setFieldValue(UsCaField.GPC_SEGMENT_INCLUDED, true);
      getSegment(1).decode(encodedSegments.get(1));
    } else {
      getSegment(1).setFieldValue(UsCaField.GPC_SEGMENT_INCLUDED, false);
    }
  }

  @Override
  protected CharSequence doEncode() {
    List<CharSequence> encodedSegments = new ArrayList<>(size());

    encodedSegments.add(getSegment(0).encodeCharSequence());
    if(size() >= 2 && getSegment(1).getFieldValue(UsCaField.GPC_SEGMENT_INCLUDED).equals(true)) {
      encodedSegments.add(getSegment(1).encodeCharSequence());
    }

    return SlicedCharSequence.join('.',  encodedSegments);
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

  public Integer getGpcSegmentType() {
    return (Integer) this.getFieldValue(UsCaField.GPC_SEGMENT_TYPE);
  }

  public Boolean getGpcSegmentIncluded() {
    return (Boolean) this.getFieldValue(UsCaField.GPC_SEGMENT_INCLUDED);
  }

  public Boolean getGpc() {
    return (Boolean) this.getFieldValue(UsCaField.GPC);
  }
}
