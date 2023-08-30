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
import com.iab.gpp.encoder.field.UsCaV1Field;
import com.iab.gpp.encoder.field.UsCtV1Field;
import com.iab.gpp.encoder.field.UspV1Field;

public class UsCtV1 extends AbstractEncodableSegmentedBitStringSection {
  public static int ID = 12;
  public static int VERSION = 1;
  public static String NAME = "usctv1";

  private AbstractBase64UrlEncoder base64UrlEncoder = new CompressedBase64UrlEncoder();

  public UsCtV1() {
    initFields();
  }

  public UsCtV1(String encodedString) throws DecodingException {
    initFields();

    if (encodedString != null && encodedString.length() > 0) {
      this.decode(encodedString);
    }
  }

  private void initFields() {
    fields = new HashMap<>();

    fields.put(UsCtV1Field.VERSION, new EncodableFixedInteger(6, UsCtV1.VERSION));
    fields.put(UsCtV1Field.SHARING_NOTICE, new EncodableFixedInteger(2, 0));
    fields.put(UsCtV1Field.SALE_OPT_OUT_NOTICE, new EncodableFixedInteger(2, 0));
    fields.put(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, new EncodableFixedInteger(2, 0));
    fields.put(UsCtV1Field.SALE_OPT_OUT, new EncodableFixedInteger(2, 0));
    fields.put(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT, new EncodableFixedInteger(2, 0));
    fields.put(UsCtV1Field.SENSITIVE_DATA_PROCESSING,
        new EncodableFixedIntegerList(2, Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0)));
    fields.put(UsCtV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
        new EncodableFixedIntegerList(2, Arrays.asList(0, 0, 0)));
    fields.put(UsCtV1Field.MSPA_COVERED_TRANSACTION, new EncodableFixedInteger(2, 0));
    fields.put(UsCtV1Field.MSPA_OPT_OUT_OPTION_MODE, new EncodableFixedInteger(2, 0));
    fields.put(UsCtV1Field.MSPA_SERVICE_PROVIDER_MODE, new EncodableFixedInteger(2, 0));

    // gpc segment
    fields.put(UsCtV1Field.GPC_SEGMENT_TYPE, new EncodableFixedInteger(2, 1));
    fields.put(UsCtV1Field.GPC_SEGMENT_INCLUDED, new EncodableBoolean(true));
    fields.put(UsCtV1Field.GPC, new EncodableBoolean(false));

    //@formatter:off
    String[] coreSegment = new String[] {
        UsCtV1Field.VERSION,
        UsCtV1Field.SHARING_NOTICE,
        UsCtV1Field.SALE_OPT_OUT_NOTICE,
        UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
        UsCtV1Field.SALE_OPT_OUT,
        UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT,
        UsCtV1Field.SENSITIVE_DATA_PROCESSING,
        UsCtV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
        UsCtV1Field.MSPA_COVERED_TRANSACTION,
        UsCtV1Field.MSPA_OPT_OUT_OPTION_MODE,
        UsCtV1Field.MSPA_SERVICE_PROVIDER_MODE
    };
    
    String[] gpcSegment = new String[] {
        UsCtV1Field.GPC_SEGMENT_TYPE,
        UsCtV1Field.GPC
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
        Boolean gpcSegmentIncluded = (Boolean) this.fields.get(UsCtV1Field.GPC_SEGMENT_INCLUDED).getValue();
        if (gpcSegmentIncluded) {
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
       * first char will contain 6 bits, we only need the first 2. There is no segment type for the CORE
       * string. Instead the first 6 bits are reserved for the encoding version, but because we're only on
       * a maximum of encoding version 2 the first 2 bits in the core segment will evaluate to 0.
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
    this.fields.get(UsCaV1Field.GPC_SEGMENT_INCLUDED).setValue(gpcSegmentIncluded);
  }

  @Override
  public int getId() {
    return UsCtV1.ID;
  }

  @Override
  public String getName() {
    return UsCtV1.NAME;
  }

  public Integer getVersion() {
    return (Integer) this.fields.get(UspV1Field.VERSION).getValue();
  }

  public Integer getSharingNotice() {
    return (Integer) this.fields.get(UsCtV1Field.SHARING_NOTICE).getValue();
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.fields.get(UsCtV1Field.SALE_OPT_OUT_NOTICE).getValue();
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.fields.get(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE).getValue();
  }

  public Integer getSaleOptOut() {
    return (Integer) this.fields.get(UsCtV1Field.SALE_OPT_OUT).getValue();
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.fields.get(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getSensitiveDataProcessing() {
    return (List<Integer>) this.fields.get(UsCtV1Field.SENSITIVE_DATA_PROCESSING).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getKnownChildSensitiveDataConsents() {
    return (List<Integer>) this.fields.get(UsCtV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS).getValue();
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.fields.get(UsCtV1Field.MSPA_COVERED_TRANSACTION).getValue();
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.fields.get(UsCtV1Field.MSPA_OPT_OUT_OPTION_MODE).getValue();
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.fields.get(UsCtV1Field.MSPA_SERVICE_PROVIDER_MODE).getValue();
  }

  public Integer getGpcSegmentType() {
    return (Integer) this.fields.get(UsCtV1Field.GPC_SEGMENT_TYPE).getValue();
  }

  public Boolean getGpcSegmentIncluded() {
    return (Boolean) this.fields.get(UsCtV1Field.GPC_SEGMENT_INCLUDED).getValue();
  }

  public Boolean getGpc() {
    return (Boolean) this.fields.get(UsCtV1Field.GPC).getValue();
  }
}
