package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import utils.ConfigurationReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageTest extends BaseTest {
    @Test
    @DisplayName("Change currency Test")
    public void changeCurrency() {
        HomePage homePage = new HomePage(context);
        String currentCurrency = homePage.changeCurrency();

        assertEquals(ConfigurationReader.get("TestChangeCurrency").substring(0, 1), currentCurrency);
    }

    @Test
    @DisplayName("Adding items to shopping bag using search")
    public void addToCart() {

        assertEquals("Success: You have added " +
                        (ConfigurationReader.get("searching_item")) + " to your shopping cart!\n×",
                new HomePage(context).addToCartSearch());
    }
}
