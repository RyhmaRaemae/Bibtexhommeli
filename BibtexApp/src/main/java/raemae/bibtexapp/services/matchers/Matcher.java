

package raemae.bibtexapp.services.matchers;

import java.util.List;
import raemae.bibtexapp.domain.Reference;


public interface Matcher {
    
    public boolean matches (Reference r);

}
