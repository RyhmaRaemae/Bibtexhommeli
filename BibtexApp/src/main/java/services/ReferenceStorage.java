
package services;

import java.util.List;
import raemae.bibtexapp.domain.Book;
import raemae.bibtexapp.domain.Reference;


public class ReferenceStorage {
    
    private List<Reference> books;
    
    public ReferenceStorage(List<Reference> books) {
        this.books = books;
    }
    
    public void addBook(Reference b) {
        books.add(b);
    }
    
    public List<Reference> getBooks() {
        return books;
    }
    

}
