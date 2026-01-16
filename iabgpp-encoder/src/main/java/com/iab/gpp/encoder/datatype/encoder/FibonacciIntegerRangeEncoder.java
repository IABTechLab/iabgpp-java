package com.iab.gpp.encoder.datatype.encoder;

import java.util.Collection;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.bitstring.BitStringReader;
import com.iab.gpp.encoder.error.DecodingException;

public class FibonacciIntegerRangeEncoder {
  private FibonacciIntegerRangeEncoder() {}

  public static int encode(BitStringBuilder builder, Collection<Integer> value) {
    BitStringBuilder rangeBuilder = new BitStringBuilder();
    int groupStart = -1;
    int last = Integer.MIN_VALUE;
    int offset = 0;
    int groupCount = 0;
    for (Integer item : value) {
      if (last != (item - 1)) {
        if (groupStart > 0) {
          groupCount++;
          writeGroup(rangeBuilder, groupStart, last, offset);
          offset = last;
        }
        groupStart = item;
      }
      last = item;
    }
    if (groupStart > 0) {
      groupCount++;
      writeGroup(rangeBuilder, groupStart, last, offset);
    }
    FixedIntegerEncoder.encode(builder,groupCount, 12);
    builder.append(rangeBuilder);
    return last;
  }

  private static void writeGroup(BitStringBuilder builder, int groupStart, int last, int offset) {
    int base = groupStart - offset;
    int span = last - groupStart;
    if (span == 0) {
      builder.append(false);
      FibonacciIntegerEncoder.encode(builder, base);
    } else {
      builder.append(true);
      FibonacciIntegerEncoder.encode(builder, base);
      FibonacciIntegerEncoder.encode(builder, span);
    }
  }

  public static IntegerSet decode(BitStringReader reader) throws DecodingException {
    int count = reader.readInt(12);
    IntegerSet value = new IntegerSet();
    int offset = 0;
    for (int i = 0; i < count; i++) {
      boolean group = reader.readBool();
      if (group) {
        int start = reader.readFibonacci() + offset;
        offset = start;
        int end = reader.readFibonacci() + offset;
        offset = end;
        value.addRange(start, end + 1);
      } else {
        int val = reader.readFibonacci() + offset;
        offset = val;
        value.addInt(val);
      }
    }
    return value;
  }
}
