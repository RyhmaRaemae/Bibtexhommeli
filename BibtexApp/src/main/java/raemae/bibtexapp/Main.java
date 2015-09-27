package raemae.bibtexapp;

import java.util.ArrayList;
import java.util.List;
import raemae.bibtexapp.domain.Book;
import raemae.bibtexapp.ui.*;
import services.BookReferenceStorage;

public class Main {

    public static void main(String[] args) {
        List<TextUIFunction> l = new ArrayList<TextUIFunction>();
        BookReferenceStorage books = new BookReferenceStorage(new ArrayList<Book>());
        ConsoleIO io = new ConsoleIO();
        l.add(new AddBook(io, books));
        l.add(new ListBooks(io, books));
        TextUI ui = new TextUI(l, io);
        ui.run();
    }
    
}
