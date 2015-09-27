
package raemae.bibtexapp.ui;


public class StubUIFunction extends TextUIFunction {
    
    private String name;
    private String description;

    public StubUIFunction(int priority, String menuname, String menudescription) {
        super(priority);
        name = menuname;
        description = menudescription;
    }

    @Override
    public void execute() {        
    }

    @Override
    public String getMenuName() {
        return name;
        
    }

    @Override
    public String getMenuDescription() {
        return description;
    }

}
