package com.iab.gpp.encoder.field;

import java.util.function.Predicate;
import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.FixedIntegerList;

public interface FieldKey {
  DataType<?, ?> getType();

  public static final Predicate<Integer> VALIDATOR_012 = (n -> n >= 0 && n <= 2);
  public static final Predicate<Integer> VALIDATOR_12 = (n -> n >= 1 && n <= 2);
  public static final Predicate<FixedIntegerList> VALIDATOR_LIST_012 = (l -> {
    for (int n : l) {
      if (n < 0 || n > 2) {
        return false;
      }
    }
    return true;
  });
}
