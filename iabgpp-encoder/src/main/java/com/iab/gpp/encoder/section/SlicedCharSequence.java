package com.iab.gpp.encoder.section;

import java.util.ArrayList;
import java.util.List;

public final class SlicedCharSequence implements CharSequence {

  private static final String EMPTY = "";
  private final CharSequence base;
  private final int start;
  private final int end;

  private SlicedCharSequence(CharSequence base, int start, int end) {
    this.base = base;
    this.start = start;
    this.end = end;
  }

  public static List<CharSequence> split(CharSequence charSequence, char splitter) {
    List<CharSequence> out = new ArrayList<>(1);
    int length = charSequence.length();
    int start = 0;
    for (int i = 0; i < length; i++) {
      if (charSequence.charAt(i) == splitter) {
        out.add(new SlicedCharSequence(charSequence, start, i));
        start = i + 1;
      }
    }
    out.add(new SlicedCharSequence(charSequence, start, length));
    return out;
  }

  public static final CharSequence join(char glue, List<CharSequence> pieces) {
    int size = pieces.size();
    if (size > 1) {
      int length = size - 1;
      for (int i = 0; i < size; i++) {
        length += pieces.get(i).length();
      }
      StringBuilder sb = new StringBuilder(length);
      sb.append(pieces.get(0));
      for (int i = 1; i < size; i++) {
        sb.append(glue).append(pieces.get(i));
      }
      return sb;
    }
    if (size == 1) {
      return pieces.get(0);
    }
    return EMPTY;
  }


  @Override
  public int length() {
    return end - start;
  }

  @Override
  public char charAt(int index) {
    return base.charAt(start + index);
  }

  @Override
  public CharSequence subSequence(int newStart, int newEnd) {
    return base.subSequence(start + newStart, start + newEnd);
  }

  @Override
  public String toString() {
    return base.subSequence(start, end).toString();
  }

}
