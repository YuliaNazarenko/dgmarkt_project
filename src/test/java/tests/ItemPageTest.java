package tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ItemPage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemPageTest extends BaseTest {

    @Test
    @DisplayName("Add item to wish list as guest")
    public void addToWishListGuest() {
        ItemPage itemPage = new ItemPage(context);
        String massage = itemPage.addToWishList();

        assertTrue(("You must login or create an account to save " + itemPage.titleOfItem.getText() + " to your wish list!\n×").
                equalsIgnoreCase(massage));

    }

    @Test
    @Disabled("Browser alert massage")
    @DisplayName("")
    public void addToWishListLogin() {
        LoginPage loginPage = new LoginPage(context);
        loginPage.loginWithValidData();
        ItemPage itemPage = new ItemPage(context);
        String massage = itemPage.addToWishList();

        assertTrue(("Success: You have added " + itemPage.titleOfItem.getText() + " to your wish list!\n×").equalsIgnoreCase(massage));
    }
}
