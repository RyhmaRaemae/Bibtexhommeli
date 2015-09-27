
package raemae.bibtexapp.ui;

import raemae.bibtexapp.domain.Book;
import services.BookReferenceStorage;


public class AddBook extends TextUIFunction {
    
    private IO io;
    private BookReferenceStorage books;

    public AddBook(IO io, BookReferenceStorage books) {
        super(1);
        this.io = io;
        this.books = books;
    }

    @Override
    public void execute() {
        io.print("");
        String author = io.readLine("Author: ");
        String title = io.readLine("Title: ");
        String year = io.readLine("Year: ");
        
        Book b = new Book();
        b.addField("author", author);
        b.addField("title", title);
        b.addField("year", year);
        books.addBook(b);
        
        io.print("Reference added.");
    }

    @Override
    public String getMenuName() {
        return "addbook";
    }

    @Override
    public String getMenuDescription() {
        return "Add a book-type reference";
    }

}
