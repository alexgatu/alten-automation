package Utils;

import java.io.*;
import java.util.Map;
import java.util.Properties;

import static Utils.ConstantsUtils.CONFIG_PATH;
import static Utils.ConstantsUtils.CONFIG_FILE;

public class ConfigReader {

    public static String URL;
    public static String ENV;
    public static String BROWSER;
    public static boolean WEBDRIVER_MANAGER;
    public static boolean HEADLESS_MODE;
    public static String DB_JDBC;
    public static String DB_USER;
    public static String DB_PASS;

    public static void readConfigFile() {

        try (InputStream input = new FileInputStream(CONFIG_PATH + CONFIG_FILE)) {
            Properties props = new Properties();
            props.load(input);
            String port = props.getProperty("URL_PORT", "80");
            String hostname = props.getProperty("URL_HOSTNAME", "localhost");
            String proto = props.getProperty("URL_PROTOCOL", "http");
            URL = proto + "://" + hostname + ":" + port + "/";
            ENV = props.getProperty("CURRENT_ENV");
            BROWSER = props.getProperty("RUN_BROWSER");
            WEBDRIVER_MANAGER = Boolean.parseBoolean(props.getProperty("WEB_DRIVER_MANAGER"));
            HEADLESS_MODE = Boolean.parseBoolean(props.getProperty("START_HEADLESS"));
            //Log.info(URL);
            String dbHost = props.getProperty("DB_HOST", "localhost");
            String dbPort = props.getProperty("DB_PORT", "3306");
            String dbSchema = props.getProperty("DB_SCHEMA", "automation");
            DB_USER = props.getProperty("DB_USER", "root");
            DB_PASS = props.getProperty("DB_PASS", "password");
            DB_JDBC = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbSchema;
        }
        catch (FileNotFoundException fnf) {
            System.out.println("File not found: " + CONFIG_PATH + CONFIG_FILE);
            //Log.error("File not found: " + CONFIG_PATH + CONFIG_FILE);
            //Log.fatal(GeneralUtils.stackTraceConvert(fnf.getStackTrace()));
        }
        catch (IOException ioex) {
            //Log.error(ioex.getMessage());
            //Log.fatal(GeneralUtils.stackTraceConvert(ioex.getStackTrace()));
        }

    }

    public static void writeConfigFile(Map<String,String> configs) {
        try (OutputStream output = new FileOutputStream(CONFIG_PATH + CONFIG_FILE)) {
            Properties props = new Properties();
            for (String key : configs.keySet()) {
                props.setProperty(key, configs.get(key));
            }
            props.store(output, null);
        }
        catch (FileNotFoundException fnf) {
            //Log.error("File not found: " + CONFIG_PATH + CONFIG_FILE);
            //Log.fatal(GeneralUtils.stackTraceConvert(fnf.getStackTrace()));
        }
        catch (IOException ioex) {
            //Log.fatal(GeneralUtils.stackTraceConvert(ioex.getStackTrace()));
        }
    }
}
