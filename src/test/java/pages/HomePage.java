package pages;

import context.TestContext;
import io.qameta.allure.Step;
import org.openqa.selenium.StaleElementReferenceException;
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

    @FindBy(xpath = "//*[@id='form-currency']//button/span[1]")
    public WebElement currentCurrency;

    @FindBy(css = "div[class='dropdown-toggle search-button']")
    public WebElement searchOpen;

    @FindBy(css = "input[id = 'text-search']")
    public WebElement inputTextSearch;

    @FindBy(css = "button[id='btn-search-category']")
    public WebElement search;

    @FindBy(xpath = "//a[contains(text(), 'Nicky Clarke')]")
    public List<WebElement> nickyClarkeItems;

    @FindBy(css = "button[id='button-cart']")
    public WebElement addToCartButton;

    @FindBy(xpath = "//*[@class='btn-group btn-block']")
    public WebElement shoppingBag;

    @FindBy(css = "a[href*='checkout/checkout']")
    public WebElement checkoutButton;

    @FindBy(xpath = "//*[@class ='alert alert-fix alert-success alert-dismissible']")
    public WebElement alertMessage;

    @Step("Opening a login form")
    public void openLoginForm() {
        context.wait.until(ExpectedConditions.visibilityOf(myAccount)).click();
        context.wait.until(ExpectedConditions.visibilityOf(logIn)).click();
    }

    @Step("Sending a valid e-mail in reset password form")
    public String sendEmailToResetPassword() {

        emailInput.sendKeys(ConfigurationReader.get("userName"));
        continueButton.click();
        return context.wait.until(ExpectedConditions.visibilityOf(emailSentConfirmMassage)).getText();
    }

    @Step("Sending an invalid e-mail in reset password form")
    public String sendInvalidEmailToResetPassword() {

        emailInput.sendKeys(ConfigurationReader.get("invalidEmail"));
        continueButton.click();
        return context.wait.until(ExpectedConditions.visibilityOf(warningMassage)).getText();
    }

    @Step("Changing currency")
    public String changeCurrency() {
        context.wait.until(ExpectedConditions.visibilityOf(currency)).click();
        String targetCurrency = ConfigurationReader.get("TestChangeCurrency");

        for (WebElement currency : listOfCurrencies) {
            String currencyText = currency.getText();
            if (currencyText.equalsIgnoreCase(targetCurrency)) {
                currency.click();
                break;
            }
        }
        return context.wait.until(ExpectedConditions.visibilityOf(currentCurrency)).getText();
    }

    @Step("Add item to cart using Search")
    public String addToCartSearch() {
        context.wait.until(ExpectedConditions.visibilityOf(searchOpen)).click();
        context.wait.until(ExpectedConditions.visibilityOf(inputTextSearch)).sendKeys("Nicky Clarke");
        search.click();

        String targetItem = (ConfigurationReader.get("searching_item"));

        for (WebElement item : nickyClarkeItems) {
            String itemText = item.getText();
            if (itemText.equalsIgnoreCase(targetItem)) {
                item.click();
                break;
            }
        }
        context.wait.until(ExpectedConditions.visibilityOf(addToCartButton)).click();
        return context.wait.until(ExpectedConditions.visibilityOf(alertMessage)).getText();
    }

    @Step("Add item to shopping bag and go to checkout page")
    public CheckoutPage addToCardQuick() {
        context.driver.get(ConfigurationReader.get("item_url"));
        context.wait.until(ExpectedConditions.visibilityOf(addToCartButton)).click();
        context.wait.until(ExpectedConditions.visibilityOf(shoppingBag)).click();
        int attempts = 0;
        while (attempts < 3) {
            try {
                context.wait.until(ExpectedConditions.visibilityOf(checkoutButton));
                checkoutButton.click();
                break;
            } catch (StaleElementReferenceException e) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                }
            } catch (Exception e) {
                break;
            }
            attempts++;
        }
        return new CheckoutPage(context);
    }
}
