package com.iab.gpp.encoder.section;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.datatype.encoder.Base64UrlEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.UspCoV1Field;
import com.iab.gpp.encoder.field.UspV1Field;

public class UspCoV1 extends AbstractEncodableSegmentedBitStringSection {
  public static int ID = 10;
  public static int VERSION = 1;
  public static String NAME = "uspco";

  public UspCoV1() {
    initFields();
  }

  public UspCoV1(String encodedString) throws DecodingException {
    initFields();

    if (encodedString != null && encodedString.length() > 0) {
      this.decode(encodedString);
    }
  }

  private void initFields() {
    fields = new HashMap<>();

    fields.put(UspCoV1Field.VERSION, new EncodableFixedInteger(6, UspCoV1.VERSION));
    fields.put(UspCoV1Field.SHARING_NOTICE, new EncodableFixedInteger(2, 0));
    fields.put(UspCoV1Field.SALE_OPT_OUT_NOTICE, new EncodableFixedInteger(2, 0));
    fields.put(UspCoV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, new EncodableFixedInteger(2, 0));
    fields.put(UspCoV1Field.SALE_OPT_OUT, new EncodableFixedInteger(2, 0));
    fields.put(UspCoV1Field.TARGETED_ADVERTISING_OPT_OUT, new EncodableFixedInteger(2, 0));
    fields.put(UspCoV1Field.SENSITIVE_DATA_PROCESSING, new EncodableFixedIntegerList(2, Arrays.asList(0, 0, 0, 0, 0, 0, 0)));
    fields.put(UspCoV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, new EncodableFixedInteger(2, 0));
    fields.put(UspCoV1Field.MSPA_COVERED_TRANSACTION, new EncodableFixedInteger(2, 0));
    fields.put(UspCoV1Field.MSPA_OPT_OUT_OPTION_MODE, new EncodableFixedInteger(2, 0));
    fields.put(UspCoV1Field.MSPA_SERVICE_PROVIDER_MODE, new EncodableFixedInteger(2, 0));

    // gpc segment
    fields.put(UspCoV1Field.GPC_SEGMENT_TYPE, new EncodableFixedInteger(2, 1));
    fields.put(UspCoV1Field.GPC, new EncodableBoolean(false));


    //@formatter:off
    String[] coreSegment = new String[] {
        UspCoV1Field.VERSION,
        UspCoV1Field.SHARING_NOTICE,
        UspCoV1Field.SALE_OPT_OUT_NOTICE,
        UspCoV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
        UspCoV1Field.SALE_OPT_OUT,
        UspCoV1Field.TARGETED_ADVERTISING_OPT_OUT,
        UspCoV1Field.SENSITIVE_DATA_PROCESSING,
        UspCoV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
        UspCoV1Field.MSPA_COVERED_TRANSACTION,
        UspCoV1Field.MSPA_OPT_OUT_OPTION_MODE,
        UspCoV1Field.MSPA_SERVICE_PROVIDER_MODE
    };
    
    String[] gpcSegment = new String[] {
        UspCoV1Field.GPC_SEGMENT_TYPE,
        UspCoV1Field.GPC
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
      encodedSegments.add(Base64UrlEncoder.encode(segmentBitStrings.get(0)));

      if (segmentBitStrings.size() >= 2) {
        encodedSegments.add(Base64UrlEncoder.encode(segmentBitStrings.get(1)));
      }
    }

    return encodedSegments.stream().collect(Collectors.joining("."));
  }

  @Override
  public void decode(String encodedSection) throws DecodingException {
    String[] encodedSegments = encodedSection.split("\\.");
    String[] segmentBitStrings = new String[2];
    for (int i = 0; i < encodedSegments.length; i++) {
      /**
       * first char will contain 6 bits, we only need the first 2. 
       * There is no segment type for the CORE string. Instead the first 6 bits are reserved for the
       * encoding version, but because we're only on a maximum of encoding version 2 the first 2 bits in
       * the core segment will evaluate to 0.
       */
      String segmentBitString = Base64UrlEncoder.decode(encodedSegments[i]);
      switch (segmentBitString.substring(0, 2)) {
        case "00": {
          segmentBitStrings[0] = segmentBitString;
          break;
        }
        case "01": {
          segmentBitStrings[1] = segmentBitString;
          break;
        }
        default: {
          throw new DecodingException("Unable to decode segment '" + encodedSegments[i] + "'");
        }
      }
    }
    this.decodeSegmentsFromBitStrings(Arrays.asList(segmentBitStrings));
  }

  @Override
  public int getId() {
    return UspCoV1.ID;
  }

  @Override
  public String getName() {
    return UspCoV1.NAME;
  }

  public Integer getVersion() {
    return (Integer) this.fields.get(UspV1Field.VERSION).getValue();
  }

  public Integer getSharingNotice() {
    return (Integer) this.fields.get(UspCoV1Field.SHARING_NOTICE).getValue();
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.fields.get(UspCoV1Field.SALE_OPT_OUT_NOTICE).getValue();
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.fields.get(UspCoV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE).getValue();
  }

  public Integer getSaleOptOut() {
    return (Integer) this.fields.get(UspCoV1Field.SALE_OPT_OUT).getValue();
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.fields.get(UspCoV1Field.TARGETED_ADVERTISING_OPT_OUT).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getSensitiveDataProcessing() {
    return (List<Integer>) this.fields.get(UspCoV1Field.SENSITIVE_DATA_PROCESSING).getValue();
  }

  public Integer getKnownChildSensitiveDataConsents() {
    return (Integer) this.fields.get(UspCoV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS).getValue();
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.fields.get(UspCoV1Field.MSPA_COVERED_TRANSACTION).getValue();
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.fields.get(UspCoV1Field.MSPA_OPT_OUT_OPTION_MODE).getValue();
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.fields.get(UspCoV1Field.MSPA_SERVICE_PROVIDER_MODE).getValue();
  }

  public Boolean getGpcSegmentType() {
    return (Boolean) this.fields.get(UspCoV1Field.GPC_SEGMENT_TYPE).getValue();
  }
  
  public Boolean getGpc() {
    return (Boolean) this.fields.get(UspCoV1Field.GPC).getValue();
  }
}
