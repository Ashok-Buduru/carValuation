package BasePage;

import Pages.searchRegInOutputFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObjectManager {

    public PageObjectManager(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    private WebDriver driver;
    private searchRegInOutputFile searchRegInOutputFile = new searchRegInOutputFile(driver);
}
