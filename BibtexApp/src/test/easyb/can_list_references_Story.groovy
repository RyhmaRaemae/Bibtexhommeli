import raemae.bibtexapp.*
import raemae.bibtexapp.domain.*
import raemae.bibtexapp.ui.*
import raemae.bibtexapp.services.*
import java.util.*

    
description "References can be listed in various ways"
    
scenario "References can be listed by type", {
    given 'multiple references of different types', {
        storage = new ReferenceStorage(new ArrayList<Reference>());
        io = new StubIO("add", "1", "Kirjoittelija", "Kirja A", "2013", "", "", "", "", "", "", "add", "1", "Spede Vasanen", "Kirja B", "2015", "", "", "", "", "", "", "add", "2", "Ababe", "Artkkeli A", "Artikla", "2014", "", "", "", "", "", "", "add", "2", "Bcdce", "Artikkeli B", "Kartikla", "2012", "", "", "", "", "", "", "add", "3", "InProceedingstekija", "Inproceedings A", "InprA", "2013", "", "", "", "", "", "", "", "", "add", "3", "Inproceedingstekijatar", "Inproceedings B", "InprB", "2014", "", "", "", "", "", "", "", "", "list", "2", "book", "quit")
        addRef = new AddReference(io, storage)
        listRef = new ListReferences(io, storage)
        deleteRef = new DeleteReference(io, storage)
        l = new ArrayList<TextUIFunction>()
        l.add(addRef)
        l.add(listRef)
        l.add(deleteRef)
        ui = new TextUI(l, io)        
    }

    when 'the option to list references of a specific type is chosen', {
        ui.run()
    }

    then 'Only the selected type of references are listed', {
        prints = io.getPrints()
        prints.shouldHave("title = {Kirja A}")
        prints.shouldNotHave("title = {Artikkeli A}")
        
    }
}

scenario "References can be listed by matching a field to a value", {
    given 'multiple references of different types', {
        storage = new ReferenceStorage(new ArrayList<Reference>());
        io = new StubIO("add", "1", "Kirjoittelija", "Kirja A", "2013", "", "", "", "", "", "", "add", "1", "Spede Vasanen", "Kirja B", "2015", "", "", "", "", "", "", "add", "2", "Ababe", "Artkkeli A", "Artikla", "2014", "", "", "", "", "", "", "add", "2", "Bcdce", "Artikkeli B", "Kartikla", "2012", "", "", "", "", "", "", "add", "3", "InProceedingstekija", "Inproceedings A", "InprA", "2013", "", "", "", "", "", "", "", "", "add", "3", "Inproceedingstekijatar", "Inproceedings B", "InprB", "2014", "", "", "", "", "", "", "", "", "list", "3", "year", "2013", "quit")
        addRef = new AddReference(io, storage)
        listRef = new ListReferences(io, storage)
        deleteRef = new DeleteReference(io, storage)
        l = new ArrayList<TextUIFunction>()
        l.add(addRef)
        l.add(listRef)
        l.add(deleteRef)
        ui = new TextUI(l, io)        
    }

    when 'the option to list references where a specific field contains a specific value is chosen', {
        ui.run()
    }

    then 'Only the matching references are listed', {
        prints = io.getPrints()
        prints.shouldHave("year = {2013}")
        prints.shouldNotHave("year = {2015}")        
    }
}

scenario "References can be listed by matching any field to a value", {
    given 'multiple references of different types', {
        storage = new ReferenceStorage(new ArrayList<Reference>());
        io = new StubIO("add", "1", "2015", "Kirja A", "2013", "", "", "", "", "", "", "add", "1", "Spede Vasanen", "Kirja B", "2015", "", "", "", "", "", "", "add", "2", "Ababe", "Artkkeli A", "Artikla", "2014", "", "", "", "", "", "", "add", "2", "Bcdce", "Artikkeli B", "Kartikla", "2012", "", "", "", "", "", "", "add", "3", "InProceedingstekija", "Inproceedings A", "InprA", "2013", "", "", "", "", "", "", "", "", "add", "3", "Inproceedingstekijatar", "Inproceedings B", "InprB", "2014", "", "", "", "", "", "", "", "", "list", "4", "2015", "quit")
        addRef = new AddReference(io, storage)
        listRef = new ListReferences(io, storage)
        deleteRef = new DeleteReference(io, storage)
        l = new ArrayList<TextUIFunction>()
        l.add(addRef)
        l.add(listRef)
        l.add(deleteRef)
        ui = new TextUI(l, io)        
    }

    when 'the option to list references where any field contains a specific value is chosen', {
        ui.run()
    }

    then 'Only the matching references are listed', {
        prints = io.getPrints()
        prints.shouldHave("author = {2015}")
        prints.shouldHave("year = {2015}")        
    }
}
   
	


