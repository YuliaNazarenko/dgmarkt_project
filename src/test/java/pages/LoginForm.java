package pages;

import context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ConfigurationReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginForm extends BasePage {
    public LoginForm(TestContext context) {
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

    public String getHeaderText() {
        return loginFormHeader.getText();
    }
    public void clickLogInButton() {
        context.wait.until(ExpectedConditions.visibilityOf(logInButton)).click();
    }
    public String checkAlertMassage(){
        return context.wait.until(ExpectedConditions.visibilityOf(alertMessage)).getText();
    }

    public void openCreateAccountForm() {
        HomePage homePage = new HomePage(context);
        homePage.openLoginForm();
        context.wait.until(ExpectedConditions.visibilityOf(createAccountButton)).click();
        String registerFormText = context.wait.until(ExpectedConditions.visibilityOf(registerForm)).getText();

        assertEquals("Register Account", registerFormText);
    }

    public void clickForgottenPasswordLink() {
        context.wait.until(ExpectedConditions.visibilityOf(forgottenPasswordLink)).click();
        String enterEmailMassageText = context.wait.until(ExpectedConditions.visibilityOf(enterEmailMassage)).getText();

        assertEquals("Enter the e-mail address associated with your account. " +
                "Click submit to have a password reset link e-mailed to you.", enterEmailMassageText);
    }

    public String login(){
        HomePage homePage = new HomePage(context);
        homePage.openLoginForm();
        context.wait.until(ExpectedConditions.visibilityOf(inputLogin)).sendKeys(ConfigurationReader.get("TestEmail"));
        inputPassword.sendKeys(ConfigurationReader.get("TestPassword"));
        clickLogInButton();

        return context.wait.until(ExpectedConditions.visibilityOf(alertSuccess)).getText();
    }
}


