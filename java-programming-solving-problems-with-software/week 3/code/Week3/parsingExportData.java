import edu.duke.*;
import java.io.File;
import org.apache.commons.csv.*;

public class parsingExportData {
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        // String country = "Nauru";
        // String countryInfoString = countryInfo(parser, country);
        // System.out.println(countryInfoString);
        
        // // Reset the parser
        // parser = fr.getCSVParser();
        
        // String exportItem1 = "fish";
        // String exportItem2 = "nuts";
        // listExportersTwoProducts(parser, exportItem1, exportItem2); 
        
        // // Reset the parser
        // parser = fr.getCSVParser();
        // String exportItem = "gold";
        // Integer numberOfExportingCountries = numberOfExporters(parser, exportItem);
        // System.out.println(numberOfExportingCountries);
        
        // Reset the parser
        parser = fr.getCSVParser();
        String amount = "$999,999,999,999";
        bigExporters(parser, amount); 
        
    }
    
    public String countryInfo(CSVParser parser, String country){
        for (CSVRecord record : parser) {
            String country_rec = record.get("Country");
            if (country_rec.equals(country)) {
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                return country + ": " + exports + ": " + value;
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1,
        String exportItem2) {
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)){
                System.out.println(record.get("Country"));
            } 
        }
    }
    
    public Integer numberOfExporters(CSVParser parser, String exportItem){
        Integer country_counter = 0;
        for (CSVRecord record : parser){
            if (record.get("Exports").contains(exportItem)){
                country_counter += 1;
            }
        }
        return country_counter;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for (CSVRecord record : parser) {
            if (record.get("Value (dollars)").length() > amount.length()){
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }
        
    
      
}
