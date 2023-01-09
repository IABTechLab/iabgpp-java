package com.iab.gpp.encoder.section;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableDatetime;
import com.iab.gpp.encoder.datatype.EncodableFixedBitfield;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerRange;
import com.iab.gpp.encoder.datatype.EncodableFixedString;
import com.iab.gpp.encoder.datatype.EncodableFlexibleBitfield;
import com.iab.gpp.encoder.datatype.encoder.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.datatype.encoder.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.TcfCaV1Field;

public class TcfCaV1 extends AbstractEncodableSegmentedBitStringSection {
  public static int ID = 5;
  public static int VERSION = 2;
  public static String NAME = "tcfcav1";

  private AbstractBase64UrlEncoder base64UrlEncoder = new CompressedBase64UrlEncoder();
  
  public TcfCaV1() {
    initFields();
  }

  public TcfCaV1(String encodedString) throws DecodingException {
    initFields();

    if (encodedString != null && encodedString.length() > 0) {
      this.decode(encodedString);
    }
  }

  private void initFields() {
    fields = new HashMap<>();

    // core section
    fields.put(TcfCaV1Field.VERSION, new EncodableFixedInteger(6, TcfCaV1.VERSION));
    fields.put(TcfCaV1Field.CREATED, new EncodableDatetime());
    fields.put(TcfCaV1Field.LAST_UPDATED, new EncodableDatetime());
    fields.put(TcfCaV1Field.CMP_ID, new EncodableFixedInteger(12, 0));
    fields.put(TcfCaV1Field.CMP_VERSION, new EncodableFixedInteger(12, 0));
    fields.put(TcfCaV1Field.CONSENT_SCREEN, new EncodableFixedInteger(6, 0));
    fields.put(TcfCaV1Field.CONSENT_LANGUAGE, new EncodableFixedString(2, "EN"));
    fields.put(TcfCaV1Field.VENDOR_LIST_VERSION, new EncodableFixedInteger(12, 0));
    fields.put(TcfCaV1Field.TCF_POLICY_VERSION, new EncodableFixedInteger(6, 2));
    fields.put(TcfCaV1Field.USE_NON_STANDARD_STACKS, new EncodableBoolean(false));
    fields.put(TcfCaV1Field.SPECIAL_FEATURE_EXPRESS_CONSENT, new EncodableFixedBitfield(12, new ArrayList<>()));
    fields.put(TcfCaV1Field.PURPOSES_EXPRESS_CONSENT, new EncodableFixedBitfield(24, new ArrayList<>()));
    fields.put(TcfCaV1Field.PURPOSES_IMPLIED_CONSENT, new EncodableFixedBitfield(24, new ArrayList<>()));
    fields.put(TcfCaV1Field.VENDOR_EXPRESS_CONSENT, new EncodableFixedIntegerRange(new ArrayList<>()));
    fields.put(TcfCaV1Field.VENDOR_IMPLIED_CONSENT, new EncodableFixedIntegerRange(new ArrayList<>()));

    // publisher purposes segment
    fields.put(TcfCaV1Field.PUB_PURPOSES_SEGMENT_TYPE, new EncodableFixedInteger(3, 3));
    fields.put(TcfCaV1Field.PUB_PURPOSES_EXPRESS_CONSENT, new EncodableFixedBitfield(24, new ArrayList<>()));
    fields.put(TcfCaV1Field.PUB_PURPOSES_IMPLIED_CONSENT, new EncodableFixedBitfield(24, new ArrayList<>()));

    EncodableFixedInteger numCustomPurposes = new EncodableFixedInteger(6, 0);
    fields.put(TcfCaV1Field.NUM_CUSTOM_PURPOSES, numCustomPurposes);

    IntSupplier getLengthSupplier = new IntSupplier() {

      @Override
      public int getAsInt() {
        return numCustomPurposes.getValue();
      }

    };

    fields.put(TcfCaV1Field.CUSTOM_PURPOSES_EXPRESS_CONSENT,
        new EncodableFlexibleBitfield(getLengthSupplier, new ArrayList<>()));

    fields.put(TcfCaV1Field.CUSTOM_PURPOSES_IMPLIED_CONSENT,
        new EncodableFlexibleBitfield(getLengthSupplier, new ArrayList<>()));

    //@formatter:off
    String[] coreSegment = new String[] {
      TcfCaV1Field.VERSION,
      TcfCaV1Field.CREATED,
      TcfCaV1Field.LAST_UPDATED,
      TcfCaV1Field.CMP_ID,
      TcfCaV1Field.CMP_VERSION,
      TcfCaV1Field.CONSENT_SCREEN,
      TcfCaV1Field.CONSENT_LANGUAGE,
      TcfCaV1Field.VENDOR_LIST_VERSION,
      TcfCaV1Field.TCF_POLICY_VERSION,
      TcfCaV1Field.USE_NON_STANDARD_STACKS,
      TcfCaV1Field.SPECIAL_FEATURE_EXPRESS_CONSENT,
      TcfCaV1Field.PURPOSES_EXPRESS_CONSENT,
      TcfCaV1Field.PURPOSES_IMPLIED_CONSENT,
      TcfCaV1Field.VENDOR_EXPRESS_CONSENT,
      TcfCaV1Field.VENDOR_IMPLIED_CONSENT
    };

    String[] publisherPurposesSegment = new String[] {
      TcfCaV1Field.PUB_PURPOSES_SEGMENT_TYPE,
      TcfCaV1Field.PUB_PURPOSES_EXPRESS_CONSENT,
      TcfCaV1Field.PUB_PURPOSES_IMPLIED_CONSENT,
      TcfCaV1Field.NUM_CUSTOM_PURPOSES,
      TcfCaV1Field.CUSTOM_PURPOSES_EXPRESS_CONSENT,
      TcfCaV1Field.CUSTOM_PURPOSES_IMPLIED_CONSENT,
    };

    segments = new String[][] {
      coreSegment, 
      publisherPurposesSegment
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
        encodedSegments.add(base64UrlEncoder.encode(segmentBitStrings.get(1)));
      }
    }

    return encodedSegments.stream().collect(Collectors.joining("."));
  }

  @Override
  public void decode(String encodedSection) throws DecodingException {
    String[] encodedSegments = encodedSection.split("\\.");
    String[] segmentBitStrings = new String[4];
    for (int i = 0; i < encodedSegments.length; i++) {
      /**
       * first char will contain 6 bits, we only need the first 3. 
       * There is no segment type for the CORE string. Instead the first 6 bits are reserved for the
       * encoding version, but because we're only on a maximum of encoding version 2 the first 3 bits in
       * the core segment will evaluate to 0.
       */
      String segmentBitString = base64UrlEncoder.decode(encodedSegments[i]);
      switch (segmentBitString.substring(0, 3)) {
        // unfortunately, the segment ordering doesn't match the segment ids
        case "000": {
          segmentBitStrings[0] = segmentBitString;
          break;
        }
        case "011": {
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
  public void setFieldValue(String fieldName, Object value) {
    super.setFieldValue(fieldName, value);

    if (!fieldName.equals(TcfCaV1Field.CREATED) && !fieldName.equals(TcfCaV1Field.LAST_UPDATED)) {
      ZonedDateTime utcDateTime = ZonedDateTime.now(ZoneId.of("UTC"));

      super.setFieldValue(TcfCaV1Field.CREATED, utcDateTime);
      super.setFieldValue(TcfCaV1Field.LAST_UPDATED, utcDateTime);
    }
  }

  @Override
  public int getId() {
    return TcfCaV1.ID;
  }

  @Override
  public String getName() {
    return TcfCaV1.NAME;
  }

  public Integer getVersion() {
    return (Integer) this.fields.get(TcfCaV1Field.VERSION).getValue();
  }

  public ZonedDateTime getCreated() {
    return (ZonedDateTime) this.fields.get(TcfCaV1Field.CREATED).getValue();
  }

  public ZonedDateTime getLastUpdated() {
    return (ZonedDateTime) this.fields.get(TcfCaV1Field.LAST_UPDATED).getValue();
  }

  public Integer getCmpId() {
    return (Integer) this.fields.get(TcfCaV1Field.CMP_ID).getValue();
  }

  public Integer getCmpVersion() {
    return (Integer) this.fields.get(TcfCaV1Field.CMP_VERSION).getValue();
  }

  public Integer getConsentScreen() {
    return (Integer) this.fields.get(TcfCaV1Field.CONSENT_SCREEN).getValue();
  }

  public String getConsentLanguage() {
    return (String) this.fields.get(TcfCaV1Field.CONSENT_LANGUAGE).getValue();
  }

  public Integer getVendorListVersion() {
    return (Integer) this.fields.get(TcfCaV1Field.VENDOR_LIST_VERSION).getValue();
  }

  public Integer getPolicyVersion() {
    return (Integer) this.fields.get(TcfCaV1Field.TCF_POLICY_VERSION).getValue();
  }

  public Boolean getUseNonStandardStacks() {
    return (Boolean) this.fields.get(TcfCaV1Field.USE_NON_STANDARD_STACKS).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getSpecialFeatureExpressConsent() {
    return (List<Integer>) this.fields.get(TcfCaV1Field.SPECIAL_FEATURE_EXPRESS_CONSENT).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getPurposesExpressConsent() {
    return (List<Integer>) this.fields.get(TcfCaV1Field.PURPOSES_EXPRESS_CONSENT).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getPurposesImpliedConsent() {
    return (List<Integer>) this.fields.get(TcfCaV1Field.PURPOSES_IMPLIED_CONSENT).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getVendorExpressConsent() {
    return (List<Integer>) this.fields.get(TcfCaV1Field.VENDOR_EXPRESS_CONSENT).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getVendorImpliedConsent() {
    return (List<Integer>) this.fields.get(TcfCaV1Field.VENDOR_IMPLIED_CONSENT).getValue();
  }

  public Integer getPubPurposesSegmentType() {
    return (Integer) this.fields.get(TcfCaV1Field.PUB_PURPOSES_SEGMENT_TYPE).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getPubPurposesExpressConsent() {
    return (List<Integer>) this.fields.get(TcfCaV1Field.PUB_PURPOSES_EXPRESS_CONSENT).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getPubPurposesImpliedConsent() {
    return (List<Integer>) this.fields.get(TcfCaV1Field.PUB_PURPOSES_IMPLIED_CONSENT).getValue();
  }

  public Integer getNumCustomPurposes() {
    return (Integer) this.fields.get(TcfCaV1Field.NUM_CUSTOM_PURPOSES).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getCustomPurposesExpressConsent() {
    return (List<Integer>) this.fields.get(TcfCaV1Field.CUSTOM_PURPOSES_EXPRESS_CONSENT).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getCustomPurposesImpliedConsent() {
    return (List<Integer>) this.fields.get(TcfCaV1Field.CUSTOM_PURPOSES_IMPLIED_CONSENT).getValue();
  }

}
