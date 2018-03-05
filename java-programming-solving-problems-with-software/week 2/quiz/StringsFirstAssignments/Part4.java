import edu.duke.*;
import java.io.File;

public class Part4 {
    public void findYoutubeLinks(){
        String ur = "C:/Users/steffan/Downloads/Computer Science Articles.htm";
        // String ur = "http://www.dukelearntoprogram.com/course2/data/manylinks.html";
        URLResource links = new URLResource(ur);
        String youtube = "youtube.com";
        for (String word : links.words()){
            int youtubeStart = word.toLowerCase().indexOf(youtube);
            if(youtubeStart!=-1){
                //System.out.println(youtubeStart);
                int quoteStart = word.lastIndexOf("\"", youtubeStart)+1;
                int youtubeEnd = youtubeStart + youtube.length();
                int quoteEnd = word.substring(youtubeEnd).indexOf("\"")+youtubeEnd;
                System.out.println("");
                System.out.println(word);
                //System.out.println(quoteStart);
                //System.out.println(quoteEnd);
                if(quoteStart>=0 && quoteEnd>=0){
                    System.out.println(word.substring(quoteStart, quoteEnd));
                }
        }
        }
    }
}
