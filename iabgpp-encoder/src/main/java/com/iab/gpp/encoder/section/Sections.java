package com.iab.gpp.encoder.section;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Sections {

  public static List<String> SECTION_ORDER;

  public static Map<Integer, String> SECTION_ID_NAME_MAP;

  static {
    SECTION_ID_NAME_MAP = new HashMap<>();

    SECTION_ID_NAME_MAP.put(TcfEuV2.ID, TcfEuV2.NAME);
    SECTION_ID_NAME_MAP.put(TcfCaV1.ID, TcfCaV1.NAME);
    SECTION_ID_NAME_MAP.put(UspV1.ID, UspV1.NAME);
    SECTION_ID_NAME_MAP.put(UspNatV1.ID, UspNatV1.NAME);
    SECTION_ID_NAME_MAP.put(UspCaV1.ID, UspCaV1.NAME);
    SECTION_ID_NAME_MAP.put(UspVaV1.ID, UspVaV1.NAME);
    SECTION_ID_NAME_MAP.put(UspCoV1.ID, UspCoV1.NAME);
    SECTION_ID_NAME_MAP.put(UspUtV1.ID, UspUtV1.NAME);
    SECTION_ID_NAME_MAP.put(UspCtV1.ID, UspCtV1.NAME);

    SECTION_ORDER = new ArrayList<Integer>(SECTION_ID_NAME_MAP.keySet()).stream().sorted().map(id -> SECTION_ID_NAME_MAP.get(id))
        .collect(Collectors.toList());
  }
}
