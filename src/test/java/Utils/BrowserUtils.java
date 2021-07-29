package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static Utils.ConfigReader.HEADLESS_MODE;
import static Utils.ConstantsUtils.*;

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
                // Deprecated for Chrome
                DesiredCapabilities capabilities = new DesiredCapabilities();
                ChromeOptions options = new ChromeOptions();

                // How to add an extension (careful on the headless):
                //options.addExtensions(new File(ConstantsUtils.EXTENSIONS_PATH + "extension_9_8_3_0.crx"));

                // Start browser maximized:
                options.addArguments("--start-maximized");

                // Start browser in headless mode (without visible UI), the app is in background:
                options.setHeadless(HEADLESS_MODE);
                //options.addArguments("--headless");

                // Change default download directory:
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("download.default_directory", DOWNLOAD_PATH);
                options.setExperimentalOption("prefs", prefs);

                driver = new ChromeDriver(options);
                break;
            }
            case FIREFOX: {
                if (isWebDriverManagerRun()) {
                    WebDriverManager.firefoxdriver().setup();
                }
                else {
                    System.setProperty("webdriver.gecko.driver", DRIVERS_PATH + "geckodriver.exe");
                }

                FirefoxProfile profile = new FirefoxProfile();

                // Add extension:
                //profile.addExtension(new File(EXTENSIONS_PATH + "metamask-9.8.2-an+fx.xpi"));

                // deprecated - desired capabilities
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability(FirefoxDriver.PROFILE, profile);
                // Preferable approach:
                FirefoxOptions options = new FirefoxOptions();

                // Start maximized:
                options.addArguments("--start-maximized");

                // Headless mode (no UI):
                options.setHeadless(HEADLESS_MODE);

                // Change to specific resolution
                //options.addArguments("--width=1280");
                //options.addArguments("--height=1024");

                // Change default download directory:
                profile.setPreference("browser.download.dir", DOWNLOAD_PATH);

                options.setProfile(profile);

                //driver = new FirefoxDriver(capabilities);
                driver = new FirefoxDriver(options);
                break;
            }
            case EDGE : {
                if (isWebDriverManagerRun()) {
                    WebDriverManager.edgedriver().setup();
                }
                else {
                    System.setProperty("webdriver.edge.driver", DRIVERS_PATH + "msedgedriver.exe");
                }
                EdgeOptions options = new EdgeOptions();
                options.setCapability("args", "['start-maximized']");

                driver = new EdgeDriver(options);
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
