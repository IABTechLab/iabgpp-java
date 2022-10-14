package com.iab.gpp.encoder.section;

import java.util.HashMap;
import java.util.List;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedBitfield;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.encoder.Base64UrlEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.UspNatV1Field;
import com.iab.gpp.encoder.field.UspV1Field;

public class UspNatV1 extends AbstractEncodableBitStringSection {
  public static int ID = 7;
  public static int VERSION = 1;
  public static String NAME = "uspnat";

  public UspNatV1() {
    initFields();
  }

  public UspNatV1(String encodedString) throws DecodingException {
    initFields();

    if (encodedString != null && encodedString.length() > 0) {
      this.decode(encodedString);
    }
  }

  private void initFields() {
    fields = new HashMap<>();

    fields.put(UspNatV1Field.VERSION, new EncodableFixedInteger(6, UspNatV1.VERSION));
    fields.put(UspNatV1Field.SHARING_NOTICE, new EncodableFixedInteger(2));
    fields.put(UspNatV1Field.SALE_OPT_OUT_NOTICE, new EncodableFixedInteger(2));
    fields.put(UspNatV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, new EncodableFixedInteger(2));
    fields.put(UspNatV1Field.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE, new EncodableFixedInteger(2));
    fields.put(UspNatV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE, new EncodableFixedInteger(2));
    fields.put(UspNatV1Field.SALE_OPT_OUT, new EncodableFixedInteger(2));
    fields.put(UspNatV1Field.SHARING_OPT_OUT, new EncodableFixedInteger(2));
    fields.put(UspNatV1Field.TARGETED_ADVERTISING_OPT_OUT, new EncodableFixedInteger(2));
    fields.put(UspNatV1Field.SENSITIVE_DATA_PROCESSING, new EncodableFixedBitfield(12));
    fields.put(UspNatV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, new EncodableFixedBitfield(2));
    fields.put(UspNatV1Field.MSPA_COVERED_TRANSACTION, new EncodableFixedInteger(2));
    fields.put(UspNatV1Field.MSPA_OPT_OUT_OPTION_MODE, new EncodableFixedInteger(2));
    fields.put(UspNatV1Field.MSPA_SERVICE_PROVIDER_MODE, new EncodableFixedInteger(2));

    fields.put(UspNatV1Field.GPC, new EncodableBoolean());



    //@formatter:off
    fieldOrder = new String[] {
        UspNatV1Field.VERSION,
        UspNatV1Field.SHARING_NOTICE,
        UspNatV1Field.SALE_OPT_OUT_NOTICE,
        UspNatV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
        UspNatV1Field.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE,
        UspNatV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE,
        UspNatV1Field.SALE_OPT_OUT,
        UspNatV1Field.SHARING_OPT_OUT,
        UspNatV1Field.TARGETED_ADVERTISING_OPT_OUT,
        UspNatV1Field.SENSITIVE_DATA_PROCESSING,
        UspNatV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
        UspNatV1Field.MSPA_COVERED_TRANSACTION,
        UspNatV1Field.MSPA_OPT_OUT_OPTION_MODE,
        UspNatV1Field.MSPA_SERVICE_PROVIDER_MODE,
        UspNatV1Field.GPC
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
    return UspNatV1.ID;
  }

  @Override
  public String getName() {
    return UspNatV1.NAME;
  }

  public Integer getVersion() {
    return (Integer) this.fields.get(UspV1Field.VERSION).getValue();
  }

  public Integer getSharingNotice() {
    return (Integer) this.fields.get(UspNatV1Field.SHARING_NOTICE).getValue();
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.fields.get(UspNatV1Field.SALE_OPT_OUT_NOTICE).getValue();
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.fields.get(UspNatV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE).getValue();
  }

  public Integer getSensitiveDataProcessingOptOutNotice() {
    return (Integer) this.fields.get(UspNatV1Field.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE).getValue();
  }

  public Integer getSensitiveDataLimitUseNotice() {
    return (Integer) this.fields.get(UspNatV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE).getValue();
  }

  public Integer getSaleOptOut() {
    return (Integer) this.fields.get(UspNatV1Field.SALE_OPT_OUT).getValue();
  }

  public Integer getSharingOptOut() {
    return (Integer) this.fields.get(UspNatV1Field.SHARING_OPT_OUT).getValue();
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.fields.get(UspNatV1Field.TARGETED_ADVERTISING_OPT_OUT).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Boolean> getSensitiveDataProcessing() {
    return (List<Boolean>) this.fields.get(UspNatV1Field.SENSITIVE_DATA_PROCESSING).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Boolean> getKnownChildSensitiveDataConsents() {
    return (List<Boolean>) this.fields.get(UspNatV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS).getValue();
  }

  public Integer getMspaCoveredtransaction() {
    return (Integer) this.fields.get(UspNatV1Field.MSPA_COVERED_TRANSACTION).getValue();
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.fields.get(UspNatV1Field.MSPA_OPT_OUT_OPTION_MODE).getValue();
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.fields.get(UspNatV1Field.MSPA_SERVICE_PROVIDER_MODE).getValue();
  }

  public Boolean getGpc() {
    return (Boolean) this.fields.get(UspNatV1Field.GPC).getValue();
  }
}
