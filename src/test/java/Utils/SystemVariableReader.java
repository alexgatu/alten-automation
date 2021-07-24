package Utils;

import java.util.Map;

public class SystemVariableReader {

    public static String BROWSER = System.getenv("DEFAULT_BROWSER");
    public static String ENV = System.getenv("CURRENT_ENV");

    public static void printSystemVariables() {
        Map<String,String> envVars = System.getenv();
        for (String keys : envVars.keySet()) {
            System.out.println(keys + " " + envVars.get(keys));
        };
    }

    public static void setSystemProperty(String key, String value) {
        System.setProperty(key, value);
    }

}
