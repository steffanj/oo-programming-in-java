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
            
            if (stopCodonIndex == -1 || stopCodonIndex == dna.length()){
                startIndex = dna.indexOf("ATG", startIndex + 1);
            }
            else {
                String gene = dna.substring(startIndex, stopCodonIndex + 3);
                System.out.println("..Found a gene: " + gene);
                startIndex = dna.indexOf("ATG",stopCodonIndex + 4);
            }
        }
    }
    
    public StorageResource getAllGenes(String dna){
        StorageResource storage = new StorageResource();        
        int startIndex = dna.indexOf("ATG");
        System.out.println("Searching for genes in DNA strand: " + dna);
        while (startIndex != -1){
            System.out.println("..startIndex = " + startIndex);
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
            
            if (stopCodonIndex == -1 || stopCodonIndex == dna.length()){
                startIndex = dna.indexOf("ATG", startIndex + 1);
            }
            else {
                String gene = dna.substring(startIndex, stopCodonIndex + 3);
                System.out.println("..Found a gene: " + gene);
                storage.add(gene);                
                startIndex = dna.indexOf("ATG",stopCodonIndex + 4);
            }
        }
        return storage;
    }      
    
    public void testGetAllGenes(){
        String dna = "XXATGXXXYYYTGATAGHUATGTAAIJHATGUIOPTAAHATGXXXYYYTAA";
        StorageResource storage = getAllGenes(dna);
        for(String gene : storage.data()){
            System.out.println(".." + gene);
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
    
    public float cgRatio(String dna){
        int cgCounter = 0;
        for (char nucleotide : dna.toCharArray()){
            if (nucleotide == 'C' || nucleotide == 'G'){
                cgCounter = cgCounter + 1;
            }
        }
        float cgRatio = (float) cgCounter / dna.length();
        return cgRatio;
    }
    
    public int countCTG(String dna){
        int countCTG = 0;
        int startFromIndex = 0;

        while (true){
            int ctgIndex = dna.indexOf("CTG", startFromIndex);
            //System.out.println("..ctgIndex: " + ctgIndex);
            if (ctgIndex == -1){break;}
            countCTG = countCTG + 1;
            startFromIndex = ctgIndex + 1;
        }
        return countCTG;
    }
    
    public void testStuff(){
        String dna = "CCATGUUUCCCTGATAGCUATGTAACTGGGGCCCTAACATGTTTCTGTAA";
        System.out.println("DNA strand: " + dna);
        System.out.println("CG ratio: " + cgRatio(dna));
        System.out.println("CTG count: " + countCTG(dna));
    }
    
    public void processGenes(StorageResource sr){
        int longerThanCounter = 0;
        int cgRatioHighCounter = 0;
        int longestGeneLength = 0;
        
        for (String gene : sr.data()){
            System.out.println("DNA strand: " + gene);
            if (gene.length() > 60){
                System.out.println("Longer than 60 chars: " + gene);
                longerThanCounter += 1;
            }
            
            if (cgRatio(gene) > 0.35){
                System.out.println("CG ratio: " + cgRatio(gene));
                cgRatioHighCounter += 1;
            }
            
            if (gene.length() > longestGeneLength){
                longestGeneLength = gene.length();
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("Strings >60 chars: " + longerThanCounter);
        System.out.println("Strings CG ratio >0.35: " + cgRatioHighCounter);
        System.out.println("Longest gene length: " + longestGeneLength + " chars");
        System.out.println("");
    }
    
    public void testProcessGenes(){
        // // Strand >9 chars
        // String dna = "ATGUUTTCCCTGTAA";
        // StorageResource sr = new StorageResource();
        // sr.add(dna);
        // // Strand <9 chars
        // dna = "ATGCCCTAA";
        // sr.add(dna);
        // // Strand CG ratio >0.35
        // dna = "ATGCCCGGGCCCTAA";
        // sr.add(dna);
        // // Strand CG ratio <0.35
        // dna = "ATGTTTAAATTTTAA";
        // sr.add(dna);
        // processGenes(sr);       
        
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        StorageResource sr = getAllGenes(dna);
        processGenes(sr);
        System.out.println("Number of genes: " + sr.size());
        System.out.println("Number of CTG codons: " + countCTG(dna));
    }
}
