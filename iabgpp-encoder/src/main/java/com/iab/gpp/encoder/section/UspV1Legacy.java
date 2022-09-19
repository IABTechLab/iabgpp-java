package com.iab.gpp.encoder.section;

import java.util.HashMap;
import java.util.Map;
import com.iab.gpp.encoder.field.UspV1LegacyField;

public class UspV1Legacy implements EncodableSection {
  public static int ID = 6;
  public static int VERSION = 1;
  public static String NAME = "uspv1";

  protected Map<String, Object> fields;

  public UspV1Legacy() {
    initFields();
  }

  public UspV1Legacy(String encodedString) {
    initFields();

    if (encodedString != null && encodedString.length() > 0) {
      this.decode(encodedString);
    }
  }

  private void initFields() {
    fields = new HashMap<>();
    fields.put(UspV1LegacyField.VERSION, UspV1Legacy.VERSION);
    fields.put(UspV1LegacyField.NOTICE, "-");
    fields.put(UspV1LegacyField.OPT_OUT_SALE, "-");
    fields.put(UspV1LegacyField.LSPA_COVERED, "-");
  }

  @Override
  public boolean hasField(String fieldName) {
    return this.fields.containsKey(fieldName);
  }

  @Override
  public Object getFieldValue(String fieldName) {
    if (this.fields.containsKey(fieldName)) {
      return this.fields.get(fieldName);
    } else {
      return null;
    }
  }

  @Override
  public void setFieldValue(String fieldName, Object value) {
    if (this.fields.containsKey(fieldName)) {
      this.fields.put(fieldName, value);
    } else {
      throw new Error(fieldName + " not found");
    }
  }

  @Override
  public String encode() {
    String str = "";
    str += this.getFieldValue(UspV1LegacyField.VERSION);
    str += this.getFieldValue(UspV1LegacyField.NOTICE);
    str += this.getFieldValue(UspV1LegacyField.OPT_OUT_SALE);
    str += this.getFieldValue(UspV1LegacyField.LSPA_COVERED);
    return str;
  }

  @Override
  public void decode(String encodedString) {
    // TODO: validate
    this.setFieldValue(UspV1LegacyField.VERSION, Integer.parseInt(String.valueOf(encodedString.charAt(0))));
    this.setFieldValue(UspV1LegacyField.NOTICE, String.valueOf(encodedString.charAt(1)));
    this.setFieldValue(UspV1LegacyField.OPT_OUT_SALE, String.valueOf(encodedString.charAt(2)));
    this.setFieldValue(UspV1LegacyField.LSPA_COVERED, String.valueOf(encodedString.charAt(3)));
  }

  @Override
  public int getId() {
    return UspV1Legacy.ID;
  }

  @Override
  public String getName() {
    return UspV1Legacy.NAME;
  }
  
  public Integer getVersion() {
    return (Integer)this.fields.get(UspV1LegacyField.VERSION);
  }
  
  public String getNotice() {
    return (String) fields.get(UspV1LegacyField.NOTICE);
  }
  
  public String getOptOutSale() {
    return (String) fields.get(UspV1LegacyField.OPT_OUT_SALE);
  }
  
  public String getLspaCovered() {
    return (String) fields.get(UspV1LegacyField.LSPA_COVERED);
  }
}
