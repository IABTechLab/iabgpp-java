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
    SECTION_ID_NAME_MAP.put(UsNatV1.ID, UsNatV1.NAME);
    SECTION_ID_NAME_MAP.put(UsCaV1.ID, UsCaV1.NAME);
    SECTION_ID_NAME_MAP.put(UsVaV1.ID, UsVaV1.NAME);
    SECTION_ID_NAME_MAP.put(UsCoV1.ID, UsCoV1.NAME);
    SECTION_ID_NAME_MAP.put(UsUtV1.ID, UsUtV1.NAME);
    SECTION_ID_NAME_MAP.put(UsCtV1.ID, UsCtV1.NAME);

    SECTION_ORDER = new ArrayList<Integer>(SECTION_ID_NAME_MAP.keySet()).stream().sorted()
        .map(id -> SECTION_ID_NAME_MAP.get(id)).collect(Collectors.toList());
  }
}
