import edu.duke.*;
import java.io.File;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        // Make everything in a consistent case
        boolean lowerCase = dna.equals(dna.toLowerCase());
        if(lowerCase){
            stopCodon = stopCodon.toLowerCase();
        }
        else {
            dna = dna.toUpperCase();
            stopCodon = stopCodon.toUpperCase();
        }
       
        // Search for stop codon that is a multiple of 3 away from startIndex
        int finished = 0;
        int possStopIndex = -1;
        int currIndex = startIndex;
        
        int stopIndex = -1;
        
        while (finished == 0){
            // Find the index of occurrence of the stop codon
            possStopIndex = dna.indexOf(stopCodon, currIndex);
            // If no occurrence is found, break out of the loop
            if (possStopIndex == -1){return dna.length();}
            // If an occurrence is found, check if it starts at a multiple of 3 away
            // from the startIndex
            if ((possStopIndex - startIndex) % 3 == 0){
                stopIndex = possStopIndex;
                finished = 1;
            }
            else {
                // If the occurrence does not start at a multiple of 3 away from the 
                // startIndex, search again for the stop codon, starting at 1 past the
                // index of the stop codon 
                currIndex = possStopIndex + 1;
            }
        }
        
        // If the stop codon was observed, but not a multiple of 3 away from the 
        // startindex, return dna.length(); if it was found a multiple of 3 away
        // from startIndex, return the index of the stop codon
        if (stopIndex == -1){
            return dna.length();
        }
        else {
            return stopIndex;
        }
    }
    
    public void testFindStopCodon(){
        String stopCodon = "TAA";
        String dna = "XXATGXXXYYYXXXTAA";
        int startIndex = 2;
        
        int stopIndex = findStopCodon(dna, startIndex, stopCodon);
        System.out.println("");
        System.out.println("Checking DNA strand: " + dna);
        System.out.println("..Stop codon: " + stopCodon);        
        System.out.println("..Start index: " + startIndex);
        if (stopIndex == dna.length()){System.out.println("..No stop codon found");}
        else {System.out.println("..stopIndex: " + stopIndex);}
        
        //
        dna = "XXATGXXXYYYXXXYTAA";
        stopIndex = findStopCodon(dna, startIndex, stopCodon);
        System.out.println("");
        System.out.println("Checking DNA strand: " + dna);
        System.out.println("..Stop codon: " + stopCodon);        
        System.out.println("..Start index: " + startIndex);
        if (stopIndex == dna.length()){System.out.println("..No stop codon found");}
        else {System.out.println("..stopIndex: " + stopIndex);}        
        
        dna = "XXATGXXXYYYXXXTAG";
        System.out.println("");
        System.out.println("Checking DNA strand: " + dna);
        System.out.println("..Stop codon: " + stopCodon);        
        stopIndex = findStopCodon(dna, startIndex, stopCodon);
        System.out.println("..Start index: " + startIndex);
        if (stopIndex == dna.length()){System.out.println("..No stop codon found");}
        else {System.out.println("..stopIndex: " + stopIndex);}            
    }
    
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        // If there is no ATG, there is no gene to be returned, so return an empty string
        if (startIndex == -1){return "";}
        
        int taaStopIndex = findStopCodon(dna, startIndex, "TAA");
        int tagStopIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaStopIndex = findStopCodon(dna, startIndex, "TGA");
        
        int tmpMinStopIndex = -1;
        if (taaStopIndex != -1 && tagStopIndex != -1){
            tmpMinStopIndex = Math.min(taaStopIndex, tagStopIndex);
        }
        else if (taaStopIndex != -1 || tagStopIndex != -1){
            tmpMinStopIndex = Math.max(taaStopIndex, tagStopIndex);
        }

        int minStopIndex = -1;
        if (tmpMinStopIndex != -1 && tgaStopIndex != -1){
            minStopIndex = Math.min(tmpMinStopIndex, tgaStopIndex);
        }
        else if (tmpMinStopIndex != -1 || tgaStopIndex != -1){
            minStopIndex = Math.max(tmpMinStopIndex, tgaStopIndex);
        }

        int stopCodonIndex = minStopIndex;
        
        
        // If there are no stop codons found return the empty string
        if (stopCodonIndex == -1 || stopCodonIndex == dna.length()) {return "";}
        // Else return the gene
        return dna.substring(startIndex, stopCodonIndex + 3);        
    }
    
    public void printAllGenes(String dna){
        int startIndex = dna.indexOf("ATG");
        System.out.println("Searching for genes in DNA strand: " + dna);
        while (startIndex != -1){
            System.out.println("..startIndex = " + startIndex);
            int taaStopIndex = findStopCodon(dna, startIndex, "TAA");
            int tagStopIndex = findStopCodon(dna, startIndex, "TAG");
            int tgaStopIndex = findStopCodon(dna, startIndex, "TGA");
            
            taaStopIndex = Math.max(taaStopIndex, -1);
            tagStopIndex = Math.max(tagStopIndex, -1);
            tgaStopIndex = Math.max(tgaStopIndex, -1);
            
            int minIndex = Math.min(taaStopIndex, Math.min(tagStopIndex, tgaStopIndex));
            
            if (minIndex == -1 || minIndex == dna.length()){System.out.println("There is no stop codon"); break;}

            String gene = dna.substring(startIndex, minIndex + 3);
            System.out.println("..Found a gene: " + gene);
            startIndex = dna.indexOf("ATG",minIndex + 4);
        }
    }
    
    
    public void testFindGene(){
        String dna = "XXATGXXXYYYXXXTAA";
        System.out.println("");
        System.out.println("Checking DNA strand: " + dna);        
        String gene = findGene(dna);
        if (gene.isEmpty()){System.out.println("..No gene found");}
        else {System.out.println("..Gene: " + gene);}

        
        dna = "XXATGXXXYYYXXXTAG";
        System.out.println("");
        System.out.println("Checking DNA strand: " + dna);        
        gene = findGene(dna);
        if (gene.isEmpty()){System.out.println("..No gene found");}
        else {System.out.println("..Gene: " + gene);}
        
        dna = "XXATGXXXYYYTGATAG";
        System.out.println("");
        System.out.println("Checking DNA strand: " + dna);        
        gene = findGene(dna);
        if (gene.isEmpty()){System.out.println("..No gene found");}
        else {System.out.println("..Gene: " + gene);}
        
        
        dna = "XXXZZATGXXXYYYXXXTAA";
        System.out.println("");
        System.out.println("Checking DNA strand: " + dna);        
        gene = findGene(dna);
        if (gene.isEmpty()){System.out.println("..No gene found");}
        else {System.out.println("..Gene: " + gene);}
        
 
        dna = "XXATPXXXYYYXXXYTAA";
        System.out.println("");
        System.out.println("Checking DNA strand: " + dna);        
        gene = findGene(dna);
        if (gene.isEmpty()){System.out.println("..No gene found");}
        else {System.out.println("..Gene: " + gene);} 
        
        dna = "XXSJNAATGXXXYYYXXXYTAS";        
        System.out.println("");
        System.out.println("Checking DNA strand: " + dna);        
        gene = findGene(dna);
        if (gene.isEmpty()){System.out.println("..No gene found");}
        else {System.out.println("..Gene: " + gene);}        
    }
}
