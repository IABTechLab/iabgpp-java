package com.iab.gpp.encoder;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.field.TcfCaV1Field;
import com.iab.gpp.encoder.field.TcfEuV2Field;
import com.iab.gpp.encoder.field.UsCaV1Field;
import com.iab.gpp.encoder.field.UsCoV1Field;
import com.iab.gpp.encoder.field.UsCtV1Field;
import com.iab.gpp.encoder.field.UsNatV1Field;
import com.iab.gpp.encoder.field.UsUtV1Field;
import com.iab.gpp.encoder.field.UspV1Field;
import com.iab.gpp.encoder.field.UsVaV1Field;
import com.iab.gpp.encoder.section.TcfCaV1;
import com.iab.gpp.encoder.section.TcfEuV2;
import com.iab.gpp.encoder.section.UsCaV1;
import com.iab.gpp.encoder.section.UsCoV1;
import com.iab.gpp.encoder.section.UsCtV1;
import com.iab.gpp.encoder.section.UsNatV1;
import com.iab.gpp.encoder.section.UsUtV1;
import com.iab.gpp.encoder.section.UspV1;
import com.iab.gpp.encoder.section.UsVaV1;

public class GppModelTest {

  private ZonedDateTime utcDateTime = ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC"));

  @Test
  public void testEncodeDefault() throws EncodingException {
    GppModel gppModel = new GppModel();
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.ID));

    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.NAME));

    String gppString = gppModel.encode();
    Assertions.assertEquals("DBAA", gppString);

    Assertions.assertEquals(new ArrayList<>(), gppModel.getSectionIds());
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.ID));

    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.NAME));

  }

  @Test
  public void testEncodeDefaultAll() throws EncodingException, InvalidFieldException {
    GppModel gppModel = new GppModel();

    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UsNatV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UsCaV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UsVaV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UsCoV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UsUtV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UsCtV1.NAME));

    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.VERSION, TcfEuV2.VERSION);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfCaV1Field.CREATED, utcDateTime);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfCaV1Field.LAST_UPDATED, utcDateTime);
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.VERSION, TcfCaV1.VERSION);
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.CREATED, utcDateTime);
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.LAST_UPDATED, utcDateTime);
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.VERSION, UspV1.VERSION);
    gppModel.setFieldValue(UsNatV1.NAME, UsNatV1Field.VERSION, UsNatV1.VERSION);
    gppModel.setFieldValue(UsCaV1.NAME, UsCaV1Field.VERSION, UsCaV1.VERSION);
    gppModel.setFieldValue(UsVaV1.NAME, UsVaV1Field.VERSION, UsVaV1.VERSION);
    gppModel.setFieldValue(UsCoV1.NAME, UsCoV1Field.VERSION, UsCoV1.VERSION);
    gppModel.setFieldValue(UsUtV1.NAME, UsUtV1Field.VERSION, UsUtV1.VERSION);
    gppModel.setFieldValue(UsCtV1.NAME, UsCtV1Field.VERSION, UsCtV1.VERSION);



    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfCaV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsNatV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsCaV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsVaV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsCoV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsUtV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsCtV1.NAME));

    String gppString = gppModel.encode();
    Assertions.assertEquals(
        "DBACOaw~CPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA~BPSG_8APSG_8AAAAAAENAABAAAAAAAAAAAAAAAAA.YAAAAAAAAAA~1---~BAAAAAAAAAA.QA~BAAAAAAA.QA~BAAAAAA~BAAAAAA.QA~BAAAAAAA~BAAAAAAA.QA",
        gppString);

  }

  @Test
  public void testEncodeUspv1() throws EncodingException, InvalidFieldException {
    GppModel gppModel = new GppModel();
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.ID));

    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.NAME));

    gppModel.setFieldValue(UspV1.NAME, UspV1Field.NOTICE, "Y");
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE, "N");
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED, "N");

    Assertions.assertEquals(Arrays.asList(6), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.ID));

    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.NAME));



    String gppString = gppModel.encode();
    Assertions.assertEquals("DBABTA~1YNN", gppString);

    Assertions.assertEquals(Arrays.asList(6), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.ID));

    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.NAME));

  }

  @Test
  public void testEncodeTcfEuV2() throws EncodingException, InvalidFieldException {
    GppModel gppModel = new GppModel();
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.ID));

    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.NAME));

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
    gppModel.setFieldValue(TcfEuV2.NAME, TcfCaV1Field.CREATED, utcDateTime);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfCaV1Field.LAST_UPDATED, utcDateTime);

    Assertions.assertEquals(Arrays.asList(2), gppModel.getSectionIds());
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.ID));

    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.NAME));


    String gppString = gppModel.encode();
    Assertions.assertEquals("DBABMA~CPSG_8APSG_8ANwAAAENAwCAAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA", gppString);

    Assertions.assertEquals(2, gppString.split("~").length);

    Assertions.assertEquals(Arrays.asList(2), gppModel.getSectionIds());
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.ID));

    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.NAME));

  }

  @Test
  public void testEncodeUspV1AndTcfEuV2() throws EncodingException, InvalidFieldException {
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
    gppModel.setFieldValue(TcfEuV2.NAME, TcfCaV1Field.CREATED, utcDateTime);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfCaV1Field.LAST_UPDATED, utcDateTime);

    Assertions.assertEquals(Arrays.asList(2), gppModel.getSectionIds());
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.NAME));

    gppModel.setFieldValue(UspV1.NAME, UspV1Field.NOTICE, "Y");
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE, "N");
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED, "N");

    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.NAME));

    String gppString = gppModel.encode();
    Assertions.assertEquals("DBACNYA~CPSG_8APSG_8ANwAAAENAwCAAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA~1YNN", gppString);

    Assertions.assertEquals(3, gppString.split("~").length);

    Assertions.assertEquals(Arrays.asList(2, 6), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.NAME));

  }

  @Test
  public void testEncodeUspV1AndTcfEuV2AndTcfCaV1() throws EncodingException, InvalidFieldException {
    GppModel gppModel = new GppModel();
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.ID));

    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.NAME));


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
    gppModel.setFieldValue(TcfEuV2.NAME, TcfCaV1Field.CREATED, utcDateTime);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfCaV1Field.LAST_UPDATED, utcDateTime);


    Assertions.assertEquals(Arrays.asList(2), gppModel.getSectionIds());
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.ID));

    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.NAME));

    gppModel.setFieldValue(UspV1.NAME, UspV1Field.NOTICE, "Y");
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE, "N");
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED, "N");

    Assertions.assertEquals(true, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.ID));

    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.NAME));

    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.CMP_ID, 50);
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.CMP_VERSION, 2);
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.VENDOR_LIST_VERSION, 413);
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.USE_NON_STANDARD_STACKS, true);
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.SPECIAL_FEATURE_EXPRESS_CONSENT,
        Arrays.asList(false, false, false, false, false, false, true, true, true, true, true, true));
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.PURPOSES_EXPRESS_CONSENT,
        Arrays.asList(true, true, true, true, true, true, false, false, false, false, false, false, true, true, true,
            true, true, true, false, false, false, false, false, false));
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.PURPOSES_IMPLIED_CONSENT,
        Arrays.asList(false, false, false, false, false, false, true, true, true, true, true, true, false, false, false,
            false, false, false, true, true, true, true, true, true));
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.VENDOR_EXPRESS_CONSENT, Arrays.asList(12, 24, 48));
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.VENDOR_IMPLIED_CONSENT, Arrays.asList(18, 30));
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.PUB_PURPOSES_EXPRESS_CONSENT,
        Arrays.asList(true, true, true, false, false, false, true, true, true, false, false, false, true, true, true,
            false, false, false, true, true, true, false, false, false));
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.PUB_PURPOSES_IMPLIED_CONSENT,
        Arrays.asList(false, false, false, true, true, true, false, false, false, true, true, true, false, false, false,
            true, true, true, false, false, false, true, true, true));
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.NUM_CUSTOM_PURPOSES, 3);
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.CUSTOM_PURPOSES_EXPRESS_CONSENT,
        Arrays.asList(false, true, false));
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.CUSTOM_PURPOSES_IMPLIED_CONSENT,
        Arrays.asList(true, false, true));

    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.CREATED, utcDateTime);
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.LAST_UPDATED, utcDateTime);

    Assertions.assertEquals(true, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(true, gppModel.hasSection(TcfCaV1.ID));

    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfCaV1.NAME));


    String gppString = gppModel.encode();
    Assertions.assertEquals(
        "DBACOeA~CPSG_8APSG_8ANwAAAENAwCAAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA~BPSG_8APSG_8AAyACAENGdBgf_gfgAfgfgBgABABAAABAB4AACAC.fHHHA4444ao~1YNN",
        gppString);

    Assertions.assertEquals(4, gppString.split("~").length);

    Assertions.assertEquals(Arrays.asList(2, 5, 6), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(true, gppModel.hasSection(TcfCaV1.ID));

    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfCaV1.NAME));
  }

  @Test
  public void testDecodeDefaults() throws DecodingException {
    String gppString = "DBAA";
    GppModel gppModel = new GppModel(gppString);

    Assertions.assertEquals(false, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.ID));

    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.NAME));

  }

  @Test
  public void testDecodeDefaultsAll() throws DecodingException {
    String gppString =
        "DBACOaw~CPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA~BPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAAAA.YAAAAAAAAAA~1---~BAAAAAAAAAA.QA~BAAAAAAA.QA~BAAAAAA~BAAAAAA.QA~BAAAAAAA~BAAAAAAA.QA";
    GppModel gppModel = new GppModel(gppString);

    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfCaV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsNatV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsCaV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsVaV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsCoV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsUtV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsCtV1.NAME));
  }

  @Test
  public void testDecodeUspv1() throws DecodingException {
    String gppString = "DBABTA~1YNN";
    GppModel gppModel = new GppModel(gppString);

    Assertions.assertEquals(Arrays.asList(6), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.ID));

    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.NAME));

    Assertions.assertEquals("Y", gppModel.getFieldValue(UspV1.ID, UspV1Field.NOTICE));
    Assertions.assertEquals("N", gppModel.getFieldValue(UspV1.ID, UspV1Field.OPT_OUT_SALE));
    Assertions.assertEquals("N", gppModel.getFieldValue(UspV1.ID, UspV1Field.LSPA_COVERED));

    Assertions.assertEquals("Y", gppModel.getFieldValue(UspV1.NAME, UspV1Field.NOTICE));
    Assertions.assertEquals("N", gppModel.getFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE));
    Assertions.assertEquals("N", gppModel.getFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED));
  }

  @Test
  public void testDecodeTcfEuV2() throws DecodingException {
    String gppString = "DBABMA~CPSG_8APSG_8ANwAAAENAwCAAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA";
    GppModel gppModel = new GppModel(gppString);

    Assertions.assertEquals(Arrays.asList(2), gppModel.getSectionIds());
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.ID));

    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.NAME));

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
    String gppString = "DBACNYA~CPSG_8APSG_8ANwAAAENAwCAAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA~1YNN";
    GppModel gppModel = new GppModel(gppString);

    Assertions.assertEquals(Arrays.asList(2, 6), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.ID));

    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.NAME));


    Assertions.assertEquals("Y", gppModel.getFieldValue(UspV1.ID, UspV1Field.NOTICE));
    Assertions.assertEquals("N", gppModel.getFieldValue(UspV1.ID, UspV1Field.OPT_OUT_SALE));
    Assertions.assertEquals("N", gppModel.getFieldValue(UspV1.ID, UspV1Field.LSPA_COVERED));

    Assertions.assertEquals("Y", gppModel.getFieldValue(UspV1.NAME, UspV1Field.NOTICE));
    Assertions.assertEquals("N", gppModel.getFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE));
    Assertions.assertEquals("N", gppModel.getFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED));

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
    String notice = uspV1Section.getNotice();
    Assertions.assertEquals(1, uspV1Version);
    Assertions.assertEquals("Y", notice);
  }

  @Test
  public void testDecodeUspv1AndTcfEuV2AndTcfCaV1() throws DecodingException {
    String gppString =
        "DBACOeA~CPSG_8APSG_8ANwAAAENAwCAAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA~BPSG_8APSG_8AAyACAENGdCgf_gfgAfgfgBgABABAAABAB4AACAC.fHHHA4444ao~1YNN";
    GppModel gppModel = new GppModel(gppString);

    Assertions.assertEquals(Arrays.asList(2, 5, 6), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfCaV1.NAME));

    Assertions.assertEquals("Y", gppModel.getFieldValue(UspV1.NAME, UspV1Field.NOTICE));
    Assertions.assertEquals("N", gppModel.getFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE));
    Assertions.assertEquals("N", gppModel.getFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED));

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
    String notice = uspV1Section.getNotice();
    Assertions.assertEquals(1, uspV1Version);
    Assertions.assertEquals("Y", notice);

    TcfCaV1 tcfCaV1Section = (TcfCaV1) gppModel.getSection(TcfCaV1.NAME);
    Assertions.assertEquals(50, tcfCaV1Section.getCmpId());
    Assertions.assertEquals(2, tcfCaV1Section.getCmpVersion());
    Assertions.assertEquals(413, tcfCaV1Section.getVendorListVersion());
    Assertions.assertEquals(true, tcfCaV1Section.getUseNonStandardStacks());
    Assertions.assertEquals(Arrays.asList(false, false, false, false, false, false, true, true, true, true, true, true),
        tcfCaV1Section.getSpecialFeatureExpressConsent());
    Assertions
        .assertEquals(
            Arrays.asList(true, true, true, true, true, true, false, false, false, false, false, false, true, true,
                true, true, true, true, false, false, false, false, false, false),
            tcfCaV1Section.getPurposesExpressConsent());
    Assertions
        .assertEquals(
            Arrays.asList(false, false, false, false, false, false, true, true, true, true, true, true, false, false,
                false, false, false, false, true, true, true, true, true, true),
            tcfCaV1Section.getPurposesImpliedConsent());
    Assertions.assertEquals(Arrays.asList(12, 24, 48), tcfCaV1Section.getVendorExpressConsent());
    Assertions.assertEquals(Arrays.asList(18, 30), tcfCaV1Section.getVendorImpliedConsent());
    Assertions
        .assertEquals(
            Arrays.asList(true, true, true, false, false, false, true, true, true, false, false, false, true, true,
                true, false, false, false, true, true, true, false, false, false),
            tcfCaV1Section.getPubPurposesExpressConsent());
    Assertions
        .assertEquals(
            Arrays.asList(false, false, false, true, true, true, false, false, false, true, true, true, false, false,
                false, true, true, true, false, false, false, true, true, true),
            tcfCaV1Section.getPubPurposesImpliedConsent());
    Assertions.assertEquals(3, tcfCaV1Section.getNumCustomPurposes());
    Assertions.assertEquals(Arrays.asList(false, true, false), tcfCaV1Section.getCustomPurposesExpressConsent());
    Assertions.assertEquals(Arrays.asList(true, false, true), tcfCaV1Section.getCustomPurposesImpliedConsent());
    Assertions.assertEquals(utcDateTime, tcfCaV1Section.getCreated());
    Assertions.assertEquals(utcDateTime, tcfCaV1Section.getLastUpdated());

    Assertions.assertEquals("EN", tcfCaV1Section.getConsentLanguage());
    Assertions.assertEquals(5, tcfCaV1Section.getId());
    Assertions.assertEquals(3, tcfCaV1Section.getPubPurposesSegmentType());
  }

  @Test
  public void testEncode1() throws EncodingException, InvalidFieldException {
    GppModel gppModel = new GppModel();

    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.VENDOR_CONSENTS, Arrays.asList(28));
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.CREATED, utcDateTime);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.LAST_UPDATED, utcDateTime);

    Assertions.assertEquals("DBABMA~CPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAOAAAABAAAAA.QAAA.IAAA", gppModel.encode());
  }

  @Test
  public void testEncode2() throws EncodingException, InvalidFieldException {
    GppModel gppModel = new GppModel();

    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.VENDOR_CONSENTS, Arrays.asList(29));
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.CREATED, utcDateTime);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.LAST_UPDATED, utcDateTime);

    Assertions.assertEquals("DBABMA~CPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAOwAQAOgAAAA.QAAA.IAAA", gppModel.encode());
  }

  @Test
  public void testEncode3() throws EncodingException, InvalidFieldException {
    GppModel gppModel = new GppModel();

    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.VENDOR_CONSENTS, Arrays.asList(1, 173, 722));
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.CREATED, utcDateTime);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.LAST_UPDATED, utcDateTime);

    Assertions.assertEquals("DBABMA~CPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAFpQAwAAgCtAWkAAAAAAA.QAAA.IAAA",
        gppModel.encode());
  }

  @Test
  public void testDecode1() throws DecodingException {
    GppModel gppModel = new GppModel("DBABMA~CPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAOAAAABAAAAA.QAAA.IAAA");
    Assertions.assertEquals(Arrays.asList(28), gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.VENDOR_CONSENTS));
  }

  @Test
  public void testDecode2() throws DecodingException {
    GppModel gppModel = new GppModel("DBABMA~CPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAOwAQAOgAAAA.QAAA.IAAA");
    Assertions.assertEquals(Arrays.asList(29), gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.VENDOR_CONSENTS));
  }

  @Test
  public void testDecode3() throws DecodingException {
    GppModel gppModel = new GppModel("DBABMA~CPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAFpQAwAAgCtAWkAAAAAAA.QAAA.IAAA");
    Assertions.assertEquals(Arrays.asList(1, 173, 722),
        gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.VENDOR_CONSENTS));
  }

  @Test
  public void testConsistency() throws InvalidFieldException, EncodingException, DecodingException {
    GppModel fromObjectModel = new GppModel();

    fromObjectModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.PURPOSE_CONSENTS,
        new ArrayList<>(List.of(true, true, true, true, true, true, true, true, true, true)));
    fromObjectModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.VENDOR_CONSENTS,
        new ArrayList<>(List.of(32, 128, 81, 210, 755, 21, 173, 238)));

    Assertions.assertEquals(fromObjectModel.getSection(TcfEuV2.NAME).encode(),
        fromObjectModel.getSection(TcfEuV2.NAME).encode());
    Assertions.assertEquals(fromObjectModel.encode(), fromObjectModel.encode());

    GppModel decodedModel = new GppModel(fromObjectModel.encode());

    Assertions.assertEquals(
        new ArrayList<>(List.of(true, true, true, true, true, true, true, true, true, true, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false)),
        decodedModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.PURPOSE_CONSENTS));
    Assertions.assertEquals(new ArrayList<>(List.of(21, 32, 81, 128, 173, 210, 238, 755)),
        decodedModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.VENDOR_CONSENTS));

  }
}
