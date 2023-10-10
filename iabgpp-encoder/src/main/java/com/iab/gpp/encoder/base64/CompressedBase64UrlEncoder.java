package com.iab.gpp.encoder.base64;

public class CompressedBase64UrlEncoder extends AbstractBase64UrlEncoder {

  private static CompressedBase64UrlEncoder instance = new CompressedBase64UrlEncoder();
  
  private CompressedBase64UrlEncoder() {
    
  }
  
  public static CompressedBase64UrlEncoder getInstance() {
    return instance;
  }
  
  @Override
  protected String pad(String bitString) {
    while (bitString.length() % 8 > 0) {
      bitString += "0";
    }
    while (bitString.length() % 6 > 0) {
      bitString += "0";
    }

    return bitString;
  }

}
