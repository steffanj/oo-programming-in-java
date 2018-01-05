/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        String startCodon = "ATG";
        int startLocation = dna.indexOf(startCodon);
        System.out.println("startLocation: " + startLocation);        
        String emptyString = ""; 
        if(startLocation == -1){
            return emptyString;
        }
        
        String stopCodon = "TAA";
        int stopLocation = dna.indexOf(stopCodon, startLocation+3);
        System.out.println("stopLocation: " + stopLocation);        
        if(stopLocation == -1){
            return emptyString;
        }
        String simpleGene = dna.substring(startLocation, stopLocation+3);
        System.out.println("substring: " + simpleGene);        
        if(simpleGene.length() % 3 != 0){
            return emptyString;
        }
        
        return simpleGene;
    }
    
    public void testSimpleGene(){
        String strand1 = "GATCTACGCTAA";
        System.out.println("Testing DNA strand " + strand1 + "...");
        String simpleGene = findSimpleGene(strand1);
        System.out.println("Gene found: " + simpleGene);

        String strand2 = "ATGCTAGCDOEDAS";
        System.out.println("Testing DNA strand " + strand2 + "...");   
        simpleGene = findSimpleGene(strand2);
        System.out.println("Gene found: " + simpleGene);        
        
        String strand3 = "DITISEENNEPPE";
        System.out.println("Testing DNA strand " + strand3 + "...");           
        simpleGene = findSimpleGene(strand3);
        System.out.println("Gene found: " + simpleGene);        
        
        String strand4 = "ATGNIETOKETAA";
        System.out.println("Testing DNA strand " + strand4 + "...");           
        simpleGene = findSimpleGene(strand4);
        System.out.println("Gene found: " + simpleGene);          
        
        String strand5 = "ATGOKEOKEOKETAA";
        System.out.println("Testing DNA strand " + strand5 + "...");           
        simpleGene = findSimpleGene(strand5);
        System.out.println("Gene found: " + simpleGene);     
        
        String quiz1 = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("Testing DNA strand " + quiz1 + "...");           
        simpleGene = findSimpleGene(quiz1);
        System.out.println("Gene found: " + simpleGene);         
    }
}
