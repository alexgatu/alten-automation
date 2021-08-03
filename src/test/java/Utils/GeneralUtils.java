package Utils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class GeneralUtils {

    public static String stackTraceConvert(Object[] obj) {
        StringBuilder output = new StringBuilder();
        for (Object o : obj) {
            output.append(o.toString());
            output.append("\n");
        }
        return output.toString();
    }

    public static void printCookies(WebDriver driver) {
        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println("Number of cookies found: " + cookies.size());
        for (Cookie c: cookies) {
            System.out.println("Cookie: " + c.getName());
            System.out.println("--> Value: " + c.getValue());
            System.out.println("--> Domain: " + c.getDomain());
            System.out.println("--> Path: " + c.getPath());
            System.out.println("--> Expires: " + c.getExpiry());
            System.out.println();

        }
    }

    // A method for searching if a cookie with specific name is present in the browser
    public static boolean checkCookieName(WebDriver driver, String cookieName) {
        Set<Cookie> cookies = driver.manage().getCookies();
        boolean found = false;
        for (Cookie c : cookies) {
            if (c.getName().toLowerCase().equals(cookieName.toLowerCase())) {
                found = true;
            }
        }
        return found;
    }

}
