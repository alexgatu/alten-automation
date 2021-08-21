package Tests.TestNgTests.LoginTests.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class LoginPage {

    @FindBy(how = How.CSS, using = "small.text-muted")
    private WebElement authText;

    @FindBy(how = How.CSS, using = "#login-form > div:nth-child(1) > label")
    private WebElement usernameLabel;

    @FindBy(how = How.CSS, using = "#login-form > div.form-group.row.row-cols-lg-true > label")
    private WebElement passwordLabel;

    @FindBy(how = How.CSS, using = "#input-login-username")
    private WebElement usernameInput;

    @FindBy(how = How.CSS, using = "#input-login-password")
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

    public boolean checkCurrentPage() {
        boolean currentPageOK = true;
        if (!authText.getText().equals("Authentication")) {
            currentPageOK = false;
        }
        if (!usernameLabel.getText().equals("Username")) {
            currentPageOK = false;
        }
        if (!passwordLabel.getText().equals("Password")) {
            currentPageOK = false;
        }
        return currentPageOK;
    }

    public void checkCurrentPage2() {
        Assert.assertEquals(authText.getText(), "Authentication");
        Assert.assertEquals(usernameLabel.getText(), "Username");
        Assert.assertEquals(passwordLabel.getText(), "Password");
    }

    public void validateUserError(String userError) {
        Assert.assertEquals(userErr.getText(), userError);
    }

    public void validateErrors(String ue, String pe, String ge) {
        Assert.assertEquals(userErr.getText(), ue);
        Assert.assertEquals(passErr.getText(), pe);
        String genErr = " ";
        if (!ge.equals("")) {
            genErr = ge;
        }
        Assert.assertEquals(generalErr.getText(), genErr);
    }

}
