
package raemae.bibtexapp.services;

import raemae.bibtexapp.domain.Reference;


public class ReferenceReader {
    
    public static String toReadable(Reference r) {
        StringBuilder sb = new StringBuilder();
        sb.append("Citation key: " + r.getCitationKey() + "\n");
        sb.append("Reference type: " + capitalize(r.getType()) + "\n");
        for (String field : r.getRequiredFields()) {
            sb.append(capitalize(field) + ": " + r.getField(field) + "\n");
        }
        for (String field : r.getOptionalFields()) {
            String value = r.getField(field);
            if (value != null) {
                if (!value.isEmpty()) {
                    sb.append(capitalize(field) + ": " + value + "\n");
                }
            }
        }
        
        return sb.toString();
    }
    
    public static String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

}
