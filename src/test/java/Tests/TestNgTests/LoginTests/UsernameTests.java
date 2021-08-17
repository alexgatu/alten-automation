package Tests.TestNgTests.LoginTests;

import Tests.TestNgTests.BaseClass;
import Tests.TestNgTests.LoginTests.Models.AccountModel;
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

// page factory design patter - find element right before we used

public class UsernameTests extends BaseClass {

    String authTextSelector = "small.text-muted";
    String usernameLabelSelector = "#login-form > div:nth-child(1) > label";
    String passwordLabelSelector = "#login-form > div.form-group.row.row-cols-lg-true > label";
    String usernameInputSelector = "username";
    String passwordInputSelector = "password";
    String submitButtonSelector = "#login-form > div:nth-child(3) > div.text-left.col-lg > button";
    String userErrSelector = "#login-form > div:nth-child(1) > div > div > div.text-left.invalid-feedback";
    String passErrSelector = "#login-form > div.form-group.row.row-cols-lg-true > div > div > div.text-left.invalid-feedback";
    String generalErrSelector = "#login-form > div:nth-child(3) > div:nth-child(1)";

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
        WebElement usernameLabel = driver.findElement(By.cssSelector(usernameLabelSelector));
        WebElement passwordLabel = driver.findElement(By.cssSelector(passwordLabelSelector));
        Assert.assertEquals(usernameLabel.getText(),"Username");
        Assert.assertEquals(passwordLabel.getText(),"Password");

        // Assert that the "Authentication" text is present on the page
        WebElement authBannerText = driver.findElement(By.cssSelector(authTextSelector));
        Assert.assertEquals(authBannerText.getText(),"Authentication");

        // Insert into the username field the number of characters for each case
        // Identify the username field in the page and insert a random string with specific length and charset type
        WebElement usernameInput = driver.findElement(By.name(usernameInputSelector));
        WebElement passwordInput = driver.findElement(By.name(passwordInputSelector));
        usernameInput.clear();
        usernameInput.sendKeys(
                        GeneralUtils.getRandomStringByLength(Integer.parseInt(lengthString),
                        Integer.parseInt(charsetType)));
        passwordInput.clear();
        passwordInput.sendKeys(GeneralUtils.getRandomStringByLength(8, 5));

        // If the username is invalid (not registered) there is an error generated. No errors related to the length of the username is displayed.
        // Grab the error message (if any) from the username field and assert that the message is as expected
        WebElement usernameError = driver.findElement(By.cssSelector(userErrSelector));
        Assert.assertEquals(usernameError.getText(),errorMessage);
    }

    @DataProvider(name = "authenticationDataProvider")
    public Iterator<Object []> authDataProvider() {
        Collection<Object[]> dp = new ArrayList<>();
        dp.add(new String[] { "dinosaur", "dinosaurpassword", "", "", " "});
        dp.add(new String[] { "dingo", "dingopassword", "", "", " " });
        dp.add(new String[] { "camel", "camelpassword", "", "", " " });
        dp.add(new String[] { "zebra", "zebrapassword", "", "", " " });
        dp.add(new String[] { "8", "8", "", "", "Account does not exist"  });
        dp.add(new String[] { "0", "8", "Username is required!", "", " " });
        dp.add(new String[] { "8", "0", "", "Password is required!", " " });
        dp.add(new String[] { "0", "0", "Username is required!", "Password is required!", " " });
        return dp.iterator();
    }

    //ALT-544:Username with register user
    //ALT-546:Username with unregistered user
    // test input filed and general error messages
    @Test(dataProvider = "authenticationDataProvider")
    public void authTests(String username, String password, String userError, String passErr, String generalErr) {
        // Go to authentication page
        driver.get(URL + "#/login");

        // Verify that the login page is displayed
        WebElement usernameLabel = driver.findElement(By.cssSelector(usernameLabelSelector));
        WebElement passwordLabel = driver.findElement(By.cssSelector(passwordLabelSelector));
        Assert.assertEquals(usernameLabel.getText(),"Username");
        Assert.assertEquals(passwordLabel.getText(),"Password");
        // Assert that the "Authentication" text is present on the page
        WebElement authBannerText = driver.findElement(By.cssSelector(authTextSelector));
        Assert.assertEquals(authBannerText.getText(),"Authentication");

        // Identify the username and password fields and submit button
        WebElement usernameInput = driver.findElement(By.name(usernameInputSelector));
        WebElement passwordInput = driver.findElement(By.name(passwordInputSelector));
        WebElement submitButton = driver.findElement(By.cssSelector(submitButtonSelector));

        // Clear the inputs and populate with data
        usernameInput.clear();
        usernameInput.sendKeys(GeneralUtils.determineInputData(username));
        passwordInput.clear();
        passwordInput.sendKeys(GeneralUtils.determineInputData(password));

        // submit the data and verify the general error message
        submitButton.submit();

        // Verify the username/password errors
        WebElement usernameErrorText = driver.findElement(By.cssSelector(userErrSelector));
        WebElement passwordErrorText = driver.findElement(By.cssSelector(passErrSelector));

        Assert.assertEquals(usernameErrorText.getText(),userError);
        Assert.assertEquals(passwordErrorText.getText(), passErr);


        WebElement generalErrorText = driver.findElement(By.cssSelector(generalErrSelector));
        Assert.assertEquals(generalErrorText.getText(), generalErr);

    }


}
