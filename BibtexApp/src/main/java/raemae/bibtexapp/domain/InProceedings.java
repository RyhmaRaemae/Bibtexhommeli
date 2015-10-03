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
public class InProceedings implements Reference {
private String citationKey;
    Map<String, String> fields;
    private String[] requiredFields;
    private String[] optionalFields;

    public InProceedings() {
        this.fields = new HashMap<String, String>();
        this.requiredFields = new String[]{"author", "title", "booktitle", "year"};
        this.optionalFields = new String[]{"editor", "pages", "organization", "publisher", "address", "month", "note", "key"};
    }

    public String[] getRequiredFields() {
        return requiredFields;
    }

    public String[] getOptionalFields() {
        return optionalFields;
    }

    public void addField(String key, String value) {
        this.fields.put(key, value);
    }

    public String getField(String key) {
        return this.fields.get(key);
    }

    public String toBibTex() {
        String result = "@inproceedings{" + citationKey;
        for (String key : this.fields.keySet()) {
            result += ",\n";
            result += key + " = {" + this.fields.get(key) + "}";
        }
        result += "\n}";
        return result;
    }

    @Override
    public void setCitationKey(String suffix) {
        citationKey = this.getField("author").substring(0, 4) + this.getField("year").substring(0, 4)
                + this.getField("title").substring(0, 4) + suffix;
    }

    @Override
    public String getCitationKey() {
        return citationKey;
    }

}