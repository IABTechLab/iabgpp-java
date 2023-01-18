package com.iab.gpp.encoder.error;

public class EncodingException extends Exception {

  private static final long serialVersionUID = 1161321945571871601L;

  public EncodingException(String msg) {
    super(msg);
  }

  public EncodingException(Exception e) {
    super(e);
  }

  public EncodingException(String msg, Exception e) {
    super(msg, e);
  }
}
