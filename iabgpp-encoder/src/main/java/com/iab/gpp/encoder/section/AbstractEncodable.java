package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.datatype.encoder.Dirtyable;

public abstract class AbstractEncodable implements Dirtyable {
  
  private CharSequence encoded;

  private boolean decoded = true;

  private final boolean isEmpty() {
    return encoded == null || encoded.length() == 0;
  }

  protected final void ensureDecode() {
    if (!this.decoded) {
      if (!isEmpty()) {
        this.doDecode(this.encoded);
      }
      this.setDirty(false);
      this.decoded = true;
    }
  }

  public final void decode(CharSequence encodedString) {
    this.encoded = encodedString;
    this.setDirty(false);
    this.decoded = false;
  }

  protected abstract void doDecode(CharSequence encodedString);

  protected abstract CharSequence doEncode();

  public final String encode() {
    return encodeCharSequence().toString();
  }

  public final CharSequence encodeCharSequence() {
    if (isEmpty() || this.isDirty()) {
      this.encoded = doEncode();
      this.setDirty(false);
      this.decoded = true;
    }
    return this.encoded;
  }

}
