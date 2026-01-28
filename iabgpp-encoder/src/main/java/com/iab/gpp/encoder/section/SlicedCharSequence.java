package com.iab.gpp.encoder.section;

import java.util.ArrayList;
import java.util.List;

public final class SlicedCharSequence implements CharSequence {

  private static final String EMPTY = "";
  private final String base;
  private final int start;
  private final int end;

  private SlicedCharSequence(String base, int start, int end) {
    this.base = base;
    this.start = start;
    this.end = end;
  }

  public static List<CharSequence> split(CharSequence charSequence, char splitter) {
    // the first time we see some other CharSequence we convert to a String.
    // this keeps all derived SlicedCharSequence instances anchored to the same base String.
    // this is important because String.indexOf internally uses an optimized intrinsic.
    // CharSequence does not have indexOf, only charAt which is quite slow in comparison.
    // also we avoid a recursive structure of SlicedCharSequence.
    String base;
    int start;
    int end;
    if (charSequence instanceof SlicedCharSequence) {
      SlicedCharSequence slicedCharSequence = (SlicedCharSequence) charSequence;
      base = slicedCharSequence.base;
      start = slicedCharSequence.start;
      end = slicedCharSequence.end;
    } else {
      base = charSequence.toString();
      start = 0;
      end = base.length();
    }
    // most sections/segments have less than 4 components
    List<CharSequence> out = new ArrayList<>(4);
    int next = 0;
    while ((next = base.indexOf(splitter, start, end)) != -1) {
      out.add(new SlicedCharSequence(base, start, next));
      start = next + 1;
    }
    out.add(new SlicedCharSequence(base, start, end));
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
    return base.substring(start, end);
  }

}
