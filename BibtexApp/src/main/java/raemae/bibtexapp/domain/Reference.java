
package raemae.bibtexapp.domain;


public interface Reference {
    
    public String[] getRequiredFields();
    public String[] getOptionalFields();
    public void setCitationKey(String suffix);
    public String getCitationKey();
    public void addField(String key, String value);
    public String getField(String key);
    public String toBibTex();
    public void loadCitationKey(String key);
    
}
