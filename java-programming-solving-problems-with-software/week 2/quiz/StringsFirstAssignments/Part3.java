
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb){
        int counter = 0;
        int stringLocation;
        stringLocation = stringb.indexOf(stringa);
        
        if(stringLocation == -1){
            return false;
        }
        stringLocation = stringb.indexOf(stringa, stringLocation+1);
        if(stringLocation == -1){
            return false;
        }
        else {
            return true;
        }
            
    }
    
    public String lastPart(String stringa, String stringb){
        int startLocation = stringb.indexOf(stringa);
        if(startLocation == -1){
            return stringb;
        }
        String lastPart = stringb.substring(startLocation + stringa.length());
        return lastPart;
    }
    
    
    void testTwoOccurrences(){
        String stringa = "haha";
        String stringb = "hahaha";
        boolean twoOccurrences = twoOccurrences(stringa, stringb);
        System.out.println("Finding string "+stringa+" in string "+stringb);
        System.out.println("..String occurred at least twice: " + twoOccurrences);
    }
    
    void testLastPart(){
        String stringa = "ha";
        String stringb = "hahaha";
        String lastPart = lastPart(stringa, stringb);
        System.out.println("Finding substring after "+stringa+" in "+stringb);
        System.out.println("..Substring: "+lastPart);
    }
}
