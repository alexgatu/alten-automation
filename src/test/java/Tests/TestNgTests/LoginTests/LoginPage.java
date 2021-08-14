package Tests.TestNgTests.LoginTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

    @FindBy(how = How.CSS, using = "small.text-muted")
    private WebElement authText;

    @FindBy(how = How.CSS, using = "#login-form > div:nth-child(1) > label")
    private WebElement usernameLabel;

    @FindBy(how = How.CSS, using = "#login-form > div.form-group.row.row-cols-lg-true > label")
    private WebElement passwordLabel;

    @FindBy(how = How.NAME, using = "username")
    private WebElement usernameInput;

    @FindBy(how = How.NAME, using = "password")
    private WebElement passwordInput;

    @FindBy(how = How.CSS, using = "#login-form > div:nth-child(3) > div.text-left.col-lg > button")
    private WebElement submitButton;

    @FindBy(how = How.CSS, using = "#login-form > div:nth-child(1) > div > div > div.text-left.invalid-feedback")
    private WebElement userErr;

    @FindBy(how = How.CSS, using = "#login-form > div.form-group.row.row-cols-lg-true > div > div > div.text-left.invalid-feedback")
    private WebElement passErr;

    @FindBy(how = How.CSS, using = "#login-form > div:nth-child(3) > div:nth-child(1)")
    private WebElement generalErr;

    public void login(String username, String password) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        submitButton.submit();
    }

}
