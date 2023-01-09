package com.iab.gpp.encoder.section;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.datatype.encoder.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.datatype.encoder.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.UspCaV1Field;
import com.iab.gpp.encoder.field.UspCoV1Field;
import com.iab.gpp.encoder.field.UspNatV1Field;
import com.iab.gpp.encoder.field.UspV1Field;

public class UspNatV1 extends AbstractEncodableSegmentedBitStringSection {
  public static int ID = 7;
  public static int VERSION = 1;
  public static String NAME = "uspnatv1";

  private AbstractBase64UrlEncoder base64UrlEncoder = new CompressedBase64UrlEncoder();
  
  public UspNatV1() {
    initFields();
  }

  public UspNatV1(String encodedString) throws DecodingException {
    initFields();

    if (encodedString != null && encodedString.length() > 0) {
      this.decode(encodedString);
    }
  }

  private void initFields() {
    fields = new HashMap<>();

    fields.put(UspNatV1Field.VERSION, new EncodableFixedInteger(6, UspNatV1.VERSION));
    fields.put(UspNatV1Field.SHARING_NOTICE, new EncodableFixedInteger(2, 0));
    fields.put(UspNatV1Field.SALE_OPT_OUT_NOTICE, new EncodableFixedInteger(2, 0));
    fields.put(UspNatV1Field.SHARING_OPT_OUT_NOTICE, new EncodableFixedInteger(2, 0));
    fields.put(UspNatV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, new EncodableFixedInteger(2, 0));
    fields.put(UspNatV1Field.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE, new EncodableFixedInteger(2, 0));
    fields.put(UspNatV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE, new EncodableFixedInteger(2, 0));
    fields.put(UspNatV1Field.SALE_OPT_OUT, new EncodableFixedInteger(2, 0));
    fields.put(UspNatV1Field.SHARING_OPT_OUT, new EncodableFixedInteger(2, 0));
    fields.put(UspNatV1Field.TARGETED_ADVERTISING_OPT_OUT, new EncodableFixedInteger(2, 0));
    fields.put(UspNatV1Field.SENSITIVE_DATA_PROCESSING, new EncodableFixedIntegerList(2, Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)));
    fields.put(UspNatV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, new EncodableFixedIntegerList(2, Arrays.asList(0, 0)));
    fields.put(UspNatV1Field.PERSONAL_DATA_CONSENTS, new EncodableFixedInteger(2, 0));
    fields.put(UspNatV1Field.MSPA_COVERED_TRANSACTION, new EncodableFixedInteger(2, 0));
    fields.put(UspNatV1Field.MSPA_OPT_OUT_OPTION_MODE, new EncodableFixedInteger(2, 0));
    fields.put(UspNatV1Field.MSPA_SERVICE_PROVIDER_MODE, new EncodableFixedInteger(2, 0));

    // gpc segment
    fields.put(UspNatV1Field.GPC_SEGMENT_TYPE, new EncodableFixedInteger(2, 1));
    fields.put(UspNatV1Field.GPC_SEGMENT_INCLUDED, new EncodableBoolean(true));
    fields.put(UspNatV1Field.GPC, new EncodableBoolean(false));


    //@formatter:off
    String[] coreSegment = new String[] {
        UspNatV1Field.VERSION,
        UspNatV1Field.SHARING_NOTICE,
        UspNatV1Field.SALE_OPT_OUT_NOTICE,
        UspNatV1Field.SHARING_OPT_OUT_NOTICE,
        UspNatV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
        UspNatV1Field.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE,
        UspNatV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE,
        UspNatV1Field.SALE_OPT_OUT,
        UspNatV1Field.SHARING_OPT_OUT,
        UspNatV1Field.TARGETED_ADVERTISING_OPT_OUT,
        UspNatV1Field.SENSITIVE_DATA_PROCESSING,
        UspNatV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
        UspNatV1Field.PERSONAL_DATA_CONSENTS,
        UspNatV1Field.MSPA_COVERED_TRANSACTION,
        UspNatV1Field.MSPA_OPT_OUT_OPTION_MODE,
        UspNatV1Field.MSPA_SERVICE_PROVIDER_MODE
    };
    
    String[] gpcSegment = new String[] {
        UspNatV1Field.GPC_SEGMENT_TYPE,
        UspNatV1Field.GPC
    };
    
    segments = new String[][] {
      coreSegment, 
      gpcSegment
    };
    //@formatter:on
  }

  @Override
  public String encode() throws EncodingException {
    List<String> segmentBitStrings = this.encodeSegmentsToBitStrings();
    List<String> encodedSegments = new ArrayList<>();
    if (segmentBitStrings.size() >= 1) {
      encodedSegments.add(base64UrlEncoder.encode(segmentBitStrings.get(0)));

      if (segmentBitStrings.size() >= 2) {
        Boolean gpcSegmentIncluded = (Boolean)this.fields.get(UspNatV1Field.GPC_SEGMENT_INCLUDED).getValue();
        if(gpcSegmentIncluded) {
          encodedSegments.add(base64UrlEncoder.encode(segmentBitStrings.get(1)));
        }
      }
    }

    return encodedSegments.stream().collect(Collectors.joining("."));
  }

  @Override
  public void decode(String encodedSection) throws DecodingException {
    String[] encodedSegments = encodedSection.split("\\.");
    String[] segmentBitStrings = new String[2];
    boolean gpcSegmentIncluded = false;
    for (int i = 0; i < encodedSegments.length; i++) {
      /**
       * first char will contain 6 bits, we only need the first 2. 
       * There is no segment type for the CORE string. Instead the first 6 bits are reserved for the
       * encoding version, but because we're only on a maximum of encoding version 2 the first 2 bits in
       * the core segment will evaluate to 0.
       */
      String segmentBitString = base64UrlEncoder.decode(encodedSegments[i]);
      switch (segmentBitString.substring(0, 2)) {
        case "00": {
          segmentBitStrings[0] = segmentBitString;
          break;
        }
        case "01": {
          gpcSegmentIncluded = true;
          segmentBitStrings[1] = segmentBitString;
          break;
        }
        default: {
          throw new DecodingException("Unable to decode segment '" + encodedSegments[i] + "'");
        }
      }
    }
    this.decodeSegmentsFromBitStrings(Arrays.asList(segmentBitStrings));
    this.fields.get(UspCaV1Field.GPC_SEGMENT_INCLUDED).setValue(gpcSegmentIncluded);
  }

  @Override
  public int getId() {
    return UspNatV1.ID;
  }

  @Override
  public String getName() {
    return UspNatV1.NAME;
  }

  public Integer getVersion() {
    return (Integer) this.fields.get(UspV1Field.VERSION).getValue();
  }

  public Integer getSharingNotice() {
    return (Integer) this.fields.get(UspNatV1Field.SHARING_NOTICE).getValue();
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.fields.get(UspNatV1Field.SALE_OPT_OUT_NOTICE).getValue();
  }

  public Integer getSharingOptOutNotice() {
    return (Integer) this.fields.get(UspNatV1Field.SHARING_OPT_OUT_NOTICE).getValue();
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.fields.get(UspNatV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE).getValue();
  }

  public Integer getSensitiveDataProcessingOptOutNotice() {
    return (Integer) this.fields.get(UspNatV1Field.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE).getValue();
  }

  public Integer getSensitiveDataLimitUseNotice() {
    return (Integer) this.fields.get(UspNatV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE).getValue();
  }

  public Integer getSaleOptOut() {
    return (Integer) this.fields.get(UspNatV1Field.SALE_OPT_OUT).getValue();
  }

  public Integer getSharingOptOut() {
    return (Integer) this.fields.get(UspNatV1Field.SHARING_OPT_OUT).getValue();
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.fields.get(UspNatV1Field.TARGETED_ADVERTISING_OPT_OUT).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getSensitiveDataProcessing() {
    return (List<Integer>) this.fields.get(UspNatV1Field.SENSITIVE_DATA_PROCESSING).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getKnownChildSensitiveDataConsents() {
    return (List<Integer>) this.fields.get(UspNatV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS).getValue();
  }

  public Integer getPersonalDataConsents() {
    return (Integer) this.fields.get(UspNatV1Field.PERSONAL_DATA_CONSENTS).getValue();
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.fields.get(UspNatV1Field.MSPA_COVERED_TRANSACTION).getValue();
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.fields.get(UspNatV1Field.MSPA_OPT_OUT_OPTION_MODE).getValue();
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.fields.get(UspNatV1Field.MSPA_SERVICE_PROVIDER_MODE).getValue();
  }

  public Boolean getGpcSegmentType() {
    return (Boolean) this.fields.get(UspNatV1Field.GPC_SEGMENT_TYPE).getValue();
  }

  public Boolean getGpcSegmentIncluded() {
    return (Boolean) this.fields.get(UspNatV1Field.GPC_SEGMENT_INCLUDED).getValue();
  }
  
  public Boolean getGpc() {
    return (Boolean) this.fields.get(UspNatV1Field.GPC).getValue();
  }
}
