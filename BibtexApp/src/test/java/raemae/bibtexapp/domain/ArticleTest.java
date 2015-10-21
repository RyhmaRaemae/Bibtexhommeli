package raemae.bibtexapp.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.internal.matchers.StringContains.containsString;

public class ArticleTest {

    @Test
    public void articleDataTest() {
        Article article = new Article();

        article.addField("author", "Vihavainen, Arto");
        article.addField("year", "2015");
        article.addField("title", "Ohjelmistotuotanto");
        article.addField("journal", "Journal");

        assertEquals(article.getField("author"), "Vihavainen, Arto");
        assertEquals(article.getField("year"), "2015");
        assertEquals(article.getField("title"), "Ohjelmistotuotanto");
        assertEquals(article.getField("journal"), "Journal");
    }

    @Test
    public void addFieldTest() {
        Article article = new Article();
        Article article2 = new Article();
        article.addField("author", "Vihavainen");
        article2.addField("author", "Vihavainen");
        assertEquals(article.getField("author"), article2.getField("author"));
        article2.addField("author", "Someoneelse");
        assertThat(article.getField("author"), is(not(article2.getField("author"))));
    }

    @Test
    public void toBibTexTest() {
        Article article = new Article();

        article.addField("author", "Vihavainen, Arto");
        article.addField("year", "2015");
        article.addField("title", "Ohjelmistotuotanto");
        article.addField("journal", "Journal");

        assertThat(article.toBibTex(), containsString("author = {Vihavainen, Arto}"));
        assertThat(article.toBibTex(), containsString("year = {2015}"));
        assertThat(article.toBibTex(), containsString("title = {Ohjelmistotuotanto}"));
        assertThat(article.toBibTex(), containsString("journal = {Journal}"));
    }
    
    @Test
    public void citationKeyLoadTest() {
    Article article = new Article();
    article.addField("author", "Vihavainen, Arto");
    article.addField("year", "2015");
    article.addField("title", "Ohjelmistotuotanto");
    article.addField("journal", "Journal");
    
    assertEquals(article.getCitationKey(), null);

    article.generateCitationKeyWithSuffix("#");
    article.setCitationKey(article.getCitationKey());

    assertEquals(article.getCitationKey(), "viha2015ohje#");
    
    }
    
    @Test
    public void citationKeyTest() {
        Article article = new Article();

        article.addField("author", "Vihavainen, Arto");
        article.addField("year", "2015");
        article.addField("title", "Ohjelmistotuotanto");
        article.addField("journal", "Journal");

        assertEquals(article.getCitationKey(), null);

        article.generateCitationKeyWithSuffix("#");

        assertEquals(article.getCitationKey(), "viha2015ohje#");
    }
    
    @Test
    public void getRequiredFieldsTest() {
        Article article = new Article();
        String[] requirements = new String[]{"author", "title", "journal", "year"};

        assertArrayEquals(article.getRequiredFields(), requirements);
    }

    @Test
    public void getOptionalFieldsTest() {
        Article article = new Article();
        String[] requirements = new String[]{"volume", "number", "pages", "month", "note", "key"};

        assertArrayEquals(article.getOptionalFields(), requirements);
    }

}
