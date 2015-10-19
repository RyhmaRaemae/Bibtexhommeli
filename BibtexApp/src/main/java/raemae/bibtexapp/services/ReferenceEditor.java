
package raemae.bibtexapp.services;

import java.util.UUID;
import raemae.bibtexapp.domain.Reference;
import raemae.bibtexapp.ui.IO;


public class ReferenceEditor {
    
    private IO io;
    
    public ReferenceEditor(IO io) {
        this.io = io;
    }
    
    
    public boolean setRequiredFields(Reference r) {
        String[] requiredFields = r.getRequiredFields();
        for (int i = 0; i < requiredFields.length; i++) {
            String fieldName = requiredFields[i];
            String field = io.readLine(fieldName + ": ");
            if (field.length() < 4) {
                if (r.getField(fieldName) != null) {
                    continue;
                }
                return false;
            }
            r.addField(fieldName, field);
        }
        return true;
    }

    public void setOptionalFields(Reference r) {
        String[] optionalFields = r.getOptionalFields();
        for (int i = 0; i < optionalFields.length; i++) {
            String fieldName = optionalFields[i];
            String field = io.readLine(fieldName + ": ");
            if (!field.isEmpty()) {
                r.addField(fieldName, field);
            }
        }
    }

    public void setUniqueCitationKey(Reference r, ReferenceStorage references) {
        r.setCitationKey("");
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
            r.setCitationKey(suffix);
        }
    }

}
