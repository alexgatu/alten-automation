package Tests.TestNgTests.DemoTests;

import Tests.TestNgTests.BaseClass;
import Utils.ConstantsUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

import static Utils.ConstantsUtils.URL_BASE2;

public class TestNgClasses extends BaseClass {

    private static String searchTerm = "boxe";

    @Test
    public void test01(){
        System.out.println("TEST01");
        driver.get(URL_BASE2);
        double a = 1.05;
        double b = 2.01;
        double sum = a + b; // 3.06, delta in assert is the tolerance 2.91 < sum < 3.11 --> passed
        Assert.assertEquals(sum,3.01,0.1, "Equals !!");
        if (a > b) {
            //
            Assert.fail();
        }
        Assert.assertTrue( a <= b);
    }

    @Test
    public void test02(){
        System.out.println("TEST02");
        driver.get(URL_BASE2);
    }

    @Test
    public void test03(){
        System.out.println("TEST03");
        // go to the specified URL
        driver.get(URL_BASE2);

        // Thread.sleep is NOT RECOMMENDED to be used in an auto framework
        try {
            System.out.println("Starting to sleep");
            Thread.sleep(500);
            System.out.println("Finished sleeping");
            }
        catch (InterruptedException iex) {
            System.out.println(iex.getMessage());
        }

        // alternative way to go to an URL
        driver.navigate().to(URL_BASE2);

        // click on the back button from the browser
        driver.navigate().back();

        // click on the forward button from the browser
        driver.navigate().forward();

        // click on the refresh button from the browser controlls
        driver.navigate().refresh();
    }

    @Test
    public void test04() {
        driver.get("https://www.google.ro/");

        WebElement agreeTerms = driver.findElement(By.cssSelector("#L2AGLb > div"));
        agreeTerms.click();

        WebElement searchBox = driver.findElement(By.cssSelector("body > div.L3eUgb > div.o3j99.ikrT4e.om7nvf > form > div:nth-child(1) > div.A8SBwf > div.RNNXgb > div > div.a4bIc > input"));
        WebElement searchBox2 = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input"));
        WebElement searchBox3 = driver.findElement(By.name("q"));

        searchBox3.sendKeys(searchTerm);
        searchBox3.sendKeys(Keys.ENTER);

        //WebElement firstLink = driver.findElement(By.cssSelector("#rso > div:nth-child(1) > div > div > div > div.yuRUbf > a"));
        WebElement firstLink = driver.findElement(By.cssSelector("div.yuRUbf > a"));
        List<WebElement> allLinks = driver.findElements(By.cssSelector("div.yuRUbf > a"));

        System.out.println("Results Links on the page: " + allLinks.size());

        for (WebElement link : allLinks) {
            System.out.println(link.getText());
        }

        firstLink.click();

    }


}
