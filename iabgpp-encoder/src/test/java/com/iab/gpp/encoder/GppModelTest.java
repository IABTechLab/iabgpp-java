package com.iab.gpp.encoder;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.field.TcfCaV1Field;
import com.iab.gpp.encoder.field.TcfEuV2Field;
import com.iab.gpp.encoder.field.UsCaField;
import com.iab.gpp.encoder.field.UsCoField;
import com.iab.gpp.encoder.field.UsCtField;
import com.iab.gpp.encoder.field.UsFlField;
import com.iab.gpp.encoder.field.UsMtField;
import com.iab.gpp.encoder.field.UsNatField;
import com.iab.gpp.encoder.field.UsOrField;
import com.iab.gpp.encoder.field.UsTxField;
import com.iab.gpp.encoder.field.UsUtField;
import com.iab.gpp.encoder.field.UsVaField;
import com.iab.gpp.encoder.field.UspV1Field;
import com.iab.gpp.encoder.section.TcfCaV1;
import com.iab.gpp.encoder.section.TcfEuV2;
import com.iab.gpp.encoder.section.UsCa;
import com.iab.gpp.encoder.section.UsCo;
import com.iab.gpp.encoder.section.UsCt;
import com.iab.gpp.encoder.section.UsDe;
import com.iab.gpp.encoder.section.UsFl;
import com.iab.gpp.encoder.section.UsIa;
import com.iab.gpp.encoder.section.UsMt;
import com.iab.gpp.encoder.section.UsNat;
import com.iab.gpp.encoder.section.UsNe;
import com.iab.gpp.encoder.section.UsNh;
import com.iab.gpp.encoder.section.UsNj;
import com.iab.gpp.encoder.section.UsOr;
import com.iab.gpp.encoder.section.UsTn;
import com.iab.gpp.encoder.section.UsTx;
import com.iab.gpp.encoder.section.UsUt;
import com.iab.gpp.encoder.section.UsVa;
import com.iab.gpp.encoder.section.UspV1;

public class GppModelTest {

  private Instant utcDateTime = ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();

  @Test
  public void testEncodeDefault() {
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
  public void testDecodingException() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new GppModel("invalid gpp string").getHeader();
    });
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new GppModel("z").getUsCtSection();
    });
  }

  @Test
  public void testEncodeDefaultAll() {
    GppModel gppModel = new GppModel();

    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UsNat.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UsCa.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UsVa.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UsCo.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UsUt.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UsCt.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UsFl.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UsMt.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UsOr.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UsTx.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UsDe.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UsIa.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UsNe.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UsNh.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UsNj.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(UsTn.NAME));

    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.VERSION, TcfEuV2.VERSION);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfCaV1Field.CREATED, utcDateTime);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfCaV1Field.LAST_UPDATED, utcDateTime);
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.VERSION, TcfCaV1.VERSION);
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.CREATED, utcDateTime);
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.LAST_UPDATED, utcDateTime);
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.VERSION, UspV1.VERSION);
    gppModel.setFieldValue(UsNat.NAME, UsNatField.VERSION, UsNat.VERSION);
    gppModel.setFieldValue(UsCa.NAME, UsCaField.VERSION, UsCa.VERSION);
    gppModel.setFieldValue(UsVa.NAME, UsVaField.VERSION, UsVa.VERSION);
    gppModel.setFieldValue(UsCo.NAME, UsCoField.VERSION, UsCo.VERSION);
    gppModel.setFieldValue(UsUt.NAME, UsUtField.VERSION, UsUt.VERSION);
    gppModel.setFieldValue(UsCt.NAME, UsCtField.VERSION, UsCt.VERSION);
    gppModel.setFieldValue(UsFl.NAME, UsFlField.VERSION, UsFl.VERSION);
    gppModel.setFieldValue(UsMt.NAME, UsMtField.VERSION, UsMt.VERSION);
    gppModel.setFieldValue(UsOr.NAME, UsOrField.VERSION, UsOr.VERSION);
    gppModel.setFieldValue(UsTx.NAME, UsTxField.VERSION, UsTx.VERSION);
    gppModel.setFieldValue(UsDe.NAME, UsTxField.VERSION, UsTx.VERSION);
    gppModel.setFieldValue(UsIa.NAME, UsTxField.VERSION, UsTx.VERSION);
    gppModel.setFieldValue(UsNe.NAME, UsTxField.VERSION, UsTx.VERSION);
    gppModel.setFieldValue(UsNh.NAME, UsTxField.VERSION, UsTx.VERSION);
    gppModel.setFieldValue(UsNj.NAME, UsTxField.VERSION, UsTx.VERSION);
    gppModel.setFieldValue(UsTn.NAME, UsTxField.VERSION, UsTx.VERSION);



    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfCaV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsNat.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsCa.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsVa.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsCo.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsUt.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsCt.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsFl.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsMt.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsOr.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsTx.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsDe.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsIa.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsNe.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsNh.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsNj.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsTn.NAME));

    String gppString = gppModel.encode();
    Assertions.assertEquals(
        "DBACOdM~CPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA~BPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAAAAAA.YAAAAAAAAAA~1---~BAAAAAAAAABA.QA~BAAAAABA.QA~BAAAABA~BAAAAEA.QA~BAAAAAQA~BAAAAAEA.QA~BAAAAABA~BAAAAABA.QA~BAAAAAABAA.QA~BAAAAAQA.QA~BAAAAAABAA.QA~BAAAAAQA.QA~BAAAAAQA.QA~BAAAAABA.QA~BAAAAAAAQA.QA~BAAAAAQA.QA",
        gppString);

  }

  @Test
  public void testEncodeUspv1() {
    GppModel gppModel = new GppModel();
    Assertions.assertEquals(false, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.ID));

    Assertions.assertEquals(false, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.NAME));

    gppModel.setFieldValue(UspV1.NAME, UspV1Field.NOTICE, 'Y');
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE, 'N');
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED, 'N');

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
  public void testEncodeTcfEuV2() {
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
  public void testEncodeUspV1AndTcfEuV2() {
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

    gppModel.setFieldValue(UspV1.NAME, UspV1Field.NOTICE, 'Y');
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE, 'N');
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED, 'N');

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
  public void testEncodeUspV1AndTcfEuV2AndTcfCaV1() {
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

    gppModel.setFieldValue(UspV1.NAME, UspV1Field.NOTICE, 'Y');
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE, 'N');
    gppModel.setFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED, 'N');

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
        Arrays.asList(6, 7, 8, 9, 10, 11));
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.PURPOSES_EXPRESS_CONSENT,
        Arrays.asList(0, 1, 2, 3, 4, 5, 12, 13, 14, 15, 16, 17));
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.PURPOSES_IMPLIED_CONSENT,
        Arrays.asList(6, 7, 8, 9, 10, 11, 18, 19, 20, 21, 22, 23));
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.VENDOR_EXPRESS_CONSENT, Arrays.asList(12, 24, 48));
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.VENDOR_IMPLIED_CONSENT, Arrays.asList(18, 30));
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.PUB_PURPOSES_EXPRESS_CONSENT,
        Arrays.asList(0, 1, 2, 6, 7, 8, 12, 13, 14, 18, 19, 20));
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.PUB_PURPOSES_IMPLIED_CONSENT,
        Arrays.asList(3, 4, 5, 9, 10, 11, 15, 16, 17, 21, 22, 23));
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.NUM_CUSTOM_PURPOSES, 3);
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.CUSTOM_PURPOSES_EXPRESS_CONSENT,
        List.of(1));
    gppModel.setFieldValue(TcfCaV1.NAME, TcfCaV1Field.CUSTOM_PURPOSES_IMPLIED_CONSENT,
        List.of(0,2));

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
        "DBACOeA~CPSG_8APSG_8ANwAAAENAwCAAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA~BPSG_8APSG_8AAyACAENGdCgf_gfgAfgfgBgABABAAABAB4AACACAAA.fHHHA4444ao~1YNN",
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
  public void testDecodeDefaults() {
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
  public void testDecodeDefaultsAll() {
    String gppString =
        "DBACOdM~CPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA~BPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAAAAAA.YAAAAAAAAAA~1---~BAAAAAAAAABA.QA~BAAAAABA.QA~BAAAABA~BAAAAEA.QA~BAAAAAQA~BAAAAAEA.QA~BAAAAABA~BAAAAABA.QA~BAAAAAABAA.QA~BAAAAAQA.QA~BAAAAAABAA.QA~BAAAAAQA.QA~BAAAAAQA.QA~BAAAAABA.QA~BAAAAAAAQA.QA~BAAAAAQA.QA";
    GppModel gppModel = new GppModel(gppString);

    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfCaV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsNat.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsCa.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsVa.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsCo.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsUt.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsCt.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsFl.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsMt.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsOr.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsTx.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsDe.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsIa.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsNe.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsNh.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsNj.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(UsTn.NAME));
  }

  @Test
  public void testDecodeUspv1() {
    String gppString = "DBABTA~1YNN";
    GppModel gppModel = new GppModel(gppString);

    Assertions.assertEquals(Arrays.asList(6), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.ID));

    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.NAME));

    Assertions.assertEquals('Y', gppModel.getFieldValue(UspV1.ID, UspV1Field.NOTICE));
    Assertions.assertEquals('N', gppModel.getFieldValue(UspV1.ID, UspV1Field.OPT_OUT_SALE));
    Assertions.assertEquals('N', gppModel.getFieldValue(UspV1.ID, UspV1Field.LSPA_COVERED));

    Assertions.assertEquals('Y', gppModel.getFieldValue(UspV1.NAME, UspV1Field.NOTICE));
    Assertions.assertEquals('N', gppModel.getFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE));
    Assertions.assertEquals('N', gppModel.getFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED));
  }

  @Test
  public void testDecodeTcfEuV2() {
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
  public void testDecodeUspv1AndTcfEuV2() {
    String gppString = "DBACNYA~CPSG_8APSG_8ANwAAAENAwCAAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA~1YNN";
    GppModel gppModel = new GppModel(gppString);

    Assertions.assertEquals(Arrays.asList(2, 6), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.ID));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.ID));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.ID));

    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(false, gppModel.hasSection(TcfCaV1.NAME));


    Assertions.assertEquals('Y', gppModel.getFieldValue(UspV1.ID, UspV1Field.NOTICE));
    Assertions.assertEquals('N', gppModel.getFieldValue(UspV1.ID, UspV1Field.OPT_OUT_SALE));
    Assertions.assertEquals('N', gppModel.getFieldValue(UspV1.ID, UspV1Field.LSPA_COVERED));

    Assertions.assertEquals('Y', gppModel.getFieldValue(UspV1.NAME, UspV1Field.NOTICE));
    Assertions.assertEquals('N', gppModel.getFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE));
    Assertions.assertEquals('N', gppModel.getFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED));

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
    Character notice = uspV1Section.getNotice();
    Assertions.assertEquals(1, uspV1Version);
    Assertions.assertEquals('Y', notice);
  }

  @Test
  public void testDecodeUspv1AndTcfEuV2AndTcfCaV1() {
    String gppString =
        "DBACOeA~CPSG_8APSG_8ANwAAAENAwCAAAAAAAAAAAAAAAAAAAAA.QAAA.IAAA~BPSG_8APSG_8AAyACAENGdCgf_gfgAfgfgBgABABAAABAB4AACACAAA.fHHHA4444ao~1YNN";
    GppModel gppModel = new GppModel(gppString);

    Assertions.assertEquals(Arrays.asList(2, 5, 6), gppModel.getSectionIds());
    Assertions.assertEquals(true, gppModel.hasSection(UspV1.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfEuV2.NAME));
    Assertions.assertEquals(true, gppModel.hasSection(TcfCaV1.NAME));

    Assertions.assertEquals('Y', gppModel.getFieldValue(UspV1.NAME, UspV1Field.NOTICE));
    Assertions.assertEquals('N', gppModel.getFieldValue(UspV1.NAME, UspV1Field.OPT_OUT_SALE));
    Assertions.assertEquals('N', gppModel.getFieldValue(UspV1.NAME, UspV1Field.LSPA_COVERED));

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
    Character notice = uspV1Section.getNotice();
    Assertions.assertEquals(1, uspV1Version);
    Assertions.assertEquals('Y', notice);

    TcfCaV1 tcfCaV1Section = (TcfCaV1) gppModel.getSection(TcfCaV1.NAME);
    Assertions.assertEquals(50, tcfCaV1Section.getCmpId());
    Assertions.assertEquals(2, tcfCaV1Section.getCmpVersion());
    Assertions.assertEquals(413, tcfCaV1Section.getVendorListVersion());
    Assertions.assertEquals(true, tcfCaV1Section.getUseNonStandardStacks());
    Assertions.assertTrue(tcfCaV1Section.getSpecialFeatureExpressConsent().intStream().anyMatch(x -> x== 7));
    Assertions.assertEquals(Set.of(7, 8, 9, 10, 11, 12),
        tcfCaV1Section.getSpecialFeatureExpressConsent());
    Assertions
        .assertEquals(
            Set.of(1, 2, 3, 4, 5, 6, 13, 14, 15, 16, 17, 18),
            tcfCaV1Section.getPurposesExpressConsent());
    Assertions
        .assertEquals(
            Set.of(7, 8, 9, 10, 11, 12, 19, 20, 21, 22, 23, 24),
            tcfCaV1Section.getPurposesImpliedConsent());
    Assertions.assertEquals(Set.of(12, 24, 48), tcfCaV1Section.getVendorExpressConsent());
    Assertions.assertEquals(Set.of(18, 30), tcfCaV1Section.getVendorImpliedConsent());
    Assertions
        .assertEquals(
            Set.of(1, 2, 3, 7, 8, 9, 13, 14, 15, 19, 20, 21),
            tcfCaV1Section.getPubPurposesExpressConsent());
    Assertions
        .assertEquals(
            Set.of(4, 5, 6, 10, 11, 12, 16, 17, 18, 22, 23, 24),
            tcfCaV1Section.getPubPurposesImpliedConsent());
    Assertions.assertEquals(3, tcfCaV1Section.getNumCustomPurposes());
    Assertions.assertEquals(Set.of(2), tcfCaV1Section.getCustomPurposesExpressConsent());
    Assertions.assertEquals(Set.of(1, 3), tcfCaV1Section.getCustomPurposesImpliedConsent());
    Assertions.assertEquals(utcDateTime, tcfCaV1Section.getCreated());
    Assertions.assertEquals(utcDateTime, tcfCaV1Section.getLastUpdated());

    Assertions.assertEquals("EN", tcfCaV1Section.getConsentLanguage());
    Assertions.assertEquals(5, tcfCaV1Section.getId());
    Assertions.assertEquals(3, tcfCaV1Section.getPubPurposesSegmentType());
  }

  @Test
  public void testEncode1() {
    GppModel gppModel = new GppModel();

    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.VENDOR_CONSENTS, Arrays.asList(28));
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.CREATED, utcDateTime);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.LAST_UPDATED, utcDateTime);

    Assertions.assertEquals("DBABMA~CPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAOAAAABAAAAA.QAAA.IAAA", gppModel.encode());
  }

  @Test
  public void testEncode2() {
    GppModel gppModel = new GppModel();

    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.VENDOR_CONSENTS, Arrays.asList(29));
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.CREATED, utcDateTime);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.LAST_UPDATED, utcDateTime);

    Assertions.assertEquals("DBABMA~CPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAOwAQAOgAAAA.QAAA.IAAA", gppModel.encode());
  }

  @Test
  public void testEncode3() {
    GppModel gppModel = new GppModel();

    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.VENDOR_CONSENTS, Arrays.asList(1, 173, 722));
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.CREATED, utcDateTime);
    gppModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.LAST_UPDATED, utcDateTime);

    Assertions.assertEquals("DBABMA~CPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAFpQAwAAgCtAWkAAAAAAA.QAAA.IAAA",
        gppModel.encode());
  }

  @Test
  public void testDecode1() {
    GppModel gppModel = new GppModel("DBABMA~CPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAOAAAABAAAAA.QAAA.IAAA");
    Assertions.assertEquals(Set.of(28), gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.VENDOR_CONSENTS));
  }

  @Test
  public void testDecode2() {
    GppModel gppModel = new GppModel("DBABMA~CPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAAOwAQAOgAAAA.QAAA.IAAA");
    Assertions.assertEquals(Set.of(29), gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.VENDOR_CONSENTS));
  }

  @Test
  public void testDecode3() {
    GppModel gppModel = new GppModel("DBABMA~CPSG_8APSG_8AAAAAAENAACAAAAAAAAAAAAAFpQAwAAgCtAWkAAAAAAA.QAAA.IAAA");
    Assertions.assertEquals(Set.of(1, 173, 722),
        gppModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.VENDOR_CONSENTS));
  }
  
  @Test
  public void testDecode4() {
    GppModel gppModel = new GppModel("DBABTA~1YYN");
    gppModel.getFieldValue(UspV1.NAME, UspV1Field.VERSION);
    
  }

  @Test
  public void testDecode5() {
    GppModel gppModel = new GppModel("DBABLA~BVQqAAAAAgA.QA");
    gppModel.getFieldValue(UsNat.NAME, UspV1Field.VERSION);
  }
    
  @Test
  public void testDecode6() {
    GppModel gppModel = new GppModel("DBABLA~BAAAAAAAAQA.QA");
    gppModel.getFieldValue(UsNat.NAME, UspV1Field.VERSION);
    Assertions.assertEquals(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        gppModel.getFieldValue(UsNat.NAME, UsNatField.SENSITIVE_DATA_PROCESSING));
    Assertions.assertEquals(Arrays.asList(0, 0, 0),
        gppModel.getFieldValue(UsNat.NAME, UsNatField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS));
  }
  
  @Test
  public void testDecode7() {
    GppModel gppModel = new GppModel("DBABLA~BAAAAAAAAABA.QA");
    gppModel.getFieldValue(UsNat.NAME, UspV1Field.VERSION);
    Assertions.assertEquals(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        gppModel.getFieldValue(UsNat.NAME, UsNatField.SENSITIVE_DATA_PROCESSING));
    Assertions.assertEquals(Arrays.asList(0, 0, 0),
        gppModel.getFieldValue(UsNat.NAME, UsNatField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS));
  }
  
  @Test
  public void testDecode8() {
    GppModel gppModel = new GppModel("DBABLA~BAAAAAABEQA.QA");
    gppModel.getFieldValue(UsNat.NAME, UspV1Field.VERSION);
    Assertions.assertEquals(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0),
        gppModel.getFieldValue(UsNat.NAME, UsNatField.SENSITIVE_DATA_PROCESSING));
    Assertions.assertEquals(Arrays.asList(0, 1, 0),
        gppModel.getFieldValue(UsNat.NAME, UsNatField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS));
  }
  
  @Test
  public void testDecode9() {
    GppModel gppModel = new GppModel("DBABLA~BAAAAAAAAQRA.QA");
    gppModel.getFieldValue(UsNat.NAME, UspV1Field.VERSION);
    Assertions.assertEquals(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1),
        gppModel.getFieldValue(UsNat.NAME, UsNatField.SENSITIVE_DATA_PROCESSING));
    Assertions.assertEquals(Arrays.asList(0, 0, 1),
        gppModel.getFieldValue(UsNat.NAME, UsNatField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS));
  }
  
  @Test
  public void testConsistency() {
    GppModel fromObjectModel = new GppModel();

    fromObjectModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.PURPOSE_CONSENTS,
        Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    fromObjectModel.setFieldValue(TcfEuV2.NAME, TcfEuV2Field.VENDOR_CONSENTS,
        Arrays.asList(32, 128, 81, 210, 755, 21, 173, 238));

    Assertions.assertEquals(fromObjectModel.getSection(TcfEuV2.NAME).encode(),
        fromObjectModel.getSection(TcfEuV2.NAME).encode());
    Assertions.assertEquals(fromObjectModel.encode(), fromObjectModel.encode());

    GppModel decodedModel = new GppModel(fromObjectModel.encode());

    Assertions.assertEquals(
        Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
        decodedModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.PURPOSE_CONSENTS));
    Assertions.assertEquals(Set.of(21, 32, 81, 128, 173, 210, 238, 755),
        decodedModel.getFieldValue(TcfEuV2.NAME, TcfEuV2Field.VENDOR_CONSENTS));

  }

  @Test
  public void testNullConstructor() {
    GppModel gppModel = new GppModel(null);
    Assertions.assertEquals("DBAA", gppModel.encode());

    gppModel.setFieldValue("uspv1", UspV1Field.NOTICE, 'Y');
    Assertions.assertEquals("DBABTA~1Y--", gppModel.encode());
  }

  @Test
  public void testEmptyStringConstructor() {
    GppModel gppModel = new GppModel("");
    Assertions.assertEquals("DBAA", gppModel.encode());

    gppModel.setFieldValue("uspv1", UspV1Field.NOTICE, 'Y');
    Assertions.assertEquals("DBABTA~1Y--", gppModel.encode());
  }

  @Test
  public void testDecodingNull() {
    GppModel gppModel = new GppModel("DBABTA~1---");
    Assertions.assertEquals("DBABTA~1---", gppModel.encode());

    gppModel.decode(null);
    Assertions.assertEquals("DBAA", gppModel.encode());

    gppModel.setFieldValue("uspv1", UspV1Field.NOTICE, 'Y');
    Assertions.assertEquals("DBABTA~1Y--", gppModel.encode());
  }

  @Test
  public void testDecodingEmptyString() {
    GppModel gppModel = new GppModel("DBABTA~1---");
    Assertions.assertEquals("DBABTA~1---", gppModel.encode());

    gppModel.decode(null);
    Assertions.assertEquals("DBAA", gppModel.encode());

    gppModel.setFieldValue("uspv1", UspV1Field.NOTICE, 'Y');
    Assertions.assertEquals("DBABTA~1Y--", gppModel.encode());
  }
  
  
}
