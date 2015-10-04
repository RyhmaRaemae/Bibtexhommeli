package raemae.bibtexapp.services;

import raemae.bibtexapp.ui.IO;
import raemae.bibtexapp.ui.TextUIFunction;

public class SaveReferencesToFile extends TextUIFunction {

    private IO io;
    private ReferenceStorage references;

    public SaveReferencesToFile(IO io, ReferenceStorage references) {
        super(3);
        this.io = io;
        this.references = references;
    }

    @Override
    public void execute() {
        io.print("");
        io.print("Saving references into file ~/BibTeX.bib");
        references.saveToFile("~/BibTeX.bib");
        io.print("File has been saved!");
        io.print("");
    }

    @Override
    public String getMenuName() {
        return "save";
    }

    @Override
    public String getMenuDescription() {
        return "Saves the references into a file in your home directory";
    }
}

