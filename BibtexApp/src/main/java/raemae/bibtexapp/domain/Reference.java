
package raemae.bibtexapp.domain;


public interface Reference {
    
    public String getType();
    public String[] getRequiredFields();
    public String[] getOptionalFields();
    public void generateCitationKeyWithSuffix(String suffix);
    public String getCitationKey();
    public void addField(String key, String value);
    public String getField(String key);
    public String toBibTex();
    public void setCitationKey(String key);    
    
}
