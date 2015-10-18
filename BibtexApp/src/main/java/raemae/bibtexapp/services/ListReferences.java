package raemae.bibtexapp.services;

import java.util.ArrayList;
import java.util.List;
import raemae.bibtexapp.domain.Reference;
import raemae.bibtexapp.ui.IO;
import raemae.bibtexapp.ui.TextUIFunction;

public class ListReferences extends TextUIFunction {

    private IO io;
    private ReferenceStorage references;

    public ListReferences(IO io, ReferenceStorage references) {
        super(2);
        this.io = io;
        this.references = references;
    }

    @Override
    public void execute() {
        io.print("");
        io.print("How would you like to list references?");
        io.print("1 - List all references");
        io.print("2 - List references of specific type");
        io.print("3 - List references where a specific field contains a value");
        io.print("4 - List references where any field contains a value");
        io.print("");
        
        String cmd = io.readLine("> ");
        int command;
        try {
            command = Integer.parseInt(cmd);
        } catch(Exception e) {
            io.print("Invalid command");
            return;
        }
        if (command < 1 || command > 4) {
            io.print("Invalid command");
        }
        ReferenceFilter rf = new ReferenceFilter();
        io.print("Found the following references:\n");
        switch(command) {
            case 1:
                listAsBibtex(references.getReferences());
                break;
            case 2:
                String type = io.readLine("Type: ");
                List<Reference> matches = rf.findByType(type, references);
                listAsBibtex(matches);
                break;
            case 3: 
                String field = io.readLine("Field: ");
                String value = io.readLine("Value: ");
                matches = rf.findbyFieldContains(field, value, references);
                listAsBibtex(matches);
                break;
            case 4:
                value = io.readLine("Value: ");
                matches = rf.findByAnyFieldContains(value, references);
                listAsBibtex(matches);
                break;
            default:
                break;            
                
        }
        
        
    }
    
    private void listAsBibtex(List<Reference> list) {
        for (Reference r : list) {
            io.print(r.toBibTex());
            io.print("");
        }
    }
    
    
    @Override
    public String getMenuName() {
        return "list";
    }

    @Override
    public String getMenuDescription() {
        return "List references currently stored within the system";
    }

}
