package raemae.bibtexapp;

import java.util.ArrayList;
import java.util.List;
import raemae.bibtexapp.domain.Reference;
import raemae.bibtexapp.ui.*;
import services.ReferenceStorage;

public class Main {

    public static void main(String[] args) {
        List<TextUIFunction> l = new ArrayList<TextUIFunction>();
        ReferenceStorage books = new ReferenceStorage(new ArrayList<Reference>());
        ConsoleIO io = new ConsoleIO();
        l.add(new AddReference(io, books));
        l.add(new ListReferences(io, books));
        TextUI ui = new TextUI(l, io);
        ui.run();
    }
    
}
