package com.iab.gpp.encoder;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.TcfEuV2Field;
import com.iab.gpp.encoder.field.UspV1Field;
import com.iab.gpp.encoder.section.TcfEuV2;
import com.iab.gpp.encoder.section.UspV1;

class GppModelTest {

  @Test
  public void testEncode1() throws EncodingException {
    GppModel gppModel = new GppModel();
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));

    String gppString = gppModel.encode();

    Assertions.assertEquals(new ArrayList<>(), gppModel.getSectionIds());
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));

    Assertions.assertEquals("DBAA", gppString);
  }
  
  @Test
  public void testEncode2() throws EncodingException {
    GppModel gppModel = new GppModel();
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));

    gppModel.setFieldValue(UspV1.NAME, UspV1Field.NOTICE, 1);
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE, 2);
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED, 3);

    Assertions.assertEquals(Arrays.asList(7), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));

    String gppString = gppModel.encode();
    Assertions.assertEquals("DBABLA~BbA", gppString);

    Assertions.assertEquals(Arrays.asList(7), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
  }
  
  @Test
  public void testEncode3() throws EncodingException {
    GppModel gppModel = new GppModel();
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));

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

    Assertions.assertEquals(Arrays.asList(5), gppModel.getSectionIds());
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));

    String gppString = gppModel.encode();
    Assertions.assertEquals(2, gppString.split("~").length);

    Assertions.assertEquals(Arrays.asList(5), gppModel.getSectionIds());
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
  }
  
  @Test
  public void testEncode4() throws EncodingException {
    GppModel gppModel = new GppModel();
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));

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

    Assertions.assertEquals(Arrays.asList(5), gppModel.getSectionIds());
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));

    gppModel.setFieldValue(UspV1.NAME, UspV1Field.NOTICE, 1);
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE, 2);
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED, 3);

    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));

    String gppString = gppModel.encode();
    Assertions.assertEquals(3, gppString.split("~").length);

    Assertions.assertEquals(Arrays.asList(5, 7), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
  }
  
  @Test
  public void testDecode1() throws DecodingException {
    String gppString = "DBAA";
    GppModel gppModel = new GppModel(gppString);

    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
  }
  
  @Test
  public void testDecode2() throws DecodingException {
    String gppString = "DBABLA~BbA";
    GppModel gppModel = new GppModel(gppString);

    Assertions.assertEquals(Arrays.asList(7), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));

    Assertions.assertEquals(1, gppModel.getFieldValue(UspV1.NAME, UspV1Field.NOTICE));
    Assertions.assertEquals(2, gppModel.getFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE));
    Assertions.assertEquals(3, gppModel.getFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED));
  }
  
  @Test
  public void testDecode3() throws DecodingException {
    String gppString = "DBABDA~CPdBusAPdBusANwAAAENAwCAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA";
    GppModel gppModel = new GppModel(gppString);

    Assertions.assertEquals(Arrays.asList(5), gppModel.getSectionIds());
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));

    Assertions.assertEquals(2, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.VERSION));
    Assertions.assertEquals(880, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.CMP_ID));
    Assertions.assertEquals(0, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.CMP_VERSION));
    Assertions.assertEquals(0, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.CONSENT_SCREEN));
    Assertions.assertEquals("EN", gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.CONSENT_LANGUAGE));
    Assertions.assertEquals(48, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.VENDOR_LIST_VERSION));
    Assertions.assertEquals(2, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.POLICY_VERSION));
    Assertions.assertEquals(false, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.IS_SERVICE_SPECIFIC));
    Assertions.assertEquals(false, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.USE_NON_STANDARD_STACKS));
    Assertions.assertEquals(false, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.PURPOSE_ONE_TREATMENT));
    Assertions.assertEquals("AA", gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.PUBLISHER_COUNTRY_CODE));
  }
  
  @Test
  public void testDecode4() throws DecodingException {
    String gppString = "DBACDMA~CPdBusAPdBusANwAAAENAwCAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA~BbA";
    GppModel gppModel = new GppModel(gppString);

    Assertions.assertEquals(Arrays.asList(5, 7), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));

    Assertions.assertEquals(1, gppModel.getFieldValue(UspV1.NAME, UspV1Field.NOTICE));
    Assertions.assertEquals(2, gppModel.getFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE));
    Assertions.assertEquals(3, gppModel.getFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED));

    Assertions.assertEquals(2, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.VERSION));
    Assertions.assertEquals(880, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.CMP_ID));
    Assertions.assertEquals(0, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.CMP_VERSION));
    Assertions.assertEquals(0, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.CONSENT_SCREEN));
    Assertions.assertEquals("EN", gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.CONSENT_LANGUAGE));
    Assertions.assertEquals(48, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.VENDOR_LIST_VERSION));
    Assertions.assertEquals(2, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.POLICY_VERSION));
    Assertions.assertEquals(false, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.IS_SERVICE_SPECIFIC));
    Assertions.assertEquals(false, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.USE_NON_STANDARD_STACKS));
    Assertions.assertEquals(false, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.PURPOSE_ONE_TREATMENT));
    Assertions.assertEquals("AA", gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.PUBLISHER_COUNTRY_CODE));
    
    TcfEuV2 tcfEuV2Section = (TcfEuV2)gppModel.getSection(TcfEuV2.NAME);
    Integer tcfEuV2Version = tcfEuV2Section.getVersion();
    String consentLanguage = tcfEuV2Section.getConsentLanguage();
    Assertions.assertEquals(2, tcfEuV2Version);
    Assertions.assertEquals("EN", consentLanguage);
    
    UspV1 uspV1Section = (UspV1)gppModel.getSection(UspV1.NAME);
    Integer uspV1Version = uspV1Section.getVersion();
    Integer notice = uspV1Section.getNotice();
    Assertions.assertEquals(1, uspV1Version);
    Assertions.assertEquals(1, notice);
  }
  
}
