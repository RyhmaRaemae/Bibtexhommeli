
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
        io.print("Please enter the following required fields:");
        String author = io.readLine("Author: ");
        if (author.length() < 4) {
            io.print("The name of the author must be at least 4 characters long.");
            return;
        }
        
        String title = io.readLine("Title: ");
        if (title.length() < 4) {
            io.print("The title must be at least 4 characters long.");
            return;
        }
        
        
        String year = io.readLine("Year: ");
        if (year.length() < 4) {
            io.print("The year must be 4 characters long.");
            return;
        }
        
        io.print("The following fields are optional and can be left empty if they are not included in the reference:");
        String volume = io.readLine("Volume or Number: ");
        String series = io.readLine("Series: ");
        String address = io.readLine("Address: ");
        String edition = io.readLine("Edition: ");
        String month = io.readLine("Month: ");
        String note = io.readLine("Note: ");
        
        
        
        Book b = new Book();
        b.addField("author", author);
        b.addField("title", title);
        b.addField("year", year);
        if (!volume.isEmpty()) {
            b.addField("volume", volume);
        }
        if (!series.isEmpty()) {
            b.addField("series", series);
        }
        if (!address.isEmpty()) {
            b.addField("address", address);
        }
        if (!edition.isEmpty()) {
            b.addField("edition", edition);
        }
        if (!month.isEmpty()) {
            b.addField("month", month);
        }
        if (!note.isEmpty()) {
            b.addField("note", note);
        }
        
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
