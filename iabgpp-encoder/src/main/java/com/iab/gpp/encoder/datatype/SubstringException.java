package com.iab.gpp.encoder.datatype;

public class SubstringException extends Exception {

  private static final long serialVersionUID = 1825100490468259890L;

  public SubstringException(String msg) {
    super(msg);
  }

  public SubstringException(Exception e) {
    super(e);
  }

  public SubstringException(String msg, Exception e) {
    super(msg, e);
  }
}
