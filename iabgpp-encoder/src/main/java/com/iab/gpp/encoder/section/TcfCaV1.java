package com.iab.gpp.encoder.section;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.iab.gpp.encoder.datatype.RangeEntry;
import com.iab.gpp.encoder.datatype.encoder.DatetimeEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.field.TcfCaV1Field;
import com.iab.gpp.encoder.segment.EncodableSegment;
import com.iab.gpp.encoder.segment.TcfCaV1CoreSegment;
import com.iab.gpp.encoder.segment.TcfCaV1DisclosedVendorsSegment;
import com.iab.gpp.encoder.segment.TcfCaV1PublisherPurposesSegment;

public class TcfCaV1 extends AbstractLazilyEncodableSection {
  
  public static final int ID = 5;
  public static final int VERSION = 1;
  public static final String NAME = "tcfcav1";

  public TcfCaV1() {
    super();
  }

  public TcfCaV1(CharSequence encodedString) {
    super();
    decode(encodedString);
  }

  @Override
  public int getId() {
    return TcfCaV1.ID;
  }

  @Override
  public String getName() {
    return TcfCaV1.NAME;
  }

  @Override
  public int getVersion() {
    return TcfCaV1.VERSION;
  }

  @Override
  protected List<EncodableSegment> initializeSegments() {
    return Arrays.asList(new TcfCaV1CoreSegment(), new TcfCaV1PublisherPurposesSegment(), new TcfCaV1DisclosedVendorsSegment());
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
         * Y-Z,a-f = '011' = 3
         * 
         * Note that there is no segment id field for the core segment. Instead the first 6 bits are reserved 
         * for the encoding version which only coincidentally works here because the version value is less than 8.
         */
        
        CharSequence encodedSegment = encodedSegments.get(i);
        if (encodedSegment.length() > 0) {
          char firstChar = encodedSegment.charAt(0);
          
          if(firstChar >= 'A' && firstChar <= 'H') {
            segments.get(0).decode(encodedSegment);
          } else if(firstChar >= 'I' && firstChar <= 'P') {
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

    encodedSegments.add(segments.get(0).encode());
    encodedSegments.add(segments.get(1).encode());
    if(!this.getDisclosedVendors().isEmpty()) {
      encodedSegments.add(segments.get(2).encode());
    }
    
    return String.join(".", encodedSegments);
  }

  @Override
  public void setFieldValue(String fieldName, Object value) throws InvalidFieldException {
    super.setFieldValue(fieldName, value);

    if (!fieldName.equals(TcfCaV1Field.CREATED) && !fieldName.equals(TcfCaV1Field.LAST_UPDATED)) {
      ZonedDateTime utcDateTime = ZonedDateTime.now(DatetimeEncoder.UTC);

      super.setFieldValue(TcfCaV1Field.CREATED, utcDateTime);
      super.setFieldValue(TcfCaV1Field.LAST_UPDATED, utcDateTime);
    }
  }

  
  public ZonedDateTime getCreated() {
    return (ZonedDateTime) this.getFieldValue(TcfCaV1Field.CREATED);
  }

  public ZonedDateTime getLastUpdated() {
    return (ZonedDateTime) this.getFieldValue(TcfCaV1Field.LAST_UPDATED);
  }

  public Integer getCmpId() {
    return (Integer) this.getFieldValue(TcfCaV1Field.CMP_ID);
  }

  public Integer getCmpVersion() {
    return (Integer) this.getFieldValue(TcfCaV1Field.CMP_VERSION);
  }

  public Integer getConsentScreen() {
    return (Integer) this.getFieldValue(TcfCaV1Field.CONSENT_SCREEN);
  }

  public String getConsentLanguage() {
    return (String) this.getFieldValue(TcfCaV1Field.CONSENT_LANGUAGE);
  }

  public Integer getVendorListVersion() {
    return (Integer) this.getFieldValue(TcfCaV1Field.VENDOR_LIST_VERSION);
  }

  public Integer getPolicyVersion() {
    return (Integer) this.getFieldValue(TcfCaV1Field.TCF_POLICY_VERSION);
  }

  public Boolean getUseNonStandardStacks() {
    return (Boolean) this.getFieldValue(TcfCaV1Field.USE_NON_STANDARD_STACKS);
  }

  @SuppressWarnings("unchecked")
  public List<Boolean> getSpecialFeatureExpressConsent() {
    return (List<Boolean>) this.getFieldValue(TcfCaV1Field.SPECIAL_FEATURE_EXPRESS_CONSENT);
  }

  @SuppressWarnings("unchecked")
  public List<Boolean> getPurposesExpressConsent() {
    return (List<Boolean>) this.getFieldValue(TcfCaV1Field.PURPOSES_EXPRESS_CONSENT);
  }

  @SuppressWarnings("unchecked")
  public List<Boolean> getPurposesImpliedConsent() {
    return (List<Boolean>) this.getFieldValue(TcfCaV1Field.PURPOSES_IMPLIED_CONSENT);
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getVendorExpressConsent() {
    return (List<Integer>) this.getFieldValue(TcfCaV1Field.VENDOR_EXPRESS_CONSENT);
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getVendorImpliedConsent() {
    return (List<Integer>) this.getFieldValue(TcfCaV1Field.VENDOR_IMPLIED_CONSENT);
  }

  @SuppressWarnings("unchecked")
  public List<RangeEntry> getPubRestrictions() {
    return (List<RangeEntry>) this.getFieldValue(TcfCaV1Field.PUB_RESTRICTIONS);
  }
  
  public Integer getPubPurposesSegmentType() {
    return (Integer) this.getFieldValue(TcfCaV1Field.PUB_PURPOSES_SEGMENT_TYPE);
  }

  @SuppressWarnings("unchecked")
  public List<Boolean> getPubPurposesExpressConsent() {
    return (List<Boolean>) this.getFieldValue(TcfCaV1Field.PUB_PURPOSES_EXPRESS_CONSENT);
  }

  @SuppressWarnings("unchecked")
  public List<Boolean> getPubPurposesImpliedConsent() {
    return (List<Boolean>) this.getFieldValue(TcfCaV1Field.PUB_PURPOSES_IMPLIED_CONSENT);
  }

  public Integer getNumCustomPurposes() {
    return (Integer) this.getFieldValue(TcfCaV1Field.NUM_CUSTOM_PURPOSES);
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getCustomPurposesExpressConsent() {
    return (List<Integer>) this.getFieldValue(TcfCaV1Field.CUSTOM_PURPOSES_EXPRESS_CONSENT);
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getCustomPurposesImpliedConsent() {
    return (List<Integer>) this.getFieldValue(TcfCaV1Field.CUSTOM_PURPOSES_IMPLIED_CONSENT);
  }

  public Integer getDisclosedVendorsSegmentType() {
    return (Integer) this.getFieldValue(TcfCaV1Field.DISCLOSED_VENDORS_SEGMENT_TYPE);
  }
  
  @SuppressWarnings("unchecked")
  public List<Integer> getDisclosedVendors() {
    return (List<Integer>) this.getFieldValue(TcfCaV1Field.DISCLOSED_VENDORS);
  }

}
