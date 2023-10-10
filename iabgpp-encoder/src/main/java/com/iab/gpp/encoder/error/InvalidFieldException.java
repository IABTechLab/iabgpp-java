package com.iab.gpp.encoder.error;

public class InvalidFieldException extends RuntimeException {

  private static final long serialVersionUID = 2098268445119981680L;

  public InvalidFieldException(String msg) {
    super(msg);
  }

  public InvalidFieldException(Exception e) {
    super(e);
  }

  public InvalidFieldException(String msg, Exception e) {
    super(msg, e);
  }
}
