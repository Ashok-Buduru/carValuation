package Tests;

import BasePage.BaseUtils;
import Pages.CarSummaryPage;
import Pages.ReadTextFile;
import com.opencsv.exceptions.CsvValidationException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class compareTestResults extends Hooks {

    @DataProvider(name = "compareOutputResults")
    public Object[][] getCarInputData() throws IOException {
        String inputFileName = "car_input.txt";
        ReadTextFile inputFile=new ReadTextFile(inputFileName);
        List<String> carInputData=inputFile.readInputTextFile();
        Object[][] objectArray=new Object[carInputData.size()][1];
        for(int index=0;index<carInputData.size();index++){
            objectArray[index][0]=carInputData.get(index);
        }
        return objectArray;
    }

    @Test(dataProvider="compareOutputResults")
    public void compareCarDetailsFromWebsite(String eachCarRecord) throws IOException, CsvValidationException {
        CarSummaryPage summaryPage=new CarSummaryPage(BaseUtils.driver);
        summaryPage.compareCarDetailsFromWebsite(eachCarRecord);
    }

}
