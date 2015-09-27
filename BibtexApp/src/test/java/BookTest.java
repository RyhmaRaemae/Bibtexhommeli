
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import raemae.bibtexapp.domain.Book;

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

    // Dummy test to test TravisCI
    @Test
    public void dummy() {
        assertEquals(0, 0);
    }
    
    @Test
    public void toBibTexTest() {
        Book book=new Book();
        
        book.addField("author","Vihavainen, Arto");
        book.addField("year","2015");
        book.addField("title","Ohjelmistotuotanto");
        System.out.println(book.toBibTex());
        assertEquals(0,0);
    }
}
