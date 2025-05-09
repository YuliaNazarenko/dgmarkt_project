package tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterAccountPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterAccountTest extends BaseTest {

    @Test
    @Disabled("every test needed a new email")
    @DisplayName("A new account registration through the Login page")
    public void registerAccountLoginPage() {
        LoginPage loginPage = new LoginPage(context);
        String registerFormText = loginPage.openCreateAccountForm();
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(context);
        String accountSuccessText = registerAccountPage.fillRegisterForm();

        assertEquals("Register Account", registerFormText);
        assertEquals("Your Account Has Been Created!", accountSuccessText);
    }

    @Test
    @Disabled("every test needed a new email")
    @DisplayName("A new account registration")
    public void registerAccountRegisterPage() {
        HomePage homePage = new HomePage(context);
        homePage.myAccount.click();
        homePage.register.click();
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(context);
        String accountSuccessText = registerAccountPage.fillRegisterForm();

        assertEquals("Your Account Has Been Created!", accountSuccessText);
    }
}
