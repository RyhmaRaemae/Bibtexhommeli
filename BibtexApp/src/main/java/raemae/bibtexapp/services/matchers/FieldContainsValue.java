
package raemae.bibtexapp.services.matchers;

import raemae.bibtexapp.domain.Reference;


public class FieldContainsValue implements Matcher {
    
    private String field;
    private String value;
    
    public FieldContainsValue(String field, String value) {
        this.field = field.trim().toLowerCase();
        this.value = value.trim();
    }

    public boolean matches(Reference r) {
        if (r.getField(field) != null) {
            if (r.getField(field).equals(value)) {
                return true;
            }
        }
        return false;
    }



}
