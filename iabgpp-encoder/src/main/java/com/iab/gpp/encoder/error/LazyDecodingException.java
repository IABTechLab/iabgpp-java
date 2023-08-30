package com.iab.gpp.encoder.error;

public class LazyDecodingException extends DecodingException {

  private static final long serialVersionUID = 2098268445119981680L;

  public LazyDecodingException(String msg) {
    super(msg);
  }

  public LazyDecodingException(Exception e) {
    super(e);
  }

  public LazyDecodingException(String msg, Exception e) {
    super(msg, e);
  }
}
