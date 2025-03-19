package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.field.Fields;

public abstract class AbstractLazilyEncodableSegment<T extends Fields<?>> implements EncodableSegment {

  protected T fields;

  private CharSequence encodedString = null;

  private boolean dirty = false;
  private boolean decoded = true;

  public AbstractLazilyEncodableSegment() {
    this.fields = initializeFields();
  }

  protected abstract T initializeFields();

  protected abstract String encodeSegment(T fields);

  protected abstract void decodeSegment(CharSequence encodedString, T Fields);

  public boolean hasField(String fieldName) {
    return this.fields.containsKey(fieldName);
  }

  public Object getFieldValue(String fieldName) {
    if (!this.decoded) {
      this.decodeSegment(this.encodedString, this.fields);
      this.dirty = false;
      this.decoded = true;
    }

    DataType<?> field = this.fields.get(fieldName);
    if (field != null) {
      return field.getValue();
    } else {
      throw new InvalidFieldException("Invalid field: '" + fieldName + "'");
    }
  }

  public void setFieldValue(String fieldName, Object value) {
    if (!this.decoded) {
      this.decodeSegment(this.encodedString, this.fields);
      this.dirty = false;
      this.decoded = true;
    }

    DataType<?> field = this.fields.get(fieldName);
    if (field != null) {
      field.setValue(value);
      this.dirty = true;
    } else {
      throw new InvalidFieldException(fieldName + " not found");
    }
  }

  public String encode() {
    if (this.encodedString == null || this.encodedString.length() == 0 || this.dirty) {
      this.validate();
      this.encodedString = encodeSegment(this.fields);
      this.dirty = false;
      this.decoded = true;
    }

    return this.encodedString.toString();
  }

  public void decode(CharSequence encodedString) {
    this.encodedString = encodedString;
    this.dirty = false;
    this.decoded = false;
  }


}
