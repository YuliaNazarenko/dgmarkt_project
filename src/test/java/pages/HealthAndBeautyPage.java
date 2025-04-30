package pages;

import context.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HealthAndBeautyPage extends BasePage {

    public HealthAndBeautyPage(TestContext context) {
        super(context);
    }

    @FindBy(css = "a[href='Camera']")
    public WebElement menuCategory;

    @FindBy(css = "div.mega-menu-container a[href*='path=62']")
    public WebElement healthAndBeauty;

    @FindBy(css = "//*[contains(text(), 'NSS111')]/ancestor::div[contains(@class, 'caption')]//button")
    public WebElement buttonAddToCart;

    public String chooseByCategory() {
        Actions actions = new Actions(context.driver);
        actions.moveToElement(menuCategory).perform();
        context.wait.until(ExpectedConditions.visibilityOf(healthAndBeauty)).click();
        WebElement buttonAddToCart = context.wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//*[contains(text(), 'NSS111')]/ancestor::div[contains(@class, 'caption')]//button")));
        ((JavascriptExecutor) context.driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", buttonAddToCart);
        actions.moveToElement(buttonAddToCart).perform();
        context.wait.until(ExpectedConditions.elementToBeClickable(buttonAddToCart)).click();

        HomePage homePage = new HomePage(context);
        return context.wait.until(ExpectedConditions.visibilityOf(homePage.alertMessage)).getText();
    }
}
