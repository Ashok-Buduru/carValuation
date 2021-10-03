package Tests;

import BasePage.BaseUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class Hooks extends BaseUtils {

    @BeforeTest
    public void launchBrowser() throws MalformedURLException, InterruptedException, AWTException {
        System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        DesiredCapabilities capabilities = new DesiredCapabilities ();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(capabilities);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.navigate().to("https://cartaxcheck.co.uk");
    }

    @AfterTest
    public void exitBrowser() throws IOException {
        String path;
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        path = "./target/" + scrFile.getName();
        FileHandler.copy(scrFile,new File(path));
        driver.quit();
    }

}
