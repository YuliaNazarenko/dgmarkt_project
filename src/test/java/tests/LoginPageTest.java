package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPageTest extends BaseTest {
    @Test
    @DisplayName("Login to account with empty fields")
    public void loginWithEmptyFieldsTest() {

        HomePage homePage = new HomePage(context);
        homePage.openLoginForm();

        LoginPage loginPage = new LoginPage(context);
        loginPage.clickLogInButton();

        String alertMessageText = loginPage.checkAlertMassage();

        assertEquals("Returning Customer", loginPage.getHeaderText());

        assertTrue(alertMessageText.equals("Warning: No match for E-Mail Address and/or Password.\n×")
                        || alertMessageText.equals("Warning: Your account has exceeded allowed number of login attempts. " +
                        "Please try again in 1 hour.\n×"),
                "Message text does not match any of the expected values");
    }

    @Test
    @DisplayName("Login to account with valid data")
    public void loginValidData() {
        LoginPage loginPage = new LoginPage(context);
        String alertText = loginPage.login();

        assertEquals("Congratulation! Login Successfully\n×", alertText);
    }

    @Test
    @DisplayName("Test that valid email is sent, when user has forgotten the password")
    public void forgotPasswordSendEmailTest() {
        HomePage homePage = new HomePage(context);
        homePage.openLoginForm();
        LoginPage loginPage = new LoginPage(context);
        loginPage.clickForgottenPasswordLink();
        String emailSentConfirmMassageText = homePage.sendEmailToResetPassword();

        assertEquals("An email with a confirmation link has been sent your email address.",
                emailSentConfirmMassageText);
    }

    @Test
    @DisplayName("Test that invalid email is not sent, when user has forgotten the password, and Warning massage is appeared")
    public void forgotPasswordInvalidEmailTest() {
        HomePage homePage = new HomePage(context);
        homePage.openLoginForm();
        LoginPage loginPage = new LoginPage(context);
        loginPage.clickForgottenPasswordLink();
        String warningMassageText = homePage.sendInvalidEmailToResetPassword();

        assertEquals("Warning: The E-Mail Address was not found in our records, please try again!",
                warningMassageText);
    }
}
