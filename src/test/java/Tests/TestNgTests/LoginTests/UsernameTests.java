package Tests.TestNgTests.LoginTests;

import Tests.TestNgTests.BaseClass;
import org.testng.annotations.Test;

import static Utils.ConstantsUtils.URL_BASE;

public class UsernameTests extends BaseClass {

    //ALT-538:Username with more than maximum length
    @Test
    public void usernameMoreMaxLength() {
        // Go to authentication page
        driver.get(URL_BASE + "#/login");

        // Verify that the login page is displayed
        // Assert that the "Authentication" text is present on the page

        // Insert into the username field more than maximum number of characters allowed
        // Identify the username field and input a random string with length 9

        // Verify that there is an error on the page related to the max length of the username exceeded
        // Grab the error message from the page and assert that it is correct
    }

    //ALT-536:Username with minimum length
    @Test
    public void usernameMinimumLength(){
        // Go to authentication page
        driver.get(URL_BASE + "#/login");

        // Verify that the login page is displayed
        // Assert that the "Authentication" text is present on the page

        // Insert into the username field a random string with the length minimum allowed (3 characters)
        // Identify the username field in the page and insert a random string with length 3

        // Verify that there are no errors related to the minimum length of the username
        // Grab the error message from the username field if exists and check that is is not the error message related
        // with the minimum length
    }

    //ALT-531:Username with less than minimum length
    @Test
    public void usernameLessMinLength() {
        // Go to authentication page
        driver.get(URL_BASE + "#/login");

        // Verify that the login page is displayed
        // Assert that the "Authentication" text is present on the page

        // Insert into the username field less than the number of minimum characters allowed
        //Identify the username field in the page and insert a random string with length 2

        //The application responds with an error that the number of characters are less than minimum
        // Grab the error message from the username field ad verify that the message is correct
    }

    // ALT-534:Username with maximum length
    @Test
    public void usernameMaxLength() {
        // Go to authentication page
        driver.get(URL_BASE + "#/login");

        // Verify that the login page is displayed
        // Assert that the "Authentication" text is present on the page

        // Insert into the username field the max number of characters allowed
        // Identify the username field in the page and insert a random string with length 8

        // If the username is invalid (not registered) there is an error generated. No errors related to the length of the username is displayed.
        // Grab the error message (if any) from the username field and assert that the message is not related with max length
    }

    //ALT-539:Username with special chars
    @Test
    public void usernameSpecialChars() {
        // Go to authentication page
        driver.get(URL_BASE + "#/login");

        // Verify that the login page is displayed
        // Assert that the "Authentication" text is present on the page

        // In the username field insert a random string between 3-8 chars that contains special chars
        // Identify the username field, generate a random string with special chars and input in the field

        // There is an error related with the fact that special characters are not allowed
        // Grab the error message from the username field and assert that the message is correct
    }

    //ALT-540:Username with alphanumeric chars
    @Test
    public void usernameAlphanumeric() {
        // Go to authentication page
        driver.get(URL_BASE + "#/login");

        // Verify that the login page is displayed
        // Assert that the "Authentication" text is present on the page

        // In the username field insert a random string between 3-8 chars that contains both numerical and alphabetical
        // Identify the username field, generate a random string with numerical chars and input in the field

        // There is an error related with the fact that numerical chars are not allowed
        // Grab the error message from the username field and assert that the message is correct
    }

    // ALT-542:Username with null
    @Test
    public void usernameNull(){
        // Go to authentication page
        driver.get(URL_BASE + "#/login");

        // Verify that the login page is displayed
        // Assert that the "Authentication" text is present on the page

        // In the username field do not insert any chars
        // Identify the username field, clear if any chars are present, assert that the length of th input is 0

        // There is an error related with the fact that null input is not allowed
        // Grab the error message from the username field and assert that the message is correct
    }

    //ALT-544:Username with register user
    @Test
    public void usernameRegistered() {
        // Go to authentication page
        driver.get(URL_BASE + "#/login");

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
        driver.get(URL_BASE + "#/login");

        // Verify that the login page is displayed
        // Assert that the "Authentication" text is present on the page

        //Input a unregistered username without any passwords
        // Identify the username and password fields and input in the username field an unregistered user
        // CLear the password field and assert that the length is 0
        // Grab the error message from the username and assert that the message is correct - user not exists
    }

}
