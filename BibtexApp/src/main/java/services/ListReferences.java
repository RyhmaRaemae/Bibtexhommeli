package services;

import raemae.bibtexapp.domain.Book;
import raemae.bibtexapp.domain.Reference;
import raemae.bibtexapp.ui.IO;
import raemae.bibtexapp.ui.TextUIFunction;
import services.ReferenceStorage;

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
        io.print("Found the following references:\n");
        for (Reference r : references.getReferences()) {
            io.print(r.toBibTex());
        }
    }

    @Override
    public String getMenuName() {
        return "list";
    }

    @Override
    public String getMenuDescription() {
        return "List all references";
    }

}
