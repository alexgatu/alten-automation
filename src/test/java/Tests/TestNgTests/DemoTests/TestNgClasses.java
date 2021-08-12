package Tests.TestNgTests.DemoTests;

import Tests.TestNgTests.BaseClass;
import Utils.ConfigReader;
import Utils.ConstantsUtils;
import Utils.GeneralUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static Utils.ConstantsUtils.URL_BASE;
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

    @Test
    public void test05() {
        driver.get(ConfigReader.URL);
        // Implicit wait - valid for all elements identification
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement waitTab = driver.findElement(By.cssSelector("#root > div > div.sidebar > a:nth-child(7)"));
        waitTab.click();

        WebElement button = driver.findElement(By.id("answer-trigger"));
        button.click();

        // Explicit wait - wait for element until
        WebDriverWait wait = new WebDriverWait(driver, 15);

        // Wait  After text matches a pattern
        GeneralUtils.waitUntilText(driver, By.cssSelector("div.text-center.the-answer.row > div"),15,"42");

        // Wait until element is present on the page
        WebElement textWait = GeneralUtils.waitForGenericElement(driver,By.cssSelector("div.text-center.the-answer.row > div"), 10);

        // Thread.sleep is another option that is not recommended !!
//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        WebElement textWait = driver.findElement(By.cssSelector("#root > div > div.content > div > div:nth-child(2) > div.text-center.the-answer.row > div"));
        System.out.println(textWait.getText());

        System.out.println(textWait.getCssValue("color"));
        System.out.println(button.getCssValue("color"));
        System.out.println(button.getLocation());

    }

    // simple alert test
    @Test
    public void test06() {
        driver.get(ConfigReader.URL);

        WebElement alertLink = driver.findElement(By.cssSelector("#root > div > div.sidebar > a:nth-child(4)"));
        alertLink.click();

        WebElement alertButton = driver.findElement(By.id("alert-trigger"));
//        WebElement alertButton = driver.findElement(By.cssSelector("#alert-trigger"));

        alertButton.click();
        Alert alert = driver.switchTo().alert();

        System.out.println(alert.getText());
        Assert.assertTrue(alert.getText().contains("alert"));

        alert.accept();

        WebElement confirmButton = driver.findElement(By.id("confirm-trigger"));
        confirmButton.click();

        Alert confirm = driver.switchTo().alert();
        System.out.println(confirm.getText());
        Assert.assertTrue(confirm.getText().contains("confirmation"));

        confirm.dismiss();
//        confirm.accept();

        WebElement promptButton = driver.findElement(By.id("prompt-trigger"));
        promptButton.click();

        Alert prompt = driver.switchTo().alert();
        System.out.println(prompt.getText());
        Assert.assertTrue(prompt.getText().contains("prompt"));

        prompt.sendKeys("Alex");
        prompt.accept();

    }

    // hover test
    @Test
    public void test07() {
        driver.get(ConfigReader.URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement hoverLink = driver.findElement(By.cssSelector("#root > div > div.sidebar > a:nth-child(6)"));
        hoverLink.click();

        WebElement hoverButton = driver.findElement(By.cssSelector("#root > div > div.content > div > div.container-table.text-center.container > div > button"));
//        hoverButton.click();
        Actions actions = new Actions(driver);
//        actions.moveToElement(hoverButton);
//        actions.build();
//        actions.perform();
        actions.moveToElement(hoverButton).build().perform();

        WebElement itemMenu = driver.findElement(By.cssSelector("#Bird"));
        itemMenu.click();

        WebElement resultText = driver.findElement(By.id("result"));
        System.out.println(resultText.getText());
        Assert.assertEquals(resultText.getText(), "You last clicked the Bird");

    }

    // stale element test
    @Test
    public void test08() {
        driver.get(ConfigReader.URL);

        WebElement staleTab = driver.findElement(By.cssSelector("#root > div > div.sidebar > a:nth-child(11)"));
        staleTab.click();

        WebElement pageText = driver.findElement(By.cssSelector("#root > div > div.content > div > div.container > div > div > h1 > small"));
        Assert.assertEquals(pageText.getText(), "Stale element (work in progress)");

        for (int i=0; i < 100 ; i++) {
            WebElement staleButton = driver.findElement(By.id("stale-button"));
            staleButton.click();
        }
    }

    // modal test
    @Test
    public void test09() {
        driver.get(ConfigReader.URL);

        WebElement modalTab = driver.findElement(By.cssSelector("#root > div > div.sidebar > a:nth-child(13)"));
        modalTab.click();

        WebElement modalText = driver.findElement(By.cssSelector("#root > div > div.content > div > div:nth-child(1) > div > div > h1 > small"));
        Assert.assertEquals(modalText.getText(), "Modal");

        WebElement launchModalButton = driver.findElement(By.cssSelector("#root > div > div.content > div > div.container-table.text-center.container > button"));
        launchModalButton.click();

        // This worked !!
//        WebElement closeModal = driver.findElement(By.cssSelector("body > div.fade.modal.show > div > div > div.modal-header > button > span:nth-child(1)"));
//        closeModal.click();


        // This worked also
//        WebElement closeModal = driver.findElement(By.cssSelector("body > div.fade.modal.show > div > div > div.modal-footer > button"));
        //closeModal.click();

        // alternative to click on the modal button is to send the ESC key to the modal
//        closeModal.sendKeys(Keys.ESCAPE);

        // Alternative to dismiss a modal if there is no close button
        WebElement modalTitle = driver.findElement(By.cssSelector("body > div.fade.modal.show > div > div > div.modal-header > div"));
        Actions actions = new Actions(driver);
        actions.click(modalTitle).sendKeys(Keys.ESCAPE).build().perform();

        modalTab.click();

    }


}
