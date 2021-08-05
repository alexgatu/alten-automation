package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
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

    public static void takeScreenshot(WebDriver driver) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        File screenShotFile = new File(ConstantsUtils.SCREENSHOT_PATH +
//                "screenshot_" + timestamp.getTime() + ".png");
        // Alternative to construct strings
        StringBuilder sb = new StringBuilder();
        sb.append(ConstantsUtils.SCREENSHOT_PATH);
        sb.append("screenshot_");
        sb.append(timestamp.getTime());
        sb.append(".png");
        File screenShotFile = new File(sb.toString());

        File capturedFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(capturedFile, screenShotFile);
        }
        catch (IOException ioex) {
            System.out.println("Screenshot File could not be saved on the disk !");
        }
    }

}
