package raemae.bibtexapp.services;

import java.util.UUID;
import raemae.bibtexapp.domain.Article;
import raemae.bibtexapp.domain.Book;
import raemae.bibtexapp.domain.InProceedings;
import raemae.bibtexapp.domain.Reference;
import raemae.bibtexapp.ui.IO;
import raemae.bibtexapp.ui.TextUIFunction;

public class AddReference extends TextUIFunction {

    private IO io;
    private ReferenceStorage references;

    public AddReference(IO io, ReferenceStorage references) {
        super(1);
        this.io = io;
        this.references = references;
    }

    @Override
    public void execute() {
        io.print("");
        io.print("Which type of reference do you wish to add?");
        io.print("1 - book");

        io.print("2 - article");

        io.print("3 - inproceedings");
        String cmd = io.readLine("> ");
        int command;
        try {
            command = Integer.parseInt(cmd);
        } catch (Exception e) {
            io.print("Invalid command");
            return;
        }
        Reference r = null;

        switch (command) {
            case 1:
                r = new Book();
                break;
            case 2:
                r = new Article();
                break;
            case 3:
                r = new InProceedings();
                break;
            default:
                io.print("Invalid command");
                return;
        }

        if (r != null) {
            r = createReference(r);
            if (r != null) {
                references.addReference(r);
            }
        }

    }

    @Override
    public String getMenuName() {
        return "add";
    }

    @Override
    public String getMenuDescription() {
        return "Add a reference";
    }

    private Reference createReference(Reference r) {


        ReferenceEditor referenceEditor = new ReferenceEditor(io);
        
        io.print("Please enter the following required fields:");
        if (!referenceEditor.setRequiredFields(r)) {            
             io.print("This field must be at least 4 characters long.");
            return null;
        }
        referenceEditor.setUniqueCitationKey(r, references);
        
        io.print("");
        io.print("The following fields are optional and can be left empty if they are not included in the reference:");
        referenceEditor.setOptionalFields(r);

        io.print("Reference added.");
        return r;
    }
    

}
