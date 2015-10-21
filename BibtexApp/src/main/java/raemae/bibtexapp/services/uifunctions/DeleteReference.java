package raemae.bibtexapp.services.uifunctions;

import java.util.List;
import raemae.bibtexapp.domain.Reference;
import raemae.bibtexapp.services.ReferenceFilter;
import raemae.bibtexapp.services.ReferenceStorage;
import raemae.bibtexapp.ui.IO;

public class DeleteReference extends TextUIFunction {

    private IO io;
    private ReferenceStorage references;

    public DeleteReference(IO io, ReferenceStorage references) {
        super(5);
        this.io = io;
        this.references = references;
    }

    @Override
    public void execute() {
        io.print("");
        io.print("Please choose which references you wish to delete:");
        io.print("1 - Delete all references");
        io.print("2 - List all references and delete a specific one");
        io.print("3 - Delete a specific reference by citation key");
        io.print("4 - Delete all references of a specific type");
        io.print("5 - Delete all references where any field contains a specific value");
        io.print("6 - Delete all references where a specific field contains a specific value");
        io.print("");
        String cmd = io.readLine("> ");
        
        int command;
        try {
            command = Integer.parseInt(cmd);
        } catch (Exception e) {
            io.print("Invalid command");
            return;
        }
        
        switch (command) {
            case 1:
                deleteAll();
                break;
            case 2:
                listAllDeleteOne();
                break;
            case 3:
                deleteByCitationKey();
                break;
            case 4:
                deleteByType();
                break;
            case 5:
                deleteByValue();
                break;
            case 6:
                deleteByFieldAndValue();
                break;
            default:
                break;
        }

    }
    
    private void deleteAll() {
        references.getReferences().clear();
    }

    private void deleteByFieldAndValue() {
        String field = io.readLine("Field: ");
        String value = io.readLine("Value: ");
        List<Reference> matches = ReferenceFilter.findbyFieldContains(field, value, references);
        references.getReferences().removeAll(matches);
    }

    private void deleteByValue() {
        String value = io.readLine("Value: ");
        List<Reference> matches = ReferenceFilter.findByAnyFieldContains(value, references);
        references.getReferences().removeAll(matches);
    }

    private void deleteByType() {
        String type = io.readLine("Type: ");
        List<Reference> matches = ReferenceFilter.findByType(type, references);
        references.getReferences().removeAll(matches);
    }

    private void deleteByCitationKey() {
        String citationKey = io.readLine("Citation key: ");
        Reference r = ReferenceFilter.findByCitationKey(citationKey, references);
        if (r != null) {
            references.getReferences().remove(r);
        }
    }

    private void listAllDeleteOne() {
        listAll();
        String cmd = io.readLine("Enter the number of the reference to delete: ");
        int index;
        try {
            index = Integer.parseInt(cmd) - 1;
        } catch (Exception e) {
            io.print("Invalid command");
            return;
        }
        references.getReferences().remove(index);
    }

    private void listAll() {
        int i = 0;
        for (Reference r : references.getReferences()) {
            i++;
            io.print("");
            io.print("Reference " + i + ":");
            io.print(r.toBibTex());
        }
        io.print("");
    }

    @Override
    public String getMenuName() {
        return "delete";
    }

    @Override
    public String getMenuDescription() {
        return "Delete a reference";
    }

}
