package pages;

import context.TestContext;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utils.ConfigurationReader;

public class CheckoutPage extends BasePage {
    public CheckoutPage(TestContext context) {
        super(context);
    }

    @FindBy(css = "[value='guest']")
    public WebElement radioGuest;

    @FindBy(css = "[value='Continue']")
    public WebElement buttonContinue;

    @FindBy(css = "[id='input-payment-firstname']")
    public WebElement firstName;

    @FindBy(css = "[id='input-payment-lastname']")
    public WebElement lastName;

    @FindBy(css = "[id='input-payment-email']")
    public WebElement email;

    @FindBy(css = "[id='input-payment-telephone']")
    public WebElement telephone;

    @FindBy(css = "[id='input-payment-company']")
    public WebElement company;

    @FindBy(css = "[id='input-payment-address-1']")
    public WebElement address1;

    @FindBy(css = "[id='input-payment-address-2']")
    public WebElement address2;

    @FindBy(css = "[id='input-payment-city']")
    public WebElement city;

    @FindBy(css = "[id='input-payment-postcode']")
    public WebElement postCode;

    @FindBy(css = "[id='input-payment-country']")
    public WebElement country;

    @FindBy(css = "[id='input-payment-zone']")
    public WebElement region;

    @FindBy(css = "[id='button-guest']")
    public WebElement buttonGuest;

    @FindBy(css = "[name='comment']")
    public WebElement deliveryMassage;

    @FindBy(css = "[id='button-shipping-method']")
    public WebElement deliveryButton;

    @FindBy(css = "[id='button-payment-method']")
    public WebElement paymentButtonContinue;

    @FindBy(xpath = "//*[@class='alert alert-danger alert-dismissible']")
    public WebElement alertMessage;

    @FindBy(css = "[name='agree']")
    public WebElement agrees;

    @FindBy(css = "[id='button-confirm']")
    public WebElement confirm;

    @FindBy(css = "[class='container-inner']")
    public WebElement finalMessage;

    @Step("Fill the fields in guest checkout form")
    public void fillGuestCheckoutForm() {
        context.wait.until(ExpectedConditions.visibilityOf(radioGuest)).click();
        context.wait.until(ExpectedConditions.visibilityOf(buttonContinue)).click();
        context.wait.until(ExpectedConditions.visibilityOf(firstName)).sendKeys("My first name");
        context.wait.until(ExpectedConditions.visibilityOf(lastName)).sendKeys("My last name");
        context.wait.until(ExpectedConditions.visibilityOf(email)).sendKeys("myemail@mail.com");
        context.wait.until(ExpectedConditions.visibilityOf(telephone)).sendKeys(ConfigurationReader.get("Telephone"));
        context.wait.until(ExpectedConditions.visibilityOf(company)).sendKeys("My Company");
        context.wait.until(ExpectedConditions.visibilityOf(address1)).sendKeys("My street 22");
        context.wait.until(ExpectedConditions.visibilityOf(address2)).sendKeys("apart. 11");
        context.wait.until(ExpectedConditions.visibilityOf(city)).sendKeys("Berlin");
        context.wait.until(ExpectedConditions.visibilityOf(postCode)).sendKeys("12277");

        Select selectCountry = new Select(context.wait.until(ExpectedConditions.visibilityOf(country)));
        selectCountry.selectByVisibleText("Germany");

        Select selectRegion = new Select(context.wait.until(ExpectedConditions.visibilityOf(region)));
        selectRegion.selectByContainsVisibleText("Berlin");

        context.wait.until(ExpectedConditions.visibilityOf(buttonGuest)).click();
        context.wait.until(ExpectedConditions.visibilityOf(deliveryMassage)).sendKeys("Lassen Sie bitte das Paket vor die Tür");
        context.wait.until(ExpectedConditions.visibilityOf(deliveryButton)).click();
        context.wait.until(ExpectedConditions.visibilityOf(paymentButtonContinue)).click();
        context.wait.until(ExpectedConditions.visibilityOf(agrees)).click();
        paymentButtonContinue.click();
        context.wait.until(ExpectedConditions.visibilityOf(confirm)).click();
        context.wait.until(ExpectedConditions.titleIs("Your order has been placed!"));

    }

    @Step("Checks that impossible to make an order without agrees")
    public String makeOrderWithoutAgrees() {
        context.wait.until(ExpectedConditions.visibilityOf(radioGuest)).click();
        context.wait.until(ExpectedConditions.visibilityOf(buttonContinue)).click();
        context.wait.until(ExpectedConditions.visibilityOf(firstName)).sendKeys("My first name");
        context.wait.until(ExpectedConditions.visibilityOf(lastName)).sendKeys("My last name");
        context.wait.until(ExpectedConditions.visibilityOf(email)).sendKeys(ConfigurationReader.get("guestEmail"));
        context.wait.until(ExpectedConditions.visibilityOf(telephone)).sendKeys(ConfigurationReader.get("Telephone"));
        context.wait.until(ExpectedConditions.visibilityOf(company)).sendKeys("My Company");
        context.wait.until(ExpectedConditions.visibilityOf(address1)).sendKeys("My street 22");
        context.wait.until(ExpectedConditions.visibilityOf(address2)).sendKeys("apart. 11");
        context.wait.until(ExpectedConditions.visibilityOf(city)).sendKeys("Berlin");
        context.wait.until(ExpectedConditions.visibilityOf(postCode)).sendKeys("12277");

        Select selectCountry = new Select(context.wait.until(ExpectedConditions.visibilityOf(country)));
        selectCountry.selectByVisibleText("Germany");

        Select selectRegion = new Select(context.wait.until(ExpectedConditions.visibilityOf(region)));
        selectRegion.selectByContainsVisibleText("Berlin");

        context.wait.until(ExpectedConditions.visibilityOf(buttonGuest)).click();
        context.wait.until(ExpectedConditions.visibilityOf(deliveryMassage)).sendKeys("Lassen Sie bitte das Paket vor die Tür");
        context.wait.until(ExpectedConditions.visibilityOf(deliveryButton)).click();
        context.wait.until(ExpectedConditions.visibilityOf(paymentButtonContinue)).click();

        return context.wait.until(ExpectedConditions.visibilityOf(alertMessage)).getText();

    }

}
