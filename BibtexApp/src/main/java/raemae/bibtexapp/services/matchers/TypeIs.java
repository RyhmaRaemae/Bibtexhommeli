
package raemae.bibtexapp.services.matchers;

import java.util.List;
import raemae.bibtexapp.domain.Reference;


public class TypeIs implements Matcher {
    
    private String type;
    
    public TypeIs(String type) {
        this.type = type.trim().toLowerCase();
    }

    @Override
    public boolean matches(Reference r) {
        if (r.getType().equals(type)) {
            return true;
        }
        return false;
    }

}
