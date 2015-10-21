package raemae.bibtexapp.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import raemae.bibtexapp.domain.*;

public class LoadReferencesFromFileTest {

    private ReferenceStorage references = new ReferenceStorage(new ArrayList<Reference>());
    private ReferenceStorage references2 = new ReferenceStorage(new ArrayList<Reference>());

    private String convertedPath = "BibTeXTest.bib";

    @Before
    public void setUp() {
        File file = new File(convertedPath);
        if (file.exists()) {
            try {
                Files.delete(file.toPath());
            } catch (IOException x) {
                System.err.println(x);
            }
        }
    }
    
    @After
    public void tearDown() {
        File file = new File(convertedPath);
        if (file.exists()) {
            try {
                Files.delete(file.toPath());
            } catch (IOException x) {
                System.err.println(x);
            }
        }
    }

    @Test
    public void fileIsLoadedSuccessfully() throws FileNotFoundException, IOException { 
        Book book = new Book();
        book.addField("author", "Vihavainen, Arto");
        book.addField("year", "2015");
        book.addField("title", "Ohjelmistotuotanto");        
        references.addReference(book);
        references.saveToFile(convertedPath);
        
        Book book2 = new Book();
        book.addField("author", "bbbb");
        book.addField("year", "bbbb");
        book.addField("title", "bbbb");        
        references.addReference(book);
        references2.loadFromFile(convertedPath);
        
        File file = new File(convertedPath);
        assertFalse(references2.getReferences() == references);

    }

}