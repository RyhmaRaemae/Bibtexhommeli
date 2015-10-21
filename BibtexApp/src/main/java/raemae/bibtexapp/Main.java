package raemae.bibtexapp;

import raemae.bibtexapp.services.*;
import java.util.ArrayList;
import java.util.List;
import raemae.bibtexapp.domain.Reference;
import raemae.bibtexapp.services.DeleteReference;
import raemae.bibtexapp.services.LoadReferencesFromFile;
import raemae.bibtexapp.ui.*;


public class Main {

    public static void main(String[] args) {
        List<TextUIFunction> l = new ArrayList<TextUIFunction>();
        ReferenceStorage storage = new ReferenceStorage(new ArrayList<Reference>());
        ConsoleIO io = new ConsoleIO();
        l.add(new AddReference(io, storage));
        l.add(new ListReferences(io, storage));
        l.add(new SaveReferencesToFile(io, storage));
        l.add(new LoadReferencesFromFile(io, storage));
        l.add(new DeleteReference(io, storage));
        l.add(new EditReference(io, storage));
        l.add(new ChangeTextColor(io));
        TextUI ui = new TextUI(l, io);
        ui.run();        
    }
    
}
