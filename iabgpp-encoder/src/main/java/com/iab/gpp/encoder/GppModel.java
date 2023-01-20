package com.iab.gpp.encoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.section.EncodableSection;
import com.iab.gpp.encoder.section.HeaderV1;
import com.iab.gpp.encoder.section.Sections;
import com.iab.gpp.encoder.section.TcfCaV1;
import com.iab.gpp.encoder.section.TcfEuV2;
import com.iab.gpp.encoder.section.UspCaV1;
import com.iab.gpp.encoder.section.UspCoV1;
import com.iab.gpp.encoder.section.UspCtV1;
import com.iab.gpp.encoder.section.UspNatV1;
import com.iab.gpp.encoder.section.UspUtV1;
import com.iab.gpp.encoder.section.UspV1;
import com.iab.gpp.encoder.section.UspVaV1;

public class GppModel {
  private Map<String, EncodableSection> sections = new HashMap<>();

  public GppModel() {

  }

  public GppModel(String encodedString) throws DecodingException {
    if (encodedString != null && encodedString.length() > 0) {
      this.decode(encodedString);
    }
  }

  public void setFieldValue(int sectionId, String fieldName, Object value) throws InvalidFieldException {
    setFieldValue(Sections.SECTION_ID_NAME_MAP.get(sectionId), fieldName, value);
  }

  public void setFieldValue(String sectionName, String fieldName, Object value) throws InvalidFieldException {
    EncodableSection section = null;
    if (!this.sections.containsKey(sectionName)) {
      if (sectionName.equals(TcfCaV1.NAME)) {
        section = new TcfCaV1();
        this.sections.put(TcfCaV1.NAME, section);
      } else if (sectionName.equals(TcfEuV2.NAME)) {
        section = new TcfEuV2();
        this.sections.put(TcfEuV2.NAME, section);
      } else if (sectionName.equals(UspV1.NAME)) {
        section = new UspV1();
        this.sections.put(UspV1.NAME, section);
      } else if (sectionName.equals(UspNatV1.NAME)) {
        section = new UspNatV1();
        this.sections.put(UspNatV1.NAME, section);
      } else if (sectionName.equals(UspCaV1.NAME)) {
        section = new UspCaV1();
        this.sections.put(UspCaV1.NAME, section);
      } else if (sectionName.equals(UspVaV1.NAME)) {
        section = new UspVaV1();
        this.sections.put(UspVaV1.NAME, section);
      } else if (sectionName.equals(UspCoV1.NAME)) {
        section = new UspCoV1();
        this.sections.put(UspCoV1.NAME, section);
      } else if (sectionName.equals(UspUtV1.NAME)) {
        section = new UspUtV1();
        this.sections.put(UspUtV1.NAME, section);
      } else if (sectionName.equals(UspCtV1.NAME)) {
        section = new UspCtV1();
        this.sections.put(UspCtV1.NAME, section);
      }
    } else {
      section = this.sections.get(sectionName);
    }

    if (section != null) {
      section.setFieldValue(fieldName, value);
    } else {
      throw new InvalidFieldException(sectionName + "." + fieldName + " not found");
    }
  }

  public Object getFieldValue(int sectionId, String fieldName) {
    return getFieldValue(Sections.SECTION_ID_NAME_MAP.get(sectionId), fieldName);
  }

  public Object getFieldValue(String sectionName, String fieldName) {
    if (this.sections.containsKey(sectionName)) {
      return this.sections.get(sectionName).getFieldValue(fieldName);
    } else {
      return null;
    }
  }

  public boolean hasField(int sectionId, String fieldName) {
    return hasField(Sections.SECTION_ID_NAME_MAP.get(sectionId), fieldName);
  }

  public boolean hasField(String sectionName, String fieldName) {
    if (this.sections.containsKey(sectionName)) {
      return this.sections.get(sectionName).hasField(fieldName);
    } else {
      return false;
    }
  }

  public boolean hasSection(int sectionId) {
    return hasSection(Sections.SECTION_ID_NAME_MAP.get(sectionId));
  }

  public boolean hasSection(String sectionName) {
    return this.sections.containsKey(sectionName);
  }

  public HeaderV1 getHeader() {
    HeaderV1 header = new HeaderV1();
    try {
      header.setFieldValue("SectionIds", this.getSectionIds());
    } catch (InvalidFieldException e) {

    }
    return header;
  }

  public EncodableSection getSection(int sectionId) {
    return getSection(Sections.SECTION_ID_NAME_MAP.get(sectionId));
  }

  public EncodableSection getSection(String sectionName) {
    if (this.sections.containsKey(sectionName)) {
      return this.sections.get(sectionName);
    } else {
      return null;
    }
  }

  public void deleteSection(int sectionId) {
    deleteSection(Sections.SECTION_ID_NAME_MAP.get(sectionId));
  }

  public void deleteSection(String sectionName) {
    if (this.sections.containsKey(sectionName)) {
      this.sections.remove(sectionName);
    }
  }

  public void clear() {
    this.sections.clear();
  }

  public TcfCaV1 getTcfCaV1Section() {
    return (TcfCaV1) getSection(TcfCaV1.NAME);
  }

  public TcfEuV2 getTcfEuV2Section() {
    return (TcfEuV2) getSection(TcfEuV2.NAME);
  }

  public UspV1 getUspV1Section() {
    return (UspV1) getSection(UspV1.NAME);
  }

  public UspNatV1 getUspNatV1Section() {
    return (UspNatV1) getSection(UspNatV1.NAME);
  }

  public UspCaV1 getUspCaV1Section() {
    return (UspCaV1) getSection(UspCaV1.NAME);
  }

  public UspVaV1 getUspVaV1Section() {
    return (UspVaV1) getSection(UspVaV1.NAME);
  }

  public UspCoV1 getUspCoV1Section() {
    return (UspCoV1) getSection(UspCoV1.NAME);
  }

  public UspUtV1 getUspUtV1Section() {
    return (UspUtV1) getSection(UspUtV1.NAME);
  }

  public UspCtV1 getUspCtV1Section() {
    return (UspCtV1) getSection(UspCtV1.NAME);
  }

  public List<Integer> getSectionIds() {
    List<Integer> sectionIds = new ArrayList<>();
    for (int i = 0; i < Sections.SECTION_ORDER.size(); i++) {
      String sectionName = Sections.SECTION_ORDER.get(i);
      if (this.sections.containsKey(sectionName)) {
        EncodableSection section = this.sections.get(sectionName);
        sectionIds.add(section.getId());
      }
    }
    return sectionIds;
  }

  public String encode() throws EncodingException {
    List<String> encodedSections = new ArrayList<>();
    List<Integer> sectionIds = new ArrayList<>();
    for (int i = 0; i < Sections.SECTION_ORDER.size(); i++) {
      String sectionName = Sections.SECTION_ORDER.get(i);
      if (this.sections.containsKey(sectionName)) {
        EncodableSection section = this.sections.get(sectionName);
        encodedSections.add(section.encode());
        sectionIds.add(section.getId());
      }
    }

    HeaderV1 header = new HeaderV1();
    try {
      header.setFieldValue("SectionIds", this.getSectionIds());
    } catch (InvalidFieldException e) {
      throw new EncodingException(e);
    }
    encodedSections.add(0, header.encode());

    String encodedString = encodedSections.stream().collect(Collectors.joining("~"));
    return encodedString;
  }

  public void decode(String str) throws DecodingException {
    this.sections.clear();

    String[] encodedSections = str.split("~");
    HeaderV1 header = new HeaderV1(encodedSections[0]);
    this.sections.put(HeaderV1.NAME, header);

    @SuppressWarnings("unchecked")
    List<Integer> sectionIds = (List<Integer>) header.getFieldValue("SectionIds");
    for (int i = 0; i < sectionIds.size(); i++) {
      if (sectionIds.get(i).equals(TcfEuV2.ID)) {
        TcfEuV2 section = new TcfEuV2(encodedSections[i + 1]);
        this.sections.put(TcfEuV2.NAME, section);
      } else if (sectionIds.get(i).equals(TcfCaV1.ID)) {
        TcfCaV1 section = new TcfCaV1(encodedSections[i + 1]);
        this.sections.put(TcfCaV1.NAME, section);
      } else if (sectionIds.get(i).equals(UspV1.ID)) {
        UspV1 section = new UspV1(encodedSections[i + 1]);
        this.sections.put(UspV1.NAME, section);
      } else if (sectionIds.get(i).equals(UspCaV1.ID)) {
        UspCaV1 section = new UspCaV1(encodedSections[i + 1]);
        this.sections.put(UspCaV1.NAME, section);
      } else if (sectionIds.get(i).equals(UspNatV1.ID)) {
        UspNatV1 section = new UspNatV1(encodedSections[i + 1]);
        this.sections.put(UspNatV1.NAME, section);
      } else if (sectionIds.get(i).equals(UspVaV1.ID)) {
        UspVaV1 section = new UspVaV1(encodedSections[i + 1]);
        this.sections.put(UspVaV1.NAME, section);
      } else if (sectionIds.get(i).equals(UspCoV1.ID)) {
        UspCoV1 section = new UspCoV1(encodedSections[i + 1]);
        this.sections.put(UspCoV1.NAME, section);
      } else if (sectionIds.get(i).equals(UspUtV1.ID)) {
        UspUtV1 section = new UspUtV1(encodedSections[i + 1]);
        this.sections.put(UspUtV1.NAME, section);
      } else if (sectionIds.get(i).equals(UspCtV1.ID)) {
        UspCtV1 section = new UspCtV1(encodedSections[i + 1]);
        this.sections.put(UspCtV1.NAME, section);
      }
    }
  }

  public String encodeSection(int sectionId) throws EncodingException {
    return encodeSection(Sections.SECTION_ID_NAME_MAP.get(sectionId));
  }

  public String encodeSection(String sectionName) throws EncodingException {
    if (this.sections.containsKey(sectionName)) {
      return this.sections.get(sectionName).encode();
    } else {
      return null;
    }
  }

  public void decodeSection(int sectionId, String encodedString) throws DecodingException {
    decodeSection(Sections.SECTION_ID_NAME_MAP.get(sectionId), encodedString);
  }

  public void decodeSection(String sectionName, String encodedString) throws DecodingException {
    EncodableSection section = null;
    if (!this.sections.containsKey(sectionName)) {
      if (sectionName.equals(TcfEuV2.NAME)) {
        section = new TcfEuV2();
        this.sections.put(TcfEuV2.NAME, section);
      } else if (sectionName.equals(TcfCaV1.NAME)) {
        section = new TcfCaV1();
        this.sections.put(TcfCaV1.NAME, section);
      } else if (sectionName.equals(UspV1.NAME)) {
        section = new UspV1();
        this.sections.put(UspV1.NAME, section);
      } else if (sectionName.equals(UspV1.NAME)) {
        section = new UspV1();
        this.sections.put(UspV1.NAME, section);
      } else if (sectionName.equals(UspNatV1.NAME)) {
        section = new UspNatV1();
        this.sections.put(UspNatV1.NAME, section);
      } else if (sectionName.equals(UspCaV1.NAME)) {
        section = new UspCaV1();
        this.sections.put(UspCaV1.NAME, section);
      } else if (sectionName.equals(UspVaV1.NAME)) {
        section = new UspVaV1();
        this.sections.put(UspVaV1.NAME, section);
      } else if (sectionName.equals(UspCoV1.NAME)) {
        section = new UspCoV1();
        this.sections.put(UspCoV1.NAME, section);
      } else if (sectionName.equals(UspUtV1.NAME)) {
        section = new UspUtV1();
        this.sections.put(UspUtV1.NAME, section);
      } else if (sectionName.equals(UspCtV1.NAME)) {
        section = new UspCtV1();
        this.sections.put(UspCtV1.NAME, section);
      }
    } else {
      section = this.sections.get(sectionName);
    }

    if (section != null) {
      section.decode(encodedString);
    }
  }
}
