
package raemae.bibtexapp.services.matchers;

import raemae.bibtexapp.domain.Reference;


public class AnyFieldContainsValue implements Matcher {
    
    private String value;
    
    public AnyFieldContainsValue(String value) {
        this.value = value.trim();
    }

    @Override
    public boolean matches(Reference r) {
        String[] optional = r.getOptionalFields();
        String[] required = r.getRequiredFields();
        
        for (int i = 0; i < required.length; i++) {
            if (r.getField(required[i]).contains(value)) {
                return true;
            }
        }
        
        for (int i = 0; i < optional.length; i++) {
            String current = r.getField(optional[i]);
            if (current != null) {
                if (current.contains(value)) {
                    return true;
                }
            }        
        }
        
        return false;
        
    }

}
