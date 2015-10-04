package raemae.bibtexapp;

import raemae.bibtexapp.services.AddReference;
import raemae.bibtexapp.services.ReferenceStorage;
import raemae.bibtexapp.services.ListReferences;
import raemae.bibtexapp.services.SaveReferencesToFile;
import java.util.ArrayList;
import java.util.List;
import raemae.bibtexapp.domain.Reference;
import raemae.bibtexapp.ui.*;


public class Main {

    public static void main(String[] args) {
        List<TextUIFunction> l = new ArrayList<TextUIFunction>();
        ReferenceStorage storage = new ReferenceStorage(new ArrayList<Reference>());
        ConsoleIO io = new ConsoleIO();
        l.add(new AddReference(io, storage));
        l.add(new ListReferences(io, storage));
        l.add(new SaveReferencesToFile(io, storage));
        TextUI ui = new TextUI(l, io);
        ui.run();        
    }
    
}
