import raemae.bibtexapp.*
import raemae.bibtexapp.domain.*
import raemae.bibtexapp.ui.*
import raemae.bibtexapp.services.*
import java.util.*

    
    description "Editing a reference will change the values of the reference accordingly"
    
    scenario "Changing a reference's data will update its fields accordingly", {
    given 'a reference that is stored within the system', {
        storage = new ReferenceStorage(new ArrayList<Reference>());
        io = new StubIO("add", "1", "Arto Vihavainen", "Ohjelmistotuotanto", "2015", "KenttaA", "KenttaB", "KenttaC", "KenttaD", "KenttaE", "KenttaF", "edit", "arto2015ohje", "Virto Ahavainen", "Tuotanto-ohjelmisto", "5102", "KenttaG", "KenttaH", "KenttaI", "KenttaJ", "KenttaK", "KenttaL", "list", "1", "quit")
        addRef = new AddReference(io, storage)
        listRef = new ListReferences(io, storage)
        editRef = new EditReference(io, storage)
        l = new ArrayList<TextUIFunction>()
        l.add(addRef)
        l.add(listRef)
        l.add(editRef)
        ui = new TextUI(l, io)      
    }

    when 'the details of the reference are edited', {
        ui.run()
    }

    then 'the details of the reference are updated accordingly, and the old citation key remains', {
        prints = io.getPrints()
        shouldContain = ["Virto Ahavainen", "Tuotanto-ohjelmisto", "5102", "KenttaG", "KenttaH", "KenttaI", "KenttaJ", "KenttaK", "KenttaL"]
        for (String s : shouldContain) {
            prints.shouldHave(s)
        }
        prints.shouldHave("arto2015ohje")
    }   
}

scenario "Not doing changes will leave the previous values unchanged", {
    given 'a reference that is stored within the system', {
        storage = new ReferenceStorage(new ArrayList<Reference>());
        io = new StubIO("add", "1", "Arto Vihavainen", "Ohjelmistotuotanto", "2015", "KenttaA", "KenttaB", "KenttaC", "KenttaD", "KenttaE", "KenttaF", "edit", "Arto2015Ohje", "", "", "", "", "", "", "", "", "", "list", "1", "quit")
        addRef = new AddReference(io, storage)
        listRef = new ListReferences(io, storage)
        editRef = new EditReference(io, storage)
        l = new ArrayList<TextUIFunction>()
        l.add(addRef)
        l.add(listRef)
        l.add(editRef)
        ui = new TextUI(l, io)      
    }

    when 'the option to edit the reference is chosen but no new details are entered', {
        ui.run()
    }

    then 'the original details are retained', {
        prints = io.getPrints()
        shouldContain = ["Arto Vihavainen", "Ohjelmistotuotanto", "2015", "KenttaA", "KenttaB", "KenttaC", "KenttaD", "KenttaE", "KenttaF"]
        for (String s : shouldContain) {
            prints.shouldHave(s)
        }
    }   
}
	


