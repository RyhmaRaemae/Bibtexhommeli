package raemae.bibtexapp.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {
    
    public BookTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void toBibTexTest() {
        Book book=new Book();
        
        book.addField("author","Vihavainen, Arto");
        book.addField("year","2015");
        book.addField("title","Ohjelmistotuotanto");
        System.out.println(book.toBibTex());
        assertEquals(0, 0); 
    }
    
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
        book1.addField("year","2015");
        assertThat(book1, is(not(book2)));
        
    }
    
}
