package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageTest extends BaseTest {
    @Test
    @DisplayName("Change currency Test")
    public void changeCurrency() {
        HomePage homePage = new HomePage(context);
        String currentCurrency = homePage.changeCurrency();

        assertEquals("£", currentCurrency);
    }
}
