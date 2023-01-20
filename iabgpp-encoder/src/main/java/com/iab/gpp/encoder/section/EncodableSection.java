package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;

public interface EncodableSection {

  int getId();

  String getName();

  boolean hasField(String fieldName);

  Object getFieldValue(String fieldName);

  void setFieldValue(String fieldName, Object value) throws InvalidFieldException;

  String encode() throws EncodingException;

  void decode(String encodedString) throws DecodingException;
}
