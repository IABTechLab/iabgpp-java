package com.iab.gpp.encoder.section;

import java.util.HashMap;
import java.util.List;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedBitfield;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.encoder.Base64UrlEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.UspCoV1Field;
import com.iab.gpp.encoder.field.UspV1Field;

public class UspCoV1 extends AbstractEncodableBitStringSection {
  public static int ID = 10;
  public static int VERSION = 1;
  public static String NAME = "uspco";

  public UspCoV1() {
    initFields();
  }

  public UspCoV1(String encodedString) throws DecodingException {
    initFields();

    if (encodedString != null && encodedString.length() > 0) {
      this.decode(encodedString);
    }
  }

  private void initFields() {
    fields = new HashMap<>();

    fields.put(UspCoV1Field.VERSION, new EncodableFixedInteger(6, UspCoV1.VERSION));
    fields.put(UspCoV1Field.SHARING_NOTICE, new EncodableFixedInteger(2));
    fields.put(UspCoV1Field.SALE_OPT_OUT_NOTICE, new EncodableFixedInteger(2));
    fields.put(UspCoV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, new EncodableFixedInteger(2));
    fields.put(UspCoV1Field.SALE_OPT_OUT, new EncodableFixedInteger(2));
    fields.put(UspCoV1Field.SENSITIVE_DATA_PROCESSING, new EncodableFixedBitfield(7));
    fields.put(UspCoV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, new EncodableFixedInteger(2));
    fields.put(UspCoV1Field.MSPA_COVERED_TRANSACTION, new EncodableFixedInteger(2));
    fields.put(UspCoV1Field.MSPA_OPT_OUT_OPTION_MODE, new EncodableFixedInteger(2));
    fields.put(UspCoV1Field.MSPA_SERVICE_PROVIDER_MODE, new EncodableFixedInteger(2));

    fields.put(UspCoV1Field.GPC, new EncodableBoolean());



    //@formatter:off
    fieldOrder = new String[] {
        UspCoV1Field.VERSION,
        UspCoV1Field.SHARING_NOTICE,
        UspCoV1Field.SALE_OPT_OUT_NOTICE,
        UspCoV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
        UspCoV1Field.SALE_OPT_OUT,
        UspCoV1Field.TARGETED_ADVERTISING_OPT_OUT,
        UspCoV1Field.SENSITIVE_DATA_PROCESSING,
        UspCoV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
        UspCoV1Field.MSPA_COVERED_TRANSACTION,
        UspCoV1Field.MSPA_OPT_OUT_OPTION_MODE,
        UspCoV1Field.MSPA_SERVICE_PROVIDER_MODE,
        UspCoV1Field.GPC
    };
    //@formatter:on
  }

  @Override
  public String encode() throws EncodingException {
    String bitString = this.encodeToBitString();
    String encodedString = Base64UrlEncoder.encode(bitString);
    return encodedString;
  }

  @Override
  public void decode(String encodedString) throws DecodingException {
    String bitString = Base64UrlEncoder.decode(encodedString);
    this.decodeFromBitString(bitString);
  }

  @Override
  public int getId() {
    return UspCoV1.ID;
  }

  @Override
  public String getName() {
    return UspCoV1.NAME;
  }

  public Integer getVersion() {
    return (Integer) this.fields.get(UspV1Field.VERSION).getValue();
  }

  public Integer getSharingNotice() {
    return (Integer) this.fields.get(UspCoV1Field.SHARING_NOTICE).getValue();
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.fields.get(UspCoV1Field.SALE_OPT_OUT_NOTICE).getValue();
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.fields.get(UspCoV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE).getValue();
  }

  public Integer getSaleOptOut() {
    return (Integer) this.fields.get(UspCoV1Field.SALE_OPT_OUT).getValue();
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.fields.get(UspCoV1Field.TARGETED_ADVERTISING_OPT_OUT).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Boolean> getSensitiveDataProcessing() {
    return (List<Boolean>) this.fields.get(UspCoV1Field.SENSITIVE_DATA_PROCESSING).getValue();
  }

  public Integer getKnownChildSensitiveDataConsents() {
    return (Integer) this.fields.get(UspCoV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS).getValue();
  }

  public Integer getMspaCoveredtransaction() {
    return (Integer) this.fields.get(UspCoV1Field.MSPA_COVERED_TRANSACTION).getValue();
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.fields.get(UspCoV1Field.MSPA_OPT_OUT_OPTION_MODE).getValue();
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.fields.get(UspCoV1Field.MSPA_SERVICE_PROVIDER_MODE).getValue();
  }

  public Boolean getGpc() {
    return (Boolean) this.fields.get(UspCoV1Field.GPC).getValue();
  }
}
