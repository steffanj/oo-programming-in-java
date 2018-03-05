import edu.duke.*;
import java.io.File;
import org.apache.commons.csv.*;

public class weatherData {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow : parser) {
            double currentRowT = Double.parseDouble(currentRow.get("TemperatureF"));  
            if (currentRowT == -9999) {
                System.out.println("T=-9999, skipping this record");
                continue;
            }
            if (lowestSoFar == null){
                lowestSoFar = currentRow;
            }
            else {
                double lowestSoFarT = Double.parseDouble(lowestSoFar.get("TemperatureF"));
                if (currentRowT < lowestSoFarT) {
                    lowestSoFar = currentRow;
                }
            }
        }
        return lowestSoFar;
    }
    
    public void testColderHourInFile() {
        FileResource fr = new FileResource();        
        CSVParser parser = fr.getCSVParser();      
        CSVRecord lowestTRecord = coldestHourInFile(parser);
        System.out.println(lowestTRecord.get("DateUTC") + " Temp:" + lowestTRecord.get("TemperatureF"));
    }
    
    public String fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        String filePath = null;
        CSVRecord lowestRecordSoFar = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord lowestRecordCurrentFile = coldestHourInFile(parser);
            double lowestTCurrentFile = Double.parseDouble(lowestRecordCurrentFile.get("TemperatureF"));
            if (lowestRecordSoFar == null) {
                lowestRecordSoFar = lowestRecordCurrentFile;
                filePath = f.getPath();
            }
            else {
                double lowestTSoFar = Double.parseDouble(lowestRecordSoFar.get("TemperatureF"));                            
                if (lowestTCurrentFile < lowestTSoFar) {
                    lowestRecordSoFar = lowestRecordCurrentFile;
                    filePath = f.getPath();
                }
            }

        }
        return filePath;
    }
    
    public void testFileWithColdestTemperature() {
        String filePath = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + filePath);
        FileResource fr = new FileResource(filePath);        
        CSVParser parser = fr.getCSVParser();      
        CSVRecord lowestTRecord = coldestHourInFile(parser);
        System.out.println("Coldest temperature on that day was " + lowestTRecord.get("TemperatureF"));

        parser = fr.getCSVParser();
        for (CSVRecord row : parser) {
            System.out.println(row.get("DateUTC") + ": " + row.get("TemperatureF"));
        }
    }    
    
}