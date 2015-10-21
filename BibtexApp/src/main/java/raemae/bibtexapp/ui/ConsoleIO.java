
package raemae.bibtexapp.ui;

import java.util.Scanner;


public class ConsoleIO implements IO {
    private Scanner scanner = new Scanner(System.in);
    
    @Override
    public void print(String toPrint) {
        System.out.println(toPrint);
    }

    @Override
    public int readInt(String prompt) {
        System.out.print(prompt+" ");
        System.out.flush();
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public String readLine(String prompt) {
        System.out.print(prompt+" ");
        System.out.flush();
        return scanner.nextLine();
    }
    
}