package Tests.TestNgTests.LoginTests;

import Tests.TestNgTests.BaseClass;
import Utils.GeneralUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static Utils.ConfigReader.URL;

public class UsernameTests extends BaseClass {

    @DataProvider(name = "UsernameDataProvider")
    public Iterator<Object[]> usernameData() {
        Collection<Object[]> dp = new ArrayList<>();
        dp.add(new String[] { "9", "Username exceeded maximum length" , "1"});
        dp.add(new String[] { "3" ,"", "1" });
        dp.add(new String[] { "2", "Username below minimum number of characters", "1" });
        dp.add(new String[] { "8", "", "1" });
        dp.add(new String[] { "8", "Username does not allow special characters", "4" });
        dp.add(new String[] { "8", "Username does not allow numbers", "3" });
        dp.add(new String[] { "8", "Username does not allow numbers", "6" });
        dp.add(new String[] { "0", "Username is required!", "1" });
        return dp.iterator();
    }

    //ALT-538:Username with more than maximum length
    //ALT-536:Username with minimum length
    //ALT-531:Username with less than minimum length
    //ALT-534:Username with maximum length
    //ALT-539:Username with special chars
    //ALT-540:Username with alphanumeric chars
    //ALT-542:Username with null
    // BONUS - username with numbers only
    @Test(dataProvider = "UsernameDataProvider")
    public void testUsernameLength(String lengthString, String errorMessage, String charsetType) {
        System.out.println(lengthString + " " + errorMessage);
        // Go to authentication page
        driver.get(URL + "#/login");

        // Verify that the login page is displayed
        WebElement usernameLabel = driver.findElement(By.cssSelector("#login-form > div:nth-child(1) > label"));
        WebElement passwordLabel = driver.findElement(By.cssSelector("#login-form > div.form-group.row.row-cols-lg-true > label"));
        Assert.assertEquals(usernameLabel.getText(),"Username");
        Assert.assertEquals(passwordLabel.getText(),"Password");

        // Assert that the "Authentication" text is present on the page
        WebElement authBannerText = driver.findElement(By.cssSelector("small.text-muted"));
        Assert.assertEquals(authBannerText.getText(),"Authentication");

        // Insert into the username field the number of characters for each case
        // Identify the username field in the page and insert a random string with specific length and charset type
        WebElement usernameInput = driver.findElement(By.name("username"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        usernameInput.clear();
        usernameInput.sendKeys(
                        GeneralUtils.getRandomStringByLength(Integer.parseInt(lengthString),
                        Integer.parseInt(charsetType)));
        passwordInput.clear();
        passwordInput.sendKeys(GeneralUtils.getRandomStringByLength(8, 5));

        // If the username is invalid (not registered) there is an error generated. No errors related to the length of the username is displayed.
        // Grab the error message (if any) from the username field and assert that the message is as expected
        WebElement usernameError = driver.findElement(By.cssSelector("#login-form > div:nth-child(1) > div > div > div.text-left.invalid-feedback"));
        Assert.assertEquals(usernameError.getText(),errorMessage);
    }

    //ALT-544:Username with register user
    @Test
    public void usernameRegistered() {
        // Go to authentication page
        driver.get(URL + "#/login");

        // Verify that the login page is displayed
        // Assert that the "Authentication" text is present on the page

        //Input a registered username without any passwords
        // Identify the username and password fields and input in the username field a registered user
        // CLear the password field and assert that the length is 0
        // Grab the error message from the username (if any) and assert that the message is correct

    }

    // ALT-546:Username with unregistered user
    @Test
    public void usernameUnregistered() {
        // Go to authentication page
        driver.get(URL + "#/login");

        // Verify that the login page is displayed
        // Assert that the "Authentication" text is present on the page

        //Input a unregistered username without any passwords
        // Identify the username and password fields and input in the username field an unregistered user
        // CLear the password field and assert that the length is 0
        // Grab the error message from the username and assert that the message is correct - user not exists
    }

}
