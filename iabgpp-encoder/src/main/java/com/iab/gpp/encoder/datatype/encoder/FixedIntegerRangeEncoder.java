package com.iab.gpp.encoder.datatype.encoder;

import java.util.Collection;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.IntegerSet;
import com.iab.gpp.encoder.error.DecodingException;

public class FixedIntegerRangeEncoder {
  private FixedIntegerRangeEncoder() {}

  public static int encode(BitString builder, Collection<Integer> value) {
    BitString rangeBuilder = new BitString();
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
    builder.writeInt(groupCount, 12);
    builder.write(rangeBuilder);
    return last;
  }

  private static void writeGroup(BitString builder, int groupStart, int last) {
    if (groupStart == last) {
      builder.writeBoolean(false);
      builder.writeInt(groupStart, 16);
    } else {
      builder.writeBoolean(true);
      builder.writeInt(groupStart, 16);
      builder.writeInt(last, 16);
    }
  }

  public static IntegerSet decode(BitString reader) throws DecodingException {
    int count = reader.readInt(12);
    IntegerSet value = new IntegerSet();
    for (int i = 0; i < count; i++) {
      boolean group = reader.readBoolean();
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
