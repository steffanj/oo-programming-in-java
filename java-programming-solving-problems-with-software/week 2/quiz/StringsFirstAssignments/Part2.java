
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        boolean lowerCase = dna.equals(dna.toLowerCase());
        if(lowerCase){
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }
        else {
            dna = dna.toUpperCase();
            startCodon = startCodon.toUpperCase();
            stopCodon = stopCodon.toUpperCase();
        }
        
        System.out.println("Checking DNA strand: " + dna);
        System.out.println("..Start codon: " + startCodon);
        System.out.println("..Stop codon: " + stopCodon);
                
        int startLocation = dna.indexOf(startCodon);
        String emptyString = ""; 
        if(startLocation == -1){
            System.out.println("..Discovered gene: no gene");
            return emptyString;
        }
        
        int stopLocation = dna.indexOf(stopCodon, startLocation+3);
        if(stopLocation == -1){
            System.out.println("..Discovered gene: no gene") ;           
            return emptyString;
        }
        String simpleGene = dna.substring(startLocation, stopLocation+3);
        if(simpleGene.length() % 3 != 0){
            System.out.println("..Discovered gene: no gene");            
            return emptyString;
        }
        System.out.println("..Discovered gene: " + simpleGene);                    
        return simpleGene;
    }
    
    public void testSimpleGene(){
        String startCodon = "ATG";
        String stopCodon = "TAA";
        
        String strand1 = "GATCTACGCTAA";
        String simpleGene = findSimpleGene(strand1, startCodon, stopCodon);

        String strand2 = "ATGCTAGCDOEDAS";
        simpleGene = findSimpleGene(strand2, startCodon, stopCodon);
        
        String strand3 = "DITISEENNEPPE";
        simpleGene = findSimpleGene(strand3, startCodon, stopCodon);
       
        String strand4 = "ATGNIETOKETAA";
        simpleGene = findSimpleGene(strand4, startCodon, stopCodon);
        
        String strand5 = "atgokeokeditisgoedtaa";
        simpleGene = findSimpleGene(strand5, startCodon, stopCodon);
    }
}