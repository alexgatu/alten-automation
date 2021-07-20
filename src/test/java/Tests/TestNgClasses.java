package Tests;

import Utils.BrowserUtils;
import Utils.Constants;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static Utils.BrowserUtils.getBrowser;

public class TestNgClasses {

    WebDriver driver;

    @BeforeClass
    public void beforeAll(){
        System.out.println("BEFORE CLASS");
    }

    @BeforeMethod
    public void beforeTests() {
        System.out.println("BEFORE TEST");
        driver = BrowserUtils.getDriver(getBrowser(Constants.CURRENT_BROWSER));
        driver.get(Constants.URL_BASE);
    }

    @Test
    public void test01(){
        System.out.println("TEST01");
    }

    @Test
    public void test02(){
        System.out.println("TEST02");
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
