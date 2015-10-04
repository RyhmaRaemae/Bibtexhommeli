
package raemae.bibtexapp.ui;

import java.util.Collections;
import java.util.List;


public class TextUI {
    
    private List<TextUIFunction> functions;
    private boolean running;
    private IO io;
    
    public TextUI(List<TextUIFunction> functions, IO io) {
        this.functions = functions;
        this.io = io;
        running = false;
        functions.add(new Quit(this));
        
    }
    
    public void run() {
        Collections.sort(functions);
        running = true;
        while (running) {
            listMenuOptions();
            
            String command = io.readLine("> ");
            TextUIFunction f = getFunctionByName(command);
            
            if (f == null) {
                io.print("Invalid command.");
            }
            else {
                f.execute();
            }
            
        }
        io.print("");
    }
    
    private void listMenuOptions() {
        io.print("");
        io.print("Please choose from one of the following options:\n");
        
        for (TextUIFunction f : functions) {
            io.print(f.getMenuName() + " - " + f.getMenuDescription());
        }
    }
    
    public void stop() {
        running = false;
    }
    
    private TextUIFunction getFunctionByName(String name) {
        
        for (TextUIFunction f : functions) {
            if (f.getMenuName().toLowerCase().equals(name.toLowerCase().trim())) {
                return f;
            }
        }
        return null;
    }
    
    
    
    

}
