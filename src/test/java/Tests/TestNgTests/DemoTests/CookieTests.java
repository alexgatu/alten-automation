package Tests.TestNgTests.DemoTests;

import Tests.TestNgTests.BaseClass;
import Utils.GeneralUtils;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.Set;

import static Utils.ConfigReader.URL;
import static Utils.ConstantsUtils.URL_BASE2;

public class CookieTests extends BaseClass {

    @Test
    public void cookieTest01(){
        //driver.get(URL);
        driver.get(URL_BASE2);
        GeneralUtils.printCookies(driver);

        // Adding a new Cookie, while on the site
        Cookie c = new Cookie("alexCookie","12345");
        // add cookie to driver / browser
        driver.manage().addCookie(c);

        // Adding a cookie from the start (without being on any site)
        Cookie c2 = new Cookie(
                "cookie1",
                "value1",
                "magazinulcolectionarului.ro",
                "/",
                new Date());
        driver.manage().addCookie(c2);

        GeneralUtils.printCookies(driver);
        // Delete a cookie that we created
        driver.manage().deleteCookie(c);
        GeneralUtils.printCookies(driver);

        // Delete a cookie based on its name
        driver.manage().deleteCookieNamed("PHPSESSID");
        GeneralUtils.printCookies(driver);

        // Delete all cookies
        driver.manage().deleteAllCookies();
        GeneralUtils.printCookies(driver);
    }

    @Test
    public void cookieTest02(){
        driver.get(URL_BASE2);
        GeneralUtils.takeScreenshot(driver);

        Set<Cookie> cookies = driver.manage().getCookies();
        Cookie phpCookie = driver.manage().getCookieNamed("PHPSESSID");

        Assert.assertEquals(phpCookie.getName(), "PHPSESSID");

        Assert.assertTrue(phpCookie.isSecure());

        Assert.assertTrue(GeneralUtils.checkCookieName(driver, "PHPSESSID"));
    }


}
