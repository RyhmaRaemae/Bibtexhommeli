package raemae.bibtexapp.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
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

}
