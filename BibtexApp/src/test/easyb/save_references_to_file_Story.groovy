import raemae.bibtexapp.*
import raemae.bibtexapp.domain.*
import raemae.bibtexapp.ui.*
import raemae.bibtexapp.services.*
import java.util.*

description "References are saved to a file"

scenario "a file is generated and bibtex is added into it", {
    given 'a new book reference is created', {
        storage = new ReferenceStorage(new ArrayList<Reference>());
        io = new StubIO("add", "1", "Arto Vihavainen", "Ohjelmistotuotanto", "2015", "", "", "", "", "", "", "save", "quit")
        addRef = new AddReference(io, storage)
        listRef = new ListReferences(io, storage)
        l = new ArrayList<TextUIFunction>()
        l.add(addRef)
        l.add(listRef)
        ui = new TextUI(l, io)
    }

    when 'the book is entered into the system and it is saved into a bibtex file', {
        ui.run()
    }

    then 'the file with contents exists', {            
        storage.loadFromFile("BibTeX.bib").shouldNotBe(null)
    }   
}