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
    public WebElement alertMessage;

    @FindBy(css = "div[class='register-form-content'] h3")
    public WebElement registerForm;

    @FindBy(css = "[class='forgotten']")
    public WebElement forgottenPasswordLink;

    @FindBy(css = "#content p")
    public WebElement enterEmailMassage;

    @FindBy(css = "div[class='alert alert-success']")
    WebElement alertSuccess;

    @Step("Get the login form header text")
    public String getHeaderText() {
        return loginFormHeader.getText();
    }

    @Step("Click a login button")
    public void clickLogInButton() {
        context.wait.until(ExpectedConditions.visibilityOf(logInButton)).click();
    }

    @Step("Check an alert massage in login form")
    public String checkAlertMassage(){
        return context.wait.until(ExpectedConditions.visibilityOf(alertMessage)).getText();
    }

    @Step("Open a form for new account creation")
    public void openCreateAccountForm() {
        HomePage homePage = new HomePage(context);
        homePage.openLoginForm();
        context.wait.until(ExpectedConditions.visibilityOf(createAccountButton)).click();
        String registerFormText = context.wait.until(ExpectedConditions.visibilityOf(registerForm)).getText();

        assertEquals("Register Account", registerFormText);
    }

    @Step("Click on the 'Forgotten password' link")
    public void clickForgottenPasswordLink() {
        context.wait.until(ExpectedConditions.visibilityOf(forgottenPasswordLink)).click();
        String enterEmailMassageText = context.wait.until(ExpectedConditions.visibilityOf(enterEmailMassage)).getText();

        assertEquals("Enter the e-mail address associated with your account. " +
                "Click submit to have a password reset link e-mailed to you.", enterEmailMassageText);
    }

    @Step("Login to account with valid data")
    public String login(){
        HomePage homePage = new HomePage(context);
        homePage.openLoginForm();
        context.wait.until(ExpectedConditions.visibilityOf(inputLogin)).sendKeys(ConfigurationReader.get("TestEmail"));
        inputPassword.sendKeys(ConfigurationReader.get("TestPassword"));
        clickLogInButton();

        return context.wait.until(ExpectedConditions.visibilityOf(alertSuccess)).getText();
    }
}


