package Tests.TestNgTests.DemoTests;

import Tests.TestNgTests.BaseClass;
import Utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class AuthenticationTests extends BaseClass {

    @Test
    public void authTest01() {
        driver.get(ConfigReader.URL);

        // Going to the authentication page
        WebElement loginPage = driver.findElement(By.cssSelector("#root > div > div.sidebar > a:nth-child(2)"));
        loginPage.click();

        // Identify elements from page
        WebElement usernameInput = driver.findElement(By.id("input-login-username"));
        WebElement passwordInput = driver.findElement(By.id("input-login-password"));
        WebElement submitButton = driver.findElement(By.cssSelector("#login-form > div:nth-child(3) > div.text-left.col-lg > button"));

        // Clear the username input and fill it with "camel"
        usernameInput.clear();
        usernameInput.sendKeys("camel");

        // Clear the password input and fill it with "camel"
        passwordInput.clear();
        passwordInput.sendKeys("camelpassword");

        // Submit the data to the server
        submitButton.submit();


    }

}
