package Utils;

public class ConstantsUtils {
    public static final String URL_BASE2 = "https://magazinulcolectionarului.ro/";
    //public static final String URL_BASE = "http://86.121.249.150:4999/";
    public static final String URL_BASE = ConfigReader.URL;

    public static final String RESOURCES_PATH="src/test/resources/";
    public static final String DRIVERS_PATH = RESOURCES_PATH + "drivers/" ;
    public static final String EXTENSIONS_PATH = RESOURCES_PATH + "extensions/" ;
    public static final String DOWNLOAD_PATH = "downloads/";
    public static final String SCREENSHOT_PATH = "screenshots/";

    public static final String CONFIG_PATH = "";
    public static final String CONFIG_FILE = "config.properties";

    public static final String CURRENT_BROWSER = ConfigReader.BROWSER;
    public static final String CURRENT_ENV = ConfigReader.ENV;
}
