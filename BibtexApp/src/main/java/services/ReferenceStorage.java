package services;

import java.util.List;
import raemae.bibtexapp.domain.Book;
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

}
