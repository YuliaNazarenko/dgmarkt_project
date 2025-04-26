package pages;

import context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ConfigurationReader;

import java.util.List;

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
    public WebElement currency;

    @FindBy(xpath = "//*[@class='currency-select btn btn-link btn-block']")
    public List<WebElement> listOfCurrencies;

    @FindBy(xpath = "//*[@id='form-currency']/div/button/span[1]")
    public WebElement currentCurrency;


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
        currency.click();
        String targetCurrency = "USD";

        for (WebElement currency : listOfCurrencies) {
            String currencyText = currency.getText();
            if (currencyText.equalsIgnoreCase(targetCurrency)) {
                currency.click();
                break;
            }
        }
        return context.wait.until(ExpectedConditions.visibilityOf(currentCurrency)).getText();
    }
}
