
package researchSupport;

import java.util.Scanner;
import java.io.*;

/**
 * Provides a text-based user interface for the ResearchSupport
 * application. Uses a supporting text interface class - PaperTUI - to get details
 * about individual papers./*
 * YOU NEED TO CHANGE A LINE IN LoadData(). See below
 */

public class ResearchSupportUserInterface {

    ResearchSupportApp RSA = new ResearchSupportApp();
    final String RESOURCE_LOCATION = "/researchSupport/resources/";
    PaperTUI pTUI = new PaperTUI();

    /**
     * Displays the main user menu and processes input There are 3 menu options.
     * The first option is to load data. The load data method is called
     * directly. The other two options display new menus.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ResearchSupportUserInterface rSUI = new ResearchSupportUserInterface();
        Scanner scan = new Scanner(System.in);
        char mChoice;
        String selection;

        rSUI.menu();
        selection = scan.nextLine().toUpperCase();
        mChoice = selection.charAt(0);
        while (mChoice != 'X') {
            switch (mChoice) {
                case 'L': {
                    rSUI.loadData();
                    break;
                }
                case 'I': {
                    rSUI.addMenu();
                    rSUI.processAddMenu();
                    break;
                }
                case 'Q': {
                    rSUI.queryMenu();
                    rSUI.processQueryMenu();
                    break;
                }
                default: {
                    System.out.println("\nInvalid choice. Try again\n");
                }
            }
            rSUI.menu();
            selection = scan.nextLine().toUpperCase();
            mChoice = selection.charAt(0);
        }
    }

    /**
     * Displays the top level menu
     */
    private void menu() {
        System.out.println("\nPAPER REPOSITORY MENU\n");
        System.out.println("L\tLoad Data");
        System.out.println("I\tInput Details");
        System.out.println("Q\tMake a Query\n");

        System.out.println("X\tEXIT\n");

        System.out.println("Enter menu choice, X to exit: ");
    }

    /**
     * Displays the menu that allows additional input to the ResearchPaper store
     */
    private void addMenu() {
        System.out.println("\nPAPER REPOSITORY INPUT MENU\n");
        System.out.println("A\tAdd a paper to the repository");
        System.out.println("B\tMake a paper a reference");

        System.out.println("X\tEXIT INPUT\n");

        System.out.println("Enter menu choice, X to exit: ");
    }

    /**
     * Displays the query menu
     */
    private void queryMenu() {
        System.out.println("\nRESEARCH SUPPORT QUERY MENU\n");
        System.out.println("K\tList paper details");
        System.out.println("L\tList direct citations");
        System.out.println("M\tList direct referneces");
        System.out.println("N\tList all citation chains up to the root");
        System.out.println("O\tList all reference chains to the lowest level");
        System.out.println("T\tList n-levels of citation");
        System.out.println("U\tList n-levels of references\n");

        System.out.println("X\tEXIT QUERY\n");

        System.out.println("Enter menu choice, X to exit: ");
    }

    /**
     * Processes input to the query menu and calls methods from ResearchSupportApp
     *
     * @throws IOException
     */
    private void processQueryMenu() throws IOException {
        Scanner scan = new Scanner(System.in);
        String selection, name;
        Paper aPaper;
        char qChoice;
        int numOfGens, id;

        selection = scan.nextLine().toUpperCase();
        qChoice = selection.charAt(0);
        while (qChoice != 'X') {
            switch (qChoice) {
                case 'K':
                    RSA.listPaperDetails(pTUI.inputPaperName());
                    break;
                case 'L':
                    RSA.listDirectCitations(pTUI.inputPaperName());
                    break;
                case 'M':
                    RSA.listDirectReferences(pTUI.inputPaperName());
                    break;
                case 'N':
                    RSA.listAllCitationChains();
                    break;
                case 'O':
                    RSA.listAllReferenceChains();
                    break;

                case 'T':
                    name = pTUI.inputPaperName();
                    System.out.println("Now enter - number of levels required : ");
                    numOfGens = scan.nextInt();
                    scan.nextLine();
                    if(numOfGens < 5 && numOfGens > 0){
                    RSA.listNCitations(name, numOfGens);
                    }else{
                        System.out.println("Number must be between 1-4");
                    }
                    break;
                case 'U':
                    name = pTUI.inputPaperName();
                    System.out.println("Now enter - number of levels required : ");
                    numOfGens = scan.nextInt();
                    scan.nextLine();
                    RSA.listNReferences(name, numOfGens);
                    break;
            }
            queryMenu();
            selection = scan.nextLine().toUpperCase();
            qChoice = selection.charAt(0);
        }
    }

    /**
     * Processes input to the add menu
     *
     * @throws IOException
     */
    private void processAddMenu() throws IOException {
        Scanner scan = new Scanner(System.in);
        Paper aPaper;
        String aPaperName, refPaperName;
        String selection;
        char iChoice;

        selection = scan.nextLine().toUpperCase();
        iChoice = selection.charAt(0);
        while (iChoice != 'X') {
            switch (iChoice) {
                case 'A':
                    aPaper = pTUI.inputPaperDetails();
                    RSA.addPaper(aPaper);
                    break;
                case 'B':
                    aPaperName = pTUI.inputPaperName();
                    refPaperName = pTUI.inputPaperName();
                    RSA.makeLinkToReference(aPaperName,
                            refPaperName);
                    break;
                default:
                    System.out.println("\nInvalid input choice. Try again\n");
                //do nothing
            }
            addMenu();
            selection = scan.nextLine().toUpperCase();
            iChoice = selection.charAt(0);
        }
    }

    /**
     * Loads data from input files Papers.txt and References.txt
     * This method is supplied for you to simplify testing and for the
     * purposes of the demo. New versions of these files will be supplied
     * 2 weeks before the assignment hand-in date.
     * YOU NEED TO CHANGE LINE THE CALL TO THE Paper CONSTRUCTOR TO MATCH YOUR 
     * DEFINITION OF Paper.
     * 
     * @throws IOException 
     */
    public void loadData() throws IOException {

        final int MAX = 6;
        Scanner pFile, rFile, lineScan;
        String entry, author[] = new String[MAX], paperName = null;
        String  refName;
        int i;

        // Set up Papers.txt 
        InputStream instream = getClass().
                getResourceAsStream(RESOURCE_LOCATION + "Papers.txt");
        pFile = new Scanner(instream);

        // Read each entry in pFile
        while (pFile.hasNext()) {
            // Read paper title and author(s)
            entry = pFile.nextLine();
            lineScan = new Scanner(entry).useDelimiter("<SEP>");
            if (lineScan.hasNext()) {
                paperName = lineScan.next(); // Get paper name
            }
            
            // Get author(s), making sure that the array is cleared of old data
            i=0;
            while ((i < MAX) && (lineScan.hasNext())) {
                author[i++] = lineScan.next();
            }
            for (i=i; i<MAX; i++) author[i]=null;
            // Create a new instance of Paper and add it to the list
            if ((paperName != null)) {
                Paper aPaper = new Paper(paperName);  // Change this to match your Paper class
                RSA.addPaper(aPaper);
                System.out.println(paperName + " added");
            }
        }

        // Set up references file
        instream = getClass().getResourceAsStream(RESOURCE_LOCATION
                + "References.txt");
        rFile = new Scanner(instream);

        // Read each entry in the file
        while (rFile.hasNext()) {
            entry = rFile.nextLine();

            // Parse each entry
            lineScan = new Scanner(entry).useDelimiter("<SEP>");
            paperName = lineScan.next();
            refName = lineScan.next();

            if ((paperName != null) && (refName != null)) {
                RSA.makeLinkToReference(paperName, refName);
                System.out.println(paperName + " references" + refName);
            }
        }
    }
}
