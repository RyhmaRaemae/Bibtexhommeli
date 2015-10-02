package raemae.bibtexapp.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {

    @Test
    public void bookDataTest() {
        Book book=new Book();
        
        book.addField("author","Vihavainen, Arto");
        book.addField("year","2015");
        book.addField("title","Ohjelmistotuotanto");
        System.out.println(book.toBibTex());
        assertEquals(book.getField("author"), "Vihavainen, Arto");
        assertEquals(book.getField("year"), "2015");
        assertEquals(book.getField("title"), "Ohjelmistotuotanto");    
    }
    
    @Test
    public void addFieldTest() {
        Book book1 = new Book();
        Book book2 = new Book();
        book1.addField("author","Vihavainen");
        book2.addField("author","Vihavainen");
        assertEquals(book1.getField("author"), book2.getField("author"));
        book2.addField("author", "Someoneelse");
        assertThat(book1.getField("author"), is(not(book2.getField("author"))));
        
    }
    
    @Test
   public void toBibTexTest() {
       Book book=new Book();
       
       book.addField("author","Vihavainen, Arto");
       book.addField("year","2015");
       book.addField("title","Ohjelmistotuotanto");
        
        assertEquals(book.toBibTex(), "@book{"+book.getCitationKey()+",\n"                                
                                    + "year = {"+book.getField("year")+"},\n"
                                    + "author = {"+book.getField("author")+"},\n"
                                    + "title = {"+book.getField("title")+"}\n}"); 
    }
   
   @Test
   public void citationKeyTest() {
       Book book=new Book();
       
       book.addField("author","Vihavainen, Arto");
       book.addField("year","2015");
       book.addField("title","Ohjelmistotuotanto");
       
       book.setCitationKey("");
       
       assertEquals(book.getCitationKey(), "Viha2015Ohje");
       
       book.setCitationKey("#");
        
       assertEquals(book.getCitationKey(), "Viha2015Ohje#"); 
    }
    
    
    
}
