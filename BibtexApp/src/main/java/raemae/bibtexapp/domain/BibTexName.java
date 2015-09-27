/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raemae.bibtexapp.domain;

/**
 *
 * @author Markku
 */
public class BibTexName {
    
    private String first;
    private String last;
    private String von;
    private String junior;
    
    public void setFirst(String n) {
        first=n;
    }
    
    public void setLast(String n) {
        last=n;
    }
    
    public void setVon(String n) {
        von=n;
    }
    
    public void setJunior(String n) {
        junior=n;
    }
    
    public String getFirst() {
        return first;
    }
    
    public String getLast() {
        return last;
    }
    
    public String getVon() {
        return von;
    }
    
    public String getJunior() {
        return junior;
    }
    
    
    
}
