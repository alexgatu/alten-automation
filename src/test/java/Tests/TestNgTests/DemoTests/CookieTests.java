package Tests.TestNgTests.DemoTests;

import Tests.TestNgTests.BaseClass;
import Utils.GeneralUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @Test
    public void cookieTest03() {
        driver.get(URL + "#/cookie");

        // Elements found by ID
        WebElement setCookieButton = driver.findElement(By.id("set-cookie"));
        WebElement deleteCookieButton = driver.findElement(By.id("delete-cookie"));

        // Elements found by CSS selector
//        WebElement setCookieButton = driver.findElement(By.cssSelector("#set-cookie"));
//        WebElement deleteCookieButton = driver.findElement(By.cssSelector("#delete-cookie"));

        // Elements found by XPATH selector
//        WebElement setCookieButton = driver.findElement(By.xpath("//*[@id=\"set-cookie\"]"));
//        WebElement deleteCookieButton = driver.findElement(By.xpath("//*[@id=\"delete-cookie\"]"));

        // Elements found by link text - not recommended as it searches for links
//        WebElement setCookieButton = driver.findElement(By.linkText("Set the cookie"));
//        WebElement deleteCookieButton = driver.findElement(By.linkText("Remove the cookie"));

        // Elements found by tag name - not recommended as there are multiple tags with the same name
//        WebElement setCookieButton = driver.findElement(By.tagName("button"));
//        WebElement deleteCookieButton = driver.findElement(By.tagName("button"));
        List<WebElement> buttons = driver.findElements(By.tagName("button"));

        // Elements found by name (attribute)  - name="button-name"
//        WebElement setCookieButton = driver.findElement(By.name("button-name"));
//        WebElement deleteCookieButton = driver.findElement(By.name("button-name"));

        // Elements found by class name -- converts to cssSelector .className
//        WebElement setCookieButton = driver.findElement(By.className("btn-success"));
//        WebElement deleteCookieButton = driver.findElement(By.className("btn-danger"));

        System.out.println("TAG name: " + setCookieButton.getTagName());
        System.out.println("TEXT: " +  setCookieButton.getText());
        System.out.println("TAG name: " + deleteCookieButton.getTagName());
        System.out.println("TEXT: " +  deleteCookieButton.getText());
        setCookieButton.click();
        deleteCookieButton.click();

        System.out.println("Number of buttons found in the page: " + buttons.size());
        buttons.get(0).click();
        buttons.get(1).click();
        for (WebElement button : buttons) {
            System.out.println(button.getText());
        }

    }

    @Test
    public void cookieTest04() {
        driver.get(URL + "#/cookie");
        WebElement addCookieButton = driver.findElement(By.id("set-cookie"));
        WebElement deleteCookieButton = driver.findElement(By.id("delete-cookie"));
        WebElement cookieText = driver.findElement(By.id("cookie-value"));

        // Get the cookies from the browser and check that there are no cookies present
        Set<Cookie> cookies = driver.manage().getCookies();
        Assert.assertEquals(cookies.size(), 0);

        // click on add cookie button
        addCookieButton.click();

        // Get the text from the cookie text
        String cookieTextVal = cookieText.getText();

        // Get the list of the cookies from the browser and check that the cookie was created
        cookies = driver.manage().getCookies();
        Assert.assertEquals(cookies.size(), 1);

        GeneralUtils.printCookies(driver);

        // Get all the cookie values and save them into a list
        List<String> cookieValues = new ArrayList<>();
        for (Cookie c : cookies) {
            cookieValues.add(c.getValue());
        }

        // Check that from the cookie value list, the one we are search for is present
        Assert.assertTrue(cookieValues.contains(cookieTextVal));

        // Delete the existing cookie
        deleteCookieButton.click();

        // Get the list of the cookies from the browser
        cookies = driver.manage().getCookies();

        // Check that there are no cookies left
        Assert.assertEquals(cookies.size(),0);

    }

}
