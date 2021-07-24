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
            //System.out.println(URL);
        }
        catch (FileNotFoundException fnf) {
            System.out.println("File not found !");
        }
        catch (IOException ioex) {
            System.out.println("IO Exception !");
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
            System.out.println("File not found !");
        }
        catch (IOException ioex) {
            System.out.println("IO Exception !");
        }
    }
}
