
package raemae.bibtexapp.services.matchers;

import raemae.bibtexapp.domain.Reference;


public class CitationKeyIs implements Matcher {
    
    private String citationKey;
    
    public CitationKeyIs(String key) {
        citationKey = key.toLowerCase().trim();
    }

    @Override
    public boolean matches(Reference r) {
        if (r.getCitationKey().equals(citationKey)) {
            return true;
        }
        return false;
    }

}
