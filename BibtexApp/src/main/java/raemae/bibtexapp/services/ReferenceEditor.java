package raemae.bibtexapp.services;

import java.util.HashMap;
import java.util.UUID;
import raemae.bibtexapp.domain.Reference;
import raemae.bibtexapp.ui.IO;

public class ReferenceEditor {

    public static HashMap<String, String> queryFields(String[] fields, IO io, boolean required, int minLength) {
        HashMap<String, String> values = new HashMap<String, String>();
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i];
            String field = io.readLine(fieldName + ": ");

            if (field.isEmpty()) {
                if (!required) {
                    continue;
                } else {
                    return null;
                }
            }

            if (field.length() < minLength) {
                return null;
            }

            values.put(fieldName, field);
        }
        return values;
    }

    public static void setFields(Reference r, HashMap<String, String> values) {

        for (String s : values.keySet()) {
            r.addField(s, values.get(s));
        }
    }

    public static void setUniqueCitationKey(Reference r, ReferenceStorage references) {
        r.generateCitationKeyWithSuffix("");
        String citationKey = r.getCitationKey();
        String suffix = "";
        Boolean unique = true;
        for (Reference ref : references.getReferences()) {
            if (ref.getCitationKey().equals(citationKey)) {
                unique = false;
            }
        }
        if (!unique) {
            suffix = UUID.randomUUID().toString().substring(0, 4);
            r.generateCitationKeyWithSuffix(suffix);
        }
    }

}
