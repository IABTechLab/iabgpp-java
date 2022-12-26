# iabgpp-java

Encode/decode consent information with the IAB GPP Framework

### Usage

#### Maven

The official iabgpp java library is distributed through maven central. Please [search maven central](https://search.maven.org/search?q=g:com.iabgpp) for the current release version.

#### Decoding

```
<dependency>
  <groupId>com.iabgpp</groupId>
  <artifactId>iabgpp-encoder</artifactId>
  <version>VERSION</version>
</dependency>
```

```
import com.iab.gpp.encoder.GppModel;
import com.iab.gpp.encoder.section.TcfEuV2;
import com.iab.gpp.encoder.section.UspV1;

String gppString = "DBACNYAA~CPSG_8APSG_8ANwAAAENAwCAAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA~BbAA";
GppModel gppModel = new GppModel(gppString);

TcfEuV2 tcfEuV2Section = (TcfEuV2)gppModel.getSection(TcfEuV2.NAME);
Integer tcfEuV2Version = tcfEuV2Section.getVersion();
String tcfEuV2ConsentLanguage = tcfEuV2Section.getConsentLanguage();
    
UspV1 uspV1Section = (UspV1)gppModel.getSection(UspV1.NAME);
Integer uspV1Version = uspV1Section.getVersion();
Integer uspV1Notice = uspV1Section.getNotice();
```

#### Encoding

```
<dependency>
  <groupId>com.iabgpp</groupId>
  <artifactId>iabgpp-encoder</artifactId>
  <version>VERSION</version>
</dependency>
```

```
import com.iab.gpp.encoder.GppModel;
import com.iab.gpp.encoder.section.TcfEuV2;
import com.iab.gpp.encoder.section.UspV1;

GppModel gppModel = new GppModel();

gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.VERSION, 2);
gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.CMP_ID, 880);
gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.CMP_VERSION, 0);
gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.CONSENT_SCREEN, 0);
gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.CONSENT_LANGUAGE, "EN");
gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.VENDOR_LIST_VERSION, 48);
gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.POLICY_VERSION, 2);
gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.IS_SERVICE_SPECIFIC, false);
gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.USE_NON_STANDARD_STACKS, false);
gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.PURPOSE_ONE_TREATMENT, false);
gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.PUBLISHER_COUNTRY_CODE, "AA");

gppModel.setFieldValue(UspV1.NAME, UspV1Field.NOTICE, 1);
gppModel.setFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE, 2);
gppModel.setFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED, 3);

String gppString = gppModel.encode();
```

#### GVL & CMP List

The `iabgpp-extras` and `iabgpp-extras-jackson` libraries provides an interface and ability to parse the GVL and CMP 
List respectively. The `iabgpp-extras-jackson` library uses Jackson 2.10.3 to parse the GVL and CMP List JSON.

```
<dependency>
  <groupId>com.iabgpp</groupId>
  <artifactId>iabgpp-extras</artifactId>
  <version>VERSION</version>
</dependency>

<dependency>
  <groupId>com.iabgpp</groupId>
  <artifactId>iabgpp-extras-jackson</artifactId>
  <version>VERSION</version>
</dependency>
```


Example of parsing the GVL,

```
import com.iab.gpp.extras.jackson.Loader;
import com.iab.gpp.extras.gvl.Gvl;

String gvlContent = "...";
Loader loader = new Loader();
Gvl gvl = loader.globalVendorList(gvlContent); 
```

Example of parsing the CMP List,

```
import com.iab.gpp.extras.jackson.Loader;
import com.iab.gpp.extras.cmp.CmpList;

String cmpListContent = "...";
Loader loader = new Loader();
CmpList cmpList = loader.cmpList(cmpListContent); 
```

### Fields

|Section Name|Section ID|Field|Data Type/Value|
|------------|----------|-----|---------------|
|tcfeuv2|2|Version|6 bit int. Value is 2.|
|tcfeuv2|2|Created|Datetime. Updated when fields are set|
|tcfeuv2|2|LastUpdated|Datetime. Updated when fields are set|
|tcfeuv2|2|CmpId|12 bit int|
|tcfeuv2|2|CmpVersion|12 bit int|
|tcfeuv2|2|ConsentScreen|6 bit int|
|tcfeuv2|2|ConsentLanguage|2 character country code|
|tcfeuv2|2|VendorListVersion|12 bit int|
|tcfeuv2|2|PolicyVersion|6 bit int. Value is 2|
|tcfeuv2|2|IsServiceSpecific|Boolean|
|tcfeuv2|2|UseNonStandardStacks|Boolean|
|tcfeuv2|2|SpecialFeatureOptins|Boolean array of size 12|
|tcfeuv2|2|PurposeConsents|Boolean array of size 24|
|tcfeuv2|2|PurposeLegitimateInterests|Boolean array of size 24|
|tcfeuv2|2|PurposeOneTreatment|Boolean|
|tcfeuv2|2|PublisherCountryCode|2 character country code|
|tcfeuv2|2|VendorConsents|Integer array of variable size|
|tcfeuv2|2|VendorLegitimateInterests|Integer array of variable size|
|tcfeuv2|2|PublisherRestrictions|Integer array of variable size|
|tcfeuv2|2|PublisherPurposesSegmentType|3 bit int. Value is 3|
|tcfeuv2|2|PublisherConsents|Boolean array of size 24|
|tcfeuv2|2|PublisherLegitimateInterests|Boolean array of size 24|
|tcfeuv2|2|NumCustomPurposes|6 bit int|
|tcfeuv2|2|PublisherCustomConsents|Boolean array where size is set by the NumCustomPurposes field|
|tcfeuv2|2|PublisherCustomLegitimateInterests|Boolean array where size is set by the NumCustomPurposes field|
|tcfeuv2|2|VendorsAllowedSegmentType|3 bit int. Value is 2|
|tcfeuv2|2|VendorsAllowed|Integer array of variable size|
|tcfeuv2|2|VendorsDisclosedSegmentType|3 bit int. Value is 1|
|tcfeuv2|2|VendorsDisclosed|Integer array of variable size|
|tcfcav2|5|Version|6 bit int. Value is 2.|
|tcfcav2|5|Created|Datetime. Updated when any fields are set|
|tcfcav2|5|LastUpdated|Datetime. Updated when any fields are set|
|tcfcav2|5|CmpId|12 bit int|
|tcfcav2|5|CmpVersion|12 bit int|
|tcfcav2|5|ConsentScreen|6 bit int|
|tcfcav2|5|ConsentLanguage|2 character country code|
|tcfcav2|5|VendorListVersion|12 bit int|
|tcfcav2|5|TcfPolicyVersion|6 bit int. Value is 2.|
|tcfcav2|5|UseNonStandardStacks|Boolean|
|tcfcav2|5|SpecialFeatureExpressConsent|Boolean array of size 12|
|tcfcav2|5|PurposesExpressConsent|Boolean array of size 24|
|tcfcav2|5|PurposesImpliedConsent|Boolean array of size 24|
|tcfcav2|5|VendorExpressConsent|Integer array of variable size|
|tcfcav2|5|VendorImpliedConsent|Integer array of variable size|
|tcfcav2|5|PubPurposesSegmentType|3 bit int. Value is 3|
|tcfcav2|5|PubPurposesExpressConsent|Boolean array of size 24|
|tcfcav2|5|PubPurposesImpliedConsent|Boolean array of size 24|
|tcfcav2|5|NumCustomPurposes|6 bit int|
|tcfcav2|5|CustomPurposesExpressConsent|Boolean array where size is set by the NumCustomPurposes field|
|tcfcav2|5|CustomPurposesImpliedConsent|Boolean array where size is set by the NumCustomPurposes field|
|uspv1|6|Version|6 bit int. Value is 1|
|uspv1|6|Notice|2 bit int|
|uspv1|6|OptOutSale|2 bit int|
|uspv1|6|LspaCovered|2 bit int|
|uspnatv1|7|Version|6 bit int. Value is 1|
|uspnatv1|7|SharingNotice|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspnatv1|7|SaleOptOutNotice|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspnatv1|7|SharingOptOutNotice|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspnatv1|7|TargetedAdvertisingOptOutNotice|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspnatv1|7|SensitiveDataProcessingOptOutNotice|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspnatv1|7|SensitiveDataLimitUseNotice|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspnatv1|7|SaleOptOut|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspnatv1|7|SharingOptOut|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspnatv1|7|TargetedAdvertisingOptOut|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspnatv1|7|SensitiveDataProcessing|2 bit int array of size 12. 0=Not applicable, 1=Yes, 2=No|
|uspnatv1|7|KnownChildSensitiveDataConsents|2 bit int array of size 2. 0=Not applicable, 1=Yes, 2=No|
|uspnatv1|7|PersonalDataConsents|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspnatv1|7|MspaCoveredTransaction|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspnatv1|7|MspaOptOutOptionMode|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspnatv1|7|MspaServiceProviderMode|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspnatv1|7|GpcSegmentType|2 bit int. Value is 1|
|uspnatv1|7|Gpc|Boolean|
|uspcav1|8|Version|6 bit int. Value is 1|
|uspcav1|8|SaleOptOutNotice|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspcav1|8|SharingOptOutNotice|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspcav1|8|SensitiveDataLimitUseNotice|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspcav1|8|SaleOptOut|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspcav1|8|SharingOptOut|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspcav1|8|SensitiveDataProcessing|2 bit int array of size 9. 0=Not applicable, 1=Yes, 2=No|
|uspcav1|8|KnownChildSensitiveDataConsents|2 bit int array of size 2. 0=Not applicable, 1=Yes, 2=No|
|uspcav1|8|PersonalDataConsents|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspcav1|8|MspaCoveredTransaction|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspcav1|8|MspaOptOutOptionMode|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspcav1|8|MspaServiceProviderMode|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspcav1|8|GpcSegmentType|2 bit int. Value is 1|
|uspcav1|8|Gpc|Boolean|
|uspvav1|9|Version|6 bit int. Value is 1|
|uspvav1|9|SharingNotice|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspvav1|9|SaleOptOutNotice|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspvav1|9|TargetedAdvertisingOptOutNotice|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspvav1|9|SaleOptOut|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspvav1|9|TargetedAdvertisingOptOut|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspvav1|9|SensitiveDataProcessing|2 bit int array of size 8. 0=Not applicable, 1=Yes, 2=No|
|uspvav1|9|KnownChildSensitiveDataConsents|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspvav1|9|MspaCoveredTransaction|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspvav1|9|MspaOptOutOptionMode|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspvav1|9|MspaServiceProviderMode|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspcov1|10|Version|6 bit int. Value is 1|
|uspcov1|10|SharingNotice|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspcov1|10|SaleOptOutNotice|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspcov1|10|TargetedAdvertisingOptOutNotice|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspcov1|10|SaleOptOut|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspcov1|10|TargetedAdvertisingOptOut|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspcov1|10|SensitiveDataProcessing|2 bit int array of size 7. 0=Not applicable, 1=Yes, 2=No|
|uspcov1|10|KnownChildSensitiveDataConsents|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspcov1|10|MspaCoveredTransaction|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspcov1|10|MspaOptOutOptionMode|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspcov1|10|MspaServiceProviderMode|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspcov1|10|GpcSegmentType|2 bit int. Value is 1|
|uspcov1|10|Gpc|Boolean|
|usputv1|11|Version|6 bit int. Value is 1|
|usputv1|11|SharingNotice|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|usputv1|11|SaleOptOutNotice|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|usputv1|11|TargetedAdvertisingOptOutNotice|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|usputv1|11|SensitiveDataProcessingOptOutNotice|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|usputv1|11|SaleOptOut|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|usputv1|11|TargetedAdvertisingOptOut|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|usputv1|11|SensitiveDataProcessing|2 bit int array of size 8. 0=Not applicable, 1=Yes, 2=No|
|usputv1|11|KnownChildSensitiveDataConsents|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|usputv1|11|MspaCoveredTransaction|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|usputv1|11|MspaOptOutOptionMode|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|usputv1|11|MspaServiceProviderMode|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspctv1|12|Version|6 bit int. Value is 1|
|uspctv1|12|SharingNotice|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspctv1|12|SaleOptOutNotice|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspctv1|12|TargetedAdvertisingOptOutNotice|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspctv1|12|SaleOptOut|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspctv1|12|TargetedAdvertisingOptOut|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspctv1|12|SensitiveDataProcessing|2 bit int array of size 8. 0=Not applicable, 1=Yes, 2=No|
|uspctv1|12|KnownChildSensitiveDataConsents|2 bit int array of size 3. 0=Not applicable, 1=Yes, 2=No|
|uspctv1|12|MspaCoveredTransaction|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspctv1|12|MspaOptOutOptionMode|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspctv1|12|MspaServiceProviderMode|2 bit int. 0=Not applicable, 1=Yes, 2=No|
|uspctv1|12|GpcSegmentType|2 bit int. Value is 1|
|uspctv1|12|Gpc|Boolean|

