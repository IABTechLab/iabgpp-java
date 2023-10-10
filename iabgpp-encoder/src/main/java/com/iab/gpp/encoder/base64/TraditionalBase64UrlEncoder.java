package com.iab.gpp.encoder.base64;

public class TraditionalBase64UrlEncoder extends AbstractBase64UrlEncoder {

  private static TraditionalBase64UrlEncoder instance = new TraditionalBase64UrlEncoder();
  
  private TraditionalBase64UrlEncoder() {
    
  }
  
  public static TraditionalBase64UrlEncoder getInstance() {
    return instance;
  }
  
  @Override
  protected String pad(String bitString) {
    while (bitString.length() % 24 > 0) {
      bitString += "0";
    }
    return bitString;
  }

}
