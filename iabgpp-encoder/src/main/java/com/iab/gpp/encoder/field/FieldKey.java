package com.iab.gpp.encoder.field;

import java.util.function.Predicate;
import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.FixedIntegerList;

public interface FieldKey {
  String getName();
  DataType<?> getType();
  
  public static final Predicate<Integer> nullableBooleanAsTwoBitIntegerValidator = (n -> n >= 0 && n <= 2);
  public static final Predicate<Integer> nonNullableBooleanAsTwoBitIntegerValidator = (n -> n >= 1 && n <= 2);
  public static final Predicate<FixedIntegerList> nullableBooleanAsTwoBitIntegerListValidator = (l -> {
    for (int n : l) {
      if (n < 0 || n > 2) {
        return false;
      }
    }
    return true;
  });
}
