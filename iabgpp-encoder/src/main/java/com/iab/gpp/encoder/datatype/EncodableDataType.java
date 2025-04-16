package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;

public interface EncodableDataType<T> extends DataType<T> {
  void encode(BitStringBuilder builder);

  void decode(BitString str);
}
