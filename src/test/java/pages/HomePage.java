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

//Elements----------------------------------------------------------

    //Account elements----------------------------------------------

    @FindBy(css = "a[data-toggle='dropdown']")
    public WebElement myAccount;
    @FindBy(css = "a[id=pt-register-link]")
    public WebElement register;
    @FindBy(id = "pt-login-link")
    public WebElement logIn;

    //Currency---------------------------------------------------------------------

    @FindBy(css = "[id='form-currency']")
    public WebElement currency;

    @FindBy(xpath = "//*[@class='currency-select btn btn-link btn-block']")
    public List<WebElement> listOfCurrencies;

    @FindBy(xpath = "//*[@id='form-currency']//button/span[1]")
    public WebElement currentCurrency;

    //Search--------------------------------------------------------------------

    @FindBy(css = "div[class='dropdown-toggle search-button']")
    public WebElement searchOpen;

    @FindBy(css = "input[id = 'text-search']")
    public WebElement inputTextSearch;

    @FindBy(css = "button[id='btn-search-category']")
    public WebElement search;

    @FindBy(xpath = "//a[contains(text(), 'Nicky Clarke')]")
    public List<WebElement> nickyClarkeItems;

    //Shopping Bag-------------------------------------------------------------

    @FindBy(xpath = "//*[@class='btn-group btn-block']")
    public WebElement shoppingBag;

    @FindBy(css = "a[href*='checkout/checkout']")
    public WebElement checkoutButton;

//Steps-------------------------------------

    @Step("Click on My Account")
    public void clickOnMyAccount() {
        context.wait.until(ExpectedConditions.visibilityOf(myAccount)).click();
    }

    @Step("Open login form")
    public void openLoginForm() {
        context.wait.until(ExpectedConditions.visibilityOf(myAccount)).click();
        context.wait.until(ExpectedConditions.visibilityOf(logIn)).click();
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
        context.wait.until(ExpectedConditions.visibilityOf(inputTextSearch)).
                sendKeys(ConfigurationReader.get("searching_item").substring(0, 12));
        search.click();

        String targetItem = (ConfigurationReader.get("searching_item"));

        for (WebElement item : nickyClarkeItems) {
            String itemText = item.getText();
            if (itemText.equalsIgnoreCase(targetItem)) {
                item.click();
                break;
            }
        }
        context.wait.until(ExpectedConditions.visibilityOf(new ItemPage(context).addToCartButton)).click();
        return context.wait.until(ExpectedConditions.visibilityOf(new ItemPage(context).alertMessage)).getText();
    }

    @Step("Add item by link and go to checkout page")
    public void addToCardByLink() {
        context.driver.get(ConfigurationReader.get("item_url"));
        context.wait.until(ExpectedConditions.visibilityOf(new ItemPage(context).addToCartButton)).click();
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
    }
}
