
package raemae.bibtexapp.services.uifunctions;

import static org.fusesource.jansi.Ansi.*;
import org.fusesource.jansi.AnsiConsole;
import raemae.bibtexapp.ui.IO;

public class ChangeTextColor extends TextUIFunction {
    private static final String[] colors = {"BLACK", "RED", "GREEN", "YELLOW",
                                            "BLUE", "MAGENTA", "CYAN", "WHITE",
                                            "DEFAULT"};
    
    private IO io;
    
    public ChangeTextColor(IO io) {
        super(8);
        this.io = io;
    }
    
    public static void initiate(){
        AnsiConsole.systemInstall();
    }
    
    public static void end() {
        AnsiConsole.systemUninstall();
    }
    
    public static String reset() {
        return ansi().reset().a("The color of the text has been reset.").toString();
    }
    
    public static String setColor(String color) {
        return ansi().fg(Color.valueOf(color)).a("The color of the text has been set to " + color +".").toString();
    }

    @Override
    public void execute() {
        io.print("");
        
        io.print("The color options for the text are:");
        for (int i = 0; i < colors.length; i++) {
            io.print(i + " - " + colors[i]);
        }
        io.print("Type the number of the color you would like to use.");
        io.print("Any other character will allow you to keep the current color.");
        
        try {
            int selection = io.readInt("> ");
            String color = colors[selection];
            io.print(setColor(color));
        } catch(Exception e) {
            io.print("The color wasn't changed.");
        }
    }

    @Override
    public String getMenuName() {
        return "color";
    }

    @Override
    public String getMenuDescription() {
        return "Change the color of the text";
    }
    
    
}
