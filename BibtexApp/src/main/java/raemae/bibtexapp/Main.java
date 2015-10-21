package raemae.bibtexapp;

import raemae.bibtexapp.services.uifunctions.TextUIFunction;
import raemae.bibtexapp.services.uifunctions.SaveReferencesToFile;
import raemae.bibtexapp.services.uifunctions.EditReference;
import raemae.bibtexapp.services.uifunctions.ListReferences;
import raemae.bibtexapp.services.uifunctions.ChangeTextColor;
import raemae.bibtexapp.services.uifunctions.AddReference;
import raemae.bibtexapp.services.*;
import java.util.ArrayList;
import java.util.List;
import raemae.bibtexapp.domain.Reference;
import raemae.bibtexapp.services.uifunctions.DeleteReference;
import raemae.bibtexapp.services.uifunctions.LoadReferencesFromFile;
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
