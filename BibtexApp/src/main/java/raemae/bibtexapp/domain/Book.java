/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raemae.bibtexapp.domain;

import java.util.List;

/**
 *
 * @author Markku
 */
public class Book {
    
    // Pakolliset kentät
    // author ja editor ovat vaihtoehtoisia, yksi niistä on pakollinen
    private List<BibTexName> author;
    private List<BibTexName> editor;
    private String title;
    private String publisher;
    
    
    // Valinnaiset kentät
    private String year;
    private String volume;
    private String number;
    private String series;
    private String address;
    private String edition;
    private String month;
    private String note;

    /**
     * @return the author
     */
    public List<BibTexName> getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(List<BibTexName> author) {
        this.author = author;
    }

    /**
     * @return the editor
     */
    public List<BibTexName> getEditor() {
        return editor;
    }

    /**
     * @param editor the editor to set
     */
    public void setEditor(List<BibTexName> editor) {
        this.editor = editor;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * @param publisher the publisher to set
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the volume
     */
    public String getVolume() {
        return volume;
    }

    /**
     * @param volume the volume to set
     */
    public void setVolume(String volume) {
        this.volume = volume;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return the series
     */
    public String getSeries() {
        return series;
    }

    /**
     * @param series the series to set
     */
    public void setSeries(String series) {
        this.series = series;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the edition
     */
    public String getEdition() {
        return edition;
    }

    /**
     * @param edition the edition to set
     */
    public void setEdition(String edition) {
        this.edition = edition;
    }

    /**
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }
    
    
    
}
