package raemae.bibtexapp;

import java.util.ArrayList;
import java.util.List;
import raemae.bibtexapp.ui.TextUI;
import raemae.bibtexapp.ui.TextUIFunction;

public class Main {

    public static void main(String[] args) {
        List<TextUIFunction> l = new ArrayList<TextUIFunction>();
        TextUI ui = new TextUI(l);
        ui.run();
    }
    
}
