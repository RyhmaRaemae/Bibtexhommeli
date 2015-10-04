/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raemae.bibtexapp.services;

/**
 *
 * @author Markku
 */


import raemae.bibtexapp.ui.IO;
import raemae.bibtexapp.ui.TextUIFunction;

public class LoadReferencesFromFile extends TextUIFunction {

    private IO io;
    private ReferenceStorage references;

    public LoadReferencesFromFile(IO io, ReferenceStorage references) {
        super(4);
        this.io = io;
        this.references = references;
    }

    @Override
    public void execute() {
        io.print("");
        io.print("Restoring references from file BibTeX.bib");
        boolean state=false;
        try {
            state=references.loadFromFile("BibTeX.bib");
        }
        catch(Exception e) {}
        
        if(state) {
            io.print("References have been loaded succesfully!");
        } else {
            io.print("An error occured while trying to load references!");
        }
        io.print("");
    }

    @Override
    public String getMenuName() {
        return "load";
    }

    @Override
    public String getMenuDescription() {
        return "Loads references from a file.";
    }
}
