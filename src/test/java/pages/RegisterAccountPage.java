package pages;

import context.TestContext;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ConfigurationReader;

public class RegisterAccountPage extends BasePage {
    public RegisterAccountPage(TestContext context) {
        super(context);
    }

    @FindBy(css = "input[name='firstname']")
    public WebElement firstName;

    @FindBy(css = "input[name='lastname']")
    public WebElement lastName;

    @FindBy(css = "input[id='input-register-email']")
    public WebElement email;

    @FindBy(css = "input[id='input-telephone']")
    public WebElement telephone;

    @FindBy(css = "input[id='input-register-password']")
    public WebElement password;

    @FindBy(css = "input[id='input-confirm']")
    public WebElement confirmPassword;

    @FindBy(css = "input[name='agree']")
    public WebElement checkboxAgree;

    @FindBy(css = "input[onclick='ptlogin.registerAction();']")
    public WebElement registerContinueButton;


    @FindBy(css = "[class='account-success'] h2")
    public WebElement accountSuccess; //Your Account Has Been Created!

    @FindBy(css = "button[class='button']")
    public WebElement continueButton;


    @Step("Registration form filling")
    public String feelRegisterForm() {

        firstName.sendKeys(ConfigurationReader.get("FirstName"));
        lastName.sendKeys(ConfigurationReader.get("LastName"));
        email.sendKeys(ConfigurationReader.get("TestEmail"));
        telephone.sendKeys(ConfigurationReader.get("Telephone"));
        password.sendKeys(ConfigurationReader.get("TestPassword"));
        confirmPassword.sendKeys(ConfigurationReader.get("TestPassword"));

        checkboxAgree.click();
        registerContinueButton.click();

        String accountSuccessText = context.wait.until(ExpectedConditions.visibilityOf(accountSuccess)).getText();
        continueButton.click();
        context.wait.until(ExpectedConditions.urlToBe("https://dgmarkt.com/index.php?route=account/account"));

        return accountSuccessText;
    }
}
