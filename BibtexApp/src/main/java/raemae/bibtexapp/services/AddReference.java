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
        int command = Integer.parseInt(cmd);
        Reference r = null;
        if (command >= 1 && command <= 3) {
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
            }

            if (r != null) {
                r = createReference(r);
                if (r != null) {
                    references.addReference(r);
                }
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

        String[] requiredFields = r.getRequiredFields();
        String[] optionalFields = r.getOptionalFields();

        if (!setRequiredFields(r, requiredFields)) {
            return null;
        }
        setUniqueCitationKey(r);
        setOptionalFields(r, optionalFields);

        io.print("Reference added.");
        return r;
    }

    private boolean setRequiredFields(Reference r, String[] requiredFields) {
        io.print("Please enter the following required fields:");
        for (int i = 0; i < requiredFields.length; i++) {
            String fieldName = requiredFields[i];
            String field = io.readLine(fieldName + ": ");
            if (field.length() < 4) {
                io.print("This field must be at least 4 characters long.");
                return false;
            }
            r.addField(fieldName, field);
        }
        return true;
    }

    private void setOptionalFields(Reference r, String[] optionalFields) {
        io.print("");
        io.print("The following fields are optional and can be left empty if they are not included in the reference:");
        for (int i = 0; i < optionalFields.length; i++) {
            String fieldName = optionalFields[i];
            String field = io.readLine(fieldName + ": ");
            if (!field.isEmpty()) {
                r.addField(fieldName, field);
            }
        }
    }

    private void setUniqueCitationKey(Reference r) {
        r.setCitationKey("");
        String citationKey = r.getCitationKey();
        String suffix = "";
        Boolean unique = true;
        for (Reference ref : references.getReferences()) {
            if (ref.getCitationKey().equals(citationKey)) {
                unique = false;
            }
        }
        if (!unique) {
            suffix = UUID.randomUUID().toString().substring(0, 4);
            r.setCitationKey(suffix);
        }
    }

}