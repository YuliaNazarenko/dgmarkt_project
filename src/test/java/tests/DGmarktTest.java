package tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginForm;
import pages.RegisterAccountForm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DGmarktTest extends BaseTest {

    @Test
    public void loginWithEmptyFieldsTest() {

        HomePage homePage = new HomePage(context);
        homePage.openLoginForm();

        LoginForm loginForm = new LoginForm(context);
        loginForm.clickLogInButton();

        String alertMessageText = loginForm.checkAlertMassage();

        assertEquals("Returning Customer", loginForm.getHeaderText());

        assertTrue(alertMessageText.equals("Warning: No match for E-Mail Address and/or Password.\n×")
                        || alertMessageText.equals("Warning: Your account has exceeded allowed number of login attempts. " +
                        "Please try again in 1 hour.\n×"),
                "Message text does not match any of the expected values");
    }

    @Test
    public void forgotPasswordSendEmailTest() {
        HomePage homePage = new HomePage(context);
        homePage.openLoginForm();
        LoginForm loginForm = new LoginForm(context);
        loginForm.clickForgottenPasswordLink();
        String emailSentConfirmMassageText = homePage.sendEmailToResetPassword();

        assertEquals("An email with a confirmation link has been sent your email address.",
                emailSentConfirmMassageText);
    }

    @Test
    public void forgotPasswordInvalidEmailTest() {
        HomePage homePage = new HomePage(context);
        homePage.openLoginForm();
        LoginForm loginForm = new LoginForm(context);
        loginForm.clickForgottenPasswordLink();
        String warningMassageText = homePage.sendInvalidEmailToResetPassword();

        assertEquals("Warning: The E-Mail Address was not found in our records, please try again!",
                warningMassageText);
    }

    @Test
    @Disabled("every test needed a new email")
    public void registerAccount() {
        LoginForm loginForm = new LoginForm(context);
        loginForm.openCreateAccountForm();
        RegisterAccountForm registerAccountForm = new RegisterAccountForm(context);
        String accountSuccessText = registerAccountForm.feelRegisterForm();

        assertEquals("Your Account Has Been Created!", accountSuccessText);
    }

    @Test
    public void loginValidData() {
        LoginForm loginForm = new LoginForm(context);
        String alertText = loginForm.login();

        assertEquals("Congratulation! Login Successfully\n×", alertText);
    }

    @Test
    public void changeCurrency() {
        HomePage homePage = new HomePage(context);
        String currentCurrency = homePage.changeCurrency();

        assertEquals("£", currentCurrency);
    }
}
