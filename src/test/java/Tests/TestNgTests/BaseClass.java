package Tests.TestNgTests;

import Utils.BrowserUtils;
import Utils.ConfigReader;
import Utils.ConstantsUtils;
import Utils.SystemVariableReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.Map;

import static Utils.BrowserUtils.getBrowser;

public class BaseClass {
    public WebDriver driver;


    @BeforeClass
    public void beforeAll(){
        System.out.println("BEFORE CLASS");
        ConfigReader.readConfigFile();
//        System.out.println(SystemVariableReader.BROWSER);
//        System.out.println(SystemVariableReader.ENV);
//        SystemVariableReader.printSystemVariables();
    }

    @BeforeMethod
    public void beforeTests() {
        System.out.println("BEFORE TEST");
        driver = BrowserUtils.getDriver(getBrowser(ConstantsUtils.CURRENT_BROWSER));
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("AFTER TEST");
        if (driver != null) {
            driver.close();
        }
    }

    @AfterClass
    public void afterAll() {
        System.out.println("AFTER ALL");
    }
}
