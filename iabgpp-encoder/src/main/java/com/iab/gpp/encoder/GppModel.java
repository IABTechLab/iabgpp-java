package com.iab.gpp.encoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PrimitiveIterator;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.field.HeaderV1Field;
import com.iab.gpp.encoder.section.EncodableSection;
import com.iab.gpp.encoder.section.HeaderV1;
import com.iab.gpp.encoder.section.Sections;
import com.iab.gpp.encoder.section.SlicedCharSequence;
import com.iab.gpp.encoder.section.TcfCaV1;
import com.iab.gpp.encoder.section.TcfEuV2;
import com.iab.gpp.encoder.section.UsCa;
import com.iab.gpp.encoder.section.UsCo;
import com.iab.gpp.encoder.section.UsCt;
import com.iab.gpp.encoder.section.UsDe;
import com.iab.gpp.encoder.section.UsFl;
import com.iab.gpp.encoder.section.UsIa;
import com.iab.gpp.encoder.section.UsMt;
import com.iab.gpp.encoder.section.UsNat;
import com.iab.gpp.encoder.section.UsNe;
import com.iab.gpp.encoder.section.UsNh;
import com.iab.gpp.encoder.section.UsNj;
import com.iab.gpp.encoder.section.UsOr;
import com.iab.gpp.encoder.section.UsTn;
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
        case UsNat.NAME:
          section = new UsNat();
          break;
        case UsCa.NAME:
          section = new UsCa();
          break;
        case UsVa.NAME:
          section = new UsVa();
          break;
        case UsCo.NAME:
          section = new UsCo();
          break;
        case UsUt.NAME:
          section = new UsUt();
          break;
        case UsCt.NAME:
          section = new UsCt();
          break;
        case UsFl.NAME:
          section = new UsFl();
          break;
        case UsMt.NAME:
          section = new UsMt();
          break;
        case UsOr.NAME:
          section = new UsOr();
          break;
        case UsTx.NAME:
          section = new UsTx();
          break;
        case UsDe.NAME:
          section = new UsDe();
          break;
        case UsIa.NAME:
          section = new UsIa();
          break;
        case UsNe.NAME:
          section = new UsNe();
          break;
        case UsNh.NAME:
          section = new UsNh();
          break;
        case UsNj.NAME:
          section = new UsNj();
          break;
        case UsTn.NAME:
          section = new UsTn();
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

  public UsDe getUsDeSection() {
    return (UsDe) getSection(UsDe.NAME);
  }

  public UsIa getUsIaSection() {
    return (UsIa) getSection(UsIa.NAME);
  }

  public UsNe getUsNeSection() {
    return (UsNe) getSection(UsNe.NAME);
  }

  public UsNh getUsNhSection() {
    return (UsNh) getSection(UsNh.NAME);
  }

  public UsNj getUsNjSection() {
    return (UsNj) getSection(UsNj.NAME);
  }

  public UsTn getUsTnSection() {
    return (UsTn) getSection(UsTn.NAME);
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
    List<CharSequence> encodedSections = new ArrayList<>(length);
    List<Integer> sectionIds = new ArrayList<>(length);
    for (int i = 0; i < length; i++) {
      String sectionName = Sections.SECTION_ORDER.get(i);
      EncodableSection section = sections.get(sectionName);
      if (section != null) {
        encodedSections.add(section.encodeCharSequence());
        sectionIds.add(section.getId());
      }
    }

    HeaderV1 header = new HeaderV1();
    try {
      header.setFieldValue("SectionIds", getSectionIds());
    } catch (InvalidFieldException e) {
      throw new EncodingException(e);
    }
    encodedSections.add(0, header.encodeCharSequence());
    return SlicedCharSequence.join('~', encodedSections).toString();
  }

  protected Map<String, EncodableSection> decodeModel(String str) {
    if (str == null || str.isEmpty() || str.startsWith("D")) {
      Map<String, EncodableSection> sections = new HashMap<>();

      if(str != null && !str.isEmpty()) {
        List<CharSequence> encodedSections = SlicedCharSequence.split(str, '~');
        HeaderV1 header = new HeaderV1(encodedSections.get(0));
        sections.put(HeaderV1.NAME, header);

        PrimitiveIterator.OfInt it = header.getSectionsIds().iterator();
        int i = 1;
        while (it.hasNext()) {
          CharSequence section = encodedSections.get(i++);
          switch (it.nextInt()) {
            case TcfEuV2.ID:
              sections.put(TcfEuV2.NAME, new TcfEuV2(section));
              break;
            case TcfCaV1.ID:
              sections.put(TcfCaV1.NAME, new TcfCaV1(section));
              break;
            case UspV1.ID:
              sections.put(UspV1.NAME, new UspV1(section));
              break;
            case UsCa.ID:
              sections.put(UsCa.NAME, new UsCa(section));
              break;
            case UsNat.ID:
              sections.put(UsNat.NAME, new UsNat(section));
              break;
            case UsVa.ID:
              sections.put(UsVa.NAME, new UsVa(section));
              break;
            case UsCo.ID:
              sections.put(UsCo.NAME, new UsCo(section));
              break;
            case UsUt.ID:
              sections.put(UsUt.NAME, new UsUt(section));
              break;
            case UsCt.ID:
              sections.put(UsCt.NAME, new UsCt(section));
              break;
            case UsFl.ID:
              sections.put(UsFl.NAME, new UsFl(section));
              break;
            case UsMt.ID:
              sections.put(UsMt.NAME, new UsMt(section));
              break;
            case UsOr.ID:
              sections.put(UsOr.NAME, new UsOr(section));
              break;
            case UsTx.ID:
              sections.put(UsTx.NAME, new UsTx(section));
              break;
            case UsDe.ID:
              sections.put(UsDe.NAME, new UsDe(section));
              break;
            case UsIa.ID:
              sections.put(UsIa.NAME, new UsIa(section));
              break;
            case UsNe.ID:
              sections.put(UsNe.NAME, new UsNe(section));
              break;
            case UsNh.ID:
              sections.put(UsNh.NAME, new UsNh(section));
              break;
            case UsNj.ID:
              sections.put(UsNj.NAME, new UsNj(section));
              break;
            case UsTn.ID:
              sections.put(UsTn.NAME, new UsTn(section));
              break;
          }
        }
      }

      return sections;
    } else if (str.startsWith("C")) {
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
      this.dirty = true;
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

  public String toString() {
    List<Integer> sectionIds = getSectionIds();
    List<String> pieces = new ArrayList<>(sectionIds.size());
    for (Integer sectionId : sectionIds) {
      pieces.add(getSection(sectionId).toString());
    }
    return pieces.toString();
  }


}
