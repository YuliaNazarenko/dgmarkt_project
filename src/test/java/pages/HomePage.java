package pages;

import context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ConfigurationReader;

public class HomePage extends BasePage {

    public HomePage(TestContext context) {
        super(context);
    }

    @FindBy(css = "a[data-toggle='dropdown']")
    public WebElement myAccount;
    @FindBy(id = "pt-login-link")
    public WebElement logIn;

    @FindBy(css = "input[id= input-email]")
    public WebElement emailInput;
    @FindBy(css = "input[value= Continue]")
    public WebElement continueButton;
    @FindBy(css = "div[class='alert alert-success alert-dismissible']")
    public WebElement emailSentConfirmMassage;
    @FindBy(css = "div[class='alert alert-danger alert-dismissible']")
    public WebElement warningMassage;

    @FindBy(css = "[id='form-currency']")
    public WebElement selectCurrency;

    @FindBy(xpath = "//*[@id='form-currency']/div/button/span[1]")
    public WebElement currentCurrency;

    @FindBy(css = "#form-currency > div > ul > li:nth-child(2) > button")
    public WebElement pound;


    public void openLoginForm() {
        context.wait.until(ExpectedConditions.visibilityOf(myAccount)).click();
        context.wait.until(ExpectedConditions.visibilityOf(logIn)).click();
    }

    public String sendEmailToResetPassword() {

        emailInput.sendKeys(ConfigurationReader.get("userName"));
        continueButton.click();

        return context.wait.until(ExpectedConditions.visibilityOf(emailSentConfirmMassage)).getText();
    }

    public String sendInvalidEmailToResetPassword() {

        emailInput.sendKeys(ConfigurationReader.get("invalidEmail"));
        continueButton.click();

        return context.wait.until(ExpectedConditions.visibilityOf(warningMassage)).getText();
    }

    public String changeCurrency() {
        selectCurrency.click();
        pound.click();
        return context.wait.until(ExpectedConditions.visibilityOf(currentCurrency)).getText();
    }
}
