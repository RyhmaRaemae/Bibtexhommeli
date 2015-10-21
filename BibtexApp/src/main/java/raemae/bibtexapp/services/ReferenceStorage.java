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
        BufferedReader reader = createReader(path);

        if (reader == null) {
            return false;
        }

        String line = getNextLine(reader);
        if (line == null) {
            return false;
        }
        
        if (readAndAddReferences(reader, line) == false) {
            return false;
        }
        
        closeReader(reader);

        return true;
    }
    
    private boolean readAndAddReferences(BufferedReader reader, String line) {
        while (line != null) {
            String[] parts = line.split("\\{");

            Reference r = getNewReferenceOfType(parts[0]);

            if (r == null) {
                return false;
            }

            String citationKey = parts[1].substring(0, parts[1].indexOf(","));
            r.loadCitationKey(citationKey);

            line = getNextLine(reader);
            addFieldsToReference(r, parts, line, reader);
            addReference(r);

            line = getNextLine(reader);
        }
        return true;
    }

    private void closeReader(BufferedReader reader) {
        try {
            reader.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private String getNextLine(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    private BufferedReader createReader(String path) {
        try {
            return new BufferedReader(new FileReader(new File(path)));
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    private boolean addFieldsToReference(Reference r, String[] parts, String line, BufferedReader reader) {
        while (line.charAt(0) != 125) {
            parts = line.split("\\s+");
            String key = parts[0];
            String value = line.substring(line.indexOf("{") + 1, line.lastIndexOf("}"));
            r.addField(key, ScandicConverter.bibTexToScand(value));
            line = getNextLine(reader);
        }
        return true;
    }

    private Reference getNewReferenceOfType(String s) {
        if (s.equals("@book")) {
            return new Book();
        }
        if (s.equals("@article")) {
            return new Article();
        }
        if (s.equals("@inproceedings")) {
            return new InProceedings();
        }
        return null;
    }

}
