package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.encoder.Dirtyable;

public abstract class AbstractEncodable implements Dirtyable {
  
  private CharSequence encodedString;

  protected boolean decoded = true;
  
  
  public final String encode() {
    return encodeCharSequence().toString();
  }
  
  protected void ensureDecode() {
    if (!this.decoded) {
      if(encodedString != null && encodedString.length() > 0) {
        this.doDecode(this.encodedString);
      }
      this.setDirty(false);
      this.decoded = true;
    }
  }
  
  public final CharSequence encodeCharSequence() {
    if (this.encodedString == null || this.encodedString.length() == 0 || this.isDirty()) {
      this.encodedString = doEncode();
      this.setDirty(false);
      this.decoded = true;
    }

    return this.encodedString;
  }

  protected abstract CharSequence doEncode();
  
  protected abstract void doDecode(CharSequence encodedString);

  public final void decode(CharSequence encodedString) {
    this.encodedString = encodedString;
    this.decoded = false;
  }

}
