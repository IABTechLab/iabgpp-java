package com.iab.gpp.encoder.datatype.encoder;

public class TraditionalBase64UrlEncoder extends AbstractBase64UrlEncoder {
  
  @Override
  protected String pad(String bitString) {
    while (bitString.length() % 24 > 0) {
      bitString += "0";
    }
    return bitString;
  }
  
}
