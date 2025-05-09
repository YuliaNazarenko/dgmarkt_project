package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.HealthAndBeautyPage;
import pages.ItemPage;
import utils.ConfigurationReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealthAndBeautyTest extends BaseTest {

    @Test
    @DisplayName("Add to cart item from category")
    public void addToCardChoosingByCategory() {
        HealthAndBeautyPage healthAndBeautyPage = new HealthAndBeautyPage(context);
        healthAndBeautyPage.chooseByCategory();
        String confirmationMassage = healthAndBeautyPage.addToCartFromCategory();

        assertEquals("Success: You have added " + ConfigurationReader.get("searching_item") + " to your shopping cart!\n×",
                confirmationMassage);
    }

    @Test
    @DisplayName("Add to shopping bag from item cart choosing by category")
    public void addFromItemCart() {
        HealthAndBeautyPage healthAndBeautyPage = new HealthAndBeautyPage(context);
        healthAndBeautyPage.chooseByCategory();
        healthAndBeautyPage.findTheItem(ConfigurationReader.get("searching_item")).click();
        ItemPage itemPage = new ItemPage(context);
        String confirmationMassage = itemPage.addToCart();

        assertEquals("Success: You have added " + ConfigurationReader.get("searching_item") + " to your shopping cart!\n×",
                confirmationMassage);
    }
}
