import raemae.bibtexapp.*
import raemae.bibtexapp.domain.*
import raemae.bibtexapp.ui.*
import raemae.bibtexapp.services.*
import java.util.*

description 'User can add books into the app'

scenario "adding author, year and title fields to a new book", {

    given "an empty empty book",{
        book = new Book();
    }

    when "author, year and title are added to the book", {
        book.addField("author","Vihavainen, Arto");
        book.addField("year","2015");
        book.addField("title","Ohjelmistotuotanto");
    }

    then "the book information contains the author, year and title", {
        book.getField("author").shouldBe "Vihavainen, Arto"
        book.getField("year").shouldBe "2015"
        book.getField("title").shouldBe "Ohjelmistotuotanto"
    }
}