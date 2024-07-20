package com.iab.gpp.encoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.field.HeaderV1Field;
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

  private EncodableSection getOrCreateSection(String sectionName) {
    EncodableSection section = this.sections.get(sectionName);
    if (section == null) {
      switch(sectionName) {
        case TcfEuV2.NAME:
          section = new TcfEuV2();
          break;
        case TcfCaV1.NAME:
          section = new TcfCaV1();
          break;
        case UspV1.NAME:
          section = new UspV1();
          break;
        case UsNatV1.NAME:
          section = new UsNatV1();
          break;
        case UsCaV1.NAME:
          section = new UsCaV1();
          break;
        case UsVaV1.NAME:
          section = new UsVaV1();
          break;
        case UsCoV1.NAME:
          section = new UsCoV1();
          break;
        case UsUtV1.NAME:
          section = new UsUtV1();
          break;
        case UsCtV1.NAME:
          section = new UsCtV1();
          break;
      }
      if (section != null) {
        this.sections.put(sectionName, section);
      }
    }
    return section;
  }

  public void setFieldValue(String sectionName, String fieldName, Object value) {
    if (!this.decoded) {
      this.sections = this.decodeModel(this.encodedString);
      this.dirty = false;
      this.decoded = true;
    }
    
    EncodableSection section = getOrCreateSection(sectionName);
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
    EncodableSection field = this.sections.get(sectionName);
    if (field != null) {
      return field.getFieldValue(fieldName);
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
    EncodableSection field = this.sections.get(sectionName);
    if (field != null) {
      return field.hasField(fieldName);
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
    
    return this.sections.get(sectionName);
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
    
    if (this.sections.remove(sectionName) != null) {
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

  public UsNatV1 getUsNatV1Section() {
    return (UsNatV1) getSection(UsNatV1.NAME);
  }

  public UsCaV1 getUsCaV1Section() {
    return (UsCaV1) getSection(UsCaV1.NAME);
  }

  public UsVaV1 getUsVaV1Section() {
    return (UsVaV1) getSection(UsVaV1.NAME);
  }

  public UsCoV1 getUsCoV1Section() {
    return (UsCoV1) getSection(UsCoV1.NAME);
  }

  public UsUtV1 getUsUtV1Section() {
    return (UsUtV1) getSection(UsUtV1.NAME);
  }

  public UsCtV1 getUsCtV1Section() {
    return (UsCtV1) getSection(UsCtV1.NAME);
  }

  public List<Integer> getSectionIds() {
    if (!this.decoded) {
      this.sections = this.decodeModel(this.encodedString);
      this.dirty = false;
      this.decoded = true;
    }
    int length = Sections.SECTION_ORDER.size();
    List<Integer> sectionIds = new ArrayList<>(length);
    for (int i = 0; i < length; i++) {
      String sectionName = Sections.SECTION_ORDER.get(i);
      EncodableSection section = this.sections.get(sectionName);
      if (section != null) {
        sectionIds.add(section.getId());
      }
    }
    return sectionIds;
  }

  protected String encodeModel(Map<String, EncodableSection> sections) {
    int length = Sections.SECTION_ORDER.size();
    List<String> encodedSections = new ArrayList<>(length);
    List<Integer> sectionIds = new ArrayList<>(length);
    for (int i = 0; i < length; i++) {
      String sectionName = Sections.SECTION_ORDER.get(i);
      EncodableSection section = sections.get(sectionName);
      if (section != null) {
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
    if(str == null || str.isEmpty() ||  str.startsWith("D")) {
      Map<String, EncodableSection> sections = new HashMap<>();
      
      if(str != null && !str.isEmpty()) {
        String[] encodedSections = str.split("~");
        HeaderV1 header = new HeaderV1(encodedSections[0]);
        sections.put(HeaderV1.NAME, header);
  
        @SuppressWarnings("unchecked")
        List<Integer> sectionIds = (List<Integer>) header.getFieldValue("SectionIds");
        for (int i = 0; i < sectionIds.size(); i++) {
          String section = encodedSections[i + 1];
          switch (sectionIds.get(i)) {
            case TcfEuV2.ID:
              sections.put(TcfEuV2.NAME, new TcfEuV2(section));
              break;
            case TcfCaV1.ID:
              sections.put(TcfCaV1.NAME, new TcfCaV1(section));
              break;
            case UspV1.ID:
              sections.put(UspV1.NAME, new UspV1(section));
              break;
            case UsCaV1.ID:
              sections.put(UsCaV1.NAME, new UsCaV1(section));
              break;
            case UsNatV1.ID:
              sections.put(UsNatV1.NAME, new UsNatV1(section));
              break;
            case UsVaV1.ID:
              sections.put(UsVaV1.NAME, new UsVaV1(section));
              break;
            case UsCoV1.ID:
              sections.put(UsCoV1.NAME, new UsCoV1(section));
              break;
            case UsUtV1.ID:
              sections.put(UsUtV1.NAME, new UsUtV1(section));
              break;
            case UsCtV1.ID:
              sections.put(UsCtV1.NAME, new UsCtV1(section));
              break;
          }
        }
      }
      
      return sections;
    } else if(str.startsWith("C")) {
      // old tcfeu only string
      Map<String, EncodableSection> sections = new HashMap<>();

      TcfEuV2 section = new TcfEuV2(str);
      sections.put(TcfEuV2.NAME, section);

      HeaderV1 header = new HeaderV1();
      header.setFieldValue(HeaderV1Field.SECTION_IDS, Arrays.asList(2));
      sections.put(HeaderV1.NAME, section);

      return sections;
    } else {
      throw new DecodingException("Unable to decode '" + str + "'");
    }
  }

  public String encodeSection(int sectionId) {
    return encodeSection(Sections.SECTION_ID_NAME_MAP.get(sectionId));
  }

  public String encodeSection(String sectionName) {
    if (!this.decoded) {
      this.sections = this.decodeModel(this.encodedString);
      this.dirty = false;
      this.decoded = true;
    }
    EncodableSection section = this.sections.get(sectionName);
    if (section != null) {
      return section.encode();
    } else {
      return null;
    }
  }

  public void decodeSection(int sectionId, String encodedString) {
    decodeSection(Sections.SECTION_ID_NAME_MAP.get(sectionId), encodedString);
  }

  public void decodeSection(String sectionName, String encodedString) {
    EncodableSection section = getOrCreateSection(sectionName);
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