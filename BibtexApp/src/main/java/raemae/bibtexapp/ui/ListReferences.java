package raemae.bibtexapp.ui;

import raemae.bibtexapp.domain.Book;
import raemae.bibtexapp.domain.Reference;
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
        io.print("Found the following book references:\n");
        for (Reference b : references.getBooks()) {
            io.print(b.toBibTex());
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
