
package raemae.bibtexapp.services;

import java.util.ArrayList;
import java.util.List;
import raemae.bibtexapp.domain.Reference;
import raemae.bibtexapp.services.matchers.*;


public class ReferenceFilter {
    
    public static List<Reference> findByMatcher(Matcher m, ReferenceStorage references) {        
        List<Reference> matches = new ArrayList<Reference>();
        for (Reference r : references.getReferences()) {
            if (m.matches(r)) {
                matches.add(r);
            }
        }
        return matches;
    }

}
