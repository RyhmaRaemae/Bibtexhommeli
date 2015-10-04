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
    private String rawPath = "~/BibTeXTest.bib";

    private String convertedPath = rawPath.replaceFirst("^~", System.getProperty("user.home"));

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
        references.saveToFile(rawPath);

        File file = new File(convertedPath);
        assertTrue(file.exists());
    }

    @Test
    public void fileIsEmptyWhenThereAreNoReferences() throws FileNotFoundException, IOException {
        references.saveToFile(rawPath);

        File file = new File(convertedPath);
        assertTrue(readFile(file).isEmpty());

        /*
         Book book = new Book();

         book.addField("author", "Vihavainen, Arto");
         book.addField("year", "2015");
         book.addField("title", "Ohjelmistotuotanto");
         System.out.println(book.toBibTex());
         assertEquals(book.getField("author"), "Vihavainen, Arto");
         assertEquals(book.getField("year"), "2015");
         assertEquals(book.getField("title"), "Ohjelmistotuotanto");
         */
    }

    @Test
    public void fileWithOneReference() throws FileNotFoundException, IOException {
        Book book = new Book();
        book.addField("author", "Vihavainen, Arto");
        book.addField("year", "2015");
        book.addField("title", "Ohjelmistotuotanto");
        
        references.addReference(book);
        
        references.saveToFile(rawPath);
        
        File file = new File(convertedPath);
        System.out.println(readFile(file));
        System.out.println("*************");
        System.out.println(book.toBibTex() + "\n");
        assertTrue(readFile(file).equals(book.toBibTex() + "\n"));
    }

    private String readFile(File file) throws FileNotFoundException, IOException {
        BufferedReader reader
                = new BufferedReader(new FileReader(file));

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
