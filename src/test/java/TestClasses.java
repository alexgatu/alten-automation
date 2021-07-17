import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestClasses {

    public static final String URL_BASE = "https://github.com/";
    public static final String DRIVERS_PATH = "src/test/resources/drivers/" ;

    @Before
    public void BeforeTest() {
        System.out.println("This surely runs each time before any test!!");
    }

    @Test
    public void Test01() {
        System.out.println("This is my very first test !");
        System.setProperty("webdriver.chrome.driver", DRIVERS_PATH + "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.navigate().to(URL_BASE);
        driver.close();
    }

    @Test
    public void Test02() {
        System.out.println("Firefox first test");
        System.setProperty("webdriver.gecko.driver",DRIVERS_PATH + "geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.navigate().to(URL_BASE);
        driver.close();
    }

    @Test
    public void Test03() {
        System.out.println("Edge first test");
        System.setProperty("webdriver.edge.driver", DRIVERS_PATH + "msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.navigate().to(URL_BASE);
        driver.close();
    }


}
