package Tests.TestNgTests;

import Utils.*;
import org.apache.log4j.BasicConfigurator;
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
//        System.out.println("BEFORE CLASS");
        ConfigReader.readConfigFile();
//        System.out.println(SystemVariableReader.BROWSER);
//        System.out.println(SystemVariableReader.ENV);
//        SystemVariableReader.printSystemVariables();
        BasicConfigurator.configure();
        //Log.info("Starting Tests.");
    }

    @BeforeMethod
    public void beforeTests() {
//        System.out.println("BEFORE TEST");
       // System.out.println(ConstantsUtils.CURRENT_BROWSER);
        System.out.println("URL: " + ConfigReader.URL);
        System.out.println("BROWSER: " + getBrowser(ConfigReader.BROWSER));
        System.out.println("ENV: " + ConfigReader.ENV);
        driver = BrowserUtils.getDriver(getBrowser(ConfigReader.BROWSER));
    }

    @AfterMethod
    public void afterMethod(){
//        System.out.println("AFTER TEST");
        if (driver != null) {
            driver.close();
        }
    }

    @AfterClass
    public void afterAll() {
        //Log.info("Tests ended.");
//        System.out.println("AFTER ALL");
    }
}
