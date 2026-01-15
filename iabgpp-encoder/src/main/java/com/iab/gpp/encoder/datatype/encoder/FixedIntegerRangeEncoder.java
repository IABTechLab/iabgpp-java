package com.iab.gpp.encoder.datatype.encoder;

import java.util.Collection;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.bitstring.BitStringReader;
import com.iab.gpp.encoder.error.DecodingException;

public class FixedIntegerRangeEncoder {
  private FixedIntegerRangeEncoder() {}

  public static int encode(BitStringBuilder builder, Collection<Integer> value) {
    BitStringBuilder rangeBuilder = new BitStringBuilder();
    int groupStart = -1;
    int last = Integer.MIN_VALUE;
    int groupCount = 0;
    for (Integer item : value) {
      if (last != (item - 1)) {
        if (groupStart > 0) {
          groupCount++;
          writeGroup(rangeBuilder, groupStart, last);
        }
        groupStart = item;
      }
      last = item;
    }
    if (groupStart > 0) {
      groupCount++;
      writeGroup(rangeBuilder, groupStart, last);
    }
    FixedIntegerEncoder.encode(builder,groupCount, 12);
    builder.append(rangeBuilder);
    return last;
  }

  private static void writeGroup(BitStringBuilder builder, int groupStart, int last) {
    if (groupStart == last) {
      builder.append(false);
      FixedIntegerEncoder.encode(builder, groupStart, 16);
    } else {
      builder.append(true);
      FixedIntegerEncoder.encode(builder, groupStart, 16);
      FixedIntegerEncoder.encode(builder, last, 16);
    }
  }

  public static IntegerSet decode(BitStringReader reader) throws DecodingException {
    int count = reader.readInt(12);
    IntegerSet value = new IntegerSet();
    for (int i = 0; i < count; i++) {
      boolean group = reader.readBool();
      if (group) {
        int start = reader.readInt(16);
        int end = reader.readInt(16);
        value.addRange(start, end + 1);
      } else {
        value.addInt(reader.readInt(16));
      }
    }
    return value;
  }
}
