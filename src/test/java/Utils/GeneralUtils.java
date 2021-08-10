package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Set;
import java.util.regex.Pattern;

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

    // Generic wait function
    public static WebElement waitForGenericElement(WebDriver driver, By by, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    // Generic wait until specific text appear on the site
    public static boolean waitUntilText(WebDriver driver, By by, int timeout, String pattern) {
        Pattern r2 = Pattern.compile(pattern, 0);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.textMatches(by, r2));
    }

}
