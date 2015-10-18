package raemae.bibtexapp.services;

import java.util.List;
import raemae.bibtexapp.domain.Reference;
import raemae.bibtexapp.ui.IO;
import raemae.bibtexapp.ui.TextUIFunction;

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
        ReferenceFilter rf = new ReferenceFilter();
        switch (command) {
            case 1:
                references.getReferences().clear();
                break;
            case 2:
                listAll();
                cmd = io.readLine("Enter the number of the reference to delete: ");
                int index;
                try {
                    index = Integer.parseInt(cmd) - 1;
                } catch (Exception e) {
                    io.print("Invalid command");
                    return;
                }
                references.getReferences().remove(index);
                break;
            case 3:
                cmd = io.readLine("Citation key: ");
                Reference r = rf.findByCitationKey(cmd, references);
                if (r != null) {
                    references.getReferences().remove(r);
                }
                break;
            case 4:
                cmd = io.readLine("Type: ");
                List<Reference> matches = rf.findByType(cmd, references);
                references.getReferences().removeAll(matches);
                break;
            case 5:
                cmd = io.readLine("Value: ");
                matches = rf.findByAnyFieldContains(cmd, references);
                references.getReferences().removeAll(matches);
                break;
            case 6:
                String field = io.readLine("Field: ");
                String value = io.readLine("Value: ");
                matches = rf.findbyFieldContains(field, value, references);
                references.getReferences().removeAll(matches);
                break;
            default:
                break;
        }

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
