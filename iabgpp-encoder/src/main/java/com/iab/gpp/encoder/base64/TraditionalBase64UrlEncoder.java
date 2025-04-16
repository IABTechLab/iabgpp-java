package com.iab.gpp.encoder.base64;

import com.iab.gpp.encoder.bitstring.BitStringBuilder;

public final class TraditionalBase64UrlEncoder extends AbstractBase64UrlEncoder {

  private static final TraditionalBase64UrlEncoder instance = new TraditionalBase64UrlEncoder();

  private TraditionalBase64UrlEncoder() {

  }

  public static TraditionalBase64UrlEncoder getInstance() {
    return instance;
  }

  @Override
  protected void pad(BitStringBuilder bitString) {
    int remainder = bitString.length() % 24;
    if (remainder > 0) {
      int padding = 24 - remainder;
      for (int i = 0; i < padding; i++) {
        bitString.append(false);
      }
    }
  }

}
