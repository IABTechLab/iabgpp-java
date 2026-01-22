package com.iab.gpp.encoder.section;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import com.iab.gpp.encoder.datatype.FixedIntegerList;
import com.iab.gpp.encoder.field.UsMtField;
import com.iab.gpp.encoder.segment.EncodableSegment;
import com.iab.gpp.encoder.segment.UsMtCoreSegment;
import com.iab.gpp.encoder.segment.UsMtGpcSegment;

public class UsMt extends AbstractLazilyEncodableSection<UsMtField> {

  public static final int ID = 14;
  public static final int VERSION = 1;
  public static final String NAME = "usmt";

  public UsMt() {
    super(Arrays.<EncodableSegment<UsMtField>>asList(new UsMtCoreSegment(), new UsMtGpcSegment()));
  }

  public UsMt(CharSequence encodedString) {
    this();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return UsMt.ID;
  }

  @Override
  public String getName() {
    return UsMt.NAME;
  }

  @Override
  public int getVersion() {
    return UsMt.VERSION;
  }

  @Override
  protected void decodeSection(CharSequence encodedString) {
    List<CharSequence> encodedSegments = SlicedCharSequence.split(encodedString, '.');

    if (encodedSegments.size() > 0) {
      segments.get(0).decode(encodedSegments.get(0));
    }

    if (encodedSegments.size() > 1) {
      segments.get(1).setFieldValue(UsMtField.GPC_SEGMENT_INCLUDED, true);
      segments.get(1).decode(encodedSegments.get(1));
    } else {
      segments.get(1).setFieldValue(UsMtField.GPC_SEGMENT_INCLUDED, false);
    }
  }

  @Override
  protected CharSequence encodeSection() {
    List<CharSequence> encodedSegments = new ArrayList<>(segments.size());

    encodedSegments.add(segments.get(0).encodeCharSequence());
    if(segments.size() >= 2 && segments.get(1).getFieldValue(UsMtField.GPC_SEGMENT_INCLUDED).equals(true)) {
      encodedSegments.add(segments.get(1).encodeCharSequence());
    }

    return SlicedCharSequence.join('.',  encodedSegments);
  }


  public Integer getSharingNotice() {
    return (Integer) this.getFieldValue(UsMtField.SHARING_NOTICE);
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.getFieldValue(UsMtField.SALE_OPT_OUT_NOTICE);
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.getFieldValue(UsMtField.TARGETED_ADVERTISING_OPT_OUT_NOTICE);
  }

  public Integer getSaleOptOut() {
    return (Integer) this.getFieldValue(UsMtField.SALE_OPT_OUT);
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.getFieldValue(UsMtField.TARGETED_ADVERTISING_OPT_OUT);
  }

  public FixedIntegerList getSensitiveDataProcessing() {
    return (FixedIntegerList) this.getFieldValue(UsMtField.SENSITIVE_DATA_PROCESSING);
  }

  public FixedIntegerList getKnownChildSensitiveDataConsents() {
    return (FixedIntegerList) this.getFieldValue(UsMtField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
  }

  public Integer getAdditionalDataProcessingConsent() {
    return (Integer) this.getFieldValue(UsMtField.ADDITIONAL_DATA_PROCESSING_CONSENT);
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.getFieldValue(UsMtField.MSPA_COVERED_TRANSACTION);
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.getFieldValue(UsMtField.MSPA_OPT_OUT_OPTION_MODE);
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.getFieldValue(UsMtField.MSPA_SERVICE_PROVIDER_MODE);
  }

  public Integer getGpcSegmentType() {
    return (Integer) this.getFieldValue(UsMtField.GPC_SEGMENT_TYPE);
  }

  public Boolean getGpcSegmentIncluded() {
    return (Boolean) this.getFieldValue(UsMtField.GPC_SEGMENT_INCLUDED);
  }

  public Boolean getGpc() {
    return (Boolean) this.getFieldValue(UsMtField.GPC);
  }
}
