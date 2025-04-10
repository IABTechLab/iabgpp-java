package com.iab.gpp.encoder.section;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.iab.gpp.encoder.field.UsNatField;
import com.iab.gpp.encoder.segment.EncodableSegment;
import com.iab.gpp.encoder.segment.UsNatCoreSegment;
import com.iab.gpp.encoder.segment.UsNatGpcSegment;

public class UsNat extends AbstractLazilyEncodableSection {

  public static final int ID = 7;
  public static final int VERSION = 1;
  public static final String NAME = "usnat";

  public UsNat() {
    super();
  }

  public UsNat(CharSequence encodedString) {
    super();
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
    return UsNat.VERSION;
  }

  @Override
  protected List<EncodableSegment> initializeSegments() {
    return Arrays.asList(new UsNatCoreSegment(), new UsNatGpcSegment());
  }

  @Override
  protected List<EncodableSegment> decodeSection(CharSequence encodedString) {
    if (encodedString != null && encodedString.length() > 0) {
      List<CharSequence> encodedSegments = SlicedCharSequence.split(encodedString, '.');

      if (encodedSegments.size() > 0) {
        segments.get(0).decode(encodedSegments.get(0));
      }

      if (encodedSegments.size() > 1) {
        segments.get(1).setFieldValue(UsNatField.GPC_SEGMENT_INCLUDED, true);
        segments.get(1).decode(encodedSegments.get(1));
      } else {
        segments.get(1).setFieldValue(UsNatField.GPC_SEGMENT_INCLUDED, false);
      }
    }

    return segments;
  }

  @Override
  protected CharSequence encodeSection(List<EncodableSegment> segments) {
    List<CharSequence> encodedSegments = new ArrayList<>(segments.size());

    if(!segments.isEmpty()) {
      encodedSegments.add(segments.get(0).encodeCharSequence());
      if(segments.size() >= 2 && segments.get(1).getFieldValue(UsNatField.GPC_SEGMENT_INCLUDED).equals(true)) {
        encodedSegments.add(segments.get(1).encodeCharSequence());
      }
    }

    return SlicedCharSequence.join('.',  encodedSegments);
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

  @SuppressWarnings("unchecked")
  public List<Integer> getSensitiveDataProcessing() {
    return (List<Integer>) this.getFieldValue(UsNatField.SENSITIVE_DATA_PROCESSING);
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getKnownChildSensitiveDataConsents() {
    return (List<Integer>) this.getFieldValue(UsNatField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS);
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

  public Integer getGpcSegmentType() {
    return (Integer) this.getFieldValue(UsNatField.GPC_SEGMENT_TYPE);
  }

  public Boolean getGpcSegmentIncluded() {
    return (Boolean) this.getFieldValue(UsNatField.GPC_SEGMENT_INCLUDED);
  }

  public Boolean getGpc() {
    return (Boolean) this.getFieldValue(UsNatField.GPC);
  }
}
