
package researchSupport;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class is responsible for getting Paper details from the keyboard
 * YOU NEED TO CHANGE THE inputPaperDetails() method
 */
public class PaperTUI {
    /**
     * Gets paper name and authors. Needs completing
     * 
     * @return Should return a Paper object containing the details input. 
     *          You need to change this method
     * @throws IOException
     */
   
    public Paper inputPaperDetails() throws IOException {
        String paperName = inputPaperName();
        addAuthors();            //You will probably need to change this
        Paper newPaper = new Paper(paperName);  // You need to change this is match your
        // version of Paper
        return newPaper;
   }
    
  
    /**
     * Gets the paper's title from the keyboard
     * 
     * @return The string as input. No validation is done
     * @throws IOException 
     */
    public String inputPaperName() throws IOException {
        String paperName;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter paper's title: ");
        paperName = scan.nextLine();
        return paperName;
    }
    
    /**
     * Gets the names of paper's authors, up to a maximum of 6. 
     * 
     * @return Returns an array holding the authors' names
     * @throws IOException 
     */
    public String[] addAuthors() throws IOException {
        final int MAX = 6;
        String input;
        String[] authorsNames = new String[MAX];
        Scanner scan = new Scanner(System.in);
        boolean done = false;
        int i = 0;

        while (!done) {
            System.out.println(
                    "Enter author's name or " + "\\" + " to end (to a max of 6): ");
            input = scan.nextLine();
            if (!input.equals("\\")){
                    authorsNames[i++] = input;
            }else{
                done = true;
            }
            if (i>=MAX) done = true;
        }
        return authorsNames;
    }    
}
