package Pages;

import BasePage.BaseUtils;
import com.google.inject.Inject;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CarSummaryPage extends BaseUtils {

    @Inject
    public CarSummaryPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//img[@alt='Car Tax Check']")
    private WebElement homeImage;

    @FindBy(id = "vrm-input")
    private WebElement vrm_input;

    @FindBy(xpath = "//a[contains(text(),'Get a Full Check')]")
    private WebElement getFullCheckButton;

    @FindBy(xpath = "//button[contains(text(),'Free Car Check')]")
    private WebElement getFreeCarCheckButton;

    @FindBy(xpath = "//button[contains(text(),'Check Vehicle')]")
    private WebElement checkValuationButton;

    /*Full Car Check Page*/

    @FindBy(xpath = "//dt[contains(text(),'Registration')]/following-sibling::dd")
    private WebElement getFullVehicleRegistrationNumber;

    @FindBy(xpath = "//dt[contains(text(),'Vehicle')]/following-sibling::dd")
    private WebElement getFullVehicleMakeModel;

    @FindBy(xpath = "//dt[contains(text(),'Year')]/following-sibling::dd")
    private WebElement getFullVehicleYear;

    @FindBy(xpath = "//dt[contains(text(),'Colour')]/following-sibling::dd")
    private WebElement getFullVehicleColor;

    /*Free Car Check Page*/

    @FindBy(xpath = "//dt[contains(text(),'Registration')]/following-sibling::dd")
    private WebElement getVehicleRegistrationNumber;

    @FindBy(xpath = "//dt[contains(text(),'Make')]/following-sibling::dd")
    private WebElement getVehicleMake;

    @FindBy(xpath = "//dt[contains(text(),'Model')]/following-sibling::dd")
    private WebElement getVehicleModel;

    @FindBy(xpath = "//dt[contains(text(),'Year')]/following-sibling::dd")
    private WebElement getVehicleYear;

    @FindBy(xpath = "//dt[contains(text(),'Colour')]/following-sibling::dd")
    private WebElement getVehicleColor;

    @FindBy(xpath = "//form[@id='checkout-form']//button")
    private WebElement payButton;

    @Test
    private void searchGetFullCarCheckFromWebsite(String carReg){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        homeImage.click();
        vrm_input.clear();
        vrm_input.sendKeys(carReg);
        getFullCheckButton.click();
        checkValuationButton.click();
        try{
            Thread.sleep(2000);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    private void searchGetFreeCarCheckFromWebsite(String carReg){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        homeImage.click();
        vrm_input.clear();
        vrm_input.sendKeys(carReg);
        getFreeCarCheckButton.click();
        try{
            Thread.sleep(2000);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void compareCarDetailsFromWebsite(String eachCarRecord) throws IOException, CsvValidationException {
        SoftAssert softAssert= new SoftAssert();
        String matchedFile = "car_results_Matched.csv";
        String notMatchedFile = "car_results_NotMatched.csv";
        CSVWriter csvWriterMatched = new CSVWriter(new FileWriter(matchedFile, true));
        CSVWriter csvWriterNotMatched =new CSVWriter(new FileWriter(notMatchedFile, true));
        String[] outputRecordFound = searchRegInOutputFile.searchCarDetailsOutputFile(eachCarRecord.trim().replace(" ",""));
        if(outputRecordFound!=null){
            searchGetFreeCarCheckFromWebsite(eachCarRecord.trim().replace(" ",""));
            String expectedRegNumber =outputRecordFound[0];
            String expectedMake = outputRecordFound[1];
            String expectedModel = outputRecordFound[2];
            String expectedColour = outputRecordFound[3];
            String expectedYear = outputRecordFound[4];
            Assert.assertEquals(expectedRegNumber, getVehicleRegistrationNumber.getText(), "Registration number not matched");
            Assert.assertEquals(expectedMake, getVehicleMake.getText(), "Make not matched");
            Assert.assertEquals(expectedModel, getVehicleModel.getText(), "Model not matched");
            Assert.assertEquals(expectedColour, getVehicleColor.getText(), "Colour not matched");
            Assert.assertEquals(expectedYear, getVehicleYear.getText(), "Year not matched");
            csvWriterMatched.writeNext(outputRecordFound);
        }
        else
        {
           Assert.assertTrue(false, "Not found in output file -"+ eachCarRecord);
           csvWriterNotMatched.writeNext(new String[]{eachCarRecord});
        }
        softAssert.assertAll();
        csvWriterMatched.close();
        csvWriterNotMatched.close();
    }
}
