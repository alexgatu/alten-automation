import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestClasses {

    public static final String URL_BASE = "https://github.com/";
    public static final String DRIVERS_PATH = "src/test/resources/drivers/" ;

    public WebDriver getDriver(String browser) {
        WebDriver driver = null;
        switch (browser) {
            case "chrome" : {
                System.setProperty("webdriver.chrome.driver", DRIVERS_PATH + "chromedriver.exe");
                driver = new ChromeDriver();
                break;
            }
            case "firefox" : {
                System.setProperty("webdriver.gecko.driver",DRIVERS_PATH + "geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            }
            case "edge" : {
                System.setProperty("webdriver.edge.driver", DRIVERS_PATH + "msedgedriver.exe");
                driver = new EdgeDriver();
                break;
            }
            default: {
                throw new IllegalArgumentException("The value provided for the browser type is illegal: " + browser);
            }
        }
        return driver;
    }

    @Before
    public void beforeTest() {
        System.out.println("This surely runs each time before any test!!");

    }

    @Test
    public void test01() {
        System.out.println("This is my very first test !");
        WebDriver driver = getDriver("chrome");
        driver.navigate().to(URL_BASE);
        driver.close();
    }

    @Test
    public void test02() {
        System.out.println("Firefox first test");
        WebDriver driver = getDriver("firefox");
        driver.navigate().to(URL_BASE);
        driver.close();
    }

    @Test
    public void test03() {
        System.out.println("Edge first test");
        WebDriver driver = getDriver("edge");
        driver.navigate().to(URL_BASE);
        driver.close();
    }


    @After
    public void afterTest() {
        System.out.println("This is the After method that runs after each tests !");
    }

}
