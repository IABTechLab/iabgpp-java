# iabgpp-java

Encode/decode consent information with the IAB GPP Framework

### Usage

#### Maven

The official iabgpp java library is distributed through maven central. Please [search maven central](https://search.maven.org/search?q=g:com.iab.gpp) for the current release version.

#### Decoding

```
<dependency>
  <groupId>com.iab.gpp</groupId>
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
  <groupId>com.iab.gpp</groupId>
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
  <groupId>com.iab.gpp</groupId>
  <artifactId>iabgpp-extras</artifactId>
  <version>VERSION</version>
</dependency>

<dependency>
  <groupId>com.iab.gpp</groupId>
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
