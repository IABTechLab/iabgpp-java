package com.iab.gpp.encoder;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.TcfCaV2Field;
import com.iab.gpp.encoder.field.TcfEuV2Field;
import com.iab.gpp.encoder.field.UspCaV1Field;
import com.iab.gpp.encoder.field.UspCoV1Field;
import com.iab.gpp.encoder.field.UspCtV1Field;
import com.iab.gpp.encoder.field.UspNatV1Field;
import com.iab.gpp.encoder.field.UspUtV1Field;
import com.iab.gpp.encoder.field.UspV1Field;
import com.iab.gpp.encoder.field.UspVaV1Field;
import com.iab.gpp.encoder.section.TcfCaV2;
import com.iab.gpp.encoder.section.TcfEuV2;
import com.iab.gpp.encoder.section.UspCaV1;
import com.iab.gpp.encoder.section.UspCoV1;
import com.iab.gpp.encoder.section.UspCtV1;
import com.iab.gpp.encoder.section.UspNatV1;
import com.iab.gpp.encoder.section.UspUtV1;
import com.iab.gpp.encoder.section.UspV1;
import com.iab.gpp.encoder.section.UspVaV1;

public class GppModelTest {

  private ZonedDateTime utcDateTime = ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC"));

  @Test
  public void testEncodeDefault() throws EncodingException {
    GppModel gppModel = new GppModel();
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.ID));
    
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

    String gppString = gppModel.encode();
    Assertions.assertEquals("DBAA", gppString);

    Assertions.assertEquals(new ArrayList<>(), gppModel.getSectionIds());
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.ID));
    
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

  }
  
  @Test
  public void testEncodeDefaultAll() throws EncodingException {
    GppModel gppModel = new GppModel();
    
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UspNatV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UspCaV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UspVaV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UspCoV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UspUtV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UspCtV1.NAME));
    
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.VERSION, TcfEuV2.VERSION);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfCaV2Field.CREATED, utcDateTime);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfCaV2Field.LAST_UPDATED, utcDateTime);
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.VERSION, TcfCaV2.VERSION);
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.CREATED, utcDateTime);
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.LAST_UPDATED, utcDateTime);
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.VERSION, UspV1.VERSION);
    gppModel.setFieldValue(UspNatV1.NAME, UspNatV1Field.VERSION, UspNatV1.VERSION);
    gppModel.setFieldValue(UspCaV1.NAME, UspCaV1Field.VERSION, UspCaV1.VERSION);
    gppModel.setFieldValue(UspVaV1.NAME, UspVaV1Field.VERSION, UspVaV1.VERSION);
    gppModel.setFieldValue(UspCoV1.NAME, UspCoV1Field.VERSION, UspCoV1.VERSION);
    gppModel.setFieldValue(UspUtV1.NAME, UspUtV1Field.VERSION, UspUtV1.VERSION);
    gppModel.setFieldValue(UspCtV1.NAME, UspCtV1Field.VERSION, UspCtV1.VERSION);
    
    
    
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfCaV2.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UspNatV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UspCaV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UspVaV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UspCoV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UspUtV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UspCtV1.NAME));

    String gppString = gppModel.encode();
    Assertions.assertEquals("DBACOaw~CPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA~CPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAA.YAAAAAAAAAA~BAA~BAAAAAAAAAA.QA~BAAAAAAA.QA~BAAAAAA~BAAAAAA.QA~BAAAAAAA~BAAAAAAA.QA", gppString);

  }

  @Test
  public void testEncodeUspv1() throws EncodingException {
    GppModel gppModel = new GppModel();
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.ID));
    
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

    gppModel.setFieldValue(UspV1.NAME, UspV1Field.NOTICE, 1);
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE, 2);
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED, 3);

    Assertions.assertEquals(Arrays.asList(6), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.ID));

    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

    
    
    String gppString = gppModel.encode();
    Assertions.assertEquals("DBABTA~BbA", gppString);

    Assertions.assertEquals(Arrays.asList(6), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.ID));
    
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

  }

  @Test
  public void testEncodeTcfEuV2() throws EncodingException {
    GppModel gppModel = new GppModel();
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.ID));

    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

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
    gppModel.setFieldValue(TcfEuV2.NAME, TcfCaV2Field.CREATED, utcDateTime);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfCaV2Field.LAST_UPDATED, utcDateTime);

    Assertions.assertEquals(Arrays.asList(2), gppModel.getSectionIds());
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.ID));

    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

    
    String gppString = gppModel.encode();
    Assertions.assertEquals("DBABMA~CPSG_8APSG_8ANwAAAENAwCAAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA", gppString);

    Assertions.assertEquals(2, gppString.split("~").length);

    Assertions.assertEquals(Arrays.asList(2), gppModel.getSectionIds());
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.ID));

    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

  }

  @Test
  public void testEncodeUspV1AndTcfEuV2() throws EncodingException {
    GppModel gppModel = new GppModel();
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));

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
    gppModel.setFieldValue(TcfEuV2.NAME, TcfCaV2Field.CREATED, utcDateTime);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfCaV2Field.LAST_UPDATED, utcDateTime);

    Assertions.assertEquals(Arrays.asList(2), gppModel.getSectionIds());
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

    gppModel.setFieldValue(UspV1.NAME, UspV1Field.NOTICE, 1);
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE, 2);
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED, 3);

    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

    String gppString = gppModel.encode();
    Assertions.assertEquals("DBACNYA~CPSG_8APSG_8ANwAAAENAwCAAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA~BbA", gppString);

    Assertions.assertEquals(3, gppString.split("~").length);

    Assertions.assertEquals(Arrays.asList(2, 6), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

  }

  @Test
  public void testEncodeUspV1AndTcfEuV2AndTcfCaV2() throws EncodingException {
    GppModel gppModel = new GppModel();
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.ID));

    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

    
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
    gppModel.setFieldValue(TcfEuV2.NAME, TcfCaV2Field.CREATED, utcDateTime);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfCaV2Field.LAST_UPDATED, utcDateTime);


    Assertions.assertEquals(Arrays.asList(2), gppModel.getSectionIds());
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.ID));

    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

    gppModel.setFieldValue(UspV1.NAME, UspV1Field.NOTICE, 1);
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE, 2);
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED, 3);

    Assertions.assertEquals(true, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.ID));

    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.CMP_ID, 50);
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.CMP_VERSION, 2);
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.VENDOR_LIST_VERSION, 413);
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.USE_NON_STANDARD_STACKS, true);
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.SPECIAL_FEATURE_EXPRESS_CONSENT,
        Arrays.asList(false, false, false, false, false, false, true, true, true, true, true, true));
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.PURPOSES_EXPRESS_CONSENT,
        Arrays.asList(true, true, true, true, true, true, false, false, false, false, false, false, true, true, true,
            true, true, true, false, false, false, false, false, false));
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.PURPOSES_IMPLIED_CONSENT,
        Arrays.asList(false, false, false, false, false, false, true, true, true, true, true, true, false, false, false,
            false, false, false, true, true, true, true, true, true));
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.VENDOR_EXPRESS_CONSENT, Arrays.asList(12, 24, 48));
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.VENDOR_IMPLIED_CONSENT, Arrays.asList(18, 30));
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.PUB_PURPOSES_EXPRESS_CONSENT,
        Arrays.asList(true, true, true, false, false, false, true, true, true, false, false, false, true, true, true,
            false, false, false, true, true, true, false, false, false));
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.PUB_PURPOSES_IMPLIED_CONSENT,
        Arrays.asList(false, false, false, true, true, true, false, false, false, true, true, true, false, false, false,
            true, true, true, false, false, false, true, true, true));
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.NUM_CUSTOM_PURPOSES, 3);
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.CUSTOM_PURPOSES_EXPRESS_CONSENT,
        Arrays.asList(false, true, false));
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.CUSTOM_PURPOSES_IMPLIED_CONSENT,
        Arrays.asList(true, false, true));

    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.CREATED, utcDateTime);
    gppModel.setFieldValue(TcfCaV2.NAME, TcfCaV2Field.LAST_UPDATED, utcDateTime);

    Assertions.assertEquals(true, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(true, gppModel.hasSection(TcfCaV2.ID));

    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfCaV2.NAME));


    String gppString = gppModel.encode();
    Assertions.assertEquals(
        "DBACOeA~CPSG_8APSG_8ANwAAAENAwCAAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA~CPSG_8APSG_8AAyACAENGdCgf_gfgAfgfgBgAMAAwADAAIACQAHg.fHHHA4444ao~BbA",
        gppString);

    Assertions.assertEquals(4, gppString.split("~").length);

    Assertions.assertEquals(Arrays.asList(2, 5, 6), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(true, gppModel.hasSection(TcfCaV2.ID));
    
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfCaV2.NAME));
  }

  @Test
  public void testDecodeDefaults() throws DecodingException {
    String gppString = "DBAA";
    GppModel gppModel = new GppModel(gppString);

    Assertions.assertEquals(false, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.ID));
    
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

  }
  
  @Test
  public void testDecodeDefaultsAll() throws DecodingException {
    String gppString = "DBACOaw~CPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA~CPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAA.YAAAAAAAAAA~BAA~BAAAAAAAAAA.QA~BAAAAAAA.QA~BAAAAAA~BAAAAAA.QA~BAAAAAAA~BAAAAAAA.QA";
    GppModel gppModel = new GppModel(gppString);

    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfCaV2.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UspNatV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UspCaV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UspVaV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UspCoV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UspUtV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UspCtV1.NAME));
  }

  @Test
  public void testDecodeUspv1() throws DecodingException {
    String gppString = "DBABTA~BbA";
    GppModel gppModel = new GppModel(gppString);

    Assertions.assertEquals(Arrays.asList(6), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.ID));

    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

    Assertions.assertEquals(1, gppModel.getFieldValue(UspV1.ID, UspV1Field.NOTICE));
    Assertions.assertEquals(2, gppModel.getFieldValue(UspV1.ID, UspV1Field.OPT_OUT_SALE));
    Assertions.assertEquals(3, gppModel.getFieldValue(UspV1.ID, UspV1Field.LSPA_COVERED));
    
    Assertions.assertEquals(1, gppModel.getFieldValue(UspV1.NAME, UspV1Field.NOTICE));
    Assertions.assertEquals(2, gppModel.getFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE));
    Assertions.assertEquals(3, gppModel.getFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED));
  }

  @Test
  public void testDecodeTcfEuV2() throws DecodingException {
    String gppString = "DBABMA~CPSG_8APSG_8ANwAAAENAwCAAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA";
    GppModel gppModel = new GppModel(gppString);

    Assertions.assertEquals(Arrays.asList(2), gppModel.getSectionIds());
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.ID));

    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

    Assertions.assertEquals(2, gppModel.getFieldValue(TcfEuV2.ID, TcfEuV2Field.VERSION));
    Assertions.assertEquals(880, gppModel.getFieldValue(TcfEuV2.ID, TcfEuV2Field.CMP_ID));
    Assertions.assertEquals(0, gppModel.getFieldValue(TcfEuV2.ID, TcfEuV2Field.CMP_VERSION));
    Assertions.assertEquals(0, gppModel.getFieldValue(TcfEuV2.ID, TcfEuV2Field.CONSENT_SCREEN));
    Assertions.assertEquals("EN", gppModel.getFieldValue(TcfEuV2.ID, TcfEuV2Field.CONSENT_LANGUAGE));
    Assertions.assertEquals(48, gppModel.getFieldValue(TcfEuV2.ID, TcfEuV2Field.VENDOR_LIST_VERSION));
    Assertions.assertEquals(2, gppModel.getFieldValue(TcfEuV2.ID, TcfEuV2Field.POLICY_VERSION));
    Assertions.assertEquals(false, gppModel.getFieldValue(TcfEuV2.ID, TcfEuV2Field.IS_SERVICE_SPECIFIC));
    Assertions.assertEquals(false, gppModel.getFieldValue(TcfEuV2.ID, TcfEuV2Field.USE_NON_STANDARD_STACKS));
    Assertions.assertEquals(false, gppModel.getFieldValue(TcfEuV2.ID, TcfEuV2Field.PURPOSE_ONE_TREATMENT));
    Assertions.assertEquals("AA", gppModel.getFieldValue(TcfEuV2.ID, TcfEuV2Field.PUBLISHER_COUNTRY_CODE));
    Assertions.assertEquals(utcDateTime, gppModel.getFieldValue(TcfEuV2.ID, TcfEuV2Field.CREATED));
    Assertions.assertEquals(utcDateTime, gppModel.getFieldValue(TcfEuV2.ID, TcfEuV2Field.LAST_UPDATED));
    
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
    Assertions.assertEquals(utcDateTime, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.CREATED));
    Assertions.assertEquals(utcDateTime, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.LAST_UPDATED));
  }

  @Test
  public void testDecodeUspv1AndTcfEuV2() throws DecodingException {
    String gppString = "DBACNYA~CPSG_8APSG_8ANwAAAENAwCAAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA~BbA";
    GppModel gppModel = new GppModel(gppString);

    Assertions.assertEquals(Arrays.asList(2, 6), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.ID));

    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV2.NAME));

    
    Assertions.assertEquals(1, gppModel.getFieldValue(UspV1.ID, UspV1Field.NOTICE));
    Assertions.assertEquals(2, gppModel.getFieldValue(UspV1.ID, UspV1Field.OPT_OUT_SALE));
    Assertions.assertEquals(3, gppModel.getFieldValue(UspV1.ID, UspV1Field.LSPA_COVERED));

    Assertions.assertEquals(1, gppModel.getFieldValue(UspV1.NAME, UspV1Field.NOTICE));
    Assertions.assertEquals(2, gppModel.getFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE));
    Assertions.assertEquals(3, gppModel.getFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED));

    Assertions.assertEquals(2, gppModel.getFieldValue(TcfEuV2.ID, TcfEuV2Field.VERSION));
    Assertions.assertEquals(880, gppModel.getFieldValue(TcfEuV2.ID, TcfEuV2Field.CMP_ID));
    Assertions.assertEquals(0, gppModel.getFieldValue(TcfEuV2.ID, TcfEuV2Field.CMP_VERSION));
    Assertions.assertEquals(0, gppModel.getFieldValue(TcfEuV2.ID, TcfEuV2Field.CONSENT_SCREEN));
    Assertions.assertEquals("EN", gppModel.getFieldValue(TcfEuV2.ID, TcfEuV2Field.CONSENT_LANGUAGE));
    Assertions.assertEquals(48, gppModel.getFieldValue(TcfEuV2.ID, TcfEuV2Field.VENDOR_LIST_VERSION));
    Assertions.assertEquals(2, gppModel.getFieldValue(TcfEuV2.ID, TcfEuV2Field.POLICY_VERSION));
    Assertions.assertEquals(false, gppModel.getFieldValue(TcfEuV2.ID, TcfEuV2Field.IS_SERVICE_SPECIFIC));
    Assertions.assertEquals(false, gppModel.getFieldValue(TcfEuV2.ID, TcfEuV2Field.USE_NON_STANDARD_STACKS));
    Assertions.assertEquals(false, gppModel.getFieldValue(TcfEuV2.ID, TcfEuV2Field.PURPOSE_ONE_TREATMENT));
    Assertions.assertEquals("AA", gppModel.getFieldValue(TcfEuV2.ID, TcfEuV2Field.PUBLISHER_COUNTRY_CODE));
    Assertions.assertEquals(utcDateTime, gppModel.getFieldValue(TcfEuV2.ID, TcfEuV2Field.CREATED));
    Assertions.assertEquals(utcDateTime, gppModel.getFieldValue(TcfEuV2.ID, TcfEuV2Field.LAST_UPDATED));

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
    Assertions.assertEquals(utcDateTime, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.CREATED));
    Assertions.assertEquals(utcDateTime, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.LAST_UPDATED));

    TcfEuV2 tcfEuV2Section = (TcfEuV2) gppModel.getSection(TcfEuV2.NAME);
    Integer tcfEuV2Version = tcfEuV2Section.getVersion();
    String consentLanguage = tcfEuV2Section.getConsentLanguage();
    Assertions.assertEquals(2, tcfEuV2Version);
    Assertions.assertEquals("EN", consentLanguage);

    UspV1 uspV1Section = (UspV1) gppModel.getSection(UspV1.NAME);
    Integer uspV1Version = uspV1Section.getVersion();
    Integer notice = uspV1Section.getNotice();
    Assertions.assertEquals(1, uspV1Version);
    Assertions.assertEquals(1, notice);
  }

  @Test
  public void testDecodeUspv1AndTcfEuV2AndTcfCaV2() throws DecodingException {
    String gppString =
        "DBACOeA~CPSG_8APSG_8ANwAAAENAwCAAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA~CPSG_8APSG_8AAyACAENGdCgf_gfgAfgfgBgAMAAwADAAIACQAHg.fHHHA4444ao~BbA";
    GppModel gppModel = new GppModel(gppString);

    Assertions.assertEquals(Arrays.asList(2, 5, 6), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfCaV2.NAME));

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
    Assertions.assertEquals(utcDateTime, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.CREATED));
    Assertions.assertEquals(utcDateTime, gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.LAST_UPDATED));

    TcfEuV2 tcfEuV2Section = (TcfEuV2) gppModel.getSection(TcfEuV2.NAME);
    Integer tcfEuV2Version = tcfEuV2Section.getVersion();
    String consentLanguage = tcfEuV2Section.getConsentLanguage();
    Assertions.assertEquals(2, tcfEuV2Version);
    Assertions.assertEquals("EN", consentLanguage);

    UspV1 uspV1Section = (UspV1) gppModel.getSection(UspV1.NAME);
    Integer uspV1Version = uspV1Section.getVersion();
    Integer notice = uspV1Section.getNotice();
    Assertions.assertEquals(1, uspV1Version);
    Assertions.assertEquals(1, notice);

    TcfCaV2 tcfCaV2Section = (TcfCaV2) gppModel.getSection(TcfCaV2.NAME);
    Assertions.assertEquals(50, tcfCaV2Section.getCmpId());
    Assertions.assertEquals(2, tcfCaV2Section.getCmpVersion());
    Assertions.assertEquals(413, tcfCaV2Section.getVendorListVersion());
    Assertions.assertEquals(true, tcfCaV2Section.getUseNonStandardStacks());
    Assertions.assertEquals(Arrays.asList(false, false, false, false, false, false, true, true, true, true, true, true),
        tcfCaV2Section.getSpecialFeatureExpressConsent());
    Assertions
        .assertEquals(
            Arrays.asList(true, true, true, true, true, true, false, false, false, false, false, false, true, true,
                true, true, true, true, false, false, false, false, false, false),
            tcfCaV2Section.getPurposesExpressConsent());
    Assertions
        .assertEquals(
            Arrays.asList(false, false, false, false, false, false, true, true, true, true, true, true, false, false,
                false, false, false, false, true, true, true, true, true, true),
            tcfCaV2Section.getPurposesImpliedConsent());
    Assertions.assertEquals(Arrays.asList(12, 24, 48), tcfCaV2Section.getVendorExpressConsent());
    Assertions.assertEquals(Arrays.asList(18, 30), tcfCaV2Section.getVendorImpliedConsent());
    Assertions
        .assertEquals(
            Arrays.asList(true, true, true, false, false, false, true, true, true, false, false, false, true, true,
                true, false, false, false, true, true, true, false, false, false),
            tcfCaV2Section.getPubPurposesExpressConsent());
    Assertions
        .assertEquals(
            Arrays.asList(false, false, false, true, true, true, false, false, false, true, true, true, false, false,
                false, true, true, true, false, false, false, true, true, true),
            tcfCaV2Section.getPubPurposesImpliedConsent());
    Assertions.assertEquals(3, tcfCaV2Section.getNumCustomPurposes());
    Assertions.assertEquals(Arrays.asList(false, true, false), tcfCaV2Section.getCustomPurposesExpressConsent());
    Assertions.assertEquals(Arrays.asList(true, false, true), tcfCaV2Section.getCustomPurposesImpliedConsent());
    Assertions.assertEquals(utcDateTime, tcfCaV2Section.getCreated());
    Assertions.assertEquals(utcDateTime, tcfCaV2Section.getLastUpdated());

    Assertions.assertEquals("EN", tcfCaV2Section.getConsentLanguage());
    Assertions.assertEquals(5, tcfCaV2Section.getId());
    Assertions.assertEquals(3, tcfCaV2Section.getPubPurposesSegmentType());
  }
  
}
