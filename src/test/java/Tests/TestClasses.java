package Tests;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static Utils.BrowserUtils.getBrowser;
import static Utils.BrowserUtils.getDriver;
import static Utils.Constants.CURRENT_BROWSER;
import static Utils.Constants.URL_BASE;

public class TestClasses {

    WebDriver driver;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("This runs just once before all tests in the same class!");
    }

    @Before
    public void beforeTest() {
        System.out.println("This surely runs each time before any test!!");
        driver = getDriver(getBrowser(CURRENT_BROWSER));
        driver.navigate().to(URL_BASE);
    }

    private void doSomething() throws IllegalArgumentException {
        throw new IllegalArgumentException("illegal !!");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test01() {
        System.out.println("This is my very first test !");
//        String s = "Alex";
//        String s2;
//        Assert.assertEquals("String assertion", s, "Alex");
//        int a = 5;
//        int b = 10;
//        Assert.assertEquals("Integer assertion", 15, a + b );
//        Assert.assertFalse(a + b != 15);
//        Assert.assertNotNull(driver);
        String searchQuery = "disney"; // search keyword
        String title =  "Povesti din colectia de aur <mark>Disney</mark>  Nr. 82 - Salvatorii in Australia"; // title extracted from site
        Assert.assertTrue(title.toLowerCase().contains(searchQuery));
        doSomething();
    }

    @Test
    public void test02() {
        System.out.println("test 02");
        // identify the search button place a string and hit run
    }

    @After
    public void afterTest() {
        System.out.println("This is the After method that runs after each tests !");
        if (driver != null) {
            driver.close();
        }
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("This runs only once at the end of the tests in the same class!");
    }

}
