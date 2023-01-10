package com.iab.gpp.encoder.datatype.encoder;

public class CompressedBase64UrlEncoder extends AbstractBase64UrlEncoder {

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
