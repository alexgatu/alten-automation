package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static Utils.Constants.DRIVERS_PATH;

public class BrowserUtils {

    public static Browsers getBrowser(String browser) {
        switch (browser.toLowerCase()) {
            case "firefox": {
                return Browsers.FIREFOX;
            }
            case "chrome": {
                return Browsers.CHROME;
            }
            case "edge": {
                return Browsers.EDGE;
            }
            default: {
                throw new IllegalArgumentException("Browser " + browser + " is not supported!!");
            }
        }
    }

    // Kept only for reference - this is deprecated
    public static WebDriver getDriverOld(Browsers browser) {
        WebDriver driver = null;
        switch (browser) {
            case CHROME : {
                System.setProperty("webdriver.chrome.driver", DRIVERS_PATH + "chromedriver.exe");
                driver = new ChromeDriver();
                break;
            }
            case FIREFOX: {
                System.setProperty("webdriver.gecko.driver",DRIVERS_PATH + "geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            }
            case EDGE : {
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

    public static WebDriver getDriver(Browsers browser) {
        WebDriver driver = null;
        switch (browser) {
            case CHROME : {
                System.setProperty("webdriver.chrome.driver", DRIVERS_PATH + "chromedriver.exe");
                driver = new ChromeDriver();
                break;
            }
            case FIREFOX: {
                System.setProperty("webdriver.gecko.driver",DRIVERS_PATH + "geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            }
            case EDGE : {
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

}
