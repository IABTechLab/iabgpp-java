package com.iab.gpp.encoder.datatype;

public interface EncodableDataType<T> extends DataType<T> {
  String encode();

  void decode(String str);
}
