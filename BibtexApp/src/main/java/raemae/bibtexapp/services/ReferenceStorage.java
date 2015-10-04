package raemae.bibtexapp.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import raemae.bibtexapp.domain.Article;
import raemae.bibtexapp.domain.Book;
import raemae.bibtexapp.domain.InProceedings;
import raemae.bibtexapp.domain.Reference;

public class ReferenceStorage {

    private List<Reference> references;

    public ReferenceStorage(List<Reference> references) {
        this.references = references;
    }

    public void addReference(Reference b) {
        references.add(b);
    }

    public List<Reference> getReferences() {
        return references;
    }

    public boolean saveToFile(String path) {
        try {
            File file = new File(path);
//            File file = new File(path.replaceFirst("^~", System.getProperty("user.home")));

            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), "UTF8"));

            StringBuilder sb = new StringBuilder();
            for (Reference reference : references) {
                sb.append(reference.toBibTex());
                sb.append("\n");
            }

            out.write(sb.toString());

            out.flush();
            out.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean loadFromFile(String path) {
        references.clear();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(new File(path)));
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
        System.out.println("AAAA");
        System.out.println("Reader: " + reader != null);
        Reference r = null;
        String[] parts;
        String line = null;
        StringBuilder sb = new StringBuilder();
        try {
            line = reader.readLine();
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
        System.out.println(line);
        while (line != null) {
            System.out.println("Luuppi");
            parts = line.split("\\{");
            System.out.println(parts[0]);
            if (parts[0].equals("@book")) {
                r = new Book();
            }
            if (parts[0].equals("@article")) {
                r = new Article();
            }
            if (parts[0].equals("@inproceedings")) {
                r = new InProceedings();
            }
            if (r == null) {
                return false;
            }

            String citationKey = parts[1].substring(0, parts[1].indexOf(","));
            System.out.println(citationKey);
            r.loadCitationKey(citationKey);
            try {
                line = reader.readLine();
            } catch (Exception e) {
                System.out.println(e.toString());
                return false;
            }
            while (line.charAt(0) != 125) {
                parts = line.split("\\s+");
                String key = parts[0];
                System.out.println("key: " + key);
                String value = line.substring(line.indexOf("{") + 1, line.indexOf("}"));
                System.out.println("value: " + value);
                r.addField(key, value);
                try {
                    line = reader.readLine();
                } catch (Exception e) {
                    System.out.println(e.toString());
                    return false;
                }
                System.out.println(line);
                System.out.println((int) line.charAt(0));
            }
            System.out.println("Löytyi: " + line);
            addReference(r);
            try {
                line = reader.readLine();
            } catch (Exception e) {
                System.out.println(e.toString());
                return false;
            }

        }
        System.out.println("Luettu kaikki!");
        try {
            reader.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }

        return true;
    }

}
