package com.iab.gpp.encoder.base64;

import java.util.Arrays;

public class CompressedBase64UrlEncoder extends AbstractBase64UrlEncoder {

  private static final CompressedBase64UrlEncoder instance = new CompressedBase64UrlEncoder();
  
  private CompressedBase64UrlEncoder() {
    
  }
  
  public static CompressedBase64UrlEncoder getInstance() {
    return instance;
  }
  
  @Override
  protected String pad(String bitString) {
    char[] chars1 = null;
    if(bitString.length() % 8 > 0) {
      chars1 = new char[8 - (bitString.length() % 8)];
    } else {
      chars1 = new char[0];
    }
    Arrays.fill(chars1, '0');
    
    char[] chars2 = null;
    if((bitString.length() + chars1.length) % 6 > 0) {
      chars2 = new char[6 - ((bitString.length() + chars1.length) % 6)];
    } else {
      chars2 = new char[0];
    }
    Arrays.fill(chars2, '0');
    
    return bitString + new String(chars1) + new String(chars2);
  }

}
