
package raemae.bibtexapp.services;

import raemae.bibtexapp.domain.Reference;
import raemae.bibtexapp.ui.IO;
import raemae.bibtexapp.ui.TextUIFunction;


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
        io.print("Reference edited.");
        
    }
    
    private Reference getReferenceByCitationKey(String citationKey) {
        ReferenceFilter rf = new ReferenceFilter();
        Reference r = rf.findByCitationKey(citationKey, references);
        
        return r;
        
    }
    
    private void editReference(Reference r) {
        ReferenceEditor re = new ReferenceEditor(io);
        re.setRequiredFields(r);
        re.setOptionalFields(r);
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
