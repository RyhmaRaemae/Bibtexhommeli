import raemae.bibtexapp.*
import raemae.bibtexapp.domain.*
import raemae.bibtexapp.ui.*
import raemae.bibtexapp.services.*
import java.util.*

description 'User can delete references'

scenario "All references can be deleted", {
    given 'multiple references of different types', {
        storage = new ReferenceStorage(new ArrayList<Reference>());
        io = new StubIO("add", "1", "Keke Kirjoittaja", "Kirja A", "2015", "", "", "", "", "", "", "add", "1", "Spede Vasanen", "Kirja B", "2015", "", "", "", "", "", "", "add", "2", "Ababe", "Artkkeli A", "Artikla", "2015", "", "", "", "", "", "", "add", "2", "Bcdce", "Artikkeli B", "Kartikla", "2015", "", "", "", "", "", "", "add", "3", "InProceedingstekija", "Inproceedings A", "InprA", "2015", "", "", "", "", "", "", "", "", "add", "3", "Inproceedingstekijatar", "Inproceedings B", "InprB", "2015", "", "", "", "", "", "", "", "", "delete", "1", "list", "1", "quit")
        addRef = new AddReference(io, storage)
        listRef = new ListReferences(io, storage)
        deleteRef = new DeleteReference(io, storage)
        l = new ArrayList<TextUIFunction>()
        l.add(addRef)
        l.add(listRef)
        l.add(deleteRef)
        ui = new TextUI(l, io)      
    }

    when 'the option to delete all references is chosen', {
        ui.run()
    }

    then 'all references are deleted', {
        storage.getReferences().size().shouldBe(0)
    }    
    
}

scenario "A specific reference can be deleted", {
    given 'multiple references of different types', {
        storage = new ReferenceStorage(new ArrayList<Reference>());
        io = new StubIO("add", "1", "Keke Kirjoittaja", "Kirja A", "2015", "", "", "", "", "", "", "add", "1", "Spede Vasanen", "Kirja B", "2015", "", "", "", "", "", "", "add", "2", "Ababe", "Artkkeli A", "Artikla", "2015", "", "", "", "", "", "", "add", "2", "Bcdce", "Artikkeli B", "Kartikla", "2015", "", "", "", "", "", "", "add", "3", "InProceedingstekija", "Inproceedings A", "InprA", "2015", "", "", "", "", "", "", "", "", "add", "3", "Inproceedingstekijatar", "Inproceedings B", "InprB", "2015", "", "", "", "", "", "", "", "", "delete", "2", "1", "list", "1", "quit")
        addRef = new AddReference(io, storage)
        listRef = new ListReferences(io, storage)
        deleteRef = new DeleteReference(io, storage)
        l = new ArrayList<TextUIFunction>()
        l.add(addRef)
        l.add(listRef)
        l.add(deleteRef)
        ui = new TextUI(l, io)      
    }

    when 'the option to delete a specific reference is chosen', {
        ui.run()
    }

    then 'the reference in question is deleted but all others remain', {
        for (Reference r : storage.getReferences()) {
            r.getField("title").shouldNotEqual("Kirja A")
        }
        storage.getReferences().size().shouldBe(5)
    }   
    
}

scenario "A specific type of reference can be deleted", {
    given 'multiple references of different types', {
        storage = new ReferenceStorage(new ArrayList<Reference>());
        io = new StubIO("add", "1", "Keke Kirjoittaja", "Kirja A", "2015", "", "", "", "", "", "", "add", "1", "Spede Vasanen", "Kirja B", "2015", "", "", "", "", "", "", "add", "2", "Ababe", "Artkkeli A", "Artikla", "2015", "", "", "", "", "", "", "add", "2", "Bcdce", "Artikkeli B", "Kartikla", "2015", "", "", "", "", "", "", "add", "3", "InProceedingstekija", "Inproceedings A", "InprA", "2015", "", "", "", "", "", "", "", "", "add", "3", "Inproceedingstekijatar", "Inproceedings B", "InprB", "2015", "", "", "", "", "", "", "", "", "delete", "4", "book", "list", "1", "quit")
        addRef = new AddReference(io, storage)
        listRef = new ListReferences(io, storage)
        deleteRef = new DeleteReference(io, storage)
        l = new ArrayList<TextUIFunction>()
        l.add(addRef)
        l.add(listRef)
        l.add(deleteRef)
        ui = new TextUI(l, io)      
    }

    when 'the option to delete a specific type of reference is chosen', {
        ui.run()
    }

    then 'the references of that type are deleted but all others remain', {
        for (Reference r : storage.getReferences()) {
            r.getType().shouldNotEqual("book")
        }
        storage.getReferences().size().shouldBe(4)
    }    
    
}

scenario "All references containing a specific value in any field can be deleted", {
    given 'multiple references of different types', {
        storage = new ReferenceStorage(new ArrayList<Reference>());
        io = new StubIO("add", "1", "Keke Kirjoittaja", "Kirja A", "2013", "", "", "", "", "", "", "add", "1", "Spede Vasanen", "Kirja B", "2015", "", "", "", "", "", "", "add", "2", "Ababe", "Artkkeli A", "Artikla", "2014", "", "", "", "", "", "", "add", "2", "Bcdce", "Artikkeli B", "Kartikla", "2014", "", "", "", "", "", "", "add", "3", "InProceedingstekija", "Inproceedings A", "InprA", "2013", "", "", "", "", "", "", "", "", "add", "3", "Inproceedingstekijatar", "Inproceedings B", "InprB", "2013", "", "", "", "", "", "", "", "", "delete", "5", "2013", "list", "1", "quit")
        addRef = new AddReference(io, storage)
        listRef = new ListReferences(io, storage)
        deleteRef = new DeleteReference(io, storage)
        l = new ArrayList<TextUIFunction>()
        l.add(addRef)
        l.add(listRef)
        l.add(deleteRef)
        ui = new TextUI(l, io)      
    }

    when 'the option to delete all references containing a specific value is chosen', {
        ui.run()
    }

    then 'the references of that type are deleted but all others remain', {
        for (Reference r : storage.getReferences()) {
            r.getField("year").shouldNotEqual("2013")
        }
        storage.getReferences().size().shouldBe(3)
    }    
    
}

scenario "All references containing a specific value in a specific field can be deleted", {
    given 'multiple references of different types', {
        storage = new ReferenceStorage(new ArrayList<Reference>());
        io = new StubIO("add", "1", "2014", "Kirja A", "2013", "", "", "", "", "", "", "add", "1", "Spede Vasanen", "Kirja B", "2015", "", "", "", "", "", "", "add", "2", "Ababe", "Artkkeli A", "Artikla", "2014", "", "", "", "", "", "", "add", "2", "Bcdce", "Artikkeli B", "Kartikla", "2012", "", "", "", "", "", "", "add", "3", "InProceedingstekija", "Inproceedings A", "InprA", "2013", "", "", "", "", "", "", "", "", "add", "3", "Inproceedingstekijatar", "Inproceedings B", "InprB", "2014", "", "", "", "", "", "", "", "", "delete", "6", "year", "2014", "list", "1", "quit")
        addRef = new AddReference(io, storage)
        listRef = new ListReferences(io, storage)
        deleteRef = new DeleteReference(io, storage)
        l = new ArrayList<TextUIFunction>()
        l.add(addRef)
        l.add(listRef)
        l.add(deleteRef)
        ui = new TextUI(l, io)      
    }

    when 'the option to delete all references containing a specific value is chosen', {
        ui.run()
    }

    then 'the references of that type are deleted but all others remain', {
        for (Reference r : storage.getReferences()) {
            r.getField("year").shouldNotEqual("2014")
        }
        storage.getReferences().get(0).getField("author").shouldBe("2014")
        storage.getReferences().size().shouldBe(4)
    }    
    
}

scenario "References can be deleted by citation key", {
    given 'multiple references of different types', {
        storage = new ReferenceStorage(new ArrayList<Reference>());
        io = new StubIO("add", "1", "Kirjoittelija", "Kirja A", "2013", "", "", "", "", "", "", "add", "1", "Spede Vasanen", "Kirja B", "2015", "", "", "", "", "", "", "add", "2", "Ababe", "Artkkeli A", "Artikla", "2014", "", "", "", "", "", "", "add", "2", "Bcdce", "Artikkeli B", "Kartikla", "2012", "", "", "", "", "", "", "add", "3", "InProceedingstekija", "Inproceedings A", "InprA", "2013", "", "", "", "", "", "", "", "", "add", "3", "Inproceedingstekijatar", "Inproceedings B", "InprB", "2014", "", "", "", "", "", "", "", "", "delete", "3", "Kirj2013Kirj", "list", "1", "quit")
        addRef = new AddReference(io, storage)
        listRef = new ListReferences(io, storage)
        deleteRef = new DeleteReference(io, storage)
        l = new ArrayList<TextUIFunction>()
        l.add(addRef)
        l.add(listRef)
        l.add(deleteRef)
        ui = new TextUI(l, io)        
    }

    when 'the option to delete a reference by citation key is chosen', {
        ui.run()
    }

    then 'the reference containing the specific citation key should be deleted but all the others remain', {
        for (Reference r : storage.getReferences()) {
            r.getCitationKey().shouldNotEqual("Kirj2013Kirj")
        }
        storage.getReferences().size().shouldBe(5)
    }    
    
}