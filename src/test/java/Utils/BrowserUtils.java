package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static Utils.ConstantsUtils.CURRENT_ENV;
import static Utils.ConstantsUtils.DRIVERS_PATH;

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
                //Log.fatal("Illegal argument for browser " + browser);
                throw new IllegalArgumentException("The value provided for the browser type is illegal: " + browser);
            }
        }
        //Log.info("Tests are running with browser: " + browser);
        return driver;
    }

    private static boolean isWebDriverManagerRun() {
        if(ConfigReader.WEBDRIVER_MANAGER) {
            return true;
        }
        if (ConfigReader.ENV.toLowerCase().contains("local")) {
            //Log.debug("Running on environment " + CURRENT_ENV + " with WebDriverManager.");
            return true;
        }
        else {
            //Log.debug("Running on environment " + CURRENT_ENV + " with System properties for browsers.");
            return false;
        }
    }

    public static WebDriver getDriver(Browsers browser) {
        WebDriver driver = null;
        switch (browser) {
            case CHROME : {
                if (isWebDriverManagerRun()) {
                    WebDriverManager.chromedriver().setup();
                }
                else {
                    System.setProperty("webdriver.chrome.driver", DRIVERS_PATH + "chromedriver.exe");
                }
                driver = new ChromeDriver();
                break;
            }
            case FIREFOX: {
                if (isWebDriverManagerRun()) {
                    WebDriverManager.firefoxdriver().setup();
                }
                else {
                    System.setProperty("webdriver.gecko.driver", DRIVERS_PATH + "geckodriver.exe");
                }
                driver = new FirefoxDriver();
                break;
            }
            case EDGE : {
                if (isWebDriverManagerRun()) {
                    WebDriverManager.edgedriver().setup();
                }
                else {
                    System.setProperty("webdriver.edge.driver", DRIVERS_PATH + "msedgedriver.exe");
                }
                driver = new EdgeDriver();
                break;
            }
            default: {
                //Log.fatal("Illegal argument for browser " + browser);
                throw new IllegalArgumentException("The value provided for the browser type is illegal: " + browser);
            }
        }
        //Log.info("Tests are running with browser: " + browser);
        if (driver == null) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }
        return driver;
    }

}
