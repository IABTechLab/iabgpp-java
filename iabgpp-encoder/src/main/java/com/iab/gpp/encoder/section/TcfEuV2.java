package com.iab.gpp.encoder.section;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.iab.gpp.encoder.datatype.RangeEntry;
import com.iab.gpp.encoder.datatype.encoder.DatetimeEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.field.TcfEuV2Field;
import com.iab.gpp.encoder.segment.EncodableSegment;
import com.iab.gpp.encoder.segment.TcfEuV2CoreSegment;
import com.iab.gpp.encoder.segment.TcfEuV2PublisherPurposesSegment;
import com.iab.gpp.encoder.segment.TcfEuV2VendorsAllowedSegment;
import com.iab.gpp.encoder.segment.TcfEuV2VendorsDisclosedSegment;

public class TcfEuV2 extends AbstractLazilyEncodableSection {
  
  public static final int ID = 2;
  public static final int VERSION = 2;
  public static final String NAME = "tcfeuv2";

  public TcfEuV2() {
    super();
  }

  public TcfEuV2(CharSequence encodedString) {
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
    return Arrays.asList(new TcfEuV2CoreSegment(), new TcfEuV2PublisherPurposesSegment(), new TcfEuV2VendorsAllowedSegment(), new TcfEuV2VendorsDisclosedSegment());
  }
  
  @Override
  public List<EncodableSegment> decodeSection(CharSequence encodedString) {
    List<EncodableSegment> segments = initializeSegments();
    
    if (encodedString != null && encodedString.length() > 0) {
      List<CharSequence> encodedSegments = SlicedCharSequence.split(encodedString, '.');
      for (int i = 0; i < encodedSegments.size(); i++) {
        
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
        
        CharSequence encodedSegment = encodedSegments.get(i);
        if (encodedSegment.length() > 0) {
          char firstChar = encodedSegment.charAt(0);
          
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
      }
    }
    
    return segments;
  }

  @Override
  public String encodeSection(List<EncodableSegment> segments) {
    List<String> encodedSegments = new ArrayList<>(segments.size());
    if (segments.size() >= 1) {
      encodedSegments.add(segments.get(0).encode());

      Boolean isServiceSpecific = (Boolean) this.getFieldValue(TcfEuV2Field.IS_SERVICE_SPECIFIC);
      if (isServiceSpecific) {
        if (segments.size() >= 2) {
          encodedSegments.add(segments.get(1).encode());
        }
      } else {
        if (segments.size() >= 2) {
          encodedSegments.add(segments.get(2).encode());

          if (segments.size() >= 3) {
            encodedSegments.add(segments.get(3).encode());
          }
        }
      }
    }

    return String.join(".", encodedSegments);
  }

  @Override
  public void setFieldValue(String fieldName, Object value) throws InvalidFieldException {
    super.setFieldValue(fieldName, value);

    if (!fieldName.equals(TcfEuV2Field.CREATED) && !fieldName.equals(TcfEuV2Field.LAST_UPDATED)) {
      ZonedDateTime utcDateTime = ZonedDateTime.now(DatetimeEncoder.UTC);

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
