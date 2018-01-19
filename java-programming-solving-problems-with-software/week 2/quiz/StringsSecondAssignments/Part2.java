import edu.duke.*;
import java.io.File;
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb){
        int startIndex = 0;
        int howManyCounter = 0;
        int finished = 0;
        
        while (finished == 0){
            int currIndex = stringb.indexOf(stringa, startIndex);
            if (currIndex != -1){
                howManyCounter = howManyCounter +1;
                startIndex = currIndex + stringa.length();
            }
            else{
                finished = 1;
            }
        }
 
        return howManyCounter;
    }
    
    public void testHowMany(){
        String stringa = "A";
        String stringb = "B";
        System.out.println("Stringa: " + stringa + ", stringb: " + stringb);
        System.out.println("Occurs " + howMany(stringa, stringb) + " times");

        stringa = "A";
        stringb = "AAAAA";
        System.out.println("Stringa: " + stringa + ", stringb: " + stringb);
        System.out.println("Occurs " + howMany(stringa, stringb) + " times");
        
        stringa = "AA";
        stringb = "AAAAAA";
        System.out.println("Stringa: " + stringa + ", stringb: " + stringb);
        System.out.println("Occurs " + howMany(stringa, stringb) + " times");      
        
        stringa = "haha";
        stringb = "hahaha";
        System.out.println("Stringa: " + stringa + ", stringb: " + stringb);
        System.out.println("Occurs " + howMany(stringa, stringb) + " times");
    }
}
