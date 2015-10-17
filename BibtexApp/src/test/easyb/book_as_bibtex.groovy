import raemae.bibtexapp.*
import raemae.bibtexapp.domain.*
import raemae.bibtexapp.ui.*
import raemae.bibtexapp.services.*
import java.util.*

description 'Books can be viewed as BibTeX'

scenario "Show one book with only required fields as BibTex", {
  given 'there is one book with the required fields in the app', {
        storage = new ReferenceStorage(new ArrayList<Reference>());
        io = new StubIO("add", "1", "Arto Vihavainen", "Ohjelmistotuotanto", "2015", "", "", "", "", "", "", "list", "quit")
        addRef = new AddReference(io, storage)
        listRef = new ListReferences(io, storage)
        l = new ArrayList<TextUIFunction>()
        l.add(addRef)
        l.add(listRef)
        ui = new TextUI(l, io)  
  }

  when 'command listbooks selected', {
      ui.run()
  }

  then 'the book will be shown as BibTeX', {
      prints = io.getPrints()
        a = ""
        b = ""
        for (String p : prints) {
            if (p.contains("@book{Arto2015Ohje") && p.contains("year = {2015}") && p.contains("author = {Arto Vihavainen}") && p.contains("title = {Ohjelmistotuotanto}")) {
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

scenario "Show multiple books with only required fields as BibTex", {
  given 'there are multiple books with the required fields in the app', {
        storage = new ReferenceStorage(new ArrayList<Reference>());
        io = new StubIO("add", "1", "Arto Vihavainen", "Ohjelmistotuotanto", "2015", "", "", "", "", "", "", "add", "1", "Toinen Jeppe", "OhTu", "2014", "", "", "", "", "", "", "list", "quit")
        addRef = new AddReference(io, storage)
        listRef = new ListReferences(io, storage)
        l = new ArrayList<TextUIFunction>()
        l.add(addRef)
        l.add(listRef)
        ui = new TextUI(l, io)  
  }

  when 'command listbooks selected', {
        ui.run()
  }

  then 'the books will be shown as BibTeX', {
      //add test to read both entries
  }
}


scenario "Show one book with optional fields as BibTex", {
  given 'there is one book with optional fields in the app', {
        storage = new ReferenceStorage(new ArrayList<Reference>());
        io = new StubIO("add", "1", "Arto Vihavainen", "Ohjelmistotuotanto", "2015", "1", "", "", "", "", "", "list", "quit")
        addRef = new AddReference(io, storage)
        listRef = new ListReferences(io, storage)
        l = new ArrayList<TextUIFunction>()
        l.add(addRef)
        l.add(listRef)
        ui = new TextUI(l, io)  
  }

  when 'command listbooks selected', {
      ui.run()
  }

  then 'the book will be shown as BibTeX', {
        prints = io.getPrints()
        a = ""
        b = ""
        for (String p : prints) {
            if (p.contains("@book{Arto2015Ohje") && p.contains("year = {2015}") && p.contains("author = {Arto Vihavainen}") && p.contains("title = {Ohjelmistotuotanto}") && p.contains("volume = {1}")) {
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


scenario "Show multiple books with optional fields as BibTex", {
  given 'there are multiple books with optional fields in the app', {
        storage = new ReferenceStorage(new ArrayList<Reference>());
        io = new StubIO("add", "1", "Arto Vihavainen", "Ohjelmistotuotanto", "2015", "1", "", "", "", "", "", "add", "1", "Toinen Jeppe", "OhTu", "2014", "2", "", "", "", "", "", "list", "quit")
        addRef = new AddReference(io, storage)
        listRef = new ListReferences(io, storage)
        l = new ArrayList<TextUIFunction>()
        l.add(addRef)
        l.add(listRef)
        ui = new TextUI(l, io)  
    }

  when 'command listbooks selected', {
      ui.run()
  }

  then 'the books will be shown as BibTeX', {
      //add test to read both entries
  }
}