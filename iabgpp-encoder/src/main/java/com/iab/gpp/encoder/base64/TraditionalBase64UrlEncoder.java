package com.iab.gpp.encoder.base64;

import java.util.Arrays;

public class TraditionalBase64UrlEncoder extends AbstractBase64UrlEncoder {

  private static final TraditionalBase64UrlEncoder instance = new TraditionalBase64UrlEncoder();
  
  private TraditionalBase64UrlEncoder() {
    
  }
  
  public static TraditionalBase64UrlEncoder getInstance() {
    return instance;
  }
  
  @Override
  protected String pad(String bitString) {
    if(bitString.length() % 24 > 0) {
      char[] chars = new char[24 - (bitString.length() % 24)];
      Arrays.fill(chars, '0');
      return bitString + new String(chars);
    } else {
      return bitString;
    }
  }

}
