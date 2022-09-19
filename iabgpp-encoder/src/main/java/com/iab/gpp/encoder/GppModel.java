package com.iab.gpp.encoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.section.EncodableSection;
import com.iab.gpp.encoder.section.HeaderV1;
import com.iab.gpp.encoder.section.TcfEuV2;
import com.iab.gpp.encoder.section.UspV1;

public class GppModel {
  private Map<String, EncodableSection> sections = new HashMap<>();
  private String[] sectionOrder = new String[] {TcfEuV2.NAME, UspV1.NAME};

  public GppModel() {
    
  }
  
  public GppModel(String encodedString) throws DecodingException {
    if (encodedString != null && encodedString.length() > 0) {
      this.decode(encodedString);
    }
  }

  public void setFieldValue(String sectionName, String fieldName, Object value) {
    EncodableSection section = null;
    if (!this.sections.containsKey(sectionName)) {
      if (sectionName.equals(TcfEuV2.NAME)) {
        section = new TcfEuV2();
        this.sections.put(TcfEuV2.NAME, section);
      } else if (sectionName.equals(UspV1.NAME)) {
        section = new UspV1();
        this.sections.put(UspV1.NAME, section);
      }
    } else {
      section = this.sections.get(sectionName);
    }

    if (section != null) {
      section.setFieldValue(fieldName, value);
    } else {
      throw new Error(sectionName + " not found");
    }
  }

  public Object getFieldValue(String sectionName, String fieldName) {
    if (this.sections.containsKey(sectionName)) {
      return this.sections.get(sectionName).getFieldValue(fieldName);
    } else {
      return null;
    }
  }

  public boolean hasField(String sectionName, String fieldName) {
    if (this.sections.containsKey(sectionName)) {
      return this.sections.get(sectionName).hasField(fieldName);
    } else {
      return false;
    }
  }

  public boolean hasSection(String sectionName) {
    return this.sections.containsKey(sectionName);
  }

  public HeaderV1 getHeader() {
    HeaderV1 header = new HeaderV1();
    header.setFieldValue("SectionIds", this.getSectionIds());
    return header;
  }

  public EncodableSection getSection(String sectionName) {
    if (this.sections.containsKey(sectionName)) {
      return this.sections.get(sectionName);
    } else {
      return null;
    }
  }

  public TcfEuV2 getTcfEuV2Section() {
    return (TcfEuV2)getSection(TcfEuV2.NAME);
  }
  
  public UspV1 getUspV1Section() {
    return (UspV1)getSection(UspV1.NAME);
  }
  
  public List<Integer> getSectionIds() {
    List<Integer> sectionIds = new ArrayList<>();
    for (int i = 0; i < this.sectionOrder.length; i++) {
      String sectionName = this.sectionOrder[i];
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
    for (int i = 0; i < this.sectionOrder.length; i++) {
      String sectionName = this.sectionOrder[i];
      if (this.sections.containsKey(sectionName)) {
        EncodableSection section = this.sections.get(sectionName);
        encodedSections.add(section.encode());
        sectionIds.add(section.getId());
      }
    }

    HeaderV1 header = new HeaderV1();
    header.setFieldValue("SectionIds", this.getSectionIds());
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
    List<Integer> sectionIds = (List<Integer>)header.getFieldValue("SectionIds");
    for (int i = 0; i < sectionIds.size(); i++) {
      if (sectionIds.get(i).equals(TcfEuV2.ID)) {
        TcfEuV2 section = new TcfEuV2(encodedSections[i + 1]);
        this.sections.put(TcfEuV2.NAME, section);
      } else if (sectionIds.get(i).equals(UspV1.ID)) {
        UspV1 section = new UspV1(encodedSections[i + 1]);
        this.sections.put(UspV1.NAME, section);
      }
    }
  }

  public String encodeSection(String sectionName) throws EncodingException {
    if (this.sections.containsKey(sectionName)) {
      return this.sections.get(sectionName).encode();
    } else {
      return null;
    }
  }

  public void decodeSection(String sectionName, String encodedString) throws DecodingException {
    EncodableSection section = null;
    if (!this.sections.containsKey(sectionName)) {
      if (sectionName.equals(TcfEuV2.NAME)) {
        section = new TcfEuV2();
        this.sections.put(TcfEuV2.NAME, section);
      } else if (sectionName.equals(UspV1.NAME)) {
        section = new UspV1();
        this.sections.put(UspV1.NAME, section);
      }
    } else {
      section = this.sections.get(sectionName);
    }

    if (section != null) {
      section.decode(encodedString);
    }
  }
}
