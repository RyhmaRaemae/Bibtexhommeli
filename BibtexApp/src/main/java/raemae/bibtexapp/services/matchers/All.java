
package raemae.bibtexapp.services.matchers;

import java.util.List;
import raemae.bibtexapp.domain.Reference;


public class All implements Matcher {

    @Override
    public boolean matches(Reference r) {
        return true;
    }

}
