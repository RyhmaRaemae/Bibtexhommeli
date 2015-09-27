
package raemae.bibtexapp.ui;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class TextUI {
    
    private List<TextUIFunction> functions;
    private boolean running;
    private Scanner scanner;
    
    public TextUI(List<TextUIFunction> functions) {
        this.functions = functions;
        running = false;
        scanner = new Scanner(System.in);
        functions.add(new Quit(this));
    }
    
    public void run() {
        Collections.sort(functions);
        running = true;
        while (running) {
            listMenuOptions();
            
            String command = scanner.nextLine();
            TextUIFunction f = getFunctionByName(command);
            
            if (f == null) {
                System.out.println("Command not recognized.");
            }
            else {
                f.execute();
            }
            
        }
        System.out.println("");
    }
    
    private void listMenuOptions() {
        System.out.println("");
        System.out.println("Please choose from one of the following options:\n");
        
        for (TextUIFunction f : functions) {
            System.out.println(f.getMenuName() + " - " + f.getMenuDescription());
        }
        System.out.print("> ");
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
