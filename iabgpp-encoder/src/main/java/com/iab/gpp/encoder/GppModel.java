package com.iab.gpp.encoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.function.Supplier;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.section.AbstractEncodable;
import com.iab.gpp.encoder.section.EncodableSection;
import com.iab.gpp.encoder.section.HeaderV1;
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

  private static final HashMap<Integer, Supplier<EncodableSection<?>>> SECTION_ID_TO_CONSTRUCTOR = new HashMap<>();
  private static final HashMap<String, Integer> SECTION_NAME_TO_ID = new HashMap<>();
  
  static {

    List<Supplier<EncodableSection<?>>> constructors = new ArrayList<>();

    // register section constructors here
    constructors.add(TcfEuV2::new);
    constructors.add(TcfCaV1::new);
    constructors.add(UspV1::new);
    constructors.add(UsNat::new);
    constructors.add(UsCa::new);
    constructors.add(UsVa::new);
    constructors.add(UsCo::new);
    constructors.add(UsUt::new);
    constructors.add(UsCt::new);
    constructors.add(UsFl::new);
    constructors.add(UsMt::new);
    constructors.add(UsOr::new);
    constructors.add(UsTx::new);
    constructors.add(UsDe::new);
    constructors.add(UsIa::new);
    constructors.add(UsNe::new);
    constructors.add(UsNh::new);
    constructors.add(UsNj::new);
    constructors.add(UsTn::new);

    for (Supplier<EncodableSection<?>> constructor : constructors) {
      EncodableSection<?> prototype = constructor.get();
      Integer id = prototype.getId();
      SECTION_ID_TO_CONSTRUCTOR.put(id, constructor);
      SECTION_NAME_TO_ID.put(prototype.getName(), id);
    }
  }

  private final HashMap<Integer, EncodableSection<?>> sections = new HashMap<>();
  private final HeaderV1 header;

  public GppModel() {
    this.header = new HeaderV1();
  }

  public GppModel(String encodedString) {
    this();
    decode(encodedString);
  }

  public void setFieldValue(String sectionName, FieldKey fieldName, Object value) {
    setFieldValue(SECTION_NAME_TO_ID.get(sectionName), fieldName, value);
  }

  private EncodableSection<?> getOrCreateSection(Integer sectionId) {
    EncodableSection<?> section = this.sections.get(sectionId);
    if (section == null) {
      Supplier<EncodableSection<?>> constructor = SECTION_ID_TO_CONSTRUCTOR.get(sectionId);
      if (constructor != null) {
        section = constructor.get();
        this.sections.put(section.getId(), section);
        this.header.getSectionsIds().add(section.getId());
      }
    }
    return section;
  }

  public void setFieldValue(int sectionId, FieldKey fieldName, Object value) {
    ensureDecode();
    EncodableSection<?> section = getOrCreateSection(sectionId);
    if (section != null) {
      section.setFieldValue(fieldName, value);
    } else {
      throw new InvalidFieldException(sectionId + "." + fieldName + " not found");
    }
  }

  public Object getFieldValue(String sectionName, FieldKey fieldName) {
    return getFieldValue(SECTION_NAME_TO_ID.get(sectionName), fieldName);
  }

  public Object getFieldValue(int sectionId, FieldKey fieldName) {
    ensureDecode();
    EncodableSection<?> field = this.sections.get(sectionId);
    if (field != null) {
      return field.getFieldValue(fieldName);
    } else {
      return null;
    }
  }

  public boolean hasField(String sectionName, FieldKey fieldName) {
    return hasField(SECTION_NAME_TO_ID.get(sectionName), fieldName);
  }

  public boolean hasField(int sectionId, FieldKey fieldName) {
    ensureDecode();
    EncodableSection<?> field = this.sections.get(sectionId);
    if (field != null) {
      return field.hasField(fieldName);
    } else {
      return false;
    }
  }

  public boolean hasSection(String sectionName) {
    return hasSection(SECTION_NAME_TO_ID.get(sectionName));
  }

  public boolean hasSection(int sectionId) {
    ensureDecode();
    return this.sections.containsKey(sectionId);
  }

  public HeaderV1 getHeader() {
    ensureDecode();
    return header;
  }

  public EncodableSection<?> getSection(int sectionId) {
    ensureDecode();
    return this.sections.get(sectionId);
  }

  public EncodableSection<?> getSection(String sectionName) {
    return getSection(SECTION_NAME_TO_ID.get(sectionName));
  }

  public void deleteSection(String sectionName) {
    deleteSection(SECTION_NAME_TO_ID.get(sectionName));
  }

  public void deleteSection(int sectionId) {
    EncodableSection<?> removed = this.sections.remove(sectionId);
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
    return new ArrayList<>(header.getSectionsIds());
  }

  @Override
  protected CharSequence doEncode() {
    List<CharSequence> encodedSections = new ArrayList<>();
    encodedSections.add(header.encodeCharSequence());
    for (Integer sectionId: header.getSectionsIds()) {
      EncodableSection<?> section = sections.get(sectionId);
      encodedSections.add(section.encodeCharSequence());
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
          CharSequence encodedSection = encodedSections.get(i++);
          int sectionId = it.nextInt();
          EncodableSection<?> section = getOrCreateSection(sectionId);
          if (section != null) {
            section.decode(encodedSection);
          } else {
            // we do not support encoding this section
            header.getSectionsIds().removeInt(sectionId);
          }
        }
      }
    } else if (str.charAt(0) == 'C') {
      // old tcfeu only string
      EncodableSection<?> section = getOrCreateSection(TcfEuV2.ID);
      section.decode(str);
      header.getSectionsIds().add(section.getId());
    } else {
      throw new DecodingException("Unable to decode '" + str + "'");
    }
  }

  public String encodeSection(String sectionName) {
    return encodeSection(SECTION_NAME_TO_ID.get(sectionName));
  }

  public String encodeSection(int sectionId) {
    ensureDecode();
    EncodableSection<?> section = this.sections.get(sectionId);
    if (section != null) {
      return section.encode();
    } else {
      return null;
    }
  }

  public void decodeSection(String sectionName, String encodedString) {
    decodeSection(SECTION_NAME_TO_ID.get(sectionName), encodedString);
  }

  public void decodeSection(int sectionId, String encodedString) {
    ensureDecode();
    EncodableSection<?> section = getOrCreateSection(sectionId);
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
    for (Integer sectionId: header.getSectionsIds()) {
      EncodableSection<?> section = sections.get(sectionId);
      if (section != null && section.isDirty()) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void setDirty(boolean dirty) {
    header.setDirty(dirty);
    for (Integer sectionId: header.getSectionsIds()) {
      EncodableSection<?> section = sections.get(sectionId);
      if (section != null) {
        section.setDirty(true);
      }
    }
  }

}
