package researchSupport;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.sound.midi.Soundbank;

/**
 * Maintains a collection of research papers. Provides functionality to add a
 * Paper to the collection, to link one Paper to another as a reference, to list
 * a Paper's references at various levels and to list citations at various
 * levels. YOU NEED TO COMPLETE THIS CLASS
 */
public class ResearchSupportApp {

    private Map<String, Paper> papers = new HashMap<>();

    public ResearchSupportApp() {
    }

    public void addPaper(Paper aPaper) {
        papers.put(aPaper.getTitle(), aPaper);
    }

    public void listPaperDetails(String title) {
    }

    public void makeLinkToReference(String aPaperName, String refPaperName) {
        for (Paper current : papers) {
            if (aPaperName.equals(current.getTitle())) {
                if(current.checkIfRoot() || )
                current.addReference(refPaperName);
            }else if(refPaperName.equals(current.getTitle())){
                current.addCitation(aPaperName);
            }
        }
    }

    public void listDirectReferences(String title) {
        Paper currentPaper = papers.get(title);
        for(String currentString : currentPaper.getDirectReferences()){
            System.out.println(currentString);
        }
        
    }

    public void listDirectCitations(String title) {
    }

    public void listAllReferenceChains(String title) {

    }

    public void listAllCitationChains() {
       
    }

    public void listNReferences(String title, int numberOfGenerations) {
        if (numberOfGenerations == 1) {
            listDirectReferences(title);
        } else if (numberOfGenerations > 1 && numberOfGenerations < 5) {
            Set<String> titleSet = new HashSet<>();
            titleSet.add(title);
            listNReferences(titleSet, numberOfGenerations);
        }
    }

    private void listNReferences(Set<String> refs, int iterationsRemaining) {
        Set<String> newRefs = new HashSet<>();
        System.out.println("References level: " + iterationsRemaining);
        for (String current : refs) {
            // Add all returned references to newRefs list for this level
            newRefs.addAll(Arrays.asList(papers.get(current).getDirectReferences()));
            // Print out all returned titles 
            listDirectReferences(current);                         
        }
        if(iterationsRemaining > 1)listNReferences(newRefs, iterationsRemaining -1);
    }

    public void listNCitations(String title, int numberOfGenerations) {
        if (numberOfGenerations == 1) {
            listDirectCitations(title);
        } else if (numberOfGenerations > 1 && numberOfGenerations < 5) {
            Set<String> titleSet = new HashSet<>();
            titleSet.add(title);
            listNCitations(titleSet, numberOfGenerations);
        }
    }
    
     private void listNCitations(Set<String> cites, int iterationsRemaining) {
          Set<String> newCites = new HashSet<>();
        System.out.println("Citations level: " + iterationsRemaining);
        for (String current : cites) {
            // Add all returned references to newRefs list for this level
            newCites.addAll(Arrays.asList(papers.get(current).getDirectCitations()));
            // Print out all returned titles 
            listDirectCitations(current);            
        }
        if(iterationsRemaining > 1)listNReferences(newCites, iterationsRemaining -1);
    }
    
}