package com.iab.gpp.encoder.segment;

import java.util.Arrays;
import java.util.List;
import com.iab.gpp.encoder.base64.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.bitstring.BitStringEncoder;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.EncodableBitStringFields;
import com.iab.gpp.encoder.field.UsCoV1Field;
import com.iab.gpp.encoder.field.UsCtV1Field;
import com.iab.gpp.encoder.section.UsCtV1;

public class UsCtV1CoreSegment extends AbstractLazilyEncodableSegment<EncodableBitStringFields> {

  private AbstractBase64UrlEncoder base64UrlEncoder = CompressedBase64UrlEncoder.getInstance();
  private BitStringEncoder bitStringEncoder = BitStringEncoder.getInstance();

  public UsCtV1CoreSegment() {
    super();
  }

  public UsCtV1CoreSegment(String encodedString) {
    super();
    this.decode(encodedString);
  }

  @Override
  public List<String> getFieldNames() {
    return UsCtV1Field.USCTV1_CORE_SEGMENT_FIELD_NAMES;
  }

  @Override
  protected EncodableBitStringFields initializeFields() {
    EncodableBitStringFields fields = new EncodableBitStringFields();
    fields.put(UsCtV1Field.VERSION, new EncodableFixedInteger(6, UsCtV1.VERSION));
    fields.put(UsCtV1Field.SHARING_NOTICE, new EncodableFixedInteger(2, 0, (v -> v >= 0 && v <= 2)));
    fields.put(UsCtV1Field.SALE_OPT_OUT_NOTICE, new EncodableFixedInteger(2, 0, (v -> v >= 0 && v <= 2)));
    fields.put(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
        new EncodableFixedInteger(2, 0, (v -> v >= 0 && v <= 2)));
    fields.put(UsCtV1Field.SALE_OPT_OUT, new EncodableFixedInteger(2, 0, (v -> v >= 0 && v <= 2)));
    fields.put(UsCtV1Field.TARGETED_ADVERTISING_OPT_OUT, new EncodableFixedInteger(2, 0, (v -> v >= 0 && v <= 2)));
    fields.put(UsCtV1Field.SENSITIVE_DATA_PROCESSING,
        new EncodableFixedIntegerList(2, Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0), v -> {
          for(Integer i : v) {
            if(i < 0 || i > 2) {
              return false;
            }
          }
          return true;
        }));
    fields.put(UsCtV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
        new EncodableFixedIntegerList(2, Arrays.asList(0, 0, 0), v -> {
          for(Integer i : v) {
            if(i < 0 || i > 2) {
              return false;
            }
          }
          return true;
        }));
    fields.put(UsCtV1Field.MSPA_COVERED_TRANSACTION, new EncodableFixedInteger(2, 1, (v -> v >= 1 && v <= 2)));
    fields.put(UsCtV1Field.MSPA_OPT_OUT_OPTION_MODE, new EncodableFixedInteger(2, 0, (v -> v >= 0 && v <= 2)));
    fields.put(UsCtV1Field.MSPA_SERVICE_PROVIDER_MODE, new EncodableFixedInteger(2, 0, (v -> v >= 0 && v <= 2)));
    return fields;
  }

  @Override
  protected String encodeSegment(EncodableBitStringFields fields) {
    String bitString = bitStringEncoder.encode(fields, getFieldNames());
    String encodedString = base64UrlEncoder.encode(bitString);
    return encodedString;
  }

  @Override
  protected void decodeSegment(String encodedString, EncodableBitStringFields fields) {
    if (encodedString == null || encodedString.isEmpty()) {
      this.fields.reset(fields);
    }
    String bitString = base64UrlEncoder.decode(encodedString);
    bitStringEncoder.decode(bitString, getFieldNames(), fields);
  }
  
  @Override
  public void validate() {
    super.validate();
    Integer saleOptOutNotice = ((EncodableFixedInteger) fields.get(UsCoV1Field.SALE_OPT_OUT_NOTICE)).getValue();
    Integer saleOptOut = ((EncodableFixedInteger) fields.get(UsCoV1Field.SALE_OPT_OUT)).getValue();
    Integer targetedAdvertisingOptOutNotice = ((EncodableFixedInteger) fields.get(UsCoV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE)).getValue();
    Integer targetedAdvertisingOptOut = ((EncodableFixedInteger) fields.get(UsCoV1Field.TARGETED_ADVERTISING_OPT_OUT)).getValue();
    Integer mspaServiceProviderMode = ((EncodableFixedInteger) fields.get(UsCoV1Field.MSPA_SERVICE_PROVIDER_MODE)).getValue();
    Integer mspaOptOutOptionMode = ((EncodableFixedInteger) fields.get(UsCoV1Field.MSPA_OPT_OUT_OPTION_MODE)).getValue();
    
    if (saleOptOutNotice == 0) {
      if (saleOptOut != 0) {
        throw new ValidationException("Invalid usct sale notice / opt out combination: {"
            + saleOptOutNotice + " / " + saleOptOut + "}");
      }
    } else if (saleOptOutNotice == 1) {
      if (saleOptOut != 1 && saleOptOut != 2) {
        throw new ValidationException("Invalid usct sale notice / opt out combination: {"
            + saleOptOutNotice + " / " + saleOptOut + "}");
      }
    } else if (saleOptOutNotice == 2) {
      if (saleOptOut != 1) {
        throw new ValidationException("Invalid usct sale notice / opt out combination: {"
            + saleOptOutNotice + " / " + saleOptOut + "}");
      }
    }
    
    if (targetedAdvertisingOptOutNotice == 0) {
      if (targetedAdvertisingOptOut != 0) {
        throw new ValidationException("Invalid usct targeted advertising notice / opt out combination: {"
            + targetedAdvertisingOptOutNotice + " / " + targetedAdvertisingOptOut + "}");
      }
    } else if (targetedAdvertisingOptOutNotice == 1) {
      if (saleOptOut != 1 && saleOptOut != 2) {
        throw new ValidationException("Invalid usct targeted advertising notice / opt out combination: {"
            + targetedAdvertisingOptOutNotice + " / " + targetedAdvertisingOptOut + "}");
      }
    } else if (targetedAdvertisingOptOutNotice == 2) {
      if (saleOptOut != 1) {
        throw new ValidationException("Invalid usct targeted advertising notice / opt out combination: {"
            + targetedAdvertisingOptOutNotice + " / " + targetedAdvertisingOptOut + "}");
      }
    }
    
    if (mspaServiceProviderMode == 0) {
      if(saleOptOutNotice != 0) {
        throw new ValidationException("Invalid usct mspa service provider mode / sale opt out notice combination: {"
            + mspaServiceProviderMode + " / " + saleOptOutNotice + "}");
      }
    } else if (mspaServiceProviderMode == 1) {
      if (mspaOptOutOptionMode != 2) {
        throw new ValidationException("Invalid usct mspa service provider / opt out option modes combination: {"
            + mspaServiceProviderMode + " / " + mspaServiceProviderMode + "}");
      }
      
      if(saleOptOutNotice != 0) {
        throw new ValidationException("Invalid usct mspa service provider mode / sale opt out notice combination: {"
            + mspaServiceProviderMode + " / " + saleOptOutNotice + "}");
      }
    } else if (mspaServiceProviderMode == 2) {
      if (mspaOptOutOptionMode != 1) {
        throw new ValidationException("Invalid usct mspa service provider / opt out option modes combination: {"
            + mspaServiceProviderMode + " / " + mspaOptOutOptionMode + "}");
      }
    }
  }
  
  
}
