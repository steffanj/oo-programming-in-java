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
    
    public void testColdestHourInFile() {
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
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow : parser) {
            String currentRowH = currentRow.get("Humidity");
            if (currentRowH == "N/A") {
                System.out.println("Humidity = 'N/A', skipping this record");
                continue;
            }
            double currentRowHD = Double.parseDouble(currentRowH);              
            if (lowestSoFar == null){
                lowestSoFar = currentRow;
            }
            else {
                double lowestSoFarHD = Double.parseDouble(lowestSoFar.get("Humidity"));
                if (currentRowHD < lowestSoFarHD) {
                    lowestSoFar = currentRow;
                }
            }
        }
        return lowestSoFar;
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();        
        CSVParser parser = fr.getCSVParser();      
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }        
    
    public CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestRecordSoFar = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord lowestRecordCurrentFile = lowestHumidityInFile(parser);
            double lowestHCurrentFile = Double.parseDouble(lowestRecordCurrentFile.get("Humidity"));
            if (lowestRecordSoFar == null) {
                lowestRecordSoFar = lowestRecordCurrentFile;
            }
            else {
                double lowestHSoFar = Double.parseDouble(lowestRecordSoFar.get("Humidity"));                            
                if (lowestHCurrentFile < lowestHSoFar) {
                    lowestRecordSoFar = lowestRecordCurrentFile;
                }
            }

        }
        return lowestRecordSoFar;
    }    
    
    public void testLowestHumidityInManyFiles() {     
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }   
    
    public double averageTemperatureInFile(CSVParser parser) {
        int tCounter = 0;
        double temperatureSum = 0;
        for (CSVRecord row : parser) {
            String tempString = row.get("TemperatureF");
            if (tempString == "-9999") {
                System.out.println("Temperature = '-9999', skipping this record");
                continue;
            }
            double temperature = Double.parseDouble(tempString);              
            tCounter += 1;
            temperatureSum += temperature;
            
        }
        return temperatureSum/tCounter;
    }    
 
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();        
        CSVParser parser = fr.getCSVParser();      
        double averageT = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + averageT);
    }   
    
}