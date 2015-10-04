package raemae.bibtexapp.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.internal.matchers.StringContains.containsString;

public class InProceedingsTest {

    @Test
    public void InProceedingsDataTest() {
        InProceedings inproceedings = new InProceedings();

        inproceedings.addField("author", "Vihavainen, Arto");
        inproceedings.addField("year", "2015");
        inproceedings.addField("title", "Ohjelmistotuotanto");
        inproceedings.addField("booktitle", "BookyBook");

        assertEquals(inproceedings.getField("author"), "Vihavainen, Arto");
        assertEquals(inproceedings.getField("year"), "2015");
        assertEquals(inproceedings.getField("title"), "Ohjelmistotuotanto");
        assertEquals(inproceedings.getField("booktitle"), "BookyBook");
    }

    @Test
    public void addFieldTest() {
        InProceedings inproceedings = new InProceedings();
        InProceedings inproceedings2 = new InProceedings();
        inproceedings.addField("author", "Vihavainen");
        inproceedings2.addField("author", "Vihavainen");
        assertEquals(inproceedings.getField("author"), inproceedings2.getField("author"));
        inproceedings2.addField("author", "Someoneelse");
        assertThat(inproceedings.getField("author"), is(not(inproceedings2.getField("author"))));

    }

    @Test
    public void toBibTexTest() {
        InProceedings inproceedings = new InProceedings();

        inproceedings.addField("author", "Vihavainen, Arto");
        inproceedings.addField("year", "2015");
        inproceedings.addField("title", "Ohjelmistotuotanto");
        inproceedings.addField("booktitle", "BookyBook");

        assertThat(inproceedings.toBibTex(), containsString("author = {Vihavainen, Arto}"));
        assertThat(inproceedings.toBibTex(), containsString("year = {2015}"));
        assertThat(inproceedings.toBibTex(), containsString("title = {Ohjelmistotuotanto}"));
        assertThat(inproceedings.toBibTex(), containsString("booktitle = {BookyBook}"));
    }

    @Test
    public void citationKeyTest() {
        InProceedings inproceedings = new InProceedings();

        inproceedings.addField("author", "Vihavainen, Arto");
        inproceedings.addField("year", "2015");
        inproceedings.addField("title", "Ohjelmistotuotanto");
        inproceedings.addField("booktitle", "BookyBook");

        assertEquals(inproceedings.getCitationKey(), null);

        inproceedings.setCitationKey("#");

        assertEquals(inproceedings.getCitationKey(), "Viha2015Ohje#");
    }

    @Test
    public void getRequiredFieldsTest() {
        InProceedings inproceedings = new InProceedings();
        String[] requirements = new String[]{"author", "title", "booktitle", "year"};

        assertArrayEquals(inproceedings.getRequiredFields(), requirements);
    }

    @Test
    public void getOptionalFieldsTest() {
        InProceedings inproceedings = new InProceedings();
        String[] requirements = new String[]{"editor", "pages", "organization", "publisher", "address", "month", "note", "key"};

        assertArrayEquals(inproceedings.getOptionalFields(), requirements);
    }

}
