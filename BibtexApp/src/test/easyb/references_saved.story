import raemae.bibtexapp.*
import raemae.bibtexapp.domain.*
import raemae.bibtexapp.ui.*
import raemae.bibtexapp.services.*
import java.util.*

description """References are saved successfully into a .bib file"""

scenario "one reference is saved successfully", {
    given 'one reference is added into the reference storage', {
        storage = new ReferenceStorage(new ArrayList<Reference>());
        io = new StubIO("add", "1", "Arto Vihavainen", "Ohjelmistotuotanto", "2015", "", "", "", "", "", "", "save", "quit")
        addRef = new AddReference(io, storage)
        listRef = new ListReferences(io, storage)
        saveRefs = new SaveReferencesToFile(io, storage)
        l = new ArrayList<TextUIFunction>()
        l.add(addRef)
        l.add(listRef)
        l.add(saveRefs)
        ui = new TextUI(l, io)      
    }

    when 'the save command is run', {
        ui.run()
    }

    then 'the file should have one reference', {
        System.out.println(io.getPrints())

        file = new File("BibTeX.bib")

        book = new Book()
        book.addField("author", "Arto Vihavainen")
        book.addField("year", "2015")
        book.addField("title", "Ohjelmistotuotanto")
        fileText = file.text.replace("Arto2015Ohje", "null").replace("\n", "")
        bookText = book.toBibTex().replace("\n", "")

        fileText.equals(bookText).shouldBe true    
    }
}
