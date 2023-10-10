package com.iab.gpp.encoder.error;

public class ValidationException extends DecodingException {

  private static final long serialVersionUID = 2098268445119981680L;

  public ValidationException(String msg) {
    super(msg);
  }

  public ValidationException(Exception e) {
    super(e);
  }

  public ValidationException(String msg, Exception e) {
    super(msg, e);
  }
}
