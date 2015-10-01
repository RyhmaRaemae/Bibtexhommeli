
package raemae.bibtexapp.domain;


public interface Reference {
    
    public String[] getRequiredFields();
    public String[] getOptionalFields();
    public void addField(String key, String value);
    public String getField(String key);
    public String toBibTex();
    
}
