import raemae.bibtexapp.*
import raemae.bibtexapp.domain.*
import raemae.bibtexapp.ui.*
import raemae.bibtexapp.services.*
import raemae.bibtexapp.services.uifunctions.*
import java.util.*

description "References are loaded from a file"

scenario "a BibTex.bib file exists  or is generated", {
    given 'a new book reference is created', {
        storage = new ReferenceStorage(new ArrayList<Reference>());
        io = new StubIO("add", "1", "aaaa", "aaaa", "aaaa", "", "", "", "", "", "", "save", "add", "1", "bbbb", "bbbb", "bbbb", "", "", "", "", "", "", "delete", "1", "load", "list", "5", "1", "quit")
        addRef = new AddReference(io, storage)
        listRef = new ListReferences(io, storage)
        saveRef = new SaveReferencesToFile(io, storage)
        loadRef = new LoadReferencesFromFile(io, storage)
        l = new ArrayList<TextUIFunction>()
        l.add(addRef)
        l.add(listRef)
        l.add(saveRef)
        l.add(loadRef)
        ui = new TextUI(l, io)
    }

    when 'file reads content fom loaded file', {
        ui.run()
    }

    then 'the program outputs contents from loaded file, instead of previous entry', {            
        prints = io.getPrints()
        prints.shouldHave("The file has been saved successfully!")
        prints.shouldHave("References have been loaded succesfully!")
        prints.shouldHave("Title: aaaa")
        prints.shouldNotHave("Title : bbbb")
    }
}