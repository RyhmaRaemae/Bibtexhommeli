
package services;

import java.util.List;
import raemae.bibtexapp.domain.Book;


public class BookReferenceStorage {
    
    private List<Book> books;
    
    public BookReferenceStorage(List<Book> books) {
        this.books = books;
    }
    
    public void addBook(Book b) {
        books.add(b);
    }
    
    public List<Book> getBooks() {
        return books;
    }
    

}
