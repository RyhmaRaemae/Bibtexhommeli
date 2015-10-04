/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raemae.bibtexapp.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import raemae.bibtexapp.domain.*;

/**
 *
 * @author jacintor
 */
public class SaveReferencesToFileTest {

    private ReferenceStorage references = new ReferenceStorage(new ArrayList<Reference>());

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
    }

    @Test
    public void fileIsCreatedSuccessfully() {
        references.saveToFile(convertedPath);

        File file = new File(convertedPath);
        assertTrue(file.exists());
    }
    
    @Test
    public void emptyPathFails() {
        assertFalse(references.saveToFile(""));
    }

    @Test
    public void fileIsEmptyWhenThereAreNoReferences() throws FileNotFoundException, IOException {
        references.saveToFile(convertedPath);

        File file = new File(convertedPath);
        assertTrue(readFile(file).isEmpty());
    }

    @Test
    public void fileContainsOneReferenceSavedCorrectly() throws FileNotFoundException, IOException {
        Book book = new Book();
        book.addField("author", "Vihavainen, Arto");
        book.addField("year", "2015");
        book.addField("title", "Ohjelmistotuotanto");
        
        references.addReference(book);
        
        references.saveToFile(convertedPath);
        
        File file = new File(convertedPath);
        assertTrue(readFile(file).equals(book.toBibTex() + "\n"));
    }
    
    @Test
    public void fileContainsSeveralReferencesSavedCorrectly() throws FileNotFoundException, IOException {
        Book book1 = new Book();
        book1.addField("author", "Vihavainen, Arto");
        book1.addField("year", "2015");
        book1.addField("title", "Ohjelmistotuotanto");
        
        Book book2 = new Book();
        book2.addField("author", "Vihavainen, Arto");
        book2.addField("year", "2014");
        book2.addField("title", "Web-palvelinohjelmointi");
        
        Book book3 = new Book();
        book3.addField("author", "Vihavainen, Arto");
        book3.addField("year", "2013");
        book3.addField("title", "Selainohjelmointi");
        
        references.addReference(book1);
        references.addReference(book2);
        references.addReference(book3);
        
        references.saveToFile(convertedPath);
        
        File file = new File(convertedPath);
        assertTrue(readFile(file).equals(book1.toBibTex() + "\n" + 
                                         book2.toBibTex() + "\n" +
                                         book3.toBibTex() + "\n"));
    }

    private String readFile(File file) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        reader.close();

        return sb.toString();
    }
}
