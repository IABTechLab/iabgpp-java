package com.iab.gpp.encoder;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import com.iab.gpp.encoder.section.TcfCaV1;
import com.iab.gpp.encoder.section.TcfEuV2;
import com.iab.gpp.encoder.section.UsCaV1;
import com.iab.gpp.encoder.section.UspV1;

public class MyBenchmark {
  private static final String GPP = "DBADOfdg~CQCT9cAQCT9cAPoABABGA-EMAP_gAP_gAAAAKXNV_G__bXlv-X736ftkeY1f9_h77sQxBhfJs-4FzLvW_JwX32EzNE36tqYKmRIAu3bBIQNtHJjUTVChaogVrzDsak2coTtKJ-BkiHMRe2dYCF5vmwtj-QKZ5vr_93d52R_t_dr-3dzyz5Vnv3a9_-b1WJidK5-tH_v_bROb-_I-9_5-_4v8_N_rE2_eT1t_tevt739-8tv_____9____7______3_-wUuar-N_-2vLf8v3v0_bI8xq_7_D33YhiDC-TZ9wLmXet-TgvvsJmaJv1bUwVMiQBdu2CQgbaOTGomqFC1RArXmHY1Js5QnaUT8DJEOYi9s6wELzfNhbH8gUzzfX_7u7zsj_b-7X9u7nlnyrPfu17_83qsTE6Vz9aP_f-2ic39-R97_z9_xf5-b_WJt-8nrb_a9fb3v795bf_____7____3______v_9gAAAA.QKXNV_G__bXlv-X736ftkeY1f9_h77sQxBhfJs-4FzLvW_JwX32EzNE36tqYKmRIAu3bBIQNtHJjUTVChaogVrzDsak2coTtKJ-BkiHMRe2dYCF5vmwtj-QKZ5vr_93d52R_t_dr-3dzyz5Vnv3a9_-b1WJidK5-tH_v_bROb-_I-9_5-_4v8_N_rE2_eT1t_tevt739-8tv_____9____7______3_-wAAA.IKXNV_G__bXlv-X736ftkeY1f9_h77sQxBhfJs-4FzLvW_JwX32EzNE36tqYKmRIAu3bBIQNtHJjUTVChaogVrzDsak2coTtKJ-BkiHMRe2dYCF5vmwtj-QKZ5vr_93d52R_t_dr-3dzyz5Vnv3a9_-b1WJidK5-tH_v_bROb-_I-9_5-_4v8_N_rE2_eT1t_tevt739-8tv_____9____7______3_-wAAA~BQCT9cAQCT9cAPoABABGBaCYAf8AAf8AAApZA9AAUABwAFQALQAaABLACgAF0AMwAbQA7gCCAEIAIoAT4ArQBbgDKAGmAOcAdwBAICSgJMAT8AzQBnQDPgGvAP4Ak8BKgCcgE_gKPAVEAqUBbwC4QF0AL3AX-AwcBmADTQG1ANxAcaA8QB5oD5AICAQkAjcBH8CUsEwATBAmuBOYCfgFJgKWApZA9AAUABwAFQALQAaABLACgAF0AMwAbQA7gCCAEIAIoAT4ArQBbgDKAGmAOcAdwBAICSgJMAT8AzQBnQDPgGvAP4Ak8BKgCcgE_gKPAVEAqUBbwC4QF0AL3AX-AwcBmADTQG1ANxAcaA8QB5oD5AICAQkAjcBH8CUsEwATBAmuBOYCfgFJgKWAAA.YAAAAAAAAAA~1---~BAAAAABA.QA~BAAAABA~BAAAAEA.QA~BAAAAAQA~BAAAAAEA.QA";

  @Benchmark
  public void decodeGpp(Blackhole blackhole) {
    GppModel gpp = new GppModel(GPP);
    TcfEuV2 tcfEuV2 = gpp.getTcfEuV2Section();
    blackhole.consume(tcfEuV2.getPublisherConsents());
    blackhole.consume(tcfEuV2.getPurposeConsents());
    blackhole.consume(tcfEuV2.getVendorConsents());
    blackhole.consume(tcfEuV2.getPurposeLegitimateInterests());
    blackhole.consume(tcfEuV2.getVendorLegitimateInterests());
    blackhole.consume(tcfEuV2.getSpecialFeatureOptins());
    blackhole.consume(tcfEuV2.getCmpId());
    blackhole.consume(tcfEuV2.getPublisherRestrictions());
    TcfCaV1 tcfCaV1 = gpp.getTcfCaV1Section();
    blackhole.consume(tcfCaV1.getPubPurposesExpressConsent());
    blackhole.consume(tcfCaV1.getPubPurposesImpliedConsent());
    blackhole.consume(tcfCaV1.getVendorImpliedConsent());
    blackhole.consume(tcfCaV1.getCustomPurposesExpressConsent());
    blackhole.consume(tcfCaV1.getCustomPurposesImpliedConsent());
    blackhole.consume(tcfCaV1.getSpecialFeatureExpressConsent());
    blackhole.consume(tcfCaV1.getCmpId());
    blackhole.consume(tcfCaV1.getPubRestrictions());
    UspV1 uspV1 = gpp.getUspV1Section();
    blackhole.consume(uspV1.getLspaCovered());
    UsCaV1 usCaV1 = gpp.getUsCaV1Section();
    blackhole.consume(usCaV1.getGpc());
    blackhole.consume(usCaV1.getSaleOptOut());
  }

}
