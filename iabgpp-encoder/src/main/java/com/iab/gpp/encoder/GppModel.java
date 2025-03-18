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
import com.iab.gpp.encoder.section.UsCa;
import com.iab.gpp.encoder.section.UsCo;
import com.iab.gpp.encoder.section.UsCt;
import com.iab.gpp.encoder.section.UsFl;
import com.iab.gpp.encoder.section.UsMt;
import com.iab.gpp.encoder.section.UsNat;
import com.iab.gpp.encoder.section.UsOr;
import com.iab.gpp.encoder.section.UsTx;
import com.iab.gpp.encoder.section.UsUt;
import com.iab.gpp.encoder.section.UsVa;
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
      } else if (sectionName.equals(UsNat.NAME)) {
        section = new UsNat();
        this.sections.put(UsNat.NAME, section);
      } else if (sectionName.equals(UsCa.NAME)) {
        section = new UsCa();
        this.sections.put(UsCa.NAME, section);
      } else if (sectionName.equals(UsVa.NAME)) {
        section = new UsVa();
        this.sections.put(UsVa.NAME, section);
      } else if (sectionName.equals(UsCo.NAME)) {
        section = new UsCo();
        this.sections.put(UsCo.NAME, section);
      } else if (sectionName.equals(UsUt.NAME)) {
        section = new UsUt();
        this.sections.put(UsUt.NAME, section);
      } else if (sectionName.equals(UsCt.NAME)) {
        section = new UsCt();
        this.sections.put(UsCt.NAME, section);
      } else if (sectionName.equals(UsFl.NAME)) {
        section = new UsFl();
        this.sections.put(UsFl.NAME, section);
      } else if (sectionName.equals(UsMt.NAME)) {
        section = new UsMt();
        this.sections.put(UsMt.NAME, section);
      } else if (sectionName.equals(UsOr.NAME)) {
        section = new UsOr();
        this.sections.put(UsOr.NAME, section);
      } else if (sectionName.equals(UsTx.NAME)) {
        section = new UsTx();
        this.sections.put(UsTx.NAME, section);
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

  public UsNat getUsNatSection() {
    return (UsNat) getSection(UsNat.NAME);
  }

  public UsCa getUsCaSection() {
    return (UsCa) getSection(UsCa.NAME);
  }

  public UsVa getUsVaSection() {
    return (UsVa) getSection(UsVa.NAME);
  }

  public UsCo getUsCoSection() {
    return (UsCo) getSection(UsCo.NAME);
  }

  public UsUt getUsUtSection() {
    return (UsUt) getSection(UsUt.NAME);
  }

  public UsCt getUsCtSection() {
    return (UsCt) getSection(UsCt.NAME);
  }
  
  public UsFl getUsFlSection() {
    return (UsFl) getSection(UsFl.NAME);
  }
  
  public UsMt getUsMtSection() {
    return (UsMt) getSection(UsMt.NAME);
  }
  
  public UsOr getUsOrSection() {
    return (UsOr) getSection(UsOr.NAME);
  }
  
  public UsTx getUsTxSection() {
    return (UsTx) getSection(UsTx.NAME);
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
    if(str == null || str.isEmpty() ||  str.startsWith("D")) {
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
          } else if (sectionIds.get(i).equals(UsCa.ID)) {
            UsCa section = new UsCa(encodedSections[i + 1]);
            sections.put(UsCa.NAME, section);
          } else if (sectionIds.get(i).equals(UsNat.ID)) {
            UsNat section = new UsNat(encodedSections[i + 1]);
            sections.put(UsNat.NAME, section);
          } else if (sectionIds.get(i).equals(UsVa.ID)) {
            UsVa section = new UsVa(encodedSections[i + 1]);
            sections.put(UsVa.NAME, section);
          } else if (sectionIds.get(i).equals(UsCo.ID)) {
            UsCo section = new UsCo(encodedSections[i + 1]);
            sections.put(UsCo.NAME, section);
          } else if (sectionIds.get(i).equals(UsUt.ID)) {
            UsUt section = new UsUt(encodedSections[i + 1]);
            sections.put(UsUt.NAME, section);
          } else if (sectionIds.get(i).equals(UsCt.ID)) {
            UsCt section = new UsCt(encodedSections[i + 1]);
            sections.put(UsCt.NAME, section);
          } else if (sectionIds.get(i).equals(UsFl.ID)) {
            UsFl section = new UsFl(encodedSections[i + 1]);
            sections.put(UsFl.NAME, section);
          } else if (sectionIds.get(i).equals(UsMt.ID)) {
            UsMt section = new UsMt(encodedSections[i + 1]);
            sections.put(UsMt.NAME, section);
          } else if (sectionIds.get(i).equals(UsOr.ID)) {
            UsOr section = new UsOr(encodedSections[i + 1]);
            sections.put(UsOr.NAME, section);
          } else if (sectionIds.get(i).equals(UsTx.ID)) {
            UsTx section = new UsTx(encodedSections[i + 1]);
            sections.put(UsTx.NAME, section);
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
      } else if (sectionName.equals(UsNat.NAME)) {
        section = new UsNat();
        this.sections.put(UsNat.NAME, section);
      } else if (sectionName.equals(UsCa.NAME)) {
        section = new UsCa();
        this.sections.put(UsCa.NAME, section);
      } else if (sectionName.equals(UsVa.NAME)) {
        section = new UsVa();
        this.sections.put(UsVa.NAME, section);
      } else if (sectionName.equals(UsCo.NAME)) {
        section = new UsCo();
        this.sections.put(UsCo.NAME, section);
      } else if (sectionName.equals(UsUt.NAME)) {
        section = new UsUt();
        this.sections.put(UsUt.NAME, section);
      } else if (sectionName.equals(UsCt.NAME)) {
        section = new UsCt();
        this.sections.put(UsCt.NAME, section);
      } else if (sectionName.equals(UsFl.NAME)) {
        section = new UsFl();
        this.sections.put(UsFl.NAME, section);
      } else if (sectionName.equals(UsMt.NAME)) {
        section = new UsMt();
        this.sections.put(UsMt.NAME, section);
      } else if (sectionName.equals(UsOr.NAME)) {
        section = new UsOr();
        this.sections.put(UsOr.NAME, section);
      } else if (sectionName.equals(UsTx.NAME)) {
        section = new UsTx();
        this.sections.put(UsTx.NAME, section);
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