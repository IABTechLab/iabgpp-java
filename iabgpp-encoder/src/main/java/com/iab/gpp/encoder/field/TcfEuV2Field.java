package com.iab.gpp.encoder.field;

import java.util.Arrays;
import java.util.List;

public class TcfEuV2Field {

  public static String VERSION = "Version";
  public static String CREATED = "Created";
  public static String LAST_UPDATED = "LastUpdated";
  public static String CMP_ID = "CmpId";
  public static String CMP_VERSION = "CmpVersion";
  public static String CONSENT_SCREEN = "ConsentScreen";
  public static String CONSENT_LANGUAGE = "ConsentLanguage";
  public static String VENDOR_LIST_VERSION = "VendorListVersion";
  public static String POLICY_VERSION = "PolicyVersion";
  public static String IS_SERVICE_SPECIFIC = "IsServiceSpecific";
  public static String USE_NON_STANDARD_STACKS = "UseNonStandardStacks";
  public static String SPECIAL_FEATURE_OPTINS = "SpecialFeatureOptins";
  public static String PURPOSE_CONSENTS = "PurposeConsents";
  public static String PURPOSE_LEGITIMATE_INTERESTS = "PurposeLegitimateInterests";
  public static String PURPOSE_ONE_TREATMENT = "PurposeOneTreatment";
  public static String PUBLISHER_COUNTRY_CODE = "PublisherCountryCode";
  public static String VENDOR_CONSENTS = "VendorConsents";
  public static String VENDOR_LEGITIMATE_INTERESTS = "VendorLegitimateInterests";
  public static String PUBLISHER_RESTRICTIONS = "PublisherRestrictions";
  public static String PUBLISHER_PURPOSES_SEGMENT_TYPE = "PublisherPurposesSegmentType";
  public static String PUBLISHER_CONSENTS = "PublisherConsents";
  public static String PUBLISHER_LEGITIMATE_INTERESTS = "PublisherLegitimateInterests";
  public static String NUM_CUSTOM_PURPOSES = "NumCustomPurposes";
  public static String PUBLISHER_CUSTOM_CONSENTS = "PublisherCustomConsents";
  public static String PUBLISHER_CUSTOM_LEGITIMATE_INTERESTS = "PublisherCustomLegitimateInterests";
  public static String VENDORS_ALLOWED_SEGMENT_TYPE = "VendorsAllowedSegmentType";
  public static String VENDORS_ALLOWED = "VendorsAllowed";
  public static String VENDORS_DISCLOSED_SEGMENT_TYPE = "VendorsDisclosedSegmentType";
  public static String VENDORS_DISCLOSED = "VendorsDisclosed";

  //@formatter:off
  public static List<String> TCFEUV2_CORE_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
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
  });
  //@formatter:on
  
  //@formatter:off
  public static List<String> TCFEUV2_PUBLISHER_PURPOSES_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      TcfEuV2Field.PUBLISHER_PURPOSES_SEGMENT_TYPE,
      TcfEuV2Field.PUBLISHER_CONSENTS,
      TcfEuV2Field.PUBLISHER_LEGITIMATE_INTERESTS,
      TcfEuV2Field.NUM_CUSTOM_PURPOSES,
      TcfEuV2Field.PUBLISHER_CUSTOM_CONSENTS,
      TcfEuV2Field.PUBLISHER_CUSTOM_LEGITIMATE_INTERESTS,
  });
  //@formatter:on
  
  //@formatter:off
  public static List<String> TCFEUV2_VENDORS_ALLOWED_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      TcfEuV2Field.VENDORS_ALLOWED_SEGMENT_TYPE,
      TcfEuV2Field.VENDORS_ALLOWED,
  });
  //@formatter:on
  
  //@formatter:off
  public static List<String> TCFEUV2_VENDORS_DISCLOSED_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      TcfEuV2Field.VENDORS_DISCLOSED_SEGMENT_TYPE,
      TcfEuV2Field.VENDORS_DISCLOSED,
  });
  //@formatter:on
}
