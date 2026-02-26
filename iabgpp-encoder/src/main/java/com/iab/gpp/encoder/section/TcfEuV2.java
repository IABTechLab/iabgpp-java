package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.datatype.RangeEntry;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.field.TcfEuV2Field;
import com.iab.gpp.encoder.segment.EncodableSegment;
import com.iab.gpp.encoder.segment.TcfEuV2CoreSegment;
import com.iab.gpp.encoder.segment.TcfEuV2PublisherPurposesSegment;
import com.iab.gpp.encoder.segment.TcfEuV2VendorsAllowedSegment;
import com.iab.gpp.encoder.segment.TcfEuV2VendorsDisclosedSegment;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class TcfEuV2 extends AbstractLazilyEncodableSection {
  
  public static int ID = 2;
  public static int VERSION = 2;
  public static String NAME = "tcfeuv2";

  public TcfEuV2() {
    super();
  }

  public TcfEuV2(String encodedString) {
    super();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return TcfEuV2.ID;
  }

  @Override
  public String getName() {
    return TcfEuV2.NAME;
  }

  @Override
  public int getVersion() {
    return TcfEuV2.VERSION;
  }

  @Override
  protected List<EncodableSegment> initializeSegments() {
    List<EncodableSegment> segments = new ArrayList<>();
    segments.add(new TcfEuV2CoreSegment());
    segments.add(new TcfEuV2PublisherPurposesSegment());
    segments.add(new TcfEuV2VendorsAllowedSegment());
    segments.add(new TcfEuV2VendorsDisclosedSegment());
    return segments;
  }

  @Override
  public List<EncodableSegment> decodeSection(final String encodedString) {
    final List<EncodableSegment> segments = initializeSegments();

    if (encodedString == null || encodedString.isEmpty()) {
      return segments;
    }

    final String[] encodedSegments = encodedString.split("\\.");

    for (final String encodedSegment: encodedSegments) {
      /**
       * The first 3 bits contain the segment id. Rather than decode the entire string, just check the first character.
       *
       * A-H     = '000' = 0
       * I-P     = '001' = 1
       * Q-X     = '010' = 2
       * Y-Z,a-f = '011' = 3
       *
       * Note that there is no segment id field for the core segment. Instead the first 6 bits are reserved
       * for the encoding version which only coincidentally works here because the version value is less than 8.
       */
      if (encodedSegment.isEmpty()) {
        continue;
      }

      final char firstChar = encodedSegment.charAt(0);

      // unfortunately, the segment ordering doesn't match the segment ids
      if(firstChar >= 'A' && firstChar <= 'H') {
        segments.get(0).decode(encodedSegment);
      } else if(firstChar >= 'I' && firstChar <= 'P') {
        segments.get(3).decode(encodedSegment);
      } else if(firstChar >= 'Q' && firstChar <= 'X') {
        segments.get(2).decode(encodedSegment);
      } else if((firstChar >= 'Y' && firstChar <= 'Z') || (firstChar >= 'a' && firstChar <= 'f')) {
        segments.get(1).decode(encodedSegment);
      } else {
        throw new DecodingException("Invalid segment '" + encodedSegment + "'");
      }
    }

    return segments;
  }

  @Override
  public String encodeSection(List<EncodableSegment> segments) {
    final StringJoiner encodedSegments = new StringJoiner(".");

    /*
     Only encode a non-core section if it doesn't have default values.
     This is intended to match https://github.com/InteractiveAdvertisingBureau/iabtcf-java/blob/master/iabtcf-encoder/src/main/java/com/iabtcf/encoder/TCStringEncoder.java.
     */
    for (final EncodableSegment segment: segments) {
      final boolean encode;

      if (segment instanceof TcfEuV2CoreSegment) {
        encode = true;
      } else if (segment instanceof TcfEuV2PublisherPurposesSegment) {
        final List<Boolean> publisherConsents = getPublisherConsents();
        final List<Boolean> publisherLegitimateInterests = getPublisherLegitimateInterests();
        encode = (publisherConsents != null && publisherConsents.contains(true))
            || (publisherLegitimateInterests != null && publisherLegitimateInterests.contains(true))
            || !Objects.equals(getNumCustomPurposes(), 0);
      } else if (segment instanceof TcfEuV2VendorsAllowedSegment) {
        encode = !Objects.equals(getVendorsAllowed(), Collections.emptyList());
      } else if (segment instanceof TcfEuV2VendorsDisclosedSegment) {
        encode = !Objects.equals(getVendorsDisclosed(), Collections.emptyList());
      } else {
        throw new EncodingException(String.format("Unknown segment type '%s' for section %s.", segment.getClass().getName(), NAME));
      }

      if (encode) {
        encodedSegments.add(segment.encode());
      }
    }

    return encodedSegments.toString();
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

  
  public ZonedDateTime getCreated() {
    return (ZonedDateTime) this.getFieldValue(TcfEuV2Field.CREATED);
  }

  public ZonedDateTime getLastUpdated() {
    return (ZonedDateTime) this.getFieldValue(TcfEuV2Field.LAST_UPDATED);
  }

  public Integer getCmpId() {
    return (Integer) this.getFieldValue(TcfEuV2Field.CMP_ID);
  }

  public Integer getCmpVersion() {
    return (Integer) this.getFieldValue(TcfEuV2Field.CMP_VERSION);
  }

  public Integer getConsentScreen() {
    return (Integer) this.getFieldValue(TcfEuV2Field.CONSENT_SCREEN);
  }

  public String getConsentLanguage() {
    return (String) this.getFieldValue(TcfEuV2Field.CONSENT_LANGUAGE);
  }

  public Integer getVendorListVersion() {
    return (Integer) this.getFieldValue(TcfEuV2Field.VENDOR_LIST_VERSION);
  }

  public Integer getPolicyVersion() {
    return (Integer) this.getFieldValue(TcfEuV2Field.POLICY_VERSION);
  }

  public Boolean getIsServiceSpecific() {
    return (Boolean) this.getFieldValue(TcfEuV2Field.IS_SERVICE_SPECIFIC);
  }

  public Boolean getUseNonStandardStacks() {
    return (Boolean) this.getFieldValue(TcfEuV2Field.USE_NON_STANDARD_STACKS);
  }

  @SuppressWarnings("unchecked")
  public List<Boolean> getSpecialFeatureOptins() {
    return (List<Boolean>) this.getFieldValue(TcfEuV2Field.SPECIAL_FEATURE_OPTINS);
  }

  @SuppressWarnings("unchecked")
  public List<Boolean> getPurposeConsents() {
    return (List<Boolean>) this.getFieldValue(TcfEuV2Field.PURPOSE_CONSENTS);
  }

  @SuppressWarnings("unchecked")
  public List<Boolean> getPurposeLegitimateInterests() {
    return (List<Boolean>) this.getFieldValue(TcfEuV2Field.PURPOSE_LEGITIMATE_INTERESTS);
  }

  public Boolean getPurposeOneTreatment() {
    return (Boolean) this.getFieldValue(TcfEuV2Field.PURPOSE_ONE_TREATMENT);
  }

  public String getPublisherCountryCode() {
    return (String) this.getFieldValue(TcfEuV2Field.PUBLISHER_COUNTRY_CODE);
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getVendorConsents() {
    return (List<Integer>) this.getFieldValue(TcfEuV2Field.VENDOR_CONSENTS);
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getVendorLegitimateInterests() {
    return (List<Integer>) this.getFieldValue(TcfEuV2Field.VENDOR_LEGITIMATE_INTERESTS);
  }

  @SuppressWarnings("unchecked")
  public List<RangeEntry> getPublisherRestrictions() {
    return (List<RangeEntry>) this.getFieldValue(TcfEuV2Field.PUBLISHER_RESTRICTIONS);
  }

  public Integer getPublisherPurposesSegmentType() {
    return (Integer) this.getFieldValue(TcfEuV2Field.PUBLISHER_PURPOSES_SEGMENT_TYPE);
  }

  @SuppressWarnings("unchecked")
  public List<Boolean> getPublisherConsents() {
    return (List<Boolean>) this.getFieldValue(TcfEuV2Field.PUBLISHER_CONSENTS);
  }

  @SuppressWarnings("unchecked")
  public List<Boolean> getPublisherLegitimateInterests() {
    return (List<Boolean>) this.getFieldValue(TcfEuV2Field.PUBLISHER_LEGITIMATE_INTERESTS);
  }

  public Integer getNumCustomPurposes() {
    return (Integer) this.getFieldValue(TcfEuV2Field.NUM_CUSTOM_PURPOSES);
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getPublisherCustomConsents() {
    return (List<Integer>) this.getFieldValue(TcfEuV2Field.PUBLISHER_CUSTOM_CONSENTS);
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getPublisherCustomLegitimateInterests() {
    return (List<Integer>) this.getFieldValue(TcfEuV2Field.PUBLISHER_CUSTOM_LEGITIMATE_INTERESTS);
  }

  public Integer getVendorsAllowedSegmentType() {
    return (Integer) this.getFieldValue(TcfEuV2Field.VENDORS_ALLOWED_SEGMENT_TYPE);
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getVendorsAllowed() {
    return (List<Integer>) this.getFieldValue(TcfEuV2Field.VENDORS_ALLOWED);
  }

  public Integer getVendorsDisclosedSegmentType() {
    return (Integer) this.getFieldValue(TcfEuV2Field.VENDORS_DISCLOSED_SEGMENT_TYPE);
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getVendorsDisclosed() {
    return (List<Integer>) this.getFieldValue(TcfEuV2Field.VENDORS_DISCLOSED);
  }
  
  
}
