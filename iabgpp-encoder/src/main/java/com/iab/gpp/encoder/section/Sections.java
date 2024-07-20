package com.iab.gpp.encoder.section;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Sections {

  public static final List<String> SECTION_ORDER;

  public static final Map<Integer, String> SECTION_ID_NAME_MAP;

  static {
    SECTION_ID_NAME_MAP = new HashMap<>();

    SECTION_ID_NAME_MAP.put(TcfEuV2.ID, TcfEuV2.NAME);
    SECTION_ID_NAME_MAP.put(TcfCaV1.ID, TcfCaV1.NAME);
    SECTION_ID_NAME_MAP.put(UspV1.ID, UspV1.NAME);
    SECTION_ID_NAME_MAP.put(UsNat.ID, UsNat.NAME);
    SECTION_ID_NAME_MAP.put(UsCa.ID, UsCa.NAME);
    SECTION_ID_NAME_MAP.put(UsVa.ID, UsVa.NAME);
    SECTION_ID_NAME_MAP.put(UsCo.ID, UsCo.NAME);
    SECTION_ID_NAME_MAP.put(UsUt.ID, UsUt.NAME);
    SECTION_ID_NAME_MAP.put(UsCt.ID, UsCt.NAME);
    SECTION_ID_NAME_MAP.put(UsFl.ID, UsFl.NAME);
    SECTION_ID_NAME_MAP.put(UsMt.ID, UsMt.NAME);
    SECTION_ID_NAME_MAP.put(UsOr.ID, UsOr.NAME);
    SECTION_ID_NAME_MAP.put(UsTx.ID, UsTx.NAME);
    SECTION_ID_NAME_MAP.put(UsDe.ID, UsDe.NAME);
    SECTION_ID_NAME_MAP.put(UsIa.ID, UsIa.NAME);
    SECTION_ID_NAME_MAP.put(UsNe.ID, UsNe.NAME);
    SECTION_ID_NAME_MAP.put(UsNh.ID, UsNh.NAME);
    SECTION_ID_NAME_MAP.put(UsNj.ID, UsNj.NAME);
    SECTION_ID_NAME_MAP.put(UsTn.ID, UsTn.NAME);

    SECTION_ORDER = new ArrayList<Integer>(SECTION_ID_NAME_MAP.keySet()).stream().sorted()
        .map(id -> SECTION_ID_NAME_MAP.get(id)).collect(Collectors.toList());
  }
}
