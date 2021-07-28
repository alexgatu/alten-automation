package Tests.TestNgTests.LoginTests;

import Tests.TestNgTests.BaseClass;
import org.testng.annotations.Test;

import static Utils.ConfigReader.URL;

public class PasswordTests extends BaseClass {

    /*
    Verify that password is not allowed less the minimum length of 8 characters
    */
    @Test
    public void passwordLessMin() {
        driver.get(URL + "#/login");
        // Assert that the "Authentication" text is present on the page
        // Identify the username field and input a valid user
        // Identify the password field insert a random password with 7 characters
        // Click on submit button
        // Assert that the error message received on the password field is correct
    }

    /*
    Verify that password input is validated correctly with minimum length of 8 characters
     */
    @Test
    public void passwordMinLength() {
        driver.get(URL + "#/login");
        // Assert that the "Authentication" text is present on the page
        // Identify the username field and input a valid user
        // Identify the password field insert a random password with 8 characters
        // Click on submit button
        // Assert that the error message received on the password field is correct - password invalid and not related to length
    }

    /*
    Verify that password input is validated correctly with max length of 20 chars
     */
    @Test
    public void passwordMaxLength() {
        driver.get(URL + "#/login");
        // Assert that the "Authentication" text is present on the page
        // Identify the username field and input a valid user
        // Identify the password field insert a random password with 20 characters
        // Click on submit button
        // Assert that the error message received on the password field is correct - password invalid and not related to length
    }

    /*
    Verify that password input is validated correctly with more than 20 characters
     */
    @Test
    public void passwordMoreMax() {
        driver.get(URL + "#/login");
        // Assert that the "Authentication" text is present on the page
        // Identify the username field and input a valid user
        // Identify the password field insert a random password with 21 characters
        // Click on submit button
        // Assert that the error message received on the password field is correct - exceeds max length
    }

    /*
    Verify that password allows special characters and alphanumeric
     */
    @Test
    public void passwordCharsetTest() {
        driver.get(URL + "#/login");
        // Assert that the "Authentication" text is present on the page
        // Identify the username field and input a valid user
        // Identify the password field insert a random password between 8-20 characters with special and alphanumeric chars
        // Click on submit button
        // Assert that the error message is related only to the fact that the password is invalid for the corresponding username
    }

    /*
    Verify that password field does not allow null
     */
    @Test
    public void passwordNull() {
        driver.get(URL + "#/login");
        // Assert that the "Authentication" text is present on the page
        // Identify the username field and input a valid user
        // Identify the password field and clear the field
        // Assert that the length of the password field is 0
        // Click on the submit button
        // Assert that there is an error message that is expected on the password field
    }

    /*
    Verify the valid password / valid username combination
     */
    @Test
    public void passwordUsernameRegistered() {
        driver.get(URL + "#/login");
        // Assert that the "Authentication" text is present on the page
        // Identify the username field and input a valid user
        // Identify the password field and input a valid password for that user
        // Click on the submit button
        // Assert that there are no errors in the page
        // Assert that the user is authenticated
    }
}
