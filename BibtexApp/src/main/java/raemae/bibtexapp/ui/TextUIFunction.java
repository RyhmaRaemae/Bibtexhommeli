
package raemae.bibtexapp.ui;


public abstract class TextUIFunction implements Comparable<TextUIFunction> {
    
    private int menuPriority;
    
    public TextUIFunction(int priority) {
        menuPriority = priority;
    }
    
    public int getMenuPriority() {
        return menuPriority;
    }
            
    public abstract void execute();
    public abstract String getMenuName();
    public abstract String getMenuDescription();
    
    @Override
    public int compareTo(TextUIFunction o) {
        return this.menuPriority - o.getMenuPriority();
    }
    
}
