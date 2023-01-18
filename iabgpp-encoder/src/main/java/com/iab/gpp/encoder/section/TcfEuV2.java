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
import com.iab.gpp.encoder.datatype.EncodableOptimizedFixedRange;
import com.iab.gpp.encoder.datatype.encoder.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.datatype.encoder.TraditionalBase64UrlEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.field.TcfEuV2Field;

public class TcfEuV2 extends AbstractEncodableSegmentedBitStringSection {
  public static int ID = 2;
  public static int VERSION = 2;
  public static String NAME = "tcfeuv2";

  private AbstractBase64UrlEncoder base64UrlEncoder = new TraditionalBase64UrlEncoder();

  public TcfEuV2() {
    initFields();
  }

  public TcfEuV2(String encodedString) throws DecodingException {
    initFields();

    if (encodedString != null && encodedString.length() > 0) {
      this.decode(encodedString);
    }
  }

  private void initFields() {
    fields = new HashMap<>();

    // core section
    fields.put(TcfEuV2Field.VERSION, new EncodableFixedInteger(6, TcfEuV2.VERSION));
    fields.put(TcfEuV2Field.CREATED, new EncodableDatetime());
    fields.put(TcfEuV2Field.LAST_UPDATED, new EncodableDatetime());
    fields.put(TcfEuV2Field.CMP_ID, new EncodableFixedInteger(12, 0));
    fields.put(TcfEuV2Field.CMP_VERSION, new EncodableFixedInteger(12, 0));
    fields.put(TcfEuV2Field.CONSENT_SCREEN, new EncodableFixedInteger(6, 0));
    fields.put(TcfEuV2Field.CONSENT_LANGUAGE, new EncodableFixedString(2, "EN"));
    fields.put(TcfEuV2Field.VENDOR_LIST_VERSION, new EncodableFixedInteger(12, 0));
    fields.put(TcfEuV2Field.POLICY_VERSION, new EncodableFixedInteger(6, 2));
    fields.put(TcfEuV2Field.IS_SERVICE_SPECIFIC, new EncodableBoolean(false));
    fields.put(TcfEuV2Field.USE_NON_STANDARD_STACKS, new EncodableBoolean(false));
    fields.put(TcfEuV2Field.SPECIAL_FEATURE_OPTINS, new EncodableFixedBitfield(12, new ArrayList<>()));
    fields.put(TcfEuV2Field.PURPOSE_CONSENTS, new EncodableFixedBitfield(24, new ArrayList<>()));
    fields.put(TcfEuV2Field.PURPOSE_LEGITIMATE_INTERESTS, new EncodableFixedBitfield(24, new ArrayList<>()));
    fields.put(TcfEuV2Field.PURPOSE_ONE_TREATMENT, new EncodableBoolean(false));
    fields.put(TcfEuV2Field.PUBLISHER_COUNTRY_CODE, new EncodableFixedString(2, "AA"));
    fields.put(TcfEuV2Field.VENDOR_CONSENTS, new EncodableOptimizedFixedRange(new ArrayList<>()));
    fields.put(TcfEuV2Field.VENDOR_LEGITIMATE_INTERESTS, new EncodableOptimizedFixedRange(new ArrayList<>()));

    fields.put(TcfEuV2Field.PUBLISHER_RESTRICTIONS, new EncodableFixedIntegerRange(new ArrayList<>()));

    // publisher purposes segment
    fields.put(TcfEuV2Field.PUBLISHER_PURPOSES_SEGMENT_TYPE, new EncodableFixedInteger(3, 3));
    fields.put(TcfEuV2Field.PUBLISHER_CONSENTS, new EncodableFixedBitfield(24, new ArrayList<>()));
    fields.put(TcfEuV2Field.PUBLISHER_LEGITIMATE_INTERESTS, new EncodableFixedBitfield(24, new ArrayList<>()));

    EncodableFixedInteger numCustomPurposes = new EncodableFixedInteger(6, 0);
    fields.put(TcfEuV2Field.NUM_CUSTOM_PURPOSES, numCustomPurposes);

    IntSupplier getLengthSupplier = new IntSupplier() {

      @Override
      public int getAsInt() {
        return numCustomPurposes.getValue();
      }

    };

    fields.put(TcfEuV2Field.PUBLISHER_CUSTOM_CONSENTS,
        new EncodableFlexibleBitfield(getLengthSupplier, new ArrayList<>()));

    fields.put(TcfEuV2Field.PUBLISHER_CUSTOM_LEGITIMATE_INTERESTS,
        new EncodableFlexibleBitfield(getLengthSupplier, new ArrayList<>()));

    fields.put(TcfEuV2Field.VENDORS_ALLOWED_SEGMENT_TYPE, new EncodableFixedInteger(3, 2));
    fields.put(TcfEuV2Field.VENDORS_ALLOWED, new EncodableOptimizedFixedRange(new ArrayList<>()));

    fields.put(TcfEuV2Field.VENDORS_DISCLOSED_SEGMENT_TYPE, new EncodableFixedInteger(3, 1));
    fields.put(TcfEuV2Field.VENDORS_DISCLOSED, new EncodableOptimizedFixedRange(new ArrayList<>()));

    //@formatter:off
    String[] coreSegment = new String[] {
      TcfEuV2Field.VERSION,
      TcfEuV2Field.CREATED,
      TcfEuV2Field.LAST_UPDATED,
      TcfEuV2Field.CMP_ID,
      TcfEuV2Field.CMP_VERSION,
      TcfEuV2Field.CONSENT_SCREEN,
      TcfEuV2Field.CONSENT_LANGUAGE,
      TcfEuV2Field.VENDOR_LIST_VERSION,
      TcfEuV2Field.POLICY_VERSION,
      TcfEuV2Field.IS_SERVICE_SPECIFIC,
      TcfEuV2Field.USE_NON_STANDARD_STACKS,
      TcfEuV2Field.SPECIAL_FEATURE_OPTINS,
      TcfEuV2Field.PURPOSE_CONSENTS,
      TcfEuV2Field.PURPOSE_LEGITIMATE_INTERESTS,
      TcfEuV2Field.PURPOSE_ONE_TREATMENT,
      TcfEuV2Field.PUBLISHER_COUNTRY_CODE,
      TcfEuV2Field.VENDOR_CONSENTS,
      TcfEuV2Field.VENDOR_LEGITIMATE_INTERESTS,
      TcfEuV2Field.PUBLISHER_RESTRICTIONS
    };

    String[] publisherPurposesSegment = new String[] {
      TcfEuV2Field.PUBLISHER_PURPOSES_SEGMENT_TYPE,
      TcfEuV2Field.PUBLISHER_CONSENTS,
      TcfEuV2Field.PUBLISHER_LEGITIMATE_INTERESTS,
      TcfEuV2Field.NUM_CUSTOM_PURPOSES,
      TcfEuV2Field.PUBLISHER_CUSTOM_CONSENTS,
      TcfEuV2Field.PUBLISHER_CUSTOM_LEGITIMATE_INTERESTS,
    };

    String[] vendorsAllowedSegment = new String[] {
      TcfEuV2Field.VENDORS_ALLOWED_SEGMENT_TYPE,
      TcfEuV2Field.VENDORS_ALLOWED,
    };

    String[] vendorsDisclosedSegment = new String[] {
      TcfEuV2Field.VENDORS_DISCLOSED_SEGMENT_TYPE,
      TcfEuV2Field.VENDORS_DISCLOSED,
    };

    segments = new String[][] {
      coreSegment, 
      publisherPurposesSegment, 
      vendorsAllowedSegment, 
      vendorsDisclosedSegment
    };
    //@formatter:on
  }

  @Override
  public String encode() throws EncodingException {
    List<String> segmentBitStrings = this.encodeSegmentsToBitStrings();
    List<String> encodedSegments = new ArrayList<>();
    if (segmentBitStrings.size() >= 1) {
      encodedSegments.add(base64UrlEncoder.encode(segmentBitStrings.get(0)));

      Boolean isServiceSpecific = (Boolean) this.getFieldValue(TcfEuV2Field.IS_SERVICE_SPECIFIC);
      if (isServiceSpecific) {
        if (segmentBitStrings.size() >= 2) {
          encodedSegments.add(base64UrlEncoder.encode(segmentBitStrings.get(1)));
        }
      } else {
        if (segmentBitStrings.size() >= 2) {
          encodedSegments.add(base64UrlEncoder.encode(segmentBitStrings.get(2)));

          if (segmentBitStrings.size() >= 3) {
            encodedSegments.add(base64UrlEncoder.encode(segmentBitStrings.get(3)));
          }
        }
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
       * first char will contain 6 bits, we only need the first 3. There is no segment type for the CORE
       * string. Instead the first 6 bits are reserved for the encoding version, but because we're only on
       * a maximum of encoding version 2 the first 3 bits in the core segment will evaluate to 0.
       */
      String segmentBitString = base64UrlEncoder.decode(encodedSegments[i]);
      switch (segmentBitString.substring(0, 3)) {
        // unfortunately, the segment ordering doesn't match the segment ids
        case "000": {
          segmentBitStrings[0] = segmentBitString;
          break;
        }
        case "001": {
          segmentBitStrings[3] = segmentBitString;
          break;
        }
        case "010": {
          segmentBitStrings[2] = segmentBitString;
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
  public void setFieldValue(String fieldName, Object value) throws InvalidFieldException {
    super.setFieldValue(fieldName, value);

    if (!fieldName.equals(TcfEuV2Field.CREATED) && !fieldName.equals(TcfEuV2Field.LAST_UPDATED)) {
      ZonedDateTime utcDateTime = ZonedDateTime.now(ZoneId.of("UTC"));

      super.setFieldValue(TcfEuV2Field.CREATED, utcDateTime);
      super.setFieldValue(TcfEuV2Field.LAST_UPDATED, utcDateTime);
    }
  }

  @Override
  public int getId() {
    return TcfEuV2.ID;
  }

  @Override
  public String getName() {
    return TcfEuV2.NAME;
  }

  public Integer getVersion() {
    return (Integer) this.fields.get(TcfEuV2Field.VERSION).getValue();
  }

  public ZonedDateTime getCreated() {
    return (ZonedDateTime) this.fields.get(TcfEuV2Field.CREATED).getValue();
  }

  public ZonedDateTime getLastUpdated() {
    return (ZonedDateTime) this.fields.get(TcfEuV2Field.LAST_UPDATED).getValue();
  }

  public Integer getCmpId() {
    return (Integer) this.fields.get(TcfEuV2Field.CMP_ID).getValue();
  }

  public Integer getCmpVersion() {
    return (Integer) this.fields.get(TcfEuV2Field.CMP_VERSION).getValue();
  }

  public Integer getConsentScreen() {
    return (Integer) this.fields.get(TcfEuV2Field.CONSENT_SCREEN).getValue();
  }

  public String getConsentLanguage() {
    return (String) this.fields.get(TcfEuV2Field.CONSENT_LANGUAGE).getValue();
  }

  public Integer getVendorListVersion() {
    return (Integer) this.fields.get(TcfEuV2Field.VENDOR_LIST_VERSION).getValue();
  }

  public Integer getPolicyVersion() {
    return (Integer) this.fields.get(TcfEuV2Field.POLICY_VERSION).getValue();
  }

  public Boolean getIsServiceSpecific() {
    return (Boolean) this.fields.get(TcfEuV2Field.IS_SERVICE_SPECIFIC).getValue();
  }

  public Boolean getUseNonStandardStacks() {
    return (Boolean) this.fields.get(TcfEuV2Field.USE_NON_STANDARD_STACKS).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Boolean> getSpecialFeatureOptins() {
    return (List<Boolean>) this.fields.get(TcfEuV2Field.SPECIAL_FEATURE_OPTINS).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Boolean> getPurposeConsents() {
    return (List<Boolean>) this.fields.get(TcfEuV2Field.PURPOSE_CONSENTS).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Boolean> getPurposeLegitimateInterests() {
    return (List<Boolean>) this.fields.get(TcfEuV2Field.PURPOSE_LEGITIMATE_INTERESTS).getValue();
  }

  public Boolean getPurposeOneTreatment() {
    return (Boolean) this.fields.get(TcfEuV2Field.PURPOSE_ONE_TREATMENT).getValue();
  }

  public String getPublisherCountryCode() {
    return (String) this.fields.get(TcfEuV2Field.PUBLISHER_COUNTRY_CODE).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getVendorConsents() {
    return (List<Integer>) this.fields.get(TcfEuV2Field.VENDOR_CONSENTS).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getVendorLegitimateInterests() {
    return (List<Integer>) this.fields.get(TcfEuV2Field.VENDOR_LEGITIMATE_INTERESTS).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getPublisherRestrictions() {
    return (List<Integer>) this.fields.get(TcfEuV2Field.PUBLISHER_RESTRICTIONS).getValue();
  }

  public Integer getPublisherPurposesSegmentType() {
    return (Integer) this.fields.get(TcfEuV2Field.PUBLISHER_PURPOSES_SEGMENT_TYPE).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Boolean> getPublisherConsents() {
    return (List<Boolean>) this.fields.get(TcfEuV2Field.PUBLISHER_CONSENTS).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Boolean> getPublisherLegitimateInterests() {
    return (List<Boolean>) this.fields.get(TcfEuV2Field.PUBLISHER_LEGITIMATE_INTERESTS).getValue();
  }

  public Integer getNumCustomPurposes() {
    return (Integer) this.fields.get(TcfEuV2Field.NUM_CUSTOM_PURPOSES).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getPublisherCustomConsents() {
    return (List<Integer>) this.fields.get(TcfEuV2Field.PUBLISHER_CUSTOM_CONSENTS).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getPublisherCustomLegitimateInterests() {
    return (List<Integer>) this.fields.get(TcfEuV2Field.PUBLISHER_CUSTOM_LEGITIMATE_INTERESTS).getValue();
  }

  public Integer getVendorsAllowedSegmentType() {
    return (Integer) this.fields.get(TcfEuV2Field.VENDORS_ALLOWED_SEGMENT_TYPE).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getVendorsAllowed() {
    return (List<Integer>) this.fields.get(TcfEuV2Field.VENDORS_ALLOWED).getValue();
  }

  public Integer getVendorsDisclosedSegmentType() {
    return (Integer) this.fields.get(TcfEuV2Field.VENDORS_DISCLOSED_SEGMENT_TYPE).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getVendorsDisclosed() {
    return (List<Integer>) this.fields.get(TcfEuV2Field.VENDORS_DISCLOSED).getValue();
  }



}
