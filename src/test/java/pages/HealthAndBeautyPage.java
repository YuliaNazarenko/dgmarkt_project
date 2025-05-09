package pages;

import context.TestContext;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ConfigurationReader;

public class HealthAndBeautyPage extends BasePage {

    public HealthAndBeautyPage(TestContext context) {
        super(context);
    }

    @FindBy(css = "a[href='Camera']")
    public WebElement menuCategory;

    @FindBy(css = "div.mega-menu-container a[href*='path=62']")
    public WebElement healthAndBeauty;


    @Step("Choose category")
    public void chooseByCategory() {
        Actions actions = new Actions(context.driver);
        actions.moveToElement(menuCategory).perform();
        context.wait.until(ExpectedConditions.visibilityOf(healthAndBeauty)).click();
    }

    @Step("Add to cart from category")
    public String addToCartFromCategory() {
        Actions actions = new Actions(context.driver);
        WebElement addToCartButton = findAddToCartButtonByItem(ConfigurationReader.get("searching_item"));
        actions.moveToElement(addToCartButton).perform();
        context.wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();

        return context.wait.until(ExpectedConditions.visibilityOf(new ItemPage(context).alertMessage)).getText();
    }

    @Step("Find the items LOCATOR in category")
    public WebElement findTheItem(String searchingItem) {

        WebElement item;
        ((JavascriptExecutor) context.driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});",
                item = context.driver.findElement(By.xpath("//*[contains(text(), '" + searchingItem + "')]")));

        return item;
    }

    @Step("Find the LOCATOR 'Add to Cart' button that belong to the item")
    public WebElement findAddToCartButtonByItem(String searchingItem) {

        WebElement button;
        ((JavascriptExecutor) context.driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});",
                button = context.wait.until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//*[contains(text(), '" + searchingItem + "')]" +
                                "/ancestor::div[contains(@class, 'caption')]//button"))));

        return button;
    }
}
