package pages;

import context.TestContext;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ConfigurationReader;

public class StartPage extends BasePage {
    public StartPage(TestContext context) {
        super(context);
    }

    @FindBy(css = "input[name='email']")
    public WebElement userName;
    @FindBy(css = "input[name='password']")
    public WebElement userPassword;
    @FindBy(css = "input[type='submit']")
    public WebElement submitButton;
    @FindBy(xpath = "//*[@class='a-close-newsletter']")
    public WebElement closeSignUp;

    @Step("Open this site for testing")
    public void startPageLogin() {

        context.wait.until(ExpectedConditions.visibilityOf(userName)).sendKeys(ConfigurationReader.get("userName"));
        userPassword.sendKeys(ConfigurationReader.get("userPassword"));
        context.wait.until(ExpectedConditions.visibilityOf(submitButton));
        submitButton.click();
        closeSignUp.click();
    }
}
