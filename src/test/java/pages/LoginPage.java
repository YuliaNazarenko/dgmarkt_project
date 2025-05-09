package pages;

import context.TestContext;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ConfigurationReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPage extends BasePage {
    public LoginPage(TestContext context) {
        super(context);
    }

//Elements-----------------------------------------------------------

    @FindBy(css = "[class=login-form-content] h3")
    public WebElement loginFormHeader;

    @FindBy(css = "input[id='input-email']")
    public WebElement inputLogin;

    @FindBy(css = "input[id='input-password']")
    public WebElement inputPassword;

    @FindBy(css = "[onclick*='ptlogin.loginAction']")
    public WebElement logInButton;

    @FindBy(css = "button[onclick='ptlogin.appendRegisterForm()']")
    public WebElement createAccountButton;

    @FindBy(xpath = "//*[@class='alert alert-danger']")
    public WebElement alertMassageWarning;

    @FindBy(css = "[class='forgotten']")
    public WebElement forgottenPasswordLink;

    @FindBy(css = "#content p")
    public WebElement enterEmailMassage;

    @FindBy(css = "div[class='alert alert-success']")
    WebElement alertSuccess;

    @FindBy(css = "input[value= Continue]")
    public WebElement continueButton;

    @FindBy(css = "div[class='alert alert-success alert-dismissible']")
    public WebElement emailSentConfirmMassage;

    @FindBy(css = "div[class='alert alert-danger alert-dismissible']")
    public WebElement warningMassage;

//Steps------------------------------------------------------------

    @Step("Get the Login form header text")
    public String getHeaderText() {
        return loginFormHeader.getText();
    }

    @Step("Click Login button")
    public void clickLogInButton() {
        context.wait.until(ExpectedConditions.visibilityOf(logInButton)).click();
    }

    @Step("Check an Warning alert massage in Login form")
    public String checkAlertMassage() {
        return context.wait.until(ExpectedConditions.visibilityOf(alertMassageWarning)).getText();
    }

    @Step("Open a form for new account creation")
    public String openCreateAccountForm() {
        HomePage homePage = new HomePage(context);
        homePage.openLoginForm();
        context.wait.until(ExpectedConditions.visibilityOf(createAccountButton)).click();
        return context.wait.until(ExpectedConditions.visibilityOf(new RegisterAccountPage(context).registerForm)).getText();
    }

    @Step("Click on the 'Forgotten password' link")
    public void clickForgottenPasswordLink() {
        context.wait.until(ExpectedConditions.visibilityOf(forgottenPasswordLink)).click();
        String enterEmailMassageText = context.wait.until(ExpectedConditions.visibilityOf(enterEmailMassage)).getText();

        assertEquals("Enter the e-mail address associated with your account. " +
                "Click submit to have a password reset link e-mailed to you.", enterEmailMassageText);
    }

    @Step("Sending a valid e-mail in reset password form")
    public String sendEmailToResetPassword() {

        inputLogin.sendKeys(ConfigurationReader.get("userName"));
        continueButton.click();
        return context.wait.until(ExpectedConditions.visibilityOf(emailSentConfirmMassage)).getText();
    }

    @Step("Sending an invalid e-mail in reset password form")
    public String sendInvalidEmailToResetPassword() {

        inputLogin.sendKeys(ConfigurationReader.get("invalidEmail"));
        continueButton.click();
        return context.wait.until(ExpectedConditions.visibilityOf(warningMassage)).getText();
    }

    @Step("Login to account with valid data")
    public String loginWithValidData() {
        HomePage homePage = new HomePage(context);
        homePage.openLoginForm();
        context.wait.until(ExpectedConditions.visibilityOf(inputLogin)).sendKeys(ConfigurationReader.get("TestEmail"));
        inputPassword.sendKeys(ConfigurationReader.get("TestPassword"));
        clickLogInButton();

        return context.wait.until(ExpectedConditions.visibilityOf(alertSuccess)).getText();
    }

    @Step("Login to account with loginWithValidData: {loginWithValidData} and password: {password}")
    public String loginWithValidParameters(String login, String password) {

        context.wait.until(ExpectedConditions.visibilityOf(inputLogin)).sendKeys(login);
        inputPassword.sendKeys(password);
        clickLogInButton();
        context.wait.until(ExpectedConditions.visibilityOf(alertSuccess)).getText();
        return alertSuccess.getText();

    }
}


