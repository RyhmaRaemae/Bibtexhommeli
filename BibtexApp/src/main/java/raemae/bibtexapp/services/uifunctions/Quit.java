
package raemae.bibtexapp.services.uifunctions;

import raemae.bibtexapp.services.uifunctions.TextUIFunction;
import raemae.bibtexapp.ui.TextUI;


public class Quit extends TextUIFunction {
    
    private TextUI ui;

    public Quit(TextUI ui) {
        super(999);
        this.ui = ui;
    }

    @Override
    public void execute() {
        ui.stop();
     }

    @Override
    public String getMenuName() {
        return "quit";
    }

    @Override
    public String getMenuDescription() {
        return "Exits the application";
    }

}
