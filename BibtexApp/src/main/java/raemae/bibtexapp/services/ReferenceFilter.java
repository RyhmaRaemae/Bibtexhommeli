
package raemae.bibtexapp.services;

import java.util.ArrayList;
import java.util.List;
import raemae.bibtexapp.domain.Reference;
import raemae.bibtexapp.services.matchers.*;


public class ReferenceFilter {
    
    public Reference findByCitationKey(String citationKey, ReferenceStorage references) {
        for (Reference r : references.getReferences()) {
           if (r.getCitationKey().equals(citationKey)) {
               return r;
           }
        }
        return null;
    }
    
    
    public List<Reference> findByType(String type, ReferenceStorage references) {
        List<Reference> matches = findByMatcher(new TypeIs(type), references);
        return matches;
    }
    
    public List<Reference> findAll(ReferenceStorage references) {
       return findByMatcher(new All(), references);
    }
    
    public List<Reference> findByAnyFieldContains(String value, ReferenceStorage references) {
        return findByMatcher(new AnyFieldContainsValue(value), references);
    }
    
    public List<Reference> findbyFieldContains(String field, String value, ReferenceStorage references) {
        List<Reference> matches = findByMatcher(new FieldContainsValue(field, value), references);
        return matches;
    }
    
    private List<Reference> findByMatcher(Matcher m, ReferenceStorage references) {        
        List<Reference> matches = new ArrayList<Reference>();
        for (Reference r : references.getReferences()) {
            if (m.matches(r)) {
                matches.add(r);
            }
        }
        return matches;
    }

}
