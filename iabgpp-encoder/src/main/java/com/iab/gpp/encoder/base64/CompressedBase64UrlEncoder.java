package com.iab.gpp.encoder.base64;

import java.util.Arrays;

public class CompressedBase64UrlEncoder extends AbstractBase64UrlEncoder {

  private static CompressedBase64UrlEncoder instance = new CompressedBase64UrlEncoder();
  
  private CompressedBase64UrlEncoder() {
    
  }
  
  public static CompressedBase64UrlEncoder getInstance() {
    return instance;
  }
  
  @Override
  protected String pad(String bitString) {
    // https://github.com/InteractiveAdvertisingBureau/Global-Privacy-Platform/blob/main/Core/Consent%20String%20Specification.md#creating-a-gpp-string
    char[] chars1 = null;
    if(bitString.length() % 6 > 0) {
      chars1 = new char[6 - (bitString.length() % 6)];
    } else {
      chars1 = new char[0];
    }
    Arrays.fill(chars1, '0');

    return bitString + new String(chars1);
  }

}
