package tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.RegisterAccountPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterAccountTest extends BaseTest {
    @Test
    @Disabled("every test needed a new email")
    @DisplayName("A new account registration Test")
    public void registerAccount() {
        LoginPage loginPage = new LoginPage(context);
        loginPage.openCreateAccountForm();
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(context);
        String accountSuccessText = registerAccountPage.feelRegisterForm();

        assertEquals("Your Account Has Been Created!", accountSuccessText);
    }
}
