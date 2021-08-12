package Tests.TestNgTests.DemoTests;

import Tests.TestNgTests.BaseClass;
import Utils.GeneralUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class PcGarageTest extends BaseClass {

    @Test
    public void authTest() {
        driver.get("https://www.pcgarage.ro/");

        WebElement myAccount = driver.findElement(By.cssSelector("#h_account_top > a"));
        Assert.assertEquals(myAccount.getText(), "Contul meu");

        myAccount.click();

        WebElement authText = driver.findElement(By.cssSelector("#auth-login-wrapper > h1"));
        Assert.assertEquals(authText.getText(), "Autentificare");

    }

    @Test
    public void hoverTest() {
        driver.get("https://www.pcgarage.ro/");

        WebElement myAccount = driver.findElement(By.cssSelector("#h_account_top > a"));
        Assert.assertEquals(myAccount.getText(), "Contul meu");
        GeneralUtils.genericSleep(2000);

        WebElement componente = driver.findElement(By.cssSelector("#cat_3 > a"));
        Actions actions = new Actions(driver);
        actions.moveToElement(componente).build().perform();

        GeneralUtils.genericSleep(2000);

        WebElement placiVideo = driver.findElement(By.cssSelector("#subcats_3 > div.menu_row > div:nth-child(1) > a:nth-child(1)"));
        placiVideo.click();

        GeneralUtils.genericSleep(2000);

        WebElement placiVideoText = driver.findElement(By.cssSelector("#category_name > h1 > b"));
        Assert.assertEquals(placiVideoText.getText(), "Placi video");

    }

}
