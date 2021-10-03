package Pages;

import BasePage.BaseUtils;
import com.google.inject.Inject;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import java.io.*;

public class searchRegInOutputFile extends BaseUtils {

    @Inject
    public searchRegInOutputFile(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @Test
    public static String[] searchCarDetailsOutputFile(String carRecord) throws IOException, CsvValidationException {
        CSVReader reader = new CSVReader(new FileReader("car_output.txt"));
        String[] recordFound=null;
        String[] allRecords;
        while((allRecords= reader.readNext())!=null){
            int eachRecord=0;
            while (eachRecord<allRecords.length) {
                String singleRecord = allRecords[eachRecord];
                    if(singleRecord.equals(carRecord)) {
                    recordFound= allRecords;
                    break;
                }
                eachRecord++;
            }
        }
        return recordFound;
    }
}
