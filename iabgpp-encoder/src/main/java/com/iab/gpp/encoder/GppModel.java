package com.iab.gpp.encoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.section.EncodableSection;
import com.iab.gpp.encoder.section.HeaderV1;
import com.iab.gpp.encoder.section.Sections;
import com.iab.gpp.encoder.section.TcfCaV1;
import com.iab.gpp.encoder.section.TcfEuV2;
import com.iab.gpp.encoder.section.UsCaV1;
import com.iab.gpp.encoder.section.UsCoV1;
import com.iab.gpp.encoder.section.UsCtV1;
import com.iab.gpp.encoder.section.UsNatV1;
import com.iab.gpp.encoder.section.UsUtV1;
import com.iab.gpp.encoder.section.UsVaV1;
import com.iab.gpp.encoder.section.UspV1;

public class GppModel {
  private Map<String, EncodableSection> sections = new HashMap<>();

  private String encodedString;
  
  private boolean dirty = false;
  private boolean decoded = true;
  
  public GppModel() {

  }

  public GppModel(String encodedString) {
    decode(encodedString);
  }

  public void setFieldValue(int sectionId, String fieldName, Object value) {
    setFieldValue(Sections.SECTION_ID_NAME_MAP.get(sectionId), fieldName, value);
  }

  public void setFieldValue(String sectionName, String fieldName, Object value) {
    if (!this.decoded) {
      this.sections = this.decodeModel(this.encodedString);
      this.dirty = false;
      this.decoded = true;
    }
    
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
      } else if (sectionName.equals(UsNatV1.NAME)) {
        section = new UsNatV1();
        this.sections.put(UsNatV1.NAME, section);
      } else if (sectionName.equals(UsCaV1.NAME)) {
        section = new UsCaV1();
        this.sections.put(UsCaV1.NAME, section);
      } else if (sectionName.equals(UsVaV1.NAME)) {
        section = new UsVaV1();
        this.sections.put(UsVaV1.NAME, section);
      } else if (sectionName.equals(UsCoV1.NAME)) {
        section = new UsCoV1();
        this.sections.put(UsCoV1.NAME, section);
      } else if (sectionName.equals(UsUtV1.NAME)) {
        section = new UsUtV1();
        this.sections.put(UsUtV1.NAME, section);
      } else if (sectionName.equals(UsCtV1.NAME)) {
        section = new UsCtV1();
        this.sections.put(UsCtV1.NAME, section);
      }
    } else {
      section = this.sections.get(sectionName);
    }

    if (section != null) {
      section.setFieldValue(fieldName, value);
      this.dirty = true;
    } else {
      throw new InvalidFieldException(sectionName + "." + fieldName + " not found");
    }
  }

  public Object getFieldValue(int sectionId, String fieldName) {
    return getFieldValue(Sections.SECTION_ID_NAME_MAP.get(sectionId), fieldName);
  }

  public Object getFieldValue(String sectionName, String fieldName) {
    if (!this.decoded) {
      this.sections = this.decodeModel(this.encodedString);
      this.dirty = false;
      this.decoded = true;
    }
    
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
    if (!this.decoded) {
      this.sections = this.decodeModel(this.encodedString);
      this.dirty = false;
      this.decoded = true;
    }
    
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
    if (!this.decoded) {
      this.sections = this.decodeModel(this.encodedString);
      this.dirty = false;
      this.decoded = true;
    }
    
    return this.sections.containsKey(sectionName);
  }

  public HeaderV1 getHeader() {
    if (!this.decoded) {
      this.sections = this.decodeModel(this.encodedString);
      this.dirty = false;
      this.decoded = true;
    }
    
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
    if (!this.decoded) {
      this.sections = this.decodeModel(this.encodedString);
      this.dirty = false;
      this.decoded = true;
    }
    
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
    if (!this.decoded) {
      this.sections = this.decodeModel(this.encodedString);
      this.dirty = false;
      this.decoded = true;
    }
    
    if (this.sections.containsKey(sectionName)) {
      this.sections.remove(sectionName);
      this.dirty = true;
    }
  }

  public void clear() {
    this.sections.clear();
    this.encodedString = null;
    this.dirty = false;
    this.decoded = true;
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

  public UsNatV1 getUspNatV1Section() {
    return (UsNatV1) getSection(UsNatV1.NAME);
  }

  public UsCaV1 getUspCaV1Section() {
    return (UsCaV1) getSection(UsCaV1.NAME);
  }

  public UsVaV1 getUspVaV1Section() {
    return (UsVaV1) getSection(UsVaV1.NAME);
  }

  public UsCoV1 getUspCoV1Section() {
    return (UsCoV1) getSection(UsCoV1.NAME);
  }

  public UsUtV1 getUspUtV1Section() {
    return (UsUtV1) getSection(UsUtV1.NAME);
  }

  public UsCtV1 getUspCtV1Section() {
    return (UsCtV1) getSection(UsCtV1.NAME);
  }

  public List<Integer> getSectionIds() {
    if (!this.decoded) {
      this.sections = this.decodeModel(this.encodedString);
      this.dirty = false;
      this.decoded = true;
    }
    
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

  protected String encodeModel(Map<String, EncodableSection> sections) {
    List<String> encodedSections = new ArrayList<>();
    List<Integer> sectionIds = new ArrayList<>();
    for (int i = 0; i < Sections.SECTION_ORDER.size(); i++) {
      String sectionName = Sections.SECTION_ORDER.get(i);
      if (sections.containsKey(sectionName)) {
        EncodableSection section = sections.get(sectionName);
        encodedSections.add(section.encode());
        sectionIds.add(section.getId());
      }
    }

    HeaderV1 header = new HeaderV1();
    try {
      header.setFieldValue("SectionIds", getSectionIds());
    } catch (InvalidFieldException e) {
      throw new EncodingException(e);
    }
    encodedSections.add(0, header.encode());

    String encodedString = encodedSections.stream().collect(Collectors.joining("~"));
    return encodedString;
  }

  protected Map<String, EncodableSection> decodeModel(String str) {
    Map<String, EncodableSection> sections = new HashMap<>();
    
    if(str != null && !str.isEmpty()) {
      String[] encodedSections = str.split("~");
      HeaderV1 header = new HeaderV1(encodedSections[0]);
      sections.put(HeaderV1.NAME, header);

      @SuppressWarnings("unchecked")
      List<Integer> sectionIds = (List<Integer>) header.getFieldValue("SectionIds");
      for (int i = 0; i < sectionIds.size(); i++) {
        if (sectionIds.get(i).equals(TcfEuV2.ID)) {
          TcfEuV2 section = new TcfEuV2(encodedSections[i + 1]);
          sections.put(TcfEuV2.NAME, section);
        } else if (sectionIds.get(i).equals(TcfCaV1.ID)) {
          TcfCaV1 section = new TcfCaV1(encodedSections[i + 1]);
          sections.put(TcfCaV1.NAME, section);
        } else if (sectionIds.get(i).equals(UspV1.ID)) {
          UspV1 section = new UspV1(encodedSections[i + 1]);
          sections.put(UspV1.NAME, section);
        } else if (sectionIds.get(i).equals(UsCaV1.ID)) {
          UsCaV1 section = new UsCaV1(encodedSections[i + 1]);
          sections.put(UsCaV1.NAME, section);
        } else if (sectionIds.get(i).equals(UsNatV1.ID)) {
          UsNatV1 section = new UsNatV1(encodedSections[i + 1]);
          sections.put(UsNatV1.NAME, section);
        } else if (sectionIds.get(i).equals(UsVaV1.ID)) {
          UsVaV1 section = new UsVaV1(encodedSections[i + 1]);
          sections.put(UsVaV1.NAME, section);
        } else if (sectionIds.get(i).equals(UsCoV1.ID)) {
          UsCoV1 section = new UsCoV1(encodedSections[i + 1]);
          sections.put(UsCoV1.NAME, section);
        } else if (sectionIds.get(i).equals(UsUtV1.ID)) {
          UsUtV1 section = new UsUtV1(encodedSections[i + 1]);
          sections.put(UsUtV1.NAME, section);
        } else if (sectionIds.get(i).equals(UsCtV1.ID)) {
          UsCtV1 section = new UsCtV1(encodedSections[i + 1]);
          sections.put(UsCtV1.NAME, section);
        }
      }
    }
    
    return sections;
  }

  public String encodeSection(int sectionId) {
    return encodeSection(Sections.SECTION_ID_NAME_MAP.get(sectionId));
  }

  public String encodeSection(String sectionName) {
    if (this.sections.containsKey(sectionName)) {
      return this.sections.get(sectionName).encode();
    } else {
      return null;
    }
  }

  public void decodeSection(int sectionId, String encodedString) {
    decodeSection(Sections.SECTION_ID_NAME_MAP.get(sectionId), encodedString);
  }

  public void decodeSection(String sectionName, String encodedString) {
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
      } else if (sectionName.equals(UsNatV1.NAME)) {
        section = new UsNatV1();
        this.sections.put(UsNatV1.NAME, section);
      } else if (sectionName.equals(UsCaV1.NAME)) {
        section = new UsCaV1();
        this.sections.put(UsCaV1.NAME, section);
      } else if (sectionName.equals(UsVaV1.NAME)) {
        section = new UsVaV1();
        this.sections.put(UsVaV1.NAME, section);
      } else if (sectionName.equals(UsCoV1.NAME)) {
        section = new UsCoV1();
        this.sections.put(UsCoV1.NAME, section);
      } else if (sectionName.equals(UsUtV1.NAME)) {
        section = new UsUtV1();
        this.sections.put(UsUtV1.NAME, section);
      } else if (sectionName.equals(UsCtV1.NAME)) {
        section = new UsCtV1();
        this.sections.put(UsCtV1.NAME, section);
      }
    } else {
      section = this.sections.get(sectionName);
    }

    if (section != null) {
      section.decode(encodedString);
    }
  }
  
  public String encode() {
    if (this.encodedString == null || this.encodedString.isEmpty() || this.dirty) {
      this.encodedString = encodeModel(this.sections);
      this.dirty = false;
      this.decoded = true;
    }

    return this.encodedString;
  }
  
  public void decode(String encodedString) {
    this.encodedString = encodedString;
    this.dirty = false;
    this.decoded = false;
  }
  
  
}