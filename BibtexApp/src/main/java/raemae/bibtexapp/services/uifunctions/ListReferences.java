package raemae.bibtexapp.services.uifunctions;

import java.util.ArrayList;
import java.util.List;
import raemae.bibtexapp.domain.Reference;
import raemae.bibtexapp.services.ReferenceFilter;
import raemae.bibtexapp.services.ReferenceReader;
import raemae.bibtexapp.services.ReferenceStorage;
import raemae.bibtexapp.services.matchers.AnyFieldContainsValue;
import raemae.bibtexapp.services.matchers.FieldContainsValue;
import raemae.bibtexapp.services.matchers.TypeIs;
import raemae.bibtexapp.ui.IO;

public class ListReferences extends TextUIFunction {

    private IO io;
    private ReferenceStorage references;
    private boolean bibTeXFormat;

    public ListReferences(IO io, ReferenceStorage references) {
        super(2);
        this.io = io;
        this.references = references;
        bibTeXFormat = true;
    }

    @Override
    public void execute() {
        
        
        outerloop:
        while (true) {
            String format = getFormat();

            io.print("");
            io.print("Select option:");
            io.print("1 - List all references");
            io.print("2 - List references of specific type");
            io.print("3 - List references where a specific field contains a value");
            io.print("4 - List references where any field contains a value");
            io.print("5 - Change the format in which items are listed (currently: " + format + ")");
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
                    listAll();
                    break outerloop;
                case 2:
                    listByType();
                    break outerloop;
                case 3:
                    listByFieldAndValue();
                    break outerloop;
                case 4:
                    listByValue();
                    break outerloop;
                case 5:
                    setFormat();
                    break;
                default:
                    break outerloop;
            }
        }
    }

    private String getFormat() {
        if (bibTeXFormat) {
            return "BibTeX";
        }
        return "Easy to read";
    }

    private void setFormat() {
        if (bibTeXFormat) {
            bibTeXFormat = false;
        } else {
            bibTeXFormat = true;
        }
    }

    private void listAll() {
        listContents(references.getReferences());
    }

    private void listByType() {
        String type = io.readLine("Type: ");
        List<Reference> matches = ReferenceFilter.findByMatcher(new TypeIs(type), references);
        listContents(matches);
    }

    private void listByFieldAndValue() {

        String field = io.readLine("Field: ");
        String value = io.readLine("Value: ");
        List<Reference> matches = ReferenceFilter.findByMatcher(new FieldContainsValue(field, value), references);
        listContents(matches);
    }

    private void listByValue() {
        String value = io.readLine("Value: ");
        List<Reference> matches = ReferenceFilter.findByMatcher(new AnyFieldContainsValue(value), references);
        listContents(matches);
    }

    private void listContents(List<Reference> list) {
        int i = 0;
        for (Reference r : list) {
            i++;
            io.print("Reference " + i + ":");
            if (bibTeXFormat) {
                io.print(r.toBibTex());
            }
            else {
                io.print(ReferenceReader.toReadable(r));
            }
            
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
