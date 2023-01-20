package com.iab.gpp.encoder.error;

public class DecodingException extends Exception {

  private static final long serialVersionUID = 2098268445119981680L;

  public DecodingException(String msg) {
    super(msg);
  }

  public DecodingException(Exception e) {
    super(e);
  }

  public DecodingException(String msg, Exception e) {
    super(msg, e);
  }
}
