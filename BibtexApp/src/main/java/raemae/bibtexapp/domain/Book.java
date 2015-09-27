/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raemae.bibtexapp.domain;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Markku
 */
public class Book {
       

    Map<String, String> fields;
    
    public Book() {
        this.fields=new HashMap<String, String>();
    }
    
    public void addField(String key, String value) {
        this.fields.put(key, value);
    }
    
    public String getField(String key) {
        return this.fields.get(key);
    }
    
    public String toBibTex() {
        String result="@book{";
        String citationKey=this.getField("author").substring(0,4) + this.getField("year").substring(0,4)
                + this.getField("title").substring(0,4);
        result+=citationKey;
        for(String key : this.fields.keySet()) {
            result+=",\n";
            result+=key + " = {" + this.fields.get(key) + "}";
        }
        result+="\n}";
        return result;
    }
    
    
}
