
package raemae.bibtexapp.services.uifunctions;

import java.util.HashMap;
import java.util.List;
import raemae.bibtexapp.domain.Reference;
import raemae.bibtexapp.services.ReferenceEditor;
import raemae.bibtexapp.services.ReferenceFilter;
import raemae.bibtexapp.services.ReferenceStorage;
import raemae.bibtexapp.services.matchers.CitationKeyIs;
import raemae.bibtexapp.ui.IO;


public class EditReference extends TextUIFunction {
    
    private IO io;
    private ReferenceStorage references;

    public EditReference(IO io, ReferenceStorage references) {
        super(7);
        this.io = io;
        this.references = references;
    }

    @Override
    public void execute() {
        io.print("");
        io.print("Please enter the citation key of the reference you wish to edit");
        String citationKey = io.readLine("> ");
        Reference r = getReferenceByCitationKey(citationKey);
        
        if (r == null) {
            io.print("No reference matching citation key '" + citationKey + "' found.");
            return;
        }
        
        io.print("Please enter the new values for each field. If you do not wish to change the existing value, leave the input empty.");
        io.print("");
        editReference(r);
        
    }
    
    private Reference getReferenceByCitationKey(String citationKey) {
        List<Reference> r = ReferenceFilter.findByMatcher(new CitationKeyIs(citationKey), references);
        if (r != null) {
            return r.get(0);
        }
        return null;
        
    }
    
    private void editReference(Reference r) {
        HashMap<String, String> requiredValues = ReferenceEditor.queryFields(r.getRequiredFields(), io, false, 4);
        if (requiredValues == null) {
            io.print("Each required field must have a value at least 4 characters long.");
            return;
        }
        ReferenceEditor.setFields(r, requiredValues);
        HashMap<String, String> optionalValues = ReferenceEditor.queryFields(r.getOptionalFields(), io, false, 0);
        ReferenceEditor.setFields(r, optionalValues);
    }

    @Override
    public String getMenuName() {
        return "edit";
    }

    @Override
    public String getMenuDescription() {
        return "Edit a reference";
    }

}
