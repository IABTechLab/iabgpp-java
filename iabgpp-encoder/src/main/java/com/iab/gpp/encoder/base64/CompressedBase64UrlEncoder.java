package com.iab.gpp.encoder.base64;

import com.iab.gpp.encoder.bitstring.BitStringBuilder;

public final class CompressedBase64UrlEncoder extends AbstractBase64UrlEncoder {

  private static final CompressedBase64UrlEncoder instance = new CompressedBase64UrlEncoder();

  private CompressedBase64UrlEncoder() {

  }

  public static CompressedBase64UrlEncoder getInstance() {
    return instance;
  }

  @Override
  protected void pad(BitStringBuilder bitString) {
    int remainder = bitString.length() % 8;
    if(remainder > 0) {
      int padding = 8 - remainder;
      for (int i = 0; i < padding; i++) {
        bitString.append(false);
      }
    }

    remainder = bitString.length() % 6;
    if(remainder > 0) {
      int padding = 6 - remainder;
      for (int i = 0; i < padding; i++) {
        bitString.append(false);
      }
    }
  }

}
