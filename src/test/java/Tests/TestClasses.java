package Tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static Utils.BrowserUtils.getDriver;
import static Utils.Constants.CURRENT_BROWSER;
import static Utils.Constants.URL_BASE;

public class TestClasses {

    WebDriver driver;

    @Before
    public void beforeTest() {
        System.out.println("This surely runs each time before any test!!");
        driver = getDriver(CURRENT_BROWSER);
        driver.navigate().to(URL_BASE);
    }

    @Test
    public void test01() {
        System.out.println("This is my very first test !");
    }

    @Test
    public void test02() {
        System.out.println("test 02");
        // identify the search button place a string and hit run
    }

    @After
    public void afterTest() {
        System.out.println("This is the After method that runs after each tests !");
        driver.close();
    }

}
