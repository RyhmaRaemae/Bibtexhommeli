package raemae.bibtexapp.ui;

import raemae.bibtexapp.domain.Book;
import services.BookReferenceStorage;

public class ListBooks extends TextUIFunction {

    private IO io;
    private BookReferenceStorage books;

    public ListBooks(IO io, BookReferenceStorage books) {
        super(2);
        this.io = io;
        this.books = books;
    }

    @Override
    public void execute() {
        io.print("");
        io.print("Found the following book references:\n");
        for (Book b : books.getBooks()) {
            io.print(b.toBibTex());
        }
    }

    @Override
    public String getMenuName() {
        return "listbooks";
    }

    @Override
    public String getMenuDescription() {
        return "List all book-type references";
    }

}
