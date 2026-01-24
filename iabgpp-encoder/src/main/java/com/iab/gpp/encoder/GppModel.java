package com.iab.gpp.encoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PrimitiveIterator;
import com.iab.gpp.encoder.datatype.encoder.IntegerSet;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.field.HeaderV1Field;
import com.iab.gpp.encoder.section.AbstractEncodable;
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

public class GppModel extends AbstractEncodable {
  private final Map<String, EncodableSection<?>> sections = new HashMap<>();
  private final HeaderV1 header;

  public GppModel() {
    this.header = new HeaderV1();
  }

  public GppModel(String encodedString) {
    this();
    decode(encodedString);
  }

  public void setFieldValue(int sectionId, FieldKey fieldName, Object value) {
    setFieldValue(Sections.SECTION_ID_NAME_MAP.get(sectionId), fieldName, value);
  }

  private EncodableSection<?> getOrCreateSection(String sectionName) {
    EncodableSection<?> section = this.sections.get(sectionName);
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
        this.header.getSectionsIds().add(section.getId());
      }
    }
    return section;
  }

  public void setFieldValue(String sectionName, FieldKey fieldName, Object value) {
    ensureDecode();
    EncodableSection<?> section = getOrCreateSection(sectionName);
    if (section != null) {
      section.setFieldValue(fieldName, value);
    } else {
      throw new InvalidFieldException(sectionName + "." + fieldName + " not found");
    }
  }

  public Object getFieldValue(int sectionId, FieldKey fieldName) {
    return getFieldValue(Sections.SECTION_ID_NAME_MAP.get(sectionId), fieldName);
  }

  public Object getFieldValue(String sectionName, FieldKey fieldName) {
    ensureDecode();
    EncodableSection<?> field = this.sections.get(sectionName);
    if (field != null) {
      return field.getFieldValue(fieldName);
    } else {
      return null;
    }
  }

  public boolean hasField(int sectionId, FieldKey fieldName) {
    return hasField(Sections.SECTION_ID_NAME_MAP.get(sectionId), fieldName);
  }

  public boolean hasField(String sectionName, FieldKey fieldName) {
    ensureDecode();
    EncodableSection<?> field = this.sections.get(sectionName);
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
    ensureDecode();
    return this.sections.containsKey(sectionName);
  }

  public HeaderV1 getHeader() {
    ensureDecode();
    try {
      header.setFieldValue(HeaderV1Field.SECTION_IDS, this.getSectionIds());
    } catch (InvalidFieldException e) {

    }
    return header;
  }

  public EncodableSection<?> getSection(int sectionId) {
    return getSection(Sections.SECTION_ID_NAME_MAP.get(sectionId));
  }

  public EncodableSection<?> getSection(String sectionName) {
    ensureDecode();
    return this.sections.get(sectionName);
  }

  public void deleteSection(int sectionId) {
    deleteSection(Sections.SECTION_ID_NAME_MAP.get(sectionId));
  }

  public void deleteSection(String sectionName) {
    EncodableSection<?> removed = this.sections.remove(sectionName);
    if (removed != null) {
      this.header.getSectionsIds().remove(removed.getId());
    }
  }

  public void clear() {
    if (!this.sections.isEmpty()) {
      this.sections.clear();
      this.header.getSectionsIds().clear();
    }
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
    ensureDecode();
    int length = Sections.SECTION_ORDER.size();
    List<Integer> sectionIds = new ArrayList<>(length);
    for (int i = 0; i < length; i++) {
      String sectionName = Sections.SECTION_ORDER.get(i);
      EncodableSection<?> section = this.sections.get(sectionName);
      if (section != null) {
        sectionIds.add(section.getId());
      }
    }
    return sectionIds;
  }

  @Override
  protected CharSequence doEncode() {
    int length = Sections.SECTION_ORDER.size();
    List<CharSequence> encodedSections = new ArrayList<>(length);
    encodedSections.add(header.encodeCharSequence());
    List<Integer> sectionIds = new ArrayList<>(length);
    for (int i = 0; i < length; i++) {
      String sectionName = Sections.SECTION_ORDER.get(i);
      EncodableSection<?> section = sections.get(sectionName);
      if (section != null) {
        encodedSections.add(section.encodeCharSequence());
        sectionIds.add(section.getId());
      }
    }
    return SlicedCharSequence.join('~', encodedSections);
  }

  @Override
  protected void doDecode(CharSequence str) {
    if (str == null || str.isEmpty() || (str.charAt(0) == 'D' && str.charAt(1) == 'B')) {
      sections.clear();
      header.getSectionsIds().clear();

      if(str != null && !str.isEmpty()) {
        List<CharSequence> encodedSections = SlicedCharSequence.split(str, '~');
        header.decode(encodedSections.get(0));

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
    } else if (str.charAt(0) == 'C') {
      // old tcfeu only string
      TcfEuV2 section = new TcfEuV2(str);
      sections.put(TcfEuV2.NAME, section);
      header.getSectionsIds().add(section.getId());
    } else {
      throw new DecodingException("Unable to decode '" + str + "'");
    }
  }

  public String encodeSection(int sectionId) {
    return encodeSection(Sections.SECTION_ID_NAME_MAP.get(sectionId));
  }

  public String encodeSection(String sectionName) {
    ensureDecode();
    EncodableSection<?> section = this.sections.get(sectionName);
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
    ensureDecode();
    EncodableSection<?> section = getOrCreateSection(sectionName);
    if (section != null) {
      section.decode(encodedString);
    }
  }

  public String toString() {
    ensureDecode();
    List<Integer> sectionIds = getSectionIds();
    List<String> pieces = new ArrayList<>(sectionIds.size());
    for (Integer sectionId : sectionIds) {
      pieces.add(getSection(sectionId).toString());
    }
    return pieces.toString();
  }

  @Override
  public boolean isDirty() {
    if (header.isDirty()) {
      return true;
    }
    int length = Sections.SECTION_ORDER.size();
    for (int i = 0; i < length; i++) {
      String sectionName = Sections.SECTION_ORDER.get(i);
      EncodableSection<?> section = this.sections.get(sectionName);
      if (section != null && section.isDirty()) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void setDirty(boolean dirty) {
    header.setDirty(dirty);
    int length = Sections.SECTION_ORDER.size();
    for (int i = 0; i < length; i++) {
      String sectionName = Sections.SECTION_ORDER.get(i);
      EncodableSection<?> section = this.sections.get(sectionName);
      if (section != null) {
        section.setDirty(true);
      }
    }
  }

}
