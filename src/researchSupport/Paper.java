/*
 * Paper.java
 *
 * Created on 09 March 2010, 21:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package researchSupport;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Maintains details of an individual Paper.
 * YOU NEED TO COMPLETE THIS CLASS
 */
public class Paper {
    private static int nextIdNum=0;
    private int paperID;
    private String title ;
    private Set<String> authors = new HashSet<>();
    private List<String> referencedPapers = new ArrayList<>() ;
    private List<String> citedBy = new ArrayList<>();
   
 /** Creates a new instance of Paper */
    public Paper(String title)
    {
        this.title = title;
        this.paperID = nextIdNum;
        nextIdNum++;
    }
    
    public String getTitle(){
        return title;
    }
    
    public void addAuthor(String author){
        authors.add(author);
    }
    
    public void addReference(String title){
        referencedPapers.add(title);
    }
    
    public void addCitation(String title){
        citedBy.add(title);
    }
    
    public String[] getDirectReferences(){
        String[] returnArray = new String[referencedPapers.size()];
        return referencedPapers.toArray(returnArray);
    }
    
    public String[] getDirectCitations(){
        String[] returnArray = new String[referencedPapers.size()];
        return citedBy.toArray(returnArray);
    }
    
    public boolean checkIfRoot(){
        if(referencedPapers.isEmpty())return true;
        else return false;
    }
    
    public boolean checkIfLeaf(){
        if(citedBy.isEmpty())return true;
        else return false;
    }
    
    @Override 
    public String toString(){
        return "Not done yet";
    }

    
    
}
