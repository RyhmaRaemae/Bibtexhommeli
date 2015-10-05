import raemae.bibtexapp.*
import raemae.bibtexapp.domain.*
import raemae.bibtexapp.ui.*
import raemae.bibtexapp.services.*
import java.util.*

description """Citation keys generated when references are created are unique"""

scenario "book references have a unique citation key", {
    given 'two book references with the same required fields', {
        storage = new ReferenceStorage(new ArrayList<Reference>());
        io = new StubIO("add", "1", "Arto Vihavainen", "Ohjelmistotuotanto", "2015", "", "", "", "", "", "", "add", "1", "Arto Vihavainen", "Ohjelmistotuotanto", "2015", "", "", "", "", "", "", "list", "quit")
        addRef = new AddReference(io, storage)
        listRef = new ListReferences(io, storage)
        l = new ArrayList<TextUIFunction>()
        l.add(addRef)
        l.add(listRef)
        ui = new TextUI(l, io)      
    }

    when 'the two books are entered into the system and their details compared', {
        ui.run()
    }

    then 'their citation keys are different', {
        prints = io.getPrints()
        a = ""
        b = ""
        for (String p : prints) {
            if (p.contains("Arto2015Ohje")) {
                if (a.equals("")) {
                    a = p
                }
                else {
                    b = p
                }
            }
        }
        a.shouldNotEqual(b)    
    }
  
    
}

scenario "article references have a unique citation key", {
    given 'two article references with the same required fields', {
        storage = new ReferenceStorage(new ArrayList<Reference>());
        io = new StubIO("add", "2", "ArtoVihavainen", "Ohjelmistotuotanto", "Ohtu", "2015", "", "", "", "", "", "", "add", "2", "Arto Vihavainen", "Ohjelmistotuotanto", "Ohtu", "2015", "", "", "", "", "", "", "list", "quit")
        addRef = new AddReference(io, storage)
        listRef = new ListReferences(io, storage)
        l = new ArrayList<TextUIFunction>()
        l.add(addRef)
        l.add(listRef)
        ui = new TextUI(l, io)      
    }

    when 'the two articles are entered into the system and their details compared', {
        ui.run()
    }

    then 'their citation keys are different', {
        prints = io.getPrints()
        a = ""
        b = ""
        for (String p : prints) {
            if (p.contains("Arto2015Ohje")) {
                if (a.equals("")) {
                    a = p
                }
                else {
                    b = p
                }
            }
        }
        a.shouldNotEqual(b)
    }
  
    
}

scenario "inproceedings references have a unique citation key", {
    given 'two inproceedings references with the same required fields', {
        storage = new ReferenceStorage(new ArrayList<Reference>());
        io = new StubIO("add", "3", "ArtoVihavainen", "Ohjelmistotuotanto", "Ohtu", "2015", "", "", "", "", "", "", "", "", "add", "3", "Arto Vihavainen", "Ohjelmistotuotanto", "Ohtu", "2015", "", "", "", "", "", "", "", "", "list", "quit")
        addRef = new AddReference(io, storage)
        listRef = new ListReferences(io, storage)
        l = new ArrayList<TextUIFunction>()
        l.add(addRef)
        l.add(listRef)
        ui = new TextUI(l, io)      
    }

    when 'the two inproceedings are entered into the system and their details compared', {
        ui.run()
    }

    then 'their citation keys are different', {
        prints = io.getPrints()
        a = ""
        b = ""
        for (String p : prints) {
            if (p.contains("Arto2015Ohje")) {
                if (a.equals("")) {
                    a = p
                }
                else {
                    b = p
                }
            }
        }
        a.shouldNotEqual(b)
    }
  
    
}

